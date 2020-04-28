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
     System.out.println("4. Show product profit");
     System.out.println("5. Show total profit");
     System.out.println("6. Sort");
    
    int choose = Integer.parseInt(reader.readLine());
  
     switch (choose) {
         case 1: this.addProduct();break;
         case 2: this.showAllProduct(); break;
         case 3: this.removeProductFromStock();break;
         case 4: this.CalculationProfit(); break;
         case 5: this.CalculationTotalProfit();break;
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
     this.menuSale();
}

  private void showAllProduct() throws IOException {           
    System.out.println(" !!! Wellcome to WareHouse !!! ");
    System.out.println("| Name |  PurchasePrice |  SellingPrice  |  ExpirationDate  | Description | "); 
        
    listProducts.forEach((p) -> {
        System.out.println(p.getName()+" | "+p.getPurchasePrice()+" | "+p.getSellingPrice()+" | "+p.getExpirationDate()+" | "+p.getDescription());
     });    
      System.out.println("Are you in stock products = "+listProducts.size());   
      
      this.menuSale();
    }
 

  private void removeProductFromStock() throws IOException {
      int countProduct=0;
      System.out.println(" !!! Selling products !!! ");
      System.out.println(" Enter name product = ");
      String nameProduct = reader.readLine();
      System.out.println(" Enter amount = ");
      int amountProduct = Integer.parseInt(reader.readLine());
      int []saveIdProduct = new int[amountProduct];
      
      for(int i = 0; i<listProducts.size(); i++){  
         Product p = listProducts.get(i);
         
         if(p.getName().equals(nameProduct))
             saveIdProduct[countProduct++] = listProducts.indexOf(p);
   }      
      try{
      for (int i = 0; i<amountProduct; i++) 
       listProducts.remove(saveIdProduct[i]);
      }catch(IndexOutOfBoundsException e){
          System.out.println(" Error: in stock no quantity required sorry ((:");
      }
      
      this.menuSale();   
  }

    private void CalculationProfit() throws IOException {
        System.out.println("******Show product profit********");
        System.out.println("| Name |  PurchasePrice |  SellingPrice  | Profit from Product | ");   
        listProducts.forEach((p) -> {
        System.out.println(p.getName()+" | "+p.getPurchasePrice()+" | "+p.getSellingPrice()+" | "+(p.getSellingPrice()-p.getPurchasePrice())+"|");
     });    
      
       this.menuSale();
    }


    private void CalculationTotalProfit() throws IOException {
      double sumProfit = 0;
      System.out.println(" !!! Show total profit !!! ");
      System.out.println(" Enter name product = ");
      String nameProduct = reader.readLine();
      
      for(int i = 0; i<listProducts.size(); i++){  
         Product p = listProducts.get(i);
         if(p.getName().equals(nameProduct))
          sumProfit+=p.getSellingPrice() - p.getPurchasePrice();
   }      
     
        System.out.println("Profit total by product ".concat(nameProduct)+" is "+sumProfit);
     
      this.menuSale();   
}

    private void menuSort() throws IOException {
        
        System.out.println("********MENU SORT***********");
        System.out.println("1. Sort by name ");
        System.out.println("2. Sort by expirationDate ");
        System.out.println("3. Sort by price asc ");
        System.out.println("4. Sort by price desc ");
        
       int choose = Integer.parseInt(reader.readLine());
       
        switch (choose) {
            case 1:{
              listProducts.sort((Product p1, Product p2) ->{
                return p1.getName().compareTo(p2.getName());
                 });
           } break;
            case 2:{
              listProducts.sort((Product p1, Product p2) -> {               
                return p2.getExpirationDate().compareTo(p1.getExpirationDate());
                 });        
            }break;
            
            case 3:{
               listProducts.sort((Product p1, Product p2) -> {               
                 return String.valueOf(p1.getPurchasePrice()).compareTo(String.valueOf(p2.getPurchasePrice()));
                 });
            }break; 
            
            case 4:{
                listProducts.sort((Product p1, Product p2) -> {               
                 return String.valueOf( p2.getPurchasePrice()    ).compareTo(String.valueOf(p1.getPurchasePrice()));
                 });
            }break;

        }
       
        this.menuSale();
    }
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
}

