package com.step;
import com.step.comparator.AgeComparator;
import com.step.comparator.IdentityCodeComparator;
import com.step.comparator.NameComparator;
import com.step.comparator.SurNameComparator;
import com.step.enums.Genre;
import com.step.model.Employee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import com.step.validator.Validator;
import static java.lang.System.exit;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;



public class EmployeeManager implements IEmployeeManager,Validator {
    
    
    
    
    List <Employee> listEmployee = new ArrayList();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    EmployeeDao employeeDao;
    
    String name,surname;
    int idEmployeeList, age, identityCode;
    Genre genre;
    LocalDate birthDate;
    
    
    
    public EmployeeManager(){
         this.employeeDao = new EmployeeDao();
    }
    

    @Override
    public void addEmployee() throws IOException {
        System.out.println("\t\t\t\t\t**************|Add Employee|*******");   
        
        System.out.println("Insert Name :: ");   
        name=Validator.verificationInputString(reader.readLine());

        System.out.println("Insert Surname :: ");
        surname=Validator.verificationInputString(reader.readLine());
              
        System.out.println("Insert Age :: ");
        age=Validator.verificationInputInteger(reader.readLine());
        
        System.out.println("Insert IdentityCode :: ");
        identityCode=Validator.verificationInputInteger(reader.readLine());   
        
        System.out.println("Insert Genre Male or Female :: ");
        genre=Validator.verificationInputGenre(reader.readLine());

        System.out.println("Insert Birthdate with format example -> |01.12.2012| :: ");
        birthDate = Validator.verificationInputBirthDate(reader.readLine());
        
    
         while(true){
            System.out.println("Do you want to save now employee in the employee database or after ???  Y/N");
            String response = Validator.verificationInputString(reader.readLine());
            if(response.charAt(0)=='Y'||response.charAt(0)=='y'){
            employeeDao.addEmployeeDb(new Employee(name,surname,age,identityCode,genre,birthDate));
            break;
            }
            else if(response.charAt(0)=='N'||response.charAt(0)=='n'){  
                listEmployee.add(new Employee(name,surname,age,identityCode,genre,birthDate));
                System.out.println("SUCCES: The added employee has been successfully added but don't forget  add to database employee !!!");
                break;
            }  
            else
                System.out.println(" Unknown option, please repeat !!! ");
            }
            menuEmployee();

         } 
     
    public void searchMenu(){
        System.out.println("\t\t\t\t\t**************|Search Employee|*******");
        System.out.println("1. Search Name");
        System.out.println("2. Search Surname");
        System.out.println("3. Search Age");
        System.out.println("4. Search Genre");
        System.out.println("5. Search BrithDate");
        System.out.println("6. Back to Menu");
        System.out.println("7. Exit(0)");
    }
    
    @Override
    public void searchEmployee() throws IOException{
        searchMenu();   
  
        List<Employee> queryEmployee;
        System.out.print("Insert options :: ");   
        int option=Integer.parseInt(reader.readLine());
        
        switch(option){ 
            case 1:{
                System.out.println("Enter Name = ");
                name=Validator.verificationInputString(reader.readLine());
                queryEmployee = listEmployee.stream().filter(p-> p.getName().equals(name)).collect(Collectors.toList());
                viewSearchEmployee(queryEmployee);
            }break;
            case 2:{
                System.out.println("Enter Surname = ");
                surname=Validator.verificationInputString(reader.readLine());
                queryEmployee = listEmployee.stream().filter(p-> p.getName().equals(surname)).collect(Collectors.toList());
                viewSearchEmployee(queryEmployee); 
            };break;
            case 3:{
                System.out.println("Enter Age  = ");
                age=Validator.verificationInputInteger(reader.readLine());
                queryEmployee = listEmployee.stream().filter(p-> p.getName().equals(age)).collect(Collectors.toList()); 
                viewSearchEmployee(queryEmployee);
            };break;
            case 4:{
                System.out.println(" Enter Genre = ");
                genre = Validator.verificationInputGenre(reader.readLine());
                queryEmployee = listEmployee.stream().filter(p-> p.getName().equals(genre)).collect(Collectors.toList()); 
                viewSearchEmployee(queryEmployee); 
            }break; 
            case 5:{
                System.out.println(" Enter BirthDate =");
                birthDate = Validator.verificationInputBirthDate(reader.readLine());
                queryEmployee = listEmployee.stream().filter(p-> p.getName().equals(birthDate)).collect(Collectors.toList()); 
                viewSearchEmployee(queryEmployee);
            }break;
            case 6: menuEmployee(); break;
            case 7: exit(0); break;
            default :System.out.println("Error! choose please options 1-7 "); 
        }
                                                         
   }
  
    public void viewSearchEmployee(List<Employee> queryEmployee) throws IOException{   
        if(!queryEmployee.isEmpty()){    
            for(Employee employee: queryEmployee){    
                System.out.println(" Name = "+employee.getName()+" Surname = "+employee.getSurname()+" Age = "+employee.getAge()+" Genre = "+ employee.getGenre()+" BirthDate = "+ employee.getBirthdate()+" IdentityCode =  "+employee.getIdentityCode());              
            }   
        }else{
            System.out.println("Error! There is no employee ");  
            searchEmployee();    
        } 
    }
    
    
    @Override
    public void editEmployee() throws IOException{   
    menuEdit();
    System.out.print("Insert options :: ");   
    int option=Integer.parseInt(reader.readLine());
  
    System.out.println("Choose id Employee :: ");
    idEmployeeList=Validator.verificationInputInteger(reader.readLine()); 
  
    switch(option){
         case 1:{
         System.out.println("Enter new Name :: ");
         name=Validator.verificationInputString(reader.readLine());
         listEmployee.get(idEmployeeList-1).setName(name);
         menuEmployee();
         }break;
         case 2:{
         System.out.println("Enter new Surname :: ");
         surname=Validator.verificationInputString(reader.readLine());
         listEmployee.get(idEmployeeList-1).setSurname(surname);
         menuEmployee();
         };break;
         case 3:{  
         System.out.println("Enter new Age :: ");
         age=Validator.verificationInputInteger(reader.readLine());
         listEmployee.get(idEmployeeList-1).setAge(age);
         menuEmployee();
         };break;
         case 4: {
         System.out.println("Enter new IdentityCode :: ");
         identityCode=Validator.verificationInputInteger(reader.readLine());
         listEmployee.get(idEmployeeList-1).setIdentityCode(identityCode);    
         menuEmployee(); 
         }; break; 
         case 5:{ 
         System.out.println("Enter new Genre :: ");
         genre=Validator.verificationInputGenre(reader.readLine());
         listEmployee.get(idEmployeeList-1).setGenre(genre);
         menuEmployee();
         };break;
         case 6: { 
         System.out.println("Enter new BirthDate :: ");
         birthDate=Validator.verificationInputBirthDate(reader.readLine());
         listEmployee.get(idEmployeeList-1).setBirthdate(birthDate);
         menuEmployee();    
         }break;
         case 7:{
         this.menuEmployee();
         };break; 
         case 8:{System.exit(0);};break;
         default:{System.out.println("Error! choose please options 1-8");}
     }
  }

   public void menuEdit()throws IOException{
    System.out.println("\t\t\t\t\t**************|Menu Edit|***************");
    System.out.println("1.Edit Name ");
    System.out.println("2.Edit Surname ");
    System.out.println("3.Edit Age ");
    System.out.println("4.Edit IdentityCode ");
    System.out.println("5.Edit Genre ");
    System.out.println("6.Edit Birthdate ");
    System.out.println("7.Back to menu ");
    System.out.println("8.Exit ");
  }   
   
   public void menuSort()throws IOException{
    System.out.println("\t\t\t\t\t**************|Menu Sort|***************");
    System.out.println("1.Sort Name ");
    System.out.println("2.Sort Surname ");
    System.out.println("3.Sort Age ");
    System.out.println("4.Sort IdentityCode ");
    System.out.println("5.Back to menu ");
    System.out.println("6.Exit "); 
   }
   

    @Override
    public void deleteEmployee() throws IOException {
      System.out.println("\t\t\t\t\t**************|Delete Employee|*******");   
      System.out.println("Choose number Id Employee = ");
      idEmployeeList = Validator.verificationInputInteger(reader.readLine());
      listEmployee.remove(idEmployeeList-1);
      System.out.println(" Succes employee was deleted  !!! ");
      menuEmployee();
    }  

    @Override
    public void viewEmployee() throws IOException{
      
        if(listEmployee.isEmpty())
           System.out.println("INFORMATION: List is Empty  !!!   ");
        
       int number = 1;
       System.out.println("\t\t\t\t\t**************|View Employee|***************");
       for(Employee emp: listEmployee){
           System.out.println(" Nr = "+number++);
           System.out.println(" Name = "+ emp.getName());
           System.out.println(" Surname = "+ emp.getSurname());
           System.out.println(" Age= "+ emp.getAge());
           System.out.println(" IdentityCode = "+ emp.getIdentityCode());
           System.out.println(" Genre = "+ emp.getGenre());  
           System.out.println(" BirthDate = "+emp.getBirthdate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));  
           System.out.println("----------------------------------------");
       }
            menuEmployee();
    }

    @Override
    public void sortEmployee() throws IOException {
        menuSort(); 
         System.out.print("Insert options :: ");   
         int option=Integer.parseInt(reader.readLine());
         switch(option){
             case 1:{listEmployee.sort(new NameComparator());};break;
             case 2:{listEmployee.sort(new SurNameComparator());};break;
             case 3:{listEmployee.sort(new AgeComparator());};break; 
             case 4:{listEmployee.sort(new IdentityCodeComparator());};break;
             case 5:{sortEmployee();};break;
             case 6:{exit(0);};break;
             default:{System.out.println("Error! choose please options 1-6");}
        } 
    }

    public void menuEmployee() throws IOException {    
        System.out.println("\t\t\t\t\t**************|Menu|***************");
        System.out.println("1.Add Employee");
        System.out.println("2.Edit Employee");
        System.out.println("3.Delete Employee");
        System.out.println("4.View Employee");
        System.out.println("5.Sort Employee");
        System.out.println("6.Search Employee");
        System.out.println("7.Save all added Employees in database"); 
        System.out.println("8.Print all Employees for database");
        System.out.println("9.Search Employee for database");
        System.out.println("10.Edit Employee for database");
        System.out.println("11.Deleted Employee for database");
        System.out.println("12.Export Employees");
        System.out.println("13.Import Employees ");
        System.out.println("14.Exit(0)");
    
        System.out.print("Insert options = ");
        int option=Integer.parseInt(reader.readLine());
         switch(option){
             case 1:addEmployee();break;
             case 2:editEmployee();break;
             case 3:deleteEmployee();break;
             case 4:viewEmployee();break;
             case 5:sortEmployee();break; 
             case 6:searchEmployee();break;
             case 7:{employeeDao.addMultipleEmployee(listEmployee); menuEmployee();};break;
             case 8:{employeeDao.getAllEmployeeDb(); menuEmployee();};break;
             case 9:{employeeDao.searchEmployeeDb();menuEmployee();};break;
             case 10:{employeeDao.editEmployeeDb(); menuEmployee();}break;
             case 11:{employeeDao.deletedEmployeeDb(); menuEmployee();}break;
             case 12:{employeeDao.menuExport(listEmployee);menuEmployee();};break;
             case 13:{employeeDao.menuImport(listEmployee);menuEmployee();}
             default:System.out.println("Error! choose please options 1-8");
         }   
      
       }
    }
