package javaClasses;

public class Product {
	private String name;
	private long weight;
	private long price;
	
	public Product(String name, long weight, long price) {
		this.name = name;
		this.weight = weight;
		this.price = price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public String getName() {return this.name;}
	public long getWeight() {return this.weight;}
	public long getPrice() {return this.price;}
}
