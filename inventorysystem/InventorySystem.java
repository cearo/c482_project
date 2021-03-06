package inventorysystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Cory
 */
public class InventorySystem extends Application {
    public static final String BASE_FOLDER_PATH = "/inventorysystem/View_Controller/";
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Inventory System");
        Parent root = FXMLLoader.load(getClass().getResource(
                BASE_FOLDER_PATH + "MainScreen.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
