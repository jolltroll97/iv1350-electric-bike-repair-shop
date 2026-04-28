package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;

import java.util.ArrayList; 
import java.util.List;

public class RepairOrderRegistryTest {

    private RepairOrderRegistry repairOrderRegistry;
    private CustomerDTO firstTestCustomer;
    private CustomerDTO secondTestCustomer;
    private RepairOrderDTO firstTestRepairOrder;
    private RepairOrderDTO secondTestRepairOrder;

    @BeforeEach
    public void setUp() {

        repairOrderRegistry = new RepairOrderRegistry();

        firstTestCustomer = new CustomerDTO("Jane Doe", 701234567, null, null);

        firstTestRepairOrder = new RepairOrderDTO(null, 20260428, 1000, "Broken battery", "Finished", firstTestCustomer, 1);
        secondTestRepairOrder = new RepairOrderDTO(null, 20260427, 1500, "Flat tires", "Finished", firstTestCustomer, 2);

    }
    
    @Test
    public void testCreateRepairOrder() {
        int firstRepairOrderId = repairOrderRegistry.createRepairOrder(firstTestCustomer, 20260427, "Broken battery");
        int secondRepairOrderId = repairOrderRegistry.createRepairOrder(secondTestCustomer, 20260428, "Loud rattling noise");

        assertEquals(1, firstRepairOrderId);
        assertEquals(2, secondRepairOrderId);

        RepairOrderDTO firstRepairOrder = repairOrderRegistry.getById(firstRepairOrderId);
        RepairOrderDTO secondRepairOrder = repairOrderRegistry.getById(secondRepairOrderId);

        assertNotNull(firstRepairOrder, "First repair order not found");
        assertNotNull(secondRepairOrder, "Second repair order not found");

    }

    @Test
    public void testGetById() {

        RepairOrderDTO firstRepairOrder = repairOrderRegistry.getById(1);
        RepairOrderDTO secondRepairOrder = repairOrderRegistry.getById(2);

        assertNotNull(firstRepairOrder, "First repair order not found");
        assertNotNull(secondRepairOrder, "Second repair order not found");

    }

    @Test
    public void testGetByPhoneNum() {

        ArrayList<RepairOrderDTO> testRepairOrderList = new ArrayList<>(repairOrderRegistry.getByPhoneNum(701234567));
        assert


    }

    @Test
    public void testRetrieveRepairOrderList() {

    }

    @Test
    public void testUpdateRepairOrderDiagnostic() {

    }

    @Test
    public void testUpdateRepairOrderState() {

    }
}
