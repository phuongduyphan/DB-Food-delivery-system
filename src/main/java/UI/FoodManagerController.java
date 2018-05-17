package UI;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.Database;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
public class FoodManagerController implements Initializable{
	

	public FoodManagerController() {
	}
	
	@FXML
	private TextField newfoodTextField;
	
	@FXML
	private TableView<Food> tableUser;
	
	@FXML
	private TableColumn<Food, String> idCol;
	@FXML
	private TableColumn<Food, String> nameCol;
	
	private ArrayList<Food> listFood;
	@SuppressWarnings("static-access")
	@FXML
	private void backButton(ActionEvent event) {
		System.out.println("clicked");
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("Mainmenu.fxml"));
		       
		        Scene scene = new Scene(loader);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        
		        stage.show();
		    }catch (IOException io){
		        io.printStackTrace();
		    }
	}
	
	@FXML
	private void changeIdFood(CellEditEvent edittedCell) {
		Food food = tableUser.getSelectionModel().getSelectedItem();
		food.setID(Integer.parseInt(edittedCell.getNewValue().toString()));
	}
	
	@FXML
	private void changeFoodName(CellEditEvent edittedCell) {
		Food food = tableUser.getSelectionModel().getSelectedItem();
		food.setName(edittedCell.getNewValue().toString());
	}
	
	@FXML
	private void newFood(ActionEvent event) throws SQLException {
		String newFoodName = newfoodTextField.getText();
		System.out.println("add " + newFoodName + " to database! ");
		Food food = new Food(1, newFoodName);
		Database.getInstance().createFood(food);	
		loadButton(event);
		
	}
	
	@FXML
	private void viewFoodButton(ActionEvent event) {
		Food food = tableUser.getSelectionModel().getSelectedItem();
		GuiManager.food = food;
		System.out.println(GuiManager.food.getId());
		
		System.out.println("clicked");
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("FoodUI.fxml"));
		       
		        Scene scene = new Scene(loader);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        
		        stage.show();
		    }catch (IOException io){
		        io.printStackTrace();
		    }
		
	}
	
	@FXML
	private void deleteButton(ActionEvent event) throws SQLException {
		Food food = tableUser.getSelectionModel().getSelectedItem();
		tableUser.getItems().remove(food);
		Database.getInstance().deleteFood(food);	
	}

	@FXML
	private void loadButton(ActionEvent event) throws SQLException {
		listFood = Database.getInstance().getFoods();
		
		ObservableList<Food> data =FXCollections.observableArrayList(listFood);
		idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		tableUser.setItems(null);
		tableUser.setItems(data);
		
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
		//idCol.setCellFactory(TextFieldTableCell.forTableColumn());
		//nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
	}
	

}
