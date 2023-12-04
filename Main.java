import java.util.*;

public class Main{

    public static void displayAdminMenu()
    {
        System.out.println("\n__________________Admin Menu___________________\n");
        System.out.println("             Product Management\n");
        System.out.println("              1) Add Product");
        System.out.println("              2) Delete Product");
        System.out.println("              3) View Products");
        System.out.println("              4) Exit");
        System.out.println("\n_______________________________________________");
        System.out.print("\n--> Please Enter Your Choice: ");
    }

    public static void displayUserMenu()
    {
        System.out.println("\n_________Welcome to Gossip Girl Closet_________\n");
        System.out.println("                Ready to Shop?\n");
        System.out.println("            1) Let's Go Shopping!  ");
        System.out.println("            2) Open Cart");
        System.out.println("            3) Exit");
        System.out.println("\n_______________________________________________");
        System.out.print("\n--> Please Enter Your Choice: ");
    }

    public static void main(String[] args)
    {
        Scanner scnr = new Scanner(System.in);

        //creating instance of login system
        Login loginSystem = new Login(scnr);
        //creating an instance of inventory because the methods are not static        
        Inventory inventory = new Inventory();
        //creating an instance of ShoppingCart
        ShoppingCart userCart = new ShoppingCart();


        boolean mainRepeat = true;
        int usertypeCode = 0;
        
        System.out.println("\n         Welcome to Gossip Girl Closet");

        while(mainRepeat)
        {
        
            //running login system (admin or user)/ (login or new)
            usertypeCode = loginSystem.runLoginSystem();
                
            if (usertypeCode == 1)
            { //admin options
                boolean repeat = true;
                displayAdminMenu();
                do 
                {
                    String choice = scnr.nextLine();

                    switch(choice)
                    {
                        case "1":
                            // Call the insertProduct method from Inventory
                            inventory.insertProduct(scnr);
                            displayAdminMenu();

                            break;

                        case "2": 
                            inventory.deleteAdminProduct(scnr);
                            displayAdminMenu();

                            break;

                        case "3": 
                            inventory.viewAllProducts(true);// PRINTS OUT ALL PRODUCTS IN LIST
                            inventory.sortProduct(scnr, true, true);
    
                            displayAdminMenu();

                            break;

                        case "4":
                            System.out.println("\nThank You For Shopping With Us!!");
                            repeat = false;
                            break;

                        default:
                            System.out.println("\nInvalid Menu Choice. Please try again!");
                            displayAdminMenu();
                            break;

                    }

                } while(repeat);

            }
            
            else if (usertypeCode == 2)
            { //user options
                boolean repeat = true;
                displayUserMenu();
                do 
                {
                    String choice = scnr.nextLine();

                    switch(choice)
                    {
                        case "1":

                            inventory.viewAllProducts(false);
                            inventory.sortProduct(scnr, false, true);
                            userCart.addProduct(scnr, inventory);
                            displayUserMenu();
                        
                            break;

                        case "2": 
                            userCart.displayCartItems(scnr);
                            displayUserMenu();

                            break;
                            
                        case "3":
                            System.out.println("\nThank You For Shopping With Us!!");
                            repeat = false;
                            break;

                        default:
                            System.out.println("\nInvalid Menu Choice. Please try again!");
                            displayAdminMenu();
                            break;
                        
                    }

                } while(repeat);

            }

            else if (usertypeCode == 3)
            {
                System.out.println("\nThank You For Shopping With Us!!");
                mainRepeat = false;
            }
            
        }
    
        scnr.close();
    }
}