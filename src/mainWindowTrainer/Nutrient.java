package mainWindowTrainer;

//new Nutrient(name, getNumber()*procentage)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

abstract class Nutrient {

    public void add(Nutrient nutrient){}

    public void remove(Nutrient nutrient){}

    public double getNumber(){
        return 0;
    }

    public String getName(){
        return null;
    }

    public Nutrient getChild(int nutrientIndex){
        return null;
    }
}

class ComplexNutrient extends Nutrient {

    String complexName;
    double numberOfNutrient;
    ArrayList<Nutrient> nutrients = new ArrayList<Nutrient>();

    public ComplexNutrient(String name, double number){
        complexName=name;
        numberOfNutrient=number;
    }

    @Override
    public void add(Nutrient nutrient){
        nutrients.add(nutrient);
    }

    @Override
    public void remove(Nutrient nutrient){
        nutrients.remove(nutrient);
    }

    @Override
    public double getNumber(){
        return numberOfNutrient;
    }

    @Override
    public String getName(){
        return complexName;
    }

    public Nutrient getChild(int nutrientIndex){
        if(nutrientIndex==nutrients.size())
            return null;
        else
            return  nutrients.get(nutrientIndex);
    }
}

class BasicNutrient extends Nutrient{

    String basicName;
    double numberOfNutrient;

    public BasicNutrient(String name, double number){
        basicName=name;
        numberOfNutrient=number;
    }

    @Override
    public double getNumber(){
        return numberOfNutrient;
    }

    public String getName() {
        return basicName;
    }

}

