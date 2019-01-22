package utilities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TrainerProfile implements Externalizable {

    private final LongProperty id_trainer_profile = new SimpleLongProperty();
    private final LongProperty id_trainer = new SimpleLongProperty();
    private final StringProperty specjalizacja = new SimpleStringProperty();
    private final StringProperty informacje = new SimpleStringProperty();

    public long getId_trainer_profile() {
        return id_trainer_profile.get();
    }

    public LongProperty id_trainer_profileProperty() {
        return id_trainer_profile;
    }

    public void setId_trainer_profile(long id_trainer_profile) {
        this.id_trainer_profile.set(id_trainer_profile);
    }

    public long getId_trainer() {
        return id_trainer.get();
    }

    public LongProperty id_trainerProperty() {
        return id_trainer;
    }

    public void setId_trainer(long id_trainer) {
        this.id_trainer.set(id_trainer);
    }

    public String getSpecjalizacja() {
        return specjalizacja.get();
    }

    public StringProperty specjalizacjaProperty() {
        return specjalizacja;
    }

    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja.set(specjalizacja);
    }

    public String getInformacje() {
        return informacje.get();
    }

    public StringProperty informacjeProperty() {
        return informacje;
    }

    public void setInformacje(String informacje) {
        this.informacje.set(informacje);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId_trainer_profile());
        out.writeLong(getId_trainer());
        out.writeObject(getSpecjalizacja());
        out.writeObject(getInformacje());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId_trainer_profile(in.readLong());
        setId_trainer(in.readLong());
        setSpecjalizacja((String) in.readObject());
        setInformacje((String) in.readObject());
    }
}
