package inventorysystem.Model;

/**
 *
 * @author corobinson
 */
public class Outsourced extends Part {
    private String companyName;
    
    public Outsourced(int partID, String name, double price, int inStock,
            int min, int max, String companyName) {
        this.partID = partID;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
        this.companyName = companyName;
    }
    
        // Builds error messages using base defined above
    private static String errorMessageBuilder(String specific) {
        StringBuilder sb = new StringBuilder(ERROR_MESSAGE);
        sb.append(specific).append(".");
        return sb.toString();
    }
    
    public void setCompanyName(String name) {
        // Preventing Machine ID from being set to 0 or lower
        if(name.length() > 0) {
            this.companyName = name;
        }
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("machine ID"));
        }
    }
    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setName(String name) {
        // Preventing name from being set to blank
        if(name.length() > 0) {
            this.name = name;
        }
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("part name"));
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setPrice(double price) {
        // Preventing Price from being set to 0 or lower
        if(price > 0) {
            this.price = price;
        }
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("price"));
        }
    }
    
    public double getPrice() {
        return this.price;
    }
    
    public void setInStock(int stock) {
        // Preventing Product ID from being set to 0 or lower
        if(stock > 0) {
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
    
    public void setMin(int min) {
        // Preventing min from being set to 0 or lower
        if(min > 0) {
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
    
    public void setMax(int max) {
        // Preventing max from being set to 0 or lower
        if(max > 0) {
            this.max = max;
        }
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("maximum stock value"));
        }
    }
    
    public int getMax() {
        return this.max;
    }
    
    public void setPartID(int id) {
        // Preventing Part ID from being set to 0 or lower
        if(id> 0) {
            this.partID = id;
        }
        else {
            throw new IllegalArgumentException(
                    errorMessageBuilder("Part ID"));
        }
    }
    
    public int getPartID() {
        return this.partID;
    }
    
    
}
