package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;

public class Printer {

    Printer(){
        /* Constructor, vet ej vad som ska vara i */
    }

    public void printRepairOrder(RepairOrderDTO selectedRepairOrder){
    System.out.println("------------------------------------");
    System.out.println("           REPAIR ORDER             ");
    System.out.println("------------------------------------");

    System.out.println(": " + selectedRepairOrder.toString());


    }

}
