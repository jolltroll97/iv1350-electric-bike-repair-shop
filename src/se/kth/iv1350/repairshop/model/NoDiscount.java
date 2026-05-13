package se.kth.iv1350.repairshop.model;

public class NoDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalCost){
        return totalCost;
    }

}
