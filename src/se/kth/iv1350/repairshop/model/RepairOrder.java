package se.kth.iv1350.repairshop.model;

import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a repair order in the repair shop system.
 * Manages the calculation and handling of repair tasks and their associated costs.
 */
public class RepairOrder {
    
    /**
     * Calculates the total cost of all repair tasks in the order.
     * 
     * @param cost An array of RepairTaskDTO objects representing the tasks to be performed.
     * @return The total cost as an integer, representing the sum of all task costs.
     */
    public int calculateTotal(ArrayList<RepairTaskDTO> cost) {
        int totalCost = 0;
        // Iterate through each repair task
        for (RepairTaskDTO task : cost) {
            // Add the cost of the current task to the running total
            totalCost += task.getCost();
        }
        return totalCost;
    }
}