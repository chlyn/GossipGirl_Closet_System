package ShoppingSystem;

import java.util.*;
import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.ProductCategory;

public class Inventory 
{

    private HashMap<String, Product> productMap = new HashMap<>();
    private ArrayList<Product> productList = new ArrayList<>();

    //METHOD FOR RANDOM PRODUCTS AT START OF PROGRAM
    public void autoGenerate() {
        // Array to store product categories
        ProductCategory[] categories = ProductCategory.values();

        Random random = new Random();

        for (int i = 0; i < 4; i++) {
            
            //for choosing category
            ProductCategory category = categories[random.nextInt(categories.length)];

            // Generate a random product number
            int productNumber = random.nextInt(1000) + 1; // Adjust range as needed

            // Generate a random price
            double price = 10 + (100 - 10) * random.nextDouble();

            int listingDate = (int) System.currentTimeMillis();

            //Adding product to product class
            String title = category.toString() + " #" + productNumber;
            Product product = new Product(title, price, listingDate, category, productNumber);

            // Add the product product map and list
            productMap.put(title, product);
            productList.add(product);
        }
        
    }

    //for shopping cart method
    public HashMap<String, Product> getProductMap() 
    {
        return productMap;
    }
        
    //START OF METHODS FOR ADMIN

    //INSERTING NEW PRODUCT FOR ADMIN
    public void insertProduct(Scanner scnr) 
    {

        System.out.println("\n________________Adding Product_________________\n");
        System.out.println("Please enter all required information.\n");

        // Gather product details from the admin
        ProductCategory category = null; 
        while (category == null) 
        {

            System.out.println("Product Category (TShirt, Skirt, Jean, Blouse):");
            System.out.print("--> ");
            String categoryInput = scnr.next().toLowerCase(); //lowecase to make it easier to compare later

            for (ProductCategory enumValue : ProductCategory.values()) 
            {

                if (enumValue.toString().toLowerCase().equals(categoryInput)) 
                { //making lowercase to compare 

                    category = enumValue;
                    break;

                }

            }

            //safefall for if user types it in incorrectly
            if (category == null) 
            {

                System.out.println("\nInvalid Category Input. Please Try Again!\n");
            
            }

        }

        // DEBUG checking if valid category
        System.out.println("\nSelected Category: " + category);
        System.out.print("Product Price: $");
        double price = scnr.nextDouble();
        //this is the number that will be displayed to students
        System.out.print("Product Number: #"); 
        int productNumber = scnr.nextInt();
        // Read the leftover new line
        scnr.nextLine(); 
        String title = category.toString() + " #" + productNumber;
        int listingDate = (int) System.currentTimeMillis(); // Convert to int

        Product product = new Product(title, price, listingDate, category, productNumber);
        productMap.put(title, product);
        productList.add(product);

        //THESE ARE DEBUG LINES TO MAKE SURE THE PRODUCTS ARE GETTING ADDED CORRECTLY
        System.out.println("\nProduct added: " + title);
        System.out.println("ProductList size: " + productList.size());
        System.out.println("_______________________________________________");
        System.out.println("\nProduct Added Successfully!");

    }

    //VIEWING ALL PRODUCTS FOR ADMIN
    public void viewAllProducts(boolean isAdmin)
    {
        System.out.println("\n_______________________________________________");
        System.out.println("\n                Product List");
        System.out.println("   ----------------------------------------\n");

        if (productList.isEmpty())
        {

            System.out.println("            No Products Available :(");
            System.out.println("\n   ----------------------------------------\n");

        }

        else 
        {

            if (isAdmin)
            {

                for (Product product : productList) 
                {
                    System.out.printf("%-25s %-30s %n","   Title:", product.title);
                    System.out.printf("%-25s %-30s %n","   Category:", product.category.toString());
                    System.out.printf("%-25s $%-29.2f %n","   Price:", product.getPrice());
                    System.out.printf("%-25s #%-25d %n","   Product Number:", product.getProductNumber());
                    System.out.printf("%-25s %-25d %n","   Listing Date:", product.listingDate);
                    System.out.println("\n   ----------------------------------------\n");
                }

            }

            else
            {

                for (Product product : productList) 
                {
                    System.out.printf("%-25s %-30s %n","   Title:", product.title);
                    System.out.printf("%-25s $%-29.2f %n","   Price:", product.getPrice());
                    System.out.println("\n   ----------------------------------------\n");
                }  

            }

        }

        System.out.println("_______________________________________________");

    }
    
    //DELETING PRODUCT FOR ADMIN
    //THIS IS THE METHOD WITH THE SCANNER ISSUE; I HAVE NO IDEA HOW TO FIX IT 
    //AYUDA HERE AYUDA HERE
    public void deleteAdminProduct(Scanner scnr) 
    {
        System.out.println("\n_______________Deleting Product________________");
        boolean repeat;

        do
        {

            System.out.println("\nWould you like to see a list of all your products? (Yes/No): ");
            System.out.print("--> ");
            String choice = scnr.nextLine();
            repeat = false;

            if (choice.trim().equalsIgnoreCase("Yes")) 
            {

                viewAllProducts(true);

            }

            else if (!choice.trim().equalsIgnoreCase("No"))
            {

                System.out.println("\nInvalid Input. Please Try Again!");
                repeat = true;

            }

        } while (repeat);

        System.out.println("\nEnter the title of the product you want to delete:");
        System.out.print("--> ");
        String productDelete = scnr.nextLine();
      
        // Check if the product exists
        if (productMap.keySet().stream().anyMatch(key -> key.equalsIgnoreCase(productDelete))) 
        {

            // Remove from productMap
            productMap.remove(productDelete);

            // Remove from productList
            productList.removeIf(product -> product.title.equalsIgnoreCase(productDelete));
                        
            System.out.println("\nProduct Deleted Successfully!");
            
        } 

        else 
        {

            System.out.println("\nProduct Not Found. Unable To Delete.");

        }

    }

    //START OF METHODS FOR USERS
    public void displayFilterMenu()
    {
        System.out.println("\n_________________Filter Option_________________\n");
        System.out.println("         1) Cheapest to Expensive");
        System.out.println("         2) Expensive to Cheapest");
        System.out.println("         3) Newest to Oldest");
        System.out.println("         4) Oldest to Newest");
        System.out.println("\n_______________________________________________");
        System.out.print("\n--> Please Enter Your Choice: ");
    }

    //SORT PRODUCTS BASED ON PRICE OR DATE
    public void sortProduct(Scanner scnr, boolean isAdmin, boolean isFilter)
    {
        do 
        {

            System.out.println("\nWould you like to filter items? (Yes/No):");
            System.out.print("--> ");
            String filterChoice = scnr.nextLine();

            if (filterChoice.equalsIgnoreCase("YES"))
            {

                boolean repeat;

                do
                {

                    displayFilterMenu();
                    String choice = scnr.nextLine();
                    repeat = false;

                    switch (choice) 
                    {

                        case "1":
                            Collections.sort(productList, Comparator.comparingDouble(Product::getPrice));
                            viewAllProducts(isAdmin);

                            break;
                        
                        case "2":
                            Collections.sort(productList, Comparator.comparingDouble(Product::getPrice).reversed());
                            viewAllProducts(isAdmin);

                            break;
                        
                        case "3":
                            Collections.sort(productList, Comparator.comparingInt(Product::getListingDate).reversed());
                            viewAllProducts(isAdmin);

                            break;

                        case "4":
                            Collections.sort(productList, Comparator.comparingInt(Product::getListingDate));
                            viewAllProducts(isAdmin);

                            break;

                        default:
                            System.out.println("\nInvalid Filter Choice. Please Try Again!\n");
                            repeat = true;
                            
                            break;

                    }

                } while (repeat);

            }

            else if (filterChoice.equalsIgnoreCase("NO"))
            {

                isFilter = false;

            }

            else
            {

                System.out.println("\nInvalid Input. Please Try Again!");
                
            }

        } while(isFilter);          

    }

    public void displaySearchMenu()
    {
        System.out.println("\n_________________Search Option_________________\n");
        System.out.println("           1) Search Based On Title");
        System.out.println("           2) Search Based On Category");
        System.out.println("\n_______________________________________________");
        System.out.print("\n-->Please Enter Your Choice: ");
    }

    public void viewProduct(Product product)
    {
        System.out.printf("%-25s %-30s %n","   Title:", product.title);
        System.out.printf("%-25s $%-29.2f %n","   Price:", product.getPrice());
        System.out.println("\n   ----------------------------------------\n");
    }

    // SEARCH PRODUCT BASED ON TITLE AND CATEGORY
    public void searchProduct(Scanner scnr, boolean isSearch)
    {
        do
        {

            System.out.println("\nWould you like to search a specific item? (Yes/No):");
            System.out.print("--> ");
            String searchChoice = scnr.nextLine();
            
            if (searchChoice.equalsIgnoreCase("Yes"))
            {
                boolean repeat;

                do
                {

                    displaySearchMenu();
                    String choice = scnr.nextLine();
                    String query;
                    boolean found;
                    repeat = false;

                    found = false;

                    switch (choice) 
                    {
                        case "1":
                            System.out.println("\nEnter the title to search for:");
                            System.out.print("--> ");
                            query = scnr.nextLine().toLowerCase();

                            System.out.println("\n_______________________________________________\n");
                            System.out.println("                 Shopping Items");
                            System.out.println("   ----------------------------------------\n");

                            for (Map.Entry<String, Product> entry : productMap.entrySet())
                            {
                                String title = entry.getKey().toLowerCase();
                                
                                if (title.contains(query))
                                {
                                    Product foundProduct = entry.getValue();
                                    viewProduct(foundProduct);
                                    found = true;
                                }
                            }

                            System.out.println("\n_______________________________________________\n");

                            // IDK if we still need this
                            // for (Product product : productList)
                            // {
                            //     String title = product.title.toLowerCase();
                            //     
                            //     if (title.contains(query))
                            //     {
                            //         viewProduct(product);
                            //         found = true;
                            //    }
                            //}

                            if (!found)
                            {
                                System.out.println("\n              No Products Found :(");
                                System.out.println("\n   ----------------------------------------\n");
                            }

                            break;
                        
                        case "2":
                            System.out.println("\nEnter the category to search for (Tshirt, Jean, Skirt, Blouse):");
                            System.out.print("--> ");
                            query = scnr.nextLine().toLowerCase();

                            System.out.println("\n_______________________________________________\n");
                            System.out.println("                 Shopping Items");
                            System.out.println("   ----------------------------------------\n");

                            for (Product product : productList)
                            {
                                String category = product.category.toString().toLowerCase();

                                if (category.equalsIgnoreCase(query))
                                {
                                    viewProduct(product);
                                    found = true;
                                }
                            }

                            System.out.println("\n_______________________________________________\n");

                            if (!found)
                            {
                                System.out.println("\n              No Products Found :(");
                                System.out.println("\n   ----------------------------------------\n");
                            }
                            
                            break;

                        default:
                            System.out.println("\nInvalid Input. Please Try Again!");
                            repeat = true;
                            break;
                    }

                } while (repeat);

            }else if (searchChoice.equalsIgnoreCase("No")){
                break;
            }

        } while (isSearch);

    }

    public void removeProduct(Product product) {
        String title = product.getTitle();
        productMap.remove(title);
        productList.removeIf(p -> p.getTitle().equals(title));
    }


}
