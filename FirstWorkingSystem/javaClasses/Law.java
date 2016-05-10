package javaClasses;

import java.util.ArrayList;

public class Law {
	private long ID;
	private String date;
	private ArrayList<Limitation> limitaions = new ArrayList<>();
	
	public Law(long ID, String date, ArrayList<Limitation> limitations) {
		this.ID = ID;
		this.date = date;
		this.limitaions = limitations;
	}
	
	public long getID() {return this.ID;}
	public String getDate() {return this.date;}
	public ArrayList<Limitation> getLimitations() {return this.limitaions;}
}
