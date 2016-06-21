package admin;

import application.Show;
import dbManagement.UsersManagement;
import javaClasses.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
	private ChoiceBox<String> typeCB;
//	@FXML
//	private TextField ministryTF;
	@FXML
	private ChoiceBox<String> ministryCB;
	@FXML
	private Button okBtn;
	
	@FXML
	private void initialize() {
		ministryCB.disableProperty().bind(
				typeCB.valueProperty().isEqualTo("none").or(typeCB.valueProperty().isEqualTo("admin"))
				.or(typeCB.valueProperty().isEqualTo("customs officer").or(typeCB.valueProperty().isEqualTo("law maker"))));
	}
	
	@FXML
	public void okButtonClicked() throws Exception {
		System.out.println("Ok Button Clicked!");
		
		curUser.setUsername(this.usernameTF.getText());
		curUser.setPassword(this.passwordTF.getText());
		curUser.setType(this.typeCB.getValue());
		if(!ministryCB.isDisabled()) {
			System.out.println("choicebox is not disabled!");
			curUser.setMinistry(this.ministryCB.getValue());
			System.out.println("ministry = " + this.ministryCB.getValue());
		}
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
		typeCB.setValue(this.curUser.getType());
		System.out.println("ministry = " + this.curUser.getMinistry());
		ministryCB.setValue(this.curUser.getMinistry());
	}
}
