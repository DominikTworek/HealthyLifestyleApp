package server;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DatabaseConnection;
import utilities.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageServices {
    private static List<Message> resultSetToList(ResultSet result){
        try {
            List<Message> messages = new ArrayList<>();
            while (result.next()){
                Message m = new Message();
                m.setIdMsg(result.getLong("IdMsg"));
                m.setIdSender(result.getLong("IdSender"));
                m.setIdReceiver(result.getLong("IdReceiver"));
                m.setSenderText(result.getString("senderText"));
                m.setReceiverText(result.getString("receiverText"));
                m.setTitle(result.getString("title"));
                m.setContent(result.getString("content"));
                m.setData(result.getString("data"));
                messages.add(m);
            }
            return messages;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    public static ObservableList<Message> getMsgForSender(Long senderID){
        PreparedStatement statement = null;

        String sql = "select * from MESSAGES where IdSender=?";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, senderID);

            ResultSet result = statement.executeQuery();

            List<Message> messages = resultSetToList(result);
            Collections.sort(messages);

            return FXCollections.observableArrayList(messages);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ObservableList<Message> getMsgForReceiver(Long receiverID){
        PreparedStatement statement = null;

        String sql = "select * from MESSAGES where IdReceiver=?";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, receiverID);

            ResultSet result = statement.executeQuery();

            List<Message> messages = resultSetToList(result);
            Collections.sort(messages);

            return FXCollections.observableArrayList(messages);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addMessage(Message m){
        PreparedStatement statement = null;

        String sql = "insert into MESSAGES(IdMsg, IdSender, IdReceiver, senderText, receiverText, title, content, data) values (MESSAGES_SEQ.nextval,?,?,?,?,?,?,?)";
        try {
            statement = DatabaseConnection.getConnection().prepareStatement(sql);
            statement.setLong(1, m.getIdSender());
            statement.setLong(2, m.getIdReceiver());
            statement.setString(3, m.getSenderText());
            statement.setString(4, m.getReceiverText());
            statement.setString(5, m.getTitle());
            statement.setString(6, m.getContent());
            statement.setString(7, m.getData());
            System.out.println(statement.executeUpdate());
        }catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}