package RegistryWindow;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;


public class RegistryController {
    @FXML
    ImageView exitImage;

    @FXML
    JFXTextField loginField;

    @FXML
    JFXPasswordField passwordField;

    @FXML
    JFXTextField nameField;

    @FXML
    JFXTextField lastNameField;

    @FXML
    JFXComboBox genderComboBox;

    @FXML
    JFXTextField ageField;

    @FXML
    public void setComboBox() {
        genderComboBox.getItems().addAll(
                "mężczyzna",
                "kobieta"
        );
    }

    @FXML
    void changeToLoginWindow(MouseEvent event) throws IOException {
        Parent LoginWindowParent = FXMLLoader.load(getClass().getResource("../LoginWindow/LoginWindow.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(LoginWindowParent));
        stage.show();
    }

    @FXML
    void registryUser() {

    }
}
