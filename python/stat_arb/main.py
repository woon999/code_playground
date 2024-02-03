# https://blog.csdn.net/Linli522362242/article/details/121896073
import pandas as pd
import pandas_datareader.data as pdr
import yfinance as yfin

yfin.pdr_override()

print("Hello World")

TRADING_INSTRUMENT =  'CADUSD=X'
SYMBOLS = ['AUDUSD=X', 'GBPUSD=X', 'CADUSD=X', 'CHFUSD=X', 'EURUSD=X', 'JPYUSD=X', 'NZDUSD=X']
START_DATE = '2014-01-01' 
END_DATE = '2024-01-01'

######## 통화 가격 데이터를 읽기 (없는 경우 다운로드)
symbols_data = {}
for symbol in SYMBOLS:
    SRC_DATA_FILENAME = symbol + '_data.pkl'
    try:
        symbol_data = pd.read_pickle(SRC_DATA_FILENAME)
        print('File data found...reading', symbol)
    except FileNotFoundError:
        print('File not found...downloading', symbol)
        symbol_data = pdr.DataReader(symbol, START_DATE, END_DATE)
        symbol_data.to_pickle(SRC_DATA_FILENAME)
    symbols_data[symbol] = symbol_data


######## 통화 쌍 간의 관계를 검사하고자 통화 가격 시각화
import matplotlib.pyplot as plt
import numpy as np
from itertools import cycle

cycol = cycle('bgrcmky')

price_data = pd.DataFrame()
for symbol in SYMBOLS:
    multiplier = 1

    if symbol == 'JPYUSD=X':
        multiplier = 100.0
    
    label = symbol + ' ClosePrice'
    price_data = price_data.assign(label = pd.Series(symbols_data[symbol]['Close'] * multiplier, index=symbols_data[symbol].index))

    ax = price_data['label'].plot(color=next(cycol), lw=2., label=label)
plt.xlabel('Date', fontsize=18)
plt.ylabel('Scaled Price', fontsize=18)
plt.legend(prop = {'size': 18})
plt.show()

######## StatArb 시그널 파라미터 정의
## 이동 평균, 이동 평균과의 가격 편차, 가격 편차 히스토리, 상관관계 

import statistics as stats

# 단순이동평균과 단순이동평균과의 가격 편차를 계산하는 데 사용하는 상수/변수
SMA_NUM_PERIODS = 20 # 룩백 기간
price_history = {} # 가격 히스토리

PRICE_DEV_NUM_PRICES = 200 # SMA로부터의 종가 편차를 수집하는 룩백 기간 
price_deviation_from_sma = {} # SMA로부터의 종가 편차 히스토리

# 수집한 모든 일자의 데이터에 대해 반복 시행한다
num_days = len(symbols_data[TRADING_INSTRUMENT].index)
correlation_history = {} # 상관관계 히스토리
delta_projected_actual_history = {} # 예측된 종가 편차와 실제 종가 편차와의 차이의 히스토리
final_delta_projected_history = [] # 거래 상품(TRADING_INSTRUMENT)에 대한 최종 예측 종가 편차와 실제 종가 편차와의 차이의 히스토리

######## StatArb 트레이딩 파라미터 정의 
# 전략 트레이드, 포지션과 손익(pnl) 관리를 위한 변수
orders = [] # 매수/매도 주문을 추적하는 변수로 매수 주문은 +1, 매도 주문은 -1, 주문이 없는 경우 0
positions = [] # 포지션을 추적하는 변수로 롱 포지션은 양, 숏 포지션은 음, 포지션 청산되거나 없는 경우 0
pnls = [] # 총 손익을 추적하는 변수. 결제 포지션(고정 수익)과 미체결 포지션(시장 가격으로 평가되는 미결제 포지션 손익)의 합이다.

last_buy_price = 0 # 최근 매수 주문 가격 (같은 가격이나 그 주변 가격의 과다 거래를 막는데 사용)
last_sell_price = 0 # 최근 매도 주문 가격 (같은 가격이나 그 주변 가격의 과다 거래를 막는데 사용)
position = 0 # 트레이딩 전략의 현재 포지션
buy_sum_price_qty = 0 # 마지막으로 포지션이 청산된 후 수행된 모든 매수 거래에 대한 매수 거래 가격과 매수 거래량의 곱의 합
buy_sum_qty = 0 # 마지막으로 포지션이 청산된 후 수행된 모든 매수 거래에 대한 매수 거래량의 합
sell_sum_price_qty = 0 # 마지막으로 포지션이 청산된 후 수행된 모든 매도 거래에 대한 매도 거래 가격과 매도 거래량의 곱의 합
sell_sum_qty = 0 # 마지막으로 포지션이 청산된 후 수행된 모든 매도 거래에 대한 매도 거래량의 합
open_pnl = 0 # 시장 가격으로 평가된 미체결/미실현 손익
closed_pnl = 0 # 이제까지 청산/실현 손익 

# 전력 형태/임계값을 정의하는 상수
StatArb_VALUE_FOR_BUY_ENTRY = 0.01 # 그 위로 매수 주문/롱 포지션을 진입하는 StatArb 트레이딩 시그널 값
StatArb_VALUE_FOR_SELL_ENTRY = -0.01 # 그 아래로 매도 주문/숏 포지션을 진입하는 StatArb 트레이딩 시그널 값
MIN_PRICE_MOVE_FROM_LAST_TRADE = 0.01 # 트레이딩을 재개하기 위한 마지막 거래로부터의 최소 가격 변화. 이는 같은 가격 또는 주변의 가격에서의 과다 거래를 방지하기 위한 것이다.
NUM_SHARES_PER_TRADE = 10_000_000 # 모든 거래에 대해 매수/매도하는 통화 수
MIN_PROFIT_TO_CLOSE = 10 # 포지션 청산하고 이익을 고정하기 위한 최소 미결제/미실현 이익

######## StatArb 트레이딩 시그널 계량화와 계산
# 단순 이동 평균(SMA)계산, 롤링 SMA 가격 편차를 계산하는 함수

for i in range(0, num_days):
    close_prices = {}

    # 1. 종가 시리즈를 구축하고, 각 종목에 대한 SMA와 각 종목의 SMA로부터의 가격 편차를 계산한다.
    for symbol in SYMBOLS:
        close_prices[symbol] = symbols_data[symbol]['Close'].iloc[i]
        if not symbol in price_history.keys():
            price_history[symbol] = []
            price_deviation_from_sma[symbol] = []
        
        price_history[symbol].append(close_prices[symbol])
        if len(price_history[symbol]) > SMA_NUM_PERIODS: # 적어도 SMA_NUM_PERIODS만큼의 데이터가 있는 경우에만 SMA를 계산한다.
            del(price_history[symbol][0])
        
        sma = stats.mean(price_history[symbol]) # 롤링 SMA 계산
        price_deviation_from_sma[symbol].append(close_prices[symbol] - sma) # 평균으로부터 가격 편차

        if len(price_deviation_from_sma[symbol]) > PRICE_DEV_NUM_PRICES:
            del(price_deviation_from_sma[symbol][0])

    # 2. CAD/USD(TRADING_INSTRUMENT) 통화 쌍 가격 편차와 다른 통화 쌍 가격 편차 간의 상관관계를 계산한다.
    # 거래 수단과 모든 다른 선도 심벌과의 공분산과 상관계수 계산
    # 또한 예측된 가격 편차를 계산하고, 예측과 실제 가격 편차 간의 델타를 발견한다. 
    projected_dev_from_sma_using = {}
    for symbol in SYMBOLS:
        if symbol == TRADING_INSTRUMENT: # 거래 수단과 그 자체의 관계를 찾을 필요 없음
            continue
        
        correlation_label = TRADING_INSTRUMENT + '<-' + symbol
        if correlation_label not in correlation_history.keys():
            correlation_history[correlation_label] = []
            delta_projected_actual_history[correlation_label] = []

        if len(price_deviation_from_sma[symbol]) < 2: # 공분산/상관계수를 계산하는 데 적어도 2개의 관찰값이 필요하다. 
            correlation_history[correlation_label].append(0)
            delta_projected_actual_history[correlation_label].append(0)
            continue
        
        # 공분산/상관계수 계산
        corr = np.corrcoef(price_deviation_from_sma[TRADING_INSTRUMENT], price_deviation_from_sma[symbol]) 
        cov = np.cov(price_deviation_from_sma[TRADING_INSTRUMENT], price_deviation_from_sma[symbol])
        
        corr_trading_instrument_lead_instrument = corr[0, 1] # 2 시리즈 간의 상관계수를 구한다
        cov_trading_instrument_lead_instrument = cov[0, 0] / cov[0, 1] # 2 시리즈 간의 공분산을 구한다

        correlation_history[correlation_label].append(corr_trading_instrument_lead_instrument) # 상관계수를 기록한다.

        # 예상 가격 변동 계산 
        # 예측된 거래 수단의 가격 편차는 공분산 * 선도 심벌의 가격 편차다.
        projected_dev_from_sma_using[symbol] = price_deviation_from_sma[symbol][-1] * cov_trading_instrument_lead_instrument

        # 델타가 양이면 시그널은 거래 수단 가격이 오른 것보다 더 오르는 것을 제시한다.
        # 델타가 음이면 시그널은 거래 수단 가격이 내려간 것보다 더 하락할 것을 제시한다.
        delta_projected_actual = (projected_dev_from_sma_using[symbol] - price_deviation_from_sma[symbol][-1])
        delta_projected_actual_history[correlation_label].append(delta_projected_actual)

    # 3. 예상과 실제 가격 편차 사이의 개별 델타를 결합해 다른 모든 통화 쌍의 예측 조합인 CAD/USD에 대한 최종 StatARB 시그널 값을 얻는다. 
    # 각 쌍으로부터 가중치 예측, 가중치는 이들 쌍의 상관계수다.
    sum_weights = 0 # 가중치의 합은 각 심벌의 거래 수단과의 상관관계의 합이다.
    for symbol in SYMBOLS:
        if symbol == TRADING_INSTRUMENT:
            continue
        
        correlation_label = TRADING_INSTRUMENT + '<-' + symbol
        sum_weights += abs(correlation_history[correlation_label][-1])

    final_delta_projected = 0 # 다른 모든 심벌로부터의 예측을 가중 평균한 거래 수단의 마지막 가격 편차 예측을 보유한다. 
    close_price = close_prices[TRADING_INSTRUMENT]
    for symbol in SYMBOLS:
        if symbol == TRADING_INSTRUMENT:
            continue
        
        correlation_label = TRADING_INSTRUMENT + '<-' + symbol
        # 상관계수로 심벌의 가중치를 예측한다. 
        final_delta_projected += (abs(correlation_history[correlation_label][-1]) * delta_projected_actual_history[correlation_label][-1])

    # 모든 쌍에 대한 가중 합으로 나눠 정규화한다.
    if sum_weights != 0:
        final_delta_projected /= sum_weights
    else:
        final_delta_projected = 0

    final_delta_projected_history.append(final_delta_projected)

    ######## StatArb 실행 논리 
    # 1. 매도 거래 논리
    if(
        (final_delta_projected < StatArb_VALUE_FOR_SELL_ENTRY and abs(close_price - last_sell_price) > MIN_PRICE_MOVE_FROM_LAST_TRADE) # StatArb가 매도 진입 임계값 위면 매도한다.
        or 
        (position > 0 and (open_pnl > MIN_PROFIT_TO_CLOSE)) # 롱 포지션을 StatArb이 음에서 양으로 변하거나 포지션 이익이 나면 매도해서 청산한다.
    ):
        orders.append(-1) # 거래 표시 
        last_sell_price = close_price # 매도 주문 가격 갱신
        position -= NUM_SHARES_PER_TRADE # 포지션 감소
        sell_sum_price_qty += (close_price * NUM_SHARES_PER_TRADE) # vwap 매도 가격을 업데이트한다. 
        sell_sum_qty += NUM_SHARES_PER_TRADE
        print("Sell ", NUM_SHARES_PER_TRADE, " @ ", close_price, "Position: ", position)
        print("OpenPnL: ", open_pnl, " ClosedPnL: ", closed_pnl, ", Total: ", (open_pnl + closed_pnl))
    # 2. 매수 거래 논리 
    elif(
        (final_delta_projected > StatArb_VALUE_FOR_BUY_ENTRY and abs(close_price - last_buy_price) > MIN_PRICE_MOVE_FROM_LAST_TRADE) # StatArb가 매수 진입 임계값 아래면 매수한다.
        or 
        (position < 0 and (open_pnl > MIN_PROFIT_TO_CLOSE)) # 숏 포지션을 StatArb이 양에서 음으로 변하거나 포지션 이익이 나면 매수해서 청산한다.
    ):
        orders.append(+1) # 거래 표시
        last_buy_price = close_price
        position += NUM_SHARES_PER_TRADE # 이 거래 크기만큼 포지션을 증가한다.
        buy_sum_price_qty += (close_price * NUM_SHARES_PER_TRADE) # update the vwap buy price
        buy_sum_qty += NUM_SHARES_PER_TRADE
        print("Buy ", NUM_SHARES_PER_TRADE, " @ ", close_price, "Position: ", position)
        print("OpenPnL: ", open_pnl, " ClosedPnL: ", closed_pnl, ", Total: ", (open_pnl + closed_pnl))
    else: 
        orders.append(0) # 거래 없음
    positions.append(position)

    # 3. 포지션 관리 및 PnL 업데이트
    open_pnl = 0
    if position > 0: # long 
        if sell_sum_qty > 0: # 롱 포지션과 이에 대한 매도 포지션을 수행하면 롱 포지션에 대해 얼마나 매도했는가를 기반으로 그만큼 청산한다.
            open_pnl = abs(sell_sum_qty) * (sell_sum_price_qty/sell_sum_qty - buy_sum_price_qty/buy_sum_qty)
        
        # 나머지 포지션을 시장 가격으로 평가한다. 즉 현재 가격으로 청산하면 얻었을 손익을 표시한다. 
        open_pnl += abs(sell_sum_qty - position) * (close_price - buy_sum_price_qty / buy_sum_qty)
    elif position < 0: # short 
        if buy_sum_qty > 0: # 숏 포지션과 이에 대한 매수 포지션을 수행하면 숏 포지션에 대해 얼마나 매수했는가를 기반으로 그만큼 청산한다.
            open_pnl = abs(buy_sum_qty) * (sell_sum_price_qty/sell_sum_qty - buy_sum_price_qty/buy_sum_qty)
        
        # 나머지 포지션을 시장 가격으로 평가한다. 즉 현재 가격으로 청산하면 얻었을 손익을 표시한다.
        open_pnl += abs(buy_sum_qty - position) * (sell_sum_price_qty/sell_sum_qty - close_price)
    else:
        closed_pnl += (sell_sum_price_qty - buy_sum_price_qty)
        buy_sum_prod_qty = 0
        buy_sum_qty = 0
        sell_sum_price_qty = 0
        sell_sum_qty = 0
        last_buy_price = 0
        last_sell_price = 0

    pnls.append(closed_pnl + open_pnl)

######## StatArb 시그널 및 전략 성능 분석
# 거래 수단과 다른 통화 쌍과의 상관관계를 시각화한다.
correlation_data = pd.DataFrame()
for symbol in SYMBOLS:
    if symbol == TRADING_INSTRUMENT:
        continue

    correlation_label = TRADING_INSTRUMENT + '<-' + symbol
    correlation_data = correlation_data.assign(label = pd.Series(correlation_history[correlation_label], index=symbols_data[symbol].index))
    ax = correlation_data['label'].plot(color=next(cycol), lw=2., label='Correlation ' + correlation_label)


for i in np.arange(-1, 1, 0.25):
    plt.axhline(y=i, lw=0.5, color='k')
plt.legend()
plt.show()

# 각 통화 쌍에 의해 제공되는 StatArb 시그널을 시각화한다.
delta_projected_actual_data = pd.DataFrame()
for symbol in SYMBOLS:
    if symbol == TRADING_INSTRUMENT:
        continue

    projection_label = TRADING_INSTRUMENT + '<-' + symbol
    delta_projected_actual_data = delta_projected_actual_data.assign(StatArbTradingSignal = pd.Series(delta_projected_actual_history[projection_label], index=symbols_data[TRADING_INSTRUMENT].index))
    ax = delta_projected_actual_data['StatArbTradingSignal'].plot(color=next(cycol), lw=1., label='StatArbTradingSignal ' + projection_label)
plt.legend()
plt.show()

# 종가, 거래 포지션, PnL을 시각화한다.
delta_projected_actual_data = delta_projected_actual_data.assign(ClosePrice = pd.Series(symbols_data[TRADING_INSTRUMENT]['Close'], index=symbols_data[TRADING_INSTRUMENT].index))
delta_projected_actual_data = delta_projected_actual_data.assign(FinalStatArbTradingSignal = pd.Series(final_delta_projected_history, index=symbols_data[TRADING_INSTRUMENT].index))
delta_projected_actual_data = delta_projected_actual_data.assign(Trades = pd.Series(orders, index=symbols_data[TRADING_INSTRUMENT].index))
delta_projected_actual_data = delta_projected_actual_data.assign(Position = pd.Series(positions, index=symbols_data[TRADING_INSTRUMENT].index))
delta_projected_actual_data = delta_projected_actual_data.assign(Pnl = pd.Series(pnls, index=symbols_data[TRADING_INSTRUMENT].index))

plt.plot(delta_projected_actual_data.index, delta_projected_actual_data.ClosePrice, color='k', lw=1., label='ClosePrice')
plt.plot(delta_projected_actual_data.loc[delta_projected_actual_data.Trades == 1].index, 
        delta_projected_actual_data.ClosePrice[delta_projected_actual_data.Trades == 1], color='r', lw=0, marker='^', markersize=7, label='buy')
plt.plot(delta_projected_actual_data.loc[delta_projected_actual_data.Trades == -1].index,
        delta_projected_actual_data.ClosePrice[delta_projected_actual_data.Trades == -1], color='g', lw=0, marker='v', markersize=7, label='sell') 
plt.legend()
plt.show()
