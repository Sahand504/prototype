package dbManagement;

import java.sql.*;
import java.util.ArrayList;

import application.Show;
import javaClasses.User;
import javafx.stage.Stage;
import popupControllers.Popupp;

public class UsersManagement extends DBManagement{

	public void deleteUser(User user) throws Exception {
		Connection con = getConnection();
		PreparedStatement delete = con.prepareStatement("DELETE FROM users WHERE username = '" + user.getUsername() + "'");
		delete.executeUpdate();
	}
	
	public void updateUser(User user) throws Exception {
		Connection con = getConnection();
		PreparedStatement update = con.prepareStatement("UPDATE users SET"
				+ " username='" + user.getUsername() 
				+ "', password='" + user.getPassword()
				+ "', type='" + user.getType()
				+ "', ministry='" + user.getMinistry()
				+ "' WHERE username='" + user.getUsername() + "'"
				);
		update.executeUpdate();
	}
	
	public ArrayList<User> getUsers() throws Exception {
		ArrayList<User> users = new ArrayList<>();
		Connection con = getConnection();
		PreparedStatement find = con.prepareStatement("SELECT * FROM users");
		ResultSet resultSet = find.executeQuery();
		while(resultSet.next()) {
			users.add(new User(resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("type"), resultSet.getString("ministry")));
		}
		return users;
	}
	
	public boolean checkUser(String username) throws Exception {
		Connection con = getConnection();
		PreparedStatement find = con.prepareStatement("SELECT * FROM users WHERE username = '" + username + "'");
		ResultSet result = find.executeQuery();
		while(result.next()) {
			if(result.getString("username").equals(username))
				return true;
		}
		return false;
	}
	
	public void ShowUserPage(ResultSet resultSet) throws Exception {
		Show show = new Show();
		switch (resultSet.getString("type")) {
		case "admin" :
			show.showShowUsersPage();
			System.out.println("AdminPage");
			break;
		case "customs officer" :
			show.showCustomsOfficerPage();
			break;
		case "ministry representative":
			show.showIssuingPermitPage();
			break;
		case "law maker":
			show.showLawsPage();
			break;
		default:
			break;
		}
	}
	
	public void registerUser(String username, String password) throws Exception {
		if(checkUser(username)) {
			System.out.println("");
			Popupp p = new Popupp("Registered Failed!", "This username already exists!");
			p.start(new Stage());
		}
		else {
			addUser(username, password);
			System.out.println("Registered!");
			Popupp p = new Popupp("Registered Successfully!", "Please wait for admin confirmation!");
			p.start(new Stage());
		}
	}
	
	public void loginUser(String username, String password) throws Exception {
		Connection con = getConnection();
		PreparedStatement find = con.prepareStatement("SELECT * FROM users WHERE username='"
				+ username + "' AND password='" + password + "'");
		ResultSet resultSet = find.executeQuery();
		System.out.println("findUser Complete!");
		while(resultSet.next()) {
			if (resultSet.getString("type").equals("none")) {	//	type=none -> failed
				Popupp p = new Popupp("Login Failed!", "You have to wait for confirmation!");
				p.start(new Stage());
				return;
			}
			else {	//	found -> show user page
				ShowUserPage(resultSet);
				return;
			}
		}
		Popupp p = new Popupp("Login Failed!", "Username or Password is incorrect!");
		p.start(new Stage());
	}
	
	public void  addUser(String username, String password) throws Exception{
		Connection con = getConnection();
		PreparedStatement add = con.prepareStatement("INSERT INTO users (username, password, type) VALUES ('" + username + "', '" + password + "', 'none')");
		add.executeUpdate();
		System.out.println("User Insert Completed!");
	}
	
	public void createTable() {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS users(id int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
					+ " username varchar(255) NOT NULL, password varchar(255) NOT NULL, type varchar(255) NOT NULL, ministry varchar(255))");
			create.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		finally {System.out.println("Create users Completed!");};

		}
}
