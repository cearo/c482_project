package inventorysystem.View_Controller;

import inventorysystem.InventorySystem;
import inventorysystem.Model.InHouse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import inventorysystem.Model.Inventory;
import inventorysystem.Model.Outsourced;
import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cory
 */
public class AddPartController implements Initializable {

    @FXML
    private RadioButton inHouseOption;
    @FXML
    private ToggleGroup partSource;
    @FXML
    private RadioButton outsourcedOption;
    @FXML
    private Label altFieldLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField inventory;
    @FXML
    private TextField price;
    @FXML
    private TextField id;
    @FXML
    private TextField altField;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inHouseOption.setSelected(true);
    }    

    @FXML
    private void inHouseOptionListener(ActionEvent event) {
        outsourcedOption.setSelected(false);
        altField.clear();
        altFieldLabel.setText("Machine ID");
        altField.setPromptText("Machine ID");
    }

    @FXML
    private void outsourcedOptionListener(ActionEvent event) {
        inHouseOption.setSelected(false);
        altField.clear();
        altFieldLabel.setText("Company Name");
        altField.setPromptText("Company Name");
    }

    @FXML
    private void saveButtonListener(ActionEvent event) {

        // Gets the current global ID for Parts
        int partID = Inventory.getPartIDCount();
        
        // Increments the Part ID Count
        int partIDIncremented = partID + 1;
        // Setting incremented ID
        Inventory.setPartIDCount(partIDIncremented);
        
        String partName = name.getText();
        int partInventory = Integer.parseInt(inventory.getText());
        double partPrice = Double.parseDouble(price.getText());
        int partMax = Integer.parseInt(max.getText());
        int partMin = Integer.parseInt(min.getText());
        String altFieldText = altField.getText();
        
        // Gathers which radio button was selected at the time of save
        Toggle optionSelected = partSource.getSelectedToggle();
        
        // In House radio button selected
        if (optionSelected == inHouseOption) {
            int altFieldInt = Integer.parseInt(altField.getText());
            InHouse newPart = new InHouse();
            newPart.setPartID(partID);
            newPart.setName(partName);
            newPart.setPrice(partPrice);
            newPart.setInStock(partInventory);
            newPart.setMin(partMin);
            newPart.setMax(partMax);
            newPart.setMachineID(altFieldInt);
            Inventory.addPart(newPart);
        }
        // Outsourced radio button selected
        else {
            Outsourced newPart = new Outsourced();
            newPart.setPartID(partID);
            newPart.setName(partName);
            newPart.setPrice(partPrice);
            newPart.setInStock(partInventory);
            newPart.setMin(partMin);
            newPart.setMax(partMax);
            newPart.setCompanyName(altFieldText);
            Inventory.addPart(newPart);
        }
        changeScreen("MainScreen", saveButton);
    }

    @FXML
    private void cancelButtonListener(ActionEvent event) {
        // Creating Alert window and dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Go back to Main Screen?");
        alert.setHeaderText("You are about to go back to the Main Screen");
        alert.setContentText("Any unsaved data will be permenently deleted."
                + " Are you sure you're ready to go back to the Main Screen?");
        // Creating Yes/No buttons
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        // Setting the buttons on the Alert
        alert.getButtonTypes().setAll(yesButton, noButton);
        // Getting what the user selected
        Optional<ButtonType> result = alert.showAndWait();
        // They want to go back to the main screen
        if(result.get() == yesButton) {
            changeScreen("MainScreen", cancelButton);
        }
        // No button was pressed
        else {
            // Just close the alert
            alert.close();
        }
    }
    
    // Takes button pressed and directs the Stage to the specified screen
    private void changeScreen (String screenName, Button buttonPressed) {
        String screenFile = screenName + ".fxml";
        try {
            Parent root = FXMLLoader.load(getClass().
                    getResource(
                            InventorySystem.BASE_FOLDER_PATH + screenFile));
            Stage stage = (Stage) buttonPressed.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Exception loading Add Part Screen.");
            ex.printStackTrace();
        }
    }
    
}
