package ResetPasswordWindow;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ResetPasswordWindowController {

    @FXML
    JFXTextField rLoginField;

    @FXML
    JFXTextField rAgeField;

    @FXML
    JFXPasswordField rPasswordField1;

    @FXML
    JFXPasswordField rPasswordField2;

    @FXML
    void changeToLoginWindow(MouseEvent event) throws IOException {
        Parent LoginWindowParent = FXMLLoader.load(getClass().getResource("../LoginWindow/LoginWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(LoginWindowParent));
        stage.show();
    }

    @FXML
    void resetUserPassword(MouseEvent event) throws IOException {
        Parent LoginWindowParent = FXMLLoader.load(getClass().getResource("../LoginWindow/LoginWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(LoginWindowParent));
        stage.show();
    }
}
