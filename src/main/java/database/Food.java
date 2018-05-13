package database;

import java.util.ArrayList;

public class Food {
    private Integer id;
    private String name;
    public Food(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static boolean create(Food food){
        return Database.getInstance().createFood(food);
    }

    public static ArrayList getAll() {
        return Database.getInstance().getFoods();
    }

    public static boolean delete(Food food){

        return Database.getInstance().deleteFood(food);
    }

    public ArrayList getMaterials() {
        return Database.getInstance().getMaterials(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean save(){
        return Database.getInstance().updateFood(this);
    }

    public Integer getId() {
        return id;
    }
}
