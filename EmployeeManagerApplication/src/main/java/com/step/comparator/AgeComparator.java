package com.step.comparator;

import com.step.model.Employee;
import java.util.Comparator;

public class AgeComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee em1, Employee em2) {
       if(em1.getAge()==em2.getAge())
           return 0;
       else if(em1.getAge()>em2.getAge())
           return 1;
       else
           return -1;
    }       
}
