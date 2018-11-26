package inventorysystem.Model;

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
    
    public static boolean removeProduct(Product product) {
        boolean isProductDeleted = products.remove(product);
        return isProductDeleted;
    }
    // Lookup Product by ID
    public static Product lookupProduct(int id) {
        Product productFound = null;
        for(Product product: products){
            int productID = product.getProductID();
            if(id == productID) {
                productFound = product;
            }
        }
        return productFound;
    }
    // Lookup Product by Name
    public static Product lookupProduct(String name) {
        Product productFound = null;
        for(Product product: products){
            String productName = product.getName();
            if(productName.equals(name)) {
                productFound = product;
            }
        }
        return productFound;
    }
    public static int updateProduct(int index, Product product) {
        // update product
        products.set(index, product);
        return index;
    }
    // Lookup Part by ID
    public static Part lookupPart(int id) {
        Part partFound = null;
        for(Part part: allParts){
            int partID = part.getPartID();
            if(partID == id){
                partFound = part;
            }
        }
        return partFound;
    }
    // Lookup Part by Name
    public static Part lookupPart(String name){
        Part partFound = null;
        for(Part part: allParts){
            String partName = part.getName();
            if(partName.equals(name)){
                partFound = part;
            }
        }
        return partFound;
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
    
    public static int updatePart(int index, Part part) {
        // update part
        allParts.set(index, part);
        
        return allParts.indexOf(part);
    }
}
