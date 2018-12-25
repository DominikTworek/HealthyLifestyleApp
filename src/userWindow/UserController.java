package userWindow;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class UserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public AnchorPane mainWindow;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private VBox drawerVbox;

    private String source;

    public void initialize() {
        initMenu();
    }

    private void initMenu() {
        HamburgerSlideCloseTransition zadanie = new HamburgerSlideCloseTransition(hamburger);
        zadanie.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (Event event) -> {
            zadanie.setRate(zadanie.getRate() * -1);
            zadanie.play();
            if (drawer.isClosed()) {
                drawer.open();
                drawer.setPrefWidth(Region.USE_COMPUTED_SIZE);
                drawerVbox.setVisible(true);
                mainWindow.setLayoutX(0);
            } else {
                drawer.close();
                drawer.setPrefWidth(0);
                drawerVbox.setVisible(false);
                mainWindow.setLayoutX(150);
            }
        });
    }


    void loadMainWindow(String source) throws IOException {
        AnchorPane change = FXMLLoader.load(getClass().getResource(source));
        mainWindow.getChildren().setAll(change);
    }


    public void loadEditProfile(ActionEvent actionEvent) throws IOException {
        loadMainWindow("../mainWindow/Profile.fxml");
    }

    public void loadChooseTrainer(ActionEvent actionEvent) throws IOException {
        loadMainWindow("../mainWindow/Trainer.fxml");
    }

    public void loadNutritionPlan(ActionEvent actionEvent) throws IOException {
        loadMainWindow("../mainWindow/NutritionPlan.fxml");
    }

    public void loadTrainingPlan(ActionEvent actionEvent) throws IOException {
        loadMainWindow("../mainWindow/TrainingPlan.fxml");
    }

    public void loadUpdateProgress(ActionEvent actionEvent) throws IOException {
        loadMainWindow("../mainWindow/UpdateProgress.fxml");
    }

    public void loadCalendar(ActionEvent actionEvent) throws IOException {
        loadMainWindow("../mainWindow/Calendar.fxml");
    }


    public void loadMessage(ActionEvent actionEvent) throws IOException {
        loadMainWindow("../mainWindow/Message.fxml");
    }
}
