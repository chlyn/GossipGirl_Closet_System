package ShoppingSystem.Products.ProductType;

import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.ProductCategory;

public class Tshirt extends Product
{
  
  public Tshirt(String name, double price, int listingDate, ProductCategory category, int productNumber) 
  {
    super(name, price, listingDate, category, productNumber);
  }

  ProductCategory category = ProductCategory.Tshirt;

  String name;
  double price;
  int listingDate;

}