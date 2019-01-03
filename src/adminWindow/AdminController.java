package adminWindow;

import LoginWindow.LoadLoginWindow;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainWindowAdmin.UserAController;
import utilities.User;
import utilities.UserService;
import windowFunctions.Functions;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class AdminController extends Functions {


    @FXML
    private JFXButton logoutButton;

    @FXML
    public AnchorPane mainWindow;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox drawerVbox;

    private  UserService userService = LoadLoginWindow.getUserService();

    public void initialize() {
        Functions.initMenu(hamburger, drawer, drawerVbox, mainWindow);
    }

    public void loadUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainWindowAdmin/UserA.fxml"));
        AnchorPane change = loader.load();

        mainWindow.getChildren().setAll(change);
    }



    public void loadTrainers(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowAdmin/TrainerA.fxml", mainWindow);
    }

    public void other(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowAdmin/OtherA.fxml", mainWindow);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

}
