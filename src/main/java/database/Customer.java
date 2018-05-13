package database;

import java.util.ArrayList;

public class Customer  {
    private Integer id;
    private String name;
    private Integer tel;
    private String address;

    public Customer(Integer id, String name, Integer tel, String address) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.address = address;
    }

    public static boolean create(Customer customer) {
        return Database.getInstance().createCustomer(customer);
    }

    public static ArrayList getAll() {
        return Database.getInstance().getCustomers();
    }

    public static boolean delete(Customer customer){
        return Database.getInstance().deleteCustomer(customer);
    }

    public boolean save(){
        return Database.getInstance().updateCustomer(this);
    }

    public ArrayList getOrders() {
        return Database.getInstance().getOrders(this);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
