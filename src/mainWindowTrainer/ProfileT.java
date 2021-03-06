package mainWindowTrainer;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import utilities.TrainerProfile;
import utilities.User;
import utilities.UserService;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class ProfileT implements Initializable {

    @FXML
    private ImageView image;

    @FXML
    private JFXTextField plec;

    @FXML
    private JFXTextField imie;

    @FXML
    private JFXTextField nazwisko;

    @FXML
    private JFXTextField wiek;

    @FXML
    private JFXTextField specjalizacja;

    @FXML
    private JFXTextField inne;

    @FXML
    private JFXTextField imieEdit;

    @FXML
    private JFXTextField nazwiskoEdit;

    @FXML
    private JFXTextField wiekEdit;

    @FXML
    private JFXTextArea informacje;

    @FXML
    private JFXTextArea informacjeEdit;

    @FXML
    private JFXComboBox<String> specjalizacjaEdit;

    @FXML
    private JFXButton addImage;

    private String tmp = "";

    @FXML
    private Text textImage;

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboBox();
        setInformation();
        try {
            loadImage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void setComboBox() {
        specjalizacjaEdit.getItems().addAll("Spalanie", "Budowa", "Brak");
    }

    void setInformation() {
        try {
            imie.setText("Imie: " + userService.getFieldFromUser(IDuser, "Imie"));
            nazwisko.setText("Nazwisko: " + userService.getFieldFromUser(IDuser, "Nazwisko"));
            wiek.setText("Wiek: " + userService.getFieldFromUser(IDuser, "Pesel"));
            plec.setText("Plec: " + userService.getFieldFromUser(IDuser, "Plec"));
            imieEdit.setText(userService.getFieldFromUser(IDuser, "Imie"));
            nazwiskoEdit.setText(userService.getFieldFromUser(IDuser, "Nazwisko"));
            wiekEdit.setText(userService.getFieldFromUser(IDuser, "Pesel"));
            String spr_specjalizacji;
            spr_specjalizacji = userService.getFieldFromTrainerProfile(IDuser, "specjalizacja");
            if (spr_specjalizacji != null) {
                if (spr_specjalizacji.equals("Spalanie")) {
                    specjalizacja.setText("Specjalizacja: " + spr_specjalizacji);
                    specjalizacjaEdit.getSelectionModel().select(0);
                } else if (spr_specjalizacji.equals("Budowa")) {
                    specjalizacja.setText("Specjalizacja: " + spr_specjalizacji);
                    specjalizacjaEdit.getSelectionModel().select(1);
                } else {
                    specjalizacja.setText("Specjalizacja: Brak");
                    specjalizacjaEdit.getSelectionModel().select(2);
                }
                informacje.setText(userService.getFieldFromTrainerProfile(IDuser, "informacje"));
            }
            else{
                specjalizacja.setText("Specjalizacja: Brak");
                specjalizacjaEdit.getSelectionModel().select(2);
                informacje.setText("Informacje: brak");
                informacjeEdit.setText("Informacje: Brak");

            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addImage(ActionEvent event) {
        try {
            String nazwaZdjecia = userService.getFieldFromUser(IDuser, "Login");
            FileChooser fc = new FileChooser();
            File seletedFile = fc.showOpenDialog(null);
            String png = ".png";
            String jpg = ".jpg";
            Boolean zawiera;
            Boolean zawiera2;
            if (seletedFile != null) {
                tmp = seletedFile.getAbsolutePath();
                zawiera = tmp.contains(png);
                zawiera2 = tmp.contains(jpg);
                if (zawiera) {
                    try {
                        Files.copy(Paths.get(tmp), Paths.get("src/TrainerImages/" + nazwaZdjecia + ".png"), StandardCopyOption.REPLACE_EXISTING);
                        textImage.setText("Zdjęcie Dodane");
                        loadImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    textImage.getStyleClass().add("smalltext_bad");
                    textImage.setText("Format akceptowalny zdjęcia to PNG/JPG.");
                }
                if (zawiera2) {
                    try {
                        textImage.getStyleClass().remove("smalltext_bad");
                        Files.copy(Paths.get(tmp), Paths.get("src/TrainerImages/" + nazwaZdjecia + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
                        textImage.setText("Zdjęcie Dodane");
                        loadImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    textImage.getStyleClass().add("smalltext_bad");
                    textImage.setText("Format akceptowalny zdjęcia to PNG/JPG.");
                }
            } else {
                textImage.getStyleClass().add("smalltext_bad");
                textImage.setText("Błąd w dodawaniu zdjęcia. Dodaj Jeszcze raz.");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    void loadImage() throws IOException {
        String nazwaZdjecia = userService.getFieldFromUser(IDuser, "Login");
        if (Files.exists(Paths.get("src/TrainerImages/" + nazwaZdjecia + ".jpg"))) {
            Image image1 = new Image(new FileInputStream("src/TrainerImages/" + nazwaZdjecia + ".jpg"));
            image.setImage(image1);
        } else {
            Image image2 = new Image(new FileInputStream("src/TrainerImages/zdjecie.png"));
            image.setImage(image2);
        }
    }


    Boolean checkData() {
        if (imieEdit.getText().isEmpty() || nazwiskoEdit.getText().isEmpty() || wiekEdit.getText().isEmpty())
            return false;

        else
            return true;
    }

    @FXML
    void editButton(ActionEvent event) throws RemoteException {
        if (checkData()) {
            textImage.getStyleClass().remove("smalltext_bad");
            textImage.setText("Pomyślnie edytowano dane");
            User user = new User();
            user.setIdUser(Long.valueOf(IDuser));
            user.setLogin(userService.getFieldFromUser(IDuser, "Login"));
            user.setPassword(userService.getFieldFromUser(IDuser, "Password"));
            user.setImie(imieEdit.getText());
            user.setNazwisko(nazwiskoEdit.getText());
            user.setPlec(userService.getFieldFromUser(IDuser, "Plec"));
            user.setPesel(wiekEdit.getText());
            user.setRola(userService.getFieldFromUser(IDuser, "Rola"));

            userService.updateUser(user);


            TrainerProfile trainerProfile = new TrainerProfile();

            trainerProfile.setId_trainer(IDuser);
            trainerProfile.setSpecjalizacja(specjalizacjaEdit.getValue().toString());
            trainerProfile.setInformacje(informacjeEdit.getText());

            Long spr;
            try {
                spr = Long.valueOf(userService.getFieldFromTrainerProfile(IDuser, "id_trainer"));
            }catch (NumberFormatException e){
                userService.insertTrainerProfile(trainerProfile);
                spr = Long.valueOf(userService.getFieldFromTrainerProfile(IDuser, "id_trainer"));
            }

            if (!spr.equals(IDuser)) {
                userService.insertTrainerProfile(trainerProfile);
            } else {
                userService.updateTrainerProfile(trainerProfile);
            }
            System.out.println(trainerProfile);
            setInformation();
        } else {
            textImage.getStyleClass().add("smalltext_bad");
            textImage.setText("Błąd w edycji. Sprawdź wszystkie pola");
        }

    }
}
