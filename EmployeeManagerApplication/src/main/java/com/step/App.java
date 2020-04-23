package com.step;

import java.io.IOException;
import java.sql.SQLException;
import org.json.simple.parser.ParseException;


public class App {
    
    public static void main(String[]args) throws IOException, SQLException, ParseException{
        EmployeeManager em=new EmployeeManager();   
        em.menuEmployee(); 
    }
}
