package userWindow;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import java.io.IOException;
import java.time.YearMonth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainWindowUser.CalendarUController;
import utilities.Calendar.FullCalendarView;
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

    public void initialize() {
        Functions.initMenu(hamburger, drawer, drawerVbox, mainWindow);
    }

    public void loadEditProfile(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowUser/ProfileU.fxml", mainWindow);
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
        //Functions.loadMainWindow("../mainWindowUser/CalendarU.fxml", mainWindow);
        FXMLLoader loader = new FXMLLoader(Functions.class.getResource("../mainWindowUser/CalendarU.fxml"));
        AnchorPane change = loader.load();
        CalendarUController controller = loader.getController();
        controller.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
        mainWindow.getChildren().setAll(change);
    }


    public void loadMessage(ActionEvent actionEvent) throws IOException {
        Functions.loadMainWindow("../mainWindowUser/MessageU.fxml", mainWindow);
    }

    public void logout(ActionEvent actionEvent) {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
}
