package RegistryWindow;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
        genderComboBox.getItems().addAll("mężczyzna", "kobieta");
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
        User user = new User();
        try {

            if (isIncorrectLength(loginField,5)){
                throw new IllegalArgumentException("Nie podano loginu lub jest za krótki, minimum to 5 znaków!");
            }

            if (isIncorrectLength(passwordField)){
                throw new IllegalArgumentException("Nie podano hasła lub jest za krótkie, minimum to 6 znaków!");
            }

            if (isIncorrectLength(nameField, 4)) {
                throw new IllegalArgumentException("Nie podano imienia lub jest za krótkie!");
            }

            if (isIncorrectLength(lastNameField, 3)) {
                throw new IllegalArgumentException("Nie podano nazwiska lub jest za krótkie!");
            }

            if (isIncorrectLength(genderComboBox)) {
                throw new IllegalArgumentException("Nie wybrano płci!");
            }

            if (isIncorrectAgeLength(ageField)) {
                throw new IllegalArgumentException("Nie podano wieku lub jesteś za młody/młoda!");
            }

            user.setLogin(loginField.getText().trim());
            user.setPassword(passwordField.getText().trim());
            user.setImie(nameField.getText().trim());
            user.setNazwisko(lastNameField.getText().trim());
            user.setPlec(genderComboBox.getValue().toString().trim());
            user.setPesel(ageField.getText().trim());

        } catch (IllegalArgumentException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }

        /*
        * Przesłanie usera do serwera, który utworzy go w bazie danych.
        * Możliwe że trzeba utworzyc klase Connect z metoda request która połączy sie za pomocą socketu i wyśle co trzeba.
        * */

        //Sprawdzenie czy dobrze zczytuje
        if (user.getLogin() != null || user.getPassword() != null || user.getImie() != null || user.getNazwisko()!= null || user.getPlec()!= null || user.getPesel()!= null){
            System.out.println(user.getLogin());
            System.out.println(user.getPassword());
            System.out.println(user.getImie());
            System.out.println(user.getNazwisko());
            System.out.println(user.getPlec());
            System.out.println(user.getPesel());
        }
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
}
