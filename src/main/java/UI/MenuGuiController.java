package UI;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import database.Database;
import database.Menu;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MenuGuiController implements Initializable{
	
	private ArrayList<Menu> menuList;
	
	@FXML
	private TextField newMenuTextField;
	
	@FXML
	private TableView<Menu> tableUser;
	
	@FXML
	private TableColumn<Menu, String> idCol;
	@FXML
	private TableColumn<Menu, String> nameCol;
	@FXML
	private TableColumn<Menu, String> avaiCol;
	
	@FXML
	private void changeIdMenu(CellEditEvent edittedCell) {
		Menu menu = tableUser.getSelectionModel().getSelectedItem();
		menu.setID(Integer.parseInt(edittedCell.getNewValue().toString()));
	}
	
	@FXML
	private void changeMenuName(CellEditEvent edittedCell) {
		Menu menu = tableUser.getSelectionModel().getSelectedItem();
		menu.setName(edittedCell.getNewValue().toString());
	}
	
	
	@FXML
	private void availabilityButton(CellEditEvent edittedCell) {
		Menu menu = tableUser.getSelectionModel().getSelectedItem();
		menu.setAvailability(Boolean.parseBoolean(edittedCell.getNewValue().toString()));
	}
	
	@FXML
	private void loadButton(ActionEvent event) throws SQLException {
		System.out.println("click load");
		menuList = Database.getInstance().getMenus();
		
		ObservableList<Menu> data =FXCollections.observableArrayList(menuList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		tableUser.setItems(data);
		
	}
	
	@FXML
	private void deleteMenuButton(ActionEvent event) throws SQLException {
		Menu menu = tableUser.getSelectionModel().getSelectedItem();
		tableUser.getItems().remove(menu);
		Database.getInstance().deleteMenu(menu);	
	}
	
	@FXML
	private void viewButton(ActionEvent event) {
		Menu menu = tableUser.getSelectionModel().getSelectedItem();
		GuiManager.menu = menu;
		 try {
			 	Parent loader = new FXMLLoader().load(getClass().getResource("DishGui.fxml"));
		       
		        Scene scene = new Scene(loader);
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        
		        stage.show();
		    }catch (IOException io){
		        io.printStackTrace();
		    }
	}
	
	@FXML
	private void newMenu(ActionEvent event) throws SQLException {
		String newMenuName = newMenuTextField.getText();
		System.out.println("add " + newMenuName + " to database! ");
		Menu menu = new Menu(1, newMenuName, true);
		Database.getInstance().createMenu(menu);		
		
	}
	
	
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
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		menuList = new ArrayList<Menu>();
		menuList = Database.getInstance().getMenus();
		
		ObservableList<Menu> data =FXCollections.observableArrayList(menuList);
		idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
		tableUser.setItems(data);
		tableUser.setEditable(true);
	}

}
