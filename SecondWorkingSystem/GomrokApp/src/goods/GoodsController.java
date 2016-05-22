package goods;

import dbManagement.DeclarationsManagement;
import dbManagement.GoodsManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import popupControllers.Popupp;

public class GoodsController {
	@FXML
	private TextField ProductTypeTF;
	@FXML
	private TextField productNameTF;
	@FXML
	private TextField ManufacturerTF;
	@FXML
	private TextField weightTF;
	@FXML
	private TextField numberTF;
	@FXML
	private TextField priceTF;
	@FXML
	private TextField productCountryTF;
	@FXML
	private Button addBtn;
	
	@FXML
	void addButtonClicked() throws Exception {
		GoodsManagement goodsManagement = new GoodsManagement();
		goodsManagement.createTable();
//		if(!goodsManagement.checkGoods(getName()))
//			goodsManagement.addGoods(getType(), getName(), getManufacturer(), getWight(), 
//					getPrice(), getProducingCountry());
		
		goodsManagement.addGoods(getType(), getName(), getManufacturer(), getWight(), getNumber(), 
				getPrice(), getProducingCountry(), getdeclarationID());
		
		DeclarationsManagement declarationsManagement = new DeclarationsManagement();
		declarationsManagement.updateTotalPrice(getdeclarationID(), getWight(), getNumber(), getPrice());
		Popupp p = new Popupp("Goods are Added!", "Your declaration ID is: " + getdeclarationID());
		p.start(new Stage());
	}
	
	@SuppressWarnings("unused")
	private Stage primaryStage = new Stage();
	private int declarationID;
	
	public int getdeclarationID() {return declarationID;}

	public void setStage(Stage stage) {primaryStage = stage;}
	public void setdeclarationID(int declarationID) {this.declarationID = declarationID;}
	
	public String getType() {return this.ProductTypeTF.getText();}
	public String getName() {return this.productNameTF.getText();}
	public String getManufacturer() {return this.ManufacturerTF.getText();}
	public int getWight() {return weightTF.getText().isEmpty()? -1 : Integer.parseInt(this.weightTF.getText());}
	public int getNumber() {return numberTF.getText().isEmpty()? -1 : Integer.parseInt(this.numberTF.getText());}
	public int getPrice()  {return Integer.parseInt(this.priceTF.getText());}
	public String getProducingCountry() {return productCountryTF.getText();}
}
