package windowFunctions;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import com.sun.javafx.tools.packager.Main;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import utilities.UserService;

import java.io.IOException;

public class Functions {


    private Main main;

    private UserService userService;

    public static void loadMainWindow(String source, AnchorPane mainWindow) throws IOException {
        FXMLLoader loader = new FXMLLoader(Functions.class.getResource(source));
        AnchorPane change = loader.load();
        mainWindow.getChildren().setAll(change);
    }

    public static void initMenu(JFXHamburger hamburger, JFXDrawer drawer, VBox drawerVbox, AnchorPane mainWindow) {
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
                mainWindow.setLayoutX(75);
            }
        });
    }


}
