package utilities;

import javafx.beans.property.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Training implements Externalizable {
    private final LongProperty ID_training = new SimpleLongProperty();
    private final StringProperty chest = new SimpleStringProperty();
    private final StringProperty shoulders = new SimpleStringProperty();
    private final StringProperty biceps = new SimpleStringProperty();
    private final StringProperty triceps = new SimpleStringProperty();
    private final StringProperty back = new SimpleStringProperty();
    private final StringProperty abs = new SimpleStringProperty();
    private final StringProperty legs = new SimpleStringProperty();

    private final IntegerProperty chest_s = new SimpleIntegerProperty();
    private final IntegerProperty shoulders_s = new SimpleIntegerProperty();
    private final IntegerProperty biceps_s = new SimpleIntegerProperty();
    private final IntegerProperty triceps_s = new SimpleIntegerProperty();
    private final IntegerProperty back_s = new SimpleIntegerProperty();
    private final IntegerProperty abs_s = new SimpleIntegerProperty();
    private final IntegerProperty legs_s = new SimpleIntegerProperty();

    private final IntegerProperty chest_p = new SimpleIntegerProperty();
    private final IntegerProperty shoulders_p = new SimpleIntegerProperty();
    private final IntegerProperty biceps_p = new SimpleIntegerProperty();
    private final IntegerProperty triceps_p = new SimpleIntegerProperty();
    private final IntegerProperty back_p = new SimpleIntegerProperty();
    private final IntegerProperty abs_p = new SimpleIntegerProperty();
    private final IntegerProperty legs_p = new SimpleIntegerProperty();

    private final LongProperty ID_user = new SimpleLongProperty();

    public long getID_user() {
        return ID_user.get();
    }

    public LongProperty ID_userProperty() {
        return ID_user;
    }

    public void setID_user(long ID_user) {
        this.ID_user.set(ID_user);
    }

    public void setID_training(long ID_training) {
        this.ID_training.set(ID_training);
    }

    public void setChest(String chest) {
        this.chest.set(chest);
    }

    public void setShoulders(String shoulders) {
        this.shoulders.set(shoulders);
    }

    public void setBiceps(String biceps) {
        this.biceps.set(biceps);
    }

    public void setTriceps(String triceps) {
        this.triceps.set(triceps);
    }

    public void setBack(String back) {
        this.back.set(back);
    }

    public void setAbs(String abs) {
        this.abs.set(abs);
    }

    public void setLegs(String legs) {
        this.legs.set(legs);
    }

    public void setChest_s(int chest_s) {
        this.chest_s.set(chest_s);
    }

    public void setShoulders_s(int shoulders_s) {
        this.shoulders_s.set(shoulders_s);
    }

    public void setBiceps_s(int biceps_s) {
        this.biceps_s.set(biceps_s);
    }

    public void setTriceps_s(int triceps_s) {
        this.triceps_s.set(triceps_s);
    }

    public void setBack_s(int back_s) {
        this.back_s.set(back_s);
    }

    public void setAbs_s(int abs_s) {
        this.abs_s.set(abs_s);
    }

    public void setLegs_s(int legs_s) {
        this.legs_s.set(legs_s);
    }

    public void setChest_p(int chest_p) {
        this.chest_p.set(chest_p);
    }

    public void setShoulders_p(int shoulders_p) {
        this.shoulders_p.set(shoulders_p);
    }

    public void setBiceps_p(int biceps_p) {
        this.biceps_p.set(biceps_p);
    }

    public void setTriceps_p(int triceps_p) {
        this.triceps_p.set(triceps_p);
    }

    public void setBack_p(int back_p) {
        this.back_p.set(back_p);
    }

    public void setAbs_p(int abs_p) {
        this.abs_p.set(abs_p);
    }

    public void setLegs_p(int legs_p) {
        this.legs_p.set(legs_p);
    }

    public long getID_training() {
        return ID_training.get();
    }

    public LongProperty ID_trainingProperty() {
        return ID_training;
    }

    public String getChest() {
        return chest.get();
    }

    public StringProperty chestProperty() {
        return chest;
    }

    public String getShoulders() {
        return shoulders.get();
    }

    public StringProperty shouldersProperty() {
        return shoulders;
    }

    public String getBiceps() {
        return biceps.get();
    }

    public StringProperty bicepsProperty() {
        return biceps;
    }

    public String getTriceps() {
        return triceps.get();
    }

    public StringProperty tricepsProperty() {
        return triceps;
    }

    public String getBack() {
        return back.get();
    }

    public StringProperty backProperty() {
        return back;
    }

    public String getAbs() {
        return abs.get();
    }

    public StringProperty absProperty() {
        return abs;
    }

    public String getLegs() {
        return legs.get();
    }

    public StringProperty legsProperty() {
        return legs;
    }

    public int getChest_s() {
        return chest_s.get();
    }

    public IntegerProperty chest_sProperty() {
        return chest_s;
    }

    public int getShoulders_s() {
        return shoulders_s.get();
    }

    public IntegerProperty shoulders_sProperty() {
        return shoulders_s;
    }

    public int getBiceps_s() {
        return biceps_s.get();
    }

    public IntegerProperty biceps_sProperty() {
        return biceps_s;
    }

    public int getTriceps_s() {
        return triceps_s.get();
    }

    public IntegerProperty triceps_sProperty() {
        return triceps_s;
    }

    public int getBack_s() {
        return back_s.get();
    }

    public IntegerProperty back_sProperty() {
        return back_s;
    }

    public int getAbs_s() {
        return abs_s.get();
    }

    public IntegerProperty abs_sProperty() {
        return abs_s;
    }

    public int getLegs_s() {
        return legs_s.get();
    }

    public IntegerProperty legs_sProperty() {
        return legs_s;
    }

    public int getChest_p() {
        return chest_p.get();
    }

    public IntegerProperty chest_pProperty() {
        return chest_p;
    }

    public int getShoulders_p() {
        return shoulders_p.get();
    }

    public IntegerProperty shoulders_pProperty() {
        return shoulders_p;
    }

    public int getBiceps_p() {
        return biceps_p.get();
    }

    public IntegerProperty biceps_pProperty() {
        return biceps_p;
    }

    public int getTriceps_p() {
        return triceps_p.get();
    }

    public IntegerProperty triceps_pProperty() {
        return triceps_p;
    }

    public int getBack_p() {
        return back_p.get();
    }

    public IntegerProperty back_pProperty() {
        return back_p;
    }

    public int getAbs_p() {
        return abs_p.get();
    }

    public IntegerProperty abs_pProperty() {
        return abs_p;
    }

    public int getLegs_p() {
        return legs_p.get();
    }

    public IntegerProperty legs_pProperty() {
        return legs_p;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException{
        out.writeLong(getID_training());
        out.writeObject(getChest());
        out.writeObject(getShoulders());
        out.writeObject(getBiceps());
        out.writeObject(getTriceps());
        out.writeObject(getBack());
        out.writeObject(getAbs());
        out.writeObject(getLegs());

        out.writeInt(getChest_s());
        out.writeInt(getShoulders_s());
        out.writeInt(getBiceps_s());
        out.writeInt(getTriceps_s());
        out.writeInt(getBack_s());
        out.writeInt(getAbs_s());
        out.writeInt(getLegs_s());

        out.writeInt(getChest_p());
        out.writeInt(getShoulders_p());
        out.writeInt(getBiceps_p());
        out.writeInt(getTriceps_p());
        out.writeInt(getBack_p());
        out.writeInt(getAbs_p());
        out.writeInt(getLegs_p());

        out.writeLong(getID_user());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setID_training(in.readLong());
        setChest((String) in.readObject());
        setShoulders((String) in.readObject());
        setBiceps((String) in.readObject());
        setTriceps((String) in.readObject());
        setBack((String) in.readObject());
        setAbs((String) in.readObject());
        setLegs((String) in.readObject());

        setChest_s(in.readInt());
        setShoulders_s(in.readInt());
        setBiceps_s(in.readInt());
        setTriceps_s(in.readInt());
        setBack_s(in.readInt());
        setAbs_s(in.readInt());
        setLegs_s(in.readInt());

        setChest_p(in.readInt());
        setShoulders_p(in.readInt());
        setBiceps_p(in.readInt());
        setTriceps_p(in.readInt());
        setBack_p(in.readInt());
        setAbs_p(in.readInt());
        setLegs_p(in.readInt());

        setID_user(in.readLong());
    }



    public static final class Builder{
        private final LongProperty ID_training = new SimpleLongProperty();
        private final StringProperty chest = new SimpleStringProperty();
        private final StringProperty shoulders = new SimpleStringProperty();
        private final StringProperty biceps = new SimpleStringProperty();
        private final StringProperty triceps = new SimpleStringProperty();
        private final StringProperty back = new SimpleStringProperty();
        private final StringProperty abs = new SimpleStringProperty();
        private final StringProperty legs = new SimpleStringProperty();

        private final IntegerProperty chest_s = new SimpleIntegerProperty();
        private final IntegerProperty shoulders_s = new SimpleIntegerProperty();
        private final IntegerProperty biceps_s = new SimpleIntegerProperty();
        private final IntegerProperty triceps_s = new SimpleIntegerProperty();
        private final IntegerProperty back_s = new SimpleIntegerProperty();
        private final IntegerProperty abs_s = new SimpleIntegerProperty();
        private final IntegerProperty legs_s = new SimpleIntegerProperty();

        private final IntegerProperty chest_p = new SimpleIntegerProperty();
        private final IntegerProperty shoulders_p = new SimpleIntegerProperty();
        private final IntegerProperty biceps_p = new SimpleIntegerProperty();
        private final IntegerProperty triceps_p = new SimpleIntegerProperty();
        private final IntegerProperty back_p = new SimpleIntegerProperty();
        private final IntegerProperty abs_p = new SimpleIntegerProperty();
        private final IntegerProperty legs_p = new SimpleIntegerProperty();

        private final LongProperty ID_user = new SimpleLongProperty();

        public Builder Chest(String chest) {
            this.chest.set(chest);
            return this;
        }

        public Builder Shoulders(String shoulders) {
            this.shoulders.set(shoulders);
            return this;
        }

        public Builder Biceps(String biceps) {
            this.biceps.set(biceps);
            return this;
        }

        public Builder Triceps(String triceps) {
            this.triceps.set(triceps);
            return this;
        }

        public Builder Back(String back) {
            this.back.set(back);
            return this;
        }

        public Builder Abs(String abs) {
            this.abs.set(abs);
            return this;
        }

        public Builder Legs(String legs) {
            this.legs.set(legs);
            return this;
        }

        public Builder Chest_s(int chest_s) {
            this.chest_s.set(chest_s);
            return this;
        }

        public Builder Shoulders_s(int shoulders_s) {
            this.shoulders_s.set(shoulders_s);
            return this;
        }

        public Builder Biceps_s(int biceps_s) {
            this.biceps_s.set(biceps_s);
            return this;
        }

        public Builder Triceps_s(int triceps_s) {
            this.triceps_s.set(triceps_s);
            return this;
        }

        public Builder Back_s(int back_s) {
            this.back_s.set(back_s);
            return this;
        }

        public Builder Abs_s(int abs_s) {
            this.abs_s.set(abs_s);
            return this;
        }

        public Builder Legs_s(int legs_s) {
            this.legs_s.set(legs_s);
            return this;
        }

        public Builder Chest_p(int chest_p) {
            this.chest_p.set(chest_p);
            return this;
        }

        public Builder Shoulders_p(int shoulders_p) {
            this.shoulders_p.set(shoulders_p);
            return this;
        }

        public Builder Biceps_p(int biceps_p) {
            this.biceps_p.set(biceps_p);
            return this;
        }

        public Builder Triceps_p(int triceps_p) {
            this.triceps_p.set(triceps_p);
            return this;
        }

        public Builder Back_p(int back_p) {
            this.back_p.set(back_p);
            return this;
        }

        public Builder Abs_p(int abs_p) {
            this.abs_p.set(abs_p);
            return this;
        }

        public Builder Legs_p(int legs_p) {
            this.legs_p.set(legs_p);
            return this;
        }

        public Builder ID_user(long ID_user) {
            this.ID_user.set(ID_user);
            return this;
        }


        public Training build(){
            if(ID_training.toString().isEmpty())
                throw new IllegalStateException("ID_training cannot be empty");
            if(ID_user.toString().isEmpty())
                throw new IllegalStateException("ID_user cannot be empty");

            Training training = new Training();

            training.chest.set(this.chest.get());
            training.shoulders.set(this.shoulders.get());
            training.biceps.set(this.biceps.get());
            training.triceps.set(this.triceps.get());
            training.back.set(this.back.get());
            training.abs.set(this.abs.get());
            training.legs.set(this.legs.get());

            training.chest_s.set(this.chest_s.get());
            training.shoulders_s.set(this.shoulders_s.get());
            training.biceps_s.set(this.biceps_s.get());
            training.triceps_s.set(this.triceps_s.get());
            training.back_s.set(this.back_s.get());
            training.abs_s.set(this.abs_s.get());
            training.legs_s.set(this.legs_s.get());

            training.chest_p.set(this.chest_p.get());
            training.shoulders_p.set(this.shoulders_p.get());
            training.biceps_p.set(this.biceps_p.get());
            training.triceps_p.set(this.triceps_p.get());
            training.back_p.set(this.back_p.get());
            training.abs_p.set(this.abs_p.get());
            training.legs_p.set(this.legs_p.get());

            training.ID_user.set(this.ID_user.get());

            return training;
        }
    }
}
