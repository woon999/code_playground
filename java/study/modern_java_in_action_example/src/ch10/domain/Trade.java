package ch10.domain;

public class Trade {
	public enum Type {BUY, SELL}

	private Type type;

	private Stock stock;
	private int quantity;
	private double price;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getValue() {
		return quantity * price;
	}

	@Override
	public String toString() {
		return "\n\t\tTrade{" +
			"type=" + type +
			", stock=" + stock +
			", quantity=" + quantity +
			", price=" + price +
			"}\n";
	}
}

