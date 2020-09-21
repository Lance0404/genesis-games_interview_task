import java.util.List;

public class Driver {

    public static void main(String[] args){
        List<Employee> freshers = EmployeeFactory.createEmployees(EmployeeType.FRESHER, 3);
        Employee techLead = EmployeeFactory.createEmployee(EmployeeType.TECHLEAD);
        Employee productManager = EmployeeFactory.createEmployee(EmployeeType.PRODUCTMANAGER);



    }
}
