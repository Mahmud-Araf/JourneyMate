package Controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Basic_Controller {

   
    protected static void changeScene(String fxmlFilePath, ActionEvent event, String title) throws IOException
    {
        BorderPane root = FXMLLoader.load(Basic_Controller.class.getResource(fxmlFilePath));
        Stage stage = null;
        if (event != null && event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        if (stage != null) {
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
        } 
    }
    
    
}
