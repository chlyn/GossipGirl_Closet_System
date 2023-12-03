import java.util.*;

public class ShoppingCart {
        private ArrayList<Product> cartItems = new ArrayList<>();

        // ADD PRODUCT TO SHOPPING CART
        public void addProductToCart(Product product) {
            cartItems.add(product);
        }

        // GET SHOPPING CART SIZE
        public int getCartSize() {
            return cartItems.size();
        }

        public void printCartItems() {
            System.out.println("Shopping Cart Items:");
            System.out.println("------------------------");
        
            for (Product product : cartItems) {
                System.out.println("Title: " + product.title);
                System.out.println("Price: $" + product.getPrice());
                System.out.println("------------------------");
            }
        }
        
        public void addProduct(Scanner scnr, Inventory inventory){
            System.out.println("Enter the title of the product: ");
            System.out.print("-> Product Title: ");
            String productTitle = scnr.nextLine();

            Product selectedProduct = inventory.getProductMap().get(productTitle);

             if (selectedProduct != null) 
             {
              addProductToCart(selectedProduct);
             } else 
             {
                 System.out.println("Product not found. Unable to add to the cart.");
              }

    }

    }
    