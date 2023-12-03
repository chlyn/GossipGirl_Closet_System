import java.util.*;

class Main{

    public static void displayAdminMenu(){
        System.out.println("\n__________Admin Menu__________");
        System.out.println(" Product Management");
        System.out.println("      1) Insert  ");
        System.out.println("      2) Delete  ");
        System.out.println("      3) View Products  ");
        System.out.println("      4) Exit  \n");
    }
    public static void displayUserMenu(){
        System.out.println("\n_____Welcome to Gossip Girl Closet_______");
        System.out.println(" Ready to Shop?");
        System.out.println("      1) Go shopping!  ");
        System.out.println("      2) Open Cart  \n");
        System.out.println("      3) Exit \n");
    }
    public static void cartOptions(){
        System.out.println("      1) Delete Items   ");
        System.out.println("      2) Check Out   ");
    }

    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);

        //creating instance of login system
        Login loginSystem = new Login(scnr);

        boolean mainRepeat = true;
        int usertypeCode = 0;
        
        System.out.println("\n     Welcome to Gossip Girl Closet         ");
        System.out.println("  -------------------------------------\n         ");

        while(mainRepeat){
        
        //running login system (admin or user)/ (login or new)
        usertypeCode = loginSystem.runLoginSystem();
            
        if (usertypeCode == 1)
        { //admin options
            Inventory inventory = new Inventory(); //creating an instance of inventory because the methods are not static

            boolean repeat = true;
            do {
                displayAdminMenu();
                System.out.print("-> Choice: ");
                int choice = scnr.nextInt();

            switch(choice){
                case 1:
                        
                        System.out.println("Inserting a new product:");

                        // Gather product details from the admin
                        ProductCategory category = null;
                        while (category == null) {
                            System.out.print("\nEnter product category (TShirt, Skirt, Jean, Blouse): ");
                            String categoryInput = scnr.next().toLowerCase(); //lowecase to make it easier to compare later

                            for (ProductCategory enumValue : ProductCategory.values()) {
                                if (enumValue.toString().toLowerCase().equals(categoryInput)) { //making lowercase to compare 
                                    category = enumValue;
                                    break;
                                }
                            }
                            //safefall for if user types it in incorrectly
                            if (category == null) {
                                System.out.println("\nInvalid category input. Please enter a valid category.");
                        }
                    }

                    // DEBUG checking if valid category
                    System.out.println("\nSelected category: " + category);

                        System.out.print("Enter product price: $");
                        double price = scnr.nextDouble();

                        System.out.print("Enter product number: "); //this is the number that will be displayed to students
                        int productNumber = scnr.nextInt();

                        // Assuming listingDate is the current time in milliseconds (we can change this later)
                        int listingDate = (int) System.currentTimeMillis();

                        // Call the insertProduct method from Inventory
                        inventory.insertProduct(category, price, productNumber, listingDate);
                        
                        System.out.println("Product inserted successfully!");
                    
                    break;
                case 2: 
                    inventory.deleteAdminProduct(scnr);
                    break;
                case 3: 
                    inventory.viewAllProductsAdmin();// PRINTS OUT ALL PRODUCTS IN LIST

                    break;
                case 4:
                    System.out.println("test 4");
                    repeat = false;
                    break;
            }
            scnr.nextLine();
        }while(repeat);

        }
        else if (usertypeCode == 2)
        { //user options
           boolean repeat = true;
            do {
                displayUserMenu();
                System.out.print("-> Choice: ");
                int choice = scnr.nextInt();

            switch(choice){
                case 1:
                    System.out.println("test 1");
                    
                    break;
                case 2: 
                    System.out.println("test 2");
            
                    break;
                case 3:
                    System.out.println("test 3");
                    repeat = false;
                    break;
            }
            scnr.nextLine();
        }while(repeat);
        }
        else if (usertypeCode == 3)
        {
            System.out.println("Thank you!");
            mainRepeat = false;
        }
        
    }
    
        scnr.close();
    }
}