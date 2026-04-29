package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;

/**
 * A class for printing repair orders.
 */
public class Printer {

    public Printer(){
        /* Constructor, vet ej vad som ska vara i */
    }

    /**
     * Prints the information of a specific repair order.
     * @param selectedRepairOrder The repair order to print.
     */
    public void printRepairOrder(RepairOrderDTO selectedRepairOrder){
    System.out.println("------------------------------------");
    System.out.println("           REPAIR ORDER             ");
    System.out.println("------------------------------------");

    System.out.println(selectedRepairOrder.toString());

    System.out.println(" ");
    System.out.println("------------------------------------");


    }

}
