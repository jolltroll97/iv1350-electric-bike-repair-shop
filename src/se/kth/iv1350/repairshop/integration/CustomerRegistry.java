package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.BikeDTO;

import java.util.ArrayList; 
import java.util.List;

/**
 * A registry for managing customer information.
 */
public class CustomerRegistry {
    
    private List<CustomerDTO> customers = new ArrayList<>();

    public CustomerRegistry(){
        BikeDTO bikeOne = new BikeDTO("Crescent", "Elody", "1234");
        BikeDTO bikeTwo = new BikeDTO("Crescent", "Elist", "1235");
        BikeDTO bikeThree = new BikeDTO("Crescent", "Eli", "1236");
        
        this.customers.add(new CustomerDTO("Douglas Andersson", 701234566, "douglas.andersson0@gmail.com", bikeOne));
        this.customers.add(new CustomerDTO("Linus Sandin", 702345677, "linus.sandin1@gmail.com", bikeTwo));
        this.customers.add(new CustomerDTO("Liza Rudaya", 703456777, "liza.rudaya@gmail.com", bikeThree));
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
