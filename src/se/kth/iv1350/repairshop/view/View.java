package se.kth.iv1350.repairshop.view;

import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;

import java.util.List;
import java.util.ArrayList;
import static java.lang.System.out;

/**
 * Placeholder for the real view.
 * Contains hardcoded fake user commands for the entire workflow.
 */

public class View {

    private final Controller contr;

    /**
     * Creates a new view instance, with a specified controller for all calls to other layers.
     * This creates the desired layer separation.
     * @param contr     The controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
    }
    
    /**
     * Performs fake user executions.
     */
    public void sampleExecution() {
        System.out.println("\n--- STARTING SAMPLE EXECUTION ---\n");
        
        // --- Workflow points 1-6 ---
        // Customer walks in, and talks to the receptionist.
        CustomerDTO MrAndersson = contr.retrieveCustomerInfo(701234566);

        // --- Workflow points 7-8 ---
        // Customer verifies the info.
        out.println("--- Method: retrieveCustomerInfo ---");
        out.println(MrAndersson);
        out.println("\n");

        // --- Workflow points 9-11 ---
        // Receptionist asks for problem description, and creates a repair order.
        contr.createRepairOrder(MrAndersson, 20260429, "Broken chain, battery dead");

        // --- Workflow points 12-14 ---
        // Customer waits. Technician asks system for repair order.
        List<RepairOrderDTO> pendingRepairOrders = contr.retrieveRepairOrderList("Newly created");
        // Print the returned list.
        out.println("--- Method: retrieveRepairOrderList ---");
        for (RepairOrderDTO order : pendingRepairOrders){
            out.println(order);
            out.println("\n");
        }
        
        // Technician selects the correct repair order (by saving the ID).
        // (The original idea was to use curentRepairOrder from Controller.
        // we later realized that this would be a front end feature, so it is not used)

        // int selectedRepairOrder = 4;
        int selectedRepairOrder = contr.currentRepairOrder;
        out.println(selectedRepairOrder);

        // --- Workflow points 15-17 ---
        // Technician performs diagnostic, and adds repair tasks.
        RepairTaskDTO taskOne = new RepairTaskDTO("Change chain", 750, 30);
        RepairTaskDTO taskTwo = new RepairTaskDTO("Change battery", 3000, 45);
        // Adding task one
        DiagnosticReportDTO reportDTOFirst = contr.addDiagnosticReport(taskOne, selectedRepairOrder);
        contr.updateRepairOrder(reportDTOFirst, selectedRepairOrder);
        // Adding taks two
        DiagnosticReportDTO reportDTOSecond = contr.addDiagnosticReport(taskTwo, selectedRepairOrder);
        contr.updateRepairOrder(reportDTOSecond, selectedRepairOrder);

        
    }
}
