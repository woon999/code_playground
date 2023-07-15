package ch10;

import static ch10.dsl.mixed.MixedBuilder.*;
import static ch10.dsl.nestedfunction.NestedFunctionOrderBuilder.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ch10.domain.Order;
import ch10.domain.Stock;
import ch10.domain.Trade;
import ch10.dsl.lambda.LambdaOrderBuilder;
import ch10.dsl.methodchain.MethodChainOrderBuilder;
import ch10.dsl.mixed.MixedBuilder;
import ch10.dsl.nestedfunction.NestedFunctionOrderBuilder;
import ch10.methodreference.Tax;
import ch10.methodreference.TaxCalculator;
import ch10.methodreference.TaxCalculator_methodRef;

public class Main {

	@Test
	void general_DSL() {
		Order order = new Order();
		order.setCustomer("BigBank");

		Trade trade1 = new Trade();
		trade1.setType(Trade.Type.BUY);

		Stock stock1 = new Stock();
		stock1.setSymbol("IBM");
		stock1.setMarket("NYSE");

		trade1.setStock(stock1);
		trade1.setPrice(125.00);
		trade1.setQuantity(80);
		order.addTrade(trade1);

		Trade trade2 = new Trade();
		trade2.setType(Trade.Type.BUY);

		Stock stock2 = new Stock();
		stock2.setSymbol("GOOGLE");
		stock2.setMarket("NASDAQ");

		trade2.setStock(stock2);
		trade2.setPrice(375.00);
		trade2.setQuantity(50);
		order.addTrade(trade2);

		System.out.println(order);
	}

	/**
	 * 여러 빌드 클래스 특히 두 개의 거래 빌더를 따로 만듦으로써 사용자가 미리 지정된 절차에 따라 플루언트 API의 메서드를 호출하도록 강제한다.
	 * 덕분에 사용자가 다음 거래를 설정하기 전에 기존 거래를 올바로 설정하게 된다.
	 * 이 접근 방법은 주문에 사용한 파라미터가 빌더 내부로 국한된다는 다른 잇점도 제공한다.
	 * 이 접근 방법은 정적 메서드 사용을 최소화하고 메서드 이름이 인수의 이름을 대신하도록 만듦으로 이런 형식의 DSL의 가독성을 개선하는 효과를 더한다.
	 * 마지막으로 이런 기법을 적용한 플루언트 DSL에는 분법적 잡음이 최소화된다.
	 *
	 * 단점: 빌더를 구현해야 한다는 것이 메서드 체인의 단점이다. 상위 수준의 빌더를 하위 수준의 빌더와 연결할 접착 많은 접착 코드가 필요하다.
	 * 		 도메인의 객체의 중첩 구조와 일치하게 들여쓰기를 강제하는 방법이 없다는 것도 단점이다.
	 */
	@Test
	void methodChain_DSL() {
		Order order = MethodChainOrderBuilder.forCustomer("BigBank")
			.buy(80)
			.stock("IBM")
			.on("NYSE")
			.at(125.00)
			.sell(50)
			.stock("GOOGLE")
			.on("NASDAQ")
			.at(375.00)
			.end();

		System.out.println(order);
	}

	/**
	 * 메서드 체인에 비해 함수의 중첩 방식이 도메인 객체 계층 구조에 그대로 반영된다는 것이 장점이다.
	 *
	 * 문제점: 결과 DSL에 더 많은 괄호를 사용해야 한다. 더욱이 인수 목록을 정적 메서드에 넘겨줘야 한다는 제약도 있다.
	 * 		   도메인 객체에 선택 사항 필드가 있으면 인수를 생략할 수 있으므로 이 가능성을 처리할 수 있도록 여러 메서드 오버라이드를 구현해야 한다.
	 * 		   마지막으로 인수의 의미가 이름이 아니라 위치에 의해 정의되었다.
	 * 		   at(), on() 메서드에서 했던 것처럼 인수의 역할을 확실하게 만드는 여러 더미 메서드를 이용해 마지막 문제를 조금은 완화할 수 있다.
	 */
	@Test
	void nestedFunction_DSL() {
		Order order = NestedFunctionOrderBuilder.order("BigBank",
			buy(80,
				stock("IBM", on("NYSE")), at(125.00)),
			sell(50,
				stock("GOOGLE", on("NASDAQ")), at(375.00))
		);
		System.out.println(order);
	}

	/**
	 * 람다 표현식을 이용 DSL을 만드려면 람다 표현식을 받아 실행해 도메인 모델을 만들어내는 여러 빌더를 구현해야 한다.
	 *
	 * 이 패턴은 이전 두 가지 DSL형식의 두 가지 장점을 더한다.
	 * - 메서드 체인 패턴처럼 플루언트 방식으로 거래 주문을 정의할 수 있다.
	 * - 또한 중첩 함수 형식처럼 다양한 람다 표현식의 중첩 수준과 비슷하게 도메인 객체의 계층 구조를 유지한다.
	 *
	 * 단점: 많은 설정 코드가 필요하며 DSL 자체가 자바 8 람다 표현식 문법에 의한 잡음의 영향을 받는다는 것이 이 패턴의 단점이다.
	 */
	@Test
	void lambda_DSL() {
		Order order = LambdaOrderBuilder.order(o -> {
			o.forCustomer("BigBank");
			o.buy(t -> {
				t.quantity(80);
				t.price(125.00);
				t.stock(s -> {
					s.symbol("IBM");
					s.market("NYSE");
				});
			});
			o.sell(t -> {
				t.quantity(50);
				t.price(375.00);
				t.stock(s -> {
					s.symbol("GOOGLE");
					s.market("NASDAQ");
				});
			});
		});

		System.out.println(order);
	}

	/**
	 * 중첩된 함수 패턴 + 람다 기법
	 *
	 * 가독성있는 DSL을 만든다.
	 *
	 * 단점: DSL이 여러가지 기법을 혼용하고 있어 한 가지 기법을 적용한 DSL에 비해 사용자가 DSL을 배우는 데 시간이 오래걸린다.
	 */
	@Test
	void mixed_nestedBuilder_DSL(){
		Order order = MixedBuilder.forCustomer("BigBank", // 최상의 수준 주문의 속성을 지정하는 중첩 함수
							buy(t-> t.quantity(80)  			    // 한 개의 주문을 만드는 람다 표현식
									.stock("IBM") 			// 거래 객체를 만드는 람다 표현식 바디의 메서드 체인
									.on("NYSE")
									.at(125.00)),
							sell(t->t.quantity(50)
									.stock("GOOGLE")
									.on("NASDAQ")
									.at(375.00))
		);

		System.out.println(order);
	}


	@Test
	void methodReference(){
		Order order = MixedBuilder.forCustomer("BigBank", // 최상의 수준 주문의 속성을 지정하는 중첩 함수
			buy(t-> t.quantity(80)  			    // 한 개의 주문을 만드는 람다 표현식
				.stock("IBM") 			// 거래 객체를 만드는 람다 표현식 바디의 메서드 체인
				.on("NYSE")
				.at(125.00)),
			sell(t->t.quantity(50)
				.stock("GOOGLE")
				.on("NASDAQ")
				.at(375.00))
		);

		double value = new TaxCalculator().withTaxRegional()
			.withTaxSurcharge()
			.calculate(order);

		System.out.println(value);

		double value2 = new TaxCalculator_methodRef().with(Tax::regional)
			.with(Tax::surcharge)
			.calculate(order);

		System.out.println(value2);


		Assertions.assertEquals(value, value2);
	}

}
