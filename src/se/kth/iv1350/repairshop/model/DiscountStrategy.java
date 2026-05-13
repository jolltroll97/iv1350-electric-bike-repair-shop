package se.kth.iv1350.repairshop.model;

/**
 * Defines the interface for different discount strategies.
 */
public interface DiscountStrategy {

    boolean isSeasonalPeriod();
    double applyDiscount(double totalCost);

}
