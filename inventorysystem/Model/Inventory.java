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
    private static int partIDCount = 0;
    private static int productIDCount = 0;
    
    public Inventory(){
    }
    
    public static ObservableList<Part> getPartsArray() {
        return allParts;
    }
    
    public static ObservableList<Product> getProductsArray() {
        return products;
    }
    
    public void addProduct(Product product) {
        // add product
        products.add(product);
    }
    
    public boolean removeProduct(int id) {
        // remove product
        try{
            products.remove(id);
        }
        catch(Exception e){
            // Item was not removed, return false
            System.out.println("Exception removing item: \n" + e);
            return false;
        }
        return true;
    }
    
    public Product lookupProduct(int id) {
        try{
            Product productFound = products.get(id);
            return productFound;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Exception in product lookup: " + e.toString());
            throw new IndexOutOfBoundsException("Product ID " + id +
                    " is invalid.");
        }
        
    }
    
    public int updateProduct(Product product) {
        // update product
            int productIndex = products.indexOf(product);
            products.set(productIndex, product);
            return productIndex;
    }
    
    public Part lookupPart(Part part) {
        int partIndex = allParts.indexOf(part);
        return allParts.get(partIndex);
    }
    
    public void addPart(Part part) {
        // add part
        allParts.add(part);
    }
    
    public boolean deletePart(Part part) {
        // delete part
        boolean isPartDeleted = allParts.remove(part);
        return isPartDeleted;
    }
    
    public int updatePart(Part part) {
        // update part
        int partIndex = allParts.indexOf(part);
        allParts.set(partIndex, part);
        return partIndex;
    }
}
