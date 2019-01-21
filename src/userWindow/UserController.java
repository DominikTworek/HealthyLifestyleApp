package userWindow;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import java.io.IOException;
import java.rmi.RemoteException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utilities.UserService;
import windowFunctions.Functions;

public class UserController extends Functions {

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

    @FXML
    Text textNick;

    @FXML
    private JFXButton changeRegisterButton;

    private UserService userService = LoadLoginWindow.getUserService();

    private long IDuser = LoginController.getIDuser();

    public void initialize() {
        Functions.initMenu(hamburger, drawer, drawerVbox, mainWindow);
        try {
            textNick.setText(userService.setLogin(IDuser));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void loadEditProfile(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../mainWindowUser/changeProfileU.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Wybierz opcje");
        stage.setScene(new Scene(root, 226, 135));
        stage.show();
    }

    public void loadChooseTrainer(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowUser/TrainerU.fxml", mainWindow);
    }

    public void loadNutritionPlan(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowUser/NutritionPlanU.fxml", mainWindow);
    }

    public void loadTrainingPlan(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowUser/TrainingPlanU.fxml", mainWindow);
    }

    public void loadUpdateProgress(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowUser/UpdateProgressU.fxml", mainWindow);
    }

    public void loadCalendar(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowUser/CalendarU.fxml", mainWindow);
    }


    public void loadMessage(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowUser/MessageU.fxml", mainWindow);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void changeToRegisterView() throws IOException {
        ((Stage)changeRegisterButton.getScene().getWindow()).close();
        Functions.loadMainWindow("../mainWindowUser/editRegister.fxml",mainWindow);
    }
}
