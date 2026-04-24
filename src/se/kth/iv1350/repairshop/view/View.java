package se.kth.iv1350.repairshop.view;

import se.kth.iv1350.repairshop.controller.Controller;

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
        /**
         * No code yet.
         */
    }
}
