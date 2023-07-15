package ch10.methodreference;

import java.util.function.DoubleUnaryOperator;

import ch10.domain.Order;

public class TaxCalculator_methodRef {
	public DoubleUnaryOperator taxFunction = d -> d;
	public TaxCalculator_methodRef with(DoubleUnaryOperator f){
		taxFunction = taxFunction.andThen(f);
		return this;
	}

	public double calculate(Order order){
		return taxFunction.applyAsDouble(order.getValue());
	}
}
