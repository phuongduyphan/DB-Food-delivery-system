package UI;

import database.Database;
import database.OrderAttribute;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
public class OrdersView extends Application{
	GridPane gridPane=new GridPane();
	Button delete= new Button("DELETE");
	Button add=new Button("ADD ORDER");
	ArrayList<OrderAttribute> list =new ArrayList<OrderAttribute>();
	TableView <OrderAttribute> table= new TableView<OrderAttribute>();

	public static void main(String[] args) {
	      launch(args);
	  }
	
	@Override
	public void start(Stage stage) {
		TableColumn<OrderAttribute, Integer> id //
	    = new TableColumn <OrderAttribute, Integer> ("ID");
		id.setCellValueFactory(new PropertyValueFactory<OrderAttribute,Integer>("id"));

		TableColumn<OrderAttribute, String> customer  //
	    = new TableColumn <OrderAttribute,String> ("Customer");
		customer.setCellValueFactory(new PropertyValueFactory<OrderAttribute,String>("customer"));

		TableColumn<OrderAttribute, String> stageStr  //
	    = new TableColumn <OrderAttribute, String> ("Stage");
		stageStr.setCellValueFactory(new PropertyValueFactory<OrderAttribute,String>("stage"));


		add.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {
	        	  CustomersView view =new CustomersView();
	        	  Stage scene = new Stage();
	        	  view.start(scene);
	          }
		});
		delete.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {
	        	  Database.getInstance().deleteOrder(table.getSelectionModel().getSelectedItem());
	        	  table.getItems().removeAll(
	                      table.getSelectionModel().getSelectedItems()
	              );
	          }
		});
		table.getColumns().addAll(id, customer,stageStr);
		list= Database.getInstance().getOrders();
		ObservableList<OrderAttribute> data =FXCollections.observableArrayList(list);
		table.setItems(data);
		System.out.println(list.size());

		add.setMinSize(100, 100);
		delete.setMinSize(100, 100);
		gridPane.setMinSize(720, 720);

	      //Setting the padding
	      gridPane.setPadding(new Insets(10, 10, 10, 10));

	      //Setting the vertical and horizontal gaps between the columns
	      gridPane.setVgap(5);
	      gridPane.setHgap(5);

	      //Setting the Grid alignment
	      gridPane.setAlignment(Pos.CENTER);


		gridPane.add(table,0,0);
		gridPane.add(delete, 1, 0);
		gridPane.add(add, 2 , 0);
		Scene scene = new Scene(gridPane);

	      //Setting title to the Stage
	      stage.setTitle("Customer");

	      //Adding scene to the stage
	      stage.setScene(scene);

	      //Displaying the contents of the stage
	      stage.show();

	}
	
	
}
