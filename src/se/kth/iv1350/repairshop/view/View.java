package se.kth.iv1350.repairshop.view;

import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.util.LogHandler;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.integration.CustomerNotFoundException;

import java.util.List;

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
            // Customer walks in, and talks to the receptionist.
            CustomerDTO mrAndersson = contr.retrieveCustomerInfo(701234566);
            // --- Workflow points 7-8 ---
            // Customer verifies the info.
            System.out.println("--- Method: retrieveCustomerInfo ---");
            System.out.println(customerDTOToString(mrAndersson));
            System.out.println("\n");

            // --- Workflow points 9-11 ---
            // Receptionist asks for problem description, and creates a repair order.
            int newRepairOrder = contr.createRepairOrder(mrAndersson, 20260429, "Broken chain, battery dead");
            // Print the returned ID
            System.out.println("--- Method: createRepairOrder ---");
            System.out.println(newRepairOrder);
            System.out.println("\n");

            // --- Workflow points 12-14 ---
            // Customer waits. Technician asks system for repair order.
            List<RepairOrderDTO> pendingRepairOrders = contr.retrieveRepairOrderList("Newly created");
            // Print the returned list.
            System.out.println("--- Method: retrieveRepairOrderList ---");
            for (RepairOrderDTO order : pendingRepairOrders){
                System.out.println(repairOrderDTOToString(order));
                System.out.println("\n");
            }
            
            // Technician selects the correct repair order (by saving the ID).
            // The ID is returned when the new repair report is created, 
            // and also shows up in the list "pendingRepairOrders"
            int selectedRepairOrder = newRepairOrder;

            // --- Workflow points 15-17 ---
            // Technician performs diagnostic, and adds repair tasks.
            RepairTaskDTO taskOne = new RepairTaskDTO("Change chain", 750, 30);
            RepairTaskDTO taskTwo = new RepairTaskDTO("Change battery", 3000, 45);
            // Adding task one
            DiagnosticReportDTO reportDTOFirst = contr.addDiagnosticReport(taskOne, selectedRepairOrder);
            // Print the returned DiagnosticReportDTO (1)
            System.out.println("--- Method: addDiagnosticReport (first task)---");
            System.out.println(diagnosticReportDTOToString(reportDTOFirst));
            System.out.println("\n");
            // Update the repair order (1)
            contr.updateRepairOrder(reportDTOFirst, selectedRepairOrder);
            // Adding task two
            DiagnosticReportDTO reportDTOSecond = contr.addDiagnosticReport(taskTwo, selectedRepairOrder);
            // Print the returned DiagnosticReportDTO (2)
            System.out.println("--- Method: addDiagnosticReport (second task)---");
            System.out.println(diagnosticReportDTOToString(reportDTOSecond));
            System.out.println("\n");
            // Update the repair order (2)
            contr.updateRepairOrder(reportDTOSecond, selectedRepairOrder);

            // --- Workflow point: 18-23 ---
            RepairOrderDTO finalReport = contr.getById(newRepairOrder);
            contr.customerResponse(true, finalReport);
        } catch (Exception exc) {
            errorMessageHandler.showErrorMsg("Error during sample execution: " + exc.getMessage());
            logger.logException(exc);
        }
    }
    
    public void alternateFlow(){
        try {
            System.out.println("\n--- STARTING ALTERNATE FLOW ---\n");

            // SCENARIO 1: A normal, successful search (from your hardcoded list)
            System.out.println("1. Cashier searches for valid customer (701234566):");
            searchForCustomer(701234566); 

            // SCENARIO 2: The Alternate Flow (Customer not found)
            System.out.println("\n2. Cashier searches for non-existent customer (999999999):");
            searchForCustomer(999999999); 

            // SCENARIO 3: The System Failure (Database crash)
            System.out.println("\n3. Cashier searches while database is down (666):");
            searchForCustomer(666);
        } catch (Exception exc) {
            errorMessageHandler.showErrorMsg("Error during alternate flow: " + exc.getMessage());
            logger.logException(exc);
        }
    }
}