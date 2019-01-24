package mainWindowUser;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import utilities.User;
import utilities.UserService;

import javax.swing.*;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class TrainerUController implements Initializable {


    @FXML
    private TableView<User> tableBody;

    @FXML
    private TableColumn<User, String> colTrenerBody;

    @FXML
    private TableColumn<User, String> colTrenerBody1;

    @FXML
    private TableColumn<User, String> colTrenerBody11;

    @FXML
    private TableView<User> tableMental;

    @FXML
    private TableColumn<User, String> colTrenerMental;

    @FXML
    private TableColumn<User, String> colTrenerMental1;

    @FXML
    private TableColumn<User, String> colTrenerMental11;

    @FXML
    private ImageView zdjecie;

    @FXML
    private JFXTextField imieNazwisko;

    @FXML
    private JFXTextArea informacje;

    @FXML
    private Text trenerWybrany;

    private UserService userService = LoadLoginWindow.getUserService();

    private Long IDuser = LoginController.getIDuser();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCollvalue();
        checkTable();

    }

    @FXML
    void trainerConfirm(ActionEvent event) {
    }

    void setCollvalue(){
        ScrollPane sp = new ScrollPane(tableBody);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        ScrollPane sp2 = new ScrollPane(tableMental);
        sp2.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        colTrenerBody.setCellValueFactory(new PropertyValueFactory<User, String>("Login"));
        colTrenerBody1.setCellValueFactory(new PropertyValueFactory<User, String>("Imie"));
        colTrenerBody11.setCellValueFactory(new PropertyValueFactory<User, String>("Nazwisko"));
        colTrenerMental.setCellValueFactory( new PropertyValueFactory<User, String>("Login"));
        colTrenerMental1.setCellValueFactory( new PropertyValueFactory<User, String>("Imie"));
        colTrenerMental11.setCellValueFactory( new PropertyValueFactory<User, String>("Nazwisko"));
        dataBaseGetTrainers();
    }

    void dataBaseGetTrainers(){
        try {
                tableBody.getItems().setAll(userService.getAllTrainerSpecjalist("Budowa"));
                tableMental.getItems().setAll(userService.getAllTrainerSpecjalist("Spalanie"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    void checkTable(){
        tableBody.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                tableMental.setDisable(true);
                imieNazwisko.setText(newValue.getImie()+" "+newValue.getNazwisko());
            }
        });
        tableMental.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                tableBody.setDisable(true);
                imieNazwisko.setText(newValue.getImie()+" "+newValue.getNazwisko());
            }
        });
    }


}
