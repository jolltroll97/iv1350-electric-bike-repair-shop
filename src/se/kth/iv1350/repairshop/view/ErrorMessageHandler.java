package se.kth.iv1350.repairshop.view;

import java.time.LocalDateTime;

/**
 * Handles error messages caused by exceptions.
 */

public class ErrorMessageHandler {

    /**
     * Displays error messages to the View layer.
     * 
     * @param msg   The message to be displayed.
     */

    public void showErrorMsg(String msg){
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(LocalDateTime.now());
        errorMsgBuilder.append(", ERROR: ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder.toString());
    }
}
