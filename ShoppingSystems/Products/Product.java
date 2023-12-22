package ShoppingSystems.Products;

public class Product 
{

    public double price;
    public String title;
    public int listingDate; // Assuming listingDate is an int
    public ProductCategory category;
    public int productNumber;

    public Product(String title, double price, int listingDate, ProductCategory category, int productNumber) 
    {
        this.title = title;
        this.price = price;
        this.listingDate = listingDate;
        this.category = category;
        this.productNumber = productNumber;
    }

    // Getter methods for category, price, and productNumber
    public ProductCategory getCategory() 
    {
        return category;
    }

    public double getPrice() 
    {
        return price;
    }

    public int getListingDate() 
    {
        return listingDate;
    }

    public int getProductNumber() 
    {
        return productNumber;
    }

    public String getTitle() 
    {
        return title;
    }
    
}
