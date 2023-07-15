package ch10.dsl.mixed;

import ch10.domain.Stock;
import ch10.domain.Trade;

public class StockBuilder {
	private final TradeBuilder builder;
	private final Trade trade;
	private final Stock stock = new Stock();

	public StockBuilder(TradeBuilder builder, Trade trade, String symbol) {
		this.builder = builder;
		this.trade = trade;
		stock.setSymbol(symbol);
	}

	public TradeBuilder on(String market){
		stock.setMarket(market);
		trade.setStock(stock);
		return builder;
	}
}
