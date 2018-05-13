package database;

public class DishQuantity
{
	String dishName;
	Integer quantity;
	Float price;
	Dish dish;
	public DishQuantity(Dish dish,Integer quantity)
	{
		this.dish=dish;
		this.quantity=quantity;
		this.price=dish.getPrice();
	}

	public String getDish() {
		return dish.getFood().getName();
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	public Dish getRealDish() {
		return dish;
	}
}
