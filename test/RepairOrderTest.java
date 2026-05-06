package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.model.RepairOrder;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;

import java.util.List;
import java.util.ArrayList;

public class RepairOrderTest {

    private RepairOrder testOrder;

    @BeforeEach
    public void setUp() {
        testOrder = new RepairOrder();
    }

    @AfterEach
    public void tearDown() {
        testOrder = null;
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

        int result = testOrder.calculateTotal(taskList);

        assertEquals(500, result, "The calculated total cost was incorrect.");
    }

    @Test
    void testCalculateTotalWithEmptyList() {
        
        ArrayList<RepairTaskDTO> emptyList = new ArrayList<>();

        int result = testOrder.calculateTotal(emptyList);

        assertEquals(0, result, "An empty task list should cost 0.");
    }
}