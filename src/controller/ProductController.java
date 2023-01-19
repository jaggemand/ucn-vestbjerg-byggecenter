 package controller;

import model.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * It's a controller class for the Product class
 */
public class ProductController {

	// A constructor.
	public ProductController() {
	}

	/**
	 * This function creates a new product and adds it to the database
	 * 
	 * @param name              The name of the product
	 * @param barcode           String of the product
	 * @param description       String of the product
	 * @param category          String[] of the product
	 * @param storageLocation   The location of the product in the storage.
	 * @param warehouseLocation The location of the product in the warehouse.
	 * @param storageAmount     The quantity of the product in the storage
	 * @param warehouseAmount   The quantity of the product in the warehouse
	 * @return The method returns the new product.
	 */
	public Product createProduct(String name, String barcode, String description, String[] category,
			String storageLocation, String warehouseLocation, int storageAmount, int warehouseAmount) {

		Product newProduct = new Product(name, barcode, description, category, storageLocation, warehouseLocation,
				storageAmount, warehouseAmount);
		addProduct(newProduct);
		return newProduct;
	}

	/**
	 * This function removes a product from the product container
	 * 
	 * @param search The barcode/ProductID of the product to be removed.
	 * @return A boolean value. True if successful
	 */
	public boolean removeProduct(String search) {
		boolean success = false;
		Product productToRemove = findProduct(search);
		success = ProductContainer.getInstance().removeProduct(productToRemove);
		return success;
	}

	/**
	 * This function adds a product to the product container
	 * 
	 * @param product The product to be added to the container.
	 * @return A boolean value true if succesful
	 */
	public boolean addProduct(Product product) {
		boolean success = false;
		success = ProductContainer.getInstance().addProduct(product);
		return success;
	}

	/**
	 * It searches through a list of products and returns the first product that
	 * matches the search
	 * criteria
	 * 
	 * @param search The barcode or product ID of the product to be found.
	 * @return The product that is found.
	 */
	public Product findProduct(String search) {
		Product outputProduct = null;
		ArrayList<Product> products = ProductContainer.getInstance().getProducts();
		boolean found = false;
		Iterator<Product> it = products.iterator();

		while (!found && it.hasNext()) {
			outputProduct = it.next();
			if (outputProduct.containsBarcodeOrProuductID(search)) {
				found = true;
			} else {
				outputProduct = null;
			}
		}
		return outputProduct;
	}

	/**
	 * This function returns an ArrayList of all the products in the
	 * ProductContainer.
	 * 
	 * @return An ArrayList of all Product objects in container.
	 */
	public ArrayList<Product> getAllProducts() {
		return ProductContainer.getInstance().getProducts();
	}

	/**
	 * If the product is not null, change its name to the new name.
	 * 
	 * @param newName The new name of the product.
	 * @param product The product to change the name of.
	 */
	public void changeProductName(String newName, Product product) {

		if (product != null) {
			product.setName(newName);
		}
	}

	/**
	 * If the product is not null, change its description.
	 * 
	 * @param newDescription The new description of the product.
	 * @param product        The product to be updated.
	 */
	public void changeProductDescription(String newDescription, Product product) {

		if (product != null) {
			product.setDescription(newDescription);
		}
	}

	/**
	 * This function changes the barcode of a product if the new barcode is not
	 * already in use
	 * And product is not null
	 *
	 * @param newBarcode The new barcode that the user wants to change the product's
	 *                   barcode to.
	 * @param product    The product to change the barcode of.
	 * @return A boolean value. true if successful
	 */
	public boolean changeProductBarcode(String newBarcode, Product product) {
		boolean success = false;
		Product check = findProduct(newBarcode);
		if (product != null && check == null) {
			product.setBarcode(newBarcode);
			success = true;
		}
		return success;
	}

	/**
	 * This function changes the category of a product
	 * Each Category is divided by a comma, example [Example,Example,Example]
	 * And the product is not null
	 *
	 * @param newCategory The new category that the product will be assigned to.
	 * @param product     The product object that you want to change the category
	 *                    for.
	 */
	public void changeProductCategory(String[] newCategory, Product product) {

		if (product != null) {
			product.setCategory(newCategory);
			for(String e : newCategory) {
				ProductContainer.getInstance().addCategory(e);
			}
		}
	}
	
	public HashSet<String> getCategoies() {
		return ProductContainer.getInstance().getCategories();
	}
	
	public boolean addCategory(String newCategory) {
		return ProductContainer.getInstance().addCategory(newCategory);
	}
	
	public boolean removeCategory(String oldCategory) {
		return ProductContainer.getInstance().removeCategory(oldCategory);
	}

	/**
	 * This function changes the storage location of a product
	 * And the product is not null
	 *
	 * @param newStorageLocation The new storage location for the product.
	 * @param product            The product to be updated
	 */
	public void changeProductStorageLocation(String newStorageLocation, Product product) {

		if (product != null) {
			product.setStorageLocation(newStorageLocation);
		}
	}

	/**
	 * This function changes the warehouse location of a product
	 * And the product is not null
	 *
	 * @param newWarehouseLocation The new warehouse location for the product.
	 * @param product              The product to be updated
	 */
	public void changeProductWarehouseLocation(String newWarehouseLocation, Product product) {

		if (product != null) {
			product.setWarehouseLocation(newWarehouseLocation);
		}
	}

	/**
	 * This function changes the storage amount of a product
	 * And the product is not null
	 *
	 * @param newStorageAmount The new storage amount of the product.
	 * @param product          The product that you want to change the storage
	 *                         amount of.
	 */
	public void changeProductStorageAmount(int newStorageAmount, Product product) {

		if (product != null) {
			product.setStorageAmount(newStorageAmount);
		}
	}

	/**
	 * It changes the warehouse amount of a product
	 * And the product is not null
	 *
	 * @param newWarehouseAmount the new quantity of the product in the warehouse
	 * @param product            The quantity that you want to change the warehouse
	 *                           amount of.
	 */
	public void changeProductWarehouseAmount(int newWarehouseAmount, Product product) {

		if (product != null) {
			product.setWarehouseAmount(newWarehouseAmount);
		}
	}

	/**
	 * This function changes the cost price of a product
	 * And the product is not null
	 *
	 * @param costPrice The new cost price of the product.
	 * @param product   The product object that you want to change the cost price
	 *                  of.
	 */
	public void changeCostPrice(double costPrice, Product product) {

		if (product != null) {
			product.setCostPrice(costPrice);
		}
	}

	/**
	 * If the product is not null, set the sales price
	 * And the product is not null
	 *
	 * @param salesPrice The new sales price of the product.
	 * @param product    The product object that you want to change the sales price
	 *                   of.
	 */
	public void changeSalesPrice(double salesPrice, Product product) {

		if (product != null) {
			product.setSalesPrice(salesPrice);
		}
	}

	/**
	 * This function changes the suggested price of a product
	 * 
	 * @param suggestedPrice The new suggested price for the product.
	 * @param product        The product that you want to change the suggested price
	 *                       for.
	 */
	public void changeSuggestedPrice(double suggestedPrice, Product product) {

		if (product != null) {
			product.setSuggestedSalesPrice(suggestedPrice);
		}
	}
	
	public void loadFile() {
		ProductContainer.getInstance().loadFile();
		ProductContainer.getInstance().loadCategoryFile();
	}
	
	public void saveFile() {
		ProductContainer.getInstance().saveFile();
		ProductContainer.getInstance().saveCategoryFile();
	}
}