package utilities.Calendar;

import javafx.beans.property.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

public class CalendarEvent implements Externalizable {
    private final LongProperty IdEvent = new SimpleLongProperty();
    private final LongProperty IdUser = new SimpleLongProperty();
    private final IntegerProperty day = new SimpleIntegerProperty();
    private final IntegerProperty month = new SimpleIntegerProperty();
    private final IntegerProperty year = new SimpleIntegerProperty();
    private final IntegerProperty hour = new SimpleIntegerProperty();
    private final StringProperty title =  new SimpleStringProperty();
    private final StringProperty description =  new SimpleStringProperty();

    public long getIdEvent() {
        return IdEvent.get();
    }

    public LongProperty idEventProperty() {
        return IdEvent;
    }

    public void setIdEvent(long idEvent) {
        this.IdEvent.set(idEvent);
    }

    public long getIdUser() {
        return IdUser.get();
    }

    public LongProperty idUserProperty() {
        return IdUser;
    }

    public void setIdUser(long idUser) {
        this.IdUser.set(idUser);
    }

    public int getDay() {
        return day.get();
    }

    public IntegerProperty dayProperty() {
        return day;
    }

    public void setDay(int day) {
        this.day.set(day);
    }

    public int getMonth() {
        return month.get();
    }

    public IntegerProperty monthProperty() {
        return month;
    }

    public void setMonth(int month) {
        this.month.set(month);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public int getHour() {
        return hour.get();
    }

    public IntegerProperty hourProperty() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour.set(hour);
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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getIdEvent());
        out.writeLong(getIdUser());
        out.writeInt(getDay());
        out.writeInt(getMonth());
        out.writeInt(getYear());
        out.writeInt(getHour());
        out.writeObject(getTitle());
        out.writeObject(getDescription());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setIdEvent(in.readLong());
        setIdUser(in.readLong());
        setDay(in.readInt());
        setMonth(in.readInt());
        setYear(in.readInt());
        setHour(in.readInt());
        setTitle((String)in.readObject());
        setDescription((String)in.readObject());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CalendarEvent that = (CalendarEvent) o;
        return Objects.equals(IdEvent, that.IdEvent) &&
                Objects.equals(IdUser, that.IdUser) &&
                Objects.equals(day, that.day) &&
                Objects.equals(month, that.month) &&
                Objects.equals(year, that.year) &&
                Objects.equals(hour, that.hour) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(IdEvent, IdUser, day, month, year, hour, title, description);
    }

    @Override
    public String toString() {
        if (getIdEvent() == -1L){
            return title.get();
        } else return hour.get()+":00 "+title.get();
    }
}
