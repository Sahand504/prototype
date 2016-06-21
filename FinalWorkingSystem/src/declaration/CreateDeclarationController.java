package declaration;

import java.time.LocalDate;

import application.Show;
import dbManagement.DeclarationsManagement;
import dbManagement.MerchantManagement;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	@FXML
	private void initialize() {
		wayCB.getSelectionModel().selectFirst();
		dateStartedDP.setValue(LocalDate.now());
		dateStartedDP.setStyle("-fx-font-size: 13;");
		
		/*	disable button until filling text-fields	*/
		disableNextBtn();
		
		/*	productNum could only be numeric	*/
		numericTextField(productNumTF);
	}
	
	@FXML
	public void nextButtonClicked() throws Exception {
		
		System.out.println(getDatePicker());
		
		MerchantManagement merchantManagement = new MerchantManagement();
		if(!merchantManagement.checkMerchant(getMerchantID()))
			merchantManagement.addMerchant(getMerchantID(), getMerchantFirst(), getMerchantLast());
		DeclarationsManagement declarationsManagement = new DeclarationsManagement();
		declarationsManagement.addDeclaration(getMerchantID(), getway(), getOriginCountry(), getProductNum(), getDatePicker());
		
		int productNum = Integer.parseInt(getProductNum());
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.close();
		
		Show show = new Show();
		show.showGoodsPage(declarationsManagement.getDeclarationID(), productNum,
				wayCB.getValue(), originCountryTF.getText(), dateStartedDP.getValue().toString());
	}
	
	public void disableNextBtn() {
		nextBtn.disableProperty().bind(
			    Bindings.isEmpty(merchantFirstTF.textProperty())
			    .or(Bindings.isEmpty(merchantLastTF.textProperty()) )
			    .or(Bindings.isEmpty(merchantIDTF.textProperty()) )
			    .or(Bindings.isEmpty(originCountryTF.textProperty()) )
			    .or(Bindings.isEmpty(productNumTF.textProperty()) )
			);
	}
	
	public void numericTextField(TextField textField) {
		textField.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) {
	            	textField.setText(newValue.replaceAll("[^\\d]", ""));
	            }
	        }
	    });
	}

	private Stage primaryStage;
	
	public void setStage(Stage stage) {this.primaryStage = stage;}
	public Stage getStage() {return this.primaryStage;}
	public String getMerchantFirst() {return merchantFirstTF.getText();}
	public String getMerchantLast() {return merchantLastTF.getText();}
	public String getMerchantID() {return merchantIDTF.getText();}
	public String getway() {return wayCB.getValue();}
	public String getOriginCountry() {return originCountryTF.getText();}
	public String getProductNum() {return productNumTF.getText();}
	public String getDatePicker() {return dateStartedDP.getValue().toString();}
}
