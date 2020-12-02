package ru.miet.toeat.infoStorage;

import java.lang.reflect.Array;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Menu;
import ru.miet.toeat.model.Nutrition;
import ru.miet.toeat.model.Product;
import ru.miet.toeat.tools.Tools;

public class User extends Nutrition {
	private static final long serialVersionUID = 1L;

	//TODO: add more categories
	public enum Lifestyle{
		seat,
		active,
	}

	String dataFilePath;

	private String name = "noname";
	private float weight = 0;
	private float height = 0;
	private boolean sex = false;
	private Date birthDate = new Date();
	private Lifestyle lifestyle = Lifestyle.active;
	private Menu menu = new Menu();

	private ArrayList<String> preferences = new ArrayList<>();
	private ArrayList<Meal> favorMeals = new ArrayList<>();
	private ArrayList<Meal> unfavorMeals = new ArrayList<>();
	private ArrayList<Product> favorProducts = new ArrayList<>();
	private ArrayList<Product> unfavorProducts = new ArrayList<>();
	private ArrayList<Meal> mealHistory = new ArrayList<>();

	public User(String userDataFilePath) {
		super();
		dataFilePath = userDataFilePath;
	}
	public User(String name, float weight, float height, boolean sex, Date birthDate, Lifestyle lifestyle) throws FormatException{
		super();
		setName(name);
		setWeight(weight);
		setHeight(height);
		setSex(sex);
		setBirthDate(birthDate);
		setLifestyle(lifestyle);
	}

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

	//TODO: add more logic, define special way
	public void genMenu() {
        Random rand = new Random();
        int[] filling = new int[6];
        // должно работать
        // но в любом случае позже всё исправлю... ДК.
        for(int i: filling) { i = -1; }
        while(true) {
        	boolean flag = false;
        	int num = rand.nextInt() % DataBase.getInstance().getMeals().size();
        	for(int i: filling) {
        		if (i == num) {flag = true;}
			}
        	if(flag) continue;
        	for(int i = 0; i < 6; i++) {
				if (filling[i] == -1) {
					// adding 10 is necessary, when we have less than 6 meals, to let repeats to be
					filling[i] = num + (DataBase.getInstance().getMeals().size() < 6 ? 10 : 0);
					switch (i) {
						case 0:
							menu.setBreakfast(DataBase.getInstance().getMeals().get(num));
							break;
						case 1:
							menu.setTiffin(DataBase.getInstance().getMeals().get(num));
							break;
						case 2:
							menu.setAnSnack(DataBase.getInstance().getMeals().get(num));
							break;
						case 3:
							menu.setDinner(DataBase.getInstance().getMeals().get(num));
							break;
						case 4:
							menu.setSupper(DataBase.getInstance().getMeals().get(num));
							break;
						case 5:
							menu.setSnack(DataBase.getInstance().getMeals().get(num));
							break;
						default:
							break;
					}
				}
			}

        	for(int i: filling) {
				if (i == -1) flag = true;
			}
        	if(!flag)
        		break;
		}
	}

	public void upload(){
		Tools.file.serialize(dataFilePath,
				name, weight, height, sex,
				birthDate, lifestyle, menu, preferences,
				favorMeals, unfavorMeals,
				favorProducts, unfavorProducts,
				mealHistory);
	}

	@SuppressWarnings("unchecked")
	public void load(){
		ArrayList<Object> data = Tools.file.deserialize(dataFilePath);
		name = (String)data.get(0);
		weight = (float)data.get(1);
		height = (float)data.get(2);
		sex = (boolean)data.get(3);

		birthDate = (Date)data.get(4);
		lifestyle = (Lifestyle)data.get(5);
		menu = (Menu)data.get(6);
		preferences = (ArrayList<String>)data.get(7);

		favorMeals = (ArrayList<Meal>)data.get(8);
		unfavorMeals = (ArrayList<Meal>)data.get(9);

		favorProducts = (ArrayList<Product>)data.get(10);
		unfavorProducts = (ArrayList<Product>)data.get(11);

		mealHistory = (ArrayList<Meal>)data.get(12);
	}
}

