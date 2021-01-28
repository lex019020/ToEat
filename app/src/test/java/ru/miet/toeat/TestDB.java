package ru.miet.toeat;

import org.junit.Before;
import org.junit.Test;

import ru.miet.toeat.infoStorage.DataBase;
import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestDB {
    private Meal breakfast = new Meal();
    private Meal tiffin = new Meal();//same as lunch
    private Meal dinner = new Meal();
    private Meal anSnack = new Meal();
    private Meal supper = new Meal();
    private Meal snack = new Meal();

    boolean badError = false;

    @Before
    public void genMeal1(){
        badError = false;
        breakfast = new Meal();
        try {
            breakfast.setRating(0);
            breakfast.setName("breakfast");
            breakfast.setType("breakfast");
            breakfast.setCalories(0);
            breakfast.setCarbs(0);
            breakfast.setFat(0);
            breakfast.setProteins(0);
        } catch (FormatException e) {
            e.printStackTrace();
            assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
    }
    @Before
    public void genMeal2(){
        badError = false;
        tiffin = new Meal();//same as lunch
        try {
            tiffin.setRating(0);
            tiffin.setName("tiffin");
            tiffin.setType("tiffin");
            tiffin.setCalories(0);
            tiffin.setCarbs(0);
            tiffin.setFat(0);
            tiffin.setProteins(0);
        } catch (FormatException e) {
            e.printStackTrace();
            assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
    }
    @Before
    public void genMeal3(){
        badError = false;
        dinner = new Meal();
        try {
            dinner.setRating(0);
            dinner.setName("dinner");
            dinner.setType("dinner");
            dinner.setCalories(0);
            dinner.setCarbs(0);
            dinner.setFat(0);
            dinner.setProteins(0);
        } catch (FormatException e) {
            e.printStackTrace();
            assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
    }
    @Before
    public void genMeal4(){
        badError = false;
        anSnack = new Meal();
        try {
            anSnack.setRating(0);
            anSnack.setName("anSnack");
            anSnack.setType("anSnack");
            anSnack.setCalories(0);
            anSnack.setCarbs(0);
            anSnack.setFat(0);
            anSnack.setProteins(0);
        } catch (FormatException e) {
            e.printStackTrace();
            assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
    }
    @Before
    public void genMeal5(){
        badError = false;
        supper = new Meal();
        try {
            supper.setRating(0);
            supper.setName("supper");
            supper.setType("supper");
            supper.setCalories(0);
            supper.setCarbs(0);
            supper.setFat(0);
            supper.setProteins(0);
        } catch (FormatException e) {
            e.printStackTrace();
            assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
    }
    @Before
    public void genMeal6(){
        badError = false;
        snack = new Meal();
        try {
            snack.setRating(0);
            snack.setName("snack");
            snack.setType("snack");
            snack.setCalories(0);
            snack.setCarbs(0);
            snack.setFat(0);
            snack.setProteins(0);
        } catch (FormatException e) {
            e.printStackTrace();
            assertTrue(true);
        } catch (Exception e){
            e.printStackTrace();
            badError = true;
        }
    }

    @Test
    public  void testBase(){
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
