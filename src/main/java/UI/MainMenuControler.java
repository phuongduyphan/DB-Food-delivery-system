package UI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenuControler implements Initializable{
	
	@SuppressWarnings("static-access")
	@FXML
	private void FoodManagerButton(ActionEvent event) {
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("FoodManager.fxml"));
		       
		        Scene scene = new Scene(loader);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        stage.show();
		    }catch (IOException io){
		        io.printStackTrace();
		    }
	}
	@FXML
	private void exitClick() {
		System.exit(0);
	}
	
	@FXML 
	private void showMenuButton(ActionEvent event) {
		System.out.println("menu click ");
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("MenuGUI.fxml"));
		       
		        Scene scene = new Scene(loader);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        stage.show();
		    }catch (IOException io){
		        io.printStackTrace();
		    }
	}
	
	
	
	@FXML
	private void customerButton(ActionEvent event) {
		System.out.println("customer click");
		CustomersView view = new CustomersView();
		Stage scene = new Stage();
		view.start(scene);
	}
	@FXML
	private void orderButon(ActionEvent event) {
		System.out.println("order click");
		OrdersView view = new OrdersView();
		Stage scene = new Stage();
		view.start(scene);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
