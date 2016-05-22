package application;

import java.io.IOException;
import java.util.ArrayList;

import admin.ShowUsersController;
import admin.UserEditController;
import dbManagement.UsersManagement;
import declaration.CreateDeclarationController;
import goods.GoodsController;
import javaClasses.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import loginRegister.LoginRegisterController;

public class Show {
	
	private ObservableList<User> userData = FXCollections.observableArrayList();
	public ObservableList<User> getUserData() {return this.userData;}
	
	private Stage primaryStage = new Stage();
	
	public void setPrimaryStage(Stage stage) {this.primaryStage = stage;}

	public void addUsersToTable() throws Exception {
		UsersManagement usersManagement = new UsersManagement();
		ArrayList<User> users= usersManagement.getUsers();
		for(User u : users) {
			userData.add(new User(u.getUsername(), u.getPassword(), u.getType()));
		}
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

	public void showGoodsPage(int id) throws IOException {
		this.primaryStage.setTitle("LoginRegisterPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("../goods/GoodsPage.fxml"));
		Pane goodsPage = (Pane) loader.load();
		Scene scene = new Scene(goodsPage);
		this.primaryStage.setScene(scene);
		
		GoodsController goodsController = loader.<GoodsController>getController();
		goodsController.setdeclarationID(id);
		
		this.primaryStage.showAndWait();
	}
}
