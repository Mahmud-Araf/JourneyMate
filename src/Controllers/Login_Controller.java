package Controllers;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import Classes.User;


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

    public void clickBackButton(ActionEvent event)throws IOException
    {
        changeScene("login.fxml", event,"JourneyMate");
    }

    public void SignUpUser(ActionEvent event,String Name,String Email,String Password) 
    {
        startDB();

        try {

            setConnection();
            preparedStatement2 = connection.prepareStatement("SELECT * FROM Users WHERE Name = ?");
            preparedStatement2.setString(1,Name);
            resultSet=preparedStatement2.executeQuery();

            if(Name.isEmpty()||Email.isEmpty()||Password.isEmpty())
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
                    alert.setContentText("User already exists");
                    DialogPane dialogpane =alert.getDialogPane();
                    dialogpane.setStyle("-fx-background-color:#e36212;");
                    alert.show();
                }
                else
                {
                    preparedStatement1 = connection.prepareStatement("INSERT INTO Users (Name, Email,Password) VALUES(?,?,?)");
                    preparedStatement1.setString(1, Name);
                    preparedStatement1.setString(2, Email);
                    preparedStatement1.setString(3, Password);
                    preparedStatement1.executeUpdate();
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
            closeDB();
        }
    }

    public void SignInUser(ActionEvent event,String Name,String Password)
    {
        startDB();

        try {
            setConnection();
            preparedStatement1 = connection.prepareStatement("SELECT * FROM Users WHERE Name = ?");
            preparedStatement1.setString(1,Name);
            resultSet=preparedStatement1.executeQuery();

            if(Name==null || !resultSet.isBeforeFirst())
            {
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Invalid Name for User");
                DialogPane dialogpane =alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            }
            else
            {
                while(resultSet.next())
                {   
                    String findName=resultSet.getString("Name");
                    String findEmail=resultSet.getString("Email");
                    String findPassword=resultSet.getString("Password");

                    if(findPassword.equals(Password))
                    {
                        try {
                        User.setInfo(findName, findEmail, findPassword);
                        changeScene("dashboard.fxml", event,"Main Menu");   
                        } catch (Exception e) {
                           e.printStackTrace();
                        }
                    }
                    else
                    {
                        Alert alert =new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Invaild Passord");
                        DialogPane dialogpane =alert.getDialogPane();
                        dialogpane.setStyle("-fx-background-color:#e36212;");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        finally
        {
            closeDB();
        }
    }

}
