package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddBooking_Controller extends Basic_Controller implements Initializable 
{
    private boolean noticeflag = false;

    @FXML
    private Label TPLabel;
    @FXML
    private Label DueLabel;

    @FXML
    private TextField BookingIDTextField;
    @FXML
    private TextField PaidTextField;

    @FXML
    private ChoiceBox<String> clientChoiceBox;
    @FXML
    private ChoiceBox<String> packageChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var clientNamelist = new ArrayList<String>();
        var packageNamelist = new ArrayList<String>();

        startDB();

        try {
            setConnection();

            preparedStatement1 =connection.prepareStatement("SELECT DISTINCT ClientName From Clients WHERE UserName =?");
            preparedStatement1.setString(1,User.Name);
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
            preparedStatement2.setString(1,User.Name);
            resultSet=preparedStatement2.executeQuery();

            while(resultSet.next())
            {
                packageNamelist.add(resultSet.getString("PackageName"));
            }

            for(var items :packageNamelist)
            {
                packageChoiceBox.getItems().add(items);
            }

            if(!noticeflag)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notice");
                alert.setHeaderText("Click Load Buttons After Changing");
                alert.setContentText("Always click Load Buttons after Changing Package");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.showAndWait();

                noticeflag =true;
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
            preparedStatement1.setString(1, User.Name);
            preparedStatement1.setString(2, st);
            resultSet=preparedStatement1.executeQuery();
            while(resultSet.next())
            {
                TPLabel.setText(Integer.toString(resultSet.getInt("TotalPrice")));
            }
           

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            
            closeDB();
        }
    }
    

    public void changeTPMouse(MouseEvent event)
    {
        String st =packageChoiceBox.getSelectionModel().getSelectedItem();
        startDB();

        try {
            setConnection();
            preparedStatement1 =connection.prepareStatement("SELECT TotalPrice FROM TourPackages WHERE UserName =? AND PackageName=?");
            preparedStatement1.setString(1, User.Name);
            preparedStatement1.setString(2, st);
            resultSet=preparedStatement1.executeQuery();
            while(resultSet.next())
            {
                TPLabel.setText(Integer.toString(resultSet.getInt("TotalPrice")));
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

    public void changeDueMouse(MouseEvent event)
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


    public void addBookingToDB()
    {

    }






    @Override
    public void clickBackButton(ActionEvent event) {
        try {
            changeScenewithBorderPane("dashboard.fxml", event, "Main Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


