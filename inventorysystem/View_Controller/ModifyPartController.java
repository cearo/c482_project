package inventorysystem.View_Controller;

import inventorysystem.InventorySystem;
import inventorysystem.Model.InHouse;
import inventorysystem.Model.Inventory;
import inventorysystem.Model.Outsourced;
import inventorysystem.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cory
 */
public class ModifyPartController implements Initializable {

    @FXML
    private AnchorPane modifyPart;
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
    // The part to be modified
    private Part selectedPart;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    // This Listener will set the selected option to In House
    @FXML
    private void inHouseOptionListener(ActionEvent event) {
        outsourcedOption.setSelected(false);
        // In House Parts have Machine IDs
        altFieldLabel.setText("Machine ID");
        altField.setPromptText("Machine ID");
    }
    // This Listener will set the selected option to Outsourced
    @FXML
    private void outsourcedOptionListener(ActionEvent event) {
        inHouseOption.setSelected(false);
        // Outsourced Parts have Company Names
        altFieldLabel.setText("Company Name");
        altField.setPromptText("Company Name");
    }

    @FXML
    private void saveButtonListener(ActionEvent event) {
        // Get the index of the selected Part
        int partIndex = Inventory.getPartsArray().indexOf(selectedPart);
        // Get Form data and convert it to proper data types
        String partName = name.getText();
        int partID = Integer.parseInt(id.getText());
        int partInventory = Integer.parseInt(inventory.getText());
        double partPrice = Double.parseDouble(price.getText());
        int partMax = Integer.parseInt(max.getText());
        int partMin = Integer.parseInt(min.getText());
        String altFieldText = altField.getText();
        
        // Gathers which radio button was selected at the time of save
        Toggle optionSelected = partSource.getSelectedToggle();
        // Creating a new In House Part
        if(optionSelected == inHouseOption) {
            int altFieldInt = Integer.parseInt(altFieldText);
            InHouse newPart = new InHouse();
            newPart.setPartID(partID);
            newPart.setName(partName);
            newPart.setPrice(partPrice);
            newPart.setInStock(partInventory);
            newPart.setMin(partMin);
            newPart.setMax(partMax);
            newPart.setMachineID(altFieldInt);
            // Updating the Part
            Inventory.updatePart(partIndex, newPart);
        }
        // Creating a new Outsourced Part
        else {
            Outsourced newPart = new Outsourced();
            newPart.setPartID(partID);
            newPart.setName(partName);
            newPart.setPrice(partPrice);
            newPart.setInStock(partInventory);
            newPart.setMin(partMin);
            newPart.setMax(partMax);
            newPart.setCompanyName(altFieldText);
            // Updating the Part
            Inventory.updatePart(partIndex, newPart);
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
    
    public void setSelectedPart(Part part) {
        this.selectedPart = part;
        String altFieldText;
        //Part part = Inventory.getPartsArray().get(modifyPartIndex);
        
        if(part instanceof InHouse){
            int machineID =((InHouse) part).getMachineID();
            altFieldText = Integer.toString(machineID);
            inHouseOption.setSelected(true);
        }
        else{
            altFieldText = ((Outsourced) part).getCompanyName();
            outsourcedOption.setSelected(true);
        }
        
        // Getting Part object data which will be used to populate the form
        String partName = selectedPart.getName();
        String partInventory = Integer.toString(selectedPart.getInStock());
        String partID = Integer.toString(selectedPart.getPartID());
        String partMax = Integer.toString(selectedPart.getMax());
        String partMin = Integer.toString(selectedPart.getMin());
        String partPrice = Double.toString(selectedPart.getPrice());
        // Populating the form
        setFieldText(partName, partInventory, partID, altFieldText, 
                partMax, partMin, partPrice);
    }
    
    // Sets the text in the form fields
    private void setFieldText(String name, String inventory, String id, 
            String altField, String max, String min, String price) {
        this.name.setText(name);
        this.inventory.setText(inventory);
        this.id.setText(id);
        this.altField.setText(altField);
        this.max.setText(max);
        this.min.setText(min);
        this.price.setText(price);
        
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
