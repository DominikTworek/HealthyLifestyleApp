package LoginWindow;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    ImageView exitImage;


    @FXML
    void closeApp() {
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
    }
}
