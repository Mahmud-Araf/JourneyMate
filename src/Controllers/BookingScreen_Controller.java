package Controllers;

import java.io.IOException;
import java.sql.SQLException;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")
public class BookingScreen_Controller extends Basic_Controller implements ControllerFunctions {

    @FXML
    private Label BookingNumberLabel;
    @FXML
    private Label DueNumberLabel;

    @FXML
    private Button DelButton;

    @FXML
    private Button ModifyButton;

    public static String previousBookingID;

    public String getBookingNumber(String UserName) {
        String st = "0";

        startDB();

        try {

            setConnection();

            preparedStatement1 = connection
                    .prepareStatement("SELECT COUNT(DISTINCT BookingID) FROM Bookings WHERE UserName = ?;");
            preparedStatement1.setString(1, UserName);
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

    public String getDueNumber(String UserName) {
        String st = "0";

        startDB();

        try {

            setConnection();

            preparedStatement1 = connection
                    .prepareStatement("SELECT COUNT(*) FROM Bookings WHERE Due <> 0 AND UserName = ?;");
            preparedStatement1.setString(1, UserName);
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
            changeScenewithAnchorPane("addBooking.fxml", event, "Add Booking");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyAction(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setTitle("Change Booking Details");
        dialog.setHeaderText("Enter Booking ID:");
        dialog.initOwner((Stage) ModifyButton.getScene().getWindow());

        TextField textField = new TextField();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(textField);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        dialogPane.setStyle("-fx-background-color:#e36212;");

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                previousBookingID = textField.getText();
                gotomodifyBookingScreen(event, textField.getText());
            }
            return null;
        });

        dialog.show();
    }

    public void gotomodifyBookingScreen(ActionEvent event, String BookingIDstr) {
        startDB();

        try {
            setConnection();
            preparedStatement1 = connection
                    .prepareStatement("Select * FROM Bookings WHERE UserName = ? AND BookingID = ?");
            preparedStatement1.setString(1, User.getName());
            preparedStatement1.setString(2, BookingIDstr);
            resultSet = preparedStatement1.executeQuery();

            if (BookingIDstr.isEmpty() || !resultSet.isBeforeFirst()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Booking not found");
                alert.setContentText("The Booking was not found in the database.");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            } else {

                String clientNameStr = null;
                String packageNamestr = null;
                String personNumberstr = null;
                String totalpricestr = null;
                String paidstr = null;
                String duestr = null;

                while (resultSet.next()) {
                    clientNameStr = resultSet.getString("ClientName");
                    packageNamestr = resultSet.getString("BookedPackages");
                    personNumberstr = resultSet.getString("PersonNumber");
                    totalpricestr = Integer.toString(resultSet.getInt("TotalPrice"));
                    paidstr = Integer.toString(resultSet.getInt("Paid"));
                    duestr = Integer.toString(resultSet.getInt("Due"));
                }

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyBookingDetails.fxml"));
                    AnchorPane root = fxmlLoader.load();

                    var tf1 = (TextField) root.lookup("#BookingIDTextField");
                    tf1.setText(previousBookingID);
                    var tf2 = (TextField) root.lookup("#PersonNumberTextField");
                    tf2.setText(personNumberstr);
                    var tf3 = (TextField) root.lookup("#PaidTextField");
                    tf3.setText(paidstr);

                    var cb1 = (ChoiceBox<String>) root.lookup("#clientChoiceBox");
                    cb1.getSelectionModel().select(clientNameStr);
                    var cb2 = (ChoiceBox<String>) root.lookup("#packageChoiceBox");
                    cb2.getSelectionModel().select(packageNamestr);

                    var l1 = (Label) root.lookup("#TPLabel");
                    l1.setText(totalpricestr);
                    var l2 = (Label) root.lookup("#DueLabel");
                    l2.setText(duestr);

                    Stage stage = null;
                    if (event != null && event.getSource() instanceof Node) {
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    }

                    if (stage != null) {
                        stage.setScene(new Scene(root));
                        stage.setTitle("Change Booking Info");
                        stage.setResizable(false);
                        stage.show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

    @Override
    public void deleteAction(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setTitle("Delete Booking");
        dialog.setHeaderText("Enter Booking ID:");
        dialog.initOwner((Stage) DelButton.getScene().getWindow());

        TextField textField = new TextField();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(textField);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        dialogPane.setStyle("-fx-background-color:#e36212;");

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                deleteBookingFromDB(event, textField.getText());
            }
            return null;
        });

        dialog.show();
    }

    public void deleteBookingFromDB(ActionEvent event, String bookingID) {
        startDB();

        try {
            setConnection();
            preparedStatement1 = connection
                    .prepareStatement("Delete FROM Bookings WHERE UserName = ? AND BookingID = ?;");
            preparedStatement1.setString(1, User.getName());
            preparedStatement1.setString(2, bookingID);
            int roweffected = preparedStatement1.executeUpdate();

            if (roweffected == 0 || bookingID.isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Booking not found");
                alert.setContentText("The Booking was not found in the database.");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            }

            BookingNumberLabel.setText(getBookingNumber(User.getName()));

            DueNumberLabel.setText(getDueNumber(User.getName()));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

    @Override
    public void showTable(ActionEvent event) {
        try {
            changeScenewithAnchorPane("showBookingTable.fxml", event, "Booking Details");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
