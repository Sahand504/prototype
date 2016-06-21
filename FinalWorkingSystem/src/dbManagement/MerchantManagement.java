package dbManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MerchantManagement extends DBManagement{
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
}
