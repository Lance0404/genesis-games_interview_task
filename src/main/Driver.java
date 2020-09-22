package main;

import java.util.ArrayList;
import java.util.List;

public class Driver {
    final static int THREAD_NUM = 10;

    public static void main(String[] args){
        List<Employee> freshers = EmployeeFactory.createEmployees(EmployeeType.FRESHER, 3);
        Employee techLead = EmployeeFactory.createEmployee(EmployeeType.TECHLEAD);
        Employee productManager = EmployeeFactory.createEmployee(EmployeeType.PRODUCTMANAGER);

        CallService callService = new CallService();
        callService.setFreshers(freshers);
        callService.setTechLead(techLead);
        callService.setProductManager(productManager);

        List<Thread> threadsOfCalls = new ArrayList<>();

//        prepare threads of jobs
        for (int i = 0; i < THREAD_NUM; i++) {
            Thread tmp = new Thread(() -> {
                    callService.handleByFresher();
            });
            threadsOfCalls.add(tmp);
        }

//        start threads
        for (Thread call: threadsOfCalls) {
            call.start();
            System.out.println(call + " started");
        }


    }
}
