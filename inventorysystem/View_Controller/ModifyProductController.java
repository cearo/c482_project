package inventorysystem.View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Cory
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TableView<?> productsTable;
    @FXML
    private TableColumn<?, ?> prodID;
    @FXML
    private TableColumn<?, ?> prodName;
    @FXML
    private TableColumn<?, ?> prodInv;
    @FXML
    private TableColumn<?, ?> prodPrice;
    @FXML
    private TableView<?> partsTable;
    @FXML
    private TableColumn<?, ?> partID;
    @FXML
    private TableColumn<?, ?> partName;
    @FXML
    private TableColumn<?, ?> partInv;
    @FXML
    private TableColumn<?, ?> partPrice;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private RadioButton inHouseOption;
    @FXML
    private ToggleGroup partSource;
    @FXML
    private RadioButton outsourceOption;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addButtonListener(ActionEvent event) {
    }

    @FXML
    private void deleteButtonListener(ActionEvent event) {
    }

    @FXML
    private void searchButtonListener(ActionEvent event) {
    }

    @FXML
    private void cancelButtonListener(ActionEvent event) {
    }

    @FXML
    private void saveButtonListener(ActionEvent event) {
    }

    @FXML
    private void inHouseListener(ActionEvent event) {
    }

    @FXML
    private void outsourceListener(ActionEvent event) {
    }
    
}
