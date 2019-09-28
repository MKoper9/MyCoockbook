package model;

import lombok.Data;

@Data
public class Ingredient {

    private static int id;
    private int user_id;
    private String name;
    private int granity;
    private int calories;

    public Ingredient(String name, int granity, int calories) {
        this.name = name;
        this.granity = granity;
        this.calories = calories;
        this.user_id=id;
        id++;
    }

    @Override
    public String toString() {
        return  name +" ( " +
                 + granity +" g, "+
                + calories +" cal) ";
    }
}
