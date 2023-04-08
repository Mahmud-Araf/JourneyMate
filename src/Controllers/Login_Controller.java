package Controllers;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Login_Controller {
    @FXML
    private Stage stage;

    public void clickSignUp(ActionEvent event) throws IOException {
        AnchorPane  root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void clickSignIn(ActionEvent event) throws IOException{
        BorderPane root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
