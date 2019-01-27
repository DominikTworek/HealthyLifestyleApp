package mainWindowTrainer;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utilities.Nutrition;
import utilities.User;
import utilities.UserProfile;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class OptionsTController implements Initializable {

    @FXML
    private JFXTextField caloriesField;

    @FXML
    private JFXTextField proteidField;

    @FXML
    private JFXTextField carbohydratesField;

    @FXML
    private JFXTextField fatField;

    @FXML
    private JFXTextField sugarsField;

    @FXML
    private JFXTextField saturedField;

    @FXML
    private JFXTextField unsaturedField;

    @FXML
    private JFXTextArea otherTextArea;

    @FXML
    private JFXTextField heightField;

    @FXML
    private JFXTextField weightField;

    @FXML
    private JFXTextField neatField;

    @FXML
    private JFXTextField goalField;

    UserService userService = LoadLoginWindow.getUserService();

    private Long trainerID = LoginController.getIDuser();

    Long userID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userID = userService.getUserById(trainerID).getIdUser_U();
            if(userID>0) {
                setProfileFields();
                setNutritionFields();
            }
        } catch (RemoteException ignored) {}
    }

    void setProfileFields() throws RemoteException {
        //User user = userService.getUserById(userID);
        UserProfile userProfile = userService.getUserProfileById(userID);
        if(userProfile!=null) {
            heightField.setText(userProfile.getHeight());
            weightField.setText(userProfile.getWeight());
            neatField.setText(userProfile.getNeat());
            goalField.setText(userProfile.getGoal());
            otherTextArea.setText(userProfile.getOther());
        }
    }

    void setNutritionFields() throws RemoteException {
        Nutrition nutrition = userService.getNutritionByUserId(userID);
        if (nutrition!=null) {
            caloriesField.setText(String.valueOf(nutrition.getCalories()));
            proteidField.setText(String.valueOf(nutrition.getProtein()));
            carbohydratesField.setText(String.valueOf(nutrition.getCarbs()));
            fatField.setText(String.valueOf(nutrition.getFat()));
            sugarsField.setText(String.valueOf(nutrition.getSugars()));
            saturedField.setText(String.valueOf(nutrition.getSaturedfat()));
            unsaturedField.setText(String.valueOf(nutrition.getUnsaturedfat()));
        }
    }
}
