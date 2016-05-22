package declaration;

import application.Show;
import dbManagement.DeclarationsManagement;
import dbManagement.MerchantManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CreateDeclarationController {
	@FXML
	private Pane pane;
	@FXML
	private TextField merchantFirstTF;
	@FXML
	private TextField merchantLastTF;
	@FXML
	private TextField merchantIDTF;
	@FXML
	private ChoiceBox<String> wayCB;
	@FXML
	private TextField originCountryTF;
	@FXML
	private TextField productNumTF;
	@FXML
	private DatePicker dateStartedDP;
	@FXML
	private Button nextBtn;
	@SuppressWarnings("static-access")
	@FXML
	public void nextButtonClicked() throws Exception {
		MerchantManagement merchantManagement = new MerchantManagement();
		if(!merchantManagement.checkMerchant(getMerchantID()))
			merchantManagement.addMerchant(getMerchantID(), getMerchantFirst(), getMerchantLast());
		DeclarationsManagement declarationsManagement = new DeclarationsManagement();
		declarationsManagement.addDeclaration(getway(), getOriginCountry(), getProductNum(), getDatePicker());
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.close();
		
		Show show = new Show();
		show.showGoodsPage(declarationsManagement.getDeclarationID());
	}
	
	@SuppressWarnings("unused")
	private Stage primaryStage;
	
	public void setStage(Stage stage) {this.primaryStage = stage;}
	public String getMerchantFirst() {return merchantFirstTF.getText();}
	public String getMerchantLast() {return merchantLastTF.getText();}
	public String getMerchantID() {return merchantIDTF.getText();}
	public String getway() {return wayCB.getValue();}
	public String getOriginCountry() {return originCountryTF.getText();}
	public String getProductNum() {return productNumTF.getText();}
	public String getDatePicker() {return dateStartedDP.getValue().toString();}

	public void test() {
		System.out.println(merchantFirstTF.getText());
		System.out.println(merchantLastTF.getText());
		System.out.println(merchantIDTF.getText());
		System.out.println(wayCB.getValue());
		System.out.println(originCountryTF.getText());
		System.out.println(productNumTF.getText());
		System.out.println(dateStartedDP.getValue().toString());
	}
}
