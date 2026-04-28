package se.kth.iv1350.repairshop.model;

import se.kth.iv1350.repairshop.dto.RepairTaskDTO;
import java.util.List;
import java.util.ArrayList;

public class RepairOrder {
    public int calculateTotal(List<RepairTaskDTO> tasks) {
        int totalCost = 0;
        for (RepairTaskDTO task : tasks) {
            totalCost += task.getCost();
        }
        return totalCost;
    }
}
