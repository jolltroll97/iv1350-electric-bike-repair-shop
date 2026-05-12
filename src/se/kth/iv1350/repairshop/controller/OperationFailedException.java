package se.kth.iv1350.repairshop.controller;

/**
 * Simplified / alternate error messages for the View layer.
 */

public class OperationFailedException extends Exception{

    /**
     * Acts as a middle man between integration and controller.
     * 
     * @param msg   The message to be logged in the error log file.
     * @param cause Description of the cause of the error.
     */

    public OperationFailedException (String msg, Exception cause){
        super(msg, cause);
    }
}
