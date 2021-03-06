package adminWindow;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.util.Duration;
import utilities.UserService;
import windowFunctions.Functions;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;


public class AdminController extends Functions implements Initializable {


    @FXML
    private JFXButton logoutButton;

    @FXML
    private Text textNick;

    @FXML
    public AnchorPane mainWindow;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox drawerVbox;

    public static Boolean userWindow;
    public static Boolean trainerWindow;

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Functions.initMenu(hamburger, drawer, drawerVbox, mainWindow);
        try {
            textNick.setText(userService.setLogin(IDuser));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    void loadWindow(Boolean userCheck, Boolean trainerCheck) throws IOException {
        userWindow = userCheck;
        trainerWindow = trainerCheck;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainWindowAdmin/UserA.fxml"));
        AnchorPane change = loader.load();

        mainWindow.getChildren().setAll(change);
        animation(mainWindow);
    }

    void animation(AnchorPane anchorPane){
        ScaleTransition fadeTransition = new ScaleTransition();
        fadeTransition.setDuration(Duration.millis(300));
        fadeTransition.setNode(anchorPane);
        fadeTransition.setFromX(1);
        fadeTransition.setToX(0);
        fadeTransition.setFromZ(1);
        fadeTransition.setToZ(0);
        fadeTransition.play();
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ScaleTransition fadeTransition2 = new ScaleTransition();
                fadeTransition2.setDuration(Duration.millis(500));
                fadeTransition2.setNode(anchorPane);
                fadeTransition2.setFromX(0);
                fadeTransition2.setToX(1);
                fadeTransition2.setFromZ(0);
                fadeTransition2.setToZ(1);
                fadeTransition2.play();
            }
        });
    }

    public void loadUser(ActionEvent actionEvent) throws IOException {
        loadWindow(true, false);
    }



    public void loadTrainers(ActionEvent actionEvent) throws IOException {
        loadWindow(false, true);
    }

    public void other(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowAdmin/OtherA.fxml", mainWindow);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }


}
