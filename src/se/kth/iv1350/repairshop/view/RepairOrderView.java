package se.kth.iv1350.repairshop.view;

import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.integration.RepairOrderObserver;

/**
 * Observer implementation that displays repair order updates to System.out.
 * This view informs technicians and receptionists about updates to repair orders
 * without them having to ask the system.
 */
public class RepairOrderView implements RepairOrderObserver {
    
    @Override
    public void repairOrderCreated(RepairOrderDTO repairOrder) {
        System.out.println("\n========== NEW REPAIR ORDER CREATED ==========");
        printRepairOrder(repairOrder);
        System.out.println("=============================================\n");
    }
    
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrder) {
        System.out.println("\n========== REPAIR ORDER UPDATED ==========");
        printRepairOrder(repairOrder);
        System.out.println("==========================================\n");
    }
    
    private void printRepairOrder(RepairOrderDTO repairOrder) {
        if (repairOrder == null) {
            System.out.println("Repair Order: null");
            return;
        }
        
        System.out.println("Repair ID: " + repairOrder.getRepairId());
        System.out.println("Date: " + repairOrder.getDate());
        System.out.println("Customer: " + repairOrder.getCustomer().getName());
        System.out.println("Phone: " + repairOrder.getCustomer().getPhoneNum());
        System.out.println("Repair Report: " + repairOrder.getRepairReport());
        System.out.println("State: " + repairOrder.getState());
        System.out.println("Total Cost: " + repairOrder.getTotalCost());
        
        if (repairOrder.getReportDTO() != null) {
            System.out.println("Diagnostic Report - Tasks: " + repairOrder.getReportDTO().getRepairTasksList().size());
            System.out.println("Diagnostic Report - Total Time: " + repairOrder.getReportDTO().getTotalTime() + " minutes");
        } else {
            System.out.println("Diagnostic Report: Not yet completed");
        }
    }
}
