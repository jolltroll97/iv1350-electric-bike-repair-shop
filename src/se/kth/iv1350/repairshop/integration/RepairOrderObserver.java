package se.kth.iv1350.repairshop.integration;

import se.kth.iv1350.repairshop.dto.RepairOrderDTO;

/**
 * Observer interface for receiving notifications about repair order updates.
 * Implementations must never call the controller or any other class,
 * but only be updated using the Observer pattern.
 */
public interface RepairOrderObserver {
    
    /**
     * Called when a repair order is created.
     * 
     * @param repairOrder The newly created repair order.
     */
    void repairOrderCreated(RepairOrderDTO repairOrder);
    
    /**
     * Called when a repair order is updated (diagnostic report or state changed).
     * 
     * @param repairOrder The updated repair order.
     */
    void repairOrderUpdated(RepairOrderDTO repairOrder);
}
