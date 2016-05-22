package customsOfficer;

import java.io.IOException;

import application.Show;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class customsOfficerController {
	@FXML
	Button createDeclarationBtn;
	@FXML
	Button confrimDeclarationBtn;
	@FXML private AnchorPane ap;
	
	@FXML
	public void createDeclarationBtnClicked() throws IOException {
		System.out.println("create!");
		Show show = new Show();
		show.showDelcarationPage();
	}
	@FXML
	public void confrimDeclarationBtn() {
		System.out.println("confirm!");
	}
}
