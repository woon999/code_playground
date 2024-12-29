package ch10.dsl.methodchain;

import ch10.domain.Order;
import ch10.domain.Trade;

public class MethodChainOrderBuilder {
	public final Order order = new Order();

	private MethodChainOrderBuilder(String customer){
		order.setCustomer(customer);
	}

	public static MethodChainOrderBuilder forCustomer(String customer){
		return new MethodChainOrderBuilder(customer);
	}

	public TradeBuilder buy(int quantity){
		return new TradeBuilder(this, Trade.Type.BUY, quantity);
	}

	public TradeBuilder sell(int quantity){
		return new TradeBuilder(this, Trade.Type.SELL, quantity);
	}

	public MethodChainOrderBuilder addTrade(Trade trade) {
		order.addTrade(trade);
		return this;
	}

	public Order end(){
		return order;
	}
}
