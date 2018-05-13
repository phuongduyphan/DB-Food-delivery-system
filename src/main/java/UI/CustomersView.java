package UI;

import database.Customer;
import database.Database;
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
public class CustomersView extends Application {
	
	
	
	GridPane gridPane=new GridPane();
	Button create=new Button("CREATE");
	Button update=new Button("UPDATE");
	Button delete= new Button("DELETE");
	Button add=new Button("VIEW ORDER");
	ArrayList<Customer> list =new ArrayList<Customer>();
	TableView <Customer> table= new TableView<Customer>();
	
	public static void main(String[] args) {
	      launch(args);
	  }
	
	@Override
	public void start(Stage stage)  {

		TableColumn<Customer, Integer> id //
	    = new TableColumn <Customer, Integer> ("ID");
		id.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("id"));

		TableColumn<Customer, String> name //
	    = new TableColumn <Customer, String> ("Name");
		name.setCellValueFactory(new PropertyValueFactory<Customer,String>("Name"));

		TableColumn<Customer, Integer> tel //
	    = new TableColumn <Customer, Integer> ("Tel");
		tel.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("Tel"));


		TableColumn<Customer, String> address //
	    = new TableColumn <Customer, String> ("Address");
		address.setCellValueFactory(new PropertyValueFactory<Customer,String>("address"));


		create.setMinSize(100, 100);
		update.setMinSize(100,100 );
		delete.setMinSize(100, 100);
		add.setMinSize(100,100);
		table.getColumns().addAll(id, name, tel, address);
		table.setEditable(true);
		list=Database.getInstance().getCustomers();
		 ObservableList<Customer> data =FXCollections.observableArrayList(list);
		table.setItems(data);
		System.out.println(list.size());



		create.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {
	        	  AddCustomer view= new AddCustomer();
	        	  Stage scene= new Stage();
	        	  view.start(scene);
	          }
	       });
		update.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {
	        	  UpdateCustomer view= new UpdateCustomer();
	        	  Stage scene= new Stage();
	        	  view.start(scene);

	          }
	       });
		delete.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {

	        	  Database.getInstance().deleteCustomer(table.getSelectionModel().getSelectedItem());
	        	  table.getItems().removeAll(
	                      table.getSelectionModel().getSelectedItems()
	              );
	          }
	       });
		add.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
	          @Override
	          public void handle(javafx.scene.input.MouseEvent e) {
	        	  Customer cus=table.getSelectionModel().getSelectedItem();
	        	  System.out.println(cus.getName());
	        	  CustomerView view= new CustomerView(cus);
	        	  Stage scene = new Stage();
	        	  view.start(scene);

	          }
	       });
		gridPane.setMinSize(720, 720);

	      //Setting the padding
	      gridPane.setPadding(new Insets(10, 10, 10, 10));

	      //Setting the vertical and horizontal gaps between the columns
	      gridPane.setVgap(5);
	      gridPane.setHgap(5);

	      //Setting the Grid alignment
	      gridPane.setAlignment(Pos.CENTER);


		gridPane.add(table,0,0);
		gridPane.add(create, 1, 0);
		gridPane.add(update, 2, 0);
		gridPane.add(delete, 3, 0);
		gridPane.add(add, 4, 0);
		//Creating a scene object
	      Scene scene = new Scene(gridPane);

	      //Setting title to the Stage
	      stage.setTitle("Customer");

	      //Adding scene to the stage
	      stage.setScene(scene);

	      //Displaying the contents of the stage
	      stage.show();
	}
	
	private class AddCustomer extends Application
	{

		Text nameLabel = new Text("Name");

	      //Text field for name
	      TextField nameText = new TextField();
	      Text adressLabel= new Text("Address");
	      TextField adressText=new TextField();
	      Text TelephoneLabel= new Text("Telephone");
	      TextField TelephoneText=new TextField();
	      Button confirm= new Button("Confirm");
		public void start(Stage stage )  {
			 confirm.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
		          @Override
		          public void handle(javafx.scene.input.MouseEvent e) {

		        	  String name=nameText.getText();
		        	  String address=adressText.getText();
		        	  String telephone=TelephoneText.getText();
		        	  Customer customer=new Customer(null,name,Integer.parseInt(telephone),address);
		        	  stage.close();
		        	Database.getInstance().createCustomer(customer);
		        	list=Database.getInstance().getCustomers();
		        	ObservableList<Customer> data =FXCollections.observableArrayList(list);
		        	table.setItems(data);
		          }
		       });
			 GridPane gridPane = new GridPane();
		      gridPane.setMinSize(720, 720);
		      gridPane.setPadding(new Insets(10, 10, 10, 10));

		      gridPane.setVgap(5);
		      gridPane.setHgap(5);

		      gridPane.setAlignment(Pos.CENTER);

		      gridPane.add(nameLabel, 0, 0);
		      gridPane.add(nameText, 1, 0);

		      gridPane.add(TelephoneLabel, 0, 1);
		      gridPane.add(TelephoneText, 1, 1);

		      gridPane.add(adressLabel, 0, 2);
		      gridPane.add(adressText, 1, 2);

		      gridPane.add(confirm, 1, 3);
		      gridPane.setStyle("-fx-background-color: BEIGE;");

		      Scene scene = new Scene(gridPane);
		      stage.setTitle("Customer");

		      stage.setScene(scene);

		      stage.show();

		}

	}
	
	private class UpdateCustomer extends Application
	{


		Customer current=table.getSelectionModel().getSelectedItem();
		Text nameLabel = new Text("Name");

	      //Text field for name
	      TextField nameText = new TextField(current.getName());
	      Text adressLabel= new Text("Address");
	      TextField adressText=new TextField(current.getAddress());
	      Text TelephoneLabel= new Text("Telephone");
	      TextField TelephoneText=new TextField(String.valueOf(current.getTel()));
	      Button confirm= new Button("Confirm");
		public void start(Stage stage )  {
			 confirm.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
		          @Override
		          public void handle(javafx.scene.input.MouseEvent e) {

		        	  String name=nameText.getText();
		        	  String address=adressText.getText();
		        	  String telephone=TelephoneText.getText();
		        	  current.setName(name);
		        	  current.setAddress(address);
		        	  current.setTel(Integer.valueOf(telephone));
		        	  stage.close();
		        	Database.getInstance().updateCustomer(current);
		        	list=Database.getInstance().getCustomers();
		        	ObservableList<Customer> data =FXCollections.observableArrayList(list);
		        	table.setItems(data);
		          }
		       });
			 GridPane gridPane = new GridPane();
		      gridPane.setMinSize(720, 720);
		      gridPane.setPadding(new Insets(10, 10, 10, 10));

		      gridPane.setVgap(5);
		      gridPane.setHgap(5);

		      gridPane.setAlignment(Pos.CENTER);

		      gridPane.add(nameLabel, 0, 0);
		      gridPane.add(nameText, 1, 0);

		      gridPane.add(TelephoneLabel, 0, 1);
		      gridPane.add(TelephoneText, 1, 1);

		      gridPane.add(adressLabel, 0, 2);
		      gridPane.add(adressText, 1, 2);

		      gridPane.add(confirm, 1, 3);
		      gridPane.setStyle("-fx-background-color: BEIGE;");

		      Scene scene = new Scene(gridPane);
		      stage.setTitle("Customer");

		      stage.setScene(scene);

		      stage.show();

		}

	}
}
