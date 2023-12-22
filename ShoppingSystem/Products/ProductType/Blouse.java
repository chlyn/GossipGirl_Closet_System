package ShoppingSystem.Products.ProductType;

import ShoppingSystem.Products.Product;
import ShoppingSystem.Products.ProductCategory;

public class Blouse extends Product
{

  public Blouse(String name, double price, int listingDate, ProductCategory category, int productNumber) 
  {
    super(name, price, listingDate, category, productNumber);
  }

  ProductCategory category = ProductCategory.Blouse;
  
  String name;
  double price;
  int listingDate;

}
