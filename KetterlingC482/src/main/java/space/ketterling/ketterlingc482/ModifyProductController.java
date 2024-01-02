package space.ketterling.ketterlingc482;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 * FXML Controller class 
 *
 * @author Taylor Ketterling
 */
public class ModifyProductController implements Initializable {
    @FXML
    private TextField idField;
    
    @FXML
    private TextField nameField;

    @FXML
    private TextField invField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TextField maxField;
    
    @FXML
    private TextField minField;
    
    @FXML
    private TextField searchFieldParts;
    
    @FXML
    private TableView<Part> partsTable;
    
    @FXML
    private TableColumn<Part, Integer> PartIDColumn;
    
    @FXML
    private TableColumn<Part, String> PartNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> PartInvColumn;
    
    @FXML
    private TableColumn<Part, Double> PartCostColumn;
    
    @FXML
    private TableView<Part> AssociatedTable;
    
    @FXML
    private TableColumn<Part, Integer> asPartIDColumn;
    
    @FXML
    private TableColumn<Part, String> asPartNameColumn;
    
    @FXML
    private TableColumn<Part, Integer> asPartInvColumn;
    
    @FXML
    private TableColumn<Part, Double> asPartCostColumn;
    
    /**
     * The part object selected in the table view by the user.
     */
    private static Part partToSelect;

    /**
     * The associated part object selected in the table view by the user.
     */
    private static Part partToRemove;
    
    /**
     * A temp list for associating parts 
     */
    private static ObservableList<Part> tempAssociatedParts = FXCollections.observableArrayList();
    
    
    
    @FXML
    void cancelAction(ActionEvent event) throws IOException {
        returnToMain(event);
    }
    
     /**
     * Adds a selected part from the parts list to the temp associate parts list for further product creation.
     * 
     * @param event 
     */
    @FXML
    void partAddAction(ActionEvent event){
        partToSelect = partsTable.getSelectionModel().getSelectedItem();
        if (partToSelect == null) {
            displayAlert(3);
        } else {
            tempAssociatedParts.add(partToSelect);
        }
    }
    
    /**
     * removes an associated part from the associate parts list, used in product creation
     * 
     * @param event 
     */
    @FXML
    void asPartRemoveAction(ActionEvent event){
        partToRemove = AssociatedTable.getSelectionModel().getSelectedItem();
        if (partToRemove == null) {
            displayAlert(3);
        } else {
            tempAssociatedParts.remove(partToRemove);
            
        }
    }
    
    /**
     * Saves the user data 
     * 
     * @param event 
     */
    @FXML
    void saveAction(ActionEvent event){
        try {
            String Name = nameField.getText();
            int stock = Integer.parseInt(invField.getText());
            Double price = Double.parseDouble(priceField.getText());
            int max = Integer.parseInt(maxField.getText());
            int min = Integer.parseInt(minField.getText());
            int ID = Integer.parseInt(idField.getText());
            
            if (ValidData(min, max, stock, Name)) {
                Product updatedProduct = new Product( ID, Name, price, stock, min, max);
                for(Part part: tempAssociatedParts){
                    updatedProduct.addAssociatedPart(part);
                }
                Inventory.updateProduct(ID, updatedProduct);
                returnToMain(event);
            }
        } catch(NumberFormatException e) {
            displayAlert(5);  // Handles wrong format in number fields
        } catch (IOException e) {               
            displayAlert(2);  // Handles i/o Exceptions  
        }
        
    }
    
    /**
     * Transitions the user to main screen
     * 
     * @param event
     * @throws IOException 
     */
    private void returnToMain(ActionEvent event) throws IOException {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch(IOException e) {
            displayAlert(2);   // If an IOException occurs (like the file not found), display an alert
        } catch(Exception e) {
            displayAlert(3);   // RUNTIME ERROR Correction: handles bad page destinations, 
        }
    }
    
    /**
     * displayAlert will inform user of various errors or information
     * 
     * @case 1 missing part data
     * @case 2 something went wrong, IOException
     * @case 3 something went wrong, Exception
     * @case 4 invalid part data
     * @case 5 Issue when attempting to generate part from data
     */
     private void displayAlert(int alertCase) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        
        switch (alertCase) {
            case 1:
                infoAlert.setTitle("Information");
                infoAlert.setHeaderText("Missing Product Data");
                infoAlert.showAndWait();
                break;
            case 2:
                errorAlert.setTitle("Error IOException");
                errorAlert.setHeaderText("Something went wrong, could be bad input data");
                errorAlert.showAndWait();
                break;
            case 3:
                errorAlert.setTitle("Error Exception");
                errorAlert.setHeaderText("Something went wrong");
                errorAlert.showAndWait();
                break;
            case 4:
                errorAlert.setTitle("Invalid Data");
                errorAlert.setHeaderText("Min, Max or Stock or Name is not qualified data");
                errorAlert.showAndWait();
                break;
            case 5:
                errorAlert.setTitle("NumberFormatException");
                errorAlert.setHeaderText("Issue With Data when generating new part");
                errorAlert.showAndWait();
                break;
        }
    }
     
    /**
     * Searches through the parts list and updates table results
     * @param searchText The text to search for in product names or IDs.
     */
    @FXML
    public void searchParts(String searchText) {
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();

        // try to parse the search text as an integer for ID matching
        Integer searchId = null;
        try {
            searchId = Integer.parseInt(searchText);
        } catch (NumberFormatException e) {
            // searchText is not a valid integer, no action needed
            // searchId will remain null, 
        }

        for (Part part : Inventory.getAllParts()) {
            // Check if the part's name contains the searchText or the part's ID matches searchId 
            if (part.getName().toLowerCase().contains(searchText.toLowerCase()) || (searchId != null && part.getId() == searchId)) {
                filteredParts.add(part);
            }
        }

        // Update the parts table view to display only the filtered parts
        partsTable.setItems(filteredParts);
    }
    
    /**
     * ValidData checks name, min, max and stock to ensure valid data
     * 
     * @param min       minimum stock allowed
     * @param max       max stock allowed
     * @param stock     amount of inventory(available stock)
     * @param Name      name of product
     * @return          return false if invalid or missing data, true if good data
     */ 
    private boolean ValidData(int min, int max, int stock, String Name){
        if (min > max || stock < min || stock > max || Name.isEmpty()){
            displayAlert(4);
            return false;
        } return true;
    }
    
    
    /**
     * Initializes the controller class
     * This method is automatically called after the FXML file has been loaded
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known
     * @param rb The resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Product selectedProduct = MainController.getProductToModify();
        
        // Set the associated Parts to the parts of the product to modify.
        tempAssociatedParts = selectedProduct.getAllAssociatedParts();
        
        // Sets up the parts table
        partsTable.setItems(Inventory.getAllParts());
        PartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        // Sets up the associated parts table for the soon to be created product
        AssociatedTable.setItems(tempAssociatedParts);
        asPartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        asPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        asPartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        asPartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        //Sets values to products current values. 
        idField.setText(String.valueOf(selectedProduct.getId()));
        nameField.setText(selectedProduct.getName());
        invField.setText(String.valueOf(selectedProduct.getStock()));
        priceField.setText(String.valueOf(selectedProduct.getPrice()));
        minField.setText(String.valueOf(selectedProduct.getMin()));
        maxField.setText(String.valueOf(selectedProduct.getMax()));
        
        
        // Add listener for the parts search field
        searchFieldParts.textProperty().addListener((obs, oldText, newText) -> {
            searchParts(newText); // Method that handles searching parts
        });
    }    
    
}
