package space.ketterling.ketterlingc482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX desktop application for Simple Inventory Management GUI
 * JavaDoc Location: "KetterlingC482\target\site\apidocs\index.html"
 * 
 * 
 * Enhancement plan:
 * => First Database Integration for Persistent Inventory Management:
 *  Consider embedding SQLite for a lightweight, serverless database for data .
 *  Explore H2 Database, which offers both embedded and server modes and supports disk-based or in-memory databases.
 *  Evaluate Firebird SQL for more robust features, available in both embedded and server modes. Super Open-source
 *  Ultimately deploy an option that enables localized storage options 
 *  Support Database back-up to USB 
 *  Support Database restore from USB
 * 
 * => Transition project to Endpoint Inventory Management System:
 *  Expand the application scope from managing Parts and Products to managing Devices and People.
 *  This transition is aimed at adding real-world practicality, particularly for IT administration in environments like schools, where there's a need for tracking device loan liability and conducting audits.
 *  As an IT administrator in such an environment, this functionality is crucial for managing and tracking the lifecycle of devices, ensuring accountability and efficient resource allocation.
 *  Implement features to support the transfer of device ownership:
 *     -> Enable the system to facilitate the transfer of device ownership between individuals (students, staff) and administrative entities (like classes, departments).
 *     -> This can include assigning devices to specific classes for educational purposes, or reallocating devices to different departments for administrative use.
 *     -> Develop a user-friendly interface for administrators to easily update ownership records, ensuring a smooth transition when devices are reallocated or repurposed within the organization setting.
 *     -> Implement robust logging and tracking capabilities to maintain a transparent history of ownership transfers, which is essential for audit trails and accountability.
 *
 * => Features for Device Management:
 *  Implement bulk device enrollment, allowing large field inputs for efficient data entry (copy-paste functionality from existing device lists).
 *  Include a feature to add new devices individually or in bulk.
 * 
 * => People Management and Device-Person Association:
 *  Introduce functionality for bulk addition of people.
 *  Develop features to associate devices with people, facilitating tracking of loaned/responsible equipment.
 *  Support smart contracts for loan agreements and automated reminders
 *  Support Email / SMS Reminders for agreement landmarks and milestones or duty requirements.
 *     -> If a device is assigned to person as a loaner it may have requirements such as when to return a device by and fees associated with failure to comply with terms.
 *     -> Milestones can be set for specific Device Types, such as loaners, individually assigned, or leased.
 *     -> A loaner may be free upfront so long it is return according to miles stone terms, such as a week after lent out.
 *     -> Milestones may trigger reminders or announcements, so long person has applicable contact data.
 *  Support guardian role for associating People to their Guardians(students to parents), Guardians may have contact information that recieves alerts depending on milestone alert
 * 
 * => Peripheral Device Support:
 *  Add support for USB-connected barcode scanners to facilitate input via QR Codes of device ID or S/n 
 *  Search by QR Code scanner input
 *  Support QR Code form input on add Device and People forms. 
 */

public class App extends Application {

    private static Scene scene;

    /**
     * Sets the starting page to the FXML scene main from loadFXL then shows the stage
     * 
     * @param stage         sets stage to the main scene
     * @throws IOException  may throw exception if file does not exists
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    /**
     * searches for the FXML file to load based of file name
     * 
     * @param fxml              the name of the fxml file
     * @throws IOException      throws exception if fxml file does not exist
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    /**
     * The main entry point for the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}