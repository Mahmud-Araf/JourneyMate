package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    @FXML
    private TextField PackageNameTextField;
    @FXML
    private TextField DistrictTextField;
    @FXML
    private TextField spotNameTextField;
    @FXML
    private TextField spotPriceTextField;

    @FXML
    private ListView<String>spotNameListView;
    @FXML
    private ListView<Integer>spotPriceListView;



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
    public void clickBackButton(ActionEvent event) {
        try {
            changeScenewithBorderPane("dashboard.fxml", event, "Main Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addSpotNameClicked(ActionEvent event) 
    {
        String text = spotNameTextField.getText();
        if (!text.isEmpty()) 
        {
            spotNameListView.getItems().add(text);
            spotNameTextField.clear();
        }
    }

    @FXML
    private void addSpotPriceClicked(ActionEvent event) 
    {
        String text = spotPriceTextField.getText();
        if (!text.isEmpty()) 
        {
            spotPriceListView.getItems().add(Integer.parseInt(text));
            spotPriceTextField.clear();
        }
    }

    public void removeFromSpotName(ActionEvent event)
    {
        int selectedIndex = spotNameListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            spotNameListView.getItems().remove(selectedIndex);
        }
    }

    public void removeFromSpotPrice(ActionEvent event)
    {
        int selectedIndex = spotPriceListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) 
        {
            spotPriceListView.getItems().remove(selectedIndex);
        }
    }


    @Override
    public void addAction(ActionEvent event) {
        try {
            changeScenewithAnchorPane("addPackage.fxml", event, "Main Menu");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void addPackageToDB(ActionEvent event)
    {
        startDB();
        var spotNameArrayList = new ArrayList<String>();
        var spotPriceArrayList =new ArrayList<Integer>();

        String PackageNamestr = PackageNameTextField.getText();
        String Districtstr = DistrictTextField.getText();

        spotNameArrayList.addAll(spotNameListView.getItems());
        spotPriceArrayList.addAll(spotPriceListView.getItems());

        int SpotNameSize = spotNameArrayList.size();
        int SpotPriceSize = spotPriceArrayList.size();

        if(SpotNameSize==0||SpotNameSize!=SpotPriceSize||PackageNamestr==null ||Districtstr==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provide all necessary information");
            DialogPane dialogpane = alert.getDialogPane();
            dialogpane.setStyle("-fx-background-color:#e36212;");
            alert.show();
        }
        else
        {
            try {
                    setConnection();
                    preparedStatement2 = connection
                            .prepareStatement("SELECT * FROM TourPackages WHERE UserName = ? AND PackageName = ? ");
                    preparedStatement2.setString(1, User.Name);
                    preparedStatement2.setString(2,PackageNamestr);
                    resultSet = preparedStatement2.executeQuery();

                    if (resultSet.isBeforeFirst())
                    {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Package already exists");
                        DialogPane dialogpane = alert.getDialogPane();
                        dialogpane.setStyle("-fx-background-color:#e36212;");
                        alert.show();
                    }
                    else{

                        int TotalPriceINT=0;

                        for(int i=0;i<SpotPriceSize;i++)
                        {
                            TotalPriceINT+=spotPriceArrayList.get(i);
                        }
                        
                        for(int i=0;i<SpotNameSize;i++)
                        {
                            preparedStatement1 = connection.prepareStatement(
                            "INSERT INTO TourPackages (UserName,PackageName,District,SpotName,SpotPrice,TotalPrice) VALUES(?,?,?,?,?,?)");
                            preparedStatement1.setString(1, User.Name);
                            preparedStatement1.setString(2, PackageNamestr);
                            preparedStatement1.setString(3, Districtstr);
                            preparedStatement1.setString(4, spotNameArrayList.get(i));
                            preparedStatement1.setInt(5, spotPriceArrayList.get(i));
                            preparedStatement1.setInt(6, TotalPriceINT);
                            preparedStatement1.executeUpdate();
                        }

                        try {
                            changeScenewithBorderPane("dashboard.fxml", event, "Main Menu");
                        } catch (IOException e) {
                            e.printStackTrace();
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
            
            
            PackageNumberLabel.setText(getTotalNumber("PackageNumber",User.Name));
            
            SpotNumberLabel.setText(getTotalNumber("SpotNumber",User.Name));
            
            RangeLabel.setText(getPriceRange(User.Name));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }






    @Override
    public void showTable(ActionEvent event) {
        
        try {
            changeScenewithAnchorPane("showPackageTable.fxml", event,"Package Details");
          } catch (IOException e) {
              e.printStackTrace();
          }
    }
}
