package javaClasses;

import java.util.ArrayList;

public class Declaration {
	private long ID;
	private String way;
	private String originCountry;
	private String dateStarted;
	private ArrayList<Product> products = new ArrayList<>();
	private Merchant merchant;
	
	public Declaration(long ID, String way, String originCountry, String dateStarted, Merchant merchant) {
		this.ID = ID;
		this.way = way;
		this.originCountry = originCountry;
		this.dateStarted = dateStarted;
		this.setMerchant(merchant);
	}
	public void addProduct(Product product) {this.products.add(product);}
	public void setMerchant(Merchant merchant) {this.merchant = merchant;}
	
	public long getID() {return this.ID;}
	public String getWay() {return this.way;}
	public String getOriginCountry() {return this.originCountry;}
	public String getStartedDate() {return this.dateStarted;}
	public ArrayList<Product> getProducts() {return this.products;}
	public Merchant getMerchant() {return merchant;}
}
