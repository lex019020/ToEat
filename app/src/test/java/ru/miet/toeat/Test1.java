package ru.miet.toeat;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

import ru.miet.toeat.infoStorage.DataBase;
import ru.miet.toeat.model.*;


public class Test1 {
    @Test
    public void genMenu(){
        boolean badError = false;
        Meal breakfast = new Meal();
        try {
            breakfast.setRating(0);
            breakfast.setName("breakfast");
            breakfast.setType("breakfast");
            breakfast.setCalories(0);
            breakfast.setCarbs(0);
            breakfast.setFat(0);
            breakfast.setProteins(0);
        } catch (FormatException e) {
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
        assertFalse(badError);
        assertEquals(breakfast.getRating(), 0, 0);
        assertEquals(breakfast.getCalories(), 0, 0);
        assertEquals(breakfast.getCarbs(), 0, 0);
        assertEquals(breakfast.getFat(), 0, 0);
        assertEquals(breakfast.getProteins(), 0, 0);
        assertEquals("breakfast", breakfast.getName());
        assertEquals("breakfast", breakfast.getType());

        badError = false;
        Meal tiffin = new Meal();//same as lunch
        try {
            tiffin.setRating(0);
            tiffin.setName("tiffin");
            tiffin.setType("tiffin");
            tiffin.setCalories(0);
            tiffin.setCarbs(0);
            tiffin.setFat(0);
            tiffin.setProteins(0);
        } catch (FormatException e) {

        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
        assertFalse(badError);
        assertEquals(tiffin.getRating(), 0, 0);
        assertEquals(tiffin.getCalories(), 0, 0);
        assertEquals(tiffin.getCarbs(), 0, 0);
        assertEquals(tiffin.getFat(), 0, 0);
        assertEquals(tiffin.getProteins(), 0, 0);
        assertEquals("tiffin", tiffin.getName());
        assertEquals("tiffin", tiffin.getType());

        badError = false;
        Meal dinner = new Meal();
        try {
            dinner.setRating(0);
            dinner.setName("dinner");
            dinner.setType("dinner");
            dinner.setCalories(0);
            dinner.setCarbs(0);
            dinner.setFat(0);
            dinner.setProteins(0);
        } catch (FormatException e) {
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
        assertFalse(badError);
        assertEquals(dinner.getRating(), 0, 0);
        assertEquals(dinner.getCalories(), 0, 0);
        assertEquals(dinner.getCarbs(), 0, 0);
        assertEquals(dinner.getFat(), 0, 0);
        assertEquals(dinner.getProteins(), 0, 0);
        assertEquals("dinner", dinner.getName());
        assertEquals("dinner", dinner.getType());

        badError = false;
        Meal anSnack = new Meal();
        try {
            anSnack.setRating(0);
            anSnack.setName("anSnack");
            anSnack.setType("anSnack");
            anSnack.setCalories(0);
            anSnack.setCarbs(0);
            anSnack.setFat(0);
            anSnack.setProteins(0);
        } catch (FormatException e) {
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
        assertFalse(badError);
        assertEquals(anSnack.getRating(), 0, 0);
        assertEquals(anSnack.getCalories(), 0, 0);
        assertEquals(anSnack.getCarbs(), 0, 0);
        assertEquals(anSnack.getFat(), 0, 0);
        assertEquals(anSnack.getProteins(), 0, 0);
        assertEquals("anSnack", anSnack.getName());
        assertEquals("anSnack", anSnack.getType());

        badError = false;
        Meal supper = new Meal();
        try {
            supper.setRating(0);
            supper.setName("supper");
            supper.setType("supper");
            supper.setCalories(0);
            supper.setCarbs(0);
            supper.setFat(0);
            supper.setProteins(0);
        } catch (FormatException e) {
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
        assertFalse(badError);
        assertEquals(supper.getRating(), 0, 0);
        assertEquals(supper.getCalories(), 0, 0);
        assertEquals(supper.getCarbs(), 0, 0);
        assertEquals(supper.getFat(), 0, 0);
        assertEquals(supper.getProteins(), 0, 0);
        assertEquals("supper", supper.getName());
        assertEquals("supper", supper.getType());

        badError = false;
        Meal snack = new Meal();
        try {
            snack.setRating(0);
            snack.setName("snack");
            snack.setType("snack");
            snack.setCalories(0);
            snack.setCarbs(0);
            snack.setFat(0);
            snack.setProteins(0);
        } catch (FormatException e) {
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
        assertFalse(badError);
        assertEquals(snack.getRating(), 0, 0);
        assertEquals(snack.getCalories(), 0, 0);
        assertEquals(snack.getCarbs(), 0, 0);
        assertEquals(snack.getFat(), 0, 0);
        assertEquals(snack.getProteins(), 0, 0);
        assertEquals("snack", snack.getName());
        assertEquals("snack", snack.getType());

        DataBase.getInstance("data.txt");
        DataBase.getInstance().addMeal(breakfast);
        DataBase.getInstance().addMeal(tiffin);
        DataBase.getInstance().addMeal(dinner);
        DataBase.getInstance().addMeal(anSnack);
        DataBase.getInstance().addMeal(supper);
        DataBase.getInstance().addMeal(snack);

        Menu menu = new Menu(
                DataBase.getInstance().getMeals().get(0),
                DataBase.getInstance().getMeals().get(1),
                DataBase.getInstance().getMeals().get(2),
                DataBase.getInstance().getMeals().get(3),
                DataBase.getInstance().getMeals().get(4),
                DataBase.getInstance().getMeals().get(5));

        assertEquals(DataBase.getInstance().getMeals().get(0), breakfast);
        assertEquals(DataBase.getInstance().getMeals().get(1), tiffin);
        assertEquals(DataBase.getInstance().getMeals().get(2), dinner);
        assertEquals(DataBase.getInstance().getMeals().get(3), anSnack);
        assertEquals(DataBase.getInstance().getMeals().get(4), supper);
        assertEquals(DataBase.getInstance().getMeals().get(5), snack);
    }
}
