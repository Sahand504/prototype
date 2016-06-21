package admin;

import application.Show;
import javaClasses.Goods;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowDeclarationGoodsController {
	
	private boolean isAdmin;
	
	@FXML
	private Pane pane;
	@FXML
	private TableView<Goods> goodsTable;
	@FXML
	private TableColumn<Goods, String> typeCol;
	@FXML
	private TableColumn<Goods, String> nameCol;
	@FXML
	private TableColumn<Goods, String> manufacturerCol;
	@FXML
	private TableColumn<Goods, String> weightCol;
	@FXML
	private TableColumn<Goods, String> numberCol;
	@FXML
	private TableColumn<Goods, String> priceCol;
	@FXML
	private TableColumn<Goods, String> producingCountryCol;
	@FXML
	private TableColumn<Goods, String> declarationIDCol;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button backBtn;
	
	@FXML
	private void initialize() {
		System.out.println("Show Declarations Page!");
		
		typeCol.setCellValueFactory(cellData -> cellData.getValue().getTypeSP());
		nameCol.setCellValueFactory(cellData -> cellData.getValue().getNameSP());
		manufacturerCol.setCellValueFactory(cellData -> cellData.getValue().getManufacturerSP());
		weightCol.setCellValueFactory(cellData -> cellData.getValue().getWeightSP());
		numberCol.setCellValueFactory(cellData -> cellData.getValue().getNumberSP());
		priceCol.setCellValueFactory(cellData -> cellData.getValue().getPriceSP());
		producingCountryCol.setCellValueFactory(cellData -> cellData.getValue().getProducingCountrySP());
//		declarationIDCol.setCellValueFactory(cellData -> cellData.getValue().getIDSP());
	}
	
	@FXML
	public void backBtnClicked() throws Exception {
		//	close this page
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.close();
		
		Show show = new Show();
		show.showShowDeclarationPage(isAdmin);
	}

	public void setShow(Show show, boolean isAdmin) {
		this.isAdmin = isAdmin;
		goodsTable.setItems(show.getGoodsData());
	}
}
