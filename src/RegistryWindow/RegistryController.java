package RegistryWindow;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.User;
import utilities.UserService;

import java.io.IOException;
import java.rmi.RemoteException;


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

    private LoadRegistryWindow loadRegistryWindow;

    private UserService userService;

    @FXML
    public void setComboBox() {
        genderComboBox.getItems().addAll("mezczyzna", "kobieta");
    }

    @FXML
    void changeToLoginWindow(MouseEvent event) throws IOException {
        Parent LoginWindowParent = FXMLLoader.load(getClass().getResource("../LoginWindow/LoginWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(LoginWindowParent));
        stage.show();
    }


    private void clearField() {
        loginField.setText("");
        passwordField.setText("");
        nameField.setText("");
        lastNameField.setText("");
        genderComboBox.getSelectionModel().clearSelection();
        ageField.setText("");
    }

    private boolean isIncorrectLength(JFXTextField field, int minLength) {
        return field.getText().trim().isEmpty() || field.getText().trim().length() < minLength;
    }

    private boolean isIncorrectLength(JFXPasswordField field) {
        return field.getText().trim().isEmpty() || field.getText().trim().length() < 6;
    }

    private boolean isIncorrectLength(JFXComboBox field) {
        return field.getValue() == null || field.getValue().toString().trim().length() < 7;
    }

    private boolean isIncorrectAgeLength(JFXTextField field) {
        int age = Integer.parseInt(field.getText());
        return age < 18 || field.getText().trim().isEmpty() || field.getText().trim().length() < 2;
    }

    private boolean isExistLogin(JFXTextField field) throws RemoteException {
        String login = field.getText();
        String logindb = userService.getLogin(login);

        if (login.equals(logindb)) {
            return true;
        } else return false;
    }

    public void setRegistry(ActionEvent actionEvent) throws IOException {
        try {
            if (isIncorrectLength(loginField, 5)) {
                throw new IllegalArgumentException("Nie podano loginu lub jest za krótki, minimum to 5 znaków!");
            } else if (isExistLogin(loginField)) {
                throw new IllegalArgumentException("Taki Login już istnieje!");
            } else if (isIncorrectLength(passwordField)) {
                throw new IllegalArgumentException("Nie podano hasła lub jest za krótkie, minimum to 6 znaków!");
            } else if (isIncorrectLength(nameField, 3)) {
                throw new IllegalArgumentException("Nie podano imienia lub jest za krótkie!");
            } else if (isIncorrectLength(lastNameField, 3)) {
                throw new IllegalArgumentException("Nie podano nazwiska lub jest za krótkie!");
            } else if (isIncorrectLength(genderComboBox)) {
                throw new IllegalArgumentException("Nie wybrano płci!");
            } else if (isIncorrectAgeLength(ageField)) {
                throw new IllegalArgumentException("Nie podano wieku lub jesteś za młody/młoda!");
            } else {
                User user = new User();
                user.setLogin(loginField.getText());
                user.setPassword(passwordField.getText());
                user.setImie(nameField.getText());
                user.setNazwisko(lastNameField.getText());
                user.setPlec(genderComboBox.getValue().toString());
                user.setPesel(ageField.getText());
                user.setRola("consumer");
                userService.insertUser(user);
                clearField();


                Parent LoginWindowParent = FXMLLoader.load(getClass().getResource("../LoginWindow/LoginWindow.fxml"));
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(new Scene(LoginWindowParent));
                stage.show();
            }
        } catch (IllegalArgumentException | RemoteException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    public void setUser() {
        this.loadRegistryWindow = loadRegistryWindow;
        this.userService = loadRegistryWindow.getUserService();
    }
}
