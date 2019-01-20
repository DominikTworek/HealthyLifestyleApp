package mainWindowAdmin;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import adminWindow.AdminController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.ScaleTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import utilities.User;
import utilities.UserService;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class UserAController implements Initializable {

    @FXML
    private JFXTextField mainText;

    @FXML
    private AnchorPane mainWindow;

    @FXML
    private JFXTextField id;


    @FXML
    private JFXTextField haslo;

    @FXML
    private JFXComboBox<String> plec;

    @FXML
    private JFXTextField login;

    @FXML
    private JFXComboBox<String> rola;

    @FXML
    private JFXTextField imie;

    @FXML
    private JFXTextField nazwisko;

    @FXML
    private JFXTextField pesel;

    @FXML
    private JFXButton addButton;

    @FXML
    private JFXButton changeButton;

    @FXML
    private ImageView exitEdit;

    @FXML
    public TableView<User> tableView;

    @FXML
    private TableColumn<User, Long> colId;

    @FXML
    private TableColumn<User, String> colLogin;

    @FXML
    private TableColumn<User, String> colPassword;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, String> colLastName;

    @FXML
    private TableColumn<User, String> colGender;

    @FXML
    private TableColumn<User, String> colPesel;

    @FXML
    private TableColumn<User, String> colRole;

    private UserService userService = LoadLoginWindow.getUserService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDisable(true);
        setVisible(false);
        mainText.setVisible(true);
        setCollvalue();
        setComboBox();
        setEditValue();
        if (AdminController.userWindow)
            mainText.setText("Zarządzanie Użytkownikami");
        if (AdminController.trainerWindow)
            mainText.setText("Zarządzanie Trenerami");
    }

    public void setComboBox() {
        plec.getItems().addAll("mezczyzna", "kobieta");
        rola.getItems().addAll("costumer", "trainer", "admin");
    }


    void setCollvalue() {
        colId.setCellValueFactory(new PropertyValueFactory<User, Long>("IdUser"));
        colLogin.setCellValueFactory(new PropertyValueFactory<User, String>("Login"));
        colPassword.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
        colName.setCellValueFactory(new PropertyValueFactory<User, String>("Imie"));
        colLastName.setCellValueFactory(new PropertyValueFactory<User, String>("Nazwisko"));
        colGender.setCellValueFactory(new PropertyValueFactory<User, String>("Plec"));
        colPesel.setCellValueFactory(new PropertyValueFactory<User, String>("Pesel"));
        colRole.setCellValueFactory(new PropertyValueFactory<User, String>("Rola"));
        dataBaseGetUser();
    }

    @FXML
    void refreshAction(ActionEvent event) {
        dataBaseGetUser();
    }

    void dataBaseGetUser()
    {
        try {
            if (AdminController.userWindow)
                tableView.getItems().setAll(userService.getAllUser());
            if (AdminController.trainerWindow)
                tableView.getItems().setAll(userService.getAllTrainer());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    void setEditValue() {
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                id.setText(String.valueOf(newValue.getIdUser()));
                login.setText(newValue.getLogin());
                haslo.setText(newValue.getPassword());
                imie.setText(newValue.getImie());
                nazwisko.setText(newValue.getPlec());
                plec.setValue(newValue.getPlec());
                //plec
                if (newValue.getPlec().equals("kobieta"))
                    plec.getSelectionModel().select(1);
                else plec.getSelectionModel().select(0);
                //
                pesel.setText(newValue.getPesel());
                //Rola
                if (newValue.getRola().equals("customer"))
                    rola.getSelectionModel().select(0);
                else if (newValue.getRola().equals("trainer"))
                    rola.getSelectionModel().select(1);
                else rola.getSelectionModel().select(2);
            }
        });
    }

    Boolean checkData() {
        if (login.getText().isEmpty() || haslo.getText().isEmpty() || imie.getText().isEmpty() || nazwisko.getText().isEmpty() || plec.getValue().isEmpty() || pesel.getText().isEmpty() || rola.getValue().isEmpty())
            return false;
        else
            return true;
    }

    @FXML
    void editConfirm(ActionEvent event) {
        int index = tableView.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            mainText.getStyleClass().add("error");
            mainText.setText("Wybierz Najpierw Użytkownika");
            return;
        }
        if (checkData()) {
            try {
                User user = new User();
                user.setIdUser(Long.valueOf(id.getText()));
                user.setLogin(login.getText());
                user.setPassword(haslo.getText());
                user.setImie(imie.getText());
                user.setNazwisko(nazwisko.getText());
                user.setPlec(plec.getValue().toString());
                user.setPesel(pesel.getText());
                user.setRola(rola.getValue().toString());
                userService.updateUser(user);
                tableView.getItems().set(index, user);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
            mainText.setVisible(true);
            mainText.getStyleClass().add("error");
            mainText.setText("Sprawdź dane, wpisałeś coś źle");
        }
    }

    void setDisable(Boolean disable) {
        login.setDisable(disable);
        haslo.setDisable(disable);
        imie.setDisable(disable);
        nazwisko.setDisable(disable);
        plec.setDisable(disable);
        pesel.setDisable(disable);
        rola.setDisable(disable);
        changeButton.setDisable(disable);
        addButton.setDisable(disable);
        exitEdit.setDisable(disable);
    }

    void setVisible(Boolean visible) {
        login.setVisible(visible);
        haslo.setVisible(visible);
        imie.setVisible(visible);
        nazwisko.setVisible(visible);
        plec.setVisible(visible);
        pesel.setVisible(visible);
        rola.setVisible(visible);
        changeButton.setVisible(visible);
        addButton.setVisible(visible);
        exitEdit.setVisible(visible);

    }

    @FXML
    void addAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainWindowAdmin/AddUser.fxml"));
        AnchorPane change = loader.load();
        mainWindow.getChildren().setAll(change);
    }


    @FXML
    void deleteAction(ActionEvent event) {
        try {
            User user = tableView.getSelectionModel().getSelectedItem();
            if (user == null) {
                return;
            }
            userService.deteleUser(user.getIdUser());
            tableView.getItems().remove(user);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editAction(ActionEvent event) {
        if (!tableView.getSelectionModel().isEmpty()) {
            mainText.setVisible(false);
            changeButton.setVisible(true);
            setDisable(false);
            setVisible(true);
        } else {
            mainText.getStyleClass().add("error");
            mainText.setText("Wybierz Najpierw Użytkownika");
        }

    }

    @FXML
    void exitEdit(MouseEvent event) {
        mainText.getStyleClass().remove("error");
        if (AdminController.userWindow)
            mainText.setText("Zarządzanie Użytkownikami");
        if (AdminController.trainerWindow)
            mainText.setText("Zarządzanie Trenerami");
        mainText.setVisible(true);
        changeButton.setVisible(false);
        addButton.setVisible(false);
        setDisable(true);
        setVisible(false);
    }

}
