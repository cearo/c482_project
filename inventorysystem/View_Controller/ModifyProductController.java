package inventorysystem.View_Controller;

import inventorysystem.InventorySystem;
import inventorysystem.Model.Inventory;
import inventorysystem.Model.Part;
import inventorysystem.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
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
public class ModifyProductController implements Initializable {

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
        System.out.println("Product Part Add Button Pressed");
        // Obtains Parts Selected
        ObservableList<Part> selectedPart = 
                partsTable.getSelectionModel().getSelectedItems();
        System.out.println("Selected Part Array: " + selectedPart.toString());
        for(Part part : selectedPart) {
            selectedProduct.addAssociatedPart(part);
        }
        
    }

    @FXML
    private void deleteButtonListener(ActionEvent event) {
    }

    @FXML
    private void searchButtonListener(ActionEvent event) {
    }

    @FXML
    private void cancelButtonListener(ActionEvent event) {
        loadMainScreen(cancelButton);
    }

    @FXML
    private void saveButtonListener(ActionEvent event) {
        int productIndex = Inventory.getProductsArray().
                indexOf(selectedProduct);
        
        String productName = name.getText();
        int productID = Integer.parseInt(id.getText());
        int productInventory = Integer.parseInt(inventory.getText());
        double productPrice = Double.parseDouble(price.getText());
        int productMax = Integer.parseInt(max.getText());
        int productMin = Integer.parseInt(min.getText());
        
//        Product newProduct = new Product();
        selectedProduct.setProductID(productID);
        selectedProduct.setName(productName);
        selectedProduct.setInStock(productInventory);
        selectedProduct.setPrice(productPrice);
        selectedProduct.setMax(productMax);
        selectedProduct.setMin(productMin);
        
//        Inventory.updateProduct(productIndex, newProduct);
        
        loadMainScreen(saveButton);
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
    
    // Takes button pressed and directs the Stage back to MainScreen.fxml
    private void loadMainScreen(Button buttonPressed) {
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
        productID.setCellValueFactory(
            new PropertyValueFactory<>("productID"));
        productName.setCellValueFactory(
            new PropertyValueFactory<>("name"));
        productInventory.setCellValueFactory(
            new PropertyValueFactory<>("inStock"));
        productPrice.setCellValueFactory(
            new PropertyValueFactory<>("price"));
        productsTable.setItems(selectedProduct.getAssociatedPartsArray());
    }
    
}
