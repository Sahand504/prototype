package admin;


import application.Show;
import dbManagement.DeclarationsManagement;
import dbManagement.GoodsManagement;
import javaClasses.Declaration;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowDeclarationsController {
	
	private boolean isAdmin;
	
	@FXML
	private Pane pane;
	@FXML
	private TableView<Declaration> declarationTable;
	@FXML
	private TableColumn<Declaration, String> declarationIDCol;
	@FXML
	private TableColumn<Declaration, String> merchantIDCol;
	@FXML
	private TableColumn<Declaration, String> merchantFirstNameCol;
	@FXML
	private TableColumn<Declaration, String> merchantLastNameCol;
	@FXML
	private TableColumn<Declaration, String> dateCol;
	@FXML
	private TableColumn<Declaration, String> ConfCol;
	@FXML
	private Button editBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button usersBtn;
	@FXML
	private Button permitsBtn;
	@FXML
	private Button lawsBtn;
	
	@FXML
	private void initialize() {
		System.out.println("Show Declarations Page!");
		declarationIDCol.setCellValueFactory(cellData -> cellData.getValue().getIDSP());
		merchantIDCol.setCellValueFactory(cellData -> cellData.getValue().getMerchantIDSP());
		merchantFirstNameCol.setCellValueFactory(cellData -> cellData.getValue().getMerchantFirstSP());
		merchantLastNameCol.setCellValueFactory(cellData -> cellData.getValue().getMerchantLastSP());
		dateCol.setCellValueFactory(cellData -> cellData.getValue().getStartedDateSP());
		ConfCol.setCellValueFactory(cellData -> cellData.getValue().getConfSP());
	}
	
	@FXML
	public void showGoodsBtn() throws Exception {
		System.out.println("showGoodsBtn clicked!");
		Declaration selectedDeclaration = declarationTable.getSelectionModel().getSelectedItem();
		if (selectedDeclaration != null) {
			Show show = new Show();
			show.showShowDeclarationGoodsPage(String.valueOf(selectedDeclaration.getid()), this.isAdmin);
			
			//	close this page
			Stage stage = (Stage)pane.getScene().getWindow();
			stage.close();
		}
	}
	
	@FXML
	public void deleteBtnClicked() throws Exception {
		System.out.println("Delete Button Clicked!");
		int selectedIndex = declarationTable.getSelectionModel().getSelectedIndex();
		Declaration selectedDeclaration = declarationTable.getItems().remove(selectedIndex);
		
		DeclarationsManagement declarationsManagement = new DeclarationsManagement();
		declarationsManagement.deleteDeclaration(selectedDeclaration);
		
		GoodsManagement goodsManagement = new GoodsManagement();
		goodsManagement.deleteDeclarationGoods(selectedDeclaration);
	}
	
	@FXML
	public void usersBtnClicked() throws Exception {
		System.out.println("Show Users Page!");
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.close();
		
		Show show = new Show();
		show.showShowUsersPage();
	}

	public void setShow(Show show, boolean isAdmin) {
		this.isAdmin = isAdmin;
		declarationTable.setItems(show.getDeclarationData());
		if(!isAdmin) {
			usersBtn.setDisable(true);
			permitsBtn.setDisable(true);
			lawsBtn.setDisable(true);
		}
	}
}
