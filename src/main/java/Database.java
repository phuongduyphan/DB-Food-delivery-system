import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String server = "localhost"; // Server name
    private static final int port = 3306;    // TCP Port
    private static final String user = "kwang";
    private static final String pass = "kwang";
    private static final String database = "ApressFinancial";
    private static final String DATABASE_URL = "jdbc:mysql://" + server
                                               + ":" + port + "/" + database;

    private static Database instance;
    private static Connection connection;
    private static Statement statement;

    private Database() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DATABASE_URL, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    /**
     * Remember to close the returned stmt and rs
     */
    private ArrayList<ArrayList> query(String sql, String resultType) {
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
                try { rs.close();} catch (SQLException sqlEx) { } // ignore
                rs = null;
            }
            if (stmt != null) {
                try { stmt.close(); } catch (SQLException sqlEx) { } // ignore
                stmt = null;
            }
        }
        return rows;
    }

    private ArrayList<ArrayList> resultSetToArrayList(ResultSet rs, String resultType) throws SQLException {
        resultType = resultType.toLowerCase();
        if (resultType.equals("menu")) return menuToArrayList(rs);
        else if (resultType.equals("dish")) return dishToArrayList(rs);
        else if (resultType.equals("food")) return foodToArrayList(rs);
        else if (resultType.equals("order")) return orderToArrayList(rs);
        else if (resultType.equals("material")) return materialToArrayList(rs);
        else if (resultType.equals("customer")) return customerToArrayList(rs);
        else throw new Error("result not found");
}

    /**
     * MENU RELATED METHODS
     */
    public ArrayList getMenus() {

    }

    public Menu createMenu(Menu menu) {

    }

    public boolean deleteMenu(Menu menu) {

    }

    public boolean updateMenu(Menu menu) {

    }

    public boolean addDishToMenu(Menu menu, Dish dish) {

    }

    public boolean removeDishFromMenu(Menu menu, Dish dish) {

    }

    /**
     * DISH RELATED METHODS
     */
    public ArrayList getDishes(Menu menu) {
    }

    public ArrayList getDishes(Order order) {

    }

    public Dish createDish(Dish dish) {

    }

    public boolean deleteDish(Dish dish) {

    }

    public boolean updateDish(Dish dish) {

    }


    /**
     * FOOD RELATED METHODS
     */
    public ArrayList getFoods() {
    }

    public ArrayList getFoods(Material material) {
    }

    public Food createFood(Food food) {

    }

    public boolean deleteFood(Food food) {

    }

    public boolean updateFood(Food food) {

    }

    /**
     * ORDER RELATED METHODS
     */
    public ArrayList getOrders(Customer customer) {
    }

    public ArrayList getOrders() {
    }

    public Order createOrder(Order order) {

    }

    public boolean addDishToOrder(Order order, Dish dish) {

    }

    public boolean removeDishFromOrder(Order order, Dish dish) {

    }

    public boolean deleteOrder(Order order) {

    }

    public boolean updateOrder(Order order) {

    }

    /**
     * MATERIAL RELATED METHODS
     */
    public ArrayList getMaterials() {

    }

    public ArrayList getMaterials(Food food) {
    }

    public Material createMaterial(Material material) {

    }

    public boolean deleteMaterial(Material material) {

    }

    public boolean updateMaterial(Material material) {

    }

    /**
     * CUSTOMER RELATED METHODS
     */
    public ArrayList getCustomers() {

    }

    public Customer createCustomer(Customer customer) {

    }

    public boolean deleteCustomer(Customer customer) {

    }

    public boolean updateCustomer(Customer customer) {

    }

}
