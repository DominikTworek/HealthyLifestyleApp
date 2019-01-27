package utilities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class Message implements Externalizable, Comparable<Message>{
    private final LongProperty IdMsg = new SimpleLongProperty();
    private final LongProperty IdSender = new SimpleLongProperty();
    private final LongProperty IdReceiver = new SimpleLongProperty();
    private final StringProperty senderText = new SimpleStringProperty();
    private final StringProperty receiverText = new SimpleStringProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty content = new SimpleStringProperty();
    private final StringProperty data = new SimpleStringProperty();

    public long getIdMsg() {
        return IdMsg.get();
    }

    public LongProperty idMsgProperty() {
        return IdMsg;
    }

    public void setIdMsg(long idMsg) {
        this.IdMsg.set(idMsg);
    }

    public long getIdSender() {
        return IdSender.get();
    }

    public LongProperty idSenderProperty() {
        return IdSender;
    }

    public void setIdSender(long idSender) {
        this.IdSender.set(idSender);
    }

    public long getIdReceiver() {
        return IdReceiver.get();
    }

    public LongProperty idReceiverProperty() {
        return IdReceiver;
    }

    public void setIdReceiver(long idReceiver) {
        this.IdReceiver.set(idReceiver);
    }

    public String getSenderText() {
        return senderText.get();
    }

    public StringProperty senderTextProperty() {
        return senderText;
    }

    public void setSenderText(String senderText) {
        this.senderText.set(senderText);
    }

    public String getReceiverText() {
        return receiverText.get();
    }

    public StringProperty receiverTextProperty() {
        return receiverText;
    }

    public void setReceiverText(String receiverText) {
        this.receiverText.set(receiverText);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public StringProperty contentProperty() {
        return content;
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getData() {
        return data.get();
    }

    public StringProperty dataProperty() {
        return data;
    }

    public void setData(String data) {
        this.data.set(data);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getIdMsg());
        out.writeLong(getIdSender());
        out.writeLong(getIdReceiver());
        out.writeObject(getSenderText());
        out.writeObject(getReceiverText());
        out.writeObject(getTitle());
        out.writeObject(getContent());
        out.writeObject(getData());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setIdMsg(in.readLong());
        setIdSender(in.readLong());
        setIdReceiver(in.readLong());
        setSenderText((String)in.readObject());
        setReceiverText((String)in.readObject());
        setTitle((String)in.readObject());
        setContent((String)in.readObject());
        setData((String)in.readObject());
    }

    @Override
    public int compareTo(Message o) {
        return data.get().compareTo(o.data.get());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(IdMsg, message.IdMsg) &&
                Objects.equals(IdSender, message.IdSender) &&
                Objects.equals(IdReceiver, message.IdReceiver) &&
                Objects.equals(senderText, message.senderText) &&
                Objects.equals(receiverText, message.receiverText) &&
                Objects.equals(title, message.title) &&
                Objects.equals(content, message.content) &&
                Objects.equals(data, message.data);
    }

    @Override
    public int hashCode() {

        return Objects.hash(IdMsg, IdSender, IdReceiver, senderText, receiverText, title, content, data);
    }

    @Override
    public String toString() {
        return senderText.get()+"do: "+receiverText.get()+" - "+title.get()+"["+ data.get()+"]";
    }
}
