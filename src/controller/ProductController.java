package controller;

import model.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductController {

	// no parameters
	public ProductController() {
	}

	/**
	 * @param name              of the product
	 * @param barcode           of the product
	 * @param description       of the product
	 * @param category          of the product
	 * @param storageLocation   of the product
	 * @param warehouseLocation of the product
	 * @param storageAmount     of the product
	 * @param warehouseAmount   of the product
	 * @return the product
	 */
	public Product createProduct(String name, String barcode, String description, String[] category,
			String storageLocation, String warehouseLocation, int storageAmount, int warehouseAmount) {

		Product newProduct = new Product(name, barcode, description, category, storageLocation, warehouseLocation,
				storageAmount, warehouseAmount);
		addProduct(newProduct);
		return newProduct;
	}

	/**
	 * @param search finding product to be removed
	 */
	public boolean removeProduct(String search) {
		boolean success = false;
		Product productToRemove = findProduct(search);
		success = ProductContainer.getInstance().removeProduct(productToRemove);
		return success;
	}

	/**
	 * @param product to be added
	 */
	public boolean addProduct(Product product) {
		boolean success = false;
		success = ProductContainer.getInstance().addProduct(product);
		return success;
	}

	/**
	 * @param search barcode or product ID
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
	 * @param newName the new name of the product
	 * @param product the specific instance of a product
	 */
	public void changeProductName(String newName, Product product) {

		if (product != null) {
			product.setName(newName);
		}
	}

	/**
	 * @param newDescription the new description for a product
	 * @param product        the specific instance of a product
	 */
	public void changeProductDescription(String newDescription, Product product) {

		if (product != null) {
			product.setDescription(newDescription);
		}
	}

	/**
	 * @param newBarcode the new barcode for a product
	 * @param product    the specific instance of a product
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
	 * @param newCategory the new category for a product
	 * @param product     the specific instance of a product
	 */
	public void changeProductCategory(String[] newCategory, Product product) {

		if (product != null) {
			product.setCategory(newCategory);
		}
	}

	/**
	 * @param newStorageLocation the new storage location to be stored
	 * @param product            the specific instance of a product
	 */
	public void changeProductStorageLocation(String newStorageLocation, Product product) {

		if (product != null) {
			product.setStorageLocation(newStorageLocation);
		}
	}

	/**
	 * @param newWarehouseLocation the new warehouse location to be stored
	 * @param product              the specific instance of a product
	 */
	public void changeProductWarehouseLocation(String newWarehouseLocation, Product product) {

		if (product != null) {
			product.setWarehouseLocation(newWarehouseLocation);
		}
	}

	/**
	 * @param newStorageAmount the amount to be changed in Storage
	 * @param product          the specific instance of a product
	 */
	public void changeProductStorageAmount(int newStorageAmount, Product product) {

		if (product != null) {
			product.setStorageAmount(newStorageAmount);
		}
	}

	/**
	 * @param newWarehouseAmount the amount to be changed in the Warehouse
	 * @param product            the specific instance of a product
	 */
	public void changeProductWarehouseAmount(int newWarehouseAmount, Product product) {

		if (product != null) {
			product.setWarehouseAmount(newWarehouseAmount);
		}
	}

	public void changeCostPrice(double costPrice, Product product) {

		if (product != null) {
			product.setCostPrice(costPrice);
		}
	}

	public void changeSalesPrice(double salesPrice, Product product) {

		if (product != null) {
			product.setSalesPrice(salesPrice);
		}
	}

	public void changeSuggestedPrice(double suggestedPrice, Product product) {

		if (product != null) {
			product.setSuggestedSalesPrice(suggestedPrice);
		}
	}
}