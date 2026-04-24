package se.kth.iv1350.repairshop.startup;

import se.kth.iv1350.repairshop.integration.RegistryCreator;
import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.view.View;

public class Main {
    
/**
 * The main method that starts the application.
 * @param args   The application does not take any command line aparameters.
 */
    public static void main(String[] args) {
        RegistryCreator creator = new RegistryCreator();

        Controller contr = new Controller(creator);

        View view = new View(contr);

        view.sampleExecution();
    }
}
