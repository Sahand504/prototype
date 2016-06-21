package dbManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PermitManagement extends DBManagement{
	
	public void add(String productType, String productName, String manufacturer, String producingCountry,
			long maxPrice, int maxNumber, int maxWeight, String dateTill, int merchantID) throws Exception {
		Connection con = getConnection();
		PreparedStatement insert = con.prepareStatement("INSERT INTO permits "
				+ "(productType, productName, manufacturer, producingCountry, "
				+ "maxPrice, maxNumber, maxWeight, dateTill, merchantID) VALUES "
				+ "('" + productType + "', '" + productName + "', '" + manufacturer + "', '" + producingCountry + "', " +
				+ maxPrice + ", " + maxNumber + ", " + maxWeight + ", '" + dateTill + "', " + merchantID + ")");
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
					+ "maxPrice long, "
					+ "maxNumber int, "
					+ "maxWeight int, "
					+ "dateTill varchar(255), "
					+ "merchantID int NOT NULL"
					+ ")");
			create.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		finally {System.out.println("Create permits Completed!");};

	}
}
