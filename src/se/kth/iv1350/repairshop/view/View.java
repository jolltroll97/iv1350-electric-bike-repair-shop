package se.kth.iv1350.repairshop.view;

import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.util.LogHandler;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.integration.CustomerNotFoundException;

import java.util.List;
import static java.lang.System.out;

/**
 * Placeholder for the real view.
 * Contains hardcoded fake user commands for the entire workflow.
 */

public class View {

    private final Controller contr;
    private final LogHandler logger;
    private final ErrorMessageHandler errorMessageHandler;

    /**
     * Creates a new view instance, with a specified controller for all calls to other layers.
     * This creates the desired layer separation.
     * @param contr     The controller to use for all calls to other layers.
     */
    public View(Controller contr, LogHandler logger, ErrorMessageHandler errorMessageHandler) {
        this.contr = contr;
        this.logger = logger;
        this.errorMessageHandler = new ErrorMessageHandler();
    }



    public String customerDTOToString(CustomerDTO customerDTO){
        StringBuilder builder = new StringBuilder();

            builder.append("Name: ").append(customerDTO.getName()).append("\n")
                    .append("Phone number: ").append(customerDTO.getPhoneNum()).append("\n")
                    .append("Email: ").append(customerDTO.getEmail()).append("\n")
                    .append("Bike: ").append(customerDTO.getBikeDTO());

        return builder.toString();
    }

    public String diagnosticReportDTOToString(DiagnosticReportDTO diagnosticReportDTO) {
        StringBuilder builder = new StringBuilder();

        builder.append("Repair Tasks: ").append(diagnosticReportDTO.getRepairTasksList().size())
               .append(" tasks").append("\n").append("Total Time: ").append(diagnosticReportDTO.getTotalTime()).append(" minutes");

        return builder.toString();
    }
    
    public String repairOrderDTOToString (RepairOrderDTO repairOrderDTO){
        if (repairOrderDTO == null){
            return "Diagnostic Report: Diagnosis not done yet\n";
        }
        
        StringBuilder builder = new StringBuilder();
        builder.append("Diagnostic Report: ")
               .append("\n")
               .append(repairOrderDTO)
               .append("\n")
               .append("Date: ").append(repairOrderDTO.getDate()).append("\n")
               .append("Total Cost: ").append(repairOrderDTO.getTotalCost()).append("\n")
               .append("Repair Report: ").append(repairOrderDTO.getRepairReport()).append("\n")
               .append("State: ").append(repairOrderDTO.getState()).append("\n")
               .append("Customer info: ").append(repairOrderDTO.getCustomer()).append("\n")
               .append("Repair ID: ").append(repairOrderDTO.getRepairId());

        return builder.toString();
    }

    public String repairTaskDTOToString (RepairTaskDTO repairTaskDTO){
        StringBuilder builder = new StringBuilder();

        builder.append("Description: ").append(repairTaskDTO.getDescription()).append("\n")
               .append("Cost: ").append(repairTaskDTO.getCost()).append("\n")
               .append("Time: ").append(repairTaskDTO.getTime());

        return builder.toString();
    }

    /**
     * Helper method to handle the actual search and catch any errors.
     */
    private void searchForCustomer(int phoneNumber) {
        try {
            contr.retrieveCustomerInfo(phoneNumber);

        } catch (CustomerNotFoundException exc) {
            errorMessageHandler.showErrorMsg("No customer with phone number: " + exc.getMessage() + " could be found");
            logger.logException(exc);

        } catch (Exception exc) {
            errorMessageHandler.showErrorMsg("Technical error");
            logger.logException(exc);

        }
    }


    /**
     * Performs fake user executions.
     */
    public void sampleExecution() {
        try {
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
        } catch (Exception exc) {
            errorMessageHandler.showErrorMsg("Error during sample execution: " + exc.getMessage());
            logger.logException(exc);
        }
    }
    
    public void alternateFlow(){
        
            System.out.println("\n--- STARTING ALTERNATE FLOW ---\n");

            // A normal, successful search
            System.out.println("1. Cashier searches for valid customer (701234566):");
            searchForCustomer(701234566); 

            // The Alternate Flow (Customer not found)
            System.out.println("\n2. Cashier searches for non-existent customer (999999999):");
            searchForCustomer(999999999); 

            // System Failure (Database crash)
            System.out.println("\n3. Cashier searches while database is down (666):");
            searchForCustomer(666);
        
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