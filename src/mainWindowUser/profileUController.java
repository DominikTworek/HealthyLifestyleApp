package mainWindowUser;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class profileUController implements Initializable {

    @FXML
    ImageView exitImage;

    @FXML
    JFXTextField heightField;

    @FXML
    JFXTextField weightField;

    @FXML
    JFXComboBox<String> neatComboBox;

    @FXML
    JFXComboBox<String> goalComboBox;

    @FXML
    JFXTextArea otherTextArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCombobox();
    }

    void setCombobox(){
        neatComboBox.getItems().addAll("Niska", "Srednia", "Wysoka");
        goalComboBox.getItems().addAll("Utrata tkanki tluszczowej", "Budowa miesni");
    }

    @FXML
    void closeApp() {
        ((Stage)heightField.getScene().getWindow()).close();
    }
}
