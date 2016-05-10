package javaClasses;

import java.util.Date;

public class License {
	private String trackingCode;
	private Date expirationDate;
	
	public String getTrackingCode() {return trackingCode;}
	public void setExpirationDate(Date expirationDate) {this.expirationDate = expirationDate;}
	
	public void setTrackingCode(String trackingCode) {this.trackingCode = trackingCode;}
	public Date getExpirationDate() {return expirationDate;}
}
