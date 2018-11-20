/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private int modifyPartIndex;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         System.out.println("Modify Controller Initialized");
         System.out.println("Modify Part Index: " + modifyPartIndex);
        String altFieldText;
        Part part = Inventory.getPartsArray().get(modifyPartIndex);
        
        if(part instanceof InHouse){
            int machineID =((InHouse) part).getMachineID();
            altFieldText = Integer.toString(machineID);
            inHouseOption.setSelected(true);
        }
        else{
            altFieldText = ((Outsourced) part).getCompanyName();
            outsourcedOption.setSelected(true);
        }
        
        String partName = part.getName();
        String partInStock = Integer.toString(part.getInStock());
        String partID = Integer.toString(part.getPartID());
        String partMax = Integer.toString(part.getMax());
        String partMin = Integer.toString(part.getMin());
        String partPrice = Double.toString(part.getPrice());
        
        setFieldText(partName, partInStock, partID, altFieldText, 
                partMax, partMin, partPrice);
    }    

    @FXML
    private void inHouseOptionListener(ActionEvent event) {
    }

    @FXML
    private void outsourcedOptionListener(ActionEvent event) {
    }

    @FXML
    private void saveButtonListener(ActionEvent event) {
    }

    @FXML
    private void cancelButtonListener(ActionEvent event) {
        System.out.println("Modify Part Cancel button pressed");
        try {
            Parent root = FXMLLoader.load(getClass().
                    getResource(InventorySystem.BASE_FOLDER_PATH + "MainScreen.fxml"));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Exception loading MainScreen from Part Add Screen.");
            ex.printStackTrace();
        }
    }
    
    public int setModifyPartIndex(int index) {
        this.modifyPartIndex = index;
        return this.modifyPartIndex;
    }
    
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
}
