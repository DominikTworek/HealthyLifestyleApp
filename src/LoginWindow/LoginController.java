package LoginWindow;

import RegistryWindow.LoadRegistryWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    ImageView exitImage;


    @FXML
    void closeApp() {
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void changeToRegistryWindow(MouseEvent event) throws Exception {
        Parent RegistryWindowParent = LoadRegistryWindow.execWindow();
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Rejestracja");
        stage.setScene(new Scene(RegistryWindowParent));
        stage.show();
    }

    @FXML
    void changeToResetPasswordWindow(MouseEvent event) throws Exception {
        Parent ResetPasswordWindowParent = FXMLLoader.load(getClass()
                .getResource("../ResetPasswordWindow/ResetPasswordWindow.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Resetuj hasło");
        stage.setScene(new Scene(ResetPasswordWindowParent));
        stage.show();
    }
}
