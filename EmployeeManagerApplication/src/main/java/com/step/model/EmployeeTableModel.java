
package com.step.model;

import com.step.EmployeeDao;
import com.step.comparator.AgeComparator;
import com.step.comparator.NameComparator;
import com.step.comparator.SurNameComparator;
import com.step.enums.Genre;
import com.step.validator.Validator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;



public class EmployeeTableModel extends AbstractTableModel implements Validator {

    private final String [] columnName;
    private List <Employee> employeeList;
    EmployeeDao empDao;
    
    
    public EmployeeTableModel() {
        this.columnName = new String[]{"Nr.","Name","Surname","Age","Genre","BirthDate","IdentityCode"};
        this.employeeList= new ArrayList();
        this.empDao = new EmployeeDao();
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return this.columnName[columnIndex];      
    }
    
    @Override
    public int getRowCount() {
        return employeeList.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee em = this.employeeList.get(rowIndex);
        switch (columnIndex) {
            case 0: return em.getIdEmployeeDb();
            case 1: return em.getName();
            case 2: return em.getSurname();
            case 3: return em.getAge();
            case 4: return em.getGenre();
            case 5: return em.getBirthdate();
            case 6: return em.getIdentityCode();
            default:
                throw new IllegalArgumentException(" Error: Unknow column  !!!");
        }
    }
       
 public void importfromDataBase(){
   employeeList.clear();
   employeeList = empDao.viewSearchEmployeeDb("SELECT * FROM manager.employee", false);
   fireTableDataChanged();
 }       
   

  public void addEmployeeDb(String name, String surname, int age, int identityCode, Genre genre,LocalDate birthdate){
     empDao.addEmployeeDb(new Employee(name,surname,age,identityCode,genre,birthdate));
     importfromDataBase();
   }
  
  public void searchEmployeeDb(String name){
      employeeList = empDao.viewSearchEmployeeDb("SELECT * FROM manager.employee where name like '"+name+"%%'", false);
      fireTableDataChanged();
  }
  
  public void deletedEmployeeDb(int indexEmployee){
    empDao.operationExecuteSQL("DELETE FROM manager.employee where id_employee ="+indexEmployee);
    this.importfromDataBase();                               
}
  
  
 public boolean editEmployeeDb(Employee e){ 
   boolean isAffected = empDao.operationExecuteSQL("UPDATE manager.employee set name = '"+e.getName()+"', surname = '"+e.getSurname()+"', genre = '"+e.getGenre()+"', identitycode ="+e.getIdentityCode()+", birthdate = '"+e.getBirthdate()+"' where id_employee = "+e.getIdEmployeeDb());
   if (isAffected){
       this.importfromDataBase();
       return true;
   }
     else
        return false;
} 
 
 
 public void sortByCategory(int category){
     switch (category) {
         case 1: employeeList.sort(new NameComparator()); break;
         case 2: employeeList.sort(new SurNameComparator()); break;
         case 3: employeeList.sort(new AgeComparator()); break;
         default:
             System.out.println(" Error: Unknown category sort !!! ");
     }
     fireTableDataChanged();
 }
 
  
}

    
    

    
    
    
