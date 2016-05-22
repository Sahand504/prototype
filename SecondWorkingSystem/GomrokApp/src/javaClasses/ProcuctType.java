package javaClasses;

import java.util.ArrayList;

public class ProcuctType {
	private String type;
	private ArrayList<Product> products;
	
	public ProcuctType(String type) {this.setType(type);}
	
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	public void addProduct(Product p) {products.add(p);}
	public  ArrayList<Product> getProducts() {return this.products;}
}
