package trainerWindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.sun.javafx.tools.packager.Main;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Cell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import utilities.DatabaseConnection;
import utilities.User;
import utilities.UserService;
import windowFunctions.functions;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;


public class TrainerController extends functions implements Initializable {


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

    private LoadTrainerWindow loadTrainerWindow;

    private UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        functions.initMenu(hamburger, drawer, drawerVbox, mainWindow);


    }

    public void loadEditProfile(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowTrainer/ProfileT.fxml", mainWindow);
    }

    public void loadTrainingPlan(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowTrainer/TrainingPlanT.fxml", mainWindow);
    }

    public void loadNutritionPlan(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowTrainer/NutritionPlanT.fxml", mainWindow);
    }


    public void loadProgress(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowTrainer/ProgressT.fxml", mainWindow);
    }

    public void loadCalendar(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowTrainer/CalendarT.fxml", mainWindow);
    }

    public void loadMessages(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowTrainer/MessageT.fxml", mainWindow);
    }

    public void loadOptions(ActionEvent actionEvent) throws IOException {
        functions.loadMainWindow("../mainWindowTrainer/OptionsT.fxml", mainWindow);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }


    public void setUser(LoadTrainerWindow loadTrainerWindow){
        this.loadTrainerWindow = loadTrainerWindow;
        this.userService = loadTrainerWindow.getUserService();

        String sql = "SELECT * FROM user";


        comboBox.getItems().add(new Label("elo"));
        comboBox.setConverter(new StringConverter<Label>() {
            @Override
            public String toString(Label object) {
                return object==null? "" : object.getText();
            }

            @Override
            public Label fromString(String string) {
                return new Label(string);
            }
        });
    }

    void updatecombo(){


    }

}
