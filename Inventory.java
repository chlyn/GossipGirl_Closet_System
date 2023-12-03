import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Inventory {
    private HashMap<String, Product> productMap = new HashMap<>();
    private ArrayList<Product> productList = new ArrayList<>();
    private ShoppingCart shoppingCart = new ShoppingCart();
    
    //START OF METHODS FOR ADMIN

    //INSERTING NEW PRODUCT FOR ADMIN
    public void insertProduct(ProductCategory category, double price, int productNumber, int listingDatee) {
        String title = category.toString() + " #" + productNumber;
        int listingDate = (int) System.currentTimeMillis(); // Convert to int
        Product product = new Product(title, price, listingDate, category, productNumber);
        productMap.put(title, product);
        productList.add(product);

        //THESE ARE DEBUG LINES TO MAKE SURE THE PRODUCTS ARE GETTING ADDED CORRECTLY
        System.out.println("\nProduct added: " + title);
        System.out.println("ProductList size: " + productList.size() + "\n");
    }

    //VIEWING ALL PRODUCTS FOR ADMIN
    public void viewAllProductsAdmin() {
        System.out.println("All Products:");
        System.out.println("------------------------");

        for (Product product : productList) {
            System.out.println("Title: " + product.title);
            System.out.println("Category: " + product.category);
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Product Number: " + product.getProductNumber());
            System.out.println("Listing Date: " + product.listingDate);
            System.out.println("------------------------");
        }
    }
    
    //DELETING PRODUCT FOR ADMIN
    //THIS IS THE METHOD WITH THE SCANNER ISSUE; I HAVE NO IDEA HOW TO FIX IT 
    //AYUDA HERE AYUDA HERE
    public void deleteAdminProduct(Scanner scnr) {
        scnr.nextLine(); //get extra line

        System.out.println("Would you like to see a list of all your products? (Yes/No): ");
        System.out.print("-> Choice: ");
        String choice = scnr.next();

        if (choice.trim().equalsIgnoreCase("Yes")) {
            viewAllProductsAdmin();
        }
        scnr.nextLine();  //get extra line

        System.out.println("\nEnter the title of the product you want to delete:");
        String titleToDelete = scnr.nextLine();
      
        // Check if the product exists
        if (productMap.containsKey(titleToDelete)) {

            // Remove from productMap
            productMap.remove(titleToDelete);

            // Remove from productList
            productList.removeIf(product -> product.title.equals(titleToDelete));
                        
            System.out.println("\nProduct deleted successfully!");
            
        } else {
            System.out.println("\nProduct not found. Unable to delete.");
        }
    }

    //START OF METHODS FOR USERS
    public void viewAllProductsUser() {
        System.out.println("All Products:");
        System.out.println("------------------------");

        for (Product product : productList) {
            System.out.println("Title: " + product.title);
            System.out.println("Price: $" + product.getPrice());  
            System.out.println("------------------------");
        }
    }

}
