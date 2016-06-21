package test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dbManagement.LawsManagement;

public class UnitTest {
	private String way;
	private String originCountry;
	private String declarationDateStr;
	private String productType;
	private String productName;
	private String manufacturer;
	private String producingCountry;
	private int weight;
	private int number;
	private long price;
	

	
	public void test() throws Exception {
		
		test22();
		LawsManagement lawsManagement = new LawsManagement();
		ArrayList<String> list = lawsManagement.findPermitsNeeded(way, originCountry, declarationDateStr,
			productType, productName, manufacturer, weight, number, price, producingCountry);
	
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + "." + list.get(i));
		}
	
	}
	
	public void test1() {
		way = "Airway";
		originCountry = "USA";
		declarationDateStr = "2016-06-21";
		productType = "car";
		productName = "chevrolet camaro";
		manufacturer = "chevrolet";
		producingCountry = "USA";
		weight = -1;
		number = 6;
		price = 50000;
	}
	
	public void test2() {
		test1();
		number = 10;
	}
	
	public void test3() {
		test1();
		number = 11;
	}
	
	public void test4() {
		test1();
		number = 40;
	}
	
	public void test5() {
		test1();
		declarationDateStr = "2016-06-19";
	}
	public void test6() {
		test1();
		declarationDateStr = "2016-06-20";
	}
	public void test7() {
		test1();
		declarationDateStr = "2016-06-21";
	}
	public void test8() {
		test1();
		productType = "mobile";
	}
	public void test9() {
		test1();
		productType = "mobile";
		price = 99;
	}
	public void test10() {
		test1();
		productType = "mobile";
		price = 200;
	}
	public void test11() {
		test1();
		productType = "mobile";
		price = 201;
	}
	public void test12() {
		test1();
		productType = "mobile";
		price = 1000;
	}
	public void test13() {
		test1();
		producingCountry = "France";
	}
	public void test14() {
		test1();
		way = "Seaway";
		originCountry = "UAE";
	}
	public void test15() {
		way = "Seaway";
		originCountry = "India";
		declarationDateStr = "2016-06-28";
		productType = "rice";
		productName = "mohsen rice";
		manufacturer = "mohsen";
		producingCountry = "India";
		weight = 50;
		number = -1;
		price = 2;
	}
	public void test16() {
		test15();
		weight = 51;
	}
	public void test17() {
		test15();
		weight = 501;
	}
	public void test18() {
		test15();
		weight = 2000;
	}
	public void test19() {
		test15();
		weight = 2000;
	}
	public void test20() {
		test15();
		productType = "wheat";
		weight = 100;
	}
	public void test21() {
		test15();
		producingCountry = "USA";
		declarationDateStr = "2016-06-28";
		productType = "wheat";
		weight = 100;
	}
	public void test22() {
		test15();
		producingCountry = "USA";
		productType = "wheat";
		weight = 220;
	}
	
	public void test26() throws ParseException {
		DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		
		Date dateFrom;
		dateFrom = df.parse("0000-01-01");
		
		Date dateTill;
		dateTill = df.parse("2016-07-05");
		
		Date decDate;
		decDate = df.parse("2016-06-21");
	
		System.out.println(decDate.compareTo(dateFrom));
		System.out.println(decDate.compareTo(dateTill));
	}
	
}
