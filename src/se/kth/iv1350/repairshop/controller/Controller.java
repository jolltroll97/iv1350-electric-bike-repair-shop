package se.kth.iv1350.repairshop.controller;

import se.kth.iv1350.repairshop.integration.RegistryCreator;
import se.kth.iv1350.repairshop.integration.CustomerRegistry;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;

import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.DiagnosticReportDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.RepairTaskDTO;

import se.kth.iv1350.repairshop.model.DiagnosticReport;
import se.kth.iv1350.repairshop.model.RepairOrder;
import se.kth.iv1350.repairshop.model.OrderHandler;
import se.kth.iv1350.repairshop.integration.Printer;

import java.util.List;
import java.util.ArrayList;

public class Controller {

    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;
    private final OrderHandler orderHandler;
    private final RepairOrder repairOrder;

    /**
     * Creates a new instance of the Controller.
     * 
     * @param creator       Used to get the classes that handle the database calls.
     */

    public Controller(RegistryCreator creator, Printer printer){
        this.customerRegistry = creator.getCustomerRegistry();
        this.repairOrderRegistry = creator.getRepairOrderRegistry();
        this.orderHandler = new OrderHandler(printer, this.repairOrderRegistry);
        this.repairOrder = new RepairOrder(this.repairOrderRegistry);
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
    public int createRepairOrder(CustomerDTO customer, int date, String repairReport){
        
        return this.repairOrderRegistry.createRepairOrder(customer, date, repairReport);

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

        return this.repairOrder.addDiagnosticReport(repairTask, repairOrderId);

    }
    
    /**
     * Updates the repair order by adding a diagnostic report.
     * This is done after each repair task that is to be added.
     * 
     * @param reportDTO     The diagnostic report to be added to the repair order.
     * @param repairOrderId The ID of the repair report that is to be updated.              
     */
    public void updateRepairOrder(DiagnosticReportDTO reportDTO, int repairOrderId){
        
        this.repairOrder.updateRepairOrder(reportDTO, repairOrderId);

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

        return this.repairOrderRegistry.getByPhoneNum(phoneNum);

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
      
            orderHandler.orderAccepted(selectedRepairOrder);
        
    }

    /**
     * Returns the correct repair order by searching for it's ID.
     * 
     * @param repairOrderId      The ID of the repair order that wants to be found
     */

    public RepairOrderDTO getById (int repairOrderId){

        return this.repairOrderRegistry.getById(repairOrderId);

    }
}
