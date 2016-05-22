package permit;

import dbManagement.PermitManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import popupControllers.Popupp;

public class PermitController {
	@FXML
	private TextField merchantIDTF;
	@FXML
	private TextField productTypeTF;
	@FXML
	private TextField productNameTF;
	@FXML
	private TextField manufacturerTF;
	@FXML
	private TextField producingCountryTF;
	@FXML
	private TextField minPriceTF;
	@FXML
	private TextField maxPriceTF;
	@FXML
	private TextField minNumberTF;
	@FXML
	private TextField maxNumberTF;
	@FXML
	private TextField minWeightTF;
	@FXML
	private TextField maxWeightTF;
	@FXML
	private DatePicker dateFromDP;
	@FXML
	private DatePicker dateTillDP;
	@FXML
	private Button givingPermitBtn;
	
	@FXML
	public void givingPermitBtnClicked() throws Exception {
		PermitManagement permitManagement = new PermitManagement();
		permitManagement.createTable();
		permitManagement.add(getProductType(), getProductName(), getManufacturer(), getproducingCountry(), getMinPrice(), 
				getMaxPrice(), getMinNumber(), getMaxNumber(), getMinWeight(), getMaxWeight(), getDateFrom(), getDateTill(), getMerchantID());
//		Merchant_permitsManagement merchant_permitsManagement = new Merchant_permitsManagement();
//		merchant_permitsManagement.createTable();
//		merchant_permitsManagement.add(getMerchantID(), permitManagement.getPermitID());
		Popupp p = new Popupp("Permit is Added!", "Your Permit ID is: " + permitManagement.getPermitID());
		p.start(new Stage());
	}
	
	public int getMerchantID() {return Integer.parseInt(merchantIDTF.getText());}
	public String getProductType() {return productTypeTF.getText();}
	public String getProductName() {return productNameTF.getText();}
	public String getManufacturer() {return manufacturerTF.getText();}
	public String getproducingCountry() {return producingCountryTF.getText();}
	public String getDateFrom() {return (dateFromDP.getValue() == null)? "0000-01-01" : dateFromDP.getValue().toString();}
	public String getDateTill() {return (dateTillDP.getValue() == null)? "9999-01-01" : dateTillDP.getValue().toString();}
	
	public long getMinPrice() {return (minPriceTF.getText().isEmpty())? 0 : Long.parseLong(minPriceTF.getText());}
	public long getMaxPrice() {return (maxPriceTF.getText().isEmpty())? Long.MAX_VALUE : Long.parseLong(maxPriceTF.getText());}
	public int getMinNumber() {return (minNumberTF.getText().isEmpty())? 0 : Integer.parseInt(minNumberTF.getText());}
	public int getMaxNumber() {return (maxNumberTF.getText().isEmpty())? Integer.MAX_VALUE : Integer.parseInt(maxNumberTF.getText());}
	public int getMinWeight() {return (minWeightTF.getText().isEmpty())? 0 : Integer.parseInt(minWeightTF.getText());}
	public int getMaxWeight() {return (maxWeightTF.getText().isEmpty())? Integer.MAX_VALUE : Integer.parseInt(maxWeightTF.getText());}
	
}
