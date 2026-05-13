package se.kth.iv1350.repairshop.view;

import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;

import java.util.List;
import static java.lang.System.out;

/**
 * Placeholder for the real view.
 * Contains hardcoded fake user commands for the entire workflow.
 */
public class View {

    private final Controller contr;

    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Performs fake user executions.
     */
    public void sampleExecution() {
        System.out.println("\n--- STARTING SAMPLE EXECUTION ---\n");
        
        // --- Workflow points 1-6 ---
        CustomerDTO mrAndersson = contr.retrieveCustomerInfo(701234566);

        // --- Workflow points 7-8 ---
        out.println("--- Method: retrieveCustomerInfo ---");
        printCustomer(mrAndersson);
        out.println("\n");

        // --- Workflow points 9-11 ---
        int newRepairOrder = contr.createRepairOrder(mrAndersson, 20260429, "Broken chain, battery dead");
        out.println("--- Method: createRepairOrder ---");
        out.println("New Order ID: " + newRepairOrder);
        out.println("\n");

        // --- Workflow points 12-14 ---
        List<RepairOrderDTO> pendingRepairOrders = contr.retrieveRepairOrderList("Newly created");
        out.println("--- Method: retrieveRepairOrderList ---");
        for (RepairOrderDTO order : pendingRepairOrders){
            printRepairOrder(order);
            out.println("\n");
        }
        
        int selectedRepairOrder = newRepairOrder;

        // --- Workflow points 15-17 ---
        RepairTaskDTO taskOne = new RepairTaskDTO("Change chain", 750, 30);
        RepairTaskDTO taskTwo = new RepairTaskDTO("Change battery", 3000, 45);

        // Adding task one
        DiagnosticReportDTO reportDTOFirst = contr.addDiagnosticReport(taskOne, selectedRepairOrder);
        out.println("--- Method: addDiagnosticReport (first task)---");
        printDiagnosticReport(reportDTOFirst);
        out.println("\n");
        contr.updateRepairOrder(reportDTOFirst, selectedRepairOrder);

        // Adding task two
        DiagnosticReportDTO reportDTOSecond = contr.addDiagnosticReport(taskTwo, selectedRepairOrder);
        out.println("--- Method: addDiagnosticReport (second task)---");
        printDiagnosticReport(reportDTOSecond);
        out.println("\n");
        contr.updateRepairOrder(reportDTOSecond, selectedRepairOrder);

        // --- Workflow point: 18-23 ---
        out.println("--- Final Repair Order ---");
        RepairOrderDTO finalReport = contr.getById(newRepairOrder);
        contr.customerResponse(true, finalReport);

        //printRepairOrder(finalReport);
    }

    // --- Hjälpmetoder för snygg formatering ---

    private void printCustomer(CustomerDTO customer) {
        out.println("Customer Name: " + customer.getName());
        out.println("Phone: " + customer.getPhoneNum());
        out.println("Email: " + customer.getEmail());
        printBike(customer.getBikeDTO());
    }

    private void printBike(BikeDTO bike) {
        out.println("Brand: " + bike.getBrand());
        out.println("Model: " + bike.getModel());
        out.println("Serial Number: " + bike.getSerialNum());
    }

    private void printRepairOrder(RepairOrderDTO order) {
    
        printCustomer(order.getCustomer());
        out.println("Repair ID: " + order.getRepairId());
        out.println("Date: " + order.getDate());
        out.println("Status: " + order.getState());
        out.println("Total Cost: " + order.getTotalCost() + " SEK");
        out.println("Repair Report: " + order.getRepairReport());
        if(order.getReportDTO() != null){
            printDiagnosticReport(order.getReportDTO());
        }
    }

    private void printRepairTasks(List<RepairTaskDTO> tasks) {
        for (RepairTaskDTO task : tasks) {
            out.println("Task: " + task.getDescription());
            out.println("Cost: " + task.getCost() + " SEK");
            out.println("Time: " + task.getTime() + " min");
        }
    }

    private void printDiagnosticReport(DiagnosticReportDTO report) {
        printRepairTasks(report.getRepairTasksList());
        out.println("Total Time: " + report.getTotalTime() + " min");
        
    }

}