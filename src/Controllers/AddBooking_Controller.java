package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddBooking_Controller extends Basic_Controller implements Initializable 
{
    
    @FXML
    private Label TPLabel;
    @FXML
    private Label DueLabel;

    @FXML
    private TextField BookingIDTextField;
    @FXML
    private TextField PaidTextField;
    @FXML
    private TextField PersonNumberTextField;

    @FXML
    private ChoiceBox<String> clientChoiceBox;
    @FXML
    private ChoiceBox<String> packageChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var clientNamelist = new ArrayList<String>();
        var packageNamelist = new ArrayList<String>();

        PersonNumberTextField.setText("1");
        PaidTextField.setText("0");

        startDB();

        try {
            setConnection();

            preparedStatement1 =connection.prepareStatement("SELECT DISTINCT ClientName From Clients WHERE UserName =?");
            preparedStatement1.setString(1,User.getName());
            resultSet=preparedStatement1.executeQuery();

            while(resultSet.next())
            {
                clientNamelist.add(resultSet.getString("ClientName"));
            }

            for(var items :clientNamelist)
            {
                clientChoiceBox.getItems().add(items);
            }

            resultSet =null;

            preparedStatement2 =connection.prepareStatement("SELECT DISTINCT PackageName From TourPackages WHERE UserName =?");
            preparedStatement2.setString(1,User.getName());
            resultSet=preparedStatement2.executeQuery();

            while(resultSet.next())
            {
                packageNamelist.add(resultSet.getString("PackageName"));
            }

            for(var items :packageNamelist)
            {
                packageChoiceBox.getItems().add(items);
            }

            if(!Login_Controller.noticeflag)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notice");
                alert.setHeaderText("Click Load Buttons After Changing");
                alert.setContentText("Always click Load Buttons after Changing Package");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.showAndWait();

                Login_Controller.noticeflag=true;
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

    public void changeTP(ActionEvent event)
    {
        String st =packageChoiceBox.getSelectionModel().getSelectedItem();
        startDB();

        try {
            setConnection();
            preparedStatement1 =connection.prepareStatement("SELECT TotalPrice FROM TourPackages WHERE UserName =? AND PackageName=?");
            preparedStatement1.setString(1, User.getName());
            preparedStatement1.setString(2, st);
            resultSet=preparedStatement1.executeQuery();
            while(resultSet.next())
            {
                try {
                TPLabel.setText(Integer.toString(resultSet.getInt("TotalPrice")*Integer.parseInt(PersonNumberTextField.getText())));
                } catch (Exception e) {
                PersonNumberTextField.setText("1");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Load Failed");
                alert.setContentText("Insert Only Numbers in PeopleNumber TextField");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
                }
            }
           

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            
            closeDB();
        }
    }
    

    public void changeDue(ActionEvent event)
    {

        if(TPLabel.getText()=="0")
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Load Failed");
            alert.setContentText("First Load the TotalPrice");
            DialogPane dialogpane = alert.getDialogPane();
            dialogpane.setStyle("-fx-background-color:#e36212;");
            alert.show();
        }
        else
        {
            try {
                DueLabel.setText(Integer.toString(Integer.parseInt(TPLabel.getText())-Integer.parseInt(PaidTextField.getText())));
            } catch (Exception e) {
                DueLabel.setText("0");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Load Failed");
                alert.setContentText("Insert Only Numbers in Paid TextField");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            }
        }  
    }

    

    public void addBookingToDB(ActionEvent event)
    {
       startDB();

       String BookingIDstr = BookingIDTextField.getText();

       String PersonNumberStr = PersonNumberTextField.getText();

       String clientNamestr = clientChoiceBox.getSelectionModel().getSelectedItem();

       String packageNamestr = packageChoiceBox.getSelectionModel().getSelectedItem();

       String totalPricestr = TPLabel.getText();

       String paidstr= PaidTextField.getText();

       String Duestr = DueLabel.getText();

       try {

        setConnection();
        preparedStatement1 = connection.prepareStatement("SELECT * FROM Bookings WHERE UserName =? AND BookingID =?");
        preparedStatement1.setString(1,User.getName());
        preparedStatement1.setString(2,BookingIDstr);

        resultSet =preparedStatement1.executeQuery();

        if(resultSet.isBeforeFirst())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Same BookingID exist.Try a New ID");
            DialogPane dialogpane = alert.getDialogPane();
            dialogpane.setStyle("-fx-background-color:#e36212;");
            alert.show();
        }
        else
        {
            if(BookingIDstr .isEmpty() || PersonNumberStr.isEmpty() || PersonNumberStr=="0" || clientNamestr.isEmpty() ||packageNamestr.isEmpty() || totalPricestr=="0"||totalPricestr.isEmpty()||paidstr.isEmpty()||Duestr.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Provide and click Load Buttons");
                alert.setContentText("Provide all Information and click load buttons ");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            }
           else
           {
            preparedStatement2 = connection.prepareStatement("INSERT INTO Bookings (UserName,BookingID,ClientName,PersonNumber,BookedPackages,TotalPrice,Paid,Due) VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement2.setString(1,User.getName());
            preparedStatement2.setString(2,BookingIDstr);
            preparedStatement2.setString(3,clientNamestr);
            preparedStatement2.setString(4,PersonNumberStr);
            preparedStatement2.setString(5,packageNamestr);
            preparedStatement2.setString(6,totalPricestr);
            preparedStatement2.setString(7,paidstr);
            preparedStatement2.setString(8,Duestr);
            preparedStatement2.executeUpdate();

            try {
                changeScenewithBorderPane("dashboard.fxml","bookings.fxml", event,"Main Menu");
            } catch (Exception e) {
                e.printStackTrace();
            }
           }
        }
         
       } catch (Exception e) {
        e.printStackTrace();
       }
       finally
       {
        closeDB();
       }

    }

    public void modifyBookingToDB(ActionEvent event)
    {
       startDB();

       String BookingIDstr = BookingIDTextField.getText();

       String PersonNumberStr = PersonNumberTextField.getText();

       String clientNamestr = clientChoiceBox.getSelectionModel().getSelectedItem();

       String packageNamestr = packageChoiceBox.getSelectionModel().getSelectedItem();

       String totalPricestr = TPLabel.getText();

       String paidstr= PaidTextField.getText();

       String Duestr = DueLabel.getText();
       
       try {

        setConnection();

        if(BookingIDstr .isEmpty() || PersonNumberStr.isEmpty() || PersonNumberStr=="0" || clientNamestr.isEmpty() ||packageNamestr.isEmpty() || totalPricestr=="0"||totalPricestr.isEmpty()||paidstr.isEmpty()||Duestr.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Provide and click Load Buttons");
            alert.setContentText("Provide all Information and click load buttons ");
            DialogPane dialogpane = alert.getDialogPane();
            dialogpane.setStyle("-fx-background-color:#e36212;");
            alert.show();
        }
        else
        {
            preparedStatement2= connection
            .prepareStatement("Delete FROM Bookings WHERE UserName = ? AND BookingID = ?");
            preparedStatement2.setString(1, User.getName());
            preparedStatement2.setString(2, BookingScreen_Controller.previousBookingID);
            preparedStatement2.executeUpdate();
            preparedStatement2=null;


            preparedStatement2 = connection.prepareStatement("INSERT INTO Bookings (UserName,BookingID,ClientName,PersonNumber,BookedPackages,TotalPrice,Paid,Due) VALUES(?,?,?,?,?,?,?,?)");
            preparedStatement2.setString(1,User.getName());
            preparedStatement2.setString(2,BookingIDstr);
            preparedStatement2.setString(3,clientNamestr);
            preparedStatement2.setString(4,PersonNumberStr);
            preparedStatement2.setString(5,packageNamestr);
            preparedStatement2.setString(6,totalPricestr);
            preparedStatement2.setString(7,paidstr);
            preparedStatement2.setString(8,Duestr);
            preparedStatement2.executeUpdate();

            try {
                changeScenewithBorderPane("dashboard.fxml","bookings.fxml", event,"Main Menu");
            } catch (Exception e) {
                e.printStackTrace();
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




    @Override
    public void clickBackButton(ActionEvent event) {
        try {
            changeScenewithBorderPane("dashboard.fxml","bookings.fxml", event, "Main Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


