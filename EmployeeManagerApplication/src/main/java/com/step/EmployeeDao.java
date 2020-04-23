package com.step;

import com.step.comparator.NameComparator;
import com.step.enums.Genre;
import com.step.model.Employee;
import com.step.validator.Validator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import static java.lang.System.exit;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;




public class EmployeeDao {   
   
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private  String name,surname;
  private  int identity,age,idEmployeeDb;
  private Genre genre;
  private LocalDate birthdate;

private Connection initConnection(){
    String url = "jdbc:postgresql://127.0.0.1:5432/EmployeeManager"; 
    String user = "postgres";
    String password="123";
    try{
      System.out.println("Connecting  to database...");
      return DriverManager.getConnection(url, user, password);
    }catch(SQLException ex){
        System.out.println("Error! Can't connection to Database Employee !!! ".concat(ex.getMessage()));
        return null;
     }
   }     

public void addEmployeeDb(Employee em){       
   operationExecuteSQL("INSERT INTO manager.employee(name,surname,age,genre,birthdate,identitycode) VALUES('"+em.getName()+"','"+em.getSurname()+"',"+em.getAge()+",'"+em.getGenre()+"','"+em.getBirthdate()+"',"+em.getIdentityCode()+")");
}      

public void getAllEmployeeDb(){ 
    viewSearchEmployeeDb("SELECT * FROM manager.employee",true);
}




public void addMultipleEmployee(List<Employee> listEmployee){
   Connection conn = initConnection();
   PreparedStatement ps = null;
   String sqlQuery="INSERT INTO manager.employee(name,surname,age,genre,birthdate,identitycode) VALUES(?,?,?,?,?,?)";
   
   try{
   if(!conn.isClosed()&&conn!=null){
   ps = conn.prepareStatement(sqlQuery);
   for(Employee emp: listEmployee){       
        ps.setString(1, emp.getName());
        ps.setString(2, emp.getSurname());
        ps.setInt(3, emp.getAge());
        ps.setString(4, emp.getGenre().toString());
        ps.setDate(5, java.sql.Date.valueOf(emp.getBirthdate()));
        ps.setInt(6, emp.getIdentityCode());
        ps.addBatch();
        ps.executeUpdate();
   }
   
   System.out.println("SUCCES: The employees was successfully added and saved to the database !!!"); 
  //Clear storage local employee !!!
   listEmployee.clear();
   }else 
   System.out.println("ERROR: Please repeat operation, if again error please contact your programmer !!!");
   }catch(SQLException ex){    
       System.out.println("ERROR: Please contact your progrmmer ->> error is "+ ex.getMessage());
   }
   finally{
       try{
       ps.close();
       conn.close();
       }catch(SQLException ex){
         System.out.println("ERROR: Please contact your progrmmer ->> error is "+ ex.getMessage());
       }   
   }
}


void searchEmployeeDb() throws IOException{
    System.out.println("\t\t\t\t\t**************| Menu Search Employee for Database|*******");
    System.out.println("1. Search Name");
    System.out.println("2. Search Surname");
    System.out.println("3. Search Age");
    System.out.println("4. Search Genre");
    System.out.println("5. Search BrithDate");
    System.out.println("6. Exit(0)");
    
   String sqlQuery=null;
   int option = Integer.parseInt(reader.readLine());
   switch(option){
       case 1:{ 
           System.out.println("Enter Name = "); 
            name = Validator.verificationInputString(reader.readLine());
            sqlQuery = "SELECT * FROM  manager.employee where name='"+name+"'";            
       };break;
       case 2:{ 
           System.out.println("Enter Surname = ");
           surname = Validator.verificationInputString(reader.readLine());
           sqlQuery = "SELECT * FROM manager.employee where surname='"+surname+"'";      
       };break;
       case 3:{  
           System.out.println(" Enter Age = ");
           age = Validator.verificationInputInteger(reader.readLine());
           sqlQuery = "SELECT * FROM manager.employee where age="+age;
       };break;
       case 4:{  
           System.out.println("Enter Genre = ");
           genre = Validator.verificationInputGenre(reader.readLine());
           sqlQuery = "SELECT * FROM manager.employee where genre='"+genre+"'";           
       };break;
       case 5:{
           System.out.println(" Enter BirthDate = ");
           birthdate = Validator.verificationInputBirthDate(reader.readLine());
           sqlQuery = "SELECT * FROM manager.employee where birthdate='"+birthdate+"'";
       };break;
       case 6: exit(0);break; 
   }
      //call method view all employee for database 
      viewSearchEmployeeDb(sqlQuery,true);
  
}   
   

public List<Employee> viewSearchEmployeeDb(String sqlQuery, boolean showApp){  
  Connection conn = initConnection();  
  Statement st = null;   
  List<Employee> employeeList = new ArrayList();
  
     try{
        if(!conn.isClosed()&&conn!=null){
           st=conn.createStatement();
           ResultSet result = st.executeQuery(sqlQuery);
          
           while(result.next()){ 
           idEmployeeDb = result.getInt("id_employee"); 
           name = result.getString("name").replaceAll("\\s+","");  
           surname = result.getString("surname").replaceAll("\\s+","");
           genre = Genre.valueOf(result.getString("genre").replaceAll("\\s+",""));
           age = result.getInt("age");
           identity = result.getInt("identitycode");
           birthdate = result.getDate("birthdate").toLocalDate();
           //add date in arryList from database !!!  
           employeeList.add(new Employee(idEmployeeDb,name,surname,genre,age,identity,birthdate));
           
           }      
        }else
            System.out.println("Error: Can't possible connection to database, please repeat operation, if again error please contact your programmer !!! ");
        
    }catch(SQLException ex){
     System.out.println("ERROR: Please contact your progrmmer ->> error is "+ ex.getMessage());
    }
     
     if(showApp==true){
     System.out.println("-----------------------------------------------------------------------------------------------");
     System.out.println("|| Nr.||     Name     ||        Surname      || Age || Genre ||  BirthDate  || IdentityCode ||");
     employeeList.forEach((em) -> {
         System.out.println("-----------------------------------------------------------------------------------------------");
         System.out.println("|| "+em.getIdEmployeeDb()+". ||    "+em.getName()+"     ||        "+em.getSurname()+"     ||   "+em.getAge()+"    ||   "+em.getGenre()+"   ||   "+em.getBirthdate()+"  ||  "+em.getIdentityCode()+" || ");
        });
     }
         
     return employeeList;
   }


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
    
void operationExecuteSQL(String sqlQuery){
    Connection conn = initConnection();    
        Statement st=null;
        try {
            if (!conn.isClosed()&&conn!=null) {
                st = conn.createStatement();
                st.executeUpdate(sqlQuery);
                
               if(sqlQuery.contains("insert".toLowerCase())||sqlQuery.contains("insert".toUpperCase())||sqlQuery.contains("Insert")){
                  System.out.println("SUCCES: The employee was successfully added and saved to the database !!!");     
               }
               else if(sqlQuery.contains("delete".toLowerCase())||sqlQuery.contains("delete".toUpperCase())||sqlQuery.contains("Delete")){        
                   System.out.println("SUCCES: The employee was successfully delete to the database !!!");    
               }
               else if(sqlQuery.contains("update".toLowerCase())||sqlQuery.contains("update".toUpperCase())||sqlQuery.contains("Update")){
                   System.out.println("SUCCES: The employee was successfully update to the database");
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
}

public void deletedEmployeeDb() throws IOException{
    System.out.println("\t\t\t\t\t**************|Deleted from Database|***************");
    System.out.println("Choose Nr Employee to be deleted = ");
    idEmployeeDb=Validator.verificationInputInteger(reader.readLine());          
     //send query in method 
     operationExecuteSQL("DELETE manager.employee where id_employee ="+idEmployeeDb); 
}


public void menuExport(List<Employee> listEmployee) throws IOException{     
     System.out.println("  \t\t\t\t\t**************|Export Employees|***************");
     
     boolean workDatabase = false,fileText = false, fileXml = false, fileJson = false;
     System.out.println("**********|Menu Export|**********");
     System.out.println("1. Export from database to file");
     System.out.println("2. Export from application to file");
     
        switch (Validator.verificationInputInteger(reader.readLine())) {
        case 1:workDatabase = true; break;  
        case 2:workDatabase = false; break;
        default:{
            System.out.println("Unknown option, please select point 1-2");
             menuExport(listEmployee);
       }
     
      }

        System.out.println("Select format to export file ? ");
        System.out.println("1. TEXT (.txt)  file  ");
        System.out.println("2. XML  (.xml)  file ");     
        System.out.println("3. JSON (.json) file ");   
     
        switch (Validator.verificationInputInteger(reader.readLine())) {
        case 1:fileText = true; break;
        case 2:fileXml =  true; break;
        case 3:fileJson = true; break;
        default: { 
            System.out.println("Unknown option, please select point 1-3");
             menuExport(listEmployee);
        }
      }
        
         if (workDatabase==true&&fileText==true)
                    createNewFileTextExportDb();
         if (workDatabase==true&&fileXml==true) 
                    createNewFileXmlExportDb();
         if  (workDatabase==true&&fileJson==true)
                    createNewFileJsonExportDb();
       
         if (workDatabase!=true&&fileText==true)
                 createNewFileTextExportApp(listEmployee);
         if (workDatabase!=true&&fileXml==true) 
                 createNewFileXmlExportApp(listEmployee);
         if  (workDatabase!=true&&fileJson==true)
                 createNewFileJsonExportApp(listEmployee);
         
    }

    
private void createNewFileTextExportDb() throws IOException{
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
    
        System.out.println(" Enter please path to save file = ");
        String pathFile= Validator.verificationInputString(reader.readLine());
       
        String getDate = null;
        PrintWriter writer1 = null;
       
        File txtFile = new File(pathFile.concat("\\").concat(nameFile).concat(".txt"));
    
        if (txtFile.exists()){
            System.out.println("There is already such a file with such a name, please change name and repeat operation !!!");
            createNewFileTextExportDb();
        }
        else{

        try{
        FileWriter fileWriter = 
        new FileWriter(new File(pathFile+"\\"+nameFile.concat(".txt")), true);
        writer1 = new PrintWriter(fileWriter);
       
        
        List<Employee> employeeList = viewSearchEmployeeDb("SELECT * FROM manager.employee",false);
        employeeList.sort(new NameComparator());
        
        
        if(employeeList.isEmpty())
            System.out.println("Error! Not found date from database ");
        else { 
        //writer1.write("|| NR ||    NAME    ||    SURNAME    || AGE || GENRE ||   BIRTHDATE   || IDENTITYCODE ||");
        //writer1.append("\n----------------------------------------------------------------------------------------");
       
        for(Employee em: employeeList)
        writer1.append(em.getName()+","+em.getSurname()+","+em.getAge()+","+em.getGenre()+","+em.getBirthdate()+","+em.getIdentityCode()+"\n");         
        
        writer1.flush();
        writer1.close();   
            System.out.println(" Export successfully!!!");
      }
      
        }catch(IOException ex){
            System.out.println("Error: Invalid file path  is not posible export employee to file -->> ("+ex.getMessage().toUpperCase()+")"); 
        }
    }
}


    private void createNewFileXmlExportDb() throws IOException {
        
        List<Employee> listEmployee = viewSearchEmployeeDb("SELECT * FROM manager.employee",false);
     
        if (listEmployee.isEmpty())
        System.out.println("Error:  Not found data, sorry is not possible generate file .xml ");    
           
        if(!listEmployee.isEmpty()){
        Element parentElementXml = new Element("Employees");
        Document myDocumentXml = new Document(parentElementXml);
       
        for(Employee em: listEmployee){
            Element childrenElementXml = new Element("employee");
           
            Element nameXml = new Element("name");
            nameXml.setText(em.getName());
            childrenElementXml.addContent(nameXml);
            
            Element surnameXml = new Element("surname");
            surnameXml.setText(em.getSurname());
            childrenElementXml.addContent(surnameXml);

            Element ageXml = new Element("age");
            ageXml.setText(String.valueOf(em.getAge()));
            childrenElementXml.addContent(ageXml);

            Element genreXml = new Element("genre");
            genreXml.setText(em.getGenre().toString());
            childrenElementXml.addContent(genreXml);

            Element birthdateXml =  new Element("birthdate");
            birthdateXml.setText(em.getBirthdate().toString());
            childrenElementXml.addContent(birthdateXml);

            Element identityXml = new Element("identitycode");
            identityXml.setText(String.valueOf(em.getIdentityCode()));
            childrenElementXml.addContent(identityXml);
            
            parentElementXml.addContent(childrenElementXml);
        }
        
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
      
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
    
        System.out.println(" Enter please path to save file = ");
        String pathFile= Validator.verificationInputString(reader.readLine());   
        
        File xmlFile = new File(pathFile+"\\".concat(nameFile).concat(".xml"));
        
        if(xmlFile.exists()){
          System.out.println("There is already such a file with such a name, please change name and repeat operation !!! ");
          createNewFileXmlExportDb();
        }
        else{
           
        try {
                xmlOutput.output(myDocumentXml, new FileOutputStream(pathFile+"\\".concat(nameFile).concat(".xml")));
            }
            catch (FileNotFoundException ex) {
                System.out.println("Error: Path is incorrect, please repeat operation !!!");
                  createNewFileXmlExportDb();
            }  
        }
             
      }   
   }       
          
    private void createNewFileJsonExportDb() throws IOException {
        
        List<Employee> employees = viewSearchEmployeeDb("SELECT * FROM manager.employee",false);   
        
        if(!employees.isEmpty()){
        JSONArray list = new JSONArray();
           for (Employee emp : employees) {
            JSONObject obj = new JSONObject();
            obj.put("name", emp.getName());
            obj.put("surname",emp.getSurname());
            obj.put("age",emp.getAge());
            obj.put("genre",emp.getGenre());
            obj.put("birthdate",emp.getBirthdate());
            obj.put("identitycode",emp.getIdentityCode());
            list.add(obj);
        }
       
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
    
        System.out.println(" Enter please path to save file = ");
        String pathFile= Validator.verificationInputString(reader.readLine());
       
        File fileJson =  new File(pathFile.concat("\\").concat(nameFile).concat(".json")); 
        
        if (fileJson.exists()) {
                System.out.println("There is already such a file with such a name, please change name and repeat operation !!!");
                createNewFileJsonExportDb();
        }
        else{
                   
        try (FileWriter file = new FileWriter(pathFile+"\\"+nameFile.concat(".json"))) {
            file.write(list.toJSONString());
        } catch (IOException e) {
            System.out.println("Could not write to JSON. Reason: " + e.getMessage());
        }
           }
        }
        
        else{
            System.out.println("Error: Not found data, please repeat or insert date in Database !!!");
            createNewFileJsonExportDb();
      }
  }
 
    private void createNewFileTextExportApp(List<Employee> listEmployee) throws IOException {
   
        while(true){
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
    
        System.out.println(" Enter please path to save file = ");
        String pathFile= Validator.verificationInputString(reader.readLine());
       
        String getDate = null;
        PrintWriter writer1 = null;
        File txtFile = new File(pathFile.concat("\\").concat(nameFile).concat(".txt"));
        
        if(txtFile.exists()){
        System.out.println("There is already such a file with such a name, please change name and repeat operation !!!");  
        }
        
        try{
        FileWriter fileWriter = 
        new FileWriter(new File(pathFile+"\\"+nameFile.concat(".txt")), true);
        writer1 = new PrintWriter(fileWriter);
        if(listEmployee.isEmpty())
            System.out.println("Error! Not found local date from application !!! ");
        else { 
       // writer1.write("|| NR ||    NAME    ||    SURNAME    || AGE || GENRE ||   BIRTHDATE   || IDENTITYCODE ||");
        //writer1.append("\n----------------------------------------------------------------------------------------");
        for(Employee em: listEmployee)
        writer1.append("\n   "+em.getIdEmployeeDb()+1+",\t  "+em.getName()+",\t  "+em.getSurname()+",\t"+em.getAge()+",\t"+em.getGenre()+",\t "+em.getBirthdate()+",\t    "+em.getIdentityCode());         
        writer1.flush();
        writer1.close(); 
            System.out.println(" Export successfully!!!");
        break;
      }

        }catch(IOException ex){
            System.out.println("Error: Invalid file path  is not posible export employee to file -->> ("+ex.getMessage().toUpperCase()+")"); 
        }
        
    }
 }      
   
    private void createNewFileXmlExportApp(List<Employee>listEmployee) throws IOException {
       
        if (listEmployee.isEmpty())
        System.out.println("Error:  Not found data, sorry is not possible generate file .xml ");    
           
        if(!listEmployee.isEmpty()){
        Element parentElementXml = new Element("Employees");
        Document myDocumentXml = new Document(parentElementXml);
       
        for(Employee em: listEmployee){
            Element childrenElementXml = new Element("employee");
           
            Element nameXml = new Element("name");
            nameXml.setText(em.getName());
            childrenElementXml.addContent(nameXml);
            
            Element surnameXml = new Element("surname");
            surnameXml.setText(em.getSurname());
            childrenElementXml.addContent(surnameXml);

            Element ageXml = new Element("age");
            ageXml.setText(String.valueOf(em.getAge()));
            childrenElementXml.addContent(ageXml);

            Element genreXml = new Element("genre");
            genreXml.setText(em.getGenre().toString());
            childrenElementXml.addContent(genreXml);

            Element birthdateXml =  new Element("birthdate");
            birthdateXml.setText(em.getBirthdate().toString());
            childrenElementXml.addContent(birthdateXml);

            Element identityXml = new Element("identitycode");
            identityXml.setText(String.valueOf(em.getIdentityCode()));
            childrenElementXml.addContent(identityXml);
            
            parentElementXml.addContent(childrenElementXml);
        }
        
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
      
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
    
        System.out.println(" Enter please path to save file = ");
        String pathFile= Validator.verificationInputString(reader.readLine());   
        
        File xmlFile = new File(pathFile+"\\".concat(nameFile).concat(".xml"));
        
        if(xmlFile.exists()){
          System.out.println("There is already such a file with such a name, please change name and repeat operation !!! ");
          createNewFileXmlExportDb();
        }
        else{
           
        try {
                xmlOutput.output(myDocumentXml, new FileOutputStream(pathFile+"\\".concat(nameFile).concat(".xml")));
            }
            catch (FileNotFoundException ex) {
                System.out.println("Error: Path is incorrect, please repeat operation !!!");
                  createNewFileXmlExportDb();
            }  
        }
             
      }   
        
  }

    private void createNewFileJsonExportApp(List<Employee>listEmployee) throws IOException {
        if(!listEmployee.isEmpty()){
        JSONArray list = new JSONArray();
           for (Employee emp : listEmployee) {
            JSONObject obj = new JSONObject();
            obj.put("name", emp.getName());
            obj.put("surname",emp.getSurname());
            obj.put("age",emp.getAge());
            obj.put("genre",emp.getGenre());
            obj.put("birthdate",emp.getBirthdate());
            obj.put("identitycode",emp.getIdentityCode());
            list.add(obj);
        }
       
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
    
        System.out.println(" Enter please path to save file = ");
        String pathFile= Validator.verificationInputString(reader.readLine());
       
        File fileJson =  new File(pathFile.concat("\\").concat(nameFile).concat(".json")); 
        
        if (fileJson.exists()) {
                System.out.println("There is already such a file with such a name, please change name and repeat operation !!!");
                createNewFileJsonExportDb();
        }
        else{
                   
        try (FileWriter file = new FileWriter(pathFile+"\\"+nameFile.concat(".json"))) {
            file.write(list.toJSONString());
        } catch (IOException e) {
            System.out.println("Could not write to JSON. Reason: " + e.getMessage());
        }
           }
        }
        
        else{
            System.out.println("Error: Not found data, please repeat or insert date in Database !!!");
      }
           
       }

    void menuImport(List<Employee> listEmployee) throws IOException {
        
     System.out.println("  \t\t\t\t\t**************|Import Employees|***************");
     
     boolean workDatabase = false,fileText = false, fileXml = false, fileJson = false;
     System.out.println("**********|Menu Import|**********");
     System.out.println("1. Import from file to database");
     System.out.println("2. Import from file to application");
     
        switch (Validator.verificationInputInteger(reader.readLine())) {
        case 1:workDatabase = true; break;  
        case 2:workDatabase = false; break;
        default:{
            System.out.println("Unknown option, please select point 1-2");

       }
     
      }
        System.out.println("Select format to import from file to database ? ");
        System.out.println("1. TEXT (.txt)  file  ");
        System.out.println("2. XML  (.xml)  file ");     
        System.out.println("3. JSON (.json) file ");   
     
        switch (Validator.verificationInputInteger(reader.readLine())) {
        case 1:fileText = true; break;
        case 2:fileXml =  true; break;
        case 3:fileJson = true; break;
        default: { 
            System.out.println("Unknown option, please select point 1-3");
           
        }
      }
        
         if (workDatabase==true&&fileText==true)
               importEmployeeTextFileDb(listEmployee);
         if (workDatabase==true&&fileXml==true) 
               importEmployeeXmlFileDb();
         if (workDatabase==true&&fileJson==true)
               importEmployeeJsonFileDb();
       
         if (workDatabase!=true&&fileText==true)
               importEmployeeTextFileApp(listEmployee);
         if (workDatabase!=true&&fileXml==true) 
               importEmployeeXmlFileApp(listEmployee);
         if (workDatabase!=true&&fileJson==true)
               importEmployeeJsonFileApp(listEmployee);
         
        
    }
    
    public List<Employee> importEmployeeTextFileApp(List<Employee>listEmployee) throws IOException {  
   
        String [] arr = null; 
        System.out.println("  \t\t\t\t\t**************| Import Employees from file to application |***************");
    
        System.out.println(" Enter please path where is file = "); 
        String pathFile = Validator.verificationInputString(reader.readLine());
        
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
                
        try{
        List<String> fileLines = Files.readAllLines(Paths.get(pathFile+"\\"+nameFile.concat(".txt")));
        listEmployee.clear();
        
        for(String line:fileLines){
        arr = line.split(",");  
        name = arr[0];
        surname = arr[1];
        age = Integer.parseInt(arr[2]);
        genre = Genre.valueOf(arr[3]);
        birthdate = LocalDate.parse(arr[4]);
        identity = Integer.parseInt(arr[5]);
        listEmployee.add(new Employee(name,surname,age,identity,genre,birthdate)); 
        }
        }
        catch(IOException ex){
            System.out.println("\"Error: Invalid file path  is not posible import employee from file to application -->> ("+ex.getMessage().toUpperCase()+")");
        }
           
        return listEmployee;
    }

    private void importEmployeeTextFileDb(List<Employee> listEmployee) {
         try{
         addMultipleEmployee(importEmployeeTextFileApp(listEmployee));
         }
         catch(Exception e){
            System.out.println("Error:".concat(e.getStackTrace().toString()));     
        }
    }

    
    private void importEmployeeXmlFileDb() throws IOException {
       
        System.out.println(" Enter please path where is file = "); 
        String pathFile = Validator.verificationInputString(reader.readLine());
        
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
        
        File inputFile = new File(pathFile.concat(nameFile).concat(".xml"));
        
        SAXBuilder saxBuilder = new SAXBuilder(); 
       
        Document document = null;
        try{
        document = saxBuilder.build(inputFile);
        }
        catch(JDOMException e){
            System.out.println(" Error: ".concat(e.getStackTrace().toString()));
        }
     
        Element root = document.getRootElement();
        
        List<Element> employees = root.getChildren("employee");
        List<Employee> listEmployee =  new ArrayList();
        Element employee,name,surname,age,genre,birthdate,identity;    
        
        for (int index = 0; index < employees.size(); index++) {
            employee = employees.get(index);
            name = employee.getChild("name");
            surname = employee.getChild("surname");
            age = employee.getChild("age");
            genre = employee.getChild("genre");
            birthdate = employee.getChild("birthdate");
            identity = employee.getChild("identitycode");    
            String nameValue = name.getText();
            String surnameValue = surname.getText();
            int ageValue = Integer.parseInt(age.getText());
            Genre genreValue = Genre.valueOf(genre.getText());
            LocalDate birthdateValue = LocalDate.parse(birthdate.getText());
            int identityValue = Integer.parseInt(age.getText());
            listEmployee.add(new Employee(nameValue,surnameValue,ageValue,identityValue,genreValue,birthdateValue));
        }
  
     addMultipleEmployee(listEmployee);
    
    }
    
    private void importEmployeeJsonFileDb() throws IOException {
      
        List<Employee> employeeList =  new ArrayList();

        System.out.println(" Enter please path where is file = "); 
        String pathFile = Validator.verificationInputString(reader.readLine());
        
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
        
        try (Reader reader = new FileReader(pathFile.concat(nameFile).concat("\\").concat(".json"))){
            
            JSONParser parser = new JSONParser(); 
            JSONArray jsonArr = (JSONArray)parser.parse(reader);

            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject employeeJson = (JSONObject) jsonArr.get(i);
                name = employeeJson.get("name").toString();
                surname = employeeJson.get("surname").toString();
                age = Integer.parseInt(employeeJson.get("age").toString());
                genre = Genre.valueOf(employeeJson.get("genre").toString());
                identity = Integer.parseInt(employeeJson.get("identitycode").toString());
                birthdate = LocalDate.parse(employeeJson.get("birthdate").toString());
                employeeList.add(new Employee(name,surname,age,identity,genre,birthdate)); 
            }
          
            //call method insert employee in database 
            addMultipleEmployee(employeeList);
            
        } catch (IOException e) {
            System.out.println("Could not read JSON. Reason: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Could not parse JSON. Reason: " + e.getMessage());
     }
    
    }

    private void importEmployeeXmlFileApp(List<Employee> listEmployee) throws IOException {
       
        System.out.println(" Enter please path where is file = "); 
        String pathFile = Validator.verificationInputString(reader.readLine());
        
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
        
        File inputFile = new File(pathFile.concat("\\").concat(nameFile).concat(".xml"));
        
        SAXBuilder saxBuilder = new SAXBuilder(); 
       
        Document document = null;
        try{
        document = saxBuilder.build(inputFile);
        }
        catch(JDOMException e){
            System.out.println(" Error: ".concat(e.getStackTrace().toString()));
        }
     
        listEmployee.clear();
        Element root = document.getRootElement();
        
        List<Element> employees = root.getChildren("employee");
        
        Element employee,name,surname,age,genre,birthdate,identity;    
        
        for (int index = 0; index < employees.size(); index++) {
            employee = employees.get(index);
            name = employee.getChild("name");
            surname = employee.getChild("surname");
            age = employee.getChild("age");
            genre = employee.getChild("genre");
            birthdate = employee.getChild("birthdate");
            identity = employee.getChild("identitycode");    
            String nameValue = name.getText();
            String surnameValue = surname.getText();
            int ageValue = Integer.parseInt(age.getText());
            Genre genreValue = Genre.valueOf(genre.getText());
            LocalDate birthdateValue = LocalDate.parse(birthdate.getText());
            int identityValue = Integer.parseInt(age.getText());
            listEmployee.add(new Employee(nameValue,surnameValue,ageValue,identityValue,genreValue,birthdateValue));
        }
            
    }

    private void importEmployeeJsonFileApp(List<Employee> listEmployee) throws IOException {
   
        System.out.println(" Enter please path where is file = "); 
        String pathFile = Validator.verificationInputString(reader.readLine());
        
        System.out.println(" Enter please file name = ");
        String nameFile = Validator.verificationInputString(reader.readLine());
        
        
           try (Reader reader = new FileReader(pathFile.concat(nameFile).concat("\\").concat(".json"))){
            listEmployee.clear();
            JSONParser parser = new JSONParser(); 
            JSONArray jsonArr = (JSONArray)parser.parse(reader);

            for (int i = 0; i < jsonArr.size(); i++) {
                JSONObject employeeJson = (JSONObject) jsonArr.get(i);
                name = employeeJson.get("name").toString();
                surname = employeeJson.get("surname").toString();
                age = Integer.parseInt(employeeJson.get("age").toString());
                genre = Genre.valueOf(employeeJson.get("genre").toString());
                identity = Integer.parseInt(employeeJson.get("identitycode").toString());
                birthdate = LocalDate.parse(employeeJson.get("birthdate").toString());
                listEmployee.add(new Employee(name,surname,age,identity,genre,birthdate)); 
            }
               
        } catch (IOException e) {
            System.out.println("Could not read JSON. Reason: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Could not parse JSON. Reason: " + e.getMessage());
      }
    
     }
}

    

   










