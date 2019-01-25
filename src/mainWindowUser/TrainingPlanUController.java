package mainWindowUser;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utilities.Training;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;



public class TrainingPlanUController implements Initializable {

    public static class Exercise{
        private SimpleStringProperty partOfBody;
        private SimpleStringProperty exercise;
        private SimpleIntegerProperty numberOfSeries;
        private SimpleIntegerProperty numberOfRepetitions;

        public Exercise(String partOfBody, String exercise, Integer numberOfSeries, Integer numberOfRepetitions){
            this.partOfBody = new SimpleStringProperty(partOfBody);
            this.exercise = new SimpleStringProperty(exercise);
            this.numberOfSeries = new SimpleIntegerProperty(numberOfSeries);
            this.numberOfRepetitions = new SimpleIntegerProperty(numberOfRepetitions);
        }

        public String getPartOfBody() {
            return partOfBody.get();
        }

        public SimpleStringProperty partOfBodyProperty() {
            return partOfBody;
        }

        public String getExercise() {
            return exercise.get();
        }

        public SimpleStringProperty exerciseProperty() {
            return exercise;
        }

        public int getNumberOfSeries() {
            return numberOfSeries.get();
        }

        public SimpleIntegerProperty numberOfSeriesProperty() {
            return numberOfSeries;
        }

        public int getNumberOfRepetitions() {
            return numberOfRepetitions.get();
        }

        public SimpleIntegerProperty numberOfRepetitionsProperty() {
            return numberOfRepetitions;
        }
    }

        @FXML
        private TableView<Exercise> trainingTableView;

        @FXML
        private TableColumn<Exercise,String> muscleCol;

        @FXML
        private TableColumn<Exercise,String> exerciseCol;

        @FXML
        private TableColumn<Exercise,Integer> sCol;

        @FXML
        private TableColumn<Exercise,Integer> pCol;

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Training training = null;
        try {
            training = userService.getTrainingByUserId(IDuser);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ObservableList<Exercise> data = FXCollections.observableArrayList(
                new Exercise("Klatka piersiowa", training.getChest(), training.getChest_s(), training.getChest_p()),
                new Exercise("Barki", training.getShoulders(), training.getShoulders_s(), training.getShoulders_p()),
                new Exercise("Biceps", training.getBiceps(), training.getBiceps_s(), training.getShoulders_p()),
                new Exercise("Triceps", training.getTriceps(), training.getTriceps_s(), training.getTriceps_p()),
                new Exercise("Plecy", training.getBack(), training.getBack_s(), training.getBack_p()),
                new Exercise("Brzuch", training.getAbs(), training.getAbs_s(), training.getAbs_p()),
                new Exercise("Nogi", training.getLegs(), training.getLegs_s(), training.getLegs_p())
        );


        muscleCol.setCellValueFactory(new PropertyValueFactory<>("partOfBody"));
        exerciseCol.setCellValueFactory(new PropertyValueFactory<>("exercise"));
        sCol.setCellValueFactory(new PropertyValueFactory<>("numberOfSeries"));
        pCol.setCellValueFactory(new PropertyValueFactory<>("numberOfRepetitions"));

        muscleCol.setStyle( "-fx-alignment: CENTER;");
        exerciseCol.setStyle( "-fx-alignment: CENTER;");
        sCol.setStyle( "-fx-alignment: CENTER;");
        pCol.setStyle( "-fx-alignment: CENTER;");

        trainingTableView.setItems(data);

    }
}


