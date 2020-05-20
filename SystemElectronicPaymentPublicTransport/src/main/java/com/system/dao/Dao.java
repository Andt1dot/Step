package com.system.dao;

import com.system.model.Customer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Dao {
    
    private Connection initConnection(){
     String url = "jdbc:postgresql://127.0.0.1:5432/MServices"; 
     String user = "postgres";
     String password ="123"; 
       try{
           System.out.println("Connection to database...");    
           return DriverManager.getConnection(url, user,password);
       }catch(SQLException ex){
           System.out.println("Error! Can't connection to database ");
           return null;
       }   
        
  } 
    

   public boolean updateBalanceCard(double newBalance){   
      return this.operationExecuteSQL("UPDATE * FROM MService.customer where balance = "+newBalance); 
   }
   
 
   public boolean operationExecuteSQL(String sqlQuery){
       Connection conn = initConnection();
       Statement st = null;
       boolean resultQuery = false;
       try{
           if(!conn.isClosed()){
               st =  conn.createStatement();
               st.executeUpdate(sqlQuery); 
               System.out.println("Operation successfully !!!");
               resultQuery = true;
           }
       }catch(SQLException ex){
               System.out.println("Operation failed !!!"+ex.getMessage());
               resultQuery = false;
       }
       finally{
           try{
              conn.close();
              st.close();
           }catch(SQLException ex){
               System.out.println("Operation failed !!!"+ex.getMessage());
               resultQuery = false;
           }
       }        
       return resultQuery;
   } 
    
    
    
   public Customer checkExistCustomer(String identityCard){
       
       Connection conn = initConnection();
       Statement st = null;
 try{          
       if(!conn.isClosed()) {
           st = conn.createStatement();
           ResultSet result = st.executeQuery("SELECT * FROM where identityCard = "+identityCard);    
           
           while (result.next()) {              
             String name =  result.getString("");
             String surname = result.getString("");
             LocalDate birthdate = LocalDate.parse(result.getString(""));
             int identitycode = result.getInt(1);
             String identitycard  = result.getString("");
             double balanceCard = result.getDouble(1);
             return new Customer(name,surname,birthdate,identitycode,identitycard,balanceCard);
           }
       }   
       else
          System.out.println("Error, can't possible connection to database !!!");
                 
       }catch(SQLException ex){
           System.out.println("Error, please contact your progrmmer ->> error is "+ ex.getMessage());;   
       }
     return null;           
   }
  
  }
    