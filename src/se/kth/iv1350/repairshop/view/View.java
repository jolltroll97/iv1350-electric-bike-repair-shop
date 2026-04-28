package se.kth.iv1350.repairshop.view;

import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;

import java.util.List;

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
        
        // Customer walks in, and talks to the receptionist (Workflow points 1-6)
        
    }
}
