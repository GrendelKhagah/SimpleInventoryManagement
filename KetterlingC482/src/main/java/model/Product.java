package model;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
/**
 * Class Product
 * @version     1.0
 * @author      Taylor Ketterling
 */
public class Product{
    /** List of associated parts within a product */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    /** Product ID */
    private int id;
    
    /** Product's Name */
    private String name;
    
    /** Product's Price */
    private double price;
    
    /** Product's Available Stock */
    private int stock;
    
    /** Product's Min allowed Stock */
    private int min;
    
    /** Product's Max allowed stock */
    private int max;   
    
    /**
     * Constructs a new instance of a Product.
     *
     * @param id            Product ID number, unique
     * @param name          Product Name
     * @param price         Product's price
     * @param stock         Inventory remaining
     * @param min           Min Inventory allowed
     * @param max           Max Inventory allowed(integer)
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    /**
     * Sets the product's ID.
     *
     * @param id the unique identifier to set for the product
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Sets the Product's Name
     * 
     * @param name the name of the Product
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Sets the Product's Price
     * 
     * @param price the price to set for the product
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Sets the total stock available for the product
     * 
     * @param stock the amount inventory for a product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
     /**
     * Sets the minimum stock amount for a product
     * 
     * @param min the minimum allowable stock for the product
     */
    public void setMin(int min) {
        this.min = min;
    }
    
    /**
     * Sets the Max stock amount for a product
     *
     * @param max the max allowable stock for the product
     */
    public void setMax(int max) {
        this.max = max;
    }
    
    /**
     * Returns the Product ID
     * 
     * @return id of product
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the Product Name
     * 
     * @return the product's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Product's Price
     * 
     * @return price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the Product's available stock
     * 
     * @return stock of Product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Returns min allowed stock for product
     * 
     * @return the min stock allowed for product
     */
    public int getMin() {
        return min;
    }

    /**
     * Returns max allowed stock for product
     * 
     * @return the max stock of product allowed
     */
    public int getMax() {
        return max;
    }

    
    /**
     * Associates Parts to a product
     * 
     * @param toAdd, the Part to Associate to the Product
     */
    public void addAssociatedPart(Part toAdd) {
        associatedParts.add(toAdd);
    }
    
    /**
     * Deletes a part for a Product's Associated list of Parts
     * 
     * @param toDel, the part to remove
     * @return true if part removed, false if not
     */
    public boolean deleteAssociatePart(Part toDel) {
        if(associatedParts.contains(toDel)) {
            associatedParts.remove(toDel);
            return true;
        } return false;
        
    }
    
    /**
     * Returns the list of Associated parts within this Product
     * 
     * @return all associatedParts this product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
} // coffeeeee