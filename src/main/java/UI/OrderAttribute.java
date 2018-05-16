package UI;

import java.util.ArrayList;

import database.Database;

public class OrderAttribute
	{
		private Integer id;
		private String  stage;
		private Integer customer;
		public OrderAttribute(Integer id,Integer  customer, String stage) {
		
	        this.id = id;

	        this.customer = customer;
	        this.stage = stage;
	    }
		public OrderAttribute(Integer  customer, String stage) {
			
	        this.id = null;

	        this.customer = customer;
	        this.stage = stage;
	    }
		public OrderAttribute(Integer  customer) {
			
	        this.id = null;

	        this.customer = customer;
	        this.stage = "Received";
	    }
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getStage() {
			return stage;
		}
		public void setStage(String stage) {
			this.stage = stage;
		}
		public String getCustomer() {
			return Database.getInstance().getCustomer(customer).getName();
		}
		public Integer getCustomerID() {
			return customer;
		}
		public void setCustomer(Integer customer) {
			this.customer = customer;
		}
		public boolean save() {
	        return Database.getInstance().updateOrder(this);
	    }
		public boolean addDish(Dish dish) {
	        return Database.getInstance().addDishToOrder(this, dish);
	    }

	    public boolean removeDish(Dish dish) {
	        return Database.getInstance().removeDishFromOrder(this, dish);
	    }

	    public ArrayList getDishes() {
	        return Database.getInstance().getDishes(this);
	    }

		
	}