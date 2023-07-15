package ch10.dsl.mixed;

import java.util.function.Consumer;
import java.util.stream.Stream;

import ch10.domain.Order;
import ch10.domain.Trade;

public class MixedBuilder {

	public static Order forCustomer(String customer, TradeBuilder... builders){
		Order order = new Order();
		order.setCustomer(customer);
		Stream.of(builders).forEach(b -> order.addTrade(b.getTrade()));
		return order;
	}

	public static TradeBuilder buy(Consumer<TradeBuilder> consumer){
		return buildTrade(consumer, Trade.Type.BUY);
	}

	public static TradeBuilder sell(Consumer<TradeBuilder> consumer){
		return buildTrade(consumer, Trade.Type.SELL);
	}

	private static TradeBuilder buildTrade(Consumer<TradeBuilder> consumer, Trade.Type type) {
		TradeBuilder builder = new TradeBuilder();
		builder.getTrade().setType(type);
		consumer.accept(builder);
		return builder;
	}
}
