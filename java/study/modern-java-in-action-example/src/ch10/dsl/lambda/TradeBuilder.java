package ch10.dsl.lambda;

import java.util.function.Consumer;

import ch10.domain.Trade;

public class TradeBuilder {
	private Trade trade = new Trade();

	public void quantity(int quantity){
		trade.setQuantity(quantity);
	}

	public void price(double price){
		trade.setPrice(price);
	}

	public void stock(Consumer<StockBuilder> consumer){
		StockBuilder builder = new StockBuilder();
		consumer.accept(builder);
		trade.setStock(builder.getStock());
	}

	public Trade getTrade() {
		return trade;
	}

}
