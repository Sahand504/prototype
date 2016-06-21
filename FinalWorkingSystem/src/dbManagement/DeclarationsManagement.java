package dbManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javaClasses.Declaration;

public class DeclarationsManagement extends DBManagement {

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
					+ "merchantID varchar(255) NOT NULL, "
					+ "isConfirmed BOOLEAN DEFAULT 0 NOT NULL" 
					+ ")");
			create.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		finally {System.out.println("Create goods Completed!");};
	}
	
	public void addDeclaration(String merchantID, String way, String originCountry,
			String productNumber, String startedDate) throws Exception {
		
		Connection con = getConnection();
		PreparedStatement add = con.prepareStatement("INSERT INTO declarations"
				+ " (way, originCountry, productNum, startedDate, merchantID) VALUES "
				+ "('" + way + "', '" + originCountry + "', '" + productNumber 
				+ "', '" + startedDate +"', '" + merchantID + "')");
		add.executeUpdate();
		PreparedStatement getID = con.prepareStatement("SELECT max(id) FROM declarations");
		ResultSet resultSet =  getID.executeQuery();
		while(resultSet.next()) {
			String declarationID = resultSet.getString("max(id)");
			System.out.println("Your Declaration ID is: "+ declarationID);
		}
		System.out.println("Insert declaration Completed!");
	}
	
	public void deleteDeclaration(Declaration declaration) throws Exception {
		Connection con = getConnection();
		PreparedStatement delete = con.prepareStatement("DELETE FROM declarations WHERE id = " + declaration.getid());
		delete.executeUpdate();
	}
	
	public ArrayList<Declaration> getDeclarations() throws Exception {
		ArrayList<Declaration> declarations = new ArrayList<>();
		Connection con = getConnection();
//		PreparedStatement find = con.prepareStatement("SELECT * FROM declarations");
		PreparedStatement find = con.prepareStatement("SELECT * FROM declarations JOIN merchants ON declarations.merchantID=merchants.id");
		ResultSet resultSet = find.executeQuery();
		while(resultSet.next()) {
			declarations.add(new Declaration(resultSet.getLong("id"), resultSet.getString("merchantID"), resultSet.getString("firstName"), 
					resultSet.getString("lastName"), resultSet.getString("startedDate"), resultSet.getInt("isConfirmed")));
		}
		return declarations;
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
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
}
