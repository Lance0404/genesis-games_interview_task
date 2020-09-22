package main;

import java.util.ArrayList;
import java.util.List;

public class EmployeeFactory {

    public static Employee createEmployee(EmployeeType employeeType){
        if (employeeType == EmployeeType.FRESHER){
            return new Fresher();
        } else if (employeeType == EmployeeType.TECHLEAD){
            return new TechLead();
        } else if (employeeType == EmployeeType.PRODUCTMANAGER){
            return new ProductManager();
        }
        return new Fresher();
    }

    public static List<Employee> createEmployees(EmployeeType employeeType, int count){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++){
            employees.add(createEmployee(employeeType));
        }
        return employees;
    }
}
