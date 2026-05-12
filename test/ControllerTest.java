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

/**
 * Tests the {@link Controller} class.
 * Verifies that the controller correctly routes calls between the view layer
 * and the integration/model layers, and handles data transfers properly.
 */
public class ControllerTest {

    private Controller testController;

    /**
     * Sets up the test environment before each test method runs.
     * Initializes a fresh instance of the Controller with a new RegistryCreator and Printer
     * to ensure test isolation.
     */
    @BeforeEach
    public void setUp() {
        
        RegistryCreator creator = new RegistryCreator();
        Printer printer = new Printer();
        
        testController = new Controller(creator, printer);
    }

    /**
     * Cleans up the test environment after each test method completes.
     * Nullifies the controller instance to free up resources.
     */
    @AfterEach
    public void tearDown() {
        
        testController = null;
    }

    /**
     * Tests the retrieval of customer information using a phone number.
     * Verifies that the correct customer data is fetched from the registry.
     */
    @Test
    void testRetrieveCustomerInfo() throws Exception{
        System.out.println(">>> RUNNING TEST: retrieveCustomerInfo <<<");
        
        int phoneToSearch = 701234566; // Douglas Andersson
        CustomerDTO result = testController.retrieveCustomerInfo(phoneToSearch);

        assertNotNull(result, "Controller should have retrieved a customer, but got null.");
        assertEquals("Douglas Andersson", result.getName(), "Controller retrieved the wrong customer name.");
    }

    /**
     * Tests the creation of a new repair order.
     * Verifies that the order is successfully saved in the registry and returns a valid positive ID.
     */
    @Test
    void testCreateRepairOrder() {
        System.out.println(">>> RUNNING TEST: createRepairOrder <<<");

        
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Test Name", 123123, "test@email.com", dummyBike);
        
        
        int returnedId = testController.createRepairOrder(dummyCustomer, 20231027, "Flat tire");

        
        assertTrue(returnedId > 0, "The returned ID should be a valid positive number.");

        
        List<RepairOrderDTO> orders = testController.getByPhoneNum(123123);
        
        assertNotNull(orders, "The order list should not be null.");
        assertEquals(1, orders.size(), "There should be exactly 1 order in the registry.");
        assertEquals("Flat tire", orders.get(0).getRepairReport(), "The repair report description did not match.");
        
        
        assertEquals(returnedId, orders.get(0).getRepairId(), "The returned ID did not match the actual saved ID!");
    }

    /**
     * Tests fetching multiple repair orders associated with a single customer's phone number.
     * Verifies that the correct number of orders is returned in the list.
     */
    @Test
    void testGetByPhoneNum() {
        System.out.println(">>> RUNNING TEST: getByPhoneNum <<<");

        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        
        CustomerDTO repeatCustomer = new CustomerDTO("Janne", 555555, "janne@email.com", dummyBike);
        testController.createRepairOrder(repeatCustomer, 20231001, "Broken chain");
        testController.createRepairOrder(repeatCustomer, 20231005, "Squeaky brakes");

        
        List<RepairOrderDTO> orders = testController.getByPhoneNum(555555);

        
        assertNotNull(orders);
        assertEquals(2, orders.size(), "The controller should have found 2 orders for Janne.");
    }

    /**
     * Tests retrieving a list of repair orders filtered by their current state.
     * Verifies that the system correctly identifies and returns orders matching the requested state.
     */
    @Test
    void testRetrieveRepairOrderList() {
        System.out.println(">>> RUNNING TEST: retrieveRepairOrderList <<<");

        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        
        CustomerDTO dummyCustomer = new CustomerDTO("Bob", 999999, "bob@email.com", dummyBike);
        testController.createRepairOrder(dummyCustomer, 20231101, "Battery dead");

        
        List<RepairOrderDTO> pendingOrders = testController.retrieveRepairOrderList("Newly created"); // Change "Pending" if your default state is named differently!

        
        assertNotNull(pendingOrders);
        assertTrue(pendingOrders.size() > 0, "Should find at least one order in the starting state.");
    }

    /**
     * Tests adding a diagnostic repair task to an existing repair order.
     * Verifies that the task is successfully appended to the diagnostic report DTO.
     */
    @Test
    void testAddDiagnosticReport() {
        System.out.println(">>> RUNNING TEST: addDiagnosticReport <<<");

        
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Test Name", 111222, "test@email.com", dummyBike);
        testController.createRepairOrder(dummyCustomer, 20231027, "Flat tire");
        
        List<RepairOrderDTO> orders = testController.getByPhoneNum(111222);
        int targetOrderId = orders.get(0).getRepairId();
        
        
        RepairTaskDTO dummyTask = new RepairTaskDTO("Change tire", 500, 30); 
        
        
        DiagnosticReportDTO updatedReport = testController.addDiagnosticReport(dummyTask, targetOrderId);
        
        
        assertNotNull(updatedReport, "The report should not be null.");
        assertEquals(1, updatedReport.getRepairTasksList().size(), "The report should contain exactly 1 task.");
    }

    /**
     * Tests updating a repair order with a finalized diagnostic report.
     * Verifies that the new diagnostic details are successfully saved to the database.
     */
    @Test
    void testUpdateRepairOrder() {
        System.out.println(">>> RUNNING TEST: updateRepairOrder <<<");

        
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Update Tester", 333444, "update@email.com", dummyBike);
        testController.createRepairOrder(dummyCustomer, 20231027, "Broken spokes");
        
        List<RepairOrderDTO> orders = testController.getByPhoneNum(333444);
        int targetOrderId = orders.get(0).getRepairId();
        
        
        RepairTaskDTO dummyTask = new RepairTaskDTO("Fix spokes", 300, 45);
        DiagnosticReportDTO reportToSave = testController.addDiagnosticReport(dummyTask, targetOrderId);
        
        
        testController.updateRepairOrder(reportToSave, targetOrderId);
        
        
        List<RepairOrderDTO> updatedOrders = testController.getByPhoneNum(333444);
        RepairOrderDTO updatedOrder = updatedOrders.get(0);
        
        assertNotNull(updatedOrder.getReportDTO(), "The order should now have a diagnostic report attached.");
        assertEquals(1, updatedOrder.getReportDTO().getRepairTasksList().size(), "The attached report should have our task.");
    }

    /**
     * Tests the system's ability to handle a customer's response (accept or reject).
     * Verifies that the repair order's state is correctly updated based on the response.
     */
    @Test
    void testCustomerResponse() {
        System.out.println(">>> RUNNING TEST: customerResponse <<<");

        
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Response Tester", 888999, "response@email.com", dummyBike);
        testController.createRepairOrder(dummyCustomer, 20231027, "Motor dead");
        
        List<RepairOrderDTO> orders = testController.getByPhoneNum(888999);
        RepairOrderDTO orderToAccept = orders.get(0);
        
        
        testController.customerResponse(true, orderToAccept);
        
        
        List<RepairOrderDTO> updatedOrders = testController.getByPhoneNum(888999);
        RepairOrderDTO updatedOrder = updatedOrders.get(0);
        
        
        assertNotEquals("Newly created", updatedOrder.getState(), "The state should have changed after customer accepted.");
    }

    /**
     * Tests fetching a specific repair order using its unique ID.
     * Verifies that the exact matching order and its contents are returned.
     */
    @Test
    void testGetById() {
        System.out.println(">>> RUNNING TEST: getById <<<");

        
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("ID Fetch Tester", 998877, "fetch@email.com", dummyBike);
        
        int expectedId = testController.createRepairOrder(dummyCustomer, 20231120, "Gears are skipping");

        
        RepairOrderDTO fetchedOrder = testController.getById(expectedId);

        
        assertNotNull(fetchedOrder, "Controller should have found the order, but returned null.");
        assertEquals(expectedId, fetchedOrder.getRepairId(), "The fetched order has the wrong ID.");
        assertEquals("Gears are skipping", fetchedOrder.getRepairReport(), "The fetched order has the wrong repair report.");
        assertEquals("ID Fetch Tester", fetchedOrder.getCustomer().getName(), "The fetched order belongs to the wrong customer.");
    }
}