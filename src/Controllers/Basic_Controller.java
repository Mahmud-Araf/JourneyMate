package Controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Basic_Controller {

    protected static Stage stage;

    protected static void changeScene(String fxmlFilePath,ActionEvent event)throws IOException
    {
        BorderPane root = FXMLLoader.load(Basic_Controller.class.getResource(fxmlFilePath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
    
}
