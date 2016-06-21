package permitsNeeded;

import java.io.IOException;
import java.util.ArrayList;

import application.Show;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PermitsNeededController {
	
	@FXML
	private Pane pane;
	@FXML
	private ListView<String> listView = new ListView<>();
	@FXML
	private Button okBtn;
	
	@FXML
	private void okBtnClicked() throws IOException {
		Stage stage = (Stage)pane.getScene().getWindow();
		stage.close();
		
		Show show = new Show();
		show.showCustomsOfficerPage();
	}
	
	public void setList(ArrayList<String> list) {
		listView.setItems(FXCollections.observableList(list));
	}
}
