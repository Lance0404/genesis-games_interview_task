import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args){
        List<Employee> freshers = EmployeeFactory.createEmployees(EmployeeType.FRESHER, 3);
        Employee techLead = EmployeeFactory.createEmployee(EmployeeType.TECHLEAD);
        Employee productManager = EmployeeFactory.createEmployee(EmployeeType.PRODUCTMANAGER);

        CallService callService = new CallService();
        callService.setFreshers(freshers);
        callService.setTechLead(techLead);
        callService.setProductManager(productManager);

        List<Thread> threadsOfCalls = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Thread tmp = new Thread(() -> {
                try {
                    callService.handleByEmployeeType(EmployeeType.FRESHER);
                } catch (Exception ex) {
                    System.err.println(ex);
                }

            });
            threadsOfCalls.add(tmp);
        }

        for (Thread call: threadsOfCalls) {
            call.start();
            System.out.println(call + " started");
        }


    }
}
