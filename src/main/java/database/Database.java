package database;

import java.sql.*;
import java.util.ArrayList;

public class Database {

	// private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String server = "localhost"; // Server name
	private static final int port = 3306; // TCP Port
	private static final String user = "root";
	private static final String pass = "17041998";
	private static final String database = "food_delivery";
	private static final String DATABASE_URL = "jdbc:mysql://" + server + ":" + port + "/" + database
			+ "?autoReconnect=true&useSSL=false";

	private static Database instance;
	private static Connection connection;

	private Database() {
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DATABASE_URL, user, pass);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Database getInstance() {
		if (instance == null)
			instance = new Database();
		return instance;
	}

	/**
	 * Remember to close the returned stmt and rs
	 */

	private boolean update(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		int affectedRow = 0;
		try {
			stmt = connection.createStatement();
			affectedRow = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
		return (affectedRow > 0);
	}

	private ArrayList query(String sql, String resultType) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList rows = null;

		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			rows = resultSetToArrayList(rs, resultType);
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException sqlEx) {
				} // ignore
				stmt = null;
			}
		}
		return rows;
	}

    private ArrayList resultSetToArrayList(ResultSet rs, String resultType) throws SQLException {
        resultType = resultType.toLowerCase();
        if (resultType.equals("menu")) return menuToArrayList(rs);
        else if (resultType.equals("dish")) return dishToArrayList(rs);
        else if (resultType.equals("food")) return foodToArrayList(rs);
        else if (resultType.equals("orderattribute")) return orderToArrayList(rs);
        else if (resultType.equals("material")) return materialToArrayList(rs);
        else if (resultType.equals("customer")) return customerToArrayList(rs);
        else if (resultType.equals("stage")) return stageToArrayList(rs);
        else throw new Error("result not found");
    }

	private ArrayList<Menu> menuToArrayList(ResultSet rs) throws SQLException {
		rs.beforeFirst();
		ArrayList<Menu> menus = new ArrayList<>();
		while (rs.next()) {
			menus.add(new Menu(rs.getInt("Menu.ID"), rs.getString("Menu.MenuStr"), true));
		}
		return menus;
	}

    private ArrayList<Dish> dishToArrayList(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        ArrayList<Dish> dishes = new ArrayList<>();
        while (rs.next()) {
            dishes.add(new Dish(rs.getInt("Dish.ID"),
            					getMenu(rs.getInt("Dish.MenuID")),
                                getFood(rs.getInt("Dish.FoodID")),
                                rs.getFloat("Dish.Price")));
        }
        return dishes;
    }

	private ArrayList<Food> foodToArrayList(ResultSet rs) throws SQLException {
		rs.beforeFirst();
		ArrayList<Food> foods = new ArrayList<>();
		while (rs.next()) {
			foods.add(new Food(rs.getInt("Food.ID"), rs.getString("Food.FoodStr")));
			// System.out.println(rs.getString("Food.FoodStr"));
		}
		return foods;
	}

    private ArrayList<OrderAttribute> orderToArrayList(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        ArrayList<OrderAttribute> orders = new ArrayList<>();
        while (rs.next()) {
            orders.add(new OrderAttribute(rs.getInt("Order.ID"),
                                 rs.getInt("Order.CustomerID"),
                                 rs.getString("Order.stageStr")));
        }
        return orders;
    }

	private ArrayList<Material> materialToArrayList(ResultSet rs) throws SQLException {
		rs.beforeFirst();
		ArrayList<Material> materials = new ArrayList<>();
		while (rs.next()) {
			materials.add(new Material(rs.getInt("Material.ID"), rs.getString("Material.MaterialStr"),
					rs.getFloat("Material.UnitPrice")));
		}
		return materials;
	}

	private ArrayList<Customer> customerToArrayList(ResultSet rs) throws SQLException {
		rs.beforeFirst();
		ArrayList<Customer> customers = new ArrayList<>();
		while (rs.next()) {
			customers.add(new Customer(rs.getInt("Customer.ID"), rs.getString("Customer.Name"),
					rs.getInt("Customer.Tel"), rs.getString("Customer.Address")));
		}
		return customers;
	}

	private ArrayList<String> stageToArrayList(ResultSet rs) throws SQLException {
		rs.beforeFirst();
		ArrayList<String> strings = new ArrayList<>();
		while (rs.next()) {
			strings.add(rs.getString("Stage.StageStr"));
		}
		return strings;
	}

	/**
	 * MENU RELATED METHODS
	 */
	public ArrayList getMenus() {
		return query("SELECT * FROM MENU", "menu");
	}

	public Menu getMenu(Integer id) {
		ArrayList<Menu> a = query("SELECT * FROM MENU WHERE ID = " + id, "menu");
		assert (a.size() == 1);
		return a.get(0);
	}

	public boolean createMenu(Menu menu) {
		return update("INSERT INTO `food_delivery`.`menu` (`MenuStr`) VALUES ('" + menu.getName() + "')");
	}

	public boolean deleteMenu(Menu menu) {
		return update("DELETE FROM `food_delivery`.`menu` WHERE `ID`='" + menu.getId() + "';");
	}

	public boolean updateMenu(Menu menu) {
		return update("UPDATE `food_delivery`.`menu` SET `MenuStr`='" + menu.getName() + "' WHERE `ID`='" + menu.getId()
				+ "';");
	}

	public boolean addDishToMenu(Menu menu, Dish dish) {
		return update("INSERT INTO Dish (MenuID, FoodID, Price) " + "VALUES (" + menu.getId() + ","
				+ dish.getFood().getId() + "," + dish.getPrice() + ")");
	}

    /**
     * DISH RELATED METHODS
     */
    public ArrayList getDishes(Menu menu) {
        return query("SELECT * FROM DISH WHERE MenuID = " + menu.getId(),
                     "dish");
    }

    public ArrayList getDishes(OrderAttribute order) {
        return query("SELECT * FROM DISH,contains " +
                     "WHERE contains.OrderID = " + order.getId(),
                     "dish");
    }

	public boolean createDish(Dish dish) {
		return update("INSERT INTO Dish (MenuID, FoodID, Price) " + "VALUES (" + dish.getMenu().getId() + ","
				+ dish.getFood().getId() + "," + dish.getPrice() + ")");
	}

	public boolean deleteDish(Dish dish) {
		return update("DELETE FROM Dish " + "WHERE ID = " + dish.getID());
	}

	public boolean updateDish(Dish dish) {
		return update("UPDATE Dish SET `Price`='" + dish.getPrice() + "' " + ", `MenuID`='" + dish.getMenu().getId()
				+ "' ,FoodID = " + dish.getFood().getId() + " " + "WHERE ID = " + dish.getID());
	}

	/**
	 * FOOD RELATED METHODS
	 */
	public ArrayList getFoods() {
		return query("SELECT * FROM FOOD", "food");
	}

	public ArrayList getFoods(Material material) {
		return query("SELECT * FROM Food, needs " + "WHERE needs.materialID = " + material.getId(), "food");
	}

	public Food getFood(Integer id) {
		ArrayList<Food> a = query("SELECT * FROM Food WHERE ID = " + id, "food");
		assert (a.size() == 1);
		return a.get(0);
	}

	public boolean createFood(Food food) {
		return update("INSERT INTO `food_delivery`.`food` (`FoodStr`) " + "VALUES ('" + food.getName() + "')");
	}

    public boolean addMaterialToFood(Food food, Material material, Integer amount) {
        return update("INSERT INTO `food_delivery`.`needs` (`FoodID`, `MaterialID`, `MaterialAmount`) " +
                      "VALUES ('"+food.getId()+"', '"+material.getId()+"', '"+amount+"');");
    }

	public boolean deleteFood(Food food) {
		return update("DELETE FROM FOOD " + "WHERE ID = " + food.getId());
	}

	public boolean updateFood(Food food) {
		return update("UPDATE Dish SET `FoodStr`='" + food.getName() + "' " + "WHERE `ID`='" + food.getId());
	}

    /**
     * ORDER RELATED METHODS
     */
    public ArrayList getOrders(Customer customer) {
        return query("SELECT * FROM `Order` " +
                     "WHERE `CustomerID` = " + customer.getId(),
                     "orderattribute");
    }

    public ArrayList getOrders() {
        return query("SELECT * FROM `Order` ",
                     "orderattribute");
    }

    public Boolean createOrder(OrderAttribute order) {
        return update("INSERT INTO `Order` (CustomerID,StageStr) " +
                      "VALUES (" + order.getCustomerID() + ",'" + order.getStage() + "')");
    }

    public boolean addDishToOrder(OrderAttribute order, Dish dish) {
        return update("INSERT INTO contains (OrderID, DishID) " +
                      "VALUES (" + order.getId() + "," + dish.getID()+")");
    }

    public boolean removeDishFromOrder(OrderAttribute order, Dish dish) {
        return update("DELETE FROM contains WHERE " +
                      "OrderID = " + order.getId() + " " +
                      "DishID = " + dish.getID());
    }

    public boolean deleteOrder(OrderAttribute order) {
        return update("DELETE FROM `Order` WHERE ID = " + order.getId());
    }

    public boolean updateOrder(OrderAttribute order) {
        return update("UPDATE `Order` SET CustomerID= " + order.getCustomerID() + " ," +
                      "StageStr = '" + order.getStage() + "' " +
                      "WHERE `ID`=" + order.getId());
    }

    /**
     * MATERIAL RELATED METHODS
     */
    public ArrayList getMaterials() {
        return query("SELECT * FROM Material",
                     "material");
    }

	public ArrayList getMaterials(Food food) {
		return query("SELECT * FROM Material, needs " + "WHERE needs.FoodID = " + food.getId(), "food");
	}

	// "INSERT INTO `food_delivery`.`menu` (`MenuStr`) VALUES ('" + menu.getName() +
	// "')");
	/*
	 * public boolean createFood(Food food) { return
	 * update("INSERT INTO `food_delivery`.`food` (`FoodStr`) " + "VALUES ('" +
	 * food.getName() + "')"); }
	 */
	public boolean createMaterial(Material material) {
		return update("INSERT INTO `food_delivery`.`material` (`MaterialStr`, `UnitPrice`) " + "VALUES ('"
				+ material.getName() + "','" + material.getUnitPrice() + "')");
	}

	public boolean deleteMaterial(Material material) {
		return update("DELETE FROM Material " + "WHERE ID = " + material.getId());
	}

	public boolean updateMaterial(Material material) {
		return update("UPDATE Material SET " + "MaterialStr = " + material.getName() + "," + "UnitPrice = "
				+ material.getUnitPrice() + " " + "WHERE ID = " + material.getId());
	}

	/**
	 * CUSTOMER RELATED METHODS
	 */
	public ArrayList getCustomers() {
		return query("SELECT * FROM Customer", "customer");
	}

    public Customer getCustomer(Integer id) {
        ArrayList<Customer> customers = query("SELECT * FROM `Customer` " +
                                   "WHERE `Customer`.ID = " + id,
                                   "customer");
        assert(customers.size() == 1);
        return customers.get(0);
    }

    public boolean createCustomer(Customer customer) {
        return update("INSERT INTO Customer (Name,Tel,Address) " +
                      "VALUES ('" + customer.getName() + "','" + customer.getTel() + "','" + customer.getAddress() + "')");
    }

	public boolean deleteCustomer(Customer customer) {
		return update("DELETE FROM Customer " + "WHERE ID = " + customer.getId());
	}

    public boolean updateCustomer(Customer customer) {
        return update("UPDATE Customer SET " +
                      "`Name` = '" + customer.getName() + "'," +
                      "`Tel` = " + customer.getTel() + "," +
                      "Address = '" + customer.getAddress() + "' " +
                      "WHERE ID = " + customer.getId());
    }

	/**
	 * STAGE RELATED METHODS
	 */
	public ArrayList<String> getStages() {
		return query("SELECT * FROM Stage ", "stage");
	}

}
