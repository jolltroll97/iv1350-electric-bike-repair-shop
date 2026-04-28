package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.integration.CustomerRegistry;
import se.kth.iv1350.repairshop.dto.CustomerDTO;

/**
 * Since there is only one method to test, @BeforeAll and @AfterAll has been omitted.
 */

public class CustomerRegistryTest {

    private CustomerRegistry testRegistry;

    @BeforeEach
    public void setUp(){
        testRegistry = new CustomerRegistry();
    }

    @AfterEach
    public void tearDown(){
        testRegistry = null;
    }

    @Test
    public void testFindCustomer() {
        System.out.println(">>> RUNNING TEST: FindCustomer <<<");

        int phoneNumSearch = 701234566; // Douglas Andersson
        CustomerDTO foundCustomer = testRegistry.findCustomer(phoneNumSearch);

        assertNotNull(foundCustomer, "The registry should have found a customer, but returned null.");
        assertEquals("Douglas Andersson", foundCustomer.getName(), "The registry found a customer, but the name was wrong.");
        
    }

    @Test
    public void testFindCustomerThatDoesNotExist() {
        System.out.println(">>> RUNNING TEST: FindCustomerThatDoesNotExist <<<");

        
        int fakePhone = 999999999; // Fake phone number
        CustomerDTO foundCustomer = testRegistry.findCustomer(fakePhone);

        assertNull(foundCustomer, "The registry should have returned null for a fake number, but it returned a customer!");
    }
}
