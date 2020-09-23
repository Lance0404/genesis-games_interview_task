import callService.CallService;
import callService.CallServiceUtil;
import employee.Employee;
import employee.EmployeeFactory;
import employee.EmployeeType;
import employee.Fresher;
import employee.ProductManager;
import employee.TechLead;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;

public class CallServiceTest {
    private CallService callService;

    private List<Employee> freshers;
    private Employee techLead;
    private Employee productManager;

    private CallServiceUtil callServiceUtil = Mockito.mock(CallServiceUtil.class);

    @Before
    public void setup(){
        freshers = EmployeeFactory.createEmployees(EmployeeType.FRESHER, 3);
        techLead = EmployeeFactory.createEmployee(EmployeeType.TECHLEAD);
        productManager = EmployeeFactory.createEmployee(EmployeeType.PRODUCTMANAGER);

        callService = new CallService(freshers, techLead, productManager, callServiceUtil);
    }

    @After
    public void tearDown(){
        callService = null;
    }

    @Test
    public void testHandleByFresherSuccessfully(){
        // given
        Mockito.when(callServiceUtil.handleByEmployee(Mockito.any(Fresher.class))).thenReturn(true);

        // when
        boolean result = callService.handleByFresher();

        // then
        Mockito.verify(callServiceUtil, Mockito.times(1)).handleByEmployee(Mockito.eq(freshers.get(0)));
        Mockito.verify(callServiceUtil, Mockito.times(0)).handleByEmployee(Mockito.eq(techLead));
        assertTrue(result);
    }

    @Test
    public void testHandleByFresherFailedTechLeadSucceeded(){

        // given
        Mockito.when(callServiceUtil.handleByEmployee(Mockito.any(Fresher.class))).thenReturn(false);
        Mockito.when(callServiceUtil.handleByEmployee(Mockito.any(TechLead.class))).thenReturn(true);

        // when
        boolean result = callService.handleByFresher();

        // then
        Mockito.verify(callServiceUtil, Mockito.times(1)).handleByEmployee(Mockito.eq(freshers.get(0)));
        Mockito.verify(callServiceUtil, Mockito.times(1)).handleByEmployee(Mockito.any(TechLead.class));
        assertTrue(result);
    }

    @Test
    public void testHandleByFresherFailedTechLeadFailedProductManagerSucceeded(){

        // given
        Mockito.when(callServiceUtil.handleByEmployee(Mockito.any(Fresher.class))).thenReturn(false);
        Mockito.when(callServiceUtil.handleByEmployee(Mockito.any(TechLead.class))).thenReturn(false);
        Mockito.when(callServiceUtil.handleByEmployee(Mockito.any(ProductManager.class))).thenReturn(true);

        // when
        boolean result = callService.handleByFresher();

        // then
        Mockito.verify(callServiceUtil, Mockito.times(1)).handleByEmployee(Mockito.eq(freshers.get(0)));
        Mockito.verify(callServiceUtil, Mockito.times(1)).handleByEmployee(Mockito.any(TechLead.class));
        Mockito.verify(callServiceUtil, Mockito.times(1)).handleByEmployee(Mockito.any(ProductManager.class));
        assertTrue(result);
    }
}
