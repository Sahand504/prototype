package declaration;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ConfirmDeclarationController {
	@FXML
	private TextField mechantIDTF;
	@FXML
	private TextField declarationIDTF;
	@FXML
	private Button checkAndConfirmBtn;
	
	@FXML
	void checkAndConfrimBtnClicked() throws Exception {
//		DeclarationsManagement declarationsManagement = new DeclarationsManagement();
//		declarationsManagement.checkAndConfirm(getMerchantID(), getDeclarationID());
	}
	
	public int getMerchantID() {return Integer.parseInt(this.mechantIDTF.getText());}
	public int getDeclarationID() {return Integer.parseInt(this.declarationIDTF.getText());}
}
