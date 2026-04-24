package se.kth.iv1350.repairshop.model;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import java.util.ArrayList;


public class DiagnosticReport {
    private ArrayList<RepairTaskDTO> repairTasksList;
    
    public DiagnosticReport(DiagnosticReportDTO existingReportDTO) {
        // If there are no old tasks already stored, create a new arraylist
        if (existingReportDTO == null || existingReportDTO.getRepairTasksList() == null) {
            this.repairTasksList = new ArrayList<>();
        } 
        // If there are older tasks already stored, load them
        else {
            this.repairTasksList = new ArrayList<>(existingReportDTO.getRepairTasksList());
        }
    }
    
    public void addToList(RepairTaskDTO repairTask) {
        this.repairTasksList.add(repairTask); 
    }
    
    public int calculateTotalTime(ArrayList<RepairTaskDTO> repairTasks) {
        int totalTime = 0;
        for (RepairTaskDTO task : repairTasks) {
            totalTime += task.getTime();
        }
        return totalTime;
    }

    public ArrayList<RepairTaskDTO> getRepairTasksList() {
        return this.repairTasksList;
    }
}
