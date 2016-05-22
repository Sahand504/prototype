package dbManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PermitManagement {
	
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
	
	public void add(String productType, String productName, String manufacturer, String producingCountry, long minPrice, long maxPrice, 
			int minNumber, int maxNumber, int minWeight, int maxWeight, String dateFrom, String dateTill, int merchantID) throws Exception {
		Connection con = getConnection();
		PreparedStatement insert = con.prepareStatement("INSERT INTO permits (productType, productName, manufacturer, producingCountry, "
				+ "minPrice, maxPrice, minNumber, maxNumber, minWeight, maxWeight, dateFrom, dateTill, merchantID) VALUES "
				+ "('" + productType + "', '" + productName + "', '" + manufacturer + "', '" + producingCountry + "', " + minPrice + ", "
				 + maxPrice + ", " + minNumber + ", " + maxNumber + ", " + minWeight + ", " + maxWeight + ", '"
				 + dateFrom + "', '" + dateTill + "', " + merchantID + ")");
		insert.executeUpdate();
	}
	
	public int getPermitID() throws Exception {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement getLastID = con.prepareStatement("SELECT max(id) from permits");
		ResultSet resultSet = getLastID.executeQuery();
		while(resultSet.next()) {
			result = Integer.parseInt(resultSet.getString("max(id)"));
		}
		System.out.println("Last permit ID = "  + result);
		return result;
	}
	
	public void createTable() {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS permits("
					+ "id int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
					+ " productType varchar(255), "
					+ "productName varchar(255), "
					+ "manufacturer varchar(255), "
					+ "producingCountry varchar(255), "
					+ "minPrice long, maxPrice long, "
					+ "minNumber int, "
					+ "maxNumber int, "
					+ "minWeight int, "
					+ "maxWeight int, "
					+ "dateFrom varchar(255), "
					+ "dateTill varchar(255), "
					+ "merchantID int NOT NULL"
					+ ")");
			create.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		finally {System.out.println("Create permits Completed!");};

	}
}
