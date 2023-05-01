package Controllers;

import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BookingScreen_Controller extends Basic_Controller implements ControllerFunctions{

    @FXML
    private Label BookingNumberLabel;
    @FXML
    private Label DueNumberLabel;
    
    public String getBookingNumber(String UserName)
    {
        String st = "0";

        startDB();

        try {

            setConnection();

            preparedStatement1 = connection.prepareStatement("SELECT COUNT(DISTINCT BookingID) FROM Bookings WHERE UserName = ?;");
            preparedStatement1.setString(1,UserName);
            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                st = Integer.toString(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return st; 

    }

    public String getDueNumber(String UserName)
    {
        String st = "0";

        startDB();

        try {

            setConnection();

            preparedStatement1 = connection.prepareStatement("SELECT COUNT(*) FROM Bookings WHERE Due <> 0 AND UserName = ?;");
            preparedStatement1.setString(1,UserName);
            resultSet = preparedStatement1.executeQuery();

            if (resultSet.next()) {
                st = Integer.toString(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return st; 
    }




    
    @Override
    public void addAction(ActionEvent event) {
        try {
            changeScenewithAnchorPane("addBooking.fxml", event,"Add Booking");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @Override
    public void modifyAction(ActionEvent event) {
        
    }

    @Override
    public void deleteAction(ActionEvent event) {
       
    }

    @Override
    public void showTable(ActionEvent event) {
        
    }

    
    
}
