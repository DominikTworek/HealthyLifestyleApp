package mainWindowUser;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utilities.UserService;

import javax.swing.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TrainerUController implements Initializable {

    @FXML
    private JFXComboBox<String> trainer;

    private UserService userService = LoadLoginWindow.getUserService();

    private Long IDuser = LoginController.getIDuser();

    @FXML
    void trainerConfirm(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
           /* Integer i = 0;
            while (userService.getTrainerFieldFromUser("Login") != null) {
                DefaultComboBoxModel model = new DefaultComboBoxModel(new String[]{});
                if (model.getIndexOf(userService.getTrainerFieldFromUser("Login")) == -1) {
                    model.addElement(userService.getTrainerFieldFromUser("Login"));
                    trainer.getItems().set(i, userService.getTrainerFieldFromUser("Login"));
                    i = i +1;
                }
            }*/
           trainer.getItems().setAll(String.valueOf(userService.getAllTrainer()));

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
