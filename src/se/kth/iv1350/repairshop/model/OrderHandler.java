package se.kth.iv1350.repairshop.model;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;
import se.kth.iv1350.repairshop.integration.Printer;


public class OrderHandler {
    private Printer printer;
    private RepairOrderRegistry repairOrderRegistry;

    public OrderHandler(Printer printer, RepairOrderRegistry repairOrderRegistry) {
        this.printer = printer;
        this.repairOrderRegistry = repairOrderRegistry;
    }

    public void orderAccepted(RepairOrderDTO selectedRepairOrder) {
        // Update order state to "Accepted" and persist
        RepairOrderDTO acceptedOrder = createUpdatedOrder(selectedRepairOrder, "Accepted");
        printer.printRepairOrder(acceptedOrder);
        repairOrderRegistry.updateRepairOrder(acceptedOrder);
    }

    public void orderRejected(RepairOrderDTO selectedRepairOrder) {
        // Update order state to "Rejected" and persist
        RepairOrderDTO rejectedOrder = createUpdatedOrder(selectedRepairOrder, "Rejected");
        repairOrderRegistry.updateRepairOrder(rejectedOrder);
    }

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
