package se.kth.iv1350.repairshop.model;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import java.util.ArrayList;

/**
 * Represents a diagnostic report for a repair order.
 * This class manages a list of repair tasks, including adding new tasks and calculating the total time required for all tasks.
 */
public class DiagnosticReport {
    private ArrayList<RepairTaskDTO> repairTasksList;
    
    /**
     * Creates a new DiagnosticReport instance.
     * If existing report is provided, it loads the previous repair tasks.
     * If not existing report is provided, it initializes an empty task list.
     * 
     * @param existingReportDTO The existing diagnostic report containing previous tasks,
     *                          or null to create a new empty report.
     */
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
    
    /**
     * Adds a new repair task to the diagnostic report.
     * 
     * @param repairTask The repair task to be added to the report.
     */
    public void addToList(RepairTaskDTO repairTask) {
        this.repairTasksList.add(repairTask); 
    }
    
    /**
     * Calculates the total time required to complete all repair tasks.
     * This method iterates through all tasks and sums their individual time requirements.
     * 
     * @param repairTasks The list of repair tasks for which to calculate the total time.
     * @return The total time in the unit used by RepairTaskDTO (e.g., minutes or hours).
     */
    public int calculateTotalTime(ArrayList<RepairTaskDTO> repairTasks) {
        int totalTime = 0;
        for (RepairTaskDTO task : repairTasks) {
            totalTime += task.getTime();
        }
        return totalTime;
    }

    /**
     * Retrieves the list of all repair tasks in this diagnostic report.
     * 
     * @return An ArrayList containing all repair tasks in the report.
     */
    public ArrayList<RepairTaskDTO> getRepairTasksList() {
        return this.repairTasksList;
    }
}