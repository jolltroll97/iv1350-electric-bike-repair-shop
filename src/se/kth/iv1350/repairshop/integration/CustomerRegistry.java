package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import java.util.ArrayList; 
import java.util.List;

/**
 * A registry for managing customer information.
 */
public class CustomerRegistry {
    
    private List<CustomerDTO> customers = new ArrayList<>();

    CustomerRegistry(){
        /* Constructor, vet ej vad som ska vara i */
    }

    /**
     * Searches for a customer by their phone number.
     * @param phoneNum The phone number of the customer to find.
     * @return All customer information
     */
    public CustomerDTO findCustomer(int phoneNum){

        for(CustomerDTO customer : customers){

            if(customer.getPhoneNum()== phoneNum){
                return customer;
            }

        }

        return null;
    }

}
