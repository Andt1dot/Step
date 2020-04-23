package com.step.comparator;

import com.step.model.Employee;
import java.util.Comparator;

public class SurNameComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee em1, Employee em2) {
       return em1.getSurname().compareTo(em2.getSurname());
   }

}
