package ResetPasswordWindow;

import LoginWindow.LoadLoginWindow;
import RegistryWindow.RegistryController;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import utilities.User;
import utilities.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordWindowController implements Initializable {

    @FXML
    JFXTextField rLoginField;

    @FXML
    JFXTextField rAgeField;

    @FXML
    JFXPasswordField rPasswordField1;

    @FXML
    JFXPasswordField rPasswordField2;

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colors();
    }

    private void colors(){
        rLoginField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    rLoginField.getStyleClass().add("correct");
                }
                else
                {
                    rLoginField.getStyleClass().remove("correct");
                }
            }
        });
        rAgeField.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    rAgeField.getStyleClass().add("correct");
                }
                else
                {
                    rAgeField.getStyleClass().remove("correct");
                }
            }
        });
        rPasswordField1.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    rPasswordField1.getStyleClass().add("correct");
                }
                else
                {
                    rPasswordField1.getStyleClass().remove("correct");
                }
            }
        });
        rPasswordField2.focusedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue)
            {
                if (newPropertyValue)
                {
                    rPasswordField2.getStyleClass().add("correct");
                }
                else
                {
                    rPasswordField2.getStyleClass().remove("correct");
                }
            }
        });
    }


    @FXML
    void changeToLoginWindow(Event event) throws Exception {
        //Parent LoginWindowParent = LoadLoginWindow.execWindow();
        Parent LoginWindowParent = FXMLLoader.load(getClass()
                .getResource("../LoginWindow/LoginWindow.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(LoginWindowParent));
        stage.show();
    }

    @FXML
    void resetUserPassword(Event event) throws Exception, IllegalAccessException {
        rLoginField.getStyleClass().remove("incorrectly");
        rAgeField.getStyleClass().remove("incorrectly");
        rPasswordField1.getStyleClass().remove("incorrectly");
        rPasswordField2.getStyleClass().remove("incorrectly");
        rLoginField.getStyleClass().remove("correct");
        rAgeField.getStyleClass().remove("correct");
        rPasswordField1.getStyleClass().remove("correct");
        rPasswordField2.getStyleClass().remove("correct");
        String login = rLoginField.getText();
        String pesel = rAgeField.getText();
        String password1 = rPasswordField1.getText();
        String password2 = rPasswordField2.getText();
        String getLogin = userService.getLogin(login);
        String getPesel = userService.getPesel(login, pesel);

        try {
        if(login.isEmpty() && pesel.isEmpty())
        {
            rLoginField.getStyleClass().add("incorrectly");
            rAgeField.getStyleClass().add("incorrectly");
        }
        else if(login.isEmpty()){
            rLoginField.getStyleClass().add("incorrectly");
        }
        else if(!login.equals(getLogin)){
            rLoginField.getStyleClass().add("incorrectly");
        }
        else if(pesel.isEmpty()){
            rAgeField.getStyleClass().add("incorrectly");
        }
        else if(!pesel.equals(getPesel)){
            rAgeField.getStyleClass().add("incorrectly");
        }
        else if(password1.isEmpty() && password2.isEmpty())
        {
            rPasswordField1.getStyleClass().add("incorrectly");
            rPasswordField2.getStyleClass().add("incorrectly");
        }
        else if(password1.isEmpty())
        {
            rPasswordField1.getStyleClass().add("incorrectly");
        }
        else if(password2.isEmpty())
        {
            rPasswordField2.getStyleClass().add("incorrectly");
        }
        else if(!password1.equals(password2))
        {
            rPasswordField1.getStyleClass().add("incorrectly");
            rPasswordField2.getStyleClass().add("incorrectly");
            throw new IllegalArgumentException("Hasła są takie same!");
        }
        else if (RegistryController.isIncorrectLength(rPasswordField1) ||
                RegistryController.isIncorrectLength(rPasswordField2))
        {
            rPasswordField1.getStyleClass().add("incorrectly");
            rPasswordField2.getStyleClass().add("incorrectly");
            throw new IllegalArgumentException("Hasło ma niepoprawną długość, minimum to 6 znaków!");
        }
        else {
            User user = userService.getUser(login,pesel);
            user.setPassword(password1);
            userService.updateUser(user);

            clearField();
            changeToLoginWindow(event);
        }
        }
        catch (IllegalArgumentException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    private void clearField(){
        rLoginField.setText("");
        rAgeField.setText("");
        rPasswordField1.setText("");
        rPasswordField2.setText("");
    }
}
