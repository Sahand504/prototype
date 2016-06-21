package dbManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LawsManagement extends DBManagement{
	
	public ArrayList<String> findPermitsNeeded(String way, String originCountry, String declarationDateStr,
			String productType, String productName, String manufacturer, int weight, int number, long price, String producingCountry) throws Exception {
		
		ArrayList<String> result = new ArrayList<>();
		
		Connection con = getConnection();
		PreparedStatement find = con.prepareStatement("SELECT * FROM laws");
		ResultSet resultSet = find.executeQuery();
		while(resultSet.next()) {
			
			String dateFromStr = resultSet.getString("dateFrom");
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy"); 
			Date dateFrom;
			dateFrom = df.parse(dateFromStr);
			
			String dateTillStr = resultSet.getString("dateTill");
			Date dateTill;
			dateTill = df.parse(dateTillStr);
			
			Date declarationDate;
			declarationDate = df.parse(declarationDateStr);
			
//			System.out.println("decDate = " + declarationDateStr);
//			System.out.println("dateFrom = " + dateFromStr);
//			System.out.println("dateTill = " + dateTillStr);
//			System.out.println(declarationDate.compareTo(dateFrom));
//			System.out.println(declarationDate.compareTo(dateTill));
//			System.out.println("--------------------------------------------------------------------------------");
			
			if(!resultSet.getString("productType").equals(productType) && !resultSet.getString("productType").isEmpty())
				continue;	/*  skip this law  */
			else if(!resultSet.getString("productName").equals(productName) && !resultSet.getString("productName").isEmpty())
				continue;	
			else if(!resultSet.getString("manufacturer").equals(manufacturer) && !resultSet.getString("manufacturer").isEmpty())
				continue;
			else if(!resultSet.getString("producingCountry").equals(producingCountry) && !resultSet.getString("producingCountry").isEmpty())
				continue;
			else if(!resultSet.getString("way").equals(way) && !resultSet.getString("way").equals("null"))
				continue;
			else if(!resultSet.getString("originCountry").equals(originCountry) && !resultSet.getString("originCountry").isEmpty())
				continue;
			
			else if((number != -1) && (number < resultSet.getInt("minNumber") || number > resultSet.getInt("maxNumber") ) )
				continue;
			else if((number == -1) && (resultSet.getInt("minNumber") != 0 || resultSet.getInt("maxNumber") != Integer.MAX_VALUE ) )
				continue;
			else if((weight != -1) && (weight < resultSet.getInt("minWeight") || weight > resultSet.getInt("maxWeight") ) )
				continue;
			else if((weight == -1) && (resultSet.getInt("minWeight") != 0 || resultSet.getInt("maxWeight") != Integer.MAX_VALUE ) )
				continue;
			else if((price < resultSet.getLong("minPrice") || price > resultSet.getLong("maxPrice") ) )
				continue;
			
			else if(declarationDate.compareTo(dateFrom) < 0 || declarationDate.compareTo(dateTill) > 0)
				continue;
				
			else
				result.add(resultSet.getString("permitName"));
		}
		
		return result;
	}
	
	public void add(String permitName, String ministry, String productType, String productName, String manufacturer, String producingCountry, String way, String originCountry, 
			long minPrice, long maxPrice, int minNumber, int maxNumber, int minWeight, int maxWeight, String dateFrom, String dateTill) throws Exception {
		Connection con = getConnection();
		PreparedStatement insert = con.prepareStatement("INSERT INTO laws (permitName, ministry, productType, productName, manufacturer, producingCountry, "
				+ "way, originCountry, minPrice, maxPrice, minNumber, maxNumber, minWeight, maxWeight, dateFrom, dateTill) VALUES "
				+ "('" + permitName + "', '" + ministry + "', '" + productType + "', '" + productName + "', '" + manufacturer + "', '" + producingCountry + "', '" 
				+ way + "', '" + originCountry + "', " + minPrice + ", " + maxPrice + ", " + minNumber + ", " + maxNumber + ", " + minWeight + ", " + maxWeight + ", '" 
				+ dateFrom + "', '" + dateTill + "')");
		insert.executeUpdate();
	}
	
	public int getLawID() throws Exception {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement getLastID = con.prepareStatement("SELECT max(id) from laws");
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
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS laws(id int NOT NULL PRIMARY KEY AUTO_INCREMENT,"
					+ " permitName varchar(255) NOT NULL, ministry varchar(255) NOT NULL,"
					+ " productType varchar(255), productName varchar(255), manufacturer varchar(255), producingCountry varchar(255),"
					+ " way varchar(255), originCountry varchar(255),"
					+ " minPrice long, maxPrice long, minNumber int, maxNumber int, minWeight int, maxWeight int,"
					+ " dateFrom varchar(255), dateTill varchar(255))");
			create.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		finally {System.out.println("Create laws Completed!");};

	}
}
