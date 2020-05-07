package com.step;

import com.step.enums.FuelType;
import com.step.model.Car;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Garage {

  List<Car> listCar;  
    
    public Garage(){    
    listCar = new ArrayList();
    listCar.add(new Car("Șkoda","Superb",LocalDate.parse("20.10.2005", DateTimeFormatter.ofPattern("dd.MM.yyyy")),1500,FuelType.Benzine));
    listCar.add(new Car("Opel","Astra",LocalDate.parse("10.09.2010", DateTimeFormatter.ofPattern("dd.MM.yyyy")),1900,FuelType.Diesel));
    listCar.add(new Car("Toyota","Prius",LocalDate.parse("30.10.2015", DateTimeFormatter.ofPattern("dd.MM.yyyy")),1800,FuelType.Benzine));
    listCar.add(new Car("Honda","Civic",LocalDate.parse("13.04.2012", DateTimeFormatter.ofPattern("dd.MM.yyyy")),1600,FuelType.Benzine));
    listCar.add(new Car("Hyundai","Accent",LocalDate.parse("15.09.2004", DateTimeFormatter.ofPattern("dd.MM.yyyy")),1400,FuelType.Diesel));
    }

  public void sortCarByModel(){   
    List<Car> listCarModelAcendingOrder = listCar.stream()
                                                 .sorted((C1,C2)->C1.getModelAuto().compareTo(C2.getModelAuto()))
                                                 .collect(Collectors.toList());
  }
    
  public void sortCarByYear(){
    List<Car> listCarYearOldTenYear = listCar.stream()
                                             .filter(C->(LocalDate.now().getYear()-C.getYearAuto().getYear())>10)
                                             .collect(Collectors.toList());                                                       
  }
  
  
   public void sortCarByMotorCapacity(){        
     List<Car> listCarBiggerMotor1500 = listCar.stream()
                                               .filter(C->C.getMotorCapacity()>1500)
                                               .collect(Collectors.toList());  
   }
  
   
    public static void main(String[] args) {
      Garage myGarage = new Garage();
      
      //this use the desired filtering method  --> myGarage.sortCarByModel();
        
    }
    
}
