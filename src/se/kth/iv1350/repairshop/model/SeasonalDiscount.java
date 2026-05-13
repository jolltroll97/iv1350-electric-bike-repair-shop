package se.kth.iv1350.repairshop.model;

import java.time.LocalDate;
import java.time.Month;

/**
    * Implements a seasonal discount strategy that applies a discount during specific months of the year.
    * The discount is applied during the winter months providing a 20% discount on the total cost of the repair order.
 */
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

