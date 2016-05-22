package laws;

import dbManagement.LawsManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import popupControllers.Popupp;

public class LawsController {
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
	private Button createLawBtn;
	
	@FXML
	public void createLawBtnClicked() throws Exception {
		LawsManagement lawsManagement = new LawsManagement();
		lawsManagement.add(getProductType(), getProductName(), getManufacturer(), getproducingCountry(), getMinPrice(), getMaxPrice(), getMinNumber(), getMaxNumber(), getMinWeight(), getMaxWeight(), getDateFrom(), getDateTill());
		Popupp p = new Popupp("Law is Added!", "Law is added\nLaw ID : " + lawsManagement.getLawID());
		p.start(new Stage());
	}
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
