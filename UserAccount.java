public class UserAccount {
    private String username;
    private String password;
    private String email;
    private ShoppingCart shoppingCart;


    public UserAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.shoppingCart = new ShoppingCart(); 
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getEmail(){
        return email;
    }
    
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    
    public void print(){
        System.out.println(username + " " + password);
    }
}