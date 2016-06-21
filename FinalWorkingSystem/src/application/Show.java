package application;

import java.io.IOException;
import java.util.ArrayList;

import admin.ShowDeclarationGoodsController;
import admin.ShowDeclarationsController;
import admin.ShowUsersController;
import admin.UserEditController;
import dbManagement.DeclarationsManagement;
import dbManagement.GoodsManagement;
import dbManagement.UsersManagement;
import declaration.CreateDeclarationController;
import goods.GoodsController;
import javaClasses.Declaration;
import javaClasses.Goods;
import javaClasses.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginRegister.LoginRegisterController;
import permitsNeeded.PermitsNeededController;

public class Show {
	
	private ObservableList<Goods> goodsData = FXCollections.observableArrayList();
	public ObservableList<Goods> getGoodsData() {return this.goodsData;}
	
	private ObservableList<Declaration> declarationData = FXCollections.observableArrayList();
	private ObservableList<User> userData = FXCollections.observableArrayList();
	public ObservableList<User> getUserData() {return this.userData;}
	public ObservableList<Declaration> getDeclarationData() {return this.declarationData;}
	
	private Stage primaryStage = new Stage();
	public void setPrimaryStage(Stage stage) {this.primaryStage = stage;}

	public void addUsersToTable() throws Exception {
		UsersManagement usersManagement = new UsersManagement();
		ArrayList<User> users = usersManagement.getUsers();
		for(User u : users) {
			userData.add(new User(u.getUsername(), u.getPassword(), u.getType(), u.getMinistry()));
		}
	}
	
	public void addDeclarationsToTable() throws Exception {
		DeclarationsManagement declarationsManagement = new DeclarationsManagement();
		ArrayList<Declaration> declarations = declarationsManagement.getDeclarations();
		for(Declaration d : declarations) {
			declarationData.add(new Declaration(d.getid(), d.getMerchantID(),
					d.getMerchantFirst(), d.getMerchantLast(), d.getStartedDate(), d.getConf()));
		}
	}
	
	public void addGoodsToTable(String declarationID) throws Exception {
		System.out.println("declarationID in show2 = " +  declarationID);
		GoodsManagement goodsManagement = new GoodsManagement();
		ArrayList<Goods> goods = goodsManagement.getGoods(declarationID);
		System.out.println("number of goods = " +  goods.size());
		for(Goods g : goods) {
			goodsData.add(new Goods(g.getType(), g.getName(),
					g.getManufacturer(), g.getWeight(), g.getNumber(), g.getPrice(), g.getProducingCountry()));
		}
	}
	
	public void showPermitsNeeded(ArrayList<String> permitsNeeded) throws IOException {
		this.primaryStage.setTitle("ShowPermitsNeeded");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../permitsNeeded/PermitsNeededPage.fxml"));
		Pane pane = (Pane) loader.load();
		
		PermitsNeededController controller = loader.getController();
		controller.setList(permitsNeeded);
		
		Scene scene = new Scene(pane);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public void showShowDeclarationGoodsPage(String declarationID, boolean isAdmin) throws Exception {
		this.primaryStage.setTitle("ShowGoodsPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../admin/ShowDeclarationGoodsPage.fxml"));
		Pane showShowGoodsPage = (Pane) loader.load();
		
		System.out.println("declarationID in show1 = " +  declarationID);
		addGoodsToTable(declarationID);
		
		ShowDeclarationGoodsController controller = loader.getController();
		controller.setShow(this, isAdmin);
		
		Scene scene = new Scene(showShowGoodsPage);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public void showShowDeclarationPage(boolean isAdmin) throws Exception {
		this.primaryStage.setTitle("ShowDeclarationPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../admin/ShowDeclarationsPage.fxml"));
		Pane showShowDeclarationPage = (Pane) loader.load();
		
		addDeclarationsToTable();
		ShowDeclarationsController controller = loader.getController();
		controller.setShow(this, isAdmin);
		
		Scene scene = new Scene(showShowDeclarationPage);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public void showUserEditPage(User curUser) throws IOException {
		this.primaryStage.setTitle("UserEditPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../admin/UserEditPage.fxml"));
		Pane UserEditPage = (Pane) loader.load();
		
		UserEditController controller = loader.getController();
		controller.setCurUser(curUser);
		controller.fillFields();
		
		Scene scene = new Scene(UserEditPage);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public void showShowUsersPage() throws Exception {
		this.primaryStage.setTitle("ShowUsersPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../admin/ShowUsersPage.fxml"));
		Pane showUsersPage = (Pane) loader.load();
		
		addUsersToTable();
		ShowUsersController controller = loader.getController();
		controller.setShow(this);
		
		Scene scene = new Scene(showUsersPage);
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
	}
	
	public void showLawsPage() throws IOException {
		this.primaryStage.setTitle("CreateLawsPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../laws/LawsPage.fxml"));
		Pane lawsPage = (Pane) loader.load();
		Scene scene = new Scene(lawsPage);
		this.primaryStage.setScene(scene);
		this.primaryStage.showAndWait();
	}
	
	public void showConfirmDeclarationPage() throws IOException {
		this.primaryStage.setTitle("ConfirmDeclarationPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../declaration/ConfirmDeclarationPage.fxml"));
		Pane confirmDeclarationPage = (Pane) loader.load();
		Scene scene = new Scene(confirmDeclarationPage);
		this.primaryStage.setScene(scene);
		this.primaryStage.showAndWait();
	}
	
	public void showIssuingPermitPage() throws IOException {
		this.primaryStage.setTitle("IssuingPermitPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../permit/PermitPage.fxml"));
		Pane issuingPermitPage = (Pane) loader.load();
		Scene scene = new Scene(issuingPermitPage);
		this.primaryStage.setScene(scene);
		this.primaryStage.showAndWait();
	}
	
	public void showCustomsOfficerPage() throws IOException {
		this.primaryStage.setTitle("CustomsOfficerPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../customsOfficer/customsOfficerPage.fxml"));
		Pane customsOfficerPage = (Pane) loader.load();
		Scene scene = new Scene(customsOfficerPage);
		this.primaryStage.setScene(scene);
		this.primaryStage.showAndWait();
	}
	
	public void showLoginRegisterPage() throws IOException {
		this.primaryStage.setTitle("LoginRegisterPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../loginRegister/LoginRegisterPage.fxml"));
		Pane loginRegisterPage = (Pane) loader.load();
		Scene scene = new Scene(loginRegisterPage);
		this.primaryStage.setScene(scene);
		
		LoginRegisterController loginRegisterController = loader.getController();
		loginRegisterController.setStage(new Stage());
		
		this.primaryStage.showAndWait();
	}
	
	public void showDelcarationPage() throws IOException {
		this.primaryStage.setTitle("DeclarationPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../declaration/CreateDeclarationPage.fxml"));
		Pane declarationPage = (Pane) loader.load();
		System.out.println("showDeclarationPage!");
		Scene scene = new Scene(declarationPage);
		this.primaryStage.setScene(scene);
		
		CreateDeclarationController declarationController = loader.getController();
		declarationController.setStage(new Stage());
		
		this.primaryStage.show();
	}

	public void showGoodsPage(int id, int productNum, String way, String originCountry, String declarationDate) throws IOException {
		this.primaryStage.setTitle("LoginRegisterPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../goods/GoodsPage.fxml"));
		Pane goodsPage = (Pane) loader.load();
		Scene scene = new Scene(goodsPage);
		this.primaryStage.setScene(scene);
		
		GoodsController goodsController = loader.<GoodsController>getController();
		goodsController.setdeclarationID(id);
		goodsController.setProctNum(productNum);
		goodsController.setWay(way);
		goodsController.setOriginCountry(originCountry);
		goodsController.setDeclarationDate(declarationDate);
		
		this.primaryStage.show();
	}
}
