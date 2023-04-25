package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

public class ClientScreen_Controller extends Basic_Controller 
{
    
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField mobileNumTextField;
    @FXML
    private TextField addressTextField;

    public String getTotalClientNumber(String UserName)
    {
        String st= "0";

        startDB();

        try {

            setConnection();
            preparedStatement1 = connection.prepareStatement("SELECT COUNT(*) FROM Clients WHERE UserName = ?");
            preparedStatement1.setString(1, UserName);
            resultSet = preparedStatement1.executeQuery();

            if(resultSet.next())
            {
                st=Integer.toString(resultSet.getInt(1));
            }

            
        } catch (SQLException e) {
           e.printStackTrace();
        }
        finally
        {
            closeDB();
        }

        return st;
    }
    
    @Override
    public void addAction(ActionEvent event) 
    {
        try {
        changeScenewithAnchorPane("addclient.fxml", event,"Add Client");   
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void modifyAction(ActionEvent event)
    {
        
    }

    @Override
    public void deleteAction(ActionEvent event)
    {
      
    }
 
    @Override
    public void showTable(ActionEvent event)
    {
        
    }

    @Override
    public void clickBackButton(ActionEvent event)
    {
        try {
            changeScenewithBorderPane("dashboard.fxml", event,"Main Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addClientToDB(ActionEvent event)
    {
        startDB();
        String Name = nameTextField.getText();
        String MobileNum = mobileNumTextField.getText();
        String Address = addressTextField.getText();

        try 
        {
            setConnection();
            preparedStatement2 = connection.prepareStatement("SELECT * FROM Clients WHERE MobileNumber = ?");
            preparedStatement2.setString(1,MobileNum);
            resultSet=preparedStatement2.executeQuery();

            if(Name.isEmpty()||MobileNum.isEmpty()||Address.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provide all necessary information");
                DialogPane dialogpane =alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            }
            else
            {
                if(resultSet.isBeforeFirst())
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Client already exists");
                    DialogPane dialogpane =alert.getDialogPane();
                    dialogpane.setStyle("-fx-background-color:#e36212;");
                    alert.show();
                }
                else
                {
                    preparedStatement1 = connection.prepareStatement("INSERT INTO Clients (UserName,ClientName,MobileNumber,Address) VALUES(?,?,?,?)");
                    preparedStatement1.setString(1, User.Name);
                    preparedStatement1.setString(2, Name);
                    preparedStatement1.setString(3, MobileNum);
                    preparedStatement1.setString(4, Address);
                    preparedStatement1.executeUpdate();
                    try {
                        changeScenewithBorderPane("dashboard.fxml",event,"Main Menu");
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                    
                }
            }
        } 
        catch (SQLException e) {
           e.printStackTrace();
        }
        finally 
        {
            closeDB();
        }
    } 
 
}