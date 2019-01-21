package utilities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserProfile implements Externalizable {
    private final LongProperty ID = new SimpleLongProperty();
    private final LongProperty USER_ID = new SimpleLongProperty();
    private final StringProperty height = new SimpleStringProperty();
    private final StringProperty weight = new SimpleStringProperty();
    private final StringProperty neat = new SimpleStringProperty();
    private final StringProperty goal = new SimpleStringProperty();
    private final StringProperty other = new SimpleStringProperty();

    public long getID() {
        return ID.get();
    }

    public LongProperty IDProperty() {
        return ID;
    }

    public void setID(long ID) {
        this.ID.set(ID);
    }

    public long getUSER_ID() {
        return USER_ID.get();
    }

    public LongProperty USER_IDProperty() {
        return USER_ID;
    }

    public void setUSER_ID(long USER_ID) {
        this.USER_ID.set(USER_ID);
    }

    public String getHeight() {
        return height.get();
    }

    public StringProperty heightProperty() {
        return height;
    }

    public void setHeight(String height) {
        this.height.set(height);
    }

    public String getWeight() {
        return weight.get();
    }

    public StringProperty weightProperty() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }

    public String getNeat() {
        return neat.get();
    }

    public StringProperty neatProperty() {
        return neat;
    }

    public void setNeat(String neat) {
        this.neat.set(neat);
    }

    public String getGoal() {
        return goal.get();
    }

    public StringProperty goalProperty() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal.set(goal);
    }

    public String getOther() {
        return other.get();
    }

    public StringProperty otherProperty() {
        return other;
    }

    public void setOther(String other) {
        this.other.set(other);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getID());
        out.writeLong(getUSER_ID());
        out.writeObject(getHeight());
        out.writeObject(getWeight());
        out.writeObject(getNeat());
        out.writeObject(getGoal());
        out.writeObject(getOther());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setID(in.readLong());
        setUSER_ID(in.readLong());
        setHeight((String) in.readObject());
        setWeight((String) in.readObject());
        setNeat((String) in.readObject());
        setGoal((String) in.readObject());
        setOther((String) in.readObject());
    }
}
