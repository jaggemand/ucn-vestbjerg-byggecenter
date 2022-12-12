package controller;
import java.util.ArrayList;
import java.util.Iterator;
import model.*;
public class OrderController {
	private Order currentOrder;
	private ProductController productController;
	/**
	 * Constructor doesn't need parameters
	 */
	public OrderController() {
		productController = new ProductController();
	}
	
	/*
	*	This method adds current order from the attributes to the OrderContainer-class
	*	@return		returns true if order is added to container
	*/
	public boolean addOrder() {
		boolean success = false;
		if(currentOrder != null) {
			success = OrderContainer.getInstance().addOrder(getCurrentOrder());
		}
		return success;
		
		//Method could be shorter.
		//return OrderContainer.getInstance().addOrder(getCurrentOrder());
	}
	
	/*
	*	This method creates a new order in the currentOrder attribute
	*	
	*/
	public void createOrder(){
		currentOrder = new Order();
	}
	/*
	*	This method return the attribute named: currentOrder
	*	@return		Order-object, can be null
	*/
	public Order getCurrentOrder(){
		return currentOrder;
	}
	/*
	 *	This method takes two input parameters search and quantity, searches for a product
	 *	if product exists, the method will add the product to current order with the input amount
	 *  
	 *	@param search 	This String should contain a barcode or product ID
	 *	@param quantity	Amount of elements of givin product
	 *	@return			returns true if the product is added to the order
	 */
	public boolean addProduct(String search, int quantity) {
		boolean success = false;
		Product toAdd = productController.findProduct(search);
		if (toAdd != null) {
			success = true;
			currentOrder.addProduct(toAdd, quantity);
		}
		
		return success;
	}
	
	/*
	 *	This method takes one parameter and uses that to find an object
	 *	if product exists, the method will add the product to current order with the input amount
	 *  
	 *	@param search 	This String should contain a barcode or product ID
	 *	@param quantity	Amount of elements of givin product
	 *	@return			returns true if the product is added to the order
	 */
	public boolean removeProduct(String search){
		boolean success = false;
		success = currentOrder.removeProduct(search);
		return success;
	}
	
	public Order findOrder(String orderNumber){
		Order outputOrder = null;
        ArrayList<Order> orders = OrderContainer.getInstance().getOrders();
        boolean found = false;
        Iterator <Order> it = orders.iterator();
		while(!found && it.hasNext()){
			outputOrder = it.next();
			if(outputOrder.getOrderNumber().equals(orderNumber)){
				found = true;
			}else{
				outputOrder = null;
			}
		}
		
		return outputOrder;
	}
	
	public boolean removeOrder(String orderNumber) {
		boolean success = false;
		Order orderToRemove = findOrder(orderNumber);
		success = OrderContainer.getInstance().removeOrder(orderToRemove);
		return success;
	}
}
