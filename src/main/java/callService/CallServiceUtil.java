package callService;

import employee.Employee;

public class CallServiceUtil {

    public boolean handleByEmployee(Employee employee){
//        System.out.println("call handled by " + employee.toString());
        if (getRandomIntBetweenRange(1,6) == 1) {
            System.out.println("call handled by " + employee.toString() + " succeeded");
            return true;
        } else {
            System.out.println("call handled by " + employee.toString() + " failed");
            return false;
        }
    }

    private int getRandomIntBetweenRange(int min, int max){
        int randomValue = (int)(Math.random()*((max-min)+1))+min;
        System.out.println("randomValue " + randomValue);
        return randomValue;
    }
}
