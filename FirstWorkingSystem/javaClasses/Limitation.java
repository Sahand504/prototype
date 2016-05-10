package javaClasses;

public class Limitation {
	private String Manufacturer;
	private long minValue;
	private long maxValue;
	private String originCountry;
	private long productsNum;
	private long productsWeight;
	
	public void setManufacturer(String manufacturer) {Manufacturer = manufacturer;}
	public void setMaxValue(long maxValue) {this.maxValue = maxValue;}
	public void setMinValue(long minValue) {this.minValue = minValue;}
	public void setOriginCountry(String originCountry) {this.originCountry = originCountry;}
	public void setProductsNum(long productsNum) {this.productsNum = productsNum;}
	public void setProductsWeight(long productsWeight) {this.productsWeight = productsWeight;}
	
	public String getManufacturer() {return Manufacturer;}
	public long getMaxValue() {return maxValue;}
	public long getMinValue() {return minValue;}
	public String getOriginCountry() {return originCountry;}
	public long getProductsNum() {return productsNum;}
	public long getProductsWeight() {return productsWeight;}
}
