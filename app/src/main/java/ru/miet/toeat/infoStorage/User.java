package ru.miet.toeat.infoStorage;

import android.content.Context;
import java.io.EOFException;
import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Menu;
import ru.miet.toeat.model.Nutrition;
import ru.miet.toeat.model.Product;
import ru.miet.toeat.tools.Tools;

public class User extends Nutrition {
	private static final long serialVersionUID = 1L;

	private static User user = null;

	private User() {
		super();
	}
	private User(String dataFilePath) throws FormatException{
		super();
		setDataFilePath(dataFilePath);
	}
	private User(String dataFilePath, String name,
				 float weight, float height,
				 boolean sex, Date birthDate,
				 Lifestyle lifestyle) throws FormatException{
		super();
		setDataFilePath(dataFilePath);
		setName(name);
		setWeight(weight);
		setHeight(height);
		setSex(sex);
		setBirthDate(birthDate);
		setLifestyle(lifestyle);
	}

	public static User getInstance() {
		if(user == null) {
			user = new User();
		}
		return user;
	}
	public static User getInstance(String dataFilePath) throws FormatException {
		user = new User(dataFilePath);
		return user;
	}
	public static User getInstance(String dataFilePath, String name,
								   float weight, float height,
								   boolean sex, Date birthDate,
								   Lifestyle lifestyle) throws FormatException {
		user = new User(dataFilePath, name, weight, height, sex, birthDate, lifestyle);
		return user;
	}

	//TODO: add more categories
	public enum Lifestyle{
		none,
		small,
		medium,
		intensive,
		max
	}

	public static String[] LifestyleStrings = {
			"Сидячий",
			"Подвижный",
			"Активный",
			"Спортивный",
			"Максимальный"};

	private String dataFilePath;

	private String name = "noname";
	private float weight = 0;
	private float height = 0;
	private boolean sex = false;
	private Date birthDate = new Date();
	private Lifestyle lifestyle = Lifestyle.small;
	private Menu menu = new Menu();

	private ArrayList<String> preferences = new ArrayList<>();
	private ArrayList<Meal> favorMeals = new ArrayList<>();
	private ArrayList<Meal> unfavorMeals = new ArrayList<>();
	private ArrayList<Product> favorProducts = new ArrayList<>();
	private ArrayList<Product> unfavorProducts = new ArrayList<>();
	private ArrayList<Meal> mealHistory = new ArrayList<>();

	public void addPreference(String c) {
		preferences.add(c);
	}
	public void removePreference(String name) {
		for(String c : preferences) {
			if(c.equals(name)) {
				preferences.remove(c);
			}
		}
	}
	public void addFavorMeal(Meal c) {
		favorMeals.add(c);
	}
	public void removeFavorMeal(String name) {
		for(Meal m : favorMeals) {
			if(m.getName() == name) {
				favorMeals.remove(m);
			}
		}
	}
	public void addUnfavorMeal(Meal m) {
		unfavorMeals.add(m);
	}
	public void removeUnfavorMeal(String name) {
		for(Meal m : unfavorMeals) {
			if(m.getName() == name) {
				unfavorMeals.remove(m);
			}
		}
	}
	public void addFavorProduct(Product p) {
		favorProducts.add(p);
	}
	public void removeFavorProduct(String name) {
		for(Product p : favorProducts) {
			if(p.getName() == name) {
				favorProducts.remove(p);
			}
		}
	}
	public void addUnfavorProduct(Product p) {
		unfavorProducts.add(p);
	}
	public void removeUnfavorProduct(String name) {
		for(Product p : unfavorProducts) {
			if(p.getName() == name) {
				unfavorProducts.remove(p);
			}
		}
	}
	public void addMealToHistory(Meal m) {
		mealHistory.add(m);
	}
	public void removeMealFromHistory(String name) {
		for(Meal m : mealHistory) {
			if(m.getName() == name) {
				mealHistory.remove(m);
			}
		}
	}

	public String getName() {
		return name;
	}
	public void setName(String name) throws FormatException {
		if(Tools.isCorrectFormat(name, ""))
			this.name = name;
		else
			throw new FormatException("Wrong set name in User");
	}
	public float getWeight(){
		return weight;
	}
	public void setWeight(float weight) throws FormatException {
		if(Tools.isInRange(weight, 0, 300))
			this.weight = weight;
		else
			throw new FormatException("Wrong set weight in User");
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) throws FormatException {
		if(Tools.isInRange(height, 0, 3))
			this.height = height;
		else
			throw new FormatException("Wrong set height in User");
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) throws FormatException {
		Date currDate = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(currDate);
		cal.add(Calendar.YEAR, -100);
		Date dateBefore100Years = cal.getTime();

		if(Tools.isInRange(birthDate, dateBefore100Years, currDate))
			this.birthDate = birthDate;
		else
			throw new FormatException("Wrong birth date in User");
	}
	public Lifestyle getLifestyle() {
		return lifestyle;
	}
	public void setLifestyle(Lifestyle lifestyle) {
		this.lifestyle = lifestyle;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public ArrayList<String> getPreferences() {
		return preferences;
	}
	public void setPreferences(ArrayList<String> preferences) {
		this.preferences = preferences;
	}
	public ArrayList<Meal> getFavorMeals() {
		return favorMeals;
	}
	public void setFavorMeals(ArrayList<Meal> favorMeals) {
		this.favorMeals = favorMeals;
	}
	public ArrayList<Meal> getUnfavorMeals() {
		return unfavorMeals;
	}
	public void setUnfavorMeals(ArrayList<Meal> unfavorMeals) {
		this.unfavorMeals = unfavorMeals;
	}
	public ArrayList<Product> getFavorProducts() {
		return favorProducts;
	}
	public void setFavorProducts(ArrayList<Product> favorProducts) {
		this.favorProducts = favorProducts;
	}
	public ArrayList<Product> getUnfavorProducts() {
		return unfavorProducts;
	}
	public void setUnfavorProducts(ArrayList<Product> unfavorProducts) {
		this.unfavorProducts = unfavorProducts;
	}
	public ArrayList<Meal> getMealHistory() {
		return mealHistory;
	}
	public void setMealHistory(ArrayList<Meal> mealHistory) {
		this.mealHistory = mealHistory;
	}
	public String getDataFilePath() {
		return dataFilePath;
	}
	public void setDataFilePath(String dataFilePath) {
		this.dataFilePath = dataFilePath;
	}

	//TODO: implement this
	public void genMenu() {

	}

	public void upload(){
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFilePath))){
			oos.writeObject(user);
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@SuppressWarnings("unchecked")
	public void load(){
		File f = new File(dataFilePath);
		if(f.exists() && !f.isDirectory()) {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
				user = (User) in.readObject();
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
			Tools.file.createFile(dataFilePath, "");
		}
	}
}

