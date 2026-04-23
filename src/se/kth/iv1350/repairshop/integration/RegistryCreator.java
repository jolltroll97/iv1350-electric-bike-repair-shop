package se.kth.iv1350.repairshop.integration;

public class RegistryCreator {
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;


    /**
     * Creates new instances of the registries available in the database.
     */
    public RegistryCreator(){
        this.customerRegistry = new CustomerRegistry();
        this.repairOrderRegistry = new RepairOrderRegistry();
    }

    /**
     * Gets the registry containing all customer data.
     */
    public CustomerRegistry getCustomerRegistry(){
        return this.customerRegistry;
    }
    
    /**
     * Gets the registry containing the data for all repair orders.
     */
    public RepairOrderRegistry getRepairOrderRegistry(){
        return this.repairOrderRegistry;
    }
}
