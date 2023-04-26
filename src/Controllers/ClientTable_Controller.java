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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientTable_Controller extends Basic_Controller implements Initializable{

    @FXML
    private TableView<Client>clientTable;
    
    @FXML
    private TableColumn<Client, String> nameColumn;
    
    @FXML
    private TableColumn<Client, String> mobileNumColumn;
    
    @FXML
    private TableColumn<Client, String> addressColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startDB();
        try {
            setConnection();

            ObservableList<Client> clients = FXCollections.observableArrayList();

            preparedStatement1 = connection.prepareStatement("Select * FROM Clients WHERE UserName=?");
            preparedStatement1.setString(1,User.Name);

            resultSet=preparedStatement1.executeQuery();

            while (resultSet.next()) {
                clients.add(new Client(resultSet.getString("ClientName"), resultSet.getString("MobileNumber"), resultSet.getString("Address")));
            }

            nameColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("Name"));
            mobileNumColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("MobileNumber"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("Address"));

            clientTable.setItems(clients);
        

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            closeDB();
        }
    }

    @Override
    public void clickBackButton(ActionEvent event)
    {
        try {
        changeScenewithBorderPane("dashboard.fxml", event, "Main Menu");    
        } catch (IOException e) {
           e.printStackTrace();
        }
    }
    
}