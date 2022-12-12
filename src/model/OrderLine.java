package model;

public class OrderLine {
	private int quantity;
	private Product product;

	/**
	 * @param quantity the amount of the specific product, i.e. 10 screws
	 * @param product  the specific instance of a product
	 */
	public OrderLine(int quantity, Product product) {
		this.quantity = quantity;
		this.product = product;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product p) {
		this.product = p;
	}
}
