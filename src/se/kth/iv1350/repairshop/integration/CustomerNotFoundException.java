package se.kth.iv1350.repairshop.integration;

/**
 * Thrown when a search for a customer in the database yields no results.
 */

public class CustomerNotFoundException extends Exception {

    private String searchedPhoneNumber;
    
    /**
     * Create a new instance representing the condition where a customer
     * could not be found.
     * 
     * @param searchedPhoneNumber   The phone number that was searched for.
     */

    public CustomerNotFoundException(String searchedPhoneNumber){

        super("Could not find a customer with phone number: " + searchedPhoneNumber);
        
        this.searchedPhoneNumber = searchedPhoneNumber;
    }

    /**
     * Gets the phone number that caused this exception.
     * 
     * @return  The phone number that was searched for.
     */

    public String getSearchedPhoneNumber(){
        return searchedPhoneNumber;
    }
}
