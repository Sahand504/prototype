package admin;

import application.Show;
import dbManagement.UsersManagement;
import javaClasses.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import popupControllers.Popupp;

public class UserEditController {
	
	private User curUser;
	
	public void setCurUser(User curUser) {this.curUser = curUser;}
	
	@FXML
	private Pane pane;
	@FXML
	private TextField usernameTF;
	@FXML
	private TextField passwordTF;
	@FXML
	private TextField typeTF;
	@FXML
	private TextField ministryTF;
	@FXML
	private Button okBtn;
	
	@FXML
	public void okButtonClicked() throws Exception {
		System.out.println("Ok Button Clicked!");
		
		curUser.setUsername(this.usernameTF.getText());
		curUser.setPassword(this.passwordTF.getText());
		curUser.setType(this.typeTF.getText());
		
		UsersManagement usersManagement = new UsersManagement();
		usersManagement.updateUser(curUser);
		
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.close();
		
		Show show = new Show();
		show.showShowUsersPage();
		
		Popupp p = new Popupp("ChangesMade", "Changes Made!");
		p.start(new Stage());
	}
	
	public void fillFields() {
		usernameTF.setText(this.curUser.getUsername());
		passwordTF.setText(this.curUser.getPassword());
		typeTF.setText(this.curUser.getType());
	}
}
