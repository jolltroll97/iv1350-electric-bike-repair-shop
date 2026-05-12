package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.integration.CustomerRegistry;
import se.kth.iv1350.repairshop.integration.CustomerNotFoundException;
import se.kth.iv1350.repairshop.integration.DatabaseFailureException;
import se.kth.iv1350.repairshop.dto.CustomerDTO;

/**
 * 
 */

public class CustomerRegistryTest {

    private CustomerRegistry testRegistry;

    @BeforeEach
    public void setUp(){
        testRegistry = CustomerRegistry.getInstance();
    }

    @AfterEach
    public void tearDown(){
        testRegistry = null;
    }

    @Test
    public void testFindCustomer() throws Exception {
        System.out.println(">>> RUNNING TEST: findCustomer <<<");

        int phoneNumSearch = 701234566; // Douglas Andersson
        CustomerDTO foundCustomer = testRegistry.findCustomer(phoneNumSearch);

        assertNotNull(foundCustomer, "The registry should have found a customer, but returned null.");
        assertEquals("Douglas Andersson", foundCustomer.getName(), "The registry found a customer, but the name was wrong.");
        
    }

    @Test
    public void testFindCustomerThrowsCustomerNotFoundException() {
        System.out.println(">>> RUNNING TEST: findCustomerThrowsCustomerNotFoundException <<<");

        int fakePhone = 999999999;

        CustomerNotFoundException exception = assertThrows(
            CustomerNotFoundException.class, 
            () -> testRegistry.findCustomer(fakePhone),
            "Expected findCustomer to throw CustomerNotFoundException, but it didn't."
        );

        assertEquals(String.valueOf(fakePhone), exception.getSearchedPhoneNumber(), 
            "The exception did not store the correct phone number.");
    }

    @Test
    public void testFindCustomerThrowsDatabaseFailureException() {
        System.out.println(">>> RUNNING TEST: findCustomerThrowsDatabaseFailureException <<<");

        int poisonPillPhoneNum = 666;

        DatabaseFailureException exception = assertThrows(
            DatabaseFailureException.class,
            () -> testRegistry.findCustomer(poisonPillPhoneNum),
            "Expected findCustomer to throw DatabaseFailureException for phone number 666."
        );

        assertTrue(exception.getMessage().contains("Cannot access"), 
            "The exception message should mention the lost connection.");
    }
}
