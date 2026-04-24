package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import java.util.LinkedList; 
import java.util.List;

public class CustomerRegistry {
    
    private List<CustomerDTO> customers = new LinkedList<>();

    CustomerRegistry(){
        /* Constructor, vet ej vad som ska vara i */
    }

    public CustomerDTO findCustomer(int phoneNum){

        for(CustomerDTO customer : customers){

            if(customer.getPhoneNum()== phoneNum){
                return customer;
            }

        }

        return null;
    }

}
