import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;

public class CallServiceTest {
    private CallService callService;

    @Before
    public void setup(){
        List<Employee> freshers = EmployeeFactory.createEmployees(EmployeeType.FRESHER, 3);
        Employee techLead = EmployeeFactory.createEmployee(EmployeeType.TECHLEAD);
        Employee productManager = EmployeeFactory.createEmployee(EmployeeType.PRODUCTMANAGER);

        callService = new CallService();
        callService.setFreshers(freshers);
        callService.setTechLead(techLead);
        callService.setProductManager(productManager);
    }

    @After
    public void tearDown(){
        callService = null;
    }

    @Test
    public void testHandleByFresher(){
        Boolean ret = callService.handleByFresher();
        assertFalse(ret);
//        Mockito.reset();
    }
}
