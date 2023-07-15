package ch9.pattern.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// 인스턴스화 로직을 클라이언트에 노출하지 않고 객체를 만들 때 팩토리 디자인 패턴을 사용한다.
abstract class Product{ }
class Loan extends Product{}
class Stock extends Product{}
class Bond extends Product{}

class ProductFactory{
	private final static Map<String, Supplier<Product>> map = new HashMap<>();
	static {
		map.put("loan", Loan::new);
		map.put("stock", Stock::new);
		map.put("bond", Bond::new);
	}

	public static Product createProduct(String name){
		Supplier<Product> p = map.get(name);
		if(p != null) return p.get();
		throw new RuntimeException("No such product " + name);
	}
}

public class Main {
	public static void main(String[] args) {
		Product p = ProductFactory.createProduct("loan");
		System.out.println("p = " + p);
	}
}
