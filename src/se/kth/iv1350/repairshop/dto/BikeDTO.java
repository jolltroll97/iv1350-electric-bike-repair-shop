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
}
