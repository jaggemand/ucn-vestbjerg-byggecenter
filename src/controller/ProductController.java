package controller;
import model.*;
import java.util.ArrayList;

public class ProductController {
	private OrderController orderController;
	
	//no parameters
	public ProductController() {
		
	}
	
	public Product findProductByBarcode(String barcode) {
        Product outputProduct = null;
        ArrayList<Product> products = ProductContainer.getInstance().getProducts();
        boolean found = false;
        
        for (int i = 0; i < products.size() && !found; i++) {
            if (products.get(i).getBarcode().equals(barcode)) {
                outputProduct = products.get(i);
                found = true;
            }
        }
        return outputProduct;
    }
	
	public Product findProductByProductID(String productID) {
		Product outputProduct = null;
        ArrayList<Product> products = ProductContainer.getInstance().getProducts();
        boolean found = false;
        
        for (int i = 0; i < products.size() && !found; i++) {
            products.get(i);
			if (Product.getProductID().equals(productID)) {
                outputProduct = products.get(i);
                found = true;
            }
        }
        return outputProduct;
	}
}
