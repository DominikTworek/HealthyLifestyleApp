package LoginWindow;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    ImageView exitImage;


    @FXML
    void closeApp() {
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void changeToRegistryWindow(MouseEvent event) throws IOException {
        Parent RegistryWindowParent = FXMLLoader.load(getClass().getResource("../RegistryWindow/RegistryWindow.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Rejestracja");
        stage.setScene(new Scene(RegistryWindowParent));
        stage.show();
    }
}
