package mainWindowUser;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import utilities.Nutrition;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class NutririonPlanUController implements Initializable {

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

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Nutrition nutrition = null;
        try {
            nutrition = userService.getNutritionByUserId(IDuser);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if(nutrition!=null){
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


