package mainWindowTrainer;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utilities.User;
import utilities.UserProgress;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ProgressTController implements Initializable {

    @FXML
    private JFXTextField wagaField;

    @FXML
    private JFXTextField klatkaField;

    @FXML
    private JFXTextField taliaField;

    @FXML
    private JFXTextField pasField;

    @FXML
    private JFXTextField biodroField;

    @FXML
    private JFXTextField udoField;

    @FXML
    private JFXTextField ramieField;

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setFields();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    void setFields() throws RemoteException {
        User trainer = userService.getUserById(IDuser);
        UserProgress userProgress = userService.getUserProgressById(trainer.getIdUser_U());
        if(userProgress!=null) {
            wagaField.setText(String.valueOf(userProgress.getWaga()));
            klatkaField.setText(String.valueOf(userProgress.getKlatka()));
            taliaField.setText(String.valueOf(userProgress.getTalia()));
            pasField.setText(String.valueOf(userProgress.getPas()));
            biodroField.setText(String.valueOf(userProgress.getBiodro()));
            udoField.setText(String.valueOf(userProgress.getUdo()));
            ramieField.setText(String.valueOf(userProgress.getRamie()));
        }
    }
}

