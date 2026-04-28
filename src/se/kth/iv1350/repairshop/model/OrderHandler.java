package se.kth.iv1350.repairshop.model;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;
import se.kth.iv1350.repairshop.integration.Printer;

/**
 * Handles the processing and state management of repair orders.
 * This class is responsible for accepting or rejecting orders,
 * printing order confirmations, and updating the order registry.
 */
public class OrderHandler {
    private Printer printer;
    private RepairOrderRegistry repairOrderRegistry;

    /**
     * Creates a new instance of OrderHandler.
     * 
     * @param printer The printer used to print repair order confirmations.
     * @param repairOrderRegistry The registry where repair orders are stored and managed.
     */
    public OrderHandler(Printer printer, RepairOrderRegistry repairOrderRegistry) {
        this.printer = printer;
        this.repairOrderRegistry = repairOrderRegistry;
    }

    /**
     * Processes an accepted repair order.
     * Updates the order state to "Accepted", prints the order confirmation,
     * and persists the updated state to the registry.
     * 
     * @param selectedRepairOrder The repair order that has been accepted.
     */
    public void orderAccepted(RepairOrderDTO selectedRepairOrder) {
        // Update order state to "Accepted" and persist
        RepairOrderDTO acceptedOrder = createUpdatedOrder(selectedRepairOrder, "Accepted");
        printer.printRepairOrder(acceptedOrder);
        repairOrderRegistry.updateRepairOrderState(acceptedOrder, "Accepted");
    }

    /**
     * Processes a rejected repair order.
     * Updates the order state to "Rejected" and persists the updated state to the registry.
     * 
     * @param selectedRepairOrder The repair order that has been rejected.
     */
    public void orderRejected(RepairOrderDTO selectedRepairOrder) {
        // Update order state to "Rejected" and persist
        RepairOrderDTO rejectedOrder = createUpdatedOrder(selectedRepairOrder, "Rejected");
        repairOrderRegistry.updateRepairOrderState(rejectedOrder, "Rejected");
    }

    /**
     * Creates a new RepairOrderDTO with an updated state.
     * All other properties are copied from the original order.
     * 
     * @param order The original repair order to be updated.
     * @param newState The new state to assign to the order (can be "Accepted" or "Rejected").
     * @return A new RepairOrderDTO instance with the updated state.
     */
    private RepairOrderDTO createUpdatedOrder(RepairOrderDTO order, String newState) {
        return new RepairOrderDTO(
            order.getReportDTO(),
            order.getDate(),
            order.getTotalCost(),
            order.getRepairReport(),
            newState,  // Update the state
            order.getCustomer(),
            order.getRepairId()
        );
    }
}