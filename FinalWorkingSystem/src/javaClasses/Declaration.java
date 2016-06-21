package javaClasses;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Declaration {
	private long id;
	private String way;
	private String originCountry;
	private String startedDate;
	private ArrayList<Goods> products = new ArrayList<>();
	private String merchantID;
	private String merchantFirst;
	private String merchantLast;
	private int conf;
	
	private StringProperty idSP;
	private StringProperty merchantIDSP;
	private StringProperty merchantFirstSP;
	private StringProperty merchantLastSP;
	private StringProperty confSP;
	
	private StringProperty startedDateSP;
	
//	public Declaration(long id, String way, String originCountry, String startedDate, String merchantID) {
//		this.id = id;
//		this.way = way;
//		this.originCountry = originCountry;
//		this.startedDate = startedDate;
//		this.setMerchantID(merchantID);
//	}
	
	public Declaration(long id, String merchantID, String merchantFirst, String merchantLast, String startedDate, int conf) {
		this.id = id;
		this.setMerchantID(merchantID);
		this.startedDate = startedDate;
		this.merchantFirst = merchantFirst;
		this.merchantLast = merchantLast;
		this.conf = conf;
		
		this.idSP = new SimpleStringProperty(new Long(id).toString());
		this.merchantIDSP = new SimpleStringProperty(merchantID);
		this.startedDateSP = new SimpleStringProperty(startedDate);
		this.merchantFirstSP = new SimpleStringProperty(merchantFirst);
		this.merchantLastSP = new SimpleStringProperty(merchantLast);
		this.confSP = new SimpleStringProperty(new Integer(conf).toString());
	}
	
//	public void add(long id, String merchantID, String startedDate) {
//		this.id = id;
//		this.merchantID = merchantID;
//		this.startedDate = startedDate;
//	}
	
	public void addProduct(Goods product) {this.products.add(product);}
	public void setMerchantID(String merchantID) {this.merchantID = merchantID;}
	
	public StringProperty getIDSP() {return this.idSP;}
	public StringProperty getMerchantIDSP() {return this.merchantIDSP;}
	public StringProperty getMerchantFirstSP() {return this.merchantFirstSP;}
	public StringProperty getMerchantLastSP() {return this.merchantLastSP;}
	public StringProperty getStartedDateSP() {return this.startedDateSP;}
	public StringProperty getConfSP() {return this.confSP;}
	
	public long getid() {return this.id;}
	public String getWay() {return this.way;}
	public String getOriginCountry() {return this.originCountry;}
	public String getStartedDate() {return this.startedDate;}
	public ArrayList<Goods> getProducts() {return this.products;}
	public String getMerchantID() {return this.merchantID;}
	public String getMerchantFirst() {return this.merchantFirst;}
	public String getMerchantLast() {return this.merchantLast;}
	public int getConf() {return this.conf;}
}
