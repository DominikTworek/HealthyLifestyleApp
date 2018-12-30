package LoginWindow;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import server.UserServiceImplements;
import utilities.UserService;

import java.io.IOException;
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

    private UserService userService;

    private LoadLoginWindow loadLoginWindow;

    private UserServiceImplements userServiceImplements;

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
    void login(ActionEvent event) throws RemoteException {
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


    }

    void colors(){
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
    void loginAction(KeyEvent event) {

    }
    
    @FXML
    void changeToRegistryWindow(MouseEvent event) throws IOException {
        Parent RegistryWindowParent = FXMLLoader.load(getClass().getResource("../RegistryWindow/RegistryWindow.fxml"));
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Rejestracja");
        stage.setScene(new Scene(RegistryWindowParent));
        stage.show();
    }

    public void setUser(LoadLoginWindow loadLoginWindow){
        this.loadLoginWindow = loadLoginWindow;
        this.userService = loadLoginWindow.getUserService();

    }


}
