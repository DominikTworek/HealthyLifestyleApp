package mainWindowUser;

import LoginWindow.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.MessageServices;
import utilities.Calendar.EditWindow.EditWindowController;
import utilities.Message;
import utilities.Messages.NewMessageController;
import utilities.Messages.ViewMessageController;

import java.io.IOException;


public class MessageUController {
    @FXML
    GridPane mainPane;
    @FXML
    Button newMsg;
    @FXML
    Button show;
    @FXML
    Button received;
    @FXML
    Button sent;

    private ListView<Message> msgList;

    private void onReceived(ActionEvent e){
        fillReceived();
    }

    private void onSent(ActionEvent e){
        fillSent();
    }

    private void onNewMsg(ActionEvent e){
        FXMLLoader loader = new FXMLLoader(NewMessageController.class.getResource("NewMessage.fxml"));
        Stage stage = new Stage();
        try {
            GridPane pane = loader.load();
            stage.setScene(new Scene(pane));
            stage.initOwner(((Node)e.getSource()).getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Nowa wiadomość");
            stage.showAndWait();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void onShow(ActionEvent e){
        if (!msgList.getSelectionModel().isEmpty()){
            FXMLLoader loader = new FXMLLoader(ViewMessageController.class.getResource("ViewMessage.fxml"));
            Stage stage = new Stage();
            try {
                GridPane pane = loader.load();
                stage.setScene(new Scene(pane));
                stage.initOwner(((Node)e.getSource()).getScene().getWindow());
                stage.initModality(Modality.WINDOW_MODAL);
                stage.setTitle("Wiadomość");
                ViewMessageController controller = loader.<ViewMessageController>getController();
                controller.initData(msgList.getSelectionModel().getSelectedItem());
                stage.showAndWait();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize(){
        msgList = new ListView<>();
        mainPane.add(msgList, 0, 1);
        received.setOnAction(this::onReceived);
        sent.setOnAction(this::onSent);
        newMsg.setOnAction(this::onNewMsg);
        show.setOnAction(this::onShow);
        fillReceived();
    }

    private void fillReceived(){
        msgList.getItems().setAll(MessageServices.getMsgForReceiver(LoginController.getIDuser()));
    }

    private void fillSent(){
        msgList.getItems().setAll(MessageServices.getMsgForSender(LoginController.getIDuser()));
    }
}
