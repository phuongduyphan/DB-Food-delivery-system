package UI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

public class AddDishController implements Initializable{
	
	@FXML
	private TextField newDishTextField;
	@FXML
	private TextField newpriceTextField;
	
	@FXML
	private TableView<Food> tableUser;
	
	@FXML
	private TableColumn<Food, String> idCol;
	@FXML
	private TableColumn<Food, String> nameCol;
	
	private ArrayList<Food> listFood;
	
	
	@FXML
	private void createButton(ActionEvent event) {
		String newDishName = newDishTextField.getText();
		float newPrice = Float.parseFloat(newpriceTextField.getText());
		Food food = tableUser.getSelectionModel().getSelectedItem();
		Dish dish = new Dish(1, GuiManager.menu, food, newPrice);
		Database.getInstance().addDishToMenu(GuiManager.menu, dish);
/*		
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("DishGui.fxml"));
		       
		        Scene scene = new Scene(loader);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        
		        stage.show();
		    }catch (IOException io){
		        io.printStackTrace();
		    */	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listFood = new ArrayList<Food>();
		listFood = Database.getInstance().getFoods();
		
		ObservableList<Food> data =FXCollections.observableArrayList(listFood);
		idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		tableUser.setItems(null);
		tableUser.setItems(data);
		tableUser.setEditable(true);
	}

}
