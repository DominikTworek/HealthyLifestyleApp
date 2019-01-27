package utilities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class UserProgress implements Externalizable {
    private final LongProperty id_progress = new SimpleLongProperty();
    private final IntegerProperty waga = new SimpleIntegerProperty();
    private final IntegerProperty klatka = new SimpleIntegerProperty();
    private final IntegerProperty talia = new SimpleIntegerProperty();
    private final IntegerProperty pas = new SimpleIntegerProperty();
    private final IntegerProperty biodro = new SimpleIntegerProperty();
    private final IntegerProperty udo = new SimpleIntegerProperty();
    private final IntegerProperty ramie = new SimpleIntegerProperty();
    private final LongProperty id_user = new SimpleLongProperty();

    public long getId_progress() {
        return id_progress.get();
    }

    public LongProperty id_progressProperty() {
        return id_progress;
    }

    public void setId_progress(long id_progress) {
        this.id_progress.set(id_progress);
    }

    public int getWaga() {
        return waga.get();
    }

    public IntegerProperty wagaProperty() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga.set(waga);
    }

    public int getKlatka() {
        return klatka.get();
    }

    public IntegerProperty klatkaProperty() {
        return klatka;
    }

    public void setKlatka(int klatka) {
        this.klatka.set(klatka);
    }

    public int getTalia() {
        return talia.get();
    }

    public IntegerProperty taliaProperty() {
        return talia;
    }

    public void setTalia(int talia) {
        this.talia.set(talia);
    }

    public int getPas() {
        return pas.get();
    }

    public IntegerProperty pasProperty() {
        return pas;
    }

    public void setPas(int pas) {
        this.pas.set(pas);
    }

    public int getBiodro() {
        return biodro.get();
    }

    public IntegerProperty biodroProperty() {
        return biodro;
    }

    public void setBiodro(int biodro) {
        this.biodro.set(biodro);
    }

    public int getUdo() {
        return udo.get();
    }

    public IntegerProperty udoProperty() {
        return udo;
    }

    public void setUdo(int udo) {
        this.udo.set(udo);
    }

    public int getRamie() {
        return ramie.get();
    }

    public IntegerProperty ramieProperty() {
        return ramie;
    }

    public void setRamie(int ramie) {
        this.ramie.set(ramie);
    }

    public long getId_user() {
        return id_user.get();
    }

    public LongProperty id_userProperty() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user.set(id_user);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getId_progress());
        out.writeInt(getWaga());
        out.writeInt(getKlatka());
        out.writeInt(getTalia());
        out.writeInt(getPas());
        out.writeInt(getBiodro());
        out.writeInt(getUdo());
        out.writeInt(getRamie());
        out.writeLong(getId_user());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setId_progress(in.readLong());
        setWaga(in.readInt());
        setKlatka(in.readInt());
        setTalia(in.readInt());
        setPas(in.readInt());
        setBiodro(in.readInt());
        setUdo(in.readInt());
        setRamie(in.readInt());
        setId_user(in.readLong());
    }
}
