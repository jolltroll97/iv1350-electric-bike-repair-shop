package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;

public class RepairOrderRegistry{

    RepairOrderRegistry(){
        /* Constructor, vet ej vad som ska vara i */
    }

    public void createRepairOrder(CustomerDTO customer, int date, String repairReport){

        
    }

    /* Ska det vara RepairDTO lista istället?? */
    public RepairDTO retrieveRepairOrderList(String state){

        /* temporär return */
        return null;
    }

    public void updateRepairOrder(RepairOrderDTO newRepairOrder){


    }

    public RepairOrderDTO getById(int repairOrderId){

        
    }

    public List<RepairOrderDTO> getByPhoneNum(int phoneNum){


    }



}