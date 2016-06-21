package customsOfficer;

import java.io.IOException;

import application.Show;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class customsOfficerController {
	
	@FXML 
	private AnchorPane ap;
	@FXML
	Button createDeclarationBtn;
	@FXML
	Button confrimDeclarationBtn;
	@FXML
	Button showDeclarationsBtn;
	
	@FXML
	public void showDeclarationsBtnClicked() throws Exception {
		System.out.println("showDeclarationsBtn clicked!");
		Show show = new Show();
		show.showShowDeclarationPage(false);
	}
	
	@FXML
	public void createDeclarationBtnClicked() throws IOException {
		System.out.println("create!");
		Show show = new Show();
		show.showDelcarationPage();
		
		Stage stage = (Stage)ap.getScene().getWindow();
		stage.close();
	}
	@FXML
	public void confrimDeclarationBtn() {
		System.out.println("confirm!");
	}
}
