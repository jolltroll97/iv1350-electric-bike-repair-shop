package se.kth.iv1350.repairshop.dto;
/**
 * Contains information about one specific repair task
 */
public class RepairTaskDTO {
    
    private final String description;
    private final int cost;
    private final int time;

    /**
     * Creates a new instance representing a specific repair task
     * 
     * @param description   Description of the repair task.
     * @param cost          The price of the repair task.
     * @param time          The estimated time it takes for the technician
     *                      to complete the repair task.
     */

    public RepairTaskDTO(String description, int cost, int time){
        this.description = description;
        this.cost = cost;
        this.time = time;
    }

    /**
     * Getters for all attributes.
     */

    public String getDescription(){
        return description;
    }

    public int getCost(){
        return cost;
    }

    public int getTime(){
        return time;
    }

    /**
     * Method for printing the DTOs attributes.
     */

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append("description: " + description + ", ");
        builder.append("cost: " + cost + ", ");
        builder.append("time: " + time + ", ");
               
        return builder.toString();
    }

}
