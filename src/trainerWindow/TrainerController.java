package trainerWindow;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import mainWindowTrainer.CalendarTController;
import mainWindowUser.CalendarUController;
import utilities.Calendar.FullCalendarView;
import utilities.UserService;
import windowFunctions.Functions;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.YearMonth;
import java.util.ResourceBundle;


public class TrainerController extends Functions implements Initializable {


    @FXML
    private JFXButton logoutButton;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    public AnchorPane mainWindow;

    @FXML
    private VBox drawerVbox;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXComboBox<Label> comboBox;

    @FXML
    private Text textNick;

    private UserService userService = LoadLoginWindow.getUserService();

    private Long IDuser = LoginController.getIDuser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Functions.initMenu(hamburger, drawer, drawerVbox, mainWindow);
        try {
            textNick.setText(userService.setLogin(IDuser));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void loadEditProfile(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowTrainer/ProfileT.fxml", mainWindow);
    }

    public void loadTrainingPlan(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowTrainer/TrainingPlanT.fxml", mainWindow);
    }

    public void loadNutritionPlan(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowTrainer/NutritionPlanT.fxml", mainWindow);
    }


    public void loadProgress(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowTrainer/ProgressT.fxml", mainWindow);
    }

    public void loadCalendar(ActionEvent actionEvent) throws IOException {
        //Functions.loadMainWindow("../mainWindowTrainer/CalendarT.fxml", mainWindow);
        FXMLLoader loader = new FXMLLoader(Functions.class.getResource("../mainWindowTrainer/CalendarT.fxml"));
        AnchorPane change = loader.load();
        CalendarTController controller = loader.getController();
        controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
        mainWindow.getChildren().setAll(change);
    }


    public void loadOptions(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowTrainer/OptionsT.fxml", mainWindow);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    public void loadMessages(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(Functions.class.getResource("../mainWindowUser/MessageU.fxml"));
        GridPane change = loader.load();
        mainWindow.getChildren().setAll(change);
    }

}
