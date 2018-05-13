package UI;

import database.*;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
public class OrderView extends Application { 
	Customer customer;
	String name,telephone,address;
	Text menuLabel= new Text("MENU");
	Text dishLabel= new Text("DISH");
    Button buttonMenu[]=new Button[6];
    
    GridPane buttonSet =new GridPane();
    Button confirm= new Button("Confirm");

    GridPane gridPane = new GridPane();    
    Button nextMenu= new Button("NEXT");
    Button backMenu= new Button("BACK");
    
    Button nextDish= new Button("NEXT");
    Button backDish= new Button("BACK");
    
    ArrayList<Menu> menuList= new ArrayList<Menu>();
    ArrayList<String> menuButton=new ArrayList<String>();
    ArrayList<DishQuantity> list =new ArrayList<DishQuantity>();
	TableView <DishQuantity> table= new TableView<DishQuantity>();
	ObservableList<DishQuantity> data;
    Button buttonDish[]=new Button[6];
    GridPane dishSet = new GridPane();
    int menuPage=1;
    int dishPage=1;
    ArrayList<Dish> dishes=new ArrayList<Dish>();
    
		public OrderView(Customer customer)
		{
			this.customer=customer;
		}
    
   public static void main(String args[]){
      launch(args);
   }
	
		@Override
   public void start(Stage stage) {
      //
      // menu list
		//----------------------------------------
      menuList=Database.getInstance().getMenus();
      for (int i=0;i<menuList.size();i++)
      {
    	  menuButton.add(menuList.get(i).getName());
      }
      System.out.println(menuButton.size());
		if(menuButton.size()<=6)
		{
			backMenu.setDisable(true);
			nextMenu.setDisable(true);
			for (int i=0;i<6;i++)
		      {
		    	  if (i<menuButton.size())

		    		  {
		    		  	buttonMenu[i]=new Button(menuButton.get(i));
		    		  	buttonMenu[i].setOnMouseClicked(new MenuHandle(menuList.get(i)));
		    		  }
		    	  else
		    		  {
		    		  	buttonMenu[i]=new Button("");
		    		  	buttonMenu[i].setDisable(true);
		    		  }
		    	  buttonMenu[i].setMinSize(100, 100);

		      }
		}
		else
		{
			for (int i=0;i<6;i++)
			{
				buttonMenu[i]=new Button(menuButton.get(i));
    		  	buttonMenu[i].setOnMouseClicked(new MenuHandle(menuList.get(i)));
				buttonMenu[i].setMaxSize(1000, 1000);
				buttonMenu[i].setMinSize(100, 100);
			}
			nextMenu.setOnMouseClicked(new NextMenu());
			backMenu.setOnMouseClicked(new BackMenu());
		}
		nextMenu.setMinSize(100, 100);
		backMenu.setMinSize(100, 100);
		buttonSet.add(nextMenu, 1, 4);
		buttonSet.add(backMenu, 0, 4);

		buttonSet.setVgap(5);
		buttonSet.setHgap(5);
		for (int i=0;i<6;i++)
		{
			buttonSet.add(buttonMenu[i], i%2, i/2);
		}

		//end menu list
     //--------------------------------------------------------------------





		// Dish list
		//-------------------------------------

		for (int i=0;i<6;i++)
		{
			buttonDish[i]=new Button();
			dishSet.add(buttonDish[i],i/2 , i%2);
			buttonDish[i].setMinSize(100, 100);
			buttonDish[i].setDisable(true);
		}
		nextDish.setMinSize(100, 100);
		backDish.setMinSize(100, 100);
		nextDish.setDisable(true);
		backDish.setDisable(true);
		dishSet.add(nextDish,3,0);
		dishSet.add(backDish, 3, 1);
		dishSet.add(dishLabel, 4, 0);
		dishSet.setVgap(5);
		dishSet.setHgap(5);

		//end Dish list
		//-------------------------------------------------


		//confirm button
		//---------------------------------------------
		confirm.setMinSize(100, 100);
		 confirm.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
			 Dish dish;
				Integer quantity;
				OrderAttribute order;
				ArrayList<OrderAttribute> list =new ArrayList<OrderAttribute>();
				@Override
				public void handle(MouseEvent arg0) {
					order=new OrderAttribute(customer.getId());
					Database.getInstance().createOrder(order);
					list=Database.getInstance().getOrders();
					order=list.get(list.size()-1);
					for (int i=0;i<data.size();i++)
					{
						dish=data.get(i).getRealDish();
						quantity=data.get(i).getQuantity();
						System.out.println("dish ID: "+ dish.getID());
						for (int j=0;j<quantity;j++)
						{
							order.addDish(dish);
						}
					}
					stage.close();
				}
		 });

		//-------------------------------


		//table---------------------------------
		TableColumn<DishQuantity, String> name //
	    = new TableColumn <DishQuantity, String> ("FOOD");
		name.setCellValueFactory(new PropertyValueFactory<DishQuantity, String>("Dish"));

		TableColumn<DishQuantity, Integer> quanti  //
	    = new TableColumn <DishQuantity, Integer> ("Quantity");
		quanti.setCellValueFactory(new PropertyValueFactory<DishQuantity, Integer>("Quantity"));


		TableColumn<DishQuantity, Float> price //
	    = new TableColumn <DishQuantity, Float> ("PRICE");
		price.setCellValueFactory(new PropertyValueFactory<DishQuantity, Float>("Price"));

		table.getColumns().addAll(name,quanti,price);
		table.setEditable(true);
		//---------------------------





      //Setting size for the pane
      gridPane.setMinSize(720, 720);

      //Setting the padding
      gridPane.setPadding(new Insets(10, 10, 10, 10));

      //Setting the vertical and horizontal gaps between the columns
      gridPane.setVgap(5);
      gridPane.setHgap(5);

      //Setting the Grid alignment
      gridPane.setAlignment(Pos.CENTER);

      //Arranging all the nodes in the grid
      gridPane.add(table, 1, 1);
      gridPane.add(menuLabel, 2, 0);
      gridPane.add(buttonSet, 2, 1);
      gridPane.add(dishSet, 1, 2,2,2);
      gridPane.add(confirm, 0, 2);
      //Setting the back ground color
      gridPane.setStyle("-fx-background-color: BEIGE;");

      dishLabel.setStyle("-fx-font: normal bold 19px 'serif' ");
      menuLabel.setStyle("-fx-font: normal bold 19px 'serif' ");
      //Creating a scene object
      Scene scene = new Scene(gridPane);

      //Setting title to the Stage
      stage.setTitle("Customer");

      //Adding scene to the stage
      stage.setScene(scene);

      //Displaying the contents of the stage
      stage.show();
   }

    private class Quantity extends Application
    {

    	Text quantityLable =new Text("Quantity");
    	TextField quantityText= new TextField();
    	Integer quantity;
    	Dish dish;

    	public Quantity(Dish dish)
    	{
    		this.dish=dish;
    		System.out.println(dish.getFood().getName());
    	}
    	@Override
    	public void start(Stage stage)  {

    		System.out.println(dish.getFood().getName());
    		GridPane gridPane2= new GridPane();
    		gridPane2.setVgap(5);
    		gridPane2.add(quantityLable, 0, 0);
    		gridPane2.add(quantityText, 0, 1);


    		Scene scene=new Scene(gridPane2);
    		stage.setTitle("Quantity");
    		stage.setScene(scene);
    		Button confirm= new Button("Confirm");
    		confirm.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
              @Override
              public void handle(javafx.scene.input.MouseEvent e) {
            	  quantity=Integer.valueOf(quantityText.getText());
            	  System.out.println(quantity);
            	  stage.close();
            	  list.add(new DishQuantity(dish,quantity));
            	  System.out.print(list.size());
				data =FXCollections.observableArrayList(list);
          	  System.out.println(data.size());
				table.setItems(data);



              }
           });
    		gridPane2.add(confirm, 0, 2);
    		stage.show();

    	}
    }
	
	private class DishHandle implements EventHandler<javafx.scene.input.MouseEvent>
    {


	    Dish dish;
		public DishHandle(Dish name)
		{
			this.dish=name;
		}
		@Override
		public void handle(MouseEvent arg0) {
			Quantity quantity = new Quantity(dish);
	         Stage scene= new Stage();
	         quantity.start(scene);
	         }

    }
	
	private class NextMenu implements EventHandler<javafx.scene.input.MouseEvent>
    {


		@Override
		public void handle(MouseEvent arg0) {
			backMenu.setDisable(false);
			System.out.println(menuPage);
			if (menuButton.size()-menuPage>0)
				menuPage+=6;
			int i;
			for (i=0;i<6;i++)
		      {
					if (menuPage+i <=menuButton.size())
					{
						buttonMenu[i].setText(menuButton.get(menuPage+i-1));
						buttonMenu[i].setOnMouseClicked(new MenuHandle(menuList.get(menuPage+i-1)));
					}
					else break;
		      }
			while (i<6)
			{
				buttonMenu[i].setText("");
				buttonMenu[i].setDisable(true);
				nextMenu.setDisable(true);
				i++;
			}

		}

    }
	
	private class MenuHandle implements EventHandler<javafx.scene.input.MouseEvent>
    {

		Menu menu;
		public MenuHandle(Menu menu)
		{
			this.menu=menu;
			System.out.println(menu.getName());
		}
		@Override
		public void handle(MouseEvent arg0) {
			dishes=Database.getInstance().getDishes(menu);
			dishPage=1;
			ArrayList<String> dishName=new ArrayList<String>();

			for (int i=0;i<dishes.size();i++)
			{
				dishName.add(dishes.get(i).getFood().getName());
			}
			for (int i=0;i<6;i++)
			{
				if (i<dishName.size())
				{
					buttonDish[i].setText(dishName.get(i));
					buttonDish[i].setDisable(false);
					buttonDish[i].setOnMouseClicked(new DishHandle(dishes.get(i)));

				}
				else
					buttonDish[i].setDisable(true);
			}
			if (dishes.size()<6)
			{
				nextDish.setDisable(true);
				backDish.setDisable(true);
			}
			else
			{
				nextDish.setDisable(false);
				backDish.setDisable(true);
			}
		}
    }

	private class BackMenu implements EventHandler<javafx.scene.input.MouseEvent>
    {


		@Override
		public void handle(MouseEvent arg0) {
			nextMenu.setDisable(false);
			System.out.println(menuPage);
			if (menuPage!=1)
				menuPage-=6;
			int i;
			for (i=0;i<6;i++)
		      {
					if (menuPage+i <=menuButton.size())
					{
						buttonMenu[i].setText(menuButton.get(menuPage+i-1));
						buttonMenu[i].setDisable(false);
						buttonMenu[i].setOnMouseClicked(new MenuHandle(menuList.get(menuPage+i-1)));
					}
					else break;
		      }
			while (i<6)
			{
				buttonMenu[i].setText("");
				buttonMenu[i].setDisable(true);
				nextMenu.setDisable(true);
				i++;
			}
			if (menuPage==1)
			{
				backMenu.setDisable(true);
			}
		}

    }

	private class NextDish implements EventHandler<javafx.scene.input.MouseEvent>
    {

		@Override
		public void handle(MouseEvent arg0) {
			backDish.setDisable(false);
			int i;
			for (i=0;i<6;i++)
		      {
					if (dishPage+i <=dishes.size())
					{
						buttonDish[i].setText(dishes.get(dishPage+i-1).getFood().getName());
						buttonDish[i].setOnMouseClicked(new DishHandle(dishes.get(dishPage+i-1)));
					}
					else break;
		      }
			while (i<6)
			{
				buttonDish[i].setText("");
				buttonDish[i].setDisable(true);
				nextDish.setDisable(true);
				i++;
			}

		}
    }
}