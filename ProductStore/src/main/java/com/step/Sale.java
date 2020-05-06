package com.step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;




public class Sale {
    
 Map <String,Product> listProducts;    
 BufferedReader reader;  
    
public Sale(){
   listProducts = new HashMap(); 
   reader = new BufferedReader(new InputStreamReader(System.in));
}
 
 
 public void menuSale() throws IOException{ 
     System.out.println("**********MENU**********");
     System.out.println("1. Add Product");
     System.out.println("2. Show all product");
     System.out.println("3. Sale product");
     System.out.println("4. Show product profit");
     System.out.println("5. Show total profit");
     System.out.println("6. Sort");
    
    int choose = Integer.parseInt(reader.readLine());
  
     switch (choose) {
         case 1: this.addProduct();break;
         case 2: this.showAllProduct(); break;
         case 3: this.removeProductFromStock();break;
         case 4: this.calculateProfit(); break;
         case 5: this.calculateTotalProfit();break;
         case 6: this.menuSort(); break;
         default:  {System.out.println("Unknown option, please repeat and select 1-6");
         this.menuSale();
         }
    }

 }
    
 private void addProduct() throws IOException{
         
     System.out.println("Hello, hey enter please number how many products do you want = ");
     int numberProduct = Integer.parseInt(reader.readLine());
     
     for (int i = 0; i<numberProduct; i++){
     
     System.out.println("Enter name product = ");
     String name = reader.readLine();
     
     System.out.println("Enter amount = ");
     int amount = Integer.parseInt(reader.readLine());
     
     System.out.println("Enter purchasePrice  = ");
     double purchasePrice = Double.parseDouble(reader.readLine());
     
     System.out.println(" Enter sellingPrice = "); 
     double sellingPrice = Double.parseDouble(reader.readLine());
     
     System.out.println("Enter expirationDate = ");
     LocalDate expirationDate = LocalDate.parse(reader.readLine());
     
     System.out.println("Enter description = ");
     String description = reader.readLine();
     
     listProducts.put(name, new Product(name,purchasePrice,sellingPrice,expirationDate,description,amount));
     }
     this.menuSale();
}

  private void showAllProduct() throws IOException {           
    System.out.println(" !!! Wellcome to WareHouse !!! ");
    System.out.println("| Product |  PurchasePrice |  SellingPrice  |  ExpirationDate  | Description | Amount | "); 
    
    listProducts.entrySet()
                .stream()
                .forEach(P-> System.out.println(P.getValue().getName()+" | "+P.getValue().getPurchasePrice()+" | "
                +P.getValue().getSellingPrice()+" | "
                +P.getValue().getExpirationDate()+" | "
                +P.getValue().getDescription()+" "+P.getValue().getAmountProduct()));
 
     System.out.println("In stock products = "+listProducts.size());       
     this.menuSale();
  }
 

  private void removeProductFromStock() throws IOException {
     
      System.out.println(" !!! Selling products !!! ");
     
      System.out.println(" Enter name product = ");
      String nameProduct = reader.readLine();
      
      System.out.println(" Enter amount = ");
      int amount = Integer.parseInt(reader.readLine()); 
      
      boolean isExistProduct = listProducts.containsKey(nameProduct);
  
      if(isExistProduct){   
           int saveAmount = listProducts.get(nameProduct).getAmountProduct();    
      if (saveAmount<amount){
           System.out.println("We sorry,we don't have so many the product".concat(nameProduct)+"in stock:(((");
           System.out.println("We have "+saveAmount+" "+nameProduct);
      }else {
           listProducts.get(nameProduct).setAmountProduct(saveAmount-amount);
           System.out.println("Successful sale !!!");
      }
    }else
           System.out.println("Sorry, we don't have this product \t".concat(nameProduct));
      
      listProducts.entrySet().removeIf(P->P.getValue().getAmountProduct()<=0);
      
      this.menuSale();   
  }

    private void calculateProfit() throws IOException {
        System.out.println("******Show product profit********");
        System.out.println("| Name |  PurchasePrice |  SellingPrice  | Profit from Product | ");   
        
          listProducts.entrySet()
                .stream()
                .forEach(P-> System.out.println(P.getValue().getName()+" | "+P.getValue().getPurchasePrice()+" | "
                +P.getValue().getSellingPrice()+" | "+(P.getValue().getSellingPrice()-P.getValue().getPurchasePrice())));
    
          this.menuSale();
    }


    private void calculateTotalProfit() throws IOException {
      System.out.println(" !!! Show total profit !!! ");
      System.out.println(" Enter name product = ");
      String nameProduct = reader.readLine();
        
      listProducts.entrySet()
                  .stream()
                  .filter(P->P.getKey().equals(nameProduct))
                  .forEach(P-> System.out.println("Profit total by product = "+P.getValue().getAmountProduct()*(P.getValue().getSellingPrice()-P.getValue().getPurchasePrice())));
    
      this.menuSale();   
}
  
    private void menuSort() throws IOException {
        System.out.println("********MENU SORT***********");
        System.out.println("1. Sort by name ");
        System.out.println("2. Sort by expirationDate ");
        System.out.println("3. Sort by price asc ");
        System.out.println("4. Sort by price desc ");
        
        List<Product> product = listProducts.values()
                                            .stream()
                                            .collect(Collectors.toList());
        
        int choose = Integer.parseInt(reader.readLine());
       
        switch (choose) {
            case 1:product.sort((P1,P2) -> P1.getName().compareTo(P2.getName()));break;
            case 2:product.sort((P1, P2) -> P2.getExpirationDate().compareTo(P1.getExpirationDate())); break;
            case 3:product.sort((P1, P2) -> Double.compare(P1.getPurchasePrice(),P2.getPurchasePrice()));break; 
            case 4:product.sort((P1, P2) -> Double.compare(P2.getPurchasePrice(),P1.getPurchasePrice())); break;
            default:{
                System.out.println("Unknown choose, please select point 1-4");
                this.menuSort();
            }
        }
       
        this.menuSale();
    }
     
}

