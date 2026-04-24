package se.kth.iv1350.repairshop.dto;

public class DiagnosticReportDTO {
    // Attributes
    private RepairTaskDTO[] repairTasksList;
    private int totalTime;
    
    public DiagnosticReportDTO(RepairTaskDTO[] repairTasksList, int totalTime) {
        this.repairTasksList = repairTasksList;
        this.totalTime = totalTime;
    }
    
    // Getters
    public RepairTaskDTO[] getRepairTasksList() {
        return repairTasksList;
    }
    
    public int getTotalTime() {
        return totalTime;
    }
    
    // Setters
    public void setRepairTasksList(RepairTaskDTO[] repairTasksList) {
        this.repairTasksList = repairTasksList;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Repair Tasks: ").append(repairTasksList.length)
               .append(" tasks, Total Time: ").append(totalTime).append(" minutes");
        return builder.toString();
    }
}
