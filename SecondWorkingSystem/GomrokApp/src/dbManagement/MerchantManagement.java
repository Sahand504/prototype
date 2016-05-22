package dbManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MerchantManagement {
	public void addMerchant(String id, String firstName, String lastName) throws Exception {
		Connection con = getConnection();
		PreparedStatement post = con.prepareStatement("INSERT INTO merchants (id, firstName, lastName) VALUES ('" + id + "', '" + firstName + "', '" + lastName + "')");
		post.executeUpdate();
		System.out.println("Merchant Insert Completed!");
	}
	
	public boolean checkMerchant(String id) throws Exception {
		Connection con = getConnection();
		PreparedStatement find = con.prepareStatement("SELECT * FROM merchants WHERE id='" + id + "'");
		ResultSet result = find.executeQuery();
		while(result.next()) {
			if(result.getString("id").equals(id))
				return true;
		}
		return false;
	}
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/gomrok";
			String username = "root";
			String password = "1234";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, username, password);
			System.out.println("Connected!");
			return conn;
		} catch(Exception e) {System.out.println(e);}
		System.out.println("Connection Error!");
		return null;
	}
}
