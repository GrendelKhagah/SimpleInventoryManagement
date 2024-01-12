package space.ketterling.ketterlingc482;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Inventory;

/**
 * addPart.fxml Controller 
 * collects part form data to create a new part
 * validates data and provides feedback about errors
 * 
 * @author Taylor
 */
public class AddPartController implements Initializable {

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
    private TextField uniqueField;
    
    @FXML
    private RadioButton inHouseButton;
    
    @FXML
    private RadioButton outsourcedButton;
    
    @FXML
    private Label uniqueLabel;
    
    
    /**
     * cancelAction is used to close pager and return user to main menu
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    void cancelAction(ActionEvent event) throws IOException {
        returnToMain(event);
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
            
            if (ValidData(min, max, stock, Name)) {
                if(inHouseButton.isSelected()){
                    int machineId = Integer.parseInt(uniqueField.getText());
                    InHouse newPart = new InHouse(Inventory.NewPartID(), Name, price, stock, min, max, machineId);
                    Inventory.addPart(newPart);
                } else {
                    String companyName = uniqueField.getText();
                    Outsourced newPart = new Outsourced(Inventory.NewPartID(), Name, price, stock, min, max, companyName);
                    Inventory.addPart(newPart);
                }
                displayAlert(6);
                returnToMain(event);
            }
        } catch(NumberFormatException e) {
            displayAlert(5);
        } catch (IOException e) {
            displayAlert(2);
        } 
    }
    
    /**
     * Sets the uniqueLabel to Machine ID
     * 
     * @param event 
     */
    @FXML
    void radioSelectInhouse(ActionEvent event) {
        uniqueLabel.setText("Machine ID");
        
    }
    
    /**
     * Sets the uniqueLabel to Company Name
     * 
     * @param event 
     */
    @FXML
    void radioSelectOutsourced(ActionEvent event) {
        uniqueLabel.setText("Company Name");
    }
    
    /**
     * returns the user to main screen
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
            displayAlert(2);
        } catch(Exception e) {
            displayAlert(3);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        switch (alertCase) {
            case 1:
                alert.setTitle("Information");
                alert.setHeaderText("Missing Part Data");
                alert.showAndWait();
                break;
            case 2:
                alertError.setTitle("Error IOException");
                alertError.setHeaderText("Something went wrong");
                alertError.showAndWait();
                break;
            case 3:
                alertError.setTitle("Error Exception");
                alertError.setHeaderText("Something went wrong");
                alertError.showAndWait();
                break;
            case 4:
                alertError.setTitle("Invalid Data");
                alertError.setHeaderText("Min, Max or Stock or Name is not qualified data");
                alertError.showAndWait();
                break;
            case 5:
                alertError.setTitle("NumberFormatException");
                alertError.setHeaderText("Issue With Data when generating new part");
                alertError.showAndWait();
                break;
            case 6:
                alert.setTitle("Information");
                alert.setHeaderText("Save Successful");
                alert.showAndWait();
                break;
        }
    }
    
    /**
     * ValidData checks name, min, max and stock to ensure valid data
     * 
     * @param min       minimum stock allowed
     * @param max       max stock allowed
     * @param stock     amount of inventory(available stock)
     * @param Name      name of part
     * @return          return false if invalid or missing data, true if good data
     */ 
    private boolean ValidData(int min, int max, int stock, String Name){
        if (min > max || stock < min || stock > max || Name.isEmpty()){
            displayAlert(4);
            return false;
        } return true;
    }
    
    /**
     * Initializes page with empty fields.
     * 
     * @param url                   resource location
     * @param rb                    resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // leave empty, nothing to initialize
    }
    
}



