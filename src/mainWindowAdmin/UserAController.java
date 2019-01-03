package mainWindowAdmin;

import RegistryWindow.LoadRegistryWindow;
import RegistryWindow.RegistryController;
import adminWindow.AdminController;
import adminWindow.LoadAdminWindow;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.UserServiceImplements;
import utilities.User;
import utilities.UserService;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class UserAController implements Initializable {

    @FXML
    private Text mainText;

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

    private UserService userService;
    private LoadAdminWindow loadAdminWindow;
    private LoadRegistryWindow loadRegistryWindow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setDisable(true);
        setVisible(false);
        mainText.setVisible(true);
        setCollvalue();
        setComboBox();
        setEditValue();
    }

    public void setComboBox() {
        plec.getItems().addAll("mezczyzna", "kobieta");
        rola.getItems().addAll("costumer", "trainer", "admin");
    }

    public void setUser(LoadAdminWindow loadAdminWindow){
        this.loadAdminWindow = loadAdminWindow;
        this.userService = loadAdminWindow.getUserService();
       try {
            tableView.getItems().setAll(userService.getAllUser());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
    }

    void setEditValue(){
        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                login.setText(newValue.getLogin());
                haslo.setText(newValue.getPassword());
                imie.setText(newValue.getImie());
                nazwisko.setText(newValue.getPlec());
                plec.setValue(newValue.getPlec());
                pesel.setText(newValue.getPesel());
                rola.setValue(newValue.getRola());
            }
        });
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainWindowAdmin/UserA.fxml"));
        AnchorPane change = loader.load();

        RegistryController registryController = loader.getController();

        registryController.setUser(loadRegistryWindow);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(change));
        stage.show();
    }



    @FXML
    void editAction(ActionEvent event) {
        User user = null;
        if(tableView.getSelectionModel().isSelected((int) user.getIdUser())) {
            mainText.setVisible(false);
            changeButton.setVisible(true);
            setDisable(false);
            setVisible(true);
        }
        else{

        }

    }

    @FXML
    void exitEdit(MouseEvent event) {
        mainText.setVisible(true);
        changeButton.setVisible(false);
        addButton.setVisible(false);
        setDisable(true);
        setVisible(false);
    }
}
