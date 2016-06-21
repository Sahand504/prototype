package javaClasses;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private String username;
	private String password;
	private String type;
	private String ministry;
	
	private StringProperty usernameSP;
	private StringProperty passwordSP;
	private StringProperty typeSP;
	private StringProperty ministrySP;
	
	public User(String username, String password, String type, String ministry) {
		this.username = username;
		this.password = password;
		this.type = type;
		this.ministry = ministry;
		
		this.usernameSP = new SimpleStringProperty(username);
		this.passwordSP = new SimpleStringProperty(password);
		this.typeSP = new SimpleStringProperty(type);
		this.ministrySP = new SimpleStringProperty(ministry);
	}
	
	public void setUsername(String username) {this.username = username;}
	public void setPassword(String password) {this.password = password;}
	public void setType(String type) {this.type = type;}
	public void setMinistry(String ministry) {this.ministry = ministry;}
	
	public String getUsername() {return this.username;}
	public String getPassword() {return this.password;}
	public String getType() {return this.type;}
	public String getMinistry() {return this.ministry;}
	
	public StringProperty getUsernameStringProperty() {return this.usernameSP;}
	public StringProperty getPasswordStringProperty() {return this.passwordSP;}
	public StringProperty getTypeStringProperty() {return this.typeSP;}
	public StringProperty getMinistryStringProperty() {return this.ministrySP;}
}
