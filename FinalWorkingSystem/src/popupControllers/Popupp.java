
// to do : this should have a constructor to give message and title.
// to do : CSS of this.




package popupControllers;
 
import javafx.application.Application; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage; 
 
public class Popupp extends Application { 
    private String title;
    private String message;
    public Popupp(String t, String m){
    	title = t;
    	message = m;
    }
    @Override 
    public void start(final Stage primaryStage) { 
        primaryStage.setTitle(title);
        final HBox layout = new HBox(10); 
        Text alert = new Text(message);
        Button ok = new Button("OK"); 
        
        ok.setOnAction(new EventHandler<ActionEvent>() { 
            @Override 
            public void handle(ActionEvent event) { 
                primaryStage.close(); 
            } 
        }); 
 
 
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;"); 
        layout.getChildren().addAll(alert, ok); 
        primaryStage.setScene(new Scene(layout)); 
        primaryStage.show(); 
    } 
 
}