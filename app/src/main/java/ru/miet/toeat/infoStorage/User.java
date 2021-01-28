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
import java.time.LocalDate;
import java.time.ZoneId;
import java.lang.reflect.Array;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Locale;

import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Menu;
import ru.miet.toeat.model.Nutrition;
import ru.miet.toeat.model.Product;
import ru.miet.toeat.tools.Tools;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class User extends Nutrition {
	private static final long serialVersionUID = 1L;

	private static User user = null;

	private User() {
		super();
	}
	private User(String dataFilePath) {
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

	public enum Lifestyle{
		none(0),
		small(1),
		medium(2),
		intensive(3),
		max(4);

		private final int value;
		Lifestyle(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		public String getString(int value){
			if(LifestyleStrings.length > value)
				return LifestyleStrings[value];
			return "Wrong";
		}
	}

	public static String[] LifestyleStrings = {
			"Сидячий",
			"Подвижный",
			"Активный",
			"Спортивный",
			"Максимальный"};

	public static float[] LifestyleCoefficients = {
			1.2f,
			1.38f,
			1.46f,
			1.64f,
			1.9f
	};

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
			if(m.getName().equals(name)) {
				favorMeals.remove(m);
			}
		}
	}
	public void addUnfavorMeal(Meal m) {
		unfavorMeals.add(m);
	}
	public void removeUnfavorMeal(String name) {
		for(Meal m : unfavorMeals) {
			if(m.getName().equals(name)) {
				unfavorMeals.remove(m);
			}
		}
	}
	public void addFavorProduct(Product p) {
		favorProducts.add(p);
	}
	public void removeFavorProduct(String name) {
		for(Product p : favorProducts) {
			if(p.getName().equals(name)) {
				favorProducts.remove(p);
			}
		}
	}
	public void addUnfavorProduct(Product p) {
		unfavorProducts.add(p);
	}
	public void removeUnfavorProduct(String name) {
		for(Product p : unfavorProducts) {
			if(p.getName().equals(name)) {
				unfavorProducts.remove(p);
			}
		}
	}
	public void addMealToHistory(Meal m) {
		mealHistory.add(m);
	}
	public void removeMealFromHistory(String name) {
		for(Meal m : mealHistory) {
			if(m.getName().equals(name)) {
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
		if(Tools.isInRange(height, 0, 300))
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
		cal.add(YEAR, -100);
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

	//TODO: add more logic, define special way
	public Menu genMenu() {
		Menu newMenu = genRandMenu();


		return newMenu;
	}

	public Menu genRandMenu() {
		Menu newMenu = new Menu();
		newMenu.setBreakfast(getMealByType(newMenu, "Завтрак", false));
		newMenu.setSnack(getMealByType(newMenu, "Перекус", false));
		newMenu.setSupper(getMealByType(newMenu, "Ужин", false));
		newMenu.setDinner(getMealByType(newMenu, "Обед", false));
		newMenu.setAnSnack(getMealByType(newMenu, "Перекус", false));
		newMenu.setTiffin(getMealByType(newMenu, "Второй завтрак", false));
		return newMenu;
	}

	//checkrepeats нужно.
	private Meal getMealByType(Menu newMenu, String type, boolean checkRepeats) {
		Random rand = new Random();
		Meal meal;
		while(true) {
			meal = DataBase.getInstance().getMeals().get(rand.nextInt(DataBase.getInstance().getMeals().size()));
			//тип еды
			if(meal.getType().equals(type)) {
				//отсутствие повторов
				if (!checkRepeats || !isInList(newMenu, meal)) {
					break;
				}
			}
		}
		return meal;
	}

	//TODO implement
	private boolean mealIsUnliked () {
		return true;
	}

	private boolean isInList (Menu cMenu, Meal meal) {
		return (meal.equals(cMenu.getAnSnack()) || meal.equals(cMenu.getBreakfast()) ||
				meal.equals(cMenu.getDinner()) || meal.equals(cMenu.getSnack()) ||
				meal.equals(cMenu.getSupper()) || meal.equals(cMenu.getTiffin()));
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

	public void load(){
		File f = new File(dataFilePath);
		if(f.exists() && !f.isDirectory()) {
			try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(f))) {
				user = (User) in.readObject();
			}
			catch(EOFException ignored) {

			}
			catch(ClassNotFoundException | IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		else {
			Tools.file.createFile(dataFilePath, "");
		}
	}

	public boolean calculateCalories(){
		int years = getDiffYears(birthDate, new Date());

		if(weight < 30 || height < 120 || years < 10)
			return false;

		float kcal = weight*10f + height*6.25f - years*5f;
		if(sex)
			kcal += 5;
		else
			kcal -= 161;

		kcal *= LifestyleCoefficients[getLifestyle().getValue()];

		try {
			setCalories(kcal);
		} catch (FormatException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	public static int getDiffYears(Date first, Date last) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(last);
		int diff = b.get(YEAR) - a.get(YEAR);
		if (a.get(MONTH) > b.get(MONTH) ||
				(a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
			diff--;
		}
		return diff;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance(Locale.US);
		cal.setTime(date);
		return cal;
	}
}

