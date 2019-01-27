package mainWindowUser;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utilities.UserProfile;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
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

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCombobox();
    }

    void setCombobox(){
        UserProfile userProfile = null;
        try {
            userProfile = userService.getUserProfileById(IDuser);
            if(userProfile!=null) {
                heightField.setText(userProfile.getHeight());
                weightField.setText(userProfile.getWeight());
                neatComboBox.setValue(userProfile.getNeat());
                goalComboBox.setValue(userProfile.getGoal());
                otherTextArea.setText(userProfile.getOther());
            }
        } catch (RemoteException ignored) {}

        neatComboBox.getItems().addAll("Niska", "Srednia", "Wysoka");
        goalComboBox.getItems().addAll("Utrata tkanki tluszczowej", "Budowa miesni");
    }

    @FXML
    void closeApp() {
        ((Stage)heightField.getScene().getWindow()).close();
    }

    void clearField(){
        heightField.setText("");
        weightField.setText("");
        neatComboBox.getSelectionModel().clearSelection();
        goalComboBox.getSelectionModel().clearSelection();
        otherTextArea.setText("");
    }

    private boolean isIncorrect(JFXTextField field) {
        int value = Integer.parseInt(field.getText());
        return value < 0 || field.getText().trim().isEmpty() || field.getText().trim().length() < 2;
    }

    private boolean isIncorrect(JFXComboBox field) {
        return field.getValue() == null || field.getValue().toString().trim().length() < 4;
    }

    public void setProfile() throws RemoteException {
        try{
            if(isIncorrect(heightField)){
                throw new IllegalArgumentException("Niepoprawny wzrost!");
            } else if (isIncorrect(weightField)){
                throw new IllegalArgumentException("Niepoprawna waga!");
            } else if (isIncorrect(neatComboBox)){
                throw new IllegalArgumentException("Okresl swoja aktywnosc!");
            } else if (isIncorrect(goalComboBox)){
                throw new IllegalArgumentException("Okresl swoj cel!");
            } else {
                UserProfile userProfile = new UserProfile();
                userProfile.setUSER_ID(IDuser);
                userProfile.setHeight(heightField.getText());
                userProfile.setWeight(weightField.getText());
                userProfile.setNeat(neatComboBox.getValue());
                userProfile.setGoal(goalComboBox.getValue());
                userProfile.setOther(otherTextArea.getText());
                userService.insertUserProfile(userProfile);
                clearField();
                ((Stage)heightField.getScene().getWindow()).close();
            }
        }catch (IllegalArgumentException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).showAndWait();
        }
    }
}
