package com.system;
import com.system.dao.Dao;
import com.system.enums.Days;
import com.system.enums.TransportNumber;
import com.system.model.Customer;
import com.system.model.Travel;
import java.time.LocalTime;


public class Transaction {
   
    Customer customer;
    Dao dao;
    Travel travel;
    
    public Transaction(){
        this.customer = new Customer();
        this.dao =  new Dao();
    }
    
   public byte paymentTravel(String customerIdentityCard){
    customer.setCustomer(dao.checkExistCustomer(customerIdentityCard));    
    if(customer.getCustomer()!=null){
    
      if(customer.getBalanceCard()<5)
           return 1;
    
      else if(customer.getBalanceCard()>=5){
     
          double paymentTravel = customer.getBalanceCard() - 5;
         
          // 1. this --> Update customer balance 
         dao.updateBalanceCard(paymentTravel);
          // 2. this --> Save dates about travel
         travel = new Travel(customer.getCustomer(),TransportNumber.One,Days.Monday,LocalTime.now(),paymentTravel); 
           return 0;
        }
     }
    return 2;
   }    
    
}
