package dbManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeclarationsManagement {

	public void checkAndConfirm(int merchantID, int declarationID) throws Exception {
		
	}
	
	public void createTable() {
		try {
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS declarations("
					+ "id int NOT NULL PRIMARY KEY AUTO_INCREMENT, "
					+ "way varchar(255) NOT NULL, "
					+ "originCountry varchar(255) NOT NULL, "
					+ "startedDate varchar(255) NOT NULL, "
					+ "productNum int NOT NULL, "
					+ "totalPrice bigint DEFAULT 0, "
					+ "merchantID varchar(255)"
					+ ")");
			create.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		finally {System.out.println("Create goods Completed!");};

	}

	public void updateTotalPrice(int declarationID, int weight, int number, long price) throws Exception {
		long tmp = 0;
		Connection con = getConnection();
		PreparedStatement get = con.prepareStatement("SELECT * FROM declarations WHERE id = " + declarationID);
		ResultSet resultSet = get.executeQuery();
		while(resultSet.next()) {
			tmp = resultSet.getLong("totalPrice");
			System.out.println("tmp = " + tmp);
			if(weight == -1)
				tmp  += price*number;
			else if(number == -1)
				tmp += price*weight;
			System.out.println("new tmp = " + tmp);
		}
		System.out.println("UPDATE declarations SET totalPrice = " + tmp + " WHERE id = " + declarationID);
		PreparedStatement update = con.prepareStatement("UPDATE declarations SET totalPrice = " + tmp + " WHERE id = " + declarationID);
		update.executeUpdate();
	}
	
	public int getDeclarationID() throws Exception {
		int result = 0;
		Connection con = getConnection();
		PreparedStatement getLastID = con.prepareStatement("SELECT max(id) from declarations");
		ResultSet resultSet = getLastID.executeQuery();
		while(resultSet.next()) {
			result = Integer.parseInt(resultSet.getString("max(id)"));
		}
		System.out.println("Last declaration ID = "  + result);
		return result;
	}
	
	public static void addDeclaration(String way, String originCountry,
			String productNumber, String startedDate) throws Exception {
		Connection con = getConnection();
		PreparedStatement add = con.prepareStatement("INSERT INTO declarations"
				+ " (way, originCountry, productNum, startedDate) VALUES "
				+ "('" + way + "', '" + originCountry + "', '" + productNumber 
				+ "', '" + startedDate + "')");
		add.executeUpdate();
		PreparedStatement getID = con.prepareStatement("SELECT max(id) FROM declarations");
		ResultSet resultSet =  getID.executeQuery();
		while(resultSet.next()) {
			String declarationID = resultSet.getString("max(id)");
			System.out.println("Your Declaration ID is: "+ declarationID);
		}
		System.out.println("Insert declaration Completed!");
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
