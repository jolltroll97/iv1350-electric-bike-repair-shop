package se.kth.iv1350.repairshop.integration;


/**
 * Thrown when the database cannot be reached, or the connection fails.
 */

public class DatabaseFailureException extends Exception{
    
    /**
     * Creates a new instance representing a failure to connect to the database.
     * 
     * @param message The technical error message detailing the failure.
     */

    public DatabaseFailureException(String message){
        super(message);
    }
}
