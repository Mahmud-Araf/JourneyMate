package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import Classes.User;


public class Dashboard_Controller extends Basic_Controller implements Initializable{
     
    @FXML
    private Button AboutButton;
    @FXML
    private Button ProfileButton;
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
    @FXML
    private BorderPane MainPane;

    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        
        // System.out.println(nameLabel.getText()+" "+emailLabel.getText()+passwordLabel.getText());
        
        if(DashboardImg!=null)
        {
            DashboardImg.setOnMouseClicked(event -> {
            
                if (!SidebarPane.isVisible()) {
                    SidebarPane.setVisible(true);
            
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), SidebarPane);
                    ft.setFromValue(0);
                    ft.setToValue(1);
            
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), SidebarPane);
                    tt.setToX(0);
            
                    ParallelTransition pt = new ParallelTransition(ft, tt);
                    pt.play();
                } 
                else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), SidebarPane);
                    ft.setFromValue(1);
                    ft.setToValue(0);
            
                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), SidebarPane);
                    tt.setToX(-200);
            
                    ParallelTransition pt = new ParallelTransition(ft, tt);
                    pt.setOnFinished(e -> SidebarPane.setVisible(false));
                    pt.play();
                }
            });
        }
        
    }

    public void clickAboutButton (ActionEvent event) throws IOException
    {      
         
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
        AnchorPane newPane = fxmlLoader.load();
        ContentPane.getChildren().clear();
        ContentPane.getChildren().setAll(newPane);  
        MainPane.setStyle("-fx-background-color:  #e36212;");
        AboutButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
        ProfileButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        ClientsButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        PackagesButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        BookingButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
    }

    public void clickProfileButton(ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
        AnchorPane newPane = fxmlLoader.load();
        ContentPane.getChildren().clear();
        ContentPane.getChildren().setAll(newPane);
        MainPane.setStyle("-fx-background-color:  #e36212;");
        nameLabel = (Label) newPane.lookup("#nameLabel");
        emailLabel = (Label) newPane.lookup("#emailLabel");
        passwordLabel = (Label) newPane.lookup("#passwordLabel");

        nameLabel.setText(User.Name);
        emailLabel.setText(User.Email);
        passwordLabel.setText(User.Password);

        AboutButton.setStyle("-fx-background-color: #e36212 ;"+"-fx-background-radius:50;");
        ProfileButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
        ClientsButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        PackagesButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        BookingButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
    }

    public void clickClientsButton(ActionEvent event) throws IOException
    {      
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clients.fxml"));
        AnchorPane newPane = fxmlLoader.load();
        ContentPane.getChildren().clear();
        ContentPane.getChildren().setAll(newPane);
        MainPane.setStyle("-fx-background-color: #e36212;");
        Label ClientNumber = (Label)newPane.lookup("#ClientNumberLabel");
        ClientScreen_Controller csc = new ClientScreen_Controller();
        ClientNumber.setText(csc.getTotalClientNumber(User.Name));
        AboutButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        ProfileButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        ClientsButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
        PackagesButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        BookingButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
    }

    public void clickPackagesButton(ActionEvent event)
    {   
        ContentPane.getChildren().clear();
        MainPane.setStyle("-fx-background-color: White;");
        AboutButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        ProfileButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        ClientsButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        PackagesButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
        BookingButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
    }

    public void clickBookingButton(ActionEvent event)
    {
        ContentPane.getChildren().clear();
        MainPane.setStyle("-fx-background-color: White;");
        AboutButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        ProfileButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        ClientsButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        PackagesButton.setStyle("-fx-background-color:  #e36212;"+"-fx-background-radius:50;");
        BookingButton.setStyle("-fx-background-color:  White;"+"-fx-background-radius:50;");
    }

    public void clickBackButton(ActionEvent event)throws IOException
    {
        changeScene("login.fxml", event,"JourneyMate");
    }

    public void clickSignOut(ActionEvent event)throws IOException
    {
        changeScene("login.fxml", event,"Sign In");
    }

    
    @Override
    public  void deleteAction(ActionEvent event)
    {
       startDB();
       try {
        setConnection();
        preparedStatement1=connection.prepareStatement("Delete FROM Users WHERE Name = ?");
        preparedStatement1.setString(1,User.Name);
        preparedStatement1.executeUpdate();
        
        try {
        changeScene("signup.fxml", event,"Sign Up");   
        } catch (Exception e) {
           e.printStackTrace();
        }        
       } catch ( SQLException e) {
            e.printStackTrace();
       }
       finally
       {
        closeDB();
       }
         
    }

    
}
