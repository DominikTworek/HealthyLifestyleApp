package trainerWindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import windowFunctions.functions;

import java.io.IOException;

public class trainerController extends functions {


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


    public void initialize() {
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
}
