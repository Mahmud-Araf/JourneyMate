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


    protected static void changeScenewithBorderPane(String fxmlFilePath, ActionEvent event, String title) throws IOException
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

    protected static void changeScenewithAnchorPane(String fxmlFilePath, ActionEvent event, String title) throws IOException
    {
        AnchorPane root = FXMLLoader.load(Basic_Controller.class.getResource(fxmlFilePath));
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

    protected void clickBackButton(ActionEvent event)
    {
        System.out.println("Override this function for backbutton operation");
    }

    protected void startDB()
    {
        connection=null;
        preparedStatement1=preparedStatement2=null;
        resultSet=null;
    }

    protected void setConnection() throws SQLException{
       connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JourneyMate","araf", "password");
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

    protected void addAction(ActionEvent event)
    {
        System.out.println("Override this function for add operation");
    }

    protected void modifyAction(ActionEvent event)
    {
        System.out.println("Override this function for modify operation");
    }

    protected void deleteAction(ActionEvent event)
    {
        System.out.println("Override this function for delete operation");
    }

    protected void showTable(ActionEvent event)
    {
        System.out.println("Override this function for table showing");
    }


    
    
}
