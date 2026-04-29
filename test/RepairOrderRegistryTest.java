package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;

import java.util.ArrayList; 

public class RepairOrderRegistryTest {

    private RepairOrderRegistry repairOrderRegistry;
    private CustomerDTO firstTestCustomer;
    private CustomerDTO secondTestCustomer;

    @BeforeEach
    public void setUp() {

        repairOrderRegistry = new RepairOrderRegistry();

    }

    @AfterEach
    public void tearDown(){
        repairOrderRegistry = null;
    }
    
    @Test
    public void testCreateRepairOrder() {
        System.out.println("<<<<<<<<<< Test for createRepairOrder() >>>>>>>>>>");

        int firstRepairOrderId = repairOrderRegistry.createRepairOrder(firstTestCustomer, 20260427, "Broken battery");
        int secondRepairOrderId = repairOrderRegistry.createRepairOrder(secondTestCustomer, 20260428, "Loud rattling noise");

        assertEquals(4, firstRepairOrderId);
        assertEquals(5, secondRepairOrderId);

        RepairOrderDTO firstRepairOrder = repairOrderRegistry.getById(firstRepairOrderId);
        RepairOrderDTO secondRepairOrder = repairOrderRegistry.getById(secondRepairOrderId);

        assertNotNull(firstRepairOrder, "First repair order not found");
        assertNotNull(secondRepairOrder, "Second repair order not found");

    }

    @Test
    public void testGetById() {
        System.out.println("<<<<<<<<<< Test for getById() >>>>>>>>>>");

        RepairOrderDTO firstRepairOrder = repairOrderRegistry.getById(1);
        RepairOrderDTO secondRepairOrder = repairOrderRegistry.getById(2);

        assertNotNull(firstRepairOrder, "First repair order not found");
        assertNotNull(secondRepairOrder, "Second repair order not found");

    }

    @Test
    public void testGetByPhoneNum() {

        System.out.println("<<<<<<<<<< Test for getByPhoneNum() >>>>>>>>>>");

        int phoneNum = 701234567;
        ArrayList<RepairOrderDTO> testRepairOrderList = new ArrayList<>();
       
        CustomerDTO testCustomer = new CustomerDTO("Jane Doe", phoneNum, "bla",null);
        RepairOrderDTO originalRepairReport = new RepairOrderDTO(null, 20260428, 0, "bla","Newly created", testCustomer, 1);

        testRepairOrderList.add(originalRepairReport);

        repairOrderRegistry.getByPhoneNum(phoneNum);

        assertFalse(testRepairOrderList.isEmpty(), "List empty; list not created properly");
        assertEquals(1, testRepairOrderList.size(), "Only one repair order associated with this phone number");

        assertEquals(phoneNum, testRepairOrderList.get(0).getCustomer().getPhoneNum(), "Phone number does not match customer");

    }

    @Test
    public void testRetrieveRepairOrderList() {

        System.out.println("<<<<<<<<<< Test for retrieveRepairOrderList() >>>>>>>>>>");

        
        ArrayList<RepairOrderDTO> testStateListRepairOrders = repairOrderRegistry.retrieveRepairOrderList("Newly created");

        assertFalse(testStateListRepairOrders.isEmpty(), "List empty, list not created properly");
        assertEquals(2, testStateListRepairOrders.size(), "Two repair orders with 'Newly created' state");

        assertEquals("Newly created", testStateListRepairOrders.get(0).getState(), "State does not match; wrong state");

    }

    @Test
    public void testUpdateRepairOrderDiagnostic() {

        System.out.println("<<<<<<<<<< Test for updateRepairOrderDiagnostic() >>>>>>>>>>");

        int id = repairOrderRegistry.createRepairOrder(firstTestCustomer, 20260428, "Bike won't start");
        RepairOrderDTO originalRepairOrder = repairOrderRegistry.getById(id);

        ArrayList<RepairTaskDTO> tasks = new ArrayList<>();
        tasks.add(new RepairTaskDTO("Change battery", 1000, 60));
        DiagnosticReportDTO testDiagnosticReport = new DiagnosticReportDTO(tasks, 60);

        RepairOrderDTO repairOrderWithDiagnostic = new RepairOrderDTO(testDiagnosticReport, originalRepairOrder.getDate(), 1000, originalRepairOrder.getRepairReport(), "Ready for approval", firstTestCustomer, id);

        repairOrderRegistry.updateRepairOrderDiagnostic(repairOrderWithDiagnostic);

        RepairOrderDTO updatedRepairOrder = repairOrderRegistry.getById(id);

        assertEquals(testDiagnosticReport, updatedRepairOrder.getReportDTO(), "Diagnostic report did not update");
        assertNotNull(updatedRepairOrder,"The updated repair order should exist");
    }

    @Test
    public void testUpdateRepairOrderState() {

        System.out.println("<<<<<<<<<< Test for updateRepairOrderState() >>>>>>>>>>");

        int id = repairOrderRegistry.createRepairOrder(firstTestCustomer, 20260428, "Bike won't start");
        RepairOrderDTO originalRepairOrder = repairOrderRegistry.getById(id);

        repairOrderRegistry.updateRepairOrderState(originalRepairOrder, "Accepted");

        RepairOrderDTO updatedRepairOrder = repairOrderRegistry.getById(id);

        assertEquals("Accepted", updatedRepairOrder.getState(), "State did not update");


    }
}
