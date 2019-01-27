package utilities.Messages;

import LoginWindow.LoadLoginWindow;
import LoginWindow.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.CalendarServices;
import server.MessageServices;
import server.UserServiceImplements;
import utilities.Message;
import utilities.User;
import utilities.UserService;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NewMessageController {
    @FXML
    Button send;
    @FXML
    Button cancel;
    @FXML
    TextField title;
    @FXML
    TextArea contents;

    private Message newMsg;

    private Long IDuser = LoginController.getIDuser();

    private UserService userService = LoadLoginWindow.getUserService();

    private void onCancel(ActionEvent e){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    private void onSend(ActionEvent e){
        newMsg.setTitle(CalendarServices.abbreviateToSize(title.getText(),250));
        newMsg.setContent(CalendarServices.abbreviateToSize(contents.getText(),250));
        MessageServices.addMessage(newMsg);
        Stage stage = (Stage) send.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() throws RemoteException {
        send.setOnAction(this::onSend);
        cancel.setOnAction(this::onCancel);
        newMsg = new Message();
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy hh:mm:ss");
        newMsg.setData(ft.format(dNow));
        newMsg.setIdSender(IDuser);
        UserServiceImplements u = new UserServiceImplements();
        //Ustawic odpowiednio
        //1.IdSender
        newMsg.setIdSender(IDuser);
        //2.Sender Text na imie + nazwisko
        newMsg.setSenderText(
                u.getFieldFromUser(newMsg.getIdSender(), "Imie")+" "+
                u.getFieldFromUser(newMsg.getIdSender(), "Nazwisko"));
        //3.IdReceiver
        if(userService.getUserById(IDuser).getRola().equals("customer"))
        {
            List<User> trainers = userService.getAllTrainer();
            Iterator<User> iterator = trainers.iterator();
            User trainer;
            long trainerID = -1;
            while (iterator.hasNext())
            {
                trainer = iterator.next();
                if (trainer.getIdUser_U() == IDuser)
                    trainerID = trainer.getIdUser();
            }

            if(trainerID!=-1) {
                newMsg.setIdReceiver(trainerID);
                newMsg.setReceiverText(
                        u.getFieldFromUser(newMsg.getIdReceiver(), "Imie")+" "+
                                u.getFieldFromUser(newMsg.getIdReceiver(), "Nazwisko"));
            }
            else
                new Alert(Alert.AlertType.INFORMATION,"Nie posiadasz trenera!").showAndWait();
        }
        else{
            long reciverID = userService.getUserById(IDuser).getIdUser_U();
            newMsg.setIdReceiver(reciverID);
            newMsg.setReceiverText(
                    u.getFieldFromUser(newMsg.getIdReceiver(), "Imie")+" "+
                            u.getFieldFromUser(newMsg.getIdReceiver(), "Nazwisko"));
        }
    }
}
