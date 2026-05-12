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

    @Test
    public void testGetCustomerRegistryReturnsSameInstance() {
        CustomerRegistry firstCall = instance.getCustomerRegistry();
        CustomerRegistry secondCall = instance.getCustomerRegistry();
        assertSame(firstCall, secondCall, 
                   "Should return the same CustomerRegistry instance");
    }
    
    @Test
    public void testGetRepairOrderRegistryReturnsSameInstance() {
        RepairOrderRegistry firstCall = instance.getRepairOrderRegistry();
        RepairOrderRegistry secondCall = instance.getRepairOrderRegistry();
        assertSame(firstCall, secondCall, 
                   "Should return the same RepairOrderRegistry instance");
    }
    
    @Test
    public void testRegistryCreatorReturnsSingletonInstance() {
        
        RegistryCreator firstCreator = new RegistryCreator();
        RegistryCreator secondCreator = new RegistryCreator();
        
        
        CustomerRegistry firstCustomerRegistry = firstCreator.getCustomerRegistry();
        CustomerRegistry secondCustomerRegistry = secondCreator.getCustomerRegistry();
        
        
        assertSame(firstCustomerRegistry, secondCustomerRegistry,
                   "Because CustomerRegistry is a Singleton, both creators should return the exact same instance.");
    }
}