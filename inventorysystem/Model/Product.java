package inventorysystem.Model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author corobinson
 */
public class Product {
    private List<Part> associatedParts;
    private int productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    // Defines the base of error messages to be thrown
    private static final String ERROR_MESSAGE = "Please enter a valid ";
    
    public Product(int productID, String name, double price, 
                   int inStock, int min, int max) {
        
        associatedParts = new ArrayList<>();
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }
    
    // Builds error messages using base defined above
    private static String errorMessageBuilder(String specific) {
        StringBuilder sb = new StringBuilder(ERROR_MESSAGE);
        sb.append(specific).append(".");
        return sb.toString();
    }
    
    public void setProductID(int id) throws IllegalArgumentException {        
        // Preventing Product ID from being set to 0 or lower
        if(id > 0) {
            this.productID = id;
        }
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("product ID"));
        }
    }
    
    public int getProductID() {
        return this.productID;
    }
    
    public void setName(String name) throws IllegalArgumentException {
        // Preventing name from being set to blank
        if(name.length() > 0) {
            this.name = name;
        }
        else{
            throw new IllegalArgumentException(
                    errorMessageBuilder("product name"));
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setPrice(double price) throws IllegalArgumentException {
        // Preventing price from being set to 0 or lower
        if(price > 0) {
            this.price = price;
        }
        
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("product price"));
        }
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setInStock(int stock) throws IllegalArgumentException {
        // Preventing stock quantity from being set to a negative int
        if(stock >= 0) {
            this.inStock = stock;
        }
        
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("stock value"));
        }
    }
    
    public int getInStock() {
        return this.inStock;
    }
    
    public void setMin(int min) throws IllegalArgumentException {
        // Preventing min from being set to a negative int
        if(min >= 0) {
            this.min = min;
        }
        
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("minimum stock value"));
        }
    }
    
    public int getMin() {
        return this.min;
    }
    
    public void setMax(int max) throws IllegalArgumentException {
        // Preventing max from being set to 0 or lower
        if(max > 0) {
            this.max = max;
        }
        else {
            throw new IllegalArgumentException (
                     errorMessageBuilder("maximum stock value"));
        }
    }
    
    public int getMax() {
        return this.max;
    }
    
    public void addAssociatedPart(Part part) {
        // add associated part
    }
    
    public boolean removeAssociatedPart(int id) {
        // remove associated part
        return true;
    }
    
//    public Part lookupAssociatedPart(int id) {
//        Part placeHolder = new InHouse();
//        return placeHolder;
//    }
}
