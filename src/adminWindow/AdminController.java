package adminWindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import windowFunctions.functions;

import java.io.IOException ;

public class adminController extends functions {

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

    public void initialize() {
        functions.initMenu(hamburger, drawer, drawerVbox, mainWindow);
    }

    public void loadUser(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowAdmin/UserA.fxml", mainWindow);
    }

    public void loadTrainers(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowAdmin/TrainerA.fxml", mainWindow);
    }

    public void other(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowAdmin/OtherA.fxml", mainWindow);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
}
