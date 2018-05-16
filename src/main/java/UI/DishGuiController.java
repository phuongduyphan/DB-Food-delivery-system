package UI;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.naming.ldap.ManageReferralControl;

import database.Database;
import database.Dish;
import database.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DishGuiController implements Initializable{
	
	private ArrayList<Dish> dishList;
	@FXML
	private TextField newDishTextField;
	
	@FXML
	private TableView<Dish> tableUser;
	
	@FXML
	private TableColumn<Dish, String> idCol;
	@FXML
	private TableColumn<Dish, String> menuIdCol;
	
	@FXML
	private TableColumn<Dish, String> foodIdCol;
	@FXML
	private TableColumn<Dish, String> priceCol;
	
	@FXML
	private void backButton(ActionEvent event) {
		System.out.println("clicked back button");
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("MenuGui.fxml"));
		       
		        Scene scene = new Scene(loader);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        
		        stage.show();
		    }catch (IOException io){
		        io.printStackTrace();
		    }
	}
	
	
	@FXML 
	private void newDish(ActionEvent event) {
		/*String newDishName = newDishTextField.getText();
		System.out.println("add " + newDishName + " to database! ");
		Dish dish = new Dish(dishList.size() + 1, GuiManager.menu, );
		Database.getInstance().addDishToMenu(GuiManager.menu, dish);*/
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("AddDishGui.fxml"));
		       
		        Scene scene = new Scene(loader);
		        Stage stage = new Stage();
		        stage.setScene(scene);
		        
		        stage.show();
		    }catch (IOException io){
		        io.printStackTrace();
		    }
		
		
	}
	
	@FXML 
	private void loadButton(ActionEvent event) {
		dishList = Database.getInstance().getDishes(GuiManager.menu);
		
		ObservableList<Dish> data =FXCollections.observableArrayList(dishList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		foodIdCol.setCellValueFactory(new PropertyValueFactory<>("foodID"));
		menuIdCol.setCellValueFactory(new PropertyValueFactory<>("MenuId"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
		tableUser.setItems(data);
	}
	
	@FXML
	private void DeleteButton(ActionEvent event) throws SQLException {
		Dish dish = tableUser.getSelectionModel().getSelectedItem();
		tableUser.getItems().remove(dish);
		Database.getInstance().deleteDish(dish);	
	}

	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		dishList = new ArrayList<Dish>();
		dishList = Database.getInstance().getDishes(GuiManager.menu);
		
		ObservableList<Dish> data =FXCollections.observableArrayList(dishList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		foodIdCol.setCellValueFactory(new PropertyValueFactory<>("foodID"));
		menuIdCol.setCellValueFactory(new PropertyValueFactory<>("MenuId"));
		
		priceCol.setCellValueFactory(new PropertyValueFactory<>("Price"));
		tableUser.setItems(data);
		tableUser.setEditable(true);
		
	}

}
