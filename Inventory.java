import java.util.*;

public class Inventory 
{
    private HashMap<String, Product> productMap = new HashMap<>();
    private ArrayList<Product> productList = new ArrayList<>();
    private ShoppingCart shoppingCart = new ShoppingCart();

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
    public void viewAllProductsAdmin()
    {
        System.out.println("\n_______________________________________________");
        System.out.println("\n                Product List");
        System.out.println("   ----------------------------------------\n");

        for (Product product : productList) 
        {
            System.out.printf("%-25s %-30s %n","   Title:", product.title);
            System.out.printf("%-25s %-30s %n","   Category:", product.category.toString());
            System.out.printf("%-25s $%-29.2f %n","   Price:", product.getPrice());
            System.out.printf("%-25s #%-25d %n","   Product Number:", product.getProductNumber());
            System.out.printf("%-25s %-25d %n","   Listing Date:", product.listingDate);
            System.out.println("\n   ----------------------------------------\n");
        }

        System.out.println("_______________________________________________");

    }
    
    //DELETING PRODUCT FOR ADMIN
    //THIS IS THE METHOD WITH THE SCANNER ISSUE; I HAVE NO IDEA HOW TO FIX IT 
    //AYUDA HERE AYUDA HERE
    public void deleteAdminProduct(Scanner scnr) 
    {
        System.out.println("\n_______________Deleting Product________________\n");
        System.out.println("Would you like to see a list of all your products? (Yes/No): ");
        System.out.print("--> ");
        String choice = scnr.nextLine();

        if (choice.trim().equalsIgnoreCase("Yes")) 
        {
            viewAllProductsAdmin();
        }

        System.out.println("\nEnter the title of the product you want to delete:");
        System.out.print("--> ");
        String titleToDelete = scnr.nextLine();
      
        // Check if the product exists
        if (productMap.containsKey(titleToDelete)) 
        {

            // Remove from productMap
            productMap.remove(titleToDelete);

            // Remove from productList
            productList.removeIf(product -> product.title.equals(titleToDelete));
                        
            System.out.println("\nProduct Deleted Successfully!");
            
        } 

        else 
        {
            System.out.println("\nProduct not found. Unable to delete.");
        }

    }

    //START OF METHODS FOR USERS
    public void viewAllProductsUser() 
    {
        System.out.println("All Products:");
        System.out.println("------------------------");

        for (Product product : productList) {
            System.out.println("Title: " + product.title);
            System.out.println("Price: $" + product.getPrice());  
            System.out.println("------------------------");
        }

    }

    public void displayFilterMenu()
    {
        System.out.println("\n\n______________Filter Option______________\n");
            System.out.println("   1) Cheapest to Expensive");
            System.out.println("   2) Expensive to Cheapest");
            System.out.println("   3) Newest to Oldest");
            System.out.println("   4) Oldest to Newest");

            System.out.print("Please Choose a Filter Option: ");
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
                            
                            if (isAdmin) 
                            { 
                                viewAllProductsAdmin();
                            }
                            else 
                            { 
                                viewAllProductsUser(); 
                            }
                            break;
                        
                        case "2":
                            Collections.sort(productList, Comparator.comparingDouble(Product::getPrice).reversed());
                            if (isAdmin)
                            {
                                viewAllProductsAdmin();
                            }
                            else
                            {
                                viewAllProductsUser();
                            }
                            break;
                        
                        case "3":
                            Collections.sort(productList, Comparator.comparingInt(Product::getListingDate).reversed());
                            if (isAdmin)
                            {
                                viewAllProductsAdmin();
                            }
                            else
                            {
                                viewAllProductsUser();
                            }
                            break;

                        case "4":
                            Collections.sort(productList, Comparator.comparingInt(Product::getListingDate));
                            if (isAdmin)
                            {
                                viewAllProductsAdmin();
                            }
                            else
                            {
                                viewAllProductsUser();
                            }
                            break;

                        default:
                            System.out.println("Invalid filter choice. Please try again!");
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
                System.out.println("Not a valid input, please try again!");
            }

        } while(isFilter);          

    }

    public void displaySearchMenu()
    {
        System.out.println("\n\n______________Search Option______________\n");
            System.out.println("   1) Search Based On Title");
            System.out.println("   2) Search Based On Category");

            System.out.print("Please Choose a Filter Option: ");
    }

    public void viewProduct(Product product)
    {
        System.out.println("------------------------");
        System.out.println("Title: " + product.title);
        System.out.println("Price: $" + product.getPrice());  
        System.out.println("------------------------");    }

    // SEARCH PRODUCT BASED ON TITLE AND CATEGORY
    public void searchProduct(Scanner scnr)
    {
        boolean repeat;

        do
        {
            displaySearchMenu();
            String choice = scnr.nextLine();
            String query;
            boolean found;
            repeat = false;

            switch (choice) 
            {
                case "1":
                    System.out.println("\n Enter the title to search for: ");
                    query = scnr.nextLine().toLowerCase();
                    found = false;

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

                    for (Product product : productList)
                    {
                        String title = product.title.toLowerCase();
                        
                        if (title.contains(query))
                        {
                            viewProduct(product);
                            found = true;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No products found.");
                    }
                    break;
                
                case "2":
                    System.out.println("\n Enter the category to search for: ");
                    query = scnr.nextLine().toLowerCase();
                    found = false;

                    for (Product product : productList)
                    {
                        String category = product.category.toString().toLowerCase();
                        
                        if (category.contains(query))
                        {
                            viewProduct(product);
                            found = true;
                        }
                    }

                    if (!found)
                    {
                        System.out.println("No products found.");
                    }
                    break;

                default:
                    System.out.println("Invalid search choice. Please try again!");
                    repeat = true;
                    break;
            }

        } while (repeat);
        
    }

    

}
