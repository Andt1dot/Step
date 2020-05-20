package com.system.model;
import com.system.dao.Dao;
import com.system.enums.Days;
import com.system.enums.TransportNumber;
import java.time.LocalDate;
import java.time.LocalTime;

public class Customer {
 String name;
 String surname;
 LocalDate birthdate;
 int identitycode;   
 String identitycard;
 double balanceCard;
 
//------------------//
 Customer customer;
 Travel newTravel;  
 Dao dao;
 
    public Customer(String name, String surname, LocalDate birthdate, int identitycode, String identitycard, double balanceCard) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.identitycode = identitycode;
        this.identitycard = identitycard;
        this.balanceCard = balanceCard;
        this.dao = new Dao();
    }

    
       public Customer() {
    }

        
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public int getIdentitycode() {
        return identitycode;
    }

    public void setIdentitycode(int identitycode) {
        this.identitycode = identitycode;
    }

    public String getIdentitycard() {
        return identitycard;
    }

    public void setIdentitycard(String identitycard) {
        this.identitycard = identitycard;
    }

    public double getBalanceCard() {
        return balanceCard;
    }

    public void setBalanceCard(double balanceCard) {
        this.balanceCard = balanceCard;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
         newTravel = new Travel(customer.getCustomer(),TransportNumber.One,Days.Monday,LocalTime.now(),paymentTravel); 
           return 0;
       }
    }
    return 2;
}

}

