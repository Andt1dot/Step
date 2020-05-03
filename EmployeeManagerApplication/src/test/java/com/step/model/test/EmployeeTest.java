package com.step.model.test;

import com.step.enums.Genre;
import com.step.model.Employee;
import java.time.LocalDate;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;


public class EmployeeTest {

Employee employee;

private final int idEmployee = 1;
private final String name = "Nick"; 
private final String surname = "Ray";
private final int age = 25;
private final int identity = 200;
private final Genre genre = Genre.Male;
private final LocalDate birthdate = LocalDate.now();

@Before    
public void initTest(){
employee = new Employee(name,surname,age,identity,genre,birthdate); 
employee = new Employee(idEmployee,name,surname,genre,age,identity,birthdate); 
}    
    
@Test    
public void testEmployee_constructor6params(){
  Assert.assertEquals("Testing name attribute", name, employee.getName());
  Assert.assertEquals("Testing surname attribute", surname, employee.getSurname());
  Assert.assertEquals("Testing age attribute", age, employee.getAge());
  Assert.assertEquals("Testing identitycode",identity,employee.getIdentityCode());
  Assert.assertEquals("Testing genre ",genre,employee.getGenre());
  Assert.assertEquals("Testing birthdate",birthdate, employee.getBirthdate());
}

@Test    
public void testEmployee_constructor7params(){
  Assert.assertEquals("Testing id attribute", idEmployee,employee.getIdEmployeeDb());
  Assert.assertEquals("Testing name attribute", name, employee.getName());
  Assert.assertEquals("Testing surname attribute", surname, employee.getSurname());
  Assert.assertEquals("Testing age attribute", age, employee.getAge());
  Assert.assertEquals("Testing identitycode",identity,employee.getIdentityCode());
  Assert.assertEquals("Testing genre ",genre,employee.getGenre());
  Assert.assertEquals("Testing birthdate",birthdate, employee.getBirthdate());
}


}
