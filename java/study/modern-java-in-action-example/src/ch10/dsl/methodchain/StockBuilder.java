package ch10.dsl.methodchain;

import ch10.domain.Stock;
import ch10.domain.Trade;

public class StockBuilder {
	private final MethodChainOrderBuilder builder;
	private final Trade trade;
	private final Stock stock = new Stock();

	StockBuilder(MethodChainOrderBuilder builder, Trade trade, String symbol){
		this.builder = builder;
		this.trade = trade;
		stock.setSymbol(symbol);
	}

	public TradeBuilderWithStock on(String market){
		stock.setMarket(market);
		trade.setStock(stock);
		return new TradeBuilderWithStock(builder, trade);
	}
}
