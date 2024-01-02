package model;
/**
 * Abstract class for a generic part
 * This class provides st
 * extends to InHourse and Outsourced
 * 
 * @version     1.0
 * @author      Taylor Ketterling
 */
public abstract class Part {
    private int id;         // Part ID, private
    private String name;    // Part Name, private
    private double price;   // Part Price, private
    private int stock;      // Part inventory available, private
    private int min;        // Part minimum invitory allowed, private
    private int max;        // Part Max invitory allowed, private
    
    /**
     * Constructs a new instance of a Part.
     *
     * @param id            Product ID number, unique
     * @param name          Product Name
     * @param price         Product's price
     * @param stock         Inventory remaining
     * @param min           Min Inventory allowed
     * @param max           Max Inventory allowed(integer)
     */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    /**
     * Sets the part's ID.
     *
     * @param id the unique identifier to set for the part
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Sets the Part's Name
     * 
     * @param name the name of the Part
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the Part's Price
     * 
     * @param price the price to set for the part
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Sets the total stock available for the part
     * 
     * @param stock the amount inventory for a part
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**
     * Sets the minimum stock amount for a part
     * 
     * @param min the minimum allowable stock
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**
     * Sets the Max stock amount for a part
     *
     * @param max the max allowable stock
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * Returns the Part ID
     * 
     * @return id of part
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the Part Name
     * 
     * @return the part name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Part's Price
     * 
     * @return price of part
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the part's available stock
     * 
     * @return stock of part
     */
    public int getStock() {
        return stock;
    }

    /**
     * Returns min allowed stock for part
     * 
     * @return the min stock
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns max allowed stock for part
     * 
     * @return the max stock
     */
    public int getMax() {
        return max;
    }
}