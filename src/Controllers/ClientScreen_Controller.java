package Controllers;

import Classes.Client;
import Classes.User;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ClientScreen_Controller extends Basic_Controller implements ControllerFunctions{
    @FXML
    private Button DelButton;
    @FXML
    private Label ClientNumberLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField mobileNumTextField;
    @FXML
    private TextField addressTextField;

    private static Client client = new Client(null,null,null);

    public String getTotalClientNumber(String UserName) {
        
        String st = "0";

        startDB();

        try {

            setConnection();
            preparedStatement1 = connection.prepareStatement("SELECT COUNT(*) FROM Clients WHERE UserName = ?");
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
            changeScenewithAnchorPane("addclient.fxml", event, "Add Client");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addClientToDB(ActionEvent event) {
        startDB();
        String Name = nameTextField.getText();
        String MobileNum = mobileNumTextField.getText();
        String Address = addressTextField.getText();

        try {
            setConnection();
            preparedStatement2 = connection
                    .prepareStatement("SELECT * FROM Clients WHERE UserName = ? AND MobileNumber = ? ");
            preparedStatement2.setString(1, User.Name);
            preparedStatement2.setString(2, MobileNum);
            resultSet = preparedStatement2.executeQuery();

            if (Name.isEmpty() || MobileNum.isEmpty() || Address.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provide all necessary information");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            } else {
                if (resultSet.isBeforeFirst()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Client already exists");
                    DialogPane dialogpane = alert.getDialogPane();
                    dialogpane.setStyle("-fx-background-color:#e36212;");
                    alert.show();
                } else {
                    preparedStatement1 = connection.prepareStatement(
                            "INSERT INTO Clients (UserName,ClientName,MobileNumber,Address) VALUES(?,?,?,?)");
                    preparedStatement1.setString(1, User.Name);
                    preparedStatement1.setString(2, Name);
                    preparedStatement1.setString(3, MobileNum);
                    preparedStatement1.setString(4, Address);
                    preparedStatement1.executeUpdate();
                    try {
                        changeScenewithBorderPane("dashboard.fxml", event, "Main Menu");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

    @Override
    public void modifyAction(ActionEvent event) {
        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setTitle("Change Client Info");
        dialog.setHeaderText("Enter Client Mobile Number:");
        dialog.initOwner((Stage) DelButton.getScene().getWindow());

        TextField textField = new TextField();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(textField);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        dialogPane.setStyle("-fx-background-color:#e36212;");

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                gotomodifyClientScreen(event, textField.getText());
            }
            return null;
        });

        dialog.show();
    }


    public void gotomodifyClientScreen(ActionEvent event, String MobileNum) {

        startDB();

        try {
            setConnection();
            preparedStatement1 = connection
                    .prepareStatement("Select * FROM Clients WHERE UserName = ? AND MobileNumber = ?");
            preparedStatement1.setString(1, User.Name);
            preparedStatement1.setString(2, MobileNum);
            resultSet = preparedStatement1.executeQuery();

            if (MobileNum == null || !resultSet.isBeforeFirst()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Client not found");
                alert.setContentText("The Client was not found in the database.");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            } else {
                while (resultSet.next()) {
                    client.setName(resultSet.getString("ClientName"));
                    client.setMobileNumber(resultSet.getString("MobileNumber"));
                    client.setAddress(resultSet.getString("Address"));
                }

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("modifyClientInfo.fxml"));
                    AnchorPane root = fxmlLoader.load();
                    nameTextField = (TextField)root.lookup("#nameTextField");
                    addressTextField = (TextField)root.lookup("#addressTextField");
                    nameTextField.setText(client.getName());
                    addressTextField.setText(client.getAddress());

                    Stage stage = null;
                    if (event != null && event.getSource() instanceof Node) {
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    }

                    if (stage != null) {
                        stage.setScene(new Scene(root));
                        stage.setTitle("Change Client Info");
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

    public void modifyClientInfoToDB(ActionEvent event) {
        startDB();

        try {
            setConnection();
            preparedStatement1 = connection.prepareStatement(
                    "UPDATE Clients SET ClientName = ?,Address =? WHERE UserName = ? AND MobileNumber = ?");
            preparedStatement1.setString(1, nameTextField.getText());
            preparedStatement1.setString(2, addressTextField.getText());
            preparedStatement1.setString(3, User.Name);
            preparedStatement1.setString(4, client.getMobileNumber());
            preparedStatement1.executeUpdate();
            
            try {
               changeScenewithBorderPane("dashboard.fxml", event,"Main Menu");
            } catch (IOException e) {
               e.printStackTrace();
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
        dialog.setTitle("Delete Client");
        dialog.setHeaderText("Enter Client Mobile Number:");
        dialog.initOwner((Stage) DelButton.getScene().getWindow());

        TextField textField = new TextField();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(textField);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        dialogPane.setStyle("-fx-background-color:#e36212;");

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                deleteClientFromDB(event, textField.getText());
            }
            return null;
        });

        dialog.show();
    }

    public void deleteClientFromDB(ActionEvent event, String MobileNum) {
        startDB();

        try {
            setConnection();
            preparedStatement1 = connection
                    .prepareStatement("Delete FROM Clients WHERE UserName = ? AND MobileNumber = ?;");
            preparedStatement1.setString(1, User.Name);
            preparedStatement1.setString(2, MobileNum);
            int roweffected = preparedStatement1.executeUpdate();

            if (roweffected == 0 || MobileNum == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Client not found");
                alert.setContentText("The Client was not found in the database.");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            }


            ClientNumberLabel.setText(getTotalClientNumber(User.Name));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
    
    @Override
    public void showTable(ActionEvent event)
    {
        try {
          changeScenewithAnchorPane("showClientTable.fxml", event,"Clients Information");
        } catch (IOException e) {
            e.printStackTrace();
        }
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