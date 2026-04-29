package se.kth.iv1350.repairshop.dto;

import java.util.ArrayList;

/**
 * This object has information about repair tasks
 * and the total time required to complete them. It is used to transfer
 * diagnostic report data between different layers of the application.
 */
public class DiagnosticReportDTO {
    // Attributes
    private ArrayList<RepairTaskDTO> repairTasksList;
    private int totalTime;
    
    /**
     * Creates a new DiagnosticReportDTO with the specified repair tasks and total time.
     * 
     * @param repairTasksList The list of repair tasks included in this diagnostic report.
     * @param totalTime The total time required to complete all repair tasks, typically measured in minutes.
     */
    public DiagnosticReportDTO(ArrayList<RepairTaskDTO> repairTasksList, int totalTime) {
        this.repairTasksList = repairTasksList;
        this.totalTime = totalTime;
    }
    
    /**
     * Retrieves the list of repair tasks in this diagnostic report.
     * 
     * @return An ArrayList containing all repair tasks.
     */
    public ArrayList<RepairTaskDTO> getRepairTasksList() {
        return repairTasksList;
    }
    
    /**
     * Retrieves the total time required to complete all repair tasks.
     * 
     * @return The total time in minutes.
     */
    public int getTotalTime() {
        return totalTime;
    }
    
    /**
     * Updates the list of repair tasks in this diagnostic report.
     * 
     * @param repairTasksList The new list of repair tasks to set.
     */
    public void setRepairTasksList(ArrayList<RepairTaskDTO> repairTasksList) {
        this.repairTasksList = repairTasksList;
    }
    
    /**
     * Returns a string representation of this diagnostic report.
     * The string includes the number of repair tasks and the total time required.
     * 
     * @return A formatted string describing the diagnostic report
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Repair Tasks: ").append(repairTasksList.size())
               .append(" tasks").append("\n").append("Total Time: ").append(totalTime).append(" minutes");
        return builder.toString();
    }
}