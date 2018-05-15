package UI;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.Database;
import database.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddDishController implements Initializable{
	
	@FXML
	private TextField newDishTextField;
	
	@FXML
	private TableView<Food> tableUser;
	
	@FXML
	private TableColumn<Food, String> idCol;
	@FXML
	private TableColumn<Food, String> nameCol;
	
	private ArrayList<Food> listFood;
	
	
	@FXML
	private void createButton(ActionEvent event) {
		
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
