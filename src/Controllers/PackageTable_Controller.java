package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Classes.TourPackage;
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

public class PackageTable_Controller extends Basic_Controller implements Initializable{

    @FXML
    private TableView<TourPackage> packageTable;

    @FXML
    private TableColumn<TourPackage, String> nameColumn;

    @FXML
    private TableColumn<TourPackage, String> districtColumn;

    @FXML
    private TableColumn<TourPackage, String> spotColumn;

    @FXML
    private TableColumn<TourPackage, String> spotpriceColumn;

    @FXML
    private TableColumn<TourPackage, String> totalpriceColumn;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField searchTextField;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("None", "PackageName", "District");
        choiceBox.setItems(items);
        choiceBox.setValue("None");
    }

    @Override
    public void clickBackButton(ActionEvent event) {
        try {
            changeScenewithBorderPane("dashboard.fxml", event, "Main Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void doPackageTableQuery(ActionEvent event) {
        
        startDB();

        try {
            setConnection();

            ObservableList<TourPackage> packages = FXCollections.observableArrayList();

            if (choiceBox.getValue() == "None") {
                preparedStatement1 = connection.prepareStatement("Select * FROM TourPackages WHERE UserName=?");
                preparedStatement1.setString(1, User.Name);
            } else if (choiceBox.getValue() == "PackageName") {
                preparedStatement1 = connection
                        .prepareStatement("Select * FROM TourPackages WHERE UserName=? AND PackageName=?");
                preparedStatement1.setString(1, User.Name);
                preparedStatement1.setString(2, searchTextField.getText());
            } else if (choiceBox.getValue() == "District") {
                preparedStatement1 = connection
                        .prepareStatement("Select * FROM TourPackages WHERE UserName=? AND District =?");
                preparedStatement1.setString(1, User.Name);
                preparedStatement1.setString(2, searchTextField.getText());
            }

            resultSet = preparedStatement1.executeQuery();

            String PackageName =null;
            
            while (resultSet.next()) 
            {
                 
                String packageName = resultSet.getString("PackageName");

                String district = resultSet.getString("District");

                String spotName = resultSet.getString("SpotName");

                String spotPrice =Integer.toString(resultSet.getInt("SpotPrice"));

                String totalPrice =Integer.toString(resultSet.getInt("TotalPrice"));

                
                if(packageName.equals(PackageName))
                {
                   packages.add(new TourPackage(null,null, spotName,spotPrice,null));
                }
                else
                {
                    
                    PackageName=packageName;
                    packages.add(new TourPackage(packageName, district, spotName,spotPrice, totalPrice));
                }
                
            }

            nameColumn.setCellValueFactory(new PropertyValueFactory<TourPackage,String>("PackageName"));
            districtColumn.setCellValueFactory(new PropertyValueFactory<TourPackage,String>("District"));
            spotColumn.setCellValueFactory(new PropertyValueFactory<TourPackage,String>("SpotName"));
            spotpriceColumn.setCellValueFactory(new PropertyValueFactory<TourPackage,String>("SpotPrice"));
            totalpriceColumn.setCellValueFactory(new PropertyValueFactory<TourPackage,String>("TotalPrice"));

            packageTable.setItems(packages);

            searchTextField.setText(null);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }


    
}
    

