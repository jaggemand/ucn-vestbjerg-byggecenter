package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;

/**
 * This class is a singleton class that holds a list of products
 */
public class ProductContainer {
	private static ProductContainer instance;
	private List<Product> products;
	private Set<String> categories;

	// A constructor.
	private ProductContainer() {
		products = new ArrayList<>();
		categories = new HashSet<String>();
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
			for(String e : p.getCategory()) {
				categories.add(e);
			}
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
	
	public HashSet<String> getCategories(){
		return new HashSet<>(categories);
	}
	public boolean addCategory(String newCategory) {
		return categories.add(newCategory);
	}
	public boolean removeCategory(String categoryToRemove) {
		return categories.remove(categoryToRemove);
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
	
	public void loadFile() {
		try {
		      File myObj = new File("products.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		        importFromFile(myObj);
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public void saveFile() {
		try {
		      File myObj = new File("products.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        exportToFile(myObj);
		      } else {
		        System.out.println("File already exists.");
		        myObj.delete();
		        saveFile();
		        
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public void loadCategoryFile() {
		try {
		      File myObj = new File("categories.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else {
		        System.out.println("File already exists.");
		        importToCategoryFile();
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	public void saveCategoryFile() {
		try {
		      File myObj = new File("categories.txt");
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        exportToCategoryFile();
		      } else {
		        System.out.println("File already exists.");
		        myObj.delete();
		        saveCategoryFile();
		        
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private void importFromFile(File input) {
		int count = 0;
		try {
		      Scanner myReader = new Scanner(input);
		      myReader.nextLine(); // DISCARD top line
		      while (myReader.hasNextLine()) {
		    	//Reads next line of the file and splits it using ; as separator
		        String[] data = myReader.nextLine().split(";");
		        //Raw data goes to the constructor
		        Product newProduct = new Product(data[0], data[1], data[2], data[3].split(","), data[4], data[5], Integer.parseInt(data[6]), Integer.parseInt(data[7]));

				newProduct.setSalesPrice(Double.parseDouble(data[8]));
				newProduct.setCostPrice(Double.parseDouble(data[9]));
				newProduct.setSuggestedSalesPrice(Double.parseDouble(data[10]));
		        
		        
		        //products.add(newProduct);
				addProduct(newProduct);
		        count++;
		      }
		      System.out.println("Imported products to container: " + count);
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	private void exportToFile(File output) {
		int count = 0;
		try {
		      FileWriter myWriter = new FileWriter("products.txt");
		      myWriter.write("//NAME;BARCODE;DESCRIPTION;CATEGORY(,);STORAGELOCATION;WAREHOUSELOCATION;STORAGEAMOUNT;WAREHOUSEAMOUNT;SALESPRICE;COSTPRICE;SUGGESTEDSALESPRICE\n");
		      for(Product e : products) {
		    	  String categories = "";
		    	  for(int i = 0;i<e.getCategory().length;i++) {
		    		  if(i == 0) {
		    			  categories += e.getCategory()[i];
		    		  }
		    		  else {
		    			  categories += "," + e.getCategory()[i];
		    		  }
		    	  }
		    	  myWriter.write(
		    			  e.getName() + ";" +
		    			  e.getBarcode() + ";" +
		    			  e.getDescription() + ";" + 
		    			  categories + ";" +
		    			  e.getStorageLocation() + ";" +
		    			  e.getWarehouseLocation() + ";" +
		    			  e.getStorageAmount() + ";" +
		    			  e.getWarehouseAmount() + ";" +
		    			  e.getSalesPrice() + ";" +
		    			  e.getCostPrice() + ";" +
		    			  e.getSuggestedSalesPrice() + "\n");
		      }
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	private void importToCategoryFile() {
		try {
		      Scanner myReader = new Scanner(new File("categories.txt"));
		      myReader.nextLine(); // DISCARD top line
		      while (myReader.hasNextLine()) {
		    	categories.add(myReader.nextLine());
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	private void exportToCategoryFile() {
		try {
		      FileWriter myWriter = new FileWriter("categories.txt");
		      
		      myWriter.write("Kategorier: én per linje" + "\n");
		      
		      for(String e : categories) {
		    	  myWriter.write(e+"\n");
		      }
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}
