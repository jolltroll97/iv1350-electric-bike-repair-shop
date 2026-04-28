package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import java.util.ArrayList; 
import java.util.List;

/**
 *  This class represents the registry for repair orders.
 *  It is responsible for creating, retrieving and updating all repair orders. 
 *  @param repairOrders A list that contains all the repair orders in the registry.
 *  @param repairId An integer that is used to assign a unique ID to each repair order.
 * 
 */
public class RepairOrderRegistry{

    private List<RepairOrderDTO> repairOrders = new ArrayList<>();
    private int repairId = 4; 

    public RepairOrderRegistry(){
        /**
         * Fake customer data since there is no database to pull from
         */

        //String brand, String model, String serialNum
        BikeDTO bikeOne = new BikeDTO("Crescent", "Elody", "1234");
        BikeDTO bikeTwo = new BikeDTO("Crescent", "Elist", "1235");
        BikeDTO bikeThree = new BikeDTO("Crescent", "Eli", "1236");
        
        CustomerDTO customerOne = new CustomerDTO("Douglas Andersson", 701234566, "douglas.andersson0@gmail.com", bikeOne);
        CustomerDTO customerTwo = new CustomerDTO("Linus Sandin", 702345677, "linus.sandin1@gmail.com", bikeTwo);
        CustomerDTO customerThree = new CustomerDTO("Liza Rudaya", 703456777, "liza.rudaya@gmail.com", bikeThree);
        
        this.repairOrders.add(new RepairOrderDTO(null, 20260426, 10250, "Det mesta behöver bytas", "Newly created", customerOne, 1));
        this.repairOrders.add(new RepairOrderDTO(null, 20260427, 1650, "Punktering, rostiga bromsar", "Newly created", customerTwo, 2));
        this.repairOrders.add(new RepairOrderDTO(null, 20260428, 3550, "Rullar dåligt, kan vara kullagerna", "Ready for approval", customerThree, 3));
    }

    /**
     * Creates a new repair order and adds it to the registry. 
     * @param customer The customer who owns the bike (with bike information) that needs to be repaired.
     * @param date The date when the repair order was created.
     * @param repairReport A description of the issues with the bike (as provided by the customer).
     */
    public int createRepairOrder(CustomerDTO customer, int date, String repairReport){

        RepairOrderDTO currentRepairOrder = new RepairOrderDTO(
            null,
            date,
            0,
            repairReport,
            "Awaiting Diagnostic", 
            customer,
            repairId

        );

        repairOrders.add(currentRepairOrder);
        return repairId++;
  
    }

    /**
     * Retrieves a list of repair orders that are in a specific state.
     * @param state The state of the repair orders to retrieve (e.g. "Awaiting Diagnostic").
     */
    public List<RepairOrderDTO> retrieveRepairOrderList(String state){

        List<RepairOrderDTO> stateListRepairOrders = new ArrayList<>();

        for(RepairOrderDTO searchRepairOrders : repairOrders){
            if(searchRepairOrders.getState().equalsIgnoreCase(state)){
                stateListRepairOrders.add(searchRepairOrders);
            }

        }
        return stateListRepairOrders;

    }

    /**
     * Updates the diagnostic report of a specific repair order. 
     * @param newRepairOrder The repair order with the updated diagnostic report.
     */
    public void updateRepairOrderDiagnostic(RepairOrderDTO newRepairOrder){

        for(int i = 0; i < repairOrders.size(); i++){

            if(repairOrders.get(i).getRepairId() == newRepairOrder.getRepairId()){

                repairOrders.set(i, newRepairOrder);

            }

        }

    }
       
    /**
     * Updates the state of a specific repair order.
     * @param selectedRepairOrder The repair order to update.
     * @param newState The new state to set for the repair order (e.g. "Accepted", "Rejected").
     */
    public void updateRepairOrderState(RepairOrderDTO selectedRepairOrder, String newState){

        RepairOrderDTO updatedOrder = new RepairOrderDTO(
            selectedRepairOrder.getReportDTO(),
            selectedRepairOrder.getDate(),
            selectedRepairOrder.getTotalCost(),
            selectedRepairOrder.getRepairReport(),
            newState, 
            selectedRepairOrder.getCustomer(),
            selectedRepairOrder.getRepairId()
        );

        updateRepairOrderDiagnostic(updatedOrder);

    }

    /**
     * Retrieves a repair order by its ID.
     * @param repairOrderId The unique ID of the repair order to retrieve.
     */
    public RepairOrderDTO getById(int repairOrderId){

        for(RepairOrderDTO repairOrderById : repairOrders){
            if(repairOrderById.getRepairId() == repairOrderId){
                return repairOrderById;
            }
        }

        return null;
        
    }

    /**
     * Retrieves a list of repair orders consisting of every repair order created for specific customer.
     * @param phoneNum The phone number to search for (associated with the customer of the repair orders).
     * @return A list of repair orders associated with the provided phone number.
     */
    public List<RepairOrderDTO> getByPhoneNum(int phoneNum){

        List <RepairOrderDTO> foundRepairOrders = new ArrayList<>();

        for(RepairOrderDTO customerRepairOrder : repairOrders){

            if(customerRepairOrder.getCustomer().getPhoneNum() == phoneNum){
                foundRepairOrders.add(customerRepairOrder);
            }

        }

        return foundRepairOrders;

    }

}