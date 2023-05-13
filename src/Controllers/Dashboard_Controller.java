package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import Classes.User;

public class Dashboard_Controller extends Basic_Controller implements Initializable {

    @FXML
    public Button SummaryButton;
    @FXML
    public Button ProfileButton;
    @FXML
    public Button ClientsButton;
    @FXML
    public Button PackagesButton;
    @FXML
    public Button BookingButton;
    @FXML
    private ImageView DashboardImg;
    @FXML
    private AnchorPane SidebarPane;
    @FXML
    public AnchorPane ContentPane;

    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        if (DashboardImg != null) {
            DashboardImg.setOnMouseClicked(event -> {

                if (!SidebarPane.isVisible()) {
                    SidebarPane.setVisible(true);

                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), SidebarPane);
                    ft.setFromValue(0);
                    ft.setToValue(1);

                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), SidebarPane);
                    tt.setToX(0);

                    ParallelTransition pt = new ParallelTransition(ft, tt);
                    pt.play();
                } else {
                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), SidebarPane);
                    ft.setFromValue(1);
                    ft.setToValue(0);

                    TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), SidebarPane);
                    tt.setToX(-200);

                    ParallelTransition pt = new ParallelTransition(ft, tt);
                    pt.setOnFinished(e -> SidebarPane.setVisible(false));
                    pt.play();
                }
            });
        }

    }

    public void clickSummaryButton(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("summary.fxml"));
        AnchorPane newPane = fxmlLoader.load();
        ContentPane.getChildren().clear();
        ContentPane.getChildren().setAll(newPane);

    }

    public void clickProfileButton(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile.fxml"));
        AnchorPane newPane = fxmlLoader.load();

        ContentPane.getChildren().clear();
        ContentPane.getChildren().setAll(newPane);

        nameLabel = (Label) newPane.lookup("#nameLabel");
        emailLabel = (Label) newPane.lookup("#emailLabel");
        passwordLabel = (Label) newPane.lookup("#passwordLabel");

        nameLabel.setText(User.getName());
        emailLabel.setText(User.Email);
        passwordLabel.setText(User.Password);

    }

    public void clickClientsButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("clients.fxml"));
        AnchorPane newPane = fxmlLoader.load();

        ContentPane.getChildren().clear();
        ContentPane.getChildren().setAll(newPane);

        Label ClientNumber = (Label) newPane.lookup("#ClientNumberLabel");
        ClientNumber.setText(new ClientScreen_Controller().getTotalClientNumber(User.getName()));

    }

    public void clickPackagesButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("packages.fxml"));
        AnchorPane newPane = fxmlLoader.load();

        ContentPane.getChildren().clear();
        ContentPane.getChildren().setAll(newPane);

        Label PackageNumber = (Label) newPane.lookup("#PackageNumberLabel");
        Label SpotNumber = (Label) newPane.lookup("#SpotNumberLabel");
        Label RangeLabel = (Label) newPane.lookup("#RangeLabel");

        PackageNumber.setText(new PackageScreen_Controller().getTotalNumber("PackageNumber", User.getName()));
        SpotNumber.setText(new PackageScreen_Controller().getTotalNumber("SpotNumber", User.getName()));
        RangeLabel.setText(new PackageScreen_Controller().getPriceRange(User.getName()));
    }

    public void clickBookingButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bookings.fxml"));
        AnchorPane newPane = fxmlLoader.load();

        ContentPane.getChildren().clear();
        ContentPane.getChildren().setAll(newPane);

        Label BookingNumber = (Label) newPane.lookup("#BookingNumberLabel");
        Label DueNumber = (Label) newPane.lookup("#DueNumberLabel");
        BookingNumber.setText(new BookingScreen_Controller().getBookingNumber(User.getName()));
        DueNumber.setText(new BookingScreen_Controller().getDueNumber(User.getName()));

    }

    @Override
    public void clickBackButton(ActionEvent event) {
        try {
            changeScenewithBorderPane("login.fxml", "None", event, "JourneyMate");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickSignOut(ActionEvent event) throws IOException {
        changeScenewithBorderPane("login.fxml", "None", event, "Sign In");
    }

    public void deleteUser(ActionEvent event) {
        startDB();
        try {
            setConnection();
            preparedStatement1 = connection.prepareStatement("Delete  FROM Users WHERE Name = ?");
            preparedStatement1.setString(1, User.getName());
            preparedStatement1.executeUpdate();
            preparedStatement1 = null;
            preparedStatement1 = connection.prepareStatement("Delete  FROM Clients WHERE UserName = ?");
            preparedStatement1.setString(1, User.getName());
            preparedStatement1.executeUpdate();

            preparedStatement1 = null;
            preparedStatement1 = connection.prepareStatement("Delete  FROM TourPackages WHERE UserName = ?");
            preparedStatement1.setString(1, User.getName());
            preparedStatement1.executeUpdate();

            preparedStatement1 = null;
            preparedStatement1 = connection.prepareStatement("Delete  FROM Bookings WHERE UserName = ?");
            preparedStatement1.setString(1, User.getName());
            preparedStatement1.executeUpdate();

            try {
                changeScenewithBorderPane("signup.fxml", "None", event, "Sign Up");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

    }
}
