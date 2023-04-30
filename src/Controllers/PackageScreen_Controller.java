package Controllers;

import java.sql.SQLException;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PackageScreen_Controller extends Basic_Controller implements ControllerFunctions{

    
    @FXML
    private Label PackageNumberLabel;
    @FXML
    private Label SpotNumberLabel;
    @FXML 
    private Label RangeLabel;
    @FXML
    private Button DelButton;

    public String getTotalNumber(String type,String UserName)
    {
        String st = "0";

        startDB();

        try {

            setConnection();

            if(type=="PackageNumber")
            {
                preparedStatement1 = connection.prepareStatement("SELECT COUNT(DISTINCT PackageName) FROM TourPackages WHERE UserName = ?;");
               
            }
            if(type == "SpotNumber")
            {
                preparedStatement1 = connection.prepareStatement("SELECT COUNT(DISTINCT SpotName) FROM TourPackages WHERE UserName = ?;");
            }
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

    public String getPriceRange(String UserName)
    {
        String st = "0 Tk - 0 Tk";

        startDB();

        try {

            setConnection();

            preparedStatement1 =connection.prepareStatement("SELECT MIN(TotalPrice) FROM TourPackages WHERE UserName = ?;");
            preparedStatement1.setString(1,UserName);
            resultSet = preparedStatement1.executeQuery();
            String  st1=null;
            if (resultSet.next())
            {
                st1 = Integer.toString(resultSet.getInt(1));
            }

            preparedStatement2 =connection.prepareStatement("SELECT MAX(TotalPrice) FROM TourPackages WHERE UserName = ?;");
            preparedStatement2.setString(1,UserName);
            resultSet = preparedStatement2.executeQuery();
            String  st2=null;
            if (resultSet.next()) 
            {
                st2 = Integer.toString(resultSet.getInt(1));
            }

            if(st1!=null && st2!=null)
            {
                st=st1+" Tk - "+st2+" Tk";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return st; 
    }






    @Override
    protected void clickBackButton(ActionEvent event) {
        try {
            changeScenewithBorderPane("dashboard.fxml", event, "Main Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    @Override
    public void addAction(ActionEvent event) {
        
        
    }






    @Override
    public void modifyAction(ActionEvent event) {
        
        
    }






    @Override
    public void deleteAction(ActionEvent event) {

        Alert dialog = new Alert(Alert.AlertType.NONE);
        dialog.setTitle("Delete Package");
        dialog.setHeaderText("Enter Package Name:");
        dialog.initOwner((Stage) DelButton.getScene().getWindow());

        TextField textField = new TextField();
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(textField);
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CLOSE);
        dialogPane.setStyle("-fx-background-color:#e36212;");

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.OK) {
                deletePackageFromDB(event, textField.getText());
            }
            return null;
        });

        dialog.show();
    }


    public void deletePackageFromDB(ActionEvent event, String PackageName) {
        startDB();

        try {
            setConnection();
            preparedStatement1 = connection
                    .prepareStatement("Delete FROM TourPackages WHERE UserName = ? AND PackageName = ?");
            preparedStatement1.setString(1, User.Name);
            preparedStatement1.setString(2, PackageName);
            int roweffected = preparedStatement1.executeUpdate();

            if (roweffected == 0 || PackageName == null) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Package not found");
                alert.setContentText("The Package was not found in the database.");
                DialogPane dialogpane = alert.getDialogPane();
                dialogpane.setStyle("-fx-background-color:#e36212;");
                alert.show();
            }
            
            closeDB();
            PackageNumberLabel.setText(getTotalNumber("PackageNumber",User.Name));
            closeDB();
            SpotNumberLabel.setText(getTotalNumber("SpotNumber",User.Name));
            closeDB();
            RangeLabel.setText(getPriceRange(User.Name));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }






    @Override
    public void showTable(ActionEvent event) {
        
        
    }
}
