package inventorysystem.View_Controller;

import inventorysystem.InventorySystem;
import inventorysystem.Model.InHouse;
import inventorysystem.Model.Inventory;
import inventorysystem.Model.Outsourced;
import inventorysystem.Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    /*
        A field for holding the value of what was stored in altField. This will
        allow users to switch between the InHouse and Outsourced options without
        losing what is already stored in the array. The field will clear upon 
        selecting one of the options but, when returning to the previous option
        the old value will still be there.
    */
    private String currentAltFieldText;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         System.out.println("Modify Controller Initialized");
    }   

    @FXML
    private void inHouseOptionListener(ActionEvent event) {
        outsourcedOption.setSelected(false);
//        setCurrentAltFieldText();
        altFieldLabel.setText("Machine ID");
        altField.setPromptText("Machine ID");
    }

    @FXML
    private void outsourcedOptionListener(ActionEvent event) {
        inHouseOption.setSelected(false);
//        setCurrentAltFieldText();
        altFieldLabel.setText("Company Name");
        altField.setPromptText("Company Name");
    }

    @FXML
    private void saveButtonListener(ActionEvent event) {
        System.out.println("Modify Part Save Button Pressed");
        int partIndex = Inventory.getPartsArray().indexOf(selectedPart);

        String partName = name.getText();
        int partID = Integer.parseInt(id.getText());
        int partInventory = Integer.parseInt(inventory.getText());
        double partPrice = Double.parseDouble(price.getText());
        int partMax = Integer.parseInt(max.getText());
        int partMin = Integer.parseInt(min.getText());
        String altFieldText = altField.getText();
        
        // Gathers which radio button was selected at the time of save
        Toggle optionSelected = partSource.getSelectedToggle();
        
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
            Inventory.updatePart(partIndex, newPart);
        }
        else {
            Outsourced newPart = new Outsourced();
            newPart.setPartID(partID);
            newPart.setName(partName);
            newPart.setPrice(partPrice);
            newPart.setInStock(partInventory);
            newPart.setMin(partMin);
            newPart.setMax(partMax);
            newPart.setCompanyName(altFieldText);
            Inventory.updatePart(partIndex, newPart);
        }
        loadMainScreen(saveButton);
    }

    @FXML
    private void cancelButtonListener(ActionEvent event) {
        System.out.println("Modify Part Cancel button pressed");
        loadMainScreen(cancelButton);
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
        
        String partName = selectedPart.getName();
        String partInStock = Integer.toString(selectedPart.getInStock());
        String partID = Integer.toString(selectedPart.getPartID());
        String partMax = Integer.toString(selectedPart.getMax());
        String partMin = Integer.toString(selectedPart.getMin());
        String partPrice = Double.toString(selectedPart.getPrice());
        
        setFieldText(partName, partInStock, partID, altFieldText, 
                partMax, partMin, partPrice);
        //return this.modifyPartIndex;
    }
    
    // Sets the text in the form fields
    public void setFieldText(String name, String inventory, String id, 
            String altField, String max, String min, String price) {
        this.name.setText(name);
        this.inventory.setText(inventory);
        this.id.setText(id);
        this.altField.setText(altField);
        this.max.setText(max);
        this.min.setText(min);
        this.price.setText(price);
        
    }
    
    // Takes button pressed and directs the Stage back to MainScreen.fxml
    public void loadMainScreen(Button buttonPressed) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    InventorySystem.BASE_FOLDER_PATH + "MainScreen.fxml"));
            Stage stage = (Stage) buttonPressed.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(
                    "Exception loading MainScreen from Modify Part Screen.");
            ex.printStackTrace();
        }
    }
    
//   public void setCurrentAltFieldText() {
//       String current = altField.getText();
//       System.out.println(current);
//       String promptText = altField.getPromptText();
//       System.out.println(promptText);
//       System.out.println(!(current.equals(promptText)));
//        if(!(current.equals(promptText))){
//            System.out.println("In if");
//            this.currentAltFieldText = current;
//            System.out.println("Current Alt Field: " + currentAltFieldText);
//            this.altField.setText(currentAltFieldText);
//        }
//        else{
//            System.out.println("In else");
//            this.altField.clear();
//        }
        
//   }
}
