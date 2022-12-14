package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a singleton class that holds a list of products
 */
public class ProductContainer {
	private static ProductContainer instance;
	private List<Product> products;

	// A constructor.
	private ProductContainer() {
		products = new ArrayList<>();
	}

	/**
	 * This function resets the singleton instance of the class and clears the
	 * products list
	 */
	public void resetSingleton() {
		instance = null;
		products.clear();
	}

	/**
	 * If the instance is null, create a new instance of ProductContainer
	 * 
	 * @return The instance of the ProductContainer class.
	 */
	public static ProductContainer getInstance() {
		if (instance == null) {
			instance = new ProductContainer();
		}
		return instance;
	}

	/**
	 * This function adds a product to the list of products if the product is not
	 * already in the list
	 * 
	 * @param p The product to be added to the list.
	 * @return A boolean value true if added
	 */
	public boolean addProduct(Product p) {
		boolean success = false;

		if (!products.contains(p)) {
			success = true;
			products.add(p);
		}
		return success;
	}

	/**
	 * Returns the Container for Products
	 * 
	 * @return A copy of the products array list.
	 */
	public ArrayList<Product> getProducts() {
		return new ArrayList<>(products);
	}

	/**
	 * This function removes a product from the list of products
	 * 
	 * @param p The product to be removed from the list.
	 * @return A boolean value, true if product was removed
	 **/
	public boolean removeProduct(Product p) {
		boolean success = false;

		if (p != null && products.contains(p)) {
			success = true;
			products.remove(p);
		}
		return success;
	}
}
