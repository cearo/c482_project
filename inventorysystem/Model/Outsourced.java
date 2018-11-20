package inventorysystem.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author corobinson
 */
public class Outsourced extends Part {
    private final StringProperty companyName;

    public Outsourced() {
        super();
        companyName = new SimpleStringProperty();
    }
    
    public StringProperty getCompanyNameProperty() {
        return companyName;
    }
    
    public String getCompanyName() {
        return this.companyName.get();
    }
    
    public void setCompanyName(String name) {
        this.companyName.set(name);
    }
    
}
