package utilities;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.*;

public class User implements Externalizable {
    private final LongProperty IdUser = new SimpleLongProperty();
    private final StringProperty Login = new SimpleStringProperty();
    private final StringProperty Password = new SimpleStringProperty();
    private final StringProperty Imie = new SimpleStringProperty();
    private final StringProperty Nazwisko = new SimpleStringProperty();
    private final LongProperty Plec = new SimpleLongProperty();
    private final LongProperty Pesel = new SimpleLongProperty();
    private final LongProperty Rola = new SimpleLongProperty();

    public long getRola() {
        return Rola.get();
    }

    public LongProperty rolaProperty() {

        return Rola;
    }

    public void setRola(long value) {

        Rola.set(value);
    }



    public long getPesel() {

        return Pesel.get();
    }

    public LongProperty peselProperty() {

        return Pesel;
    }

    public void setPesel(long value) {

        Pesel.set(value);
    }



    public long getPlec() {

        return Plec.get();
    }

    public LongProperty plecProperty() {

        return Plec;
    }

    public void setPlec(long value) {

        Plec.set(value);
    }

    public String getNazwisko() {

        return Nazwisko.get();
    }

    public StringProperty nazwiskoProperty() {

        return Nazwisko;
    }

    public void setNazwisko(String value) {

        Nazwisko.set(value);
    }

    public String getImie() {

        return Imie.get();
    }

    public StringProperty imieProperty() {

        return Imie;
    }

    public void setImie(String value) {

        Imie.set(value);
    }

    public String getPassword() {

        return Password.get();
    }

    public StringProperty passwordProperty() {

        return Password;
    }

    public void setPassword(String value) {

        Password.set(value);
    }

    public void setLogin(String value) {
        Login.set(value);
    }

    public String getLogin() {

        return Login.get();
    }

    public StringProperty loginProperty() {

        return Login;
    }

    public long getIdUser(){

        return IdUser.get();
    }

    public void setIdUser(long value){

        IdUser.set(value);
    }

    public LongProperty idUserProperty() {

        return IdUser;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getIdUser());
        out.writeObject(getLogin());
        out.writeObject(getPassword());
        out.writeObject(getImie());
        out.writeObject(getNazwisko());
        out.writeObject(getPlec());
        out.writeObject(getPesel());
        out.writeObject(getRola());


    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setIdUser(in.readLong());
        setLogin((String) in.readObject());
        setPassword((String) in.readObject());
        setImie((String) in.readObject());
        setNazwisko((String) in.readObject());
        setPlec(in.readLong());
        setPesel(in.readLong());
        setRola(in.readLong());
    }
}
