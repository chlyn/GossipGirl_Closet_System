package ShoppingSystem;

import java.util.*;

public class ShoppingCart {
        private ArrayList<Product> cartItems = new ArrayList<>();


        // GET SHOPPING CART SIZE
        public int getCartSize() 
        {
            return cartItems.size();
        }

        public void displayCartItems(Scanner scnr, Inventory inventory) 
        {
            System.out.println("_______________________________________________\n");
            System.out.println("             Shopping Cart Items");
            System.out.println("   ----------------------------------------\n");
        
            if (cartItems.isEmpty())
            {

                System.out.println("                Cart is Empty :(");
                System.out.println("\n   ----------------------------------------\n");
            
            }

            else
            {

                for (Product product : cartItems) 
                {
                    System.out.printf("%-25s %-30s %n","   Title:", product.title);
                    System.out.printf("%-25s $%-29.2f %n","   Price:", product.getPrice());
                    System.out.println("\n   ----------------------------------------\n");
                }

                System.out.println("             Cart Size: " + getCartSize() + " Items\n");

            }

            cartOptions(scnr, inventory);

        }

        public void cartOptions(Scanner scnr, Inventory inventory)
        {
            boolean repeat;

            do
            {
                System.out.println("\n__________________Cart Option__________________\n");
                System.out.println("               1) Delete Items");
                System.out.println("               2) Check Out"); 
                System.out.println("               3) Go Back Shopping"); 
                System.out.println("\n_______________________________________________");
                System.out.print("\n--> Please Enter Your Choice: ");
                String choice = scnr.nextLine();

                repeat = false;

                switch (choice)
                {
                    case "1":
                        deleteProduct(scnr);
                        break;

                    case "2":
                        checkoutProduct(scnr, inventory);
                        break;

                    case "3":
                        System.out.println("\nLet's Keep Shopping!");
                        repeat = false;
                        break;

                    default:
                        System.out.println("\nInvalid Choice. Please Try Again!");
                        repeat = true;

                        break;

                }
            
            } while (repeat);

        }
        
        public void addProduct(Scanner scnr, Inventory inventory)
        {
            boolean repeat = true;

            do
            {
                System.out.println("\nWould you like to add an item to cart? (Yes/No):"); 
                System.out.print("--> ");
                String addChoice = scnr.nextLine();

                if(addChoice.equalsIgnoreCase("YES"))
                {
                    System.out.println("\n__________________Add To Cart__________________");
                    System.out.println("\nEnter the title of the product you want to add: ");
                    System.out.print("--> ");
                    String productAdd = scnr.nextLine();
                    System.out.println("\n_______________________________________________");

                    Product selectedProduct = inventory.getProductMap().get(productAdd);

                    if (selectedProduct != null) 
                    {
                        repeat = false;
                        cartItems.add(selectedProduct);
                        System.out.println("\nProduct Added To Cart Successfully!");
                    } 
                    
                    else 
                    {
                        System.out.println("\nProduct Not Found. Unable To Add To Cart.");
                    }  

                }

                else if (addChoice.equalsIgnoreCase("NO"))
                {
                    repeat = false;
                }
                
                else
                {
                    System.out.println("\nInvalid Input. Please Try Again!");
                }

            } while(repeat);   

        }

        public void deleteProduct(Scanner scnr)
        {
            System.out.println("\n_______________Delete From Cart________________");
            System.out.println("\nEnter the title of the product you want to add: ");
            System.out.print("--> ");
            String productDelete = scnr.nextLine();
            System.out.println("\n_______________________________________________");

            boolean removed = cartItems.removeIf(product -> product.title.equalsIgnoreCase(productDelete));

            if (removed)
            {
                System.out.println("\nProduct Deleted From Cart Successfully!");
            }

            else
            {
                System.out.println("\nProduct Not Found. Unable To Delete From Cart.");
            }

        }

        public void clearAfterCheckout(Inventory inventory){
            for (Product product : cartItems) {
                inventory.removeProduct(product);
            }
            cartItems.clear();
        }

        public void checkoutProduct(Scanner scnr, Inventory inventory)
        {
            System.out.println("\n___________________Checkout____________________");
            
            if (cartItems.isEmpty())
            {
                System.out.println("\nYour Cart Is Empty. Unable To Proceed To Checkout.");
            }

            else
            {
                double totalCost = 0;

                for (Product product : cartItems)
                {
                    totalCost += product.getPrice();
                }

                System.out.println("\n   ----------------------------------------\n");
                System.out.printf("%-25s $%-29.2f %n","   Total Cost:", totalCost);
                System.out.println("\n   ----------------------------------------");

                boolean repeat;

                do
                {
                    System.out.println("\nWould you like to proceed with your payment? (Yes/No):");
                    System.out.print("--> ");
                    String checkoutChoice = scnr.nextLine();
                    repeat = false;

                    if(checkoutChoice.equalsIgnoreCase("YES"))
                    {
                        System.out.println("\n____________________Payment____________________");
                        System.out.println("\n   Please enter all required information.\n");
                        System.out.print("   Credit Card Number: ");
                        String cardNumber = scnr.nextLine();
                        System.out.print("   Name: ");
                        String cardName = scnr.nextLine();
                        System.out.print("   CVV: ");
                        String cardCVV = scnr.nextLine();
                        System.out.print("   Billing Address: ");
                        String cardAddress = scnr.nextLine();
                        System.out.println("\n_______________________________________________\n");
                        System.out.println("               Billing Information");
                        System.out.println("\n   ----------------------------------------\n");
                        System.out.printf("%-18s %-30s %n","   Card Number:", cardNumber);
                        System.out.printf("%-18s %-30s %n","   Name:", cardName);
                        System.out.printf("%-18s %-30s %n","   CVV:", cardCVV);
                        System.out.printf("%-18s %-30s %n","   Address:", cardAddress);
                        System.out.println("\n   ----------------------------------------");

                        clearAfterCheckout(inventory);
                        System.out.println("\n         Your Order Has Been Placed!!");

                    }

                    else if (checkoutChoice.equalsIgnoreCase("NO"))
                    {
                        repeat = false;
                    }
                    
                    else
                    {
                        System.out.println("Invalid Input. Please Try Again!");
                    }

                } while(repeat);   

            }  

        }

    }
    