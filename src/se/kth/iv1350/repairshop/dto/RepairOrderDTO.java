package se.kth.iv1350.repairshop.dto;
/**
 * Contains information about one specific repair order.
 */
public class RepairOrderDTO {

    private final DiagnosticReportDTO reportDTO;
    private final int date;
    private final double totalCost;
    private final String repairReport;
    private final String state;
    private final CustomerDTO customer;
    private final int repairId;

    /**
     * Creates a new instance representing a specific repair order.
     * 
     * @param reportDTO         All repair tasks and their combined cost.
     * @param date              Date for when the repair report was created.
     * @param totalCost         The total cost for the entire repair order
     *                          (can contain additional fees).
     * @param repairReport      Description of the repair report, written by
     *                          the receptionist (could be the customers description).
     * @param state             The state of the order:
     *                          - Newly created
     *                          - Ready for approval
     *                          - Rejected
     *                          - Accepted
     *                          - Completed
     *                          - Payed
     * @param customer          Information about the customer connected to the repair order.
     * @param repairId          Unique identification number for the repair order.
     */

    public RepairOrderDTO(DiagnosticReportDTO reportDTO, int date, double totalCost, String repairReport, String state, CustomerDTO customer, int repairId){
        this.reportDTO = reportDTO;
        this.date = date;
        this.totalCost = totalCost;
        this.repairReport = repairReport;
        this.state = state;
        this.customer = customer;
        this.repairId = repairId;
    }

    /**
     * Getters for all attributes.
     */

    public DiagnosticReportDTO getReportDTO(){
        return reportDTO;
    }

    public int getDate(){
        return date;
    }

    public double getTotalCost(){
        return totalCost;
    }

    public String getRepairReport(){
        return repairReport;
    }

    public String getState(){
        return state;
    }

    public CustomerDTO getCustomer(){
        return customer;
    }

    public int getRepairId(){
        return repairId;
    }

    /**
     * Method for printing the DTOs attributes.
     */

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("date: " + date + ", ");
        builder.append("totalCost: " + totalCost + ", ");
        builder.append("repairReport: " + repairReport + ", ");
        builder.append("state: " + state + ", ");
        builder.append("customer: " + customer + ", ");
        builder.append("repairId: " + repairId);
               
        return builder.toString();
    }
}
