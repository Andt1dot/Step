package com.step.manager.test;

import com.step.enums.Genre;
import com.step.model.Employee;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeManagerTest {
    
Employee emp;
List<Employee> listEmployee;

//-------------------Add-----------------------//
private final String name = "Nick"; 
private final String surname = "Ray";
private final int age = 25;
private final int identity = 200;
private final Genre genre = Genre.Male;
private final LocalDate birthdate = LocalDate.now();

//--------------------Edit---------------------//
private final String editName = "Michell";
private final String editSurname = "Obama";
private final int editAge = 30;
private final int editIdentity = 300;
private final Genre editGenre = Genre.Female;
private final LocalDate editBirthdate = LocalDate.parse("24.03.1988",DateTimeFormatter.ofPattern("dd.MM.yyyy"));

@Before
public void init(){
emp =  new Employee(name,surname,age,identity,genre,birthdate);
listEmployee = new ArrayList(); 
} 

@Test
public void testAddEmployeeSucces_arrayList(){
 listEmployee.add(emp); 
 int idEmployee  = listEmployee.indexOf(emp);
 Employee employeeTest = listEmployee.get(idEmployee);
 Assert.assertEquals("Testing name", emp.getName(), employeeTest.getName());
 Assert.assertEquals("Testing surname", emp.getSurname(), employeeTest.getSurname());
 Assert.assertEquals("Testing age", emp.getAge(), employeeTest.getAge());
 Assert.assertEquals("Testing identity", emp.getIdentityCode(), employeeTest.getIdentityCode());
 Assert.assertEquals("Testing genre", emp.getGenre(), employeeTest.getGenre());
 Assert.assertEquals("Testing birthdate", emp.getBirthdate(), employeeTest.getBirthdate());
}


@Test(expected  = IllegalArgumentException.class)
public void testAddEmployeeError_arrayList(){    
 listEmployee.add(new Employee(name,surname,identity,age,Genre.valueOf("m"),birthdate));
}


@Test
public void testEditEmployeeSucces_arrayList(){  
listEmployee.add(emp);    
listEmployee.get(0).setName(editName);
listEmployee.get(0).setSurname(editSurname);
listEmployee.get(0).setAge(editAge);
listEmployee.get(0).setIdentityCode(editIdentity);
listEmployee.get(0).setGenre(editGenre);
listEmployee.get(0).setBirthdate(editBirthdate);

Assert.assertEquals(" Testing edit name =  ", editName, listEmployee.get(0).getName());
Assert.assertEquals(" Testing edit surname =  ", editSurname, listEmployee.get(0).getSurname());
Assert.assertEquals(" Testing edit age =  ", editAge, listEmployee.get(0).getAge());
Assert.assertEquals(" Testing edit identity =  ", editIdentity, listEmployee.get(0).getIdentityCode());
Assert.assertEquals(" Testing edit genre =  ", editGenre, listEmployee.get(0).getGenre());
Assert.assertEquals(" Testing edit birthdate =  ", editBirthdate, listEmployee.get(0).getBirthdate());
}


@Test(expected  = IllegalArgumentException.class)
public void testEditEmployeeError_arrayList(){  
listEmployee.add(emp);    
listEmployee.get(0).setGenre(Genre.valueOf("m"));
}


@Test
public void testDeleteEmployeeSucces_arrayList(){
listEmployee.add(emp);
boolean isTrue = listEmployee.remove(emp);
Assert.assertTrue("Testing delete employee with succes", isTrue);
}


@Test
public void testDeleteEmployeeError_arrayList(){  
emp.setName("Marin");
boolean isFalse = listEmployee.remove(emp);    
Assert.assertFalse("Testing delete employee with false", isFalse);
}


@Test
public void testGetAllEmployeeSucces_arrayList(){  
boolean isTrue = listEmployee.isEmpty();
Assert.assertTrue("Testing list Employee is empty", isTrue);          
}


@Test
public void testGetAllEmployeeError_arrayList(){
listEmployee.add(emp);
boolean isFalse = listEmployee.isEmpty();
Assert.assertFalse("Testing list show all Employee",isFalse);
    
    
}












}