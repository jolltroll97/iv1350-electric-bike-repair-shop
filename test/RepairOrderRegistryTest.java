package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.repairshop.dto.CustomerDTO;
import se.kth.iv1350.repairshop.dto.RepairOrderDTO;
import se.kth.iv1350.repairshop.dto.BikeDTO;
import se.kth.iv1350.repairshop.integration.RepairOrderRegistry;

import java.util.ArrayList; 
import java.util.List;

public class RepairOrderRegistryTest {

    private RepairOrderRegistry repairOrderRegistry;
    private CustomerDTO firstTestCustomer;
    private CustomerDTO secondTestCustomer;
    private RepairOrderDTO firstTestRepairOrder;
    private RepairOrderDTO secondTestRepairOrder;

    @BeforeEach
    public void setUp() {

        repairOrderRegistry = new RepairOrderRegistry();

        firstTestRepairOrder = new RepairOrderDTO(null, 20260428, 1000, "Broken battery", "Finished", firstTestCustomer, 1);
        secondTestRepairOrder = new RepairOrderDTO(null, 20260427, 1500, "Flat tires", "Finished", firstTestCustomer, 2);

    }
    
    @Test
    public void testCreateRepairOrder() {
        System.out.println("<<<<<<<<<< Test for createRepairOrder() >>>>>>>>>>");

        int firstRepairOrderId = repairOrderRegistry.createRepairOrder(firstTestCustomer, 20260427, "Broken battery");
        int secondRepairOrderId = repairOrderRegistry.createRepairOrder(secondTestCustomer, 20260428, "Loud rattling noise");

        assertEquals(1, firstRepairOrderId);
        assertEquals(2, secondRepairOrderId);

        RepairOrderDTO firstRepairOrder = repairOrderRegistry.getById(firstRepairOrderId);
        RepairOrderDTO secondRepairOrder = repairOrderRegistry.getById(secondRepairOrderId);

        assertNotNull(firstRepairOrder, "First repair order not found");
        assertNotNull(secondRepairOrder, "Second repair order not found");

    }

    @Test
    public void testGetById() {
        System.out.println("<<<<<<<<<< Test for getById() >>>>>>>>>>");

        RepairOrderDTO firstRepairOrder = repairOrderRegistry.getById(1);
        RepairOrderDTO secondRepairOrder = repairOrderRegistry.getById(2);

        assertNotNull(firstRepairOrder, "First repair order not found");
        assertNotNull(secondRepairOrder, "Second repair order not found");

    }

    @Test
    public void testGetByPhoneNum() {

        System.out.println("<<<<<<<<<< Test for getByPhoneNum() >>>>>>>>>>");

        int phoneNum = 701234567;
        repairOrderRegistry.createRepairOrder(firstTestCustomer, phoneNum, null);


        ArrayList<RepairOrderDTO> testRepairOrderList = repairOrderRegistry.getByPhoneNum(phoneNum);

        assertFalse(testRepairOrderList.isEmpty(), "List empty; list not created properly");
        assertEquals(1, testRepairOrderList.size(), "Only one repair order associated with this phone number");

        assertEquals(phoneNum, testRepairOrderList.getCustomer().getPhoneNum(), "Phone number does not match customer");

    }

    @Test
    public void testRetrieveRepairOrderList() {

        System.out.println("<<<<<<<<<< Test for retrieveRepairOrderList() >>>>>>>>>>");

        repairOrderRegistry.add(firstTestRepairOrder);
        repairOrderRegistry.add(secondTestRepairOrder);
         ArrayList<RepairOrderDTO> testStateListRepairOrders = repairOrderRegistry.retrieveRepairOrderList("Newly created");

         assertFalse(testStateListRepairOrders.isEmpty(), "List empty, list not created properly");
         assertEquals(2, testStateListRepairOrders.size(), "Two repair orders with 'Newly created' state");

         assertEquals("Newly created", testStateListRepairOrders.getRepairOrder().getState(), "State does not match; wrong state");


    }

    @Test
    public void testUpdateRepairOrderDiagnostic() {

        System.out.println("<<<<<<<<<< Test for updateRepairOrderDiagnostic() >>>>>>>>>>");

        int id = repairOrderRegistry.createRepairOrder(firstTestCustomer, 20260428, "Bike won't start");

        RepairOrderDTO originalRepairOrder = repairOrderRegistry.getById(id);

        RepairOrderDTO repairOrderWithDiagnostic = new RepairOrderDTO("Change battery", originalRepairOrder.getDate(), 1000, originalRepairOrder.getRepairReport(), "Ready for approval", firstTestCustomer, id);

        repairOrderRegistry.updateRepairOrderDiagnostic(repairOrderWithDiagnostic);

        RepairOrderDTO  updatedRepairOrder = repairOrderRegistry.getById(id);

        assertEquals("Change battery", updatedRepairOrder.getReportDTO(), "Diagnostic report did not update");

    }

    @Test
    public void testUpdateRepairOrderState() {

        System.out.println("<<<<<<<<<< Test for updateRepairOrderState() >>>>>>>>>>");

        RepairOrderDTO repairOrderWithNewState =  repairOrderRegistry.updateRepairOrderState(firstTestRepairOrder, "Accepted");

        assertEquals("Accepted", repairOrderWithNewState.getState(), "State did not update");


    }
}
