package utilities.Messages;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilities.Message;

public class ViewMessageController {
    @FXML
    TextField title;
    @FXML
    TextField date;
    @FXML
    TextField sender;
    @FXML
    TextField receiver;
    @FXML
    TextArea content;
    @FXML
    Button close;

    private void onClose(ActionEvent e){
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){
        close.setOnAction(this::onClose);
        date.setEditable(false);
        date.setMouseTransparent(true);
        date.setFocusTraversable(false);
        title.setEditable(false);
        title.setMouseTransparent(true);
        title.setFocusTraversable(false);
        receiver.setEditable(false);
        receiver.setMouseTransparent(true);
        receiver.setFocusTraversable(false);
        sender.setEditable(false);
        sender.setMouseTransparent(true);
        sender.setFocusTraversable(false);
        content.setEditable(false);
        content.setMouseTransparent(true);
        content.setFocusTraversable(false);
    }

    public void initData(Message m){
        title.setText(m.getTitle());
        date.setText(m.getData());
        receiver.setText(m.getReceiverText());
        sender.setText(m.getSenderText());
        content.setText(m.getContent());
    }
}
