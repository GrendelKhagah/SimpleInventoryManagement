/**
 * Module information for the KetterlingC482 App.
 * Specifies the required modules and dependencies needed by this application.
 * 
 * JavaDoc Location: "KetterlingC482\target\site\apidocs\index.html"
 */
module space.ketterling.ketterlingc482 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens space.ketterling.ketterlingc482 to javafx.fxml;
    /**
     * RUNTIME ERROR 
     *
     * --- javafx:0.0.4:run (default-cli) @ KetterlingC482 ---
     * Dec 08, 2023 11:55:04 PM javafx.fxml.FXMLLoader$ValueElement processValue
     * WARNING: Loading FXML document with JavaFX API of version 21 by JavaFX runtime of version 13
     * Dec 08, 2023 11:55:06 PM javafx.fxml.FXMLLoader$ValueElement processValue
     * WARNING: Loading FXML document with JavaFX API of version 21 by JavaFX runtime of version 13
     * Dec 08, 2023 11:55:19 PM javafx.fxml.FXMLLoader$ValueElement processValue
     * WARNING: Loading FXML document with JavaFX API of version 21 by JavaFX runtime of version 13
     * Dec 08, 2023 11:55:19 PM javafx.scene.control.cell.PropertyValueFactory getCellDataReflectively
     * WARNING: Can not retrieve property 'id' in PropertyValueFactory: javafx.scene.control.cell.PropertyValueFactory@17020b8d with provided class type: class model.InHouse
     * java.lang.RuntimeException: java.lang.IllegalAccessException: module javafx.base cannot access class model.Part (in module space.ketterling.ketterlingc482) because module space.ketterling.ketterlingc482 does not open model to javafx.base
     * 
     * Fixed by opening model to javafx.base and .fxml
     */
    opens model to javafx.base, javafx.fxml;
    exports space.ketterling.ketterlingc482;
}
