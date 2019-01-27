package utilities.Messages;

import LoginWindow.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.CalendarServices;
import server.MessageServices;
import server.UserServiceImplements;
import utilities.Message;
import utilities.User;

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
        newMsg.setIdSender(LoginController.getIDuser());
        UserServiceImplements u = new UserServiceImplements();
        //Ustawic odpowiednio
        //1.IdSender
        newMsg.setIdSender(LoginController.getIDuser());
        //2.Sender Text na imie + nazwisko
        newMsg.setSenderText(
                u.getFieldFromUser(newMsg.getIdSender(), "Imie")+" "+
                u.getFieldFromUser(newMsg.getIdSender(), "Nazwisko"));
        //3.IdReceiver
        //4.Id Receiver na imie + nazwisko
    }
}
