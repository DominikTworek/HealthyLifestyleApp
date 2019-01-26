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
    private final StringProperty Plec = new SimpleStringProperty();
    private final StringProperty Pesel = new SimpleStringProperty();
    private final StringProperty Rola = new SimpleStringProperty();
    private final LongProperty IdUser_T = new SimpleLongProperty();

    public long getIdUser_T() {
        return IdUser_T.get();
    }

    public LongProperty idUser_TProperty() {
        return IdUser_T;
    }

    public void setIdUser_T(long idUser_T) {
        this.IdUser_T.set(idUser_T);
    }

    public String getRola() {
        return Rola.get();
    }

    public StringProperty rolaProperty() {

        return Rola;
    }

    public void setRola(String value) {

        Rola.set(value);
    }



    public String getPesel() {

        return Pesel.get();
    }

    public StringProperty peselProperty() {

        return Pesel;
    }

    public void setPesel(String value) {

        Pesel.set(value);
    }



    public String getPlec() {

        return Plec.get();
    }

    public StringProperty plecProperty() {

        return Plec;
    }

    public void setPlec(String value) {

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
        out.writeLong(getIdUser_T());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setIdUser(in.readLong());
        setLogin((String) in.readObject());
        setPassword((String) in.readObject());
        setImie((String) in.readObject());
        setNazwisko((String) in.readObject());
        setPlec((String) in.readObject());
        setPesel((String) in.readObject());
        setRola((String) in.readObject());
        setIdUser_T(in.readLong());
    }
}
