package mainWindowUser;

import com.jfoenix.controls.JFXButton;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class changeProfileUController {
    @FXML
    JFXButton changeRegisterButton;

    @FXML
    public void changeToRegisterView(Event event) throws IOException {
        Parent RegistryWindowParent = FXMLLoader.load(getClass()
                .getResource("editRegister.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Zmien dane rejestracji");
        stage.setScene(new Scene(RegistryWindowParent));
        stage.show();
    }

    @FXML
    public void changeToProfileView(Event event) throws IOException {
        Parent ProfileWindowParent = FXMLLoader.load(getClass()
                .getResource("ProfileU.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Zmien dane");
        stage.setScene(new Scene(ProfileWindowParent));
        stage.show();
    }
}
