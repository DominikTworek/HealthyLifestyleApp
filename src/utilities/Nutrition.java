package utilities;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Nutrition implements Externalizable {
    private final LongProperty ID_nutrition = new SimpleLongProperty();
    private final IntegerProperty calories = new SimpleIntegerProperty();
    private final IntegerProperty protein = new SimpleIntegerProperty();
    private final IntegerProperty carbs = new SimpleIntegerProperty();
    private final IntegerProperty fat = new SimpleIntegerProperty();
    private final IntegerProperty sugars = new SimpleIntegerProperty();
    private final IntegerProperty saturedfat = new SimpleIntegerProperty();
    private final IntegerProperty unsaturedfat = new SimpleIntegerProperty();
    private final LongProperty ID_user = new SimpleLongProperty();

    public int getCalories() {
        return calories.get();
    }

    public IntegerProperty caloriesProperty() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories.set(calories);
    }

    public long getID_nutrition() {
        return ID_nutrition.get();
    }

    public LongProperty ID_nutritionProperty() {
        return ID_nutrition;
    }

    public void setID_nutrition(long ID_nutrition) {
        this.ID_nutrition.set(ID_nutrition);
    }

    public int getProtein() {
        return protein.get();
    }

    public IntegerProperty proteinProperty() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein.set(protein);
    }

    public int getCarbs() {
        return carbs.get();
    }

    public IntegerProperty carbsProperty() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs.set(carbs);
    }

    public int getFat() {
        return fat.get();
    }

    public IntegerProperty fatProperty() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat.set(fat);
    }

    public int getSugars() {
        return sugars.get();
    }

    public IntegerProperty sugarsProperty() {
        return sugars;
    }

    public void setSugars(int sugars) {
        this.sugars.set(sugars);
    }

    public int getSaturedfat() {
        return saturedfat.get();
    }

    public IntegerProperty saturedfatProperty() {
        return saturedfat;
    }

    public void setSaturedfat(int saturedfat) {
        this.saturedfat.set(saturedfat);
    }

    public int getUnsaturedfat() {
        return unsaturedfat.get();
    }

    public IntegerProperty unsaturedfatProperty() {
        return unsaturedfat;
    }

    public void setUnsaturedfat(int unsaturedfat) {
        this.unsaturedfat.set(unsaturedfat);
    }

    public long getID_user() {
        return ID_user.get();
    }

    public LongProperty ID_userProperty() {
        return ID_user;
    }

    public void setID_user(long ID_user) {
        this.ID_user.set(ID_user);
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(getID_nutrition());
        out.writeInt(getCalories());
        out.writeInt(getProtein());
        out.writeInt(getCarbs());
        out.writeInt(getFat());
        out.writeInt(getSugars());
        out.writeInt(getSaturedfat());
        out.writeInt(getUnsaturedfat());
        out.writeLong(getID_user());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setID_nutrition(in.readLong());
        setCalories(in.readInt());
        setProtein(in.readInt());
        setCarbs(in.readInt());
        setFat(in.readInt());
        setSugars(in.readInt());
        setSaturedfat(in.readInt());
        setUnsaturedfat(in.readInt());
        setID_user(in.readLong());
    }
}
