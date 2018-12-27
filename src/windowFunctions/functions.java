package windowFunctions;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class functions {

    public static void loadMainWindow(String source, AnchorPane mainWindow) throws IOException {
        AnchorPane change = FXMLLoader.load(functions.class.getResource(source));
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
                mainWindow.setLayoutX(150);
            }
        });
    }

}
