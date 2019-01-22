package mainWindowTrainer;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import utilities.UserService;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
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
    private JFXButton addImage;

    private String tmp = "";

    @FXML
    private Text textImage;

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInformation();
    }

    void setInformation() {
        try {
            imie.setText(userService.getFieldFromUser(IDuser, "Imie"));
            nazwisko.setText(userService.getFieldFromUser(IDuser, "Nazwisko"));
            wiek.setText(userService.getFieldFromUser(IDuser, "Pesel"));
            plec.setText(userService.getFieldFromUser(IDuser, "Plec"));
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
                        reloadImage();
                        Files.copy(Paths.get(tmp), Paths.get("src/TrainerImages/" + nazwaZdjecia + ".png"), StandardCopyOption.REPLACE_EXISTING);
                        textImage.setText("Zdjęcie Dodane");
                        loadImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    textImage.setText("Format akceptowalny zdjęcia to PNG/JPG.");
                }
                if (zawiera2) {
                    try {
                        reloadImage();
                        Files.copy(Paths.get(tmp), Paths.get("src/TrainerImages/"+nazwaZdjecia + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
                        textImage.setText("Zdjęcie Dodane");
                        loadImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    textImage.setText("Format akceptowalny zdjęcia to PNG/JPG.");
                }
            } else {
                textImage.setText("Błąd w dodawaniu zdjęcia. Dodaj Jeszcze raz.");
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


    void loadImage() throws IOException {
        String nazwaZdjecia = userService.getFieldFromUser(IDuser, "Login");
        Image image1 = new Image(new FileInputStream("src/TrainerImages/"+nazwaZdjecia + ".jpg"));
        image.setImage(image1);
    }

    void reloadImage(){
        image.setImage(null);
    }
}
