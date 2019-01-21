package LoginWindow;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import RegistryWindow.LoadRegistryWindow;



import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    ImageView exitImage;

    @FXML
    private JFXTextField loginField;

    @FXML
    private JFXPasswordField passwordField;

    private static Long IDuser;

    private UserService userService = LoadLoginWindow.getUserService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colors();
    }


    @FXML
    void closeApp() {
        Stage stage = (Stage) exitImage.getScene().getWindow();
        stage.close();
    }

    @FXML
    void login(Event event) throws RemoteException {
        loginField.getStyleClass().remove("incorrectly");
        passwordField.getStyleClass().remove("incorrectly");
        loginField.getStyleClass().remove("correct");
        passwordField.getStyleClass().remove("correct");
        String login = loginField.getText();
        String password = passwordField.getText();
        String getLogin = userService.getLogin(login);
        String getPassword = userService.getPassword(getLogin, password);
        if(login.isEmpty() && password.isEmpty())
        {
            loginField.getStyleClass().add("incorrectly");
            passwordField.getStyleClass().add("incorrectly");
        }
        else if(login.isEmpty()){
            loginField.getStyleClass().add("incorrectly");
        }
        else if(!login.equals(getLogin)){
            loginField.getStyleClass().add("incorrectly");
        }
        else if(password.isEmpty()){
            passwordField.getStyleClass().add("incorrectly");
        }
        else if(!password.equals(getPassword)){
            passwordField.getStyleClass().add("incorrectly");
        }
        else if(login.equals(getLogin) && password.equals(getPassword)){
            String rola = userService.getRola(getLogin, getPassword);
            IDuser = userService.getID(getLogin, getPassword);
            if(rola.equals("admin")) {
                try {
                    changeToAdminWindow(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(rola.equals("customer")) {
                try {
                    changeToUserWindow(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if(rola.equals("trainer")) {
                try {
                    changeToTrainerWindow(event);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void colors(){
        loginField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    loginField.getStyleClass().add("correct");
                }
                else
                {
                    loginField.getStyleClass().remove("correct");
                }
            }
        });
        passwordField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    passwordField.getStyleClass().add("correct");
                }
                else
                {
                    passwordField.getStyleClass().remove("correct");
                }
            }
        });
    }

    @FXML
    void changeToRegistryWindow(Event event) throws Exception {
        Parent RegistryWindowParent = FXMLLoader.load(getClass()
                .getResource("../RegistryWindow/RegistryWindow.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Rejestracja");
        stage.setScene(new Scene(RegistryWindowParent));
        stage.show();
    }


    @FXML
    void changeToResetPasswordWindow(Event event) throws Exception {
        Parent ResetPasswordWindowParent = FXMLLoader.load(getClass()
                .getResource("../ResetPasswordWindow/ResetPasswordWindow.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Resetuj hasło");
        stage.setScene(new Scene(ResetPasswordWindowParent));
        stage.show();
    }

    void changeToAdminWindow(Event event) throws Exception {
        Parent AdminWindowWindowParent = FXMLLoader.load(getClass()
                .getResource("../adminWindow/adminWindow.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Okno Administratora");
        stage.setScene(new Scene(AdminWindowWindowParent));
        stage.show();
    }


    void changeToTrainerWindow(Event event) throws Exception {
        Parent AdminWindowWindowParent = FXMLLoader.load(getClass()
                .getResource("../trainerWindow/TrainerWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Okno Trenera");
        stage.setScene(new Scene(AdminWindowWindowParent));
        stage.show();
    }

    void changeToUserWindow(Event event) throws Exception {
        Parent AdminWindowWindowParent = FXMLLoader.load(getClass()
                .getResource("../userWindow/userWindow.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Okno uzytkownika");
        stage.setScene(new Scene(AdminWindowWindowParent));
        stage.show();
    }

    public static Long getIDuser(){
        return IDuser;
    }
}
