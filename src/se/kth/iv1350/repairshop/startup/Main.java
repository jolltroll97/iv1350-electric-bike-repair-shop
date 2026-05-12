package se.kth.iv1350.repairshop.startup;

import se.kth.iv1350.repairshop.integration.RegistryCreator;
import se.kth.iv1350.repairshop.integration.Printer;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;
import se.kth.iv1350.repairshop.controller.Controller;
import se.kth.iv1350.repairshop.view.View;
import se.kth.iv1350.repairshop.view.RepairOrderView;
import se.kth.iv1350.repairshop.integration.RepairOrderLogger;
import se.kth.iv1350.repairshop.util.LogHandler;
import se.kth.iv1350.repairshop.view.ErrorMessageHandler;
import java.io.IOException;

public class Main {
    
/**
 * The main method that starts the application.
 * @param args   The application does not take any command line aparameters.
 */
    public static void main(String[] args) {
        
        try{
        RegistryCreator creator = new RegistryCreator();

        Printer printer = new Printer();

        Controller contr = new Controller(creator, printer);

        LogHandler logger = new LogHandler();

        ErrorMessageHandler errorMessageHandler = new ErrorMessageHandler();

        View view = new View(contr, logger, errorMessageHandler);

        RepairOrderRegistry repairOrderRegistry = creator.getRepairOrderRegistry();
        repairOrderRegistry.addObserver(new RepairOrderView());
        repairOrderRegistry.addObserver(new RepairOrderLogger());
        
        view.sampleExecution();
        }

        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
