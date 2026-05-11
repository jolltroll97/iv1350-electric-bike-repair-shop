package se.kth.iv1350.repairshop.dto;
import se.kth.iv1350.repairshop.dto.BikeDTO;

/**
 * Contains information about a customer, such as name, phone number, email and the bike they own. 
 * This class is used to transfer data between the different layers of the application.
 */
public class CustomerDTO {
    private final String name;
    private final int phoneNum;
    private final String email;
    private final BikeDTO bikeDTO;

    /**
     *  Creates a new instance representing a specific customer.
     * @param name The name of the customer.
     * @param phoneNum The phone number of the customer.
     * @param email The email address of the customer.
     * @param bikeDTO The bike owned by the customer. Contains information about the bike's brand, model and serial number.
     */
    public CustomerDTO(String name, int phoneNum, String email, BikeDTO bikeDTO) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
        this.bikeDTO = bikeDTO;
    }

    /**
     * Getters for all attributes.
     */
    public String getName() { return name; }
    public int getPhoneNum() { return phoneNum; }
    public String getEmail() { return email; }
    public BikeDTO getBikeDTO() { return bikeDTO; }

    /** 
     * method to print all information about the customer in a readable format.
    */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

            builder.append("name: " + name + ", ");
            builder.append("phoneNum: " + phoneNum + ", ");
            builder.append("email: " + email + ", ");
            builder.append("bike: " + bikeDTO + ", ");

        return builder.toString();
    }
    
}


