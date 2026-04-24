package se.kth.iv1350.repairshop.model;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import java.util.ArrayList;


public class DiagnosticReport {
    private ArrayList<RepairTaskDTO> repairTasksList;
    
    public DiagnosticReport() {
        this.repairTasksList = new ArrayList<>();
    }
    
    public ArrayList<RepairTaskDTO> addToList(RepairTaskDTO repairTask, RepairOrderDTO repairOrderDTO) {
        // If repairOrderDTO has existing repair tasks, add them first
        if (repairOrderDTO.getReportDTO() != null && repairOrderDTO.getReportDTO().getRepairTasksList() != null) {
            for (RepairTaskDTO existingTask : repairOrderDTO.getReportDTO().getRepairTasksList()) {
                repairTasksList.add(existingTask);
            }
        } else {
            // If no existing list, create a new empty list
            repairTasksList = new ArrayList<>();
        }
        
        // Then add the new repair task
        repairTasksList.add(repairTask);
        
        // Return as ArrayList
        return repairTasksList;
    }
    
    public int calculateTotalTime(ArrayList<RepairTaskDTO> repairTasks) {
        int totalTime = 0;
        for (RepairTaskDTO task : repairTasks) {
            totalTime += task.getTime();
        }
        return totalTime;
    }
}
