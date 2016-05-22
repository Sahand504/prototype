package application;

import dbManagement.DeclarationsManagement;
import dbManagement.GoodsManagement;
import dbManagement.LawsManagement;
import dbManagement.PermitManagement;
import dbManagement.UsersManagement;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		UsersManagement usersManagement = new UsersManagement();
		usersManagement.createTable();
		
		DeclarationsManagement declarationsManagement = new DeclarationsManagement();
		declarationsManagement.createTable();
		
		GoodsManagement goodsManagement = new GoodsManagement();
		goodsManagement.createTable();
		
		LawsManagement lawsManagement = new LawsManagement();
		lawsManagement.createTable();
		
		PermitManagement permitManagement = new PermitManagement();
		permitManagement.createTable();
		
		Show show = new Show();
		show.showLoginRegisterPage();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
