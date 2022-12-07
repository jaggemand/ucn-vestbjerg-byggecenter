package model;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductContainer {
	private static ProductContainer instance;
	private ArrayList<Product> products;
	
	
	private ProductContainer() { 
		products = new ArrayList<>();
	}
	
	public static ProductContainer getInstance() {
		if (instance == null) {
			instance = new ProductContainer();
		}
		return instance;
	}
	
	public boolean addProduct(Product p) {
		boolean success = false;

		if (!products.contains(p)) {
			success = true;
			products.add(p);
		}
		return success;
	}
	
	public ArrayList<Product> getProducts() {
		return new ArrayList<>(products);
	}
	
	/**
	 * @param barcode of the product the user/system wants to remove from the container
	 * @return true if product with corrosponding barcode was found, false if barcode was not found
	 */
	public boolean remove(String barcode) {
		boolean removed = false;
		Iterator<Product> it = products.iterator();
		while (it.hasNext()) {
			Product p = it.next();
			if (p.getBarcode().equals(barcode)) {
				it.remove();
				removed = true;
			}
		}
		return removed;
	}
}
