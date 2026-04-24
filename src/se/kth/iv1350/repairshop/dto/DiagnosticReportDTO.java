package se.kth.iv1350.repairshop.dto;

import java.util.ArrayList;

public class DiagnosticReportDTO {
    // Attributes
    private ArrayList<RepairTaskDTO> repairTasksList;
    private int totalTime;
    
    public DiagnosticReportDTO(ArrayList<RepairTaskDTO> repairTasksList, int totalTime) {
        this.repairTasksList = repairTasksList;
        this.totalTime = totalTime;
    }
    
    // Getters
    public ArrayList<RepairTaskDTO> getRepairTasksList() {
        return repairTasksList;
    }
    
    public int getTotalTime() {
        return totalTime;
    }
    
    // Setters
    public void setRepairTasksList(ArrayList<RepairTaskDTO> repairTasksList) {
        this.repairTasksList = repairTasksList;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Repair Tasks: ").append(repairTasksList.size())
               .append(" tasks, Total Time: ").append(totalTime).append(" minutes");
        return builder.toString();
    }
}
