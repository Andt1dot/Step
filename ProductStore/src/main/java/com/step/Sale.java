package com.step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Sale {
    
 List<Product> listProducts = new ArrayList();    
 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
    
 public void menuSale() throws IOException{
     
     System.out.println("**********MENU**********");
     System.out.println("1. Add Product");
     System.out.println("2. Show all product");
     System.out.println("3. Sale product");
     System.out.println("4. Get product profit");
     System.out.println("5. Total profit");
     System.out.println("6. Sort");
    
    int choose = Integer.parseInt(reader.readLine());
  
     switch (choose) {
         case 1: this.addProduct();break;
         case 2: this.showAllProduct(); break;
         case 3: this.removeProductFromStock();break;
     }
    
     
     
 }
    
 private void addProduct() throws IOException{
         
     System.out.println("Hello, hey enter please number how many products do you want = ");
     int numberProduct = Integer.parseInt(reader.readLine());
     
     for (int i = 0; i<numberProduct; i++){
     
     System.out.println("Enter name product = ");
     String name = reader.readLine();

     System.out.println("Enter purchasePrice  = ");
     double purchasePrice = Double.parseDouble(reader.readLine());
     
     System.out.println(" Enter sellingPrice = "); 
     double sellingPrice = Double.parseDouble(reader.readLine());

     
     System.out.println("Enter expirationDate = ");
     LocalDate expirationDate = LocalDate.parse(reader.readLine());
     
     System.out.println("Enter description = ");
     String description = reader.readLine();
     
     listProducts.add(new Product (name, purchasePrice,sellingPrice,expirationDate,description));
     
  }

}

  private void showAllProduct() {           
    System.out.println(" !!! Wellcome to WareHouse !!! ");
    System.out.println(" Name | PurchasePrice | SellingPrice | ExpirationDate | Description| "); 
        
    for (Product p: listProducts){         
        System.out.println(p.getName()+" | "+p.getPurchasePrice()+" | "+p.getSellingPrice()+" | "+p.getExpirationDate()+" | "+p.getDescription());            
    }    
      System.out.println("Are in stock ="+listProducts.size());    
    }
 



  private void removeProductFromStock() throws IOException {
 
      System.out.println(" !!! Selling products !!! ");
      System.out.println(" Enter name product = ");
      String nameProduct = reader.readLine();
      System.out.println("Enter amount = ");
      int amount = Integer.parseInt(reader.readLine());
      
      //--------------------------------------------//
      


}

  
}
