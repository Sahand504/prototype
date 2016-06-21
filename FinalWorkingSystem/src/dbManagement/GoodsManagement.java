package dbManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javaClasses.Declaration;
import javaClasses.Goods;

public class GoodsManagement extends DBManagement {

	public ArrayList<Goods> getGoods(String declarationID) throws Exception {
		ArrayList<Goods> goods = new ArrayList<>();
		Connection con = getConnection();
//		PreparedStatement find = con.prepareStatement("SELECT * FROM declarations");
		PreparedStatement find = con.prepareStatement("SELECT * FROM goods WHERE declarationID = " + declarationID);
		ResultSet resultSet = find.executeQuery();
		while(resultSet.next()) {
			goods.add(new Goods(resultSet.getString("type"), resultSet.getString("name"), resultSet.getString("manufacturer"), 
					resultSet.getInt("weight"), resultSet.getInt("number"), resultSet.getLong("price"), resultSet.getString("producingCountry")));
		}
		return goods;
	} 
	
	public void deleteDeclarationGoods(Declaration declaration) throws Exception {
		Connection con = getConnection();
		PreparedStatement delete = con.prepareStatement("DELETE FROM goods WHERE declarationID = " + declaration.getid());
		delete.executeUpdate();
	}
	
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
}
