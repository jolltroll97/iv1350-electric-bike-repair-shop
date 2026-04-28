package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.model.OrderHandler;
import se.kth.iv1350.repairshop.integration.RegistryCreator;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;
import se.kth.iv1350.repairshop.integration.Printer;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;

public class OrderHandlerTest {

    private OrderHandler testHandler;
    private RepairOrderRegistry testRegistry;

    @BeforeEach
    public void setUp() {
        // ARRANGE: Set up the environment with fresh instances
        RegistryCreator creator = new RegistryCreator();
        testRegistry = creator.getRepairOrderRegistry();
        Printer testPrinter = new Printer();
        
        testHandler = new OrderHandler(testPrinter, testRegistry);
    }

    @AfterEach
    public void tearDown() {
        testHandler = null;
        testRegistry = null;
    }

    @Test
    void testOrderAccepted() {
        // ARRANGE: Create a fake customer and bike
        BikeDTO dummyBike = new BikeDTO("Crescent", "Elist", "1122");
        CustomerDTO dummyCustomer = new CustomerDTO("Alice", 111222, "alice@email.com", dummyBike);
        
        // Put a brand new order into our fake database and get its ID
        int orderId = testRegistry.createRepairOrder(dummyCustomer, 20231101, "Flat tire");
        
        // Fetch the DTO from the database so we have an order to accept
        RepairOrderDTO orderToAccept = testRegistry.getById(orderId);

        // ACT: Tell the handler the customer accepted the price!
        testHandler.orderAccepted(orderToAccept);

        // ASSERT: Fetch the order from the database AGAIN to prove the handler saved the update
        RepairOrderDTO updatedOrder = testRegistry.getById(orderId);
        
        assertEquals("Accepted", updatedOrder.getState(), "The order state should have been updated to 'Accepted' in the registry.");
    }

    @Test
    void testOrderRejected() {
        // ARRANGE: Create a fake customer and bike
        BikeDTO dummyBike = new BikeDTO("Monark", "Karin", "3344");
        CustomerDTO dummyCustomer = new CustomerDTO("Bob", 333444, "bob@email.com", dummyBike);
        
        // Put a brand new order into our fake database and get its ID
        int orderId = testRegistry.createRepairOrder(dummyCustomer, 20231102, "Broken chain");
        
        // Fetch the DTO from the database
        RepairOrderDTO orderToReject = testRegistry.getById(orderId);

        // ACT: Tell the handler the customer rejected the price!
        testHandler.orderRejected(orderToReject);

        // ASSERT: Fetch the order from the database AGAIN to prove the handler saved the update
        RepairOrderDTO updatedOrder = testRegistry.getById(orderId);
        
        assertEquals("Rejected", updatedOrder.getState(), "The order state should have been updated to 'Rejected' in the registry.");
    }
}