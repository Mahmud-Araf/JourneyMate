package Controllers;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class Login_Controller extends Basic_Controller {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField passwordTextField;

    public void clickSignUp(ActionEvent event) throws IOException {

        changeScene("signup.fxml", event,"Sign Up");
    }

    public void clickSignIn(ActionEvent event){
        SignInUser(event,nameTextField.getText(),passwordTextField.getText());
    }

    public void clickSignInFromSignUP(ActionEvent event)
    {
        SignUpUser(event,nameTextField.getText(),emailTextField.getText(),passwordTextField.getText());
    }

    public static void SignUpUser(ActionEvent event,String Name,String Email,String Password) 
    {
        Connection connection = null;
        PreparedStatement userInsert = null;
        PreparedStatement checkUserExists = null;
        ResultSet resultSet = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JourneyMate","araf", "password");
            checkUserExists = connection.prepareStatement("SELECT * FROM Users WHERE Email = ?");
            checkUserExists.setString(1,Email);
            resultSet=checkUserExists.executeQuery();

            if(Name.isEmpty()||Email.isEmpty()||Password.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provide all necessary information");
                alert.show();
            }
            else
            {
                if(resultSet.isBeforeFirst())
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("User already exists");
                    alert.show();
                }
                else
                {
                    userInsert = connection.prepareStatement("INSERT INTO Users (Name, Email,Password) VALUES(?,?,?)");
                    userInsert.setString(1, Name);
                    userInsert.setString(2, Email);
                    userInsert.setString(3, Password);
                    userInsert.executeUpdate();
                    try {
                        changeScene("login.fxml",event,"Sign In");
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
            if(resultSet != null){
                try{
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(userInsert != null){
                try{
                    userInsert.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(checkUserExists != null){
                try{
                    checkUserExists.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if(connection != null){
                try{
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void SignInUser(ActionEvent event,String Name,String Password)
    {
        Connection connection = null;
        PreparedStatement userInsert = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/JourneyMate","araf", "password");
            userInsert = connection.prepareStatement("SELECT * FROM Users WHERE Name = ?");
            userInsert.setString(1,Name);
            resultSet=userInsert.executeQuery();

            if(Name==null || !resultSet.isBeforeFirst())
            {
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Name for User");
                alert.show();
            }
            else
            {
                while(resultSet.next())
                {
                    String findPassword=resultSet.getString("Password");

                    if(findPassword.equals(Password))
                    {
                        try {
                        changeScene("dashboard.fxml", event,"Main Menu");   
                        } catch (Exception e) {
                           e.printStackTrace();
                        }
                    }
                    else
                    {
                        Alert alert =new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invaild Passord");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        finally
        {
            if(resultSet!=null)
            {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(userInsert!=null)
            {
                try {
                    userInsert.close();
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

}
