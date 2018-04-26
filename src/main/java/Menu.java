import java.util.ArrayList;

public class Menu {
    private Integer id;
    private String name;
    private Boolean availability;

    public Menu(Integer id, String name, Boolean availability) {
        this.id = id;
        this.name = name;
        this.availability = availability;
    }

    public static Menu create(Menu menu){
        return Database.getInstance().createMenu(menu);
    }

    public static ArrayList getAll() {
        return Database.getInstance().getMenus();
    }

    public static boolean delete(Menu menu){
        return Database.getInstance().deleteMenu(menu);
    }

    public boolean save(){
        return Database.getInstance().updateMenu(this);
    }

    public boolean addDish(Dish dish){
        return Database.getInstance().addDishToMenu(this, dish);
    }

    public boolean removeDish(Dish dish) {
        return Database.getInstance().removeDishFromMenu(this, dish);
    }

    public ArrayList getDishes() {
        return Database.getInstance().getDishes(this);
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

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }
}
