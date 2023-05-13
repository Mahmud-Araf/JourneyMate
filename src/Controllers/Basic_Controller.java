package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Basic_Controller {

    protected Connection connection;
    protected PreparedStatement preparedStatement1, preparedStatement2;
    protected ResultSet resultSet;

    public static Dashboard_Controller dc;

    protected static void changeScenewithBorderPane(String fxmlFilePath, String contentFxml, ActionEvent event,
            String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(Basic_Controller.class.getResource(fxmlFilePath));
        BorderPane root = loader.load();

        if (fxmlFilePath.equals("dashboard.fxml")) {
            Basic_Controller.dc = loader.getController();
            dc.ContentPane.getChildren().clear();

            if (!contentFxml.equals("None")) {
                AnchorPane newPane = FXMLLoader.load(Basic_Controller.class.getResource(contentFxml));
                dc.ContentPane.getChildren().setAll(newPane);

                if (contentFxml.equals("about.fxml")) {
                    dc.SummaryButton.requestFocus();
                }
                if (contentFxml.equals("clients.fxml")) {
                    dc.ClientsButton.requestFocus();
                    Label ClientNumber = (Label) newPane.lookup("#ClientNumberLabel");
                    ClientNumber.setText(new ClientScreen_Controller().getTotalClientNumber(User.getName()));

                } else if (contentFxml.equals("packages.fxml")) {
                    dc.PackagesButton.requestFocus();

                    Label PackageNumber = (Label) newPane.lookup("#PackageNumberLabel");
                    Label SpotNumber = (Label) newPane.lookup("#SpotNumberLabel");
                    Label RangeLabel = (Label) newPane.lookup("#RangeLabel");

                    PackageNumber
                            .setText(new PackageScreen_Controller().getTotalNumber("PackageNumber", User.getName()));
                    SpotNumber.setText(new PackageScreen_Controller().getTotalNumber("SpotNumber", User.getName()));
                    RangeLabel.setText(new PackageScreen_Controller().getPriceRange(User.getName()));

                } else if (contentFxml.equals("bookings.fxml")) {
                    dc.BookingButton.requestFocus();

                    Label BookingNumber = (Label) newPane.lookup("#BookingNumberLabel");
                    Label DueNumber = (Label) newPane.lookup("#DueNumberLabel");
                    BookingNumber.setText(new BookingScreen_Controller().getBookingNumber(User.getName()));
                    DueNumber.setText(new BookingScreen_Controller().getDueNumber(User.getName()));
                }
            }
        }
        Stage stage = null;
        if (event != null && event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        if (stage != null) {
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();

            scene.getRoot().requestFocus();
        }
    }

    protected static void changeScenewithAnchorPane(String fxmlFilePath, ActionEvent event, String title)
            throws IOException {
        AnchorPane root = FXMLLoader.load(Basic_Controller.class.getResource(fxmlFilePath));
        Stage stage = null;
        if (event != null && event.getSource() instanceof Node) {
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        }

        if (stage != null) {

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle(title);
            stage.setResizable(false);
            stage.show();
            scene.getRoot().requestFocus();
        }
    }

    protected void clickBackButton(ActionEvent event) {
        System.out.println("Back button clicked");
    };

    protected void startDB() {
        connection = null;
        preparedStatement1 = null;
        preparedStatement2 = null;
        resultSet = null;
    }

    protected void setConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/JourneyMate?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8";
        String user = "araf";
        String password = "password";
        String databasePath = "src/MySQL Database/JourneyMate.sql";
        url += "&url=file:" + databasePath;
        connection = DriverManager.getConnection(url, user, password);
    }

    protected void closeDB() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement1 != null) {
            try {
                preparedStatement1.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (preparedStatement2 != null) {
            try {
                preparedStatement2.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
