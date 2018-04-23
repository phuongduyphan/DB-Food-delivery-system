import java.sql.*;
import java.util.ArrayList;

/**
 * TODO:
 * get DISH by MENU_ID
 * get FOOD by DISH_ID
 *
 * get MATERIAL by FOOD_ID
 *
 * get ORDER by DISH_ID
 * get DISH by ORDER_ID
 *
 * get CUSTOMER by ORDER_ID
 * get ORDER by CUSTOMER_ID
 */

public class Database {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String server = "localhost"; // Server name
    private static final int port = 3306;    // TCP Port
    private static final String user = "kwang";
    private static final String pass = "kwang";
    private static final String database = "ApressFinancial";
    private static final String DATABASE_URL = "jdbc:mysql://" + server
                                               + ":" + port + "/" + database;

    private Database instance;
    private Connection connection;
    private Statement statement;

    public Database getInstance() {
        if (instance == null) instance = new Database();
        return instance;
    }

    private Database() {
        try {
            // load database driver class
            Class.forName(JDBC_DRIVER);
            System.out.println("# - Driver loaded!");

            // establish connection to database
            connection = DriverManager.getConnection(DATABASE_URL, user, pass);
            System.out.println("# - Connection obtained!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remember to close the returned stmt and rs
     */
    private ArrayList<ArrayList> query(String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList rows = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            rows = resultSetToArrayList(rs
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
        return rs;
    }

    private ArrayList<ArrayList> resultSetToArrayList(ResultSet rs, String table) throws SQLException {
        int tableId;
        table = table.toLowerCase();
        if (table == "menu") tableId = 0;
        else if (table == "dish") tableId = 1;
        else if (table == "food") tableId = 2;
        else if (table == "needs") tableId = 3;
        else if (table == "contains") tableId = 4;
        else if (table == "order") tableId = 5;
        else if (table == "material") tableId = 6;
        else if (table == "stages") tableId = 7;
        else if (table == "customer") tableId = 8;
        else throw new Error("table not found");
        ArrayList<ArrayList> rows = new ArrayList<ArrayList>();
        switch (tableId) {
        case 0:
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<String>());
            break;
        case 1:
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<Integer>());
            break;
        case 2:
            rows.add(new ArrayList<Integer>());
            break;
        case 3:
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<Integer>());
            break;
        case 4:
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<Integer>());
            break;
        case 5:
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<Integer>());
            break;
        case 6:
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<String>());
            rows.add(new ArrayList<Integer>());
            break;
        case 7:
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<String>());
            break;
        case 8:
            rows.add(new ArrayList<Integer>());
            rows.add(new ArrayList<String>());
            rows.add(new ArrayList<String>());
            rows.add(new ArrayList<String>());
            break;
        default:
            throw new Error("table not found");
        }
        rs.beforeFirst();
        while (rs.next()) {
            switch (tableId) {
            case 0:
                rows.get(0).add(rs.getInt(1));
                rows.get(1).add(rs.getString(2));
                break;
            case 1:
                rows.get(0).add(rs.getInt(1));
                rows.get(1).add(rs.getInt(2));
                rows.get(2).add(rs.getInt(3));
                rows.get(3).add(rs.getInt(4));
                break;
            case 2:
                rows.get(0).add(rs.getInt(1));
                break;
            case 3:
                rows.get(0).add(rs.getInt(1));
                rows.get(1).add(rs.getInt(2));
                rows.get(2).add(rs.getInt(3));
                break;
            case 4:
                rows.get(0).add(rs.getInt(1));
                rows.get(1).add(rs.getInt(2));
                break;
            case 5:
                rows.get(0).add(rs.getInt(1));
                rows.get(1).add(rs.getInt(2));
                rows.get(2).add(rs.getInt(3));
                break;
            case 6:
                rows.get(0).add(rs.getInt(1));
                rows.get(1).add(rs.getString(2));
                rows.get(2).add(rs.getInt(3));
                break;
            case 7:
                rows.get(0).add(rs.getInt(1));
                rows.get(1).add(rs.getString(2));
                break;
            case 8:
                rows.get(0).add(rs.getInt(1));
                rows.get(1).add(rs.getString(2));
                rows.get(2).add(rs.getString(3));
                rows.get(3).add(rs.getString(4));
                break;
            default:
                throw new Error("table not found");
            }
        }
        return rows;
    }
}
