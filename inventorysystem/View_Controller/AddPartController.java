/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

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
    }
    
}
