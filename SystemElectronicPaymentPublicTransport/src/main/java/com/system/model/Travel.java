package com.system.model;
import com.system.dao.Dao;
import com.system.enums.Days;
import com.system.enums.TransportNumber;
import java.time.LocalTime;

public class Travel {
   Customer customer;
   TransportNumber transportNumber;
   Days day;
   LocalTime time;
   double price;
   Dao dao;
  
    public Travel(Customer customer,TransportNumber transportNumber, Days day, LocalTime time, double price) {
        this.customer = customer;
        this.transportNumber = transportNumber;
        this.day = day;
        this.time = time;
        this.price = price;
    }
 
}



