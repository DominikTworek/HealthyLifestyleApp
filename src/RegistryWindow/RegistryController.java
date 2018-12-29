package RegistryWindow;

import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class RegistryController {
    @FXML
    ImageView exitImage;

    @FXML
    JFXComboBox comboBox;

    @FXML
    public void setComboBox() {
        comboBox.getItems().addAll(
                "mężczyzna",
                "kobieta"
        );
    }

    @FXML
    void closeApp() {
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
    }
}
