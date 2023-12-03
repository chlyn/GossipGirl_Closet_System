import java.util.ArrayList;

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
        
    }
    