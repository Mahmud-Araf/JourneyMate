package Controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import Classes.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class SummaryScreenController extends Basic_Controller implements Initializable {

    @FXML
    private Label BookingLabel;

    @FXML
    private Label CPLabel;

    @FXML
    private Label ClientLabel;

    @FXML
    private Label DueLabel;

    @FXML
    private Label PackageLabel;

    @FXML
    private Label TDLabel;

    @FXML
    private Label TSLabel;

    private static int tsPrice=0,tdPrice=0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BookingLabel.setText(new BookingScreen_Controller().getBookingNumber(User.getName()));
        DueLabel.setText(new BookingScreen_Controller().getDueNumber(User.getName()));

        ClientLabel.setText(new ClientScreen_Controller().getTotalClientNumber(User.getName()));

        PackageLabel.setText(new PackageScreen_Controller().getTotalNumber("PackageNumber", User.getName()));

        TSLabel.setText(getTotal("TotalPrice",User.getName()));
        TDLabel.setText(getTotal("Due", User.getName()));
        CPLabel.setText(getCP());


    }

    private String getTotal(String Column,String UserName)
    {
        String str ="0";

        startDB();

        try {
            setConnection();
            preparedStatement1 =connection.prepareStatement("SELECT SUM(" + Column + ") FROM Bookings WHERE UserName = ?");
            preparedStatement1.setString(1,UserName);
            resultSet =preparedStatement1.executeQuery();

            if(resultSet.isBeforeFirst())
            {
 
                while (resultSet.next()) {

                 int price =resultSet.getInt(1);
                 
                 
                 if(Column.equals("TotalPrice"))
                 {
                    tsPrice=price;
                 }
                 else if(Column.equals("Due"))
                 {
                    tdPrice =price;
                 }
                 str=Integer.toString(price);
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

        str+=" Tk";

        return str;
    }

    private String getCP()
    {
        String str="0";

        str=Integer.toString(tsPrice-tdPrice);
        
        str+=" Tk";

        return str;
    }
    
}
