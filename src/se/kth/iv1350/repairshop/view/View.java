package se.kth.iv1350.repairshop.view;

import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.util.LogHandler;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.integration.CustomerNotFoundException;
import se.kth.iv1350.repairshop.integration.DatabaseFailureException;

import java.util.List;
import java.util.ArrayList;
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

    private String bikeDTOToString(BikeDTO bikeDTO){
        StringBuilder builder = new StringBuilder();
        
        builder.append("Brand: ").append(bikeDTO.getBrand()).append("\n")
               .append("Model: ").append(bikeDTO.getModel()).append("\n")
               .append("Serial number: ").append(bikeDTO.getSerialNum());

               return builder.toString();
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
        StringBuilder builder = new StringBuilder();

          if (repairOrderDTO != null){
            builder.append("Diagnostic Report: ")
                   .append("\n")
                   .append(repairOrderDTO) //fixa
                   .append("\n");
        } else {
            builder.append("Diagnostic Report: Diagnosis not done yet\n");
        }
        
        
        builder.append("Date: ").append(repairOrderDTO.getDate()).append("\n")
               .append("Total Cost: ").append(repairOrderDTO.getTotalCost()).append("\n")
               .append("Repair Report: ").append(repairOrderDTO.getRepairReport()).append("\n")
               .append("State: ").append(repairOrderDTO.getState()).append("\n")
               .append("Customer info: ").append(repairOrderDTO.getCustomer()).append("\n") //fixa
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

    /*
    public void sampleExecution() {
        System.out.println("\n--- STARTING SAMPLE EXECUTION ---\n");
        
        // --- Workflow points 1-6 ---
        // Customer walks in, and talks to the receptionist.
        CustomerDTO MrAndersson = contr.retrieveCustomerInfo(701234566);

        // --- Workflow points 7-8 ---
        // Customer verifies the info.
        out.println("--- Method: retrieveCustomerInfo ---");
        out.println(customerDTOToString(MrAndersson));
        out.println("\n");

        // --- Workflow points 9-11 ---
        // Receptionist asks for problem description, and creates a repair order.
        int newRepairOrder = contr.createRepairOrder(MrAndersson, 20260429, "Broken chain, battery dead");
        // Print the returned ID
        out.println("--- Method: createRepairOrder ---");
        out.println(newRepairOrder);
        out.println("\n");

        // --- Workflow points 12-14 ---
        // Customer waits. Technician asks system for repair order.
        List<RepairOrderDTO> pendingRepairOrders = contr.retrieveRepairOrderList("Newly created");
        // Print the returned list.
        out.println("--- Method: retrieveRepairOrderList ---");
        for (RepairOrderDTO order : pendingRepairOrders){
            out.println(repairOrderDTOToString(order));
            out.println("\n");
        }
        
        // Technician selects the correct repair order (by saving the ID).
        // The ID is returned when the new repair report is created, 
        // and also shows up in the list "pendingRepairOrders"
        // we later realized that this would be a front end feature, so it is not used)

        // int selectedRepairOrder = 4;
        int selectedRepairOrder = newRepairOrder;

        // --- Workflow points 15-17 ---
        // Technician performs diagnostic, and adds repair tasks.
        RepairTaskDTO taskOne = new RepairTaskDTO("Change chain", 750, 30);
        RepairTaskDTO taskTwo = new RepairTaskDTO("Change battery", 3000, 45);
        // Adding task one
        DiagnosticReportDTO reportDTOFirst = contr.addDiagnosticReport(taskOne, selectedRepairOrder);
        // Print the returned DiagnosticReportDTO (1)
        out.println("--- Method: addDiagnosticReport (first task)---");
        out.println(diagnosticReportDTOToString(reportDTOFirst));
        out.println("\n");
        // Update the repair order (1)
        contr.updateRepairOrder(reportDTOFirst, selectedRepairOrder);
        // Adding task two
        DiagnosticReportDTO reportDTOSecond = contr.addDiagnosticReport(taskTwo, selectedRepairOrder);
        // Print the returned DiagnosticReportDTO (2)
        out.println("--- Method: addDiagnosticReport (second task)---");
        out.println(diagnosticReportDTOToString(reportDTOSecond));
        out.println("\n");
        // Update the repair order (2)
        contr.updateRepairOrder(reportDTOSecond, selectedRepairOrder);

        // --- Workflow point: 18-23 ---
        RepairOrderDTO finalReport = contr.getById(newRepairOrder);
        contr.customerResponse(true, finalReport);
        
    }
    */
    public void alternateFlow(){
        System.out.println("\n--- STARTING SAMPLE EXECUTION ---\n");

        // SCENARIO 1: A normal, successful search (from your hardcoded list)
        System.out.println("1. Cashier searches for valid customer (701234566):");
        searchForCustomer(701234566); 

        // SCENARIO 2: The Alternate Flow (Customer not found)
        System.out.println("\n2. Cashier searches for non-existent customer (999999999):");
        searchForCustomer(999999999); 

        // SCENARIO 3: The System Failure (Database crash)
        System.out.println("\n3. Cashier searches while database is down (666):");
        searchForCustomer(666);
    }
}

