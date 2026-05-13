package se.kth.iv1350.repairshop.model;

public interface DiscountStrategy {

    boolean isSeasonalPeriod();
    double applyDiscount(double totalCost);

}
