package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Basic_Controller 
{
   
    protected Connection connection;
    protected PreparedStatement preparedStatement1,preparedStatement2;
    protected ResultSet resultSet;
    
    public static Dashboard_Controller dc;

    protected static void changeScenewithBorderPane(String fxmlFilePath,String contentFxml, ActionEvent event, String title) throws IOException
    {
        FXMLLoader loader= new FXMLLoader(Basic_Controller.class.getResource(fxmlFilePath));
        BorderPane root =loader.load();

        if(fxmlFilePath.equals("dashboard.fxml"))
        {
           Basic_Controller.dc = loader.getController();
           dc.ContentPane.getChildren().clear();

           if(!contentFxml.equals("None"))
           {
                AnchorPane newPane = FXMLLoader.load(Basic_Controller.class.getResource(contentFxml));
                dc.ContentPane.getChildren().setAll(newPane);
                
                if(contentFxml.equals("about.fxml"))
                {
                    dc.AboutButton.requestFocus();
                }
                if(contentFxml.equals("clients.fxml"))
                {
                    dc.ClientsButton.requestFocus();
                    dc.clickClientsButton(event);
                   
                }
                else if(contentFxml.equals("packages.fxml"))
                {
                    dc.PackagesButton.requestFocus();
                    dc.clickPackagesButton(event);
                    
                }
                else if(contentFxml.equals("bookings.fxml"))
                {
                    dc.BookingButton.requestFocus();
                    dc.clickBookingButton(event);
                   
                }
           }
        }
        Stage stage = null;
        if (event != null && event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        if (stage != null) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
           
            scene.getRoot().requestFocus(); 
        } 
    }

    protected static void changeScenewithAnchorPane(String fxmlFilePath, ActionEvent event, String title) throws IOException
    {
        AnchorPane root = FXMLLoader.load(Basic_Controller.class.getResource(fxmlFilePath));
        Stage stage = null;
        if (event != null && event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        if (stage != null) {

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
            scene.getRoot().requestFocus();
        } 
    }

    protected  void clickBackButton(ActionEvent event)
    {
        System.out.println("Back button clicked");
    };
    

    protected void startDB()
    {
        connection=null;
        preparedStatement1=null;
        preparedStatement2=null;
        resultSet=null;  
    }

    protected void setConnection() throws SQLException{
        String url = "jdbc:mysql://localhost:3306/JourneyMate?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8";
        String user = "araf";
        String password = "password";
        String databasePath = "src/MySQL Database/JourneyMate.sql";
        url += "&url=file:" + databasePath;
        connection = DriverManager.getConnection(url, user, password);
    }

    protected void closeDB()
    {
        if(resultSet!=null)
        {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(preparedStatement1!=null)
        {
            try {
                preparedStatement1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(preparedStatement2!=null)
        {
            try {
                preparedStatement2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if(connection!=null)
        {
            try {
                connection.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
    }

}
