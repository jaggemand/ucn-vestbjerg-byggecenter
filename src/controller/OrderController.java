package controller;
import model.*;
public class OrderController {
	private ProductController productController;
	
	/**
	 * Constructor doesn't need parameters
	 */
	public OrderController() {
		productController = null;
	}
	
	//TODO finish method
	public void addSale(Order o) {
		
	}
	
	//TODO finish method
	public boolean addProduct(Product P, int quantity) {
		return true;
	}
	
	//TODO finish method
	public Product addProduct(String barcode, int quantity) {
		Product returnProduct = null;
		productController = new ProductController();
		returnProduct = productController.findProductByBarcode(barcode);
		
		return returnProduct;
	}

}
