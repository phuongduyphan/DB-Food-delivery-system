public class Dish {
    private Integer id;
    private Menu menu;
    private Food food;
    private Float price;

    public Dish(Integer id, Menu menu, Food food, Float price) {
        this.id = id;
        this.menu = menu;
        this.food = food;
        this.price = price;
    }

    public static Dish create(Dish dish){
        return Database.getInstance().createDish(dish);
    }

    public static boolean delete(Dish dish){
        return Database.getInstance().deleteDish(dish);
    }

    public boolean save(){
        return Database.getInstance().updateDish(this);
    }

    public Integer getId() {
        return id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
