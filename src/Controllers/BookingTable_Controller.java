package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Classes.Booking;
import Classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class BookingTable_Controller extends Basic_Controller implements Initializable {

    @FXML
    private TableColumn<Booking, String> BookingIDColumn;

    @FXML
    private TableColumn<Booking, String> ClientNameColumn;

    @FXML
    private TableColumn<Booking, String> DueColumn;

    @FXML
    private TableColumn<Booking, String> PackageColumn;

    @FXML
    private TableColumn<Booking, String> PaidColumn;

    @FXML
    private TableColumn<Booking, String> PersonNumberColumn;

    @FXML
    private TableColumn<Booking, String> TotalPriceColumn;

    @FXML
    private TableView<Booking> bookingTable;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("None","ClientName","PackageName","Due");
        choiceBox.setItems(items);
        choiceBox.setValue("None");
    }

 
    public void clickBackButton(ActionEvent event) {
        try {
            changeScenewithBorderPane("dashboard.fxml","bookings.fxml", event, "Main Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doBookingTableQuery(ActionEvent event) {
        startDB();

        try {
            setConnection();

            ObservableList<Booking> bookings = FXCollections.observableArrayList();


            if (choiceBox.getValue() == "None") {
                preparedStatement1 = connection.prepareStatement("Select * FROM Bookings WHERE UserName=?");
                preparedStatement1.setString(1, User.getName());
            } else if (choiceBox.getValue() == "ClientName") {
                preparedStatement1 = connection
                        .prepareStatement("Select * FROM Bookings WHERE UserName=? AND ClientName=?");
                preparedStatement1.setString(1, User.getName());
                preparedStatement1.setString(2, searchTextField.getText());
            } else if (choiceBox.getValue() == "PackageName") {
                preparedStatement1 = connection
                        .prepareStatement("Select * FROM Bookings WHERE UserName=? AND BookedPackages=?");
                preparedStatement1.setString(1, User.getName());
                preparedStatement1.setString(2, searchTextField.getText());
            } 
            else {
                preparedStatement1 = connection
                        .prepareStatement("Select * FROM Bookings WHERE UserName=? AND Due<>0");
                preparedStatement1.setString(1, User.getName());
            }

            resultSet = preparedStatement1.executeQuery();

            while(resultSet.next())
            {
                bookings.add(new Booking(resultSet.getString("BookingID"), resultSet.getString("ClientName"), resultSet.getString("PersonNumber"), resultSet.getString("BookedPackages"), Integer.toString(resultSet.getInt("TotalPrice")), Integer.toString(resultSet.getInt("Paid")), Integer.toString(resultSet.getInt("Due"))));
            }

            BookingIDColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingID"));
            ClientNameColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("ClientName"));
            PersonNumberColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("PersonNumber"));
            PackageColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookedPackage"));
            TotalPriceColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("TotalPrice"));
            PaidColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Paid"));
            DueColumn.setCellValueFactory(new PropertyValueFactory<Booking, String>("Due"));
            
            bookingTable.setItems(bookings);

            searchTextField.setText(null);

        } catch (SQLException e) {
           e.printStackTrace();
        }
        finally
        {
            closeDB();
        }

    }
    
    
    
}