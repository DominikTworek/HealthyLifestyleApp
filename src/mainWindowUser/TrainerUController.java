package mainWindowUser;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utilities.User;
import utilities.UserService;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class TrainerUController implements Initializable {

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
    private ImageView image;

    @FXML
    private JFXTextArea informacje;

    @FXML
    private Text confirmText;

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    List<User> allTrainer;
    {
        try {
            allTrainer = userService.getAllTrainer();
        } catch (RemoteException e) {
        }
    }

    Iterator<User> iterator = allTrainer.iterator();

    Long idTrainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iterableTrainer();
    }

    @FXML
    void iterableTrainer(){
        if(iterator.hasNext()){
            setTrainer(iterator.next().getIdUser());
        }
        else {
            iterator = allTrainer.iterator();
            if(iterator.hasNext()){
                setTrainer(iterator.next().getIdUser());
            }
        }
    }

    void setTrainer(Long TrainerID){
            try {
                idTrainer = TrainerID;
                imie.setText("Imie: " + userService.getFieldFromUser(TrainerID, "Imie"));
                nazwisko.setText("Nazwisko: " + userService.getFieldFromUser(TrainerID, "Nazwisko"));
                wiek.setText("Wiek: " + userService.getFieldFromUser(TrainerID, "Pesel"));
                plec.setText("Plec: " + userService.getFieldFromUser(TrainerID, "Plec"));
                String spr_specjalizacji;
                spr_specjalizacji = userService.getFieldFromTrainerProfile(TrainerID, "specjalizacja");
                if (spr_specjalizacji != null) {
                    if (spr_specjalizacji.equals("Spalanie")) {
                        specjalizacja.setText("Specjalizacja: " + spr_specjalizacji);
                    } else if (spr_specjalizacji.equals("Budowa")) {
                        specjalizacja.setText("Specjalizacja: " + spr_specjalizacji);
                    } else {
                        specjalizacja.setText("Specjalizacja: Brak");
                    }
                    informacje.setText(userService.getFieldFromTrainerProfile(TrainerID, "informacje"));
                }
                else{
                    specjalizacja.setText("Specjalizacja: Brak");
                    informacje.setText("Informacje: brak");
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
    }

    @FXML
    void confirmTrainer() throws RemoteException {
        User trainer = userService.getUserById(idTrainer);

        allTrainer = userService.getAllTrainer();

        Iterator<User> iter = allTrainer.iterator();

        try {
            if (iter.hasNext()) {
                while (iter.hasNext()) {
                    if (iter.next().getIdUser_U() == IDuser) {
                        confirmText.setText("Możesz wybrać tylko jednego trenera!");
                        confirmText.setFill(Color.RED);
                        confirmText.setVisible(true);
                        return;
                    }
                }

                iter = allTrainer.iterator();
                userService.updateUserU(trainer, IDuser);
                confirmText.setVisible(true);
            }
        }catch (NullPointerException e){
            confirmText.setVisible(false);
        }

     }
}

