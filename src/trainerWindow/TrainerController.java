package trainerWindow;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import utilities.UserService;
import windowFunctions.Functions;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
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

    private UserService userService = LoadLoginWindow.getUserService();

    private long IDuser = LoginController.getIDuser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Functions.initMenu(hamburger, drawer, drawerVbox, mainWindow);


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
        Functions.loadMainWindow("../mainWindowTrainer/CalendarT.fxml", mainWindow);
    }

    public void loadMessages(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowTrainer/MessageT.fxml", mainWindow);
    }

    public void loadOptions(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowTrainer/OptionsT.fxml", mainWindow);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

}
