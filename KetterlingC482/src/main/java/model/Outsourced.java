package model;
/**
 * Outsourced Class is for parts that are outsourced to other companies.
 * Extends Part Class, adding a companyName. 
 * @version     1.0
 * @author      Taylor Ketterling
 */
public class Outsourced extends Part{
    private String companyName; //Company Name for outsourced Part, Private
    /**
     * Constructs a new instance of an outsourced object.
     *
     * @param id            Product ID number, unique
     * @param name          Product Name
     * @param price         Product's price
     * @param stock         Inventory remaining
     * @param min           Min Inventory allowed
     * @param max           Max Inventory allowed(integer)
     * @param companyName   Company's name responsible for outsourced part, String
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    
    /**
     * Sets the companyName for the outsourced part
     *
     * @param companyName   Name of 3rd party company responsible for outsourced part
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    /**
     * Get the Company's name
     *
     * @return Name of Company responsible for outsourced part.
     */
    public String getCompanyName() {
        return companyName;
    }
}