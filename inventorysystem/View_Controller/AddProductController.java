package inventorysystem.View_Controller;

import inventorysystem.InventorySystem;
import inventorysystem.Model.Inventory;
import inventorysystem.Model.Part;
import inventorysystem.Model.Product;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Cory
 */
public class AddProductController implements Initializable {

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
        changeScreen("MainScreen", cancelButton);
    }

    @FXML
    private void saveButtonListener(ActionEvent event) {
        
        // Gets current global ID for Products
        int productID = Inventory.getProductIDCount();
        
        // Increments Product ID Count
        int productIDIncremented = productID + 1;
        // Setting incremented ID
        Inventory.setProductIDCount(productIDIncremented);
        
        // Gathering form field data
        String productName = name.getText();
        int productInventory = Integer.parseInt(inventory.getText());
        double productPrice = Double.parseDouble(price.getText());
        int productMax = Integer.parseInt(max.getText());
        int productMin = Integer.parseInt(min.getText());
        
        Product newProduct = new Product();
        
        // Setting Product object fields
        newProduct.setProductID(productID);
        newProduct.setName(productName);
        newProduct.setPrice(productPrice);
        newProduct.setInStock(productInventory);
        newProduct.setMin(productMin);
        newProduct.setMax(productMax);
        
        // Adding newly created Product to the Product Array
        Inventory.addProduct(newProduct);
    }

    @FXML
    private void inHouseOptionListener(ActionEvent event) {
    }

    @FXML
    private void outsourcedOptionListener(ActionEvent event) {
    }
    
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
