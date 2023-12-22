package ShoppingSystem.Products.ProductType;

import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.ProductCategory;

public class Jean extends Product 
{

  public Jean(String name, double price, int listingDate, ProductCategory category, int productNumber) 
  {
    super(name, price, listingDate, category, productNumber);
  }
  
  ProductCategory category = ProductCategory.Jean;

  String name;
  double price;
  int listingDate;

}
