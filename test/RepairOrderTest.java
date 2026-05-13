package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.model.NoDiscount;
import se.kth.iv1350.repairshop.model.RepairOrder;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.integration.RegistryCreator;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;

import java.time.LocalDate;
import java.util.ArrayList;

public class RepairOrderTest {

    private RepairOrder testOrder;
    private RepairOrderRegistry testRegistry;

    @BeforeEach
    public void setUp() {
        // We now need to create a registry and pass it into the RepairOrder constructor
        RegistryCreator creator = new RegistryCreator();
        testRegistry = creator.getRepairOrderRegistry();
        testOrder = new RepairOrder(testRegistry);
    }

    @AfterEach
    public void tearDown() {
        testOrder = null;
        testRegistry = null;
    }

    @Test
    void testCalculateTotalWithMultipleTasks() {
        RepairTaskDTO task1 = new RepairTaskDTO("Fix tire", 250, 30);
        RepairTaskDTO task2 = new RepairTaskDTO("Replace chain", 150, 20);
        RepairTaskDTO task3 = new RepairTaskDTO("Adjust brakes", 100, 15);

        ArrayList<RepairTaskDTO> taskList = new ArrayList<>();
        taskList.add(task1);
        taskList.add(task2);
        taskList.add(task3);

        double result = testOrder.calculateTotal(taskList);

        assertEquals(500, result, "The calculated total cost was incorrect.");
    }

    @Test
    void testCalculateTotalWithEmptyList() {
        ArrayList<RepairTaskDTO> emptyList = new ArrayList<>();
        double result = testOrder.calculateTotal(emptyList);
        assertEquals(0, result, "An empty task list should cost 0.");
    }

    @Test
    void testAddDiagnosticReport() {
        System.out.println(">>> RUNNING TEST: addDiagnosticReport in RepairOrder <<<");

        // Create a dummy customer and order directly in the registry
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Test Name", 111222, "test@email.com", dummyBike);
        int targetOrderId = testRegistry.createRepairOrder(dummyCustomer, 20231027, "Flat tire");
        
        RepairTaskDTO dummyTask = new RepairTaskDTO("Change tire", 500, 30); 
        
        
        DiagnosticReportDTO updatedReport = testOrder.addDiagnosticReport(dummyTask, targetOrderId);
        
        
        assertNotNull(updatedReport, "The report should not be null.");
        assertEquals(1, updatedReport.getRepairTasksList().size(), "The report should contain exactly 1 task.");
        assertEquals("Change tire", updatedReport.getRepairTasksList().get(0).getDescription(), "Description did not match.");
    }

    @Test
    void testUpdateRepairOrder() {
        System.out.println(">>> RUNNING TEST: updateRepairOrder in RepairOrder <<<");

        // Create a dummy customer and order directly in the registry
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Update Tester", 333444, "update@email.com", dummyBike);
        int targetOrderId = testRegistry.createRepairOrder(dummyCustomer, 20231027, "Broken spokes");
        
        RepairTaskDTO dummyTask = new RepairTaskDTO("Fix spokes", 300, 45);
        DiagnosticReportDTO reportToSave = testOrder.addDiagnosticReport(dummyTask, targetOrderId);
        
        
        testOrder.updateRepairOrder(reportToSave, targetOrderId);
        
        // Fetch the order from the registry to ensure it was saved
        RepairOrderDTO updatedOrder = testRegistry.getById(targetOrderId);
        
        assertNotNull(updatedOrder.getReportDTO(), "The order should now have a diagnostic report attached.");
        assertEquals(1, updatedOrder.getReportDTO().getRepairTasksList().size(), "The attached report should have our task.");
    }
}