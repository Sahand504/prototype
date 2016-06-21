package admin;

import java.io.IOException;
import java.util.ArrayList;

import application.Show;
import dbManagement.UsersManagement;
import javaClasses.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ShowUsersController {
	
	private ObservableList<User> userData = FXCollections.observableArrayList();
	
	@FXML
	private Pane pane;
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> usernameCol;
	@FXML
	private TableColumn<User, String> passwordCol;
	@FXML
	private TableColumn<User, String> typeCol;
	@FXML
	private TableColumn<User, String> ministryCol;
	@FXML
	private Button editBtn;
	@FXML
	private Button deleteBtn;
	@FXML
	private Button DeclarationBtn;
	
	@FXML
	private void initialize() {
		System.out.println("Show Users Page!");
		usernameCol.setCellValueFactory(cellData -> cellData.getValue().getUsernameStringProperty());
		passwordCol.setCellValueFactory(cellData -> cellData.getValue().getPasswordStringProperty());
		typeCol.setCellValueFactory(cellData -> cellData.getValue().getTypeStringProperty());
		ministryCol.setCellValueFactory(cellData -> cellData.getValue().getMinistryStringProperty());
	}
	
	@FXML
	public void DeclarationBtnClicked() throws Exception {
		System.out.println("Show Declarations Page!");
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.close();
		
		Show show = new Show();
		show.showShowDeclarationPage(true);
	}
	
	@FXML
	public void editBtnClicked() throws IOException {
		System.out.println("Edit Button Clicked!");
		User selectedUser = userTable.getSelectionModel().getSelectedItem();
		if (selectedUser != null) {
			//	close this page 
			Stage stage = (Stage)pane.getScene().getWindow();
			stage.close();
			
			Show show = new Show();
			show.showUserEditPage(selectedUser);
		}
		else {
			System.out.println("No user Selected!");
		}
	}

	@FXML
	public void deleteBtnClicked() throws Exception {
		System.out.println("Delete Button Clicked!");
		int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
		User selectedUser = userTable.getItems().remove(selectedIndex);
		
		UsersManagement usersManagement = new UsersManagement();
		usersManagement.deleteUser(selectedUser);
	}
	
	public void refreshTable() throws Exception {
		userData.clear();
		UsersManagement usersManagement = new UsersManagement();
		ArrayList<User> users= usersManagement.getUsers();
		for(User u : users) {
			userData.add(new User(u.getUsername(), u.getPassword(), u.getType(), u.getMinistry()));
		}
	}
	
	public void setShow(Show show) {
		userTable.setItems(show.getUserData());
	}
}
