package mainWindowTrainer;

import LoginWindow.LoadLoginWindow;
import com.jfoenix.controls.JFXButton;
        import com.jfoenix.controls.JFXComboBox;
        import com.jfoenix.controls.JFXTextField;
        import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import utilities.Training;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class TrainingPlanTController implements Initializable {

    @FXML
    private JFXComboBox<String> chestComboBox;

    @FXML
    private JFXComboBox<String> shouldersComboBox;

    @FXML
    private JFXComboBox<String> bicepsComboBox;

    @FXML
    private JFXComboBox<String> tricepsComboBox;

    @FXML
    private JFXComboBox<String> backComboBox;

    @FXML
    private JFXComboBox<String> legsComboBox;

    @FXML
    private JFXComboBox<String> absComboBox;

    @FXML
    private JFXTextField chest_pField;

    @FXML
    private JFXTextField chest_sField;

    @FXML
    private JFXTextField shoulders_sField;

    @FXML
    private JFXTextField shoulders_pField;

    @FXML
    private JFXTextField biceps_sField;

    @FXML
    private JFXTextField biceps_pField;

    @FXML
    private JFXTextField triceps_sField;

    @FXML
    private JFXTextField triceps_pField;

    @FXML
    private JFXTextField back_sField;

    @FXML
    private JFXTextField back_pField;

    @FXML
    private JFXTextField legs_sField;

    @FXML
    private JFXTextField legs_pField;

    @FXML
    private JFXTextField abs_sField;

    @FXML
    private JFXTextField abs_pField;

    @FXML
    private JFXButton confirmButton;

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboBoxes();
    }

    @FXML
    void confirmExercises() throws RemoteException {
        if(isIncorrect()) {
            Training training = new Training.Builder()
                    .Chest(chestComboBox.getValue())
                    .Chest_s(Integer.parseInt(chest_sField.getText()))
                    .Chest_p(Integer.parseInt(chest_pField.getText()))
                    .Shoulders(shouldersComboBox.getValue())
                    .Shoulders_s(Integer.parseInt(shoulders_sField.getText()))
                    .Shoulders_p(Integer.parseInt(shoulders_pField.getText()))
                    .Biceps(bicepsComboBox.getValue())
                    .Biceps_s(Integer.parseInt(biceps_sField.getText()))
                    .Biceps_p(Integer.parseInt(biceps_pField.getText()))
                    .Triceps(tricepsComboBox.getValue())
                    .Triceps_s(Integer.parseInt(triceps_sField.getText()))
                    .Triceps_p(Integer.parseInt(triceps_pField.getText()))
                    .Back(backComboBox.getValue())
                    .Back_s(Integer.parseInt(back_sField.getText()))
                    .Back_p(Integer.parseInt(back_pField.getText()))
                    .Abs(absComboBox.getValue())
                    .Abs_s(Integer.parseInt(abs_sField.getText()))
                    .Abs_p(Integer.parseInt(abs_pField.getText()))
                    .Legs(legsComboBox.getValue())
                    .Legs_s(Integer.parseInt(legs_sField.getText()))
                    .Legs_p(Integer.parseInt(legs_pField.getText()))
                    .ID_user(6)
                    .build();

            userService.insertTraining(training);
            clearField();
        }

    }

    private void clearField() {
        chestComboBox.getSelectionModel().clearSelection();
        chest_sField.setText("");
        chest_pField.setText("");

        shouldersComboBox.getSelectionModel().clearSelection();
        shoulders_sField.setText("");
        shoulders_pField.setText("");

        bicepsComboBox.getSelectionModel().clearSelection();
        biceps_pField.setText("");
        biceps_sField.setText("");

        backComboBox.getSelectionModel().clearSelection();
        back_pField.setText("");
        back_sField.setText("");

        legsComboBox.getSelectionModel().clearSelection();
        legs_pField.setText("");
        legs_sField.setText("");

        absComboBox.getSelectionModel().clearSelection();
        abs_pField.setText("");
        abs_sField.setText("");
    }

    void setComboBoxes(){
        chestComboBox.getItems().addAll("Wyciskanie na lawce plaskiej");
        chestComboBox.getItems().addAll("Wyciskanie na lawce skosnej");
        chestComboBox.getItems().addAll("Wyciskanie na lawce plaskiej hantlami");
        chestComboBox.getItems().addAll("Wyciskanie na lawce skosnej hantlami");
        chestComboBox.getItems().addAll("Krzyzowanie linek wyciagu gornego");
        chestComboBox.getItems().addAll("Rozpietki w lezeniu na lawce plaskiej");

        shouldersComboBox.getItems().addAll("Wyciskanie sztangi nad glowę");
        shouldersComboBox.getItems().addAll("Wyciskanie hantli nad glowe");
        shouldersComboBox.getItems().addAll("Wyciskanie hantli nad glowe siedzac");
        shouldersComboBox.getItems().addAll("Unoszenie hantli bokiem stojac");
        shouldersComboBox.getItems().addAll("Unoszenie ramion w bok na wyciagu");
        shouldersComboBox.getItems().addAll("Facepull");
        shouldersComboBox.getItems().addAll("Unoszenie bokiem w opadzie tulowia");

        absComboBox.getItems().addAll("Plank");
        absComboBox.getItems().addAll("Uginanie nog w lezeniu na lawce");
        absComboBox.getItems().addAll("Unoszenie nog w zwisie na drazku");
        absComboBox.getItems().addAll("Allahy");
        absComboBox.getItems().addAll("Hollow body");

        bicepsComboBox.getItems().addAll("Uginanie hantli z supinacja");
        bicepsComboBox.getItems().addAll("Uginanie hantli mlotkowe");
        bicepsComboBox.getItems().addAll("Uginanie hantli na modlitewniku");
        bicepsComboBox.getItems().addAll("Uginanie hantli sztangi prostej stojac");
        bicepsComboBox.getItems().addAll("Uginanie hantli sztangi prostej na modlitewniku");

        tricepsComboBox.getItems().addAll("Uginanie hantli francuskie stojac");
        tricepsComboBox.getItems().addAll("Uginanie hantli francuskie siedzac");
        tricepsComboBox.getItems().addAll("Uginanie hantli francuskie lezac");
        tricepsComboBox.getItems().addAll("Dipy na poreczach");
        tricepsComboBox.getItems().addAll("Wyciskanie sztangi wasko");

        backComboBox.getItems().addAll("Podciaganie na drazku");
        backComboBox.getItems().addAll("Sciaganie linki wyciagu gornego do klatki");
        backComboBox.getItems().addAll("Wioslowanie sztanga w opadzie tulowia");
        backComboBox.getItems().addAll("Wioslowanie hantlem w oparciu o lawke");
        backComboBox.getItems().addAll("Pendlay Row");
        backComboBox.getItems().addAll("Wioslowanie koncem sztangi");
        backComboBox.getItems().addAll("Martwy ciag klasyczny");
        backComboBox.getItems().addAll("Rumunski martwy ciag");

        legsComboBox.getItems().addAll("Przysiad klasyczny");
        legsComboBox.getItems().addAll("Przysiad ze sztanga z przodu");
        legsComboBox.getItems().addAll("Przysiad bulgarski");
        legsComboBox.getItems().addAll("Wypychanie nog na suwnicy");
        legsComboBox.getItems().addAll("Prostowanie nog na maszynie");
        legsComboBox.getItems().addAll("Dzien dobry");
        legsComboBox.getItems().addAll("Uginanie nog lezac brzuchem na lawce");
    }

    boolean isIncorrect(){
        try {
            if ((chestComboBox.getValue() == null) || (shouldersComboBox.getValue() == null) ||
                    (bicepsComboBox.getValue() == null) || (tricepsComboBox.getValue() == null) ||
                    (backComboBox.getValue() == null) || (legsComboBox.getValue() == null) ||
                    (absComboBox.getValue() == null))
                throw new IllegalArgumentException();
            else if((Integer.parseInt(chest_sField.getText()) < 1) || (Integer.parseInt(chest_pField.getText()) < 1) ||
                    (Integer.parseInt(shoulders_sField.getText())) < 1 || (Integer.parseInt(shoulders_pField.getText())<1) ||
                    Integer.parseInt(biceps_sField.getText()) < 1 || Integer.parseInt(biceps_pField.getText()) < 1 ||
                    Integer.parseInt(biceps_sField.getText()) < 1 || Integer.parseInt(biceps_pField.getText()) < 1 ||
                    Integer.parseInt(triceps_sField.getText()) < 1 || Integer.parseInt(triceps_pField.getText()) < 1 ||
                    Integer.parseInt(back_sField.getText()) < 1 || Integer.parseInt(back_pField.getText()) < 1 ||
                    Integer.parseInt(legs_sField.getText()) < 1 || Integer.parseInt(legs_pField.getText()) < 1 ||
                    Integer.parseInt(abs_sField.getText()) < 1 || Integer.parseInt(abs_pField.getText()) < 1)
                throw new IllegalArgumentException();
        }catch (IllegalArgumentException e){
            new Alert(Alert.AlertType.ERROR, "Uzupełnij wszystkie pola poprawnie!").showAndWait();
            return false;
        }
        return true;
    }
}
