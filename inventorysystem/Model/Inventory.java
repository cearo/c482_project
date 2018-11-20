package inventorysystem.Model;

import java.util.ArrayList;
import java.util.HashSet;
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
    private static int partIDCount = 1;
    private static int productIDCount = 1;
    
    public Inventory(){
    }
    
    public static ObservableList<Part> getPartsArray() {
        return allParts;
    }
    
    public static ObservableList<Product> getProductsArray() {
        return products;
    }
    
    public static int getPartIDCount() {
        return partIDCount;
    }
    
    public static void setPartIDCount(int newCount) {
            partIDCount = newCount;
    }
    
    public static int getProductIDCount() {
        return productIDCount;
    }
    
    public static void setProductIDCount(int newCount) {
            productIDCount = newCount;
    }
    
    public static void addProduct(Product product) {
        // add product
        products.add(product);
    }
    
    public static boolean removeProduct(int id) {
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
    
    public static Product lookupProduct(int id) {
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
    
    public static int updateProduct(Product product) {
        // update product
            int productIndex = products.indexOf(product);
            products.set(productIndex, product);
            return productIndex;
    }
    
    public static Part lookupPart(Part part) {
        int partIndex = allParts.indexOf(part);
        return allParts.get(partIndex);
    }
    
    public static void addPart(Part part) {
        // add part
        allParts.add(part);
    }
    
    public static boolean removePart(Part part) {
        // delete part
        boolean isPartDeleted = allParts.remove(part);
        return isPartDeleted;
    }
    
    public static int updatePart(int id, Part part) {
        // update part
        allParts.set(id, part);
        
        return allParts.indexOf(part);
    }
}
