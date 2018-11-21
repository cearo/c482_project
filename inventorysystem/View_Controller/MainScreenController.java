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
import inventorysystem.Model.InHouse;
import inventorysystem.Model.Outsourced;
import inventorysystem.Model.Product;
import inventorysystem.Model.Part;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import inventorysystem.InventorySystem;
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
//    public MainScreenController(){};
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
        System.out.println("Part Add button clicked");
//        try {
//            Parent root = FXMLLoader.load(getClass().
//                    getResource(InventorySystem.BASE_FOLDER_PATH + "AddPart.fxml"));
//            Stage stage = (Stage) partAddButton.getScene().getWindow();
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            System.out.println("Exception loading Add Part Screen.");
//            ex.printStackTrace();
//        }
        changeScreen("AddPart", partAddButton);
    }

    @FXML
    private void partModifyButtonHandler(ActionEvent event) {
        try {
            /*
                Creating a new FXMLLoader object in order to call the controller
                and pass the modifypartIndex to it so it can find the array
                and populate its view with data about the selected Part.
            */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().
               getResource(InventorySystem.BASE_FOLDER_PATH + 
                            "ModifyPart.fxml"));
            
            // Get the part that is currently selected
            Part modifyPart = partsTable.getSelectionModel().getSelectedItem();
            System.out.println("Modify Part Name: " + modifyPart.getName());

            System.out.println("Before load");
            Parent root = loader.load();
            System.out.println("After Load");
            
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
        boolean result = Inventory.removePart(selectedPart);
    }

    @FXML
    private void partSearchButtonHandler(ActionEvent event) {
    }

    @FXML
    private void productAddButtonListener(ActionEvent event) {
        changeScreen("AddProduct", productAddButton);
    }

    @FXML
    private void productDeleteButtonListener(ActionEvent event) {
        Product selectedProduct = productsTable.
                getSelectionModel().getSelectedItem();
        boolean result = Inventory.removeProduct(selectedProduct);
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
    private void exitButtonListener(ActionEvent event) {
    }
    
    private void changeScreen (String screenName, Button buttonPressed) {
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
