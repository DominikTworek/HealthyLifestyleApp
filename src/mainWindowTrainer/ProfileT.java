package mainWindowTrainer;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ProfileT implements Initializable {

    @FXML
    private JFXTextField plec;

    @FXML
    private JFXTextField imie;

    @FXML
    private JFXTextField nazwisko;

    @FXML
    private JFXTextField wiek;

    @FXML
    private JFXTextField specjalizacja;

    @FXML
    private JFXTextField inne;

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInformation();
    }

    void setInformation(){
        try {
            imie.setText(userService.getFieldFromUser(IDuser, "Imie"));
            nazwisko.setText(userService.getFieldFromUser(IDuser, "Nazwisko"));
            wiek.setText(userService.getFieldFromUser(IDuser, "Pesel"));
            plec.setText(userService.getFieldFromUser(IDuser, "Plec"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
