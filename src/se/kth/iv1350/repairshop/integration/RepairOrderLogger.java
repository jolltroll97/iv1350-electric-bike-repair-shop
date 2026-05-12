package se.kth.iv1350.repairshop.integration;

import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Observer implementation that logs repair order updates to a file.
 * This provides arecord of all repair order changes.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    
    private static final String LOG_FILE = "repair_orders.log";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    
    @Override
    public void repairOrderCreated(RepairOrderDTO repairOrder) {
        logToFile("NEW REPAIR ORDER CREATED", repairOrder);
    }
    
    @Override
    public void repairOrderUpdated(RepairOrderDTO repairOrder) {
        logToFile("REPAIR ORDER UPDATED", repairOrder);
    }
    
    private synchronized void logToFile(String action, RepairOrderDTO repairOrder) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(LocalDateTime.now().format(TIME_FORMATTER)).append("] ");
            sb.append(action).append(" - ");
            
            if (repairOrder == null) {
                sb.append("Repair Order: null\n");
            } else {
                sb.append("Repair ID: ").append(repairOrder.getRepairId())
                  .append(", Customer: ").append(repairOrder.getCustomer().getName())
                  .append(", Phone: ").append(repairOrder.getCustomer().getPhoneNum())
                  .append(", State: ").append(repairOrder.getState())
                  .append(", Total Cost: ").append(repairOrder.getTotalCost());
                
                if (repairOrder.getReportDTO() != null) {
                    sb.append(", Tasks: ").append(repairOrder.getReportDTO().getRepairTasksList().size());
                }
                sb.append("\n");
            }
            
            writer.write(sb.toString());
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error writing to repair order log file: " + e.getMessage());
        }
    }
}