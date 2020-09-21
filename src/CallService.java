import java.util.List;

public class CallService {

    private List<Employee> freshers;
    private Employee techLead;
    private Employee productManager;

    public void handle(EmployeeType employeeType){
        if (employeeType == EmployeeType.FRESHER){
            synchronized (freshers){
                for (Employee fresher: freshers) {
                    if (fresher.isAvalibility()){
                        fresher.setAvalibility(false);
                        if (!handle(fresher)){ handle(techLead);}
                        fresher.setAvalibility(true);
                    }
                }
                handle(techLead);
            }
        } else if (employeeType == EmployeeType.TECHLEAD) {
            synchronized (techLead){
                if (techLead.isAvalibility()){
                    techLead.setAvalibility(false);
                    if (!handle(techLead)){
                        handle(productManager);
                    }
                    techLead.setAvalibility(true);
                }
            }
        } else if (employeeType == EmployeeType.PRODUCTMANAGER) {
            synchronized (productManager) {
                if (productManager.isAvalibility()){
                    productManager.setAvalibility(false);
                    handle(productManager);
                    productManager.setAvalibility(true);
                }
            }
        }

    }

    public boolean handle(Employee employee){
//        System.out.println("call handled by " + employee.toString());
        if (getRandomIntBetweenRange(1,6) == 1) {
            System.out.println("call handled by " + employee.toString() + " succeeded");
            return true;
        } else {
            System.out.println("call handled by " + employee.toString() + " failed");
            return false;
        }
    }

    private static int getRandomIntBetweenRange(int min, int max){
        return (int)(Math.random()*((max-min)+1))+min;
    }

    public void setFreshers(List<Employee> freshers) {
        this.freshers = freshers;
    }

    public void setTechLead(Employee techLead) {
        this.techLead = techLead;
    }

    public void setProductManager(Employee productManager) {
        this.productManager = productManager;
    }
}
