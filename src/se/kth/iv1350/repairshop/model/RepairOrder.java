package se.kth.iv1350.repairshop.model;

import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;
import se.kth.iv1350.repairshop.model.DiscountStrategy;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a repair order in the repair shop system.
 * Manages the calculation and handling of repair tasks and their associated costs.
 */
public class RepairOrder {

    private RepairOrderRegistry repairOrderRegistry;
    private DiscountStrategy strategy;
    

    public RepairOrder(RepairOrderRegistry repairOrderRegistry){
        this.repairOrderRegistry = repairOrderRegistry;
    }

    /**
     * Calculates the total cost of all repair tasks in the order.
     * 
     * @param cost An array of RepairTaskDTO objects representing the tasks to be performed.
     * @return The total cost as an integer, representing the sum of all task costs.
     */
    public double calculateTotal(ArrayList<RepairTaskDTO> cost) {
        double totalCost = 0;
        // Iterate through each repair task
        for (RepairTaskDTO task : cost) {
            // Add the cost of the current task to the running total
            totalCost += task.getCost();
        }

        totalCost = strategy.addSeasonalDiscount(totalCost);
        return totalCost;

    }

    public DiagnosticReportDTO addDiagnosticReport(RepairTaskDTO repairTask, int repairOrderId){
        
        // Fetch the correct data using the current ID
        RepairOrderDTO orderDTO = this.repairOrderRegistry.getById(repairOrderId);
       
        // Extract the diagnostic report
        DiagnosticReportDTO existingReportDTO = orderDTO.getReportDTO();

        // Build the object
        DiagnosticReport reportModel = new DiagnosticReport(existingReportDTO);

        // Call add to list
        reportModel.addToList(repairTask);

        // Get the updated list
        ArrayList<RepairTaskDTO> currentTasks = reportModel.getRepairTasksList();

        int totalTime = reportModel.calculateTotalTime(currentTasks);

        DiagnosticReportDTO updatedReportDTO = new DiagnosticReportDTO(currentTasks, totalTime);

        return updatedReportDTO;

    }

    public void updateRepairOrder(DiagnosticReportDTO reportDTO, int repairOrderId){
        // 1. Fetch the outdated order from the database
        RepairOrderDTO oldOrder = this.repairOrderRegistry.getById(repairOrderId);
        
        // 2. Create a brand-new DTO. We copy all the old details,
        // except the DiagnosticReportDTO (that is updated).
        RepairOrderDTO updatedOrder = new RepairOrderDTO(
            reportDTO,
            oldOrder.getDate(),
            oldOrder.getTotalCost(), 
            oldOrder.getRepairReport(),
            oldOrder.getState(),
            oldOrder.getCustomer(),
            oldOrder.getRepairId()
        );
        
        // Update the database
        this.repairOrderRegistry.updateRepairOrderDiagnostic(updatedOrder);
    }
}