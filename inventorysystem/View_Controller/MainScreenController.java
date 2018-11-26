package inventorysystem.View_Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import inventorysystem.Model.Inventory;
import inventorysystem.Model.Product;
import inventorysystem.Model.Part;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import inventorysystem.InventorySystem;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Cory
 */
public class MainScreenController implements Initializable {

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
    private Button partAddButton;
    @FXML
    private Button partModifyButton;
    @FXML
    private Button partDeleteButton;
    @FXML
    private Button partSearchButton;
    @FXML
    private TextField partSearchField;
    @FXML
    private TableView<Product> productsTable;
    @FXML
    private TableColumn<Product, Integer> productID;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, Integer> productInventory;
    @FXML
    private TableColumn<Product, Double> productPrice;
    @FXML
    private Button productAddButton;
    @FXML
    private Button productDeleteButton;
    @FXML
    private Button productModifyButton;
    @FXML
    private Button productSearchButton;
    @FXML
    private TextField productSearchField;
    @FXML
    private Button exitButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        productID.setCellValueFactory(
            new PropertyValueFactory<>("productID"));
        productName.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(
            new PropertyValueFactory<>("inStock"));
        productPrice.setCellValueFactory(
            new PropertyValueFactory<>("price"));
        productsTable.setItems(Inventory.getProductsArray());
        
    }    

    @FXML
    private void partAddButtonHandler(ActionEvent event) {
            changeScreen("AddPart", partAddButton);
    }

    @FXML
    private void partModifyButtonHandler(ActionEvent event) {
        try {
            // Get the part that is currently selected
            Part modifyPart = partsTable.getSelectionModel().getSelectedItem();
            /*
                Creating a new FXMLLoader object in order to call the controller
                and pass the modifypartIndex to it so it can find the array
                and populate its view with data about the selected Part.
            */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().
               getResource(InventorySystem.BASE_FOLDER_PATH + 
                            "ModifyPart.fxml"));
            
            Parent root = loader.load();
            
            // Obtains the ModifyPartController
            ModifyPartController controller = loader.getController();
            
            // Passes the Part object to be modified to the ModifyPartController
            controller.setSelectedPart(modifyPart);
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) partModifyButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Exception loading Modify Part Screen.");
            ex.printStackTrace();
        }
    }

    @FXML
    private void partDeleteButtonHandler(ActionEvent event) {
        // Gets the currently selected part
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        String productName = selectedPart.getName();
        // Creating Alert window and dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Are you sure you want to delete?");
        alert.setHeaderText("You are about to delete the Part \"" 
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
        Alert partDeletedAlert;
        // They have confirmed they want to delete the object
        if(result.get() == yesButton){
            boolean isPartDeleted = Inventory.removePart(selectedPart);
            // Object successfully deleted
            if(isPartDeleted == true) {
                 partDeletedAlert = new Alert(AlertType.INFORMATION);
                 partDeletedAlert.setTitle("Part Successfully Deleted");
                 partDeletedAlert.setHeaderText(null);
                 partDeletedAlert.setContentText(productName + 
                         " was sucecssfully deleted");
            }
            // Objected not deleted. Will likely require programmer intervention.
            else {
                 partDeletedAlert = new Alert(AlertType.ERROR);
                 partDeletedAlert.setTitle("Part Delete Failed");
                 partDeletedAlert.setHeaderText(null);
                 partDeletedAlert.setContentText(productName + 
                         " failed to delete.");
            }
            partDeletedAlert.show();
        }
        // They don't want to delete the object
        else{
            alert.close();
        }
        
        
        
    }

    @FXML
    private void partSearchButtonHandler(ActionEvent event) {
        // Getting search text
        String searchTerm = partSearchField.getText();
        // No Search text was entered
        if(searchTerm.length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
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
                    Alert alert = new Alert(AlertType.WARNING);
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
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Item not found");
                    alert.setHeaderText(null);
                    alert.setContentText("Item  " + searchTerm + " not found.");
                    alert.show();
                }
            }
        }    
    }

    @FXML
    private void productAddButtonListener(ActionEvent event) {
        changeScreen("AddProduct", productAddButton);
    }

    @FXML
    private void productDeleteButtonListener(ActionEvent event) {
        // Get the selected Product
        Product selectedProduct = productsTable.
                getSelectionModel().getSelectedItem();
        String productName = selectedProduct.getName();
        // Creating Alert window and dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
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
        Alert productDeletedAlert;
        // They have confirmed they want to delete the object
        if(result.get() == yesButton) {
            boolean isProductDeleted = Inventory.removeProduct(selectedProduct);
            // Object successfully deleted
            if(isProductDeleted == true) {
                 productDeletedAlert = new Alert(AlertType.INFORMATION);
                 productDeletedAlert.setTitle("Product Successfully Deleted");
                 productDeletedAlert.setHeaderText(null);
                 productDeletedAlert.setContentText(productName + 
                         " was sucecssfully deleted");
            }
            // Object not deleted. Will likely need programmer intervention.
            else {
                 productDeletedAlert = new Alert(AlertType.ERROR);
                 productDeletedAlert.setTitle("Product Delete Failed");
                 productDeletedAlert.setHeaderText(null);
                 productDeletedAlert.setContentText(productName + 
                         " failed to delete.");
            }
            productDeletedAlert.show();
        }
        // They do not want to delete the object
        else {
            alert.close();
        }
    }

    @FXML
    private void productModifyButtonListener(ActionEvent event) {
        try {
            /*
                Creating a new FXMLLoader object in order to call the controller
                and pass the modifypartIndex to it so it can find the array
                and populate its view with data about the selected Part.
            */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().
               getResource(InventorySystem.BASE_FOLDER_PATH + 
                            "ModifyProduct.fxml"));
            
            // Get the part that is currently selected
            Product modifyProduct = 
                    productsTable.getSelectionModel().getSelectedItem();

            Parent root = loader.load();
            
            // Obtains the ModifyPartController
            ModifyProductController controller = loader.getController();
            
            // Passes the Part object to be modified to the ModifyPartController
            controller.setSelectedProduct(modifyProduct);
            controller.fillTableCellData();
            
            Scene scene = new Scene(root);
            Stage stage = (Stage) productModifyButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Exception loading Modify Part Screen.");
            ex.printStackTrace();
        }
    }
    @FXML
    private void productSearchButtonHandler(ActionEvent event) {
        // Getting search text
        String searchTerm = productSearchField.getText();
        // No text entered in the search field
        if(searchTerm.length() == 0) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No search term entered");
            alert.setHeaderText(null);
            alert.setContentText(
                    "Please enter a Product ID or name to search for.");
            alert.show();
        }
        // Search text found
        else {
            try{
                // Extracting the int Product ID
                int productIDFound = Integer.parseInt(searchTerm);
                // Searching the Products array
                Product productFound = Inventory.lookupProduct(productIDFound);
                // Something was found
                if(productFound != null){
                    // Set the selected Product in the table
                    productsTable.getSelectionModel().select(productFound);
                    // This index will be used to set the focus and scroll the view
                    int selectedIndex = 
                            productsTable.getSelectionModel().getSelectedIndex();
                    // May I have focus?
                    productsTable.requestFocus();
                    // Focus on the found Product
                    productsTable.getFocusModel().focus(selectedIndex);
                    // Scroll the table's view to the found Product
                    productsTable.scrollTo(selectedIndex);
                }
                // Nothing found
                else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Item not found");
                    alert.setHeaderText(null);
                    alert.setContentText("Item  " + searchTerm + " not found.");
                    alert.show();
                }
            }
            catch(NumberFormatException e) {
                Product productFound = Inventory.lookupProduct(searchTerm);
                // Something was found
                if(productFound != null){
                    // Set the selected Product in the table
                    productsTable.getSelectionModel().select(productFound);
                    // This index will be used to set the focus and scroll the view
                    int selectedIndex = 
                            productsTable.getSelectionModel().getSelectedIndex();
                    // May I have focus?
                    productsTable.requestFocus();
                    // Focus on the found Product
                    productsTable.getFocusModel().focus(selectedIndex);
                    // Scroll the table's view to the found Product
                    productsTable.scrollTo(selectedIndex);
                }
                else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Item not found");
                    alert.setHeaderText(null);
                    alert.setContentText("Item  " + searchTerm + " not found.");
                    alert.show();
                }
            }
        }    
    }

    @FXML
    private void exitButtonListener(ActionEvent event) {
        // Creating Alert window and dialog
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Are you sure you want to exit?");
        alert.setHeaderText("You are about to exit the Inventory System");
        alert.setContentText("Any unsaved data will be permenently deleted."
                + " Are you sure you're ready to exit?");
        // Creating Yes/No buttons
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        // Setting the buttons on the Alert
        alert.getButtonTypes().setAll(yesButton, noButton);
        // Getting what the user selected
        Optional<ButtonType> result = alert.showAndWait();
        // They want to exit
        if(result.get() == yesButton) {
            // Close the entire application
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
        // No button was pressed
        else {
            // Just close the alert
            alert.close();
        }
        
    }
    // Used to change to a new screen
    private void changeScreen (String screenName, Button buttonPressed) {
        // All screen files end in .fxml
        final String SCREEN_FILE = screenName + ".fxml";
        try {
            Parent root = FXMLLoader.load(getClass().
                    getResource(
                            InventorySystem.BASE_FOLDER_PATH + SCREEN_FILE));
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
