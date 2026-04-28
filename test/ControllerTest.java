package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.integration.RegistryCreator;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.integration.Printer;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;

import java.util.List;

public class ControllerTest {

    private Controller testController;

    @BeforeEach
    public void setUp() {
        // ARRANGE: To test the Controller, we must give it the tools it needs in its constructor.
        RegistryCreator creator = new RegistryCreator();
        Printer printer = new Printer();
        
        testController = new Controller(creator, printer);
    }

    @AfterEach
    public void tearDown() {
        // Clean up after every test
        testController = null;
    }

    @Test
    void testRetrieveCustomerInfo() {
        System.out.println(">>> RUNNING TEST: retrieveCustomerInfo <<<");
        // ACT: Search for the hardcoded customer we put in the CustomerRegistry earlier!
        int phoneToSearch = 701234566; // Douglas Andersson
        CustomerDTO result = testController.retrieveCustomerInfo(phoneToSearch);

        // ASSERT: Verify the Controller successfully asked the registry and got the answer back
        assertNotNull(result, "Controller should have retrieved a customer, but got null.");
        assertEquals("Douglas Andersson", result.getName(), "Controller retrieved the wrong customer name.");
    }

    @Test
    void testCreateRepairOrder() {
        System.out.println(">>> RUNNING TEST: createRepairOrder <<<");

        // ARRANGE: Create a dummy customer
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        //String name, int phoneNum, String email, BikeDTO bikeDTO
        CustomerDTO dummyCustomer = new CustomerDTO("Test Name", 123123, "test@email.com", dummyBike);
        
        // ACT: Tell the controller to create the order
        testController.createRepairOrder(dummyCustomer, 20231027, "Flat tire");

        // ASSERT: To prove it worked, we ask the controller to fetch orders for this phone number.
        // It should return a list with exactly 1 item in it!
        List<RepairOrderDTO> orders = testController.getByPhoneNum(123123);
        
        assertNotNull(orders, "The order list should not be null.");
        assertEquals(1, orders.size(), "There should be exactly 1 order in the registry for this customer.");
        assertEquals("Flat tire", orders.get(0).getRepairReport(), "The repair report description did not match.");
    }

    @Test
    void testGetByPhoneNum() {
        System.out.println(">>> RUNNING TEST: getByPhoneNum <<<");

        System.out.println(">>> RUNNING TEST: getByPhoneNum <<<");
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        // ARRANGE: Create two orders for the same person
        CustomerDTO repeatCustomer = new CustomerDTO("Janne", 555555, "janne@email.com", dummyBike);
        testController.createRepairOrder(repeatCustomer, 20231001, "Broken chain");
        testController.createRepairOrder(repeatCustomer, 20231005, "Squeaky brakes");

        // ACT: Fetch the list
        List<RepairOrderDTO> orders = testController.getByPhoneNum(555555);

        // ASSERT: We should get exactly 2 orders back
        assertNotNull(orders);
        assertEquals(2, orders.size(), "The controller should have found 2 orders for Jane.");
    }

    @Test
    void testRetrieveRepairOrderList() {
        System.out.println(">>> RUNNING TEST: retrieveRepairOrderList <<<");

        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        // ARRANGE: Create an order. (Assuming default state is "Pending" or similar when created)
        CustomerDTO dummyCustomer = new CustomerDTO("Bob", 999999, "bob@email.com", dummyBike);
        testController.createRepairOrder(dummyCustomer, 20231101, "Battery dead");

        // ACT: Search by whatever state your system defaults to when creating an order
        List<RepairOrderDTO> pendingOrders = testController.retrieveRepairOrderList("Newly created"); // Change "Pending" if your default state is named differently!

        // ASSERT: Ensure the list is not empty
        assertNotNull(pendingOrders);
        assertTrue(pendingOrders.size() > 0, "Should find at least one order in the starting state.");
    }

    // --- The complex DTO tests below ---
    // You can fill these in next once your basic tests above are running green!

    @Test
    void testAddDiagnosticReport() {
        System.out.println(">>> RUNNING TEST: addDiagnosticReport <<<");

        // ARRANGE: Create an order and fetch it to get its ID
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Test Name", 111222, "test@email.com", dummyBike);
        testController.createRepairOrder(dummyCustomer, 20231027, "Flat tire");
        
        List<RepairOrderDTO> orders = testController.getByPhoneNum(111222);
        int targetOrderId = orders.get(0).getRepairId(); // Grab the ID!
        
        // Create a dummy task (Assuming it takes: Description, Cost, Time)
        RepairTaskDTO dummyTask = new RepairTaskDTO("Change tire", 500, 30); 
        
        // ACT: Add the report
        DiagnosticReportDTO updatedReport = testController.addDiagnosticReport(dummyTask, targetOrderId);
        
        // ASSERT: Verify the report was created and contains our task
        assertNotNull(updatedReport, "The report should not be null.");
        assertEquals(1, updatedReport.getRepairTasksList().size(), "The report should contain exactly 1 task.");
    }

    @Test
    void testUpdateRepairOrder() {
        System.out.println(">>> RUNNING TEST: updateRepairOrder <<<");

        // ARRANGE: Create an order and fetch it to get its ID
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Update Tester", 333444, "update@email.com", dummyBike);
        testController.createRepairOrder(dummyCustomer, 20231027, "Broken spokes");
        
        List<RepairOrderDTO> orders = testController.getByPhoneNum(333444);
        int targetOrderId = orders.get(0).getRepairId();
        
        // Create a dummy task and get a valid DiagnosticReportDTO using our Controller method
        RepairTaskDTO dummyTask = new RepairTaskDTO("Fix spokes", 300, 45);
        DiagnosticReportDTO reportToSave = testController.addDiagnosticReport(dummyTask, targetOrderId);
        
        // ACT: Save it to the database!
        testController.updateRepairOrder(reportToSave, targetOrderId);
        
        // ASSERT: Fetch the order completely fresh from the database and check if the report is attached
        List<RepairOrderDTO> updatedOrders = testController.getByPhoneNum(333444);
        RepairOrderDTO updatedOrder = updatedOrders.get(0);
        
        assertNotNull(updatedOrder.getReportDTO(), "The order should now have a diagnostic report attached.");
        assertEquals(1, updatedOrder.getReportDTO().getRepairTasksList().size(), "The attached report should have our task.");
    }

    @Test
    void testCustomerResponse() {
        System.out.println(">>> RUNNING TEST: customerResponse <<<");

        // ARRANGE: Create an order and fetch it so we have the specific DTO
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Response Tester", 888999, "response@email.com", dummyBike);
        testController.createRepairOrder(dummyCustomer, 20231027, "Motor dead");
        
        List<RepairOrderDTO> orders = testController.getByPhoneNum(888999);
        RepairOrderDTO orderToAccept = orders.get(0);
        
        // ACT: The customer accepts the repair!
        testController.customerResponse(true, orderToAccept);
        
        // ASSERT: Fetch the order fresh from the database and see if the state changed
        List<RepairOrderDTO> updatedOrders = testController.getByPhoneNum(888999);
        RepairOrderDTO updatedOrder = updatedOrders.get(0);
        
        // We assert that the state is no longer "Newly created" (or whatever your default is)
        assertNotEquals("Newly created", updatedOrder.getState(), "The state should have changed after customer accepted.");
    }
}