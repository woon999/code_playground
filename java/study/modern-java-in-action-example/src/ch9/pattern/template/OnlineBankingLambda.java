package ch9.pattern.template;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// 알고리즘의 개요를 제시한 다음에 알고리즘의 일부를 고칠 수 있는 유연함을 제공해야 할 때 템플릿 메서드 디자인 패턴을 제공한다.
class Customer{
	private int id;
	private String name;
	private int condition;

	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

class Database{
	private static Map<Integer, Customer> database = new HashMap<>();
	public static void addCustomer(Customer customer){
		database.putIfAbsent(customer.getId(), customer);
	}

	public static Customer getCustomerWithId(int id) {
		return database.get(id);
	}
}

abstract class OnlineBanking {
	 public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
	 	Customer c = Database.getCustomerWithId(id);
		 makeCustomerHappy.accept(c);

	 }
}

public class OnlineBankingLambda {
	public void processCustomer(int id, Consumer<Customer> makeCustomerHappy){
		Customer c = Database.getCustomerWithId(id);
		makeCustomerHappy.accept(c);

	}

	public static void main(String[] args) {
		Database.addCustomer(new Customer(1337, "steve"));
		new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("c.getName() = " + c.getName()));
	}
}
