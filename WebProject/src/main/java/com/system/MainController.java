package com.system;

import com.system.dao.EmployeeDao;
import com.system.model.Employee;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {   
  
        HttpSession session = req.getSession();
        EmployeeDao daoEmployee = new EmployeeDao();  
       
  
        List<Employee> listOfEmployees = daoEmployee.getAllEmployeeDb();
   
 
        session.setAttribute("emp", listOfEmployees);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
 
       
    
}
