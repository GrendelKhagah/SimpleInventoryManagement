package model;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
/**
 * Class Inventory
 * 
 * @version     1.0
 * @author      Taylor Ketterling
 */
public class Inventory{
    /* an Observable List for storing associated Products, static Global */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    /* an Observable List for storing associated , static Global */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
       
    /**
     * Adds a newPart to allProducts
     * 
     * @param newPart the part to add to the allParts list
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    
    /**
     * Adds a newProduct to allProducts
     * 
     * @param newProduct the product to add to the allProducts list
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    
    /**
     * Looks through allParts to find the matching PartId
     *
     * @param partID    the Part Id to be looked up
     * @return          Null if not found, returns part if ID found.
     */
    public static Part lookupPart(int partID) {
        Part temp = null;
        for (Part part : allParts) {
            if (part.getId() == partID) {
                temp = part;
            }
        } return temp;
    }
    
    /**
     * Looks through allProducts to find matching ProductIds
     *
     * @param productID     the Product Id to be looked up
     * @return              Null if not found, product if ID found
     */
    public static Product lookupProduct(int productID) {
        Product temp = null;
        for (Product product : allProducts) {
            if (product.getId() == productID) {
                temp = product;
            }
        } return temp;
    }
    
    /**
     * Looks through allParts to find matching Part names
     * Assist with searching for parts by name
     * @param partName      the Part name to be looked up
     * @return              empty if nothing found, a PartList if items with name found
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> temp = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().equals(partName)) {
                temp.add(part);
            }
        } return temp;
    }
    
    /**
     * Looks through allProducts to find matching Product names
     * Assist with searching for products by name
     * @param productName      the Product name to be looked up
     * @return                  empty if nothing found, a ProductList if items with name found
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> temp = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                temp.add(product);
            }
        } return temp;
    }
    
    /**
     * Replaces the part at index with the new slectedPart
     * Can be used to update a part.
     * 
     * @param index         the index to be updated
     * @param selectedPart  new part to be place in at index
     */
    public static void updatePart(Integer index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }
    
    /**
     * Replaces the product at index with the new slectedProduct
     * Can be used to update a product.
     * 
     * @param index             the index to be updated
     * @param selectedProduct   new product to be place in at index
     */
    public static void updateProduct(Integer index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }
    
    /**
     * If the Part Exists in the list, it will be removed
     *
     * @param selectedPart  The part to be deleted
     * @return              True if deleted, False if not found in list
     */
    public static Boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } return false;
    }
    
    /**
     * If the Product  Exists in the list, it will be removed
     *
     * @param selectedProduct  The product to be deleted
     * @return                 True if deleted, False if not found in list
     */
    public static Boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        } return false;
    }
    
    /**
     * checks if data already exists, if so adds 1 to the largest ID to enable contiguous IDs
     * my thought for getting part ID, is i don't want to initialize the ID to 0, incase the program reboots.
     * Currently the stored data isn't persistent but im thinking ahead for persistent data,
     * you don't want to always start at 0
     *
     * if data does not exists. starts ID's at 0
     * @return   next available unique Part ID, contiguous
     */
    public static int NewPartID() {
        return allParts.isEmpty() ? 0 : allParts.stream().mapToInt(Part::getId).max().getAsInt() + 1;  
    }
    
    /**
     * checks if data already exists, if so adds 1 to the largest ID to enable contiguous IDs
     * 
     * if data does not exists. starts ID's at 0
     * @return   next available unique Product ID, contiguous
     */
    public static int NewProductID() {
        return allProducts.isEmpty() ? 0 : allProducts.stream().mapToInt(Product::getId).max().getAsInt() + 1;  
    }
    
    /*
     * Returns the entire Parts Lists
     *
     * @return allParts ObersvableList<Part>, empty if no Parts
     */
    public static ObservableList<Part> getAllParts() {return allParts;}
    
    /*
     * Returns the entire Product Lists
     *
     * @return allProduct ObersvableList<Product>, empty if no Products
     */
    public static ObservableList<Product> getAllProducts() {return allProducts;}
}