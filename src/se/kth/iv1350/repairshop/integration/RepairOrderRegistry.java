package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import java.util.LinkedList; 
import java.util.List;

public class RepairOrderRegistry{

    private List<RepairOrderDTO> repairOrders = new LinkedList<>();
    private int repairId = 1; /* Satte denna som 1 för vi inte vet hur många ordrar sen innan */

    RepairOrderRegistry(){
        /* Constructor, vet ej vad som ska vara i */
    }

    public void createRepairOrder(CustomerDTO customer, int date, String repairReport){

        RepairOrderDTO currentRepairOrder = new RepairOrderDTO(
            null,
            date,
            0,
            repairReport,
            "Newly Created", /* Osäker om detta är rätt för state */
            customer,
            repairId

        )

        repairOrders.add(currentRepairOrder);
        repairId++;
  
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

    /* OBS! samma metod finns i customerRegistry men här går vi igenom
    listan för repairOrders istället för listan med customers. Är detta duplicated code? */
    public List<RepairOrderDTO> getByPhoneNum(int phoneNum){

        for(CustomerDTO customer : repairOrders){

            if(customer.getPhoneNum()== phoneNum){
                return customer;
            }

        }

    }



}