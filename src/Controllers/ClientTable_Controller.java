package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Classes.Client;
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

public class ClientTable_Controller extends Basic_Controller implements Initializable  {

    @FXML
    private TableView<Client> clientTable;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> mobileNumColumn;

    @FXML
    private TableColumn<Client, String> addressColumn;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField searchTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("None", "Name", "MobileNumber", "Address");
        choiceBox.setItems(items);
        choiceBox.setValue("None");

    }

    @Override
    public void clickBackButton(ActionEvent event) {
        try {
            changeScenewithBorderPane("dashboard.fxml","clients.fxml", event, "Main Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doClientTableQuery(ActionEvent event) {
        startDB();

        try {
            setConnection();

            ObservableList<Client> clients = FXCollections.observableArrayList();

            if (choiceBox.getValue() == "None") {
                preparedStatement1 = connection.prepareStatement("Select * FROM Clients WHERE UserName=?");
                preparedStatement1.setString(1, User.getName());
            } else if (choiceBox.getValue() == "Name") {
                preparedStatement1 = connection
                        .prepareStatement("Select * FROM Clients WHERE UserName=? AND ClientName=?");
                preparedStatement1.setString(1, User.getName());
                preparedStatement1.setString(2, searchTextField.getText());
            } else if (choiceBox.getValue() == "MobileNumber") {
                preparedStatement1 = connection
                        .prepareStatement("Select * FROM Clients WHERE UserName=? AND MobileNumber=?");
                preparedStatement1.setString(1, User.getName());
                preparedStatement1.setString(2, searchTextField.getText());
            } else {
                preparedStatement1 = connection
                        .prepareStatement("Select * FROM Clients WHERE UserName=? AND Address=?");
                preparedStatement1.setString(1, User.getName());
                preparedStatement1.setString(2, searchTextField.getText());
            }

            resultSet = preparedStatement1.executeQuery();

            while (resultSet.next()) {
                clients.add(new Client(resultSet.getString("ClientName"), resultSet.getString("MobileNumber"),
                        resultSet.getString("Address")));
            }

            nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("Name"));
            mobileNumColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("MobileNumber"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("Address"));

            clientTable.setItems(clients);

            searchTextField.setText(null);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }


    
}