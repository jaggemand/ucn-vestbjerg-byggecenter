package model;

/**
 * The OrderLine class is a class that represents a line in an order. It
 * contains a quantity and a
 * product.
 */
public class OrderLine {
	private int quantity;
	private Product product;

	/**
	 * A constructor. It is a method that is called when an object is created.
	 * 
	 * @param quantity the amount of the specific product, i.e. 10 screws
	 * @param product  the specific instance of a product
	 */
	public OrderLine(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}

	/**
	 * This function returns the quantity of the item
	 * 
	 * @return The quantity of the item.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * This function sets the quantity of the item
	 * 
	 * @param quantity The number of items in the order.
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * This function returns the product
	 * 
	 * @return The product object.
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * This function sets the product of the current object to the product passed in
	 * as a parameter
	 * 
	 * @param p The product to be added to the cart.
	 */
	public void setProduct(Product p) {
		this.product = p;
	}
}
