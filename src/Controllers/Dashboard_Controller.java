package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class Dashboard_Controller extends Basic_Controller implements Initializable{

    @FXML
    private Button AboutButton;
    @FXML
    private Button ClientsButton;
    @FXML
    private Button PackagesButton;
    @FXML
    private Button BookingButton;
    @FXML
    private ImageView DashboardImg;
    @FXML
    private AnchorPane SidebarPane;
    @FXML
    private AnchorPane ContentPane;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       
        
        
        DashboardImg.setOnMouseClicked(event->
        {
 
            if(SidebarPane.isVisible()==false)
            {   
                SidebarPane.setVisible(true);
              
                FadeTransition ft=new FadeTransition(Duration.seconds(0.5),SidebarPane);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();

                TranslateTransition tt= new TranslateTransition(Duration.seconds(0.5),SidebarPane);
                tt.setByX(+200);
                tt.play();
                
            }
            else
            {   
                
                FadeTransition ft=new FadeTransition(Duration.seconds(0.5),SidebarPane);
                ft.setFromValue(1);
                ft.setToValue(0);
                ft.play();

                TranslateTransition tt= new TranslateTransition(Duration.seconds(0.5),SidebarPane);
                tt.setByX(-200);
                tt.play();
                
                SidebarPane.setVisible(false);
            }
        });
    }

    public void clickAboutButton (ActionEvent event) throws IOException
    {      
           
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
        AnchorPane newPane = fxmlLoader.load();
        ContentPane.getChildren().setAll(newPane);  
        AboutButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
        ClientsButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        PackagesButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        BookingButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
    }

    public void clickClientsButton(ActionEvent event)
    {      
           ContentPane.getChildren().clear();
           AboutButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
           ClientsButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
           PackagesButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
           BookingButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
    }

    public void clickPackagesButton(ActionEvent event)
    {      
           AboutButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
           ClientsButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
           PackagesButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
           BookingButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
    }

    public void clickBookingButton(ActionEvent event)
    {
           AboutButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
           ClientsButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
           PackagesButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
           BookingButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
    }

    public void clickBackButton(ActionEvent event)throws IOException
    {
        changeScene("login.fxml", event,"JourneyMate");
    }

    
}
