package ch10.dsl.methodchain;

import ch10.domain.Trade;

public class TradeBuilder {
	private final MethodChainOrderBuilder builder;
	private final Trade trade = new Trade();

	TradeBuilder(MethodChainOrderBuilder builder, Trade.Type type, int quantity) {
		this.builder = builder;
		trade.setType(type);
		trade.setQuantity(quantity);
	}

	public StockBuilder stock(String symbol){
		return new StockBuilder(builder, trade, symbol);
	}
}
