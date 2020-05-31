package com.system.dao;

import com.system.enums.Genre;
import com.system.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/EmployeeManager"; 
    static final String USER = "postgres";
    static final String PASSWORD = "123";
    
    
    public EmployeeDao(){
        try{
        Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }

    }
    
   
private Connection initConnection(){
    
      try{
      System.out.println("Connecting  to database...");
      return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }catch(SQLException ex){
        System.out.println("Error! Can't connection to Database Employee !!! ".concat(ex.getMessage()));
        return null;
     }
   }     

public void addEmployeeDb(Employee em){       
   operationExecuteSQL("INSERT INTO manager.employee(name,surname,age,genre,birthdate,identitycode) VALUES('"+em.getName()+"','"+em.getSurname()+"',"+em.getAge()+",'"+em.getGenre()+"','"+em.getBirthdate()+"',"+em.getIdentityCode()+")");
}      


public boolean authorizationApp(String log,String pass){
  String sqlQuery = "SELECT * FROM manager.authorization where login ='"+log+"'and password ='"+pass+"'";  
  Connection conn = initConnection();  
  Statement st = null;   
  
     try{
        if(!conn.isClosed()&&conn!=null){
           st=conn.createStatement();
           ResultSet result = st.executeQuery(sqlQuery);  
          
           while(result.next()){  
           String login = result.getString("login");
           String password = result.getString("password");
                   
             if(password.contentEquals(pass)&&login.contentEquals(log))
                return true;        
           }
         } 
      }
           catch(SQLException ex){  
               System.out.println(ex.getMessage());
    }
     return false;
  }


//public void searchEmployeeDb() throws IOException{
//    System.out.println("\t\t\t\t\t**************| Menu Search Employee for Database|*******");
//    System.out.println("1. Search Name");
//    System.out.println("2. Search Surname");
//    System.out.println("3. Search Age");
//    System.out.println("4. Search Genre");
//    System.out.println("5. Search BrithDate");
//    System.out.println("6. Exit(0)");
//    
//   String sqlQuery=null;
//   int option = Integer.parseInt(reader.readLine());
//   switch(option){
//       case 1:{ 
//           System.out.println("Enter Name = "); 
//            name = Validator.verificationInputString(reader.readLine());
//            sqlQuery = "SELECT * FROM  manager.employee where name='"+name+"'";            
//       };break;
//       case 2:{ 
//           System.out.println("Enter Surname = ");
//           surname = Validator.verificationInputString(reader.readLine());
//           sqlQuery = "SELECT * FROM manager.employee where surname='"+surname+"'";      
//       };break;
//       case 3:{  
//           System.out.println(" Enter Age = ");
//           age = Validator.verificationInputInteger(reader.readLine());
//           sqlQuery = "SELECT * FROM manager.employee where age="+age;
//       };break;
//       case 4:{  
//           System.out.println("Enter Genre = ");
//           genre = Validator.verificationInputGenre(reader.readLine());
//           sqlQuery = "SELECT * FROM manager.employee where genre='"+genre+"'";           
//       };break;
//       case 5:{
//           System.out.println(" Enter BirthDate = ");
//           birthdate = Validator.verificationInputBirthDate(reader.readLine());
//           sqlQuery = "SELECT * FROM manager.employee where birthdate='"+birthdate+"'";
//       };break;
//       case 6: exit(0);break; 
//   }
//      //call method view all employee for database 
//      viewSearchEmployeeDb(sqlQuery,true);
//  
//}   
   


public List<Employee> getAllEmployeeDb(){  
  Connection conn = initConnection();  
  Statement st = null;   
  List<Employee> employeeList = new ArrayList();
  
     try{
        if(!conn.isClosed()&&conn!=null){
           st=conn.createStatement();
           ResultSet result = st.executeQuery("SELECT * FROM manager.employee");
         
          while(result.next()){ 
                                        //------this remove spaces----///
          int idEmployeeDb = result.getInt("id_employee"); 
          String name = result.getString("name").replaceAll("\\s+","");  
          String surname = result.getString("surname").replaceAll("\\s+","");
          Genre genre = Genre.valueOf(result.getString("genre").replaceAll("\\s+",""));
          int age = result.getInt("age");
          int identity = result.getInt("identitycode");
          LocalDate birthdate = result.getDate("birthdate").toLocalDate();
           //add date in arryList from database !!!  
          employeeList.add(new Employee(idEmployeeDb,name,surname,genre,age,identity,birthdate));
           }      
        }else   
           return null;
    }catch(SQLException ex){
     return null;
    }
          
     return employeeList;
   }














//public List<Employee> getAllEmployeeDb(){  
//  Connection conn = initConnection();  
//  Statement st = null;   
//  List<Employee> employeeList = new ArrayList();
//  
//     try{
//        if(!conn.isClosed()&&conn!=null){
//           st=conn.createStatement();
//           ResultSet result = st.executeQuery("SELECT * FROM manager.employee");
//          
//           while(result.next()){ 
//           employeeList.add(new Employee(result.getInt("id_employee"),result.getString("name"),result.getString("surname"),Genre.valueOf(result.getString("genre")),result.getInt("age"),result.getInt("identitycode"),result.getDate("birthdate").toLocalDate()));
//           }      
//        }else   
//           return null;
//    }catch(SQLException ex){
//     return null;
//    }
//          
//     return employeeList;
//   }

/*
public void editEmployeeDb() throws IOException{
    String sqlQuery = null;
    System.out.println("\t\t\t\t\t**************|Menu Edit from Database|***************");
    System.out.println("1.Edit Name ");
    System.out.println("2.Edit Surname ");
    System.out.println("3.Edit Age ");
    System.out.println("4.Edit IdentityCode ");
    System.out.println("5.Edit Genre ");
    System.out.println("6.Edit Birthdate ");
    System.out.println("7.Exit ");
    
    int option = Validator.verificationInputInteger(reader.readLine());
    System.out.println("Choose Nr Employee = ");
    idEmployeeDb=Validator.verificationInputInteger(reader.readLine());
     
    switch(option){
        case 1: {
            System.out.println("Enter new Name = ");
            name=Validator.verificationInputString(reader.readLine());
            sqlQuery="Update manager.employee set name = '"+name+"' where id_employee ="+idEmployeeDb;           
        };break;    
        
        case 2:{
            System.out.println("Enter new Surname = ");
            surname=Validator.verificationInputString(reader.readLine());
            sqlQuery="Update manager.employee set surname = '"+surname+"' where id_employee ="+idEmployeeDb;
        }break;
        case 3:{
            System.out.println("Enter new Age = ");
            age=Validator.verificationInputInteger(reader.readLine());
            sqlQuery="Update manager.employee set age = "+age+" where id_employee ="+idEmployeeDb;
        };break;
        case 4:{
            System.out.println(" Enter new IdentityCode = ");
            identity = Validator.verificationInputInteger(reader.readLine());
            sqlQuery="Update manager.employee set identitycode = "+identity+" where id_employee ="+idEmployeeDb;               
        };break;
        case 5:{
            System.out.println(" Enter new Genre = ");
            genre = Validator.verificationInputGenre(reader.readLine());
            sqlQuery ="Update manager.employee set name = '"+genre+"' where id_employee ="+idEmployeeDb;
        };break;
        case 6:{
             System.out.println("Enter new BirthDate = ");  
             birthdate = Validator.verificationInputBirthDate(reader.readLine());
             sqlQuery="Update manager.employee set birthdate = '"+birthdate+"' where id_employee ="+idEmployeeDb;
        };break;
        
        case 7: exit(0);break;
    }
     //send query in method 
     operationExecuteSQL(sqlQuery); 
}
*/    
public boolean operationExecuteSQL(String sqlQuery){
    Connection conn = initConnection();    
        Statement st=null;
        try {
            if (!conn.isClosed()&&conn!=null) {
                st = conn.createStatement();
                st.executeUpdate(sqlQuery);
                
               if(sqlQuery.contains("insert".toLowerCase())||sqlQuery.contains("insert".toUpperCase())||sqlQuery.contains("Insert")){
                  System.out.println("SUCCES: The employee was successfully added and saved to the database !!!");     
                  return true;
               }
               else if(sqlQuery.contains("delete".toLowerCase())||sqlQuery.contains("delete".toUpperCase())||sqlQuery.contains("Delete")){        
                   System.out.println("SUCCES: The employee was successfully delete to the database !!!");    
                   return true;
               }
               else if(sqlQuery.contains("update".toLowerCase())||sqlQuery.contains("update".toUpperCase())||sqlQuery.contains("Update")){
                   System.out.println("SUCCES: The employee was successfully update to the database");
                   return true;
               }
               
            } else  {
                System.out.println("ERROR: Operation failed, please repeat operation with database employee !!!");
            }
        } catch (SQLException ex) {
            System.out.println("ERROR! Operation failed  " + ex.getMessage());
        } finally {
            try {
                conn.close();
                st.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return false;
}

//public void deletedEmployeeDb() throws IOException{
//    System.out.println("\t\t\t\t\t**************|Deleted from Database|***************");
//    System.out.println("Choose Nr Employee to be deleted = ");
//    idEmployeeDb=Validator.verificationInputInteger(reader.readLine());          
//     //send query in method 
//     operationExecuteSQL("DELETE FROM manager.employee where id_employee ="+idEmployeeDb); 
//}
//

    
}
