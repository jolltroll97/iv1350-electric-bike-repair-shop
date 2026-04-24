package se.kth.iv1350.repairshop.model;

import se.kth.iv1350.repairshop.dto.RepairTaskDTO;

public class RepairOrder {
    public int calculateTotal(RepairTaskDTO[] cost) {
        int totalCost = 0;
        for (RepairTaskDTO task : cost) {
            totalCost += task.getCost();
        }
        return totalCost;
    }
}
