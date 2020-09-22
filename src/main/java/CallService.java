import java.util.List;

public class CallService {

    private List<Employee> freshers;
    private Employee techLead;
    private Employee productManager;

    public boolean handleByFresher(){
        synchronized (freshers){
            for (Employee fresher: freshers) {
                if (fresher.isAvailability()){
                    System.out.println(fresher.toString() + " is available");
                    fresher.setAvailability(false);
                    if (handleByEmployee(fresher)){
                        fresher.setAvailability(true);
                        return true;
                    } else{
                        fresher.setAvailability(true);
                        return false;
                    }
                }
            }
            handleByTechLead();
            return false;
        }
    }

    private boolean handleByTechLead(){
        synchronized (techLead){
            if (techLead.isAvailability()){
                System.out.println(techLead.toString() + " is available");
                techLead.setAvailability(false);
                if (handleByEmployee(techLead)){
                    techLead.setAvailability(true);
                    return true;
                } else {
                    handleByProductManager();
                    techLead.setAvailability(true);
                    return false;
                }
            }
            handleByProductManager();
            return false;
        }
    }

    private boolean handleByProductManager(){
        synchronized (productManager) {
            if (productManager.isAvailability()){
                System.out.println(productManager.toString() + " is available");
                productManager.setAvailability(false);
                if (handleByEmployee(productManager)){
                    productManager.setAvailability(true);
                    return true;
                } else {
                    productManager.setAvailability(true);
                    System.err.println("PRODUCT MANAGER failed to handle the call");
                    return false;
                }
            }
            return false;
        }
    }

    private boolean handleByEmployee(Employee employee){
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
