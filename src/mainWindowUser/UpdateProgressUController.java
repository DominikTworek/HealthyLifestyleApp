package mainWindowUser;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import utilities.UserProgress;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class UpdateProgressUController implements Initializable {

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

    @FXML
    void saveProgress() throws RemoteException {
        if(checkFields())
        {
            UserProgress userProgress = new UserProgress();
            userProgress.setWaga(Integer.parseInt(wagaField.getText()));
            userProgress.setKlatka(Integer.parseInt(klatkaField.getText()));
            userProgress.setTalia(Integer.parseInt(taliaField.getText()));
            userProgress.setPas(Integer.parseInt(pasField.getText()));
            userProgress.setBiodro(Integer.parseInt(biodroField.getText()));
            userProgress.setUdo(Integer.parseInt(udoField.getText()));
            userProgress.setRamie(Integer.parseInt(ramieField.getText()));
            userProgress.setId_user(IDuser);

            userService.insertUserProgress(userProgress);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setFields();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    boolean checkFields(){
        try {
            if (Integer.parseInt(wagaField.getText())<0 || Integer.parseInt(klatkaField.getText())<0 ||
                    Integer.parseInt(taliaField.getText())<0 || Integer.parseInt(taliaField.getText())<0 ||
            Integer.parseInt(pasField.getText())<0 || Integer.parseInt(biodroField.getText())<0 ||
                    Integer.parseInt(udoField.getText())<0 || Integer.parseInt(ramieField.getText())<0)
                throw new IllegalArgumentException();
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Uzupełnij pola poprawnie!");
            return false;
        }
        return true;
    }

    void setFields() throws RemoteException {
        UserProgress userProgress = userService.getUserProgressById(IDuser);
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
