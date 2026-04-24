package se.kth.iv1350.repairshop.dto;
import se.kth.iv1350.repairshop.dto.BikeDTO;

public class CustomerDTO {
    private final String name;
    private final int phoneNum;
    private final String email;
    private final BikeDTO bikeDTO;

    public CustomerDTO(String name, int phoneNum, String email, BikeDTO bikeDTO) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.bikeDTO = bikeDTO;
    }

    public String getName() { return name; }
    public int getPhoneNum() { return phoneNum; }
    public String getEmail() { return email; }
    public BikeDTO getBikeDTO() { return bikeDTO; }
    
}


