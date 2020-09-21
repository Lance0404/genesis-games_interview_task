import java.util.List;

public class CallService {

    private List<Employee> freshers;
    private Employee techLead;
    private Employee productManager;

    public void handleByEmployeeType(EmployeeType employeeType) throws Exception{
        System.out.println("method handleByEmployeeType( " + employeeType + " ) is called");
        if (employeeType == EmployeeType.FRESHER){
            synchronized (freshers){
                for (Employee fresher: freshers) {
                    if (fresher.isAvailability()){
                        System.out.println(fresher.toString() + " is available");
                        fresher.setAvailability(false);
                        if (!handleByEmployee(fresher)){
                            handleByEmployeeType(EmployeeType.TECHLEAD);
                        }
                        fresher.setAvailability(true);
                        return;
                    }
                }
                handleByEmployeeType(EmployeeType.TECHLEAD);
            }
        } else if (employeeType == EmployeeType.TECHLEAD) {
            synchronized (techLead){
                if (techLead.isAvailability()){
                    System.out.println(techLead.toString() + " is available");
                    techLead.setAvailability(false);
                    if (!handleByEmployee(techLead)){
                        handleByEmployeeType(EmployeeType.PRODUCTMANAGER);
                    }
                    techLead.setAvailability(true);
                }
            }
        } else if (employeeType == EmployeeType.PRODUCTMANAGER) {
            synchronized (productManager) {
                if (productManager.isAvailability()){
                    System.out.println(productManager.toString() + " is available");
                    productManager.setAvailability(false);
                    if (!handleByEmployee(productManager)){
                        productManager.setAvailability(true);
                        throw new Exception("PRODUCT MANAGER failed to handle the call");
                    }
                    productManager.setAvailability(true);
                }
            }
        }

    }

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
