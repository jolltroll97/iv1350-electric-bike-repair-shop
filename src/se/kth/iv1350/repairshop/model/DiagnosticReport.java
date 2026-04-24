package se.kth.iv1350.repairshop.model;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import java.util.ArrayList;


public class DiagnosticReport {
    private ArrayList<RepairTaskDTO> repairTasksList;
    
    public DiagnosticReport(DiagnosticReportDTO reportDTO) {
        this.repairTasksList = new ArrayList<>();
    }
    
    public RepairTaskDTO[] addToList(RepairTaskDTO repairTask, RepairOrderDTO repairOrderDTO) {
        repairTasksList.add(repairTask);
        return repairTasksList.toArray(new RepairTaskDTO[0]);
    }
    
    public int calculateTotalTime(RepairTaskDTO[] repairTasks) {
        int totalTime = 0;
        for (RepairTaskDTO task : repairTasks) {
            totalTime += task.getTime();
        }
        return totalTime;
    }
}
