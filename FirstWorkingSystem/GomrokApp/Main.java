package GomrokApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javaClasses.Declaration;
import javaClasses.Product;
import javaClasses.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	@FXML
	private Pane pane;
	@FXML
	private Label label;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	@FXML
	private Button loginButton;
	@FXML
	private Button addUserButton;
	@FXML
	private ChoiceBox<String> wayChoiceBox;
	@FXML
	private TextField originCountryTextField;
	@FXML
	private TextField productNumTextField;
	@FXML
	private DatePicker dateStartedDatePicker;
	@FXML
	private Button nextButton;
	@FXML
	private void handleButtonAction(ActionEvent event) {
		if(this.checkUserPass(this.username.getText(), this.password.getText())) {
			this.currentUser = this.findUser(this.username.getText());
			System.out.println(this.currentUser.getUsername() + " logged in!");
		}
	}
	@FXML
	private void handleNextButtonAction(ActionEvent event) throws IOException {
//		System.out.println("Clicked!");
		String way = new String();
		switch(wayChoiceBox.getValue()) {
			case "زمینی":
				way = "zamini";
				break;
			case "هوایی" :
				way = "havayi";
				break;
			case "دریایی" :
				way = "daryayi";
				break;
			default:
				way = "Not selected";
				break;
		}
		Declaration d = new Declaration(this.ID, way, this.originCountryTextField.getText(),
				this.dateStartedDatePicker.getValue().toString());
		productNum = Long.parseLong(productNumTextField.getText());
//		this.declarations.add(d);
		this.ID++;
		showGoodsPage(d);
//		showDeclarations();
	}
	
	private Stage primaryStage = new Stage();
	private ArrayList<User> users = new ArrayList<>
		(Arrays.asList(new User("admin", "admin"), new User("1", "1"), new User("2", "2")));
	private User currentUser;
	private long ID = 0;
	private long productNum = 0;
	private ArrayList<Declaration> declarations = new ArrayList<>();
	
	public User findUser(String username) {
		for(User u: users) {
			if(u.getUsername().equals(username))
				return u;
		}
		return new User("", "");
	}
	
	public boolean checkUserPass(String username, String password) {
		User tmp = this.findUser(username);
		if(!tmp.getUsername().equals("") && tmp.getPassword().equals(password))
			return true;
		System.out.println("Incorrect username or password!");
		return false;
	}

	
	public void showUsers() {
		for(User u: users)
			System.out.println("Username: " + u.getUsername() + "    Password: " + u.getPassword());
	}
	
	public void showDeclarations() {
		for(Declaration d: this.declarations) {
			System.out.println("ID: " + d.getID());
			System.out.println("Way: " + d.getWay());
			System.out.println("Origin country: " + d.getOriginCountry());
			System.out.println("Started date: " + d.getStartedDate());
			for(Product p: d.getProducts()) {
				System.out.println("	Product name: " + p.getName());
				System.out.println("	price: " + p.getPrice());
				System.out.println("	weight: " + p.getWeight());
			}
			
			System.out.println("----------------------------------------------------------------------");
		}
	}
	
	public void showLoginPage() throws IOException {
		primaryStage.setTitle("LoginPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("LoginFXML.fxml"));
		Pane loginPage = (Pane) loader.load();
		Scene scene = new Scene(loginPage);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void showDeclarationPage() throws IOException {
		primaryStage.setTitle("DeclarationFormPage");
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DeclarationFormFXML.fxml"));
		Pane declarationPage = (Pane) loader.load();
		Scene scene = new Scene(declarationPage);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void showGoodsPage(Declaration declaration) throws IOException {
		primaryStage.setTitle("GoodsPage");
		Group root = new Group();
		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
	
		final ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMinHeight(300);
		scrollPane.setMinWidth(400);
		final Pane pane = new Pane();
		pane.setMinWidth(300);
		pane.setMinHeight(400);
		scrollPane.setContent(pane);

		Label l1 = new Label("کالای شماره 1");
		l1.setFont(new Font(20));
		l1.relocate(140, 50);
		
		ChoiceBox<String> cboices = new ChoiceBox<String>(FXCollections.observableArrayList("خوراکی", "ورزشی", "دیجیتالی", "وسایل نقلیه", "بهداشتی"));
		cboices.relocate(120, 100);
		cboices.setMinWidth(150);
		
//		TextField tf1 = new TextField();
//		tf1.relocate(120, 100);
//		tf1.setPromptText("نوع کالا");
//		tf1.setAlignment(Pos.CENTER_RIGHT);
		
		TextField tf2 = new TextField();
		tf2.relocate(120, 140);
		tf2.setPromptText("نام کالا");
		tf2.setAlignment(Pos.CENTER_RIGHT);
		
		TextField tf3 = new TextField();
		tf3.relocate(120, 180);
		tf3.setPromptText("وزن کالا");
		tf3.setAlignment(Pos.CENTER_RIGHT);
		
		TextField tf4 = new TextField();
		tf4.relocate(120, 220);
		tf4.setPromptText("قیمت کالا");
		tf4.setAlignment(Pos.CENTER_RIGHT);
		
		Button nextButt = new Button();
		nextButt.setText("کالای بعدی");
		nextButt.relocate(160, 260);
		nextButt.setOnAction(new EventHandler<ActionEvent>() {	
			
			@Override
			public void handle(ActionEvent arg0) {
				declaration.addProduct(new Product(tf2.getText(), Long.parseLong(tf3.getText()),
						Long.parseLong(tf4.getText())));
				l1.setText("کالای شماره " + Integer.toString((declaration.getProducts().size()+1)));
				if(declaration.getProducts().size() == productNum) {
					declarations.add(declaration);
					showDeclarations();
//						showDeclarationPage();
					primaryStage.close();
				}
			}
		});
		
		
		pane.getChildren().addAll(l1, tf2, tf3, tf4, cboices, nextButt);
		root.getChildren().add(scrollPane);
		primaryStage.show();
		showDeclarations();
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		
		this.showUsers();
//		showLoginPage();
		showDeclarationPage();
//		showGoodsPage();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
