package UI;

import database.Database;
import database.Dish;
import database.Food;
import database.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddNewMaterialView {
	
	@FXML
	private TextField newMenuNameTextField;
	@FXML
	private TextField newPriceTextField;

	
	@FXML
	private void createButton(ActionEvent event) {
		String newDishName = newMenuNameTextField.getText();
		float newPrice = Float.parseFloat(newPriceTextField.getText());
		Material material = new Material(1, newDishName, newPrice);
		Database.getInstance().addMaterialToFood(GuiManager.food, material, 1);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}
	
}
