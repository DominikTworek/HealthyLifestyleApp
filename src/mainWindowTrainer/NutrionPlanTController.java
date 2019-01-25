package mainWindowTrainer;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import utilities.Nutrition;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.ResourceBundle;

public class NutrionPlanTController implements Initializable {

        @FXML
        private JFXTextField weightField;

        @FXML
        private JFXComboBox<Integer> multipierComboBox;

        @FXML
        private JFXComboBox<Integer> proteinComboBox;

        @FXML
        private JFXComboBox<Integer> carbohydratesComboBox;

        @FXML
        private JFXComboBox<Integer> fatComboBox;

        @FXML
        private JFXComboBox<Integer> sugarsComboBox;

        @FXML
        private JFXComboBox<Integer> saturedComboBox;

        @FXML
        private JFXComboBox<Integer> unsaturedComboBox;

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
        private JFXButton calculateButton;

        UserService userService = LoadLoginWindow.getUserService();

        Nutrient calculateCalories() {
                Integer caloriesValue = Integer.parseInt(weightField.getText())*(multipierComboBox.getValue());
                Nutrient calories = new ComplexNutrient("Kalorie", caloriesValue);

                Nutrient protein = new BasicNutrient("Bialko",
                        (calories.getNumber()*proteinComboBox.getValue()*0.01)/4);

                Nutrient carbohydrates = new ComplexNutrient("Weglowodany",
                        (calories.getNumber()*carbohydratesComboBox.getValue())*0.01/4);
                Nutrient sugars = new BasicNutrient("Cukry",
                        carbohydrates.getNumber()*sugarsComboBox.getValue()*0.01);

                carbohydrates.add(sugars);

                Nutrient fat = new ComplexNutrient("Tluszcz",
                        (calories.getNumber()*fatComboBox.getValue()*0.01)/9);
                Nutrient satured = new BasicNutrient("Tluszcze nasycone",
                        fat.getNumber()*saturedComboBox.getValue()*0.01);
                Nutrient unsatured = new BasicNutrient("Tluszcze nienasycone",
                        fat.getNumber()*unsaturedComboBox.getValue()*0.01);

                fat.add(satured);
                fat.add(unsatured);

                calories.add(protein);
                calories.add(carbohydrates);
                calories.add(fat);

                return calories;
        }

        @FXML
        void setTextField(){
            if(checkFields()) {
                Nutrient kcal = calculateCalories();
                caloriesField.setText(String.valueOf(Math.round(kcal.getNumber())));

                for (int i = 0; kcal.getChild(i) != null; i++) {
                    Nutrient tmp = kcal.getChild(i);
                    if (tmp.getName().equals("Bialko"))
                        proteidField.setText(String.valueOf(Math.round(tmp.getNumber())));
                    if (tmp.getName().equals("Weglowodany"))
                        carbohydratesField.setText(String.valueOf(Math.round(tmp.getNumber())));
                    if (tmp.getName().equals("Tluszcz"))
                        fatField.setText(String.valueOf(Math.round(tmp.getNumber())));

                    for (int j = 0; tmp.getChild(j) != null; j++) {
                        Nutrient tmpChild = tmp.getChild(j);
                        if (tmpChild.getName().equals("Cukry"))
                            sugarsField.setText(String.valueOf(Math.round(tmpChild.getNumber())));
                        if (tmpChild.getName().equals("Tluszcze nasycone"))
                            saturedField.setText(String.valueOf(Math.round(tmpChild.getNumber())));
                        if (tmpChild.getName().equals("Tluszcze nienasycone"))
                            unsaturedField.setText(String.valueOf(Math.round(tmpChild.getNumber())));
                    }
                }
            }
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboBox();
    }

    void setComboBox(){
            multipierComboBox.getItems().addAll(31,32,33,34,35);
            proteinComboBox.getItems().addAll(10,15,20,25,30,35,40,45,50,55,60,65,70,75,80);
            carbohydratesComboBox.getItems().addAll(10,15,20,25,30,35,40,45,50,55,60,65,70,75,80);
            fatComboBox.getItems().addAll(10,15,20,25,30,35,40,45,50,55,60,65,70,75,80);
            sugarsComboBox.getItems().addAll(10,15,20,25,30,35,40,45,50,55,60,65,70,75,80);
            saturedComboBox.getItems().addAll(10,15,20,25,30,35,40,45,50,55,60,65,70,75,80);
            unsaturedComboBox.getItems().addAll(10,15,20,25,30,35,40,45,50,55,60,65,70,75,80);
    }

    boolean checkFields(){
            try {
                if(weightField.getText().trim().isEmpty() || multipierComboBox.getValue() == null || proteinComboBox.getValue() == null ||
                        carbohydratesComboBox.getValue()==null || fatComboBox.getValue()==null ||
                        sugarsComboBox.getValue()==null|| saturedComboBox.getValue()==null||
                        unsaturedComboBox.getValue()==null)
                    throw new IllegalArgumentException("Uzupełnij dane!");
                else if (proteinComboBox.getValue() + carbohydratesComboBox.getValue() + fatComboBox.getValue() != 100)
                    throw new IllegalArgumentException("Procent makroskładnikow musi wynosić 100%!");
                else if(saturedComboBox.getValue()+unsaturedComboBox.getValue() > 100)
                    throw new IllegalArgumentException("Składowe tłuszczu nie mogą przekraczać 100%!");
            }catch (IllegalArgumentException e){
                new Alert (Alert.AlertType.ERROR,e.getMessage()).showAndWait();
                return false;
            }

            return true;
    }

    @FXML
    void sendToClient() throws RemoteException {
        Nutrition nutrition = new Nutrition();
        nutrition.setCalories(Integer.parseInt(caloriesField.getText()));
        nutrition.setProtein(Integer.parseInt(proteidField.getText()));
        nutrition.setCarbs(Integer.parseInt(carbohydratesField.getText()));
        nutrition.setFat(Integer.parseInt(fatField.getText()));
        nutrition.setSugars(Integer.parseInt(sugarsField.getText()));
        nutrition.setSaturedfat(Integer.parseInt(saturedField.getText()));
        nutrition.setUnsaturedfat(Integer.parseInt(unsaturedField.getText()));
        nutrition.setID_user(6);

        userService.insertNutrition(nutrition);
    }
}
