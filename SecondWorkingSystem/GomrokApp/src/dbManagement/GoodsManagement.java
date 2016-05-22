package dbManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class GoodsManagement {

//	public boolean checkGoods(String name) throws Exception {
//		Connection con = getConnection();
//		PreparedStatement find = con.prepareStatement("SELECT * FROM goods WHERE name='" + name + "'");
//		ResultSet result = find.executeQuery();
//		while(result.next()) {
//			if(result.getString("name").equals(name))
//				return true;
//		}
//		return false;
//	}
	
	public void addGoods(String type, String name, String manufacturer,
			int weight, int number, int price, String producingCountry, int declarationID) throws Exception {
		Connection con = getConnection();
		PreparedStatement add = con.prepareStatement("INSERT INTO goods "
				+ "(type, name, manufacturer, weight, number,  price, producingCountry, declarationID) VALUES "
				+ "('" + type + "', '" + name + "', '" + manufacturer + "', " + weight + ", " + number + ", "
				+ price + ", '" + producingCountry + "', " + declarationID + ")");
		add.executeUpdate();
		System.out.println("Insert Goods Completed");
	}
	
	public void createTable() {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS goods("
					+ "type varchar(255) NOT NULL, "
					+ "name varchar(255) NOT NULL, "
					+ "manufacturer varchar(255) NOT NULL, "
					+ "weight int NOT NULL DEFAULT -1, "
					+ "number int NOT NULL DEFAULT -1, "
					+ "price int NOT NULL, "
					+ "producingCountry varchar(255) NOT NULL, "
					+ "declarationID int NOT NULL"
					+ ")");
			create.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		finally {System.out.println("Create goods Completed!");};

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
