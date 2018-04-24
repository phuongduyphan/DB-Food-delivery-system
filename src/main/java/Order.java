import java.util.ArrayList;

public class Order {
    private Integer id;
    private Customer customer;

    public Order(Integer id, Customer customer, ArrayList<Dish> dishes) {
        this.id = id;
        this.customer = customer;
    }

    public static Order create(Order order){
        return Database.getInstance().createOrder(order);
    }

    public static ArrayList getAll() {
        return Database.getInstance().getOrders();
    }

    public static boolean delete(Order order){
        return Database.getInstance().deleteOrder(order);
    }

    public boolean save(){
        return Database.getInstance().updateOrder(this);
    }

    public Float getTotal() {
        return new Float(0.0);
    }

    public boolean addDish(Dish dish){
        return Database.getInstance().addDishToOrder(this, dish);
    }

    public boolean removeDish(Dish dish) {
        return Database.getInstance().removeDishFromOrder(this, dish);
    }

    public ArrayList getDishes() {
        return Database.getInstance().getDishes(this);
    }

    public Integer getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;

    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
