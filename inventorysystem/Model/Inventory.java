package inventorysystem.Model;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author corobinson
 */
public class Inventory {
    private static ObservableList<Product> products = FXCollections.observableArrayList();
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    
    public Inventory(){
    }
    
    public void addProduct(Product product) {
        // add product
    }
    
    public boolean removeProduct(int id) {
        // remove product
        return true;
    }
    
//    public Product lookupProduct(int id) {
//        // remove product
//        Product placeHolder = new Product();
//        
//        return placeHolder;
//    }
    
    public void updateProduct(int id) {
        // update product
    }
    
    public void addPart(Part part) {
        // add part
    }
    
    public boolean deletePart(Part part) {
        // delete part
        return true;
    }
    
//    public Part lookupPart(int id) {
//        Part placeHolder = new Part();
//        return placeHolder;
//    }
    
    public void updatePart(int id) {
        // update part
    }
}
