package UI;

import database.Customer;
import database.Database;
import database.Dish;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
public class OrdersView extends Application{
	GridPane gridPane=new GridPane();
	Button delete= new Button("DELETE");
	Button add=new Button("ADD ORDER");
	Button edit=new Button("EDIT STAGE");
	ArrayList<OrderAttribute> list =new ArrayList<OrderAttribute>();
	TableView <OrderAttribute> table= new TableView<OrderAttribute>();
	ArrayList<String> listStage=new ArrayList<String>();

	public static void main(String[] args) {
	      launch(args);
	  }
	
	private class StageChange extends Application
    {

    	Button[] buttonSet =new Button[7];
		OrderAttribute order= table.getSelectionModel().getSelectedItem();

    	@Override
    	public void start(Stage stage)  {
    		GridPane gridPane2= new GridPane();
    		gridPane2.setVgap(5);
    		gridPane2.setHgap(5);
    		for (int i=0;i<7;i++)
    		{
    			buttonSet[i]=new Button(listStage.get(i));
    			buttonSet[i].setMinSize(100, 100);
    			gridPane2.add(buttonSet[i], i%2, i/2);
    			String text1= listStage.get(i);
    			buttonSet[i].setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
    				String text=text1;
    				@Override
    				public void handle(javafx.scene.input.MouseEvent e) {
    					System.out.println(order.getCustomer());
    					System.out.println(text);
    					order.setStage(text);
    					System.out.println(order.getStage());
    					System.out.println("UPDATE `Order` SET CustomerID= " + order.getCustomerID() + " ,\n" +
                      "StageStr = '" + order.getStage() + "' " +
                      "WHERE `ID`='" + order.getId());
    					order.save();
    					stage.close();
    					list= Database.getInstance().getOrders();
    					ObservableList<OrderAttribute> data =FXCollections.observableArrayList(list);
    					table.setItems(data);
    				}
    			});
    		}
    		

    		Scene scene=new Scene(gridPane2);
    		stage.setTitle("Stage");
    		stage.setScene(scene);
    		stage.show();

    	}
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
		edit.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
			OrderAttribute order= table.getSelectionModel().getSelectedItem();
	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {
	        	  StageChange view = new StageChange();
	        	  Stage scene=new Stage();
	        	  view.start(scene);
	          }
		});
		//listStage
		listStage.add("Cancelled");
		listStage.add("Cooking");
		listStage.add("Delivered");
		listStage.add("Delivering");
		listStage.add("Packed");
		listStage.add("Packing");
		listStage.add("Received");

		//--------------------
		
		
		table.getColumns().addAll(id, customer,stageStr);
		list= Database.getInstance().getOrders();
		ObservableList<OrderAttribute> data =FXCollections.observableArrayList(list);
		table.setItems(data);
		System.out.println(list.size());

		add.setMinSize(100, 100);
		delete.setMinSize(100, 100);
		edit.setMinSize(100,100);
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
		gridPane.add(edit, 3, 0);
		Scene scene = new Scene(gridPane);

	      //Setting title to the Stage
	      stage.setTitle("Customer");

	      //Adding scene to the stage
	      stage.setScene(scene);

	      //Displaying the contents of the stage
	      stage.show();

	}
	public static void main1(String[] args) {
        launch(args);
    }
	
	
}
