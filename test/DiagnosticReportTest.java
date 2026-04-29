package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import java.util.ArrayList;
import se.kth.iv1350.repairshop.model.DiagnosticReport;

public class DiagnosticReportTest {
    private DiagnosticReport instance;

    @BeforeEach
    public void setUp() {
        instance = new DiagnosticReport(null);
    }

     @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testConstructorWithNullDTO() {
        assertNotNull(instance.getRepairTasksList(), "Task list should be initialized even if DTO is null.");
        assertTrue(instance.getRepairTasksList().isEmpty(), "Task list should be empty initially.");
    }

    @Test
    public void testConstructorWithExistingTasks() {
        ArrayList<RepairTaskDTO> existingTasks = new ArrayList<>();
        existingTasks.add(new RepairTaskDTO("Oil Change", 500, 30));
        
        DiagnosticReportDTO dto = new DiagnosticReportDTO(existingTasks, 30);
        
        DiagnosticReport reportWithExisting = new DiagnosticReport(dto);
        
        assertEquals(1, reportWithExisting.getRepairTasksList().size(), "Should load existing tasks from DTO.");
        assertEquals("Oil Change", reportWithExisting.getRepairTasksList().get(0).getDescription());
    }

    @Test
    public void testAddToList() {
        RepairTaskDTO task = new RepairTaskDTO("Brake Check", 200, 45);
        instance.addToList(task);
        
        ArrayList<RepairTaskDTO> list = instance.getRepairTasksList();
        assertTrue(list.contains(task), "The added task should be present in the list.");
        assertEquals(1, list.size(), "List size should increment after adding a task.");
    }

    @Test
    public void testCalculateTotalTime() {
        ArrayList<RepairTaskDTO> tasksToCalculate = new ArrayList<>();
        tasksToCalculate.add(new RepairTaskDTO("Task 1", 100, 20));
        tasksToCalculate.add(new RepairTaskDTO("Task 2", 200, 40));
        tasksToCalculate.add(new RepairTaskDTO("Task 3", 50, 15));
        
        int expectedTotal = 20 + 40 + 15;
        int actualTotal = instance.calculateTotalTime(tasksToCalculate);
        
        assertEquals(expectedTotal, actualTotal, "Total time calculated is incorrect.");
    }

    @Test
    public void testCalculateTotalTimeEmptyList() {
        ArrayList<RepairTaskDTO> emptyList = new ArrayList<>();
        int total = instance.calculateTotalTime(emptyList);
        assertEquals(0, total, "Total time for an empty list should be 0.");
    }
}