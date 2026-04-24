package se.kth.iv1350.repairshop.dto;

public class BikeDTO {

    private String brand;
    private String model;
    private String serialNum;

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
