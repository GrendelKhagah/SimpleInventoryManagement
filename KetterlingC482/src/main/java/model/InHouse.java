package model;
/**
 * Inhouse Class is for parts that are made within the company
 * Extends Part Class, adding a machineId. 
 * @version     1.0
 * @author      Taylor Ketterling
 */
public class InHouse extends Part{
    private int machineId; //Machine ID for inhouse Part, Private
    
    /**
     * Constructs a new instance of an Inhouse object.
     *
     * @param id            Product ID number, unique
     * @param name          Product Name
     * @param price         Product's price
     * @param stock         Inventory remaining
     * @param min           Min Inventory allowed
     * @param max           Max Inventory allowed(integer)
     * @param machineId     The MachineId for which the part was built off of
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    
    /**
     * Sets the machine ID for the inhoused part
     * @param machineId     MachineId part was made on
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
    
    /**
     * Gets the machine ID of this inhoused part
     * @return              The Machine Id of the part
     */
    public int getMachineId() {
        return machineId;
    }
}