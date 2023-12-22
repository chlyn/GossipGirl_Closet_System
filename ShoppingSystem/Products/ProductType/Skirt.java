package ShoppingSystem.Products.ProductType;

import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.ProductCategory;

public class Skirt extends Product 
{

    public Skirt(String name, double price, int listingDate, ProductCategory category, int productNumber) 
    {
        super(name, price, listingDate, category, productNumber);
    }

    ProductCategory category = ProductCategory.Skirt;

    String name;
    double price;
    int listingDate;

}


