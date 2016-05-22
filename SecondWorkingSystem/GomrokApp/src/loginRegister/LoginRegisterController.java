
package loginRegister;

import dbManagement.UsersManagement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginRegisterController {
	@FXML
	private TextField usernameTF1;
	@FXML
	private TextField passwordTF1;
	@FXML
	private TextField usernameTF2;
	@FXML
	private TextField passwordTF2;
	@FXML
	private Button loginBtn;
	@FXML
	private Button registerBtn;

	private boolean isClicked = false;
	@SuppressWarnings("unused")
	private Stage primaryStage = new Stage();
	
	@FXML
	private void loginButtonClicked() throws Exception {		
		UsersManagement usersManagement = new UsersManagement();
		usersManagement.loginUser(getLoginUsername(), getLoginPassword());
		this.setClicked(true);
	}
	@FXML
	private void registerButtonClicked() throws Exception {
		UsersManagement usersManagement = new UsersManagement();
		usersManagement.registerUser(getRegisterUsername(), getRegisterPassword());
	}

	public void setStage(Stage stage) {this.primaryStage = stage;}
	public void setClicked(boolean isClicked) {this.isClicked = isClicked;}
		
	public boolean isClicked() {return isClicked;}
	public String getLoginUsername() {return this.usernameTF1.getText();}
	public String getLoginPassword() {return this.passwordTF1.getText();}
	
	public String getRegisterUsername() {return this.usernameTF2.getText();}
	public String getRegisterPassword() {return this.passwordTF2.getText();}
}
