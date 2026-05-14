package se.kth.iv1350.repairshop.integration;
import java.util.List;

import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;

import static java.lang.System.out;

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
    public void printRepairOrderFinal(RepairOrderDTO selectedRepairOrder){
    System.out.println("------------------------------------");
    System.out.println("           REPAIR ORDER             ");
    System.out.println("------------------------------------");

    printRepairOrder(selectedRepairOrder);

    System.out.println(" ");
    System.out.println("------------------------------------");


    }

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
