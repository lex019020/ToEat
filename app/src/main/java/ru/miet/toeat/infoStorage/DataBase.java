package ru.miet.toeat.infoStorage;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import ru.miet.toeat.model.Meal;
import ru.miet.toeat.tools.Tools;

public class DataBase implements Serializable{
    private static final long serialVersionUID = 1L;

    private static DataBase base = null;
    private String filePath;
    private ArrayList<Meal> meals = new ArrayList<>();

    private DataBase() {
        super();
    }
    private DataBase(String dbFilePath) {
        super();

        this.filePath = dbFilePath;
    }

    public ArrayList<Meal> getMeals() {
        return meals;
    }
    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
    public void addMeal(Meal m) {
        meals.add(m);
    }

    public static DataBase getInstance() {
        if(base == null) {
            base = new DataBase();
        }
        return base;
    }
    public static DataBase getInstance(String dbFilePath) {
        if(base == null) {
            base = new DataBase(dbFilePath);
        }
        return base;
    }

    public String getDbFilePath() {
        return filePath;
    }
    public void setDbFilePath(String dbFilePath) {
        this.filePath = dbFilePath;
    }

    public void upload(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeObject(base);
        }
        catch(IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public void load(){
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory()) {
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
                base = (DataBase)in.readObject();
            }
            catch(EOFException e) {

            }
            catch(ClassNotFoundException e) {
                e.printStackTrace();
                System.exit(0);
            }
            catch(IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        else {
            Tools.file.createFile(filePath, "");
        }
    }

    //TODO: implement this
    public void updateUserData(User u) {

    }
}
