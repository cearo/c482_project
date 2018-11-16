package inventorysystem.Model;

/**
 *
 * @author corobinson
 */
public abstract class Part {
    int partID;
    String name;
    double price;
    int inStock;
    int min;
    int max;
    // Defines the base of error messages to be thrown
    static final String ERROR_MESSAGE = "Please enter a valid ";
    
    public abstract void setName(String name) throws IllegalArgumentException;
    public abstract String getName();
    public abstract void setPrice(double price) throws IllegalArgumentException;
    public abstract double getPrice();
    public abstract void setInStock(int stock) throws IllegalArgumentException;
    public abstract int getInStock();
    public abstract void setMin(int min) throws IllegalArgumentException;
    public abstract int getMin();
    public abstract void setMax(int max) throws IllegalArgumentException;
    public abstract int getMax();
    public abstract void setPartID(int id) throws IllegalArgumentException;
    public abstract int getPartID();
}


