package inventorysystem.View_Controller;

import inventorysystem.InventorySystem;
import inventorysystem.Model.Inventory;
import inventorysystem.Model.Part;
import inventorysystem.Model.Product;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cory
 */
public class ModifyProductController implements Initializable {

    @FXML
    private TableView<Part> productPartsTable;
    @FXML
    private TableColumn<Part, Integer> productPartID;
    @FXML
    private TableColumn<Part, String> productPartName;
    @FXML
    private TableColumn<Part, Integer> productPartInventory;
    @FXML
    private TableColumn<Part, Double> productPartPrice;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partID;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> partInventory;
    @FXML
    private TableColumn<Part, Double> partPrice;
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
    private TextField name;
    @FXML
    private TextField inventory;
    @FXML
    private TextField price;
    @FXML
    private TextField id;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    
    private Product selectedProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void addButtonListener(ActionEvent event) {
        // Obtains Part Selected
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        // Adds the selected part to the associated parts array
        this.selectedProduct.addAssociatedPart(selectedPart);
    }

    @FXML
    private void deleteButtonListener(ActionEvent event) {
        // Get the selected Product
        Part selectedPart = productPartsTable.
                getSelectionModel().getSelectedItem();
        String productName = selectedPart.getName();
        // Creating Alert window and dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Are you sure you want to delete?");
        alert.setHeaderText("You are about to delete the Product \"" 
                + productName + "\"!");
        alert.setContentText("One deleted, this cannot be undone."
                + " Are you sure you're ready to delete " + productName + "?");
        // Creating Yes/No buttons
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        // Setting the buttons on the Alert
        alert.getButtonTypes().setAll(yesButton, noButton);
        // Getting what the user selected
        Optional<ButtonType> result = alert.showAndWait();
        /* Setting up the alert to be thrown. The alert type will vary depending
           on if the object could be deleted from the array. If not, an error
           will be thrown. This would be a system issue, not user error.
        */
        Alert productPartDeletedAlert;
        // They have confirmed they want to delete the object
        if(result.get() == yesButton) {
            boolean isProductDeleted = this.selectedProduct.removeAssociatedPart(
                    selectedPart.getPartID());
            // Object successfully deleted
            if(isProductDeleted == true) {
                 productPartDeletedAlert = new Alert(Alert.AlertType.INFORMATION);
                 productPartDeletedAlert.setTitle("Product Successfully Deleted");
                 productPartDeletedAlert.setHeaderText(null);
                 productPartDeletedAlert.setContentText(productName + 
                         " was sucecssfully deleted");
            }
            // Object not deleted. Will likely need programmer intervention.
            else {
                 productPartDeletedAlert = new Alert(Alert.AlertType.ERROR);
                 productPartDeletedAlert.setTitle("Product Delete Failed");
                 productPartDeletedAlert.setHeaderText(null);
                 productPartDeletedAlert.setContentText(productName + 
                         " failed to delete.");
            }
            productPartDeletedAlert.show();
        }
        // They do not want to delete the object
        else {
            alert.close();
        }
    }

    @FXML
    private void searchButtonListener(ActionEvent event) {
        // Getting search text
        String searchTerm = searchField.getText();
        // No Search text was entered
        if(searchTerm.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No search term entered");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Please enter a Part ID or name to search for.");
            alert.show();
        }
        // Search text was found
        else {
            try{
                // Extracting the int part ID
                int partID = Integer.parseInt(searchTerm);
                // Searching the Parts array
                Part partFound = Inventory.lookupPart(partID);
                // A Part was found
                if(partFound != null){
                    // Setting the selected Part in the Table View
                    partsTable.getSelectionModel().select(partFound);
                    // This index will be used to set the focus and scroll the view
                    int selectedIndex = 
                            partsTable.getSelectionModel().getSelectedIndex();
                    // May I have focus?
                    partsTable.requestFocus();
                    // Set the tables focus to the found Part
                    partsTable.getFocusModel().focus(selectedIndex);
                    // Scroll to the found Part
                    partsTable.scrollTo(selectedIndex);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Item not found");
                    alert.setHeaderText(null);
                    alert.setContentText("Item  " + searchTerm + " not found.");
                    alert.show();
                }
            }
            // The user must be trying to search by name
            catch(NumberFormatException e) {
                Part partFound = Inventory.lookupPart(searchTerm);
                // A Part was found
                if(partFound != null){
                    // Setting the selected Part in the Table View
                    partsTable.getSelectionModel().select(partFound);
                    // This index will be used to set the focus and scroll the view
                    int selectedIndex = 
                            partsTable.getSelectionModel().getSelectedIndex();
                    // May I have focus?
                    partsTable.requestFocus();
                    // Set the tables focus to the found Part
                    partsTable.getFocusModel().focus(selectedIndex);
                    // Scroll to the found Part
                    partsTable.scrollTo(selectedIndex);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Item not found");
                    alert.setHeaderText(null);
                    alert.setContentText("Item  " + searchTerm + " not found.");
                    alert.show();
                }
            }
        }
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

    @FXML
    private void saveButtonListener(ActionEvent event) {
        int productIndex = Inventory.getProductsArray().
                indexOf(selectedProduct);
        
        // Gathering form data
        String productName = name.getText();
        int productID = Integer.parseInt(id.getText());
        int productInventory = Integer.parseInt(inventory.getText());
        double productPrice = Double.parseDouble(price.getText());
        int productMax = Integer.parseInt(max.getText());
        int productMin = Integer.parseInt(min.getText());
        // How many Parts are associated with this Product?
        int associatedPartArraySize = selectedProduct.
                getAssociatedPartsArray().size();
        // No Associated Parts found for this Product
        if(associatedPartArraySize == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Associated Part Found");
            alert.setHeaderText("Products must have Parts.");
            alert.setContentText(
                    "Please Add a Part to this Product before Saving");
            alert.show();
        }
        else {
        // Setting the new Product values
        selectedProduct.setProductID(productID);
        selectedProduct.setName(productName);
        selectedProduct.setInStock(productInventory);
        selectedProduct.setPrice(productPrice);
        selectedProduct.setMax(productMax);
        selectedProduct.setMin(productMin);
        
        changeScreen("MainScreen", saveButton);
        }
    }
    
    public void setSelectedProduct(Product product) {
        this.selectedProduct = product;
        
        // Getting Product object data which will be used to populate the form
        String productName = selectedProduct.getName();
        String productInventory = Integer.toString(
                selectedProduct.getInStock());
        String productPrice = Double.toString(selectedProduct.getPrice());
        String productID = Integer.toString(selectedProduct.getProductID());
        String productMax = Integer.toString(selectedProduct.getMax());
        String productMin = Integer.toString(selectedProduct.getMin());
        // Populating the form
        setFieldText(productName, productInventory, productPrice, 
                productID, productMax, productMin);
    }
    
    private void setFieldText(String name, String inventory, String price, 
            String id, String max, String min) {
        this.name.setText(name);
        this.inventory.setText(inventory);
        this.price.setText(price);
        this.id.setText(id);
        this.max.setText(max);
        this.min.setText(min);
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
    // Populates the table cell data
    public void fillTableCellData() {
        // Setting Part TableView data
        partID.setCellValueFactory(
            new PropertyValueFactory<>("partID"));
        partName.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        partInventory.setCellValueFactory(
            new PropertyValueFactory<>("inStock"));
        partPrice.setCellValueFactory(
            new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.getPartsArray());
        
        // Setting Product TableView data
        productPartID.setCellValueFactory(
            new PropertyValueFactory<>("partID"));
        productPartName.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        productPartInventory.setCellValueFactory(
            new PropertyValueFactory<>("inStock"));
        productPartPrice.setCellValueFactory(
            new PropertyValueFactory<>("price"));
        productPartsTable.setItems(selectedProduct.getAssociatedPartsArray());
    }
    
}
