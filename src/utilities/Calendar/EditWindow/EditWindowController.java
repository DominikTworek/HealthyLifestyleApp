package utilities.Calendar.EditWindow;

import LoginWindow.LoginController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import server.CalendarServices;
import utilities.Calendar.CalendarEvent;

import java.time.LocalDate;


public class EditWindowController {
    @FXML
    GridPane mainPane;
    @FXML
    Label label;
    @FXML
    TextField title;
    @FXML
    TextArea description;
    @FXML
    Button delete;
    @FXML
    Button close;
    @FXML
    Button accept;

    private ComboBox<CalendarEvent> eventList;
    private ChoiceBox<Integer> hoursList;
    private LocalDate date;

    private void onChangeEvent(ActionEvent event){
        delete.setDisable(false);
        title.setText(eventList.getSelectionModel().getSelectedItem().getTitle());
        description.setText(eventList.getSelectionModel().getSelectedItem().getDescription());
        hoursList.getSelectionModel().select(eventList.getSelectionModel().getSelectedItem().getHour()
                -CalendarServices.getHours().get(0));
        if(eventList.getSelectionModel().getSelectedItem().getIdEvent() == -1L){
            delete.setDisable(true);
        }
    }

    private void onClose(ActionEvent event){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    private void onDelete(ActionEvent event){
        CalendarServices.deleteEvent(eventList.getSelectionModel().getSelectedItem().getIdEvent());
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
    }

    private void onAccept(ActionEvent event){
        if (eventList.getSelectionModel().getSelectedItem().getIdEvent() == -1){
            CalendarServices.insertEvent(LoginController.getIDuser(),
                    date,
                    hoursList.getSelectionModel().getSelectedItem(),
                    CalendarServices.abbreviateToSize(title.getText(), CalendarServices.MAX_TITLE_LEN),
                    CalendarServices.abbreviateToSize(description.getText(), CalendarServices.MAX_DESC_LEN));
        } else {
            CalendarServices.updateEvent(eventList.getSelectionModel().getSelectedItem().getIdEvent(),
                    CalendarServices.abbreviateToSize(title.getText(), CalendarServices.MAX_TITLE_LEN),
                    CalendarServices.abbreviateToSize(description.getText(), CalendarServices.MAX_DESC_LEN),
                    hoursList.getSelectionModel().getSelectedItem());
        }
        Stage stage = (Stage) accept.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){
        eventList = new ComboBox<>();
        hoursList = new ChoiceBox<>();
        hoursList.getItems().setAll(CalendarServices.getHours());
        hoursList.getSelectionModel().select(0);
        eventList.setOnAction(this::onChangeEvent);
        accept.setOnAction(this::onAccept);
        delete.setOnAction(this::onDelete);
        close.setOnAction(this::onClose);
        mainPane.add(eventList,0,1);
        mainPane.add(hoursList,0,4);
        delete.setDisable(true);
    }

    public void initData(LocalDate data, ObservableList<CalendarEvent> events){
        label.setText(data.toString());
        date = data;
        eventList.setItems(events);
        eventList.getItems().add(CalendarServices.getEmptyEvent());
        eventList.getSelectionModel().select(0);
        //Ustaw zaznaczenie na poczactek
        onChangeEvent(null);
    }
}
