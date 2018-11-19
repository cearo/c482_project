package inventorysystem.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author corobinson
 */
public class InHouse extends Part {
    private final IntegerProperty machineID;
    
    public InHouse() {
        super();
        machineID = new SimpleIntegerProperty();
    }
    
    public IntegerProperty getMachineIDProperty(){
        return machineID;
    }
    
    public int getMachineID(){
        return this.machineID.get();
    }
    
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    } 
}
