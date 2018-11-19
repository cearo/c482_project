package inventorysystem.Model;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author corobinson
 */
public class Product {
    private final List<Part> associatedParts = new ArrayList<>();
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;
    
    public Product() {
        ObservableList<Part> observableAssociatedParts = 
                FXCollections.observableArrayList(associatedParts);
        productID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }
    
    public IntegerProperty getProductIDProperty() {
        return productID;
    }
    
    public int getProductID() {
        return this.productID.get();
    }
    
    public void setProductID(int id) {
        this.productID.set(id);
    }
    
    public StringProperty getNameProperty() {
        return name;
    }
    
    public String getName() {
        return this.name.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    public DoubleProperty getPriceProperty() {
        return price;
    }
    
    public double getPrice() {
        return this.price.get();
    }
    
    public void setPrice(double price) {
        this.price.set(price);
    }
   
    public IntegerProperty getInStockProperty() {
        return inStock;
    }
    
    public int getInStock() {
        return this.inStock.get();
    }
    
    public void setInStock(int stock) {
        this.inStock.set(stock);
    }
    
     public IntegerProperty getMinProperty() {
        return min;
    }
    
    public int getMin() {
        return this.min.get();
    }
    
    public void setMin(int min) {
        this.min.set(min);
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
