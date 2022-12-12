package model;

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
	 * @return the productNumber
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * @param barcode the barcode to set
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the category
	 */
	public String[] getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String[] category) {
		this.category = category;
	}

	/**
	 * @return the storageLocation
	 */
	public String getStorageLocation() {
		return storageLocation;
	}

	/**
	 * @param storageLocation the storageLocation to set
	 */
	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	/**
	 * @return the warehouseLocation
	 */
	public String getWarehouseLocation() {
		return warehouseLocation;
	}

	/**
	 * @param warehouseLocation the warehouseLocation to set
	 */
	public void setWarehouseLocation(String warehouseLocation) {
		this.warehouseLocation = warehouseLocation;
	}

	/**
	 * @return the storageAmount
	 */
	public int getStorageAmount() {
		return storageAmount;
	}

	/**
	 * @param storageAmount the storageAmount to set
	 */
	public void setStorageAmount(int storageAmount) {
		this.storageAmount = storageAmount;
	}

	/**
	 * @return the warehouseAmount
	 */
	public int getWarehouseAmount() {
		return warehouseAmount;
	}

	/**
	 * @param warehouseAmount the warehouseAmount to set
	 */
	public void setWarehouseAmount(int warehouseAmount) {
		this.warehouseAmount = warehouseAmount;
	}

	/**
	 * @return the salesPrice
	 */
	public double getSalesPrice() {
		return salesPrice;
	}

	/**
	 * @param salesPrice the salesPrice to set
	 */
	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}

	/**
	 * @return the costPrice
	 */
	public double getCostPrice() {
		return costPrice;
	}

	/**
	 * @param costPrice the costPrice to set
	 */
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	/**
	 * @return the suggestedSalesPrice
	 */
	public double getSuggestedSalesPrice() {
		return suggestedSalesPrice;
	}

	/**
	 * @param suggestedSalesPrice the suggestedSalesPrice to set
	 */
	public void setSuggestedSalesPrice(double suggestedSalesPrice) {
		this.suggestedSalesPrice = suggestedSalesPrice;
	}

	public boolean containsBarcodeOrProuductID(String search) {
		return productID.equals(search) || barcode.equals(search);
	}
}
