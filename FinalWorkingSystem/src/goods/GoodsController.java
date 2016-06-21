package goods;

import java.util.ArrayList;

import application.Show;
import dbManagement.DeclarationsManagement;
import dbManagement.GoodsManagement;
import dbManagement.LawsManagement;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import popupControllers.Popupp;

public class GoodsController {
	
	private ArrayList<String> permitsNeeded = new ArrayList<>();
	
	@FXML
	private Pane pane;
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
	private void initialize() {
		disableAddBtn();
		
		/*	weightTF or numberTF must be empty	*/
		numberTF.disableProperty().bind(Bindings.isNotEmpty(weightTF.textProperty()));
		weightTF.disableProperty().bind(Bindings.isNotEmpty(numberTF.textProperty()));
		
		numericTextField(numberTF);
		numericTextField(weightTF);
		numericTextField(priceTF);
	}
	
	@FXML
	void addButtonClicked() throws Exception {
		GoodsManagement goodsManagement = new GoodsManagement();
		goodsManagement.createTable();
		goodsManagement.addGoods(getType(), getName(), getManufacturer(), getWight(), getNumber(), 
				getPrice(), getProducingCountry(), getdeclarationID());
		
		DeclarationsManagement declarationsManagement = new DeclarationsManagement();
		declarationsManagement.updateTotalPrice(getdeclarationID(), getWight(), getNumber(), getPrice());
		productNum -= 1;
		addPermitsNeeded();
		
		/*	Goods finished	*/
		if(productNum <= 0) {
			Stage stage = (Stage)pane.getScene().getWindow();
			stage.close();
			
			/*  show permits needed here  
			System.out.println("Permit(s) needed: ");
			for (int i = 0; i < permitsNeeded.size(); i++) {
				System.out.println("permit" + i + " : " + permitsNeeded.get(i));
			}	*/
			
			Show show = new Show();
			show.showPermitsNeeded(permitsNeeded);
			
			Popupp p = new Popupp("Goods are Added!", "Your declaration ID is: " + getdeclarationID());
			p.start(new Stage());
		}
	}
	
	void addPermitsNeeded() throws Exception {
		int weight = -1, number = -1;
		String productType = ProductTypeTF.getText();
		String productName = productNameTF.getText();
		String manufacturer = ManufacturerTF.getText();
		if(numberTF.isDisabled())
			weight = Integer.parseInt(weightTF.getText());
		if(weightTF.isDisabled())
			number = Integer.parseInt(numberTF.getText());
		long price = Long.parseLong(priceTF.getText());
		String producingCountry = productCountryTF.getText();
		
		LawsManagement lawsManagement = new LawsManagement();
		ArrayList<String> result = lawsManagement.findPermitsNeeded(this.way, this.originCountry, this.declarationDate,
				productType, productName, manufacturer, weight, number, price, producingCountry);
		
		for (int i = 0; i < result.size(); i++) {
			permitsNeeded.add(result.get(i));
		}
	}
	
	public void disableAddBtn() {
		addBtn.disableProperty().bind(
			    Bindings.isEmpty(ProductTypeTF.textProperty())
			    .or(Bindings.isEmpty(productNameTF.textProperty()) )
			    .or(Bindings.isEmpty(ManufacturerTF.textProperty()) )
			    .or(Bindings.isEmpty(weightTF.textProperty()).and(Bindings.isEmpty(numberTF.textProperty()) ) )
			    .or(Bindings.isEmpty(priceTF.textProperty()) )
			    
			    .or(Bindings.isEmpty(productCountryTF.textProperty()) )
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
	
	@SuppressWarnings("unused")
	private Stage primaryStage = new Stage();
	private int declarationID;
	private String way;
	private String originCountry;
	private String declarationDate;
	private int productNum;
	
	public int getdeclarationID() {return declarationID;}
	public int getProductNum() {return productNum;}
	
	public void setStage(Stage stage) {primaryStage = stage;}
	public void setdeclarationID(int declarationID) {this.declarationID = declarationID;}
	public void setProctNum(int productNum) {this.productNum = productNum;}
	public void setWay(String way) {this.way = way;}
	public void setOriginCountry(String originCountry) {this.originCountry = originCountry;}
	public void setDeclarationDate(String declarationDate) {this.declarationDate = declarationDate;}
	
	public String getType() {return this.ProductTypeTF.getText();}
	public String getName() {return this.productNameTF.getText();}
	public String getManufacturer() {return this.ManufacturerTF.getText();}
	public int getWight() {return weightTF.getText().isEmpty()? -1 : Integer.parseInt(this.weightTF.getText());}
	public int getNumber() {return numberTF.getText().isEmpty()? -1 : Integer.parseInt(this.numberTF.getText());}
	public int getPrice()  {return Integer.parseInt(this.priceTF.getText());}
	public String getProducingCountry() {return productCountryTF.getText();}
}
