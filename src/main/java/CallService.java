import java.util.List;

public class CallService {

    private List<Employee> freshers;
    private Employee techLead;
    private Employee productManager;
    private CallServiceUtil callServiceUtil;

    CallService(List<Employee> freshers, Employee techLead, Employee productManager, CallServiceUtil callServiceUtil){
        this.freshers = freshers;
        this.techLead = techLead;
        this.productManager = productManager;
        this.callServiceUtil = callServiceUtil;
    }

    public boolean handleByFresher(){
        synchronized (freshers){
            for (Employee fresher: freshers) {
                if (fresher.isAvailability()){
                    System.out.println(fresher.toString() + " is available");
                    fresher.setAvailability(false);
                    if (callServiceUtil.handleByEmployee(fresher)){
                        fresher.setAvailability(true);
                        return true;
                    } else {
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
                if (callServiceUtil.handleByEmployee(techLead)){
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
                if (callServiceUtil.handleByEmployee(productManager)){
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
