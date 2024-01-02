package space.ketterling.ketterlingc482;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Part;
import model.Product;
import model.Inventory;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The main controller for the application.
 * This controller handles interactions with the main application window.
 * 
 * @author Taylor Ketterling
 */
public class MainController implements Initializable {
    @FXML
    private TextField searchFieldParts;

    @FXML
    private TextField searchFieldProducts;
    
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
    private TableView<Product> productsTable;
    
    @FXML
    private TableColumn<Product, Integer> ProductIDColumn;
    
    @FXML
    private TableColumn<Product, String> ProductNameColumn;
    
    @FXML
    private TableColumn<Product, Integer> ProductInvColumn;
    
    @FXML
    private TableColumn<Product, Double> ProductCostColumn;
    
    /** The part object selected in the table view by the user. */
    private static Part partToModify;

    /**  The product object selected in the table view by the user. */
    private static Product productToModify;
    
    /**
     * Gets the part object selected by the user in the part table.
     *
     * @return A part object, null if no part selected.
     */
    public static Part getPartToModify() {
        return partToModify;
    }

    /**
     * Gets the product object selected by the user in the product table.
     *
     * @return A product object, null if no product selected.
     */
    public static Product getProductToModify() {
        return productToModify;
    }

    /**
     * Exits the program with a 0 status
     *
     * @param event         Exit
     */
    @FXML
    void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Switches to the add Part scene
     * 
     * @param event 
     */
    @FXML
    void partAddAction(ActionEvent event) {
        switchScene(event, "addPart.fxml");
    }
    
    /**
     * Deletes the selected Part after user confirmation
     * 
     * @param event 
     */
    @FXML
    void partDeleteAction(ActionEvent event) {
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            displayAlert(3);
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Confirm Part Deletion.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }
    
    /**
     * Switches to Part Modification Scene after storing which part to modify
     * 
     * @param event 
     */
    @FXML
    void partModifyAction(ActionEvent event){
        partToModify = partsTable.getSelectionModel().getSelectedItem();
        if (partToModify == null) {
            displayAlert(3);
        } else {
            switchScene(event, "modifyPart.fxml");
        }
    }
    
    /**
     * Loads AddProductController.
     *
     * @param event Add product button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void productAddAction(ActionEvent event) {
        switchScene(event, "addProduct.fxml");
    }

    /**
     * Deletes the product selected by the user in the product table.
     *
     * The method displays an error message if no product is selected and a confirmation
     * dialog before deleting the selected product. Also prevents user from deleting
     * a product with one or more associated parts.
     *
     * @param event         Product delete button action.
     */
    @FXML
    void productDeleteAction(ActionEvent event) {
        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            displayAlert(4);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = selectedProduct.getAllAssociatedParts();

                if (assocParts.size() >= 1) {
                    displayAlert(5);
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }

    /**
     * Loads the ModifyProductController.
     * This method displays an error when no product is selected
     * .
     *
     * @param event         Product modify button action.
     */
    @FXML
    void productModifyAction(ActionEvent event) {
        productToModify = productsTable.getSelectionModel().getSelectedItem();
        if (productToModify == null) {              // RUNTIME ERROR Correction: Prevents Null values from being passed
            displayAlert(4);
        } else {
            switchScene(event, "modifyProduct.fxml");
        }
    }

    /**
     * Switches to various scenes
     * 
     * @param event         
     * @param fxmlFile          fxmlFile Destination
     */
    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {                
            displayAlert(6);    // Handle IOException 
        } catch (Exception e) {
            displayAlert(7);    // Handle other unexpected exceptions such as bad address
        }
    }

    /**
     * Displays various alert messages with different severities 
     *
     * @param alertType         Alert message selector.
     */
    private void displayAlert(int alertCase) {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);

        switch (alertCase) {
            case 1:
                infoAlert.setTitle("Information");
                infoAlert.setHeaderText("Part not found");
                infoAlert.showAndWait();
                break;
            case 2:
                infoAlert.setTitle("Information");
                infoAlert.setHeaderText("Product not found");
                infoAlert.showAndWait();
                break;
            case 3:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Part not selected");
                errorAlert.showAndWait();
                break;
            case 4:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Product not selected");
                errorAlert.showAndWait();
                break;
            case 5:
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Parts Associated");
                errorAlert.setContentText("All parts must be removed from product before deletion.");
                errorAlert.showAndWait();
                break;
            case 6:
                errorAlert.setTitle("Error IOException");
                errorAlert.setHeaderText("Something went wrong");
                errorAlert.showAndWait();
                break;
            case 7:
                errorAlert.setTitle("Error Exception");
                errorAlert.setHeaderText("Something went wrong");
                errorAlert.showAndWait();
        }
    }
    
    
    /**
     * Searches for Parts based on the given search text.
     * @param searchText The text to search for in part names and IDs.
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
     * Searches through the products list and updates table results
     * @param searchText he text to search for in product names and IDs.
     */
    @FXML
    private void searchProducts(String searchText) {
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();

        Integer searchId = null;
        try {
            searchId = Integer.parseInt(searchText);
        } catch (NumberFormatException e) {
            // searchText is not a valid integer cannot be an ID, no action needed
            // searchId will remain null, 
        }

        for (Product product : Inventory.getAllProducts()) {
            // Check if the products's name contains the searchText or the products's ID matches searchId
            if (product.getName().toLowerCase().contains(searchText.toLowerCase()) || (searchId != null && product.getId() == searchId)) {
                filteredProducts.add(product);
            }
        }

        // Update the products table view to display only the filtered products
        productsTable.setItems(filteredProducts);
    }

    /**
     * Initializes controller and populates table views.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partsTable.setItems(Inventory.getAllParts());
        PartIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());
        ProductIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        ProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ProductInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ProductCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        // Add listener for the parts search field
        searchFieldParts.textProperty().addListener((obs, oldText, newText) -> {
            searchParts(newText); // Method that handles searching parts
        });

        // Add listener for the products search field
        searchFieldProducts.textProperty().addListener((obs, oldText, newText) -> {
            searchProducts(newText); // Method that handles searching products
        });
    }
}