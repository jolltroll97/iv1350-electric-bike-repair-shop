package se.kth.iv1350.repairshop.dto;

/**
 * Data Transfer Object for representing a bike in the repair shop system.
 */
public class BikeDTO {

    private String brand;
    private String model;
    private String serialNum;

    /**
     * Constructs a new BikeDTO with the specified attributes.
     * @param brand The brand of the bike.
     * @param model The model of the bike.
     * @param serialNum The serial number of the bike.
     */
    public BikeDTO(String brand, String model, String serialNum){
        this.brand = brand;
        this.model = model;
        this.serialNum = serialNum;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getSerialNum() { return serialNum; }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

            builder.append("Brand: ").append(brand).append("\n")
                    .append("Model: ").append(model).append("\n")
                    .append("Serial number: ").append(serialNum).append("\n");

        return builder.toString();
    }

}
