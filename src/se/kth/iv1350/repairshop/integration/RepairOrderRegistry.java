package se.kth.iv1350.repairshop.integration;
import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import java.util.ArrayList; 
import java.util.List;

public class RepairOrderRegistry{

    private List<RepairOrderDTO> repairOrders = new ArrayList<>();
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
            "Awaiting Diagnostic", /* Osäker om detta är rätt för state */
            customer,
            repairId

        )

        repairOrders.add(currentRepairOrder);
        repairId++;
  
    }

    public List<RepairOrderDTO> retrieveRepairOrderList(String state){

        List<RepairOrderDTO> stateListRepairOrders = new ArrayList<>();

        for(RepairOrderDTO searchRepairOrders : repairOrders){
            if(searchRepairOrders.getState() == "Awaiting Diagnostic"){
                stateListRepairOrders.add(searchRepairOrders);
            }

        }
        return stateListRepairOrders;

    }

    public void updateRepairOrderDiagnostic(RepairOrderDTO newRepairOrder){

        for(int i = 0; i < repairOrders.size(); i++){

            if(repairOrders.get(i).getRepairId() == newRepairOrder.getRepairId()){

                repairOrders.set(i, newRepairOrder);
                return;

            }

        }


    }
       
    public void updateRepairOrderState(RepairOrderDTO selectedRepairOrder){

        for(RepairOrderDTO updateState : repairOrders){


            
        }

    }

    public RepairOrderDTO getById(int repairOrderId){

        for(RepairOrderDTO repairOrderById : repairOrders){
            if(repairOrderById.getRepairId() == repairOrderId){
                return repairOrderById;
            }
        }

        return null;
        
    }

    public List<RepairOrderDTO> getByPhoneNum(int phoneNum){

        List <RepairOrderDTO> foundRepairOrders = new ArrayList<>();

        for(RepairOrderDTO customerRepairOrder : repairOrders){

            if(customerRepairOrder.getCustomer().getPhoneNum() == phoneNum){
                foundRepairOrders.add(customerRepairOrder);
            }

        }

        return foundRepairOrders;

    }



}