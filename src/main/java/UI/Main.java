package UI;

import java.io.IOException;
import javafx.application.Application ;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane ;
import javafx.stage.Stage ;
import javafx.stage.StageStyle;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	private double mouseX = 0;
	private double mouseY = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("Mainmenu.fxml"));
    	
    	primaryStage.initStyle(StageStyle.TRANSPARENT);
    	root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
            public void handle(MouseEvent event) {
				mouseX = event.getSceneX();
				mouseY = event.getSceneY();
				System.out.println(mouseX + "   " + mouseY);
            }
        });
    	
    	root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - mouseX);
				primaryStage.setY(event.getScreenY() - mouseY);
			}
		});
    	Scene scene = new Scene(root);
    	
    	scene.setFill(javafx.scene.paint.Color.TRANSPARENT);
    	primaryStage.setScene(scene);
    	primaryStage.show();
     }

    public static void main(String[] args) {
        launch(args);
    }
}