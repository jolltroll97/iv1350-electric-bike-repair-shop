package se.kth.iv1350.repairshop.controller;

import se.kth.iv1350.repairshop.integration.RegistryCreator;
import se.kth.iv1350.repairshop.integration.CustomerRegistry;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;

import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;

import se.kth.iv1350.repairshop.model.DiagnosticReport;

import java.util.List;
import java.util.ArrayList;

public class Controller {

    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;

    private int currentRepairOrder;

    /**
     * Creates a new instance of the Controller.
     * 
     * @param creator       Used to get the classes that handle the database calls.
     */

    public Controller(RegistryCreator creator){
        this.customerRegistry = creator.getCustomerRegistry();
        this.repairOrderRegistry = creator.getRepairOrderRegistry();
    }

    /**
     * Calls the integration layer to do the following task:
     * Retrieves customer info by searching for their phone number.
     * The actual searching logic is done in the integration layer.
     * 
     * @param phoneNum      The customers phone number.
     */
    public CustomerDTO retrieveCustomerInfo(int phoneNum){

        return this.customerRegistry.findCustomer(phoneNum);
    }

    /**
     * Creates a new repair order.
     * 
     * @param customer      The customer connected to the repair order.
     * @param date          The date rthe repair order was created.
     * @param repairReport  Description of the repair report (receptionist enters this).
     */
    public void createRepairOrder(CustomerDTO customer, int date, String repairReport){
        
        int newOrderId = this.repairOrderRegistry.createRepairOrder(customer, date, repairReport);

        this.currentRepairOrder = newOrderId;

    }

    /**
     * Returns a list of repair orders from the registry, filtered by state.
     * 
     * @param state         The state that filters the returned list.
     */
    public List<RepairOrderDTO> retrieveRepairOrderList(String state){
        
        return this.repairOrderRegistry.retrieveRepairOrderList(state);

    }

    /**
     * Adds a diagnostic report to a specific repair order.
     * NOTE: does NOT save the update to the registry.
     * 
     * @param repairTask    The specific task to be added.
     * @param repairOrderId The ID number used to identify the correct repair order in the registry.
     */
    public DiagnosticReportDTO addDiagnosticReport(RepairTaskDTO repairTask, int repairOrderId){
        
        // Fetch the correct data using the current ID
        RepairOrderDTO orderDTO = this.repairOrderRegistry.getById(repairOrderId);
       
        // Extract the diagnostic report
        DiagnosticReportDTO existingReportDTO = orderDTO.getReportDTO();

        // Build the object
        DiagnosticReport reportModel = new DiagnosticReport(existingReportDTO);

        // Call add to list
        List<RepairTaskDTO> currentTasks = reportModel.addToList(repairTask, orderDTO);

        int totalTime = reportModel.calculateTotalTime(currentTasks);

        DiagnosticReportDTO updatedReportDTO = new DiagnosticReportDTO(currentTasks, totalTime);

        return updatedReportDTO;

    }
    
    /**
     * Updates the repair order by adding a diagnostic report.
     * This is done after each repair task that is to be added.
     * 
     * @param reportDTO     The diagnostic report to be added to the repair order.
     * @param repairOrderId The ID of the repair report that is to be updated.              
     */
    public void updateRepairOrder(DiagnosticReportDTO reportDTO, int repairOrderId){
        /**
         * No code yet.
         */
    }

    /**
     * Returns a list of all repair orders connected to a specific customer.
     * 
     * @param phoneNum      The phone number belonging to the specific customer.
     */
    public List<RepairOrderDTO> getByPhoneNum(int phoneNum){
        /**
         * Det borde vara en lista som returneras, eller hur?
         * 
         * Registret borde använda:
         * return Collections.unmodifiableList(dtoList);
         * Detta gör att view inte råkar ta bort något ur listan.
         * 
         * No code yet.
         * Förslag på användning:
         * List<RepairOrderDTO> dtoList = new ArrayList<>();
         */
    }

    /**
     * Updates the repair order with the customers response.
     * Can be "Accepted" or "Rejected"
     * 
     * @param response              The customers response.
     * @param selectedRepairOrder   The repair order that is to be updated
     *                              (chosen from the list returned from "getByPhoneNum")
     */
    public void customerResponse(boolean response, RepairOrderDTO selectedRepairOrder){
        /**
         * No code yet.
         */
    }
}
