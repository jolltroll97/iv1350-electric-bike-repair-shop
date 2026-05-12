package se.kth.iv1350.repairshop.view;

import java.time.LocalDateTime;

public class ErrorMessageHandler {
    public void showErrorMsg(String msg){
        StringBuilder errorMsgBuilder = new StringBuilder();
        errorMsgBuilder.append(LocalDateTime.now());
        errorMsgBuilder.append(", ERROR: ");
        errorMsgBuilder.append(msg);
        System.out.println(errorMsgBuilder.toString());
    }
}
