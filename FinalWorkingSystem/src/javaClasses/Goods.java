package javaClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Goods {
	
	private String type;
	private String name;
	private String manufacturer;
	private int weight;
	private int number;
	private long price;
	private String producingCountry;
	
	private StringProperty typeSP;
	private StringProperty nameSP;
	private StringProperty manufacturerSP;
	private StringProperty weightSP;
	private StringProperty numberSP;
	private StringProperty priceSP;
	private StringProperty producingCountrySP;
	
	public Goods(String type, String name, String manufacturer, int weight, int number, long price, String producingCountry) {
		this.type = type;
		this.name = name;
		this.manufacturer = manufacturer;
		this.weight = weight;
		this.number = number;
		this.price = price;
		this.producingCountry = producingCountry;
		
		this.typeSP = new SimpleStringProperty(type);
		this.nameSP = new SimpleStringProperty(name);
		this.manufacturerSP = new SimpleStringProperty(manufacturer);
		this.weightSP = new SimpleStringProperty(new Integer(weight).toString());
		this.numberSP = new SimpleStringProperty(new Integer(number).toString());
		this.priceSP = new SimpleStringProperty(new Long(price).toString());
		this.producingCountrySP = new SimpleStringProperty(producingCountry);
	}
	
	public String getType() {return this.type;}
	public String getName() {return this.name;}
	public String getManufacturer() {return this.manufacturer;}
	public int getWeight() {return this.weight;}
	public int getNumber() {return this.number;}
	public long getPrice() {return this.price;}
	public String getProducingCountry() {return this.producingCountry;}
	
	public StringProperty getTypeSP() {return this.typeSP;}
	public StringProperty getNameSP() {return this.nameSP;}
	public StringProperty getManufacturerSP() {return this.manufacturerSP;}
	public StringProperty getWeightSP() {return this.weightSP;}
	public StringProperty getNumberSP() {return this.numberSP;}
	public StringProperty getPriceSP() {return this.priceSP;}
	public StringProperty getProducingCountrySP() {return this.producingCountrySP;}
	
	public void setPrice(long price) {this.price = price;}
}
