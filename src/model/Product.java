package model;

import java.text.DecimalFormat;

/**
 * It's a class that represents a product.
 */
public class Product {
	private String productID;
	private static int tempProductNumber;
	private String name;
	private String barcode;
	private String description;
	private String[] category;
	private String storageLocation;
	private String warehouseLocation;
	private int storageAmount;
	private int warehouseAmount;
	private double salesPrice;
	private double costPrice;
	private double suggestedSalesPrice;

	/**
	 * A constructor. Creates a new product with the given parameters.
	 * 
	 * @param name
	 * @param barcode
	 * @param description
	 * @param category
	 * @param storageLocation
	 * @param warehouseLocation
	 * @param storageAmount
	 * @param warehouseAmount
	 */
	public Product(String name, String barcode, String description, String[] category, String storageLocation,
			String warehouseLocation, int storageAmount, int warehouseAmount) {

		this.name = name;
		this.barcode = barcode;
		this.description = description;
		this.category = category;
		this.storageLocation = storageLocation;
		this.warehouseLocation = warehouseLocation;
		this.storageAmount = storageAmount;
		this.warehouseAmount = warehouseAmount;
		this.salesPrice = 0;
		this.costPrice = 0;
		this.suggestedSalesPrice = 0;

		productID = "1000-" + tempProductNumber;
		if (barcode.equals("")) {
			this.barcode = ("bc-2000-" + tempProductNumber);
		}
		tempProductNumber++;
	}

	/**
	 * This function returns the productID of the product
	 * 
	 * @return The productID
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * This function returns the name of the product
	 * 
	 * @return The name of the product.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This function sets the name of the object to the name passed in as a
	 * parameter
	 * 
	 * @param name The name of the parameter.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This function returns the barcode of the product
	 * 
	 * @return The barcode of the product.
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * This function sets the barcode of the product
	 * 
	 * @param barcode The barcode of the product.
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * This function returns the description of the product
	 * 
	 * @return The description of the product.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This function sets the description of the product
	 * 
	 * @param description The description of the product.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This function returns the category of the product
	 * 
	 * @return The category array.
	 */
	public String[] getCategory() {
		return category;
	}

	/**
	 * This function sets the category of the product
	 * 
	 * @param category The category of the product.
	 */
	public void setCategory(String[] category) {
		this.category = category;
	}

	/**
	 * This function returns the storage location of the product
	 * 
	 * @return The storage location of the product.
	 */
	public String getStorageLocation() {
		return storageLocation;
	}

	/**
	 * This function sets the storage location of the object
	 * 
	 * @param storageLocation The location of product.
	 */
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	/**
	 * This function returns the warehouse location of the product
	 * 
	 * @return The warehouseLocation variable is being returned.
	 */
	public String getWarehouseLocation() {
		return warehouseLocation;
	}

	/**
	 * This function sets the warehouse location of the product
	 * 
	 * @param warehouseLocation The location of the product.
	 */
	public void setWarehouseLocation(String warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}

	/**
	 * This function returns the storage amount of the product
	 * 
	 * @return The storageAmount variable is being returned.
	 */
	public int getStorageAmount() {
		return storageAmount;
	}

	/**
	 * This function sets the storage amount of the object
	 * 
	 * @param storageAmount The quantity of the product in the storage.
	 */
	public void setStorageAmount(int storageAmount) {
		this.storageAmount = storageAmount;
	}

	/**
	 * This function returns the quantity of the product in the warehouse
	 * 
	 * @return The quantity of the product
	 */
	public int getWarehouseAmount() {
		return warehouseAmount;
	}

	/**
	 * This function sets the quantity of the product in the warehouse
	 * 
	 * @param warehouseAmount The quantity of the product in the warehouse
	 */
	public void setWarehouseAmount(int warehouseAmount) {
		this.warehouseAmount = warehouseAmount;
	}

	/**
	 * This function returns the sales price of the item
	 * 
	 * @return The sales price of the item.
	 */
	public double getSalesPrice() {
		return salesPrice;
	}

	/**
	 * This function sets the sales price of the item
	 * 
	 * @param salesPrice The price of the item
	 */
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * This function returns the cost price of the item
	 * 
	 * @return The cost price of the item.
	 */
	public double getCostPrice() {
		return costPrice;
	}

	/**
	 * The function takes the costPrice variable and formats it to a string with two
	 * decimal places and
	 * adds DKK
	 * 
	 * @return The costPrice is being returned as a string with the format "0.00
	 *         DKK"
	 */
	public String getCostPriceFormatted() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(costPrice) + " DKK";
	}

	/**
	 * The function takes the salesPrice variable and formats it to a string with
	 * two decimal places and
	 * adds DKK
	 * 
	 * @return The sales price is being returned as a string with the format "0.00
	 *         DKK"
	 */
	public String getSalesPriceFormatted() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(salesPrice) + " DKK";
	}

	/**
	 * The function takes the suggestedSalesPrice variable, formats it to two
	 * decimal places, adds the
	 * Adds D
	 * 
	 * @return The method returns a string with the suggested sales price formatted.
	 */
	public String getSuggestedSalesPriceFormatted() {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(suggestedSalesPrice) + " DKK";
	}

	/**
	 * This function sets the cost price of the product
	 * 
	 * @param costPrice The cost price of the item.
	 */
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * This function returns the suggested sales price of the product
	 * 
	 * @return The suggested sales price.
	 */
	public double getSuggestedSalesPrice() {
		return suggestedSalesPrice;
	}

	/**
	 * This function sets the suggested sales price of the product
	 * 
	 * @param suggestedSalesPrice The suggested sales price of the item.
	 */
	public void setSuggestedSalesPrice(double suggestedSalesPrice) {
		this.suggestedSalesPrice = suggestedSalesPrice;
	}

	/**
	 * If the productID or barcode is equal to the search, return true
	 * 
	 * @param search The search term
	 * @return The method returns a boolean value.
	 */
	public boolean containsBarcodeOrProuductID(String search) {
		return productID.equals(search) || barcode.equals(search);
	}
}
