package se.kth.iv1350.repairshop.model;

import java.time.LocalDate;
import java.time.Month;


public class SeasonalDiscount implements DiscountStrategy {

    public boolean isSeasonalPeriod(){

        Month currentMonth = LocalDate.now().getMonth();
        return currentMonth == Month.NOVEMBER ||
                currentMonth == Month.DECEMBER ||
                currentMonth == Month.JANUARY ||
                currentMonth == Month.FEBRUARY;

    }

    @Override
    public double applyDiscount(double totalCost){
        if(isSeasonalPeriod()){
            return totalCost * 0.8;
        }
        return totalCost;
    }
    
}

