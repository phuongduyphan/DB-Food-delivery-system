package UI;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.Database;
import database.Material;
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

public class FoodUIController implements Initializable{
	private ArrayList<Material> listMaterial;
	
	@FXML
	private TextField newMaterialField;
	
	@FXML
	private TableView<Material> tableUser;
	
	@FXML
	private TableColumn<Material, String> idCol;
	@FXML
	private TableColumn<Material, String> nameCol;
	@FXML
	private TableColumn<Material, String> priceCol;
	
	
	
	@FXML
	private void backButton(ActionEvent event) {
		System.out.println("clicked");
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
	private void addMaterial(ActionEvent event) {
		/*String newMaterialName = newMaterialField.getText();
		//System.out.println(Material);
		System.out.println("add " + newMaterialName + " to database! ");
		float unitPrice = 0;
		Material material = new Material(1, newMaterialName, unitPrice);
		Database.getInstance().addMaterialToFood(GuiManager.food, material, 1);*/
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("AddMenuGUI.fxml"));
		       
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
		
		listMaterial = Database.getInstance().getMaterials(GuiManager.food);
		
		ObservableList<Material> data =FXCollections.observableArrayList(listMaterial);
		idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
		tableUser.setItems(null);
		tableUser.setItems(data);
		
	}
	
	
	@FXML
	private void deleteButton(ActionEvent event) {
		Material material = tableUser.getSelectionModel().getSelectedItem();
		tableUser.getItems().remove(material);
		Database.getInstance().deleteMaterial(material);	
		System.out.println("delete "+ material.getName() + "from database!");
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listMaterial = new ArrayList<Material>();
		listMaterial = Database.getInstance().getMaterials(GuiManager.food);
		
		ObservableList<Material> data =FXCollections.observableArrayList(listMaterial);
		idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		priceCol.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
		tableUser.setItems(null);
		tableUser.setItems(data);
	}
	
	
	
}
