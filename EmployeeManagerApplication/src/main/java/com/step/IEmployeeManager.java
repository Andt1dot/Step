package com.step;
import java.io.IOException;

interface IEmployeeManager  {
    void addEmployee() throws IOException;  
    void editEmployee() throws IOException;
    void deleteEmployee() throws IOException;
    void viewEmployee() throws IOException;
    void sortEmployee() throws IOException;
    void searchEmployee() throws IOException;
}
