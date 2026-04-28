package test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.repairshop.integration.RegistryCreator;
import se.kth.iv1350.repairshop.integration.CustomerRegistry;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;

public class RegistryCreatorTest {
    private RegistryCreator instance;

    @BeforeEach
    public void setUp() {
        instance = new RegistryCreator();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetCustomerRegistry() {
        CustomerRegistry result = instance.getCustomerRegistry();
        assertNotNull(result, "getCustomerRegistry should not return null");
    }

    @Test
    public void testGetRepairOrderRegistry() {
        RepairOrderRegistry result = instance.getRepairOrderRegistry();
        assertNotNull(result, "getRepairOrderRegistry should not return null");
    }
}
