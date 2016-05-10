package javaClasses;

import java.util.ArrayList;


public class Merchant {
	private String firstName;
	private String lastName;
	private String nationalID;
	private ArrayList<Declaration> declarations = new ArrayList<>();
	
	public Merchant(String firstName, String lastName, String nationalID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationalID = nationalID;
	}
	
//	public void setFirstName(String firstName) {this.firstName = firstName;}
//	public void setLastName(String lastName) {this.lastName = lastName;}
//	public void setNationalID(String nationalID) {this.nationalID = nationalID;}
	
	public void addDeclaration(Declaration declaration) {this.declarations.add(declaration);}

	public String getFirstName() {return this.firstName;}
	public String getLastName() {return this.lastName;}
	public String getNationalID() {return nationalID;}
	public ArrayList<Declaration> getDelarations() {return this.declarations;}
}
