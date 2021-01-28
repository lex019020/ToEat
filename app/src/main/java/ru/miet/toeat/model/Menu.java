package ru.miet.toeat.model;

import java.lang.reflect.Field;
import java.util.ArrayList;

import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Nutrition;

public class Menu extends Nutrition {
	private static final long serialVersionUID = 1L;

	private Meal breakfast = new Meal();
	private Meal tiffin = new Meal();//same as lunch
	private Meal dinner = new Meal();
	private Meal anSnack = new Meal();
	private Meal supper = new Meal();
	private Meal snack = new Meal();

	public Menu() {
		super();
	}

	public Menu(Meal breakfast, Meal tiffin, Meal dinner, Meal anSnack, Meal supper, Meal snack) {
		super();
		setBreakfast(breakfast);
		setTiffin(tiffin);
		setDinner(dinner);
		setSnack(anSnack);
		setSupper(supper);
		setSnack(snack);
	}

	public Meal getBreakfast() {
		return breakfast;
	}

	public void setBreakfast(Meal breakfast) {
		this.breakfast = breakfast;
	}

	public Meal getTiffin() {
		return tiffin;
	}

	public void setTiffin(Meal tiffin) {
		this.tiffin = tiffin;
	}

	public Meal getDinner() {
		return dinner;
	}

	public void setDinner(Meal dinner) {
		this.dinner = dinner;
	}

	public Meal getAnSnack() {
		return anSnack;
	}

	public void setAnSnack(Meal anSnack) {
		this.anSnack = anSnack;
	}

	public Meal getSupper() {
		return supper;
	}

	public void setSupper(Meal supper) {
		this.supper = supper;
	}

	public Meal getSnack() {
		return snack;
	}

	public void setSnack(Meal snack) {
		this.snack = snack;
	}

	public void calcNutrition() {
		proteins = getBreakfast().getProteins() + getTiffin().getProteins() +
				getDinner().getProteins() + getAnSnack().getProteins() +
				getSupper().getProteins() + getSnack().getProteins();

		fat = getBreakfast().getFat() + getTiffin().getFat() +
				getDinner().getFat() + getAnSnack().getFat() +
				getSupper().getFat() + getSnack().getFat();

		carbs = getBreakfast().getCarbs() + getTiffin().getCarbs() +
				getDinner().getCarbs() + getAnSnack().getCarbs() +
				getSupper().getCarbs() + getSnack().getCarbs();

		calories = getBreakfast().getCalories() + getTiffin().getCalories() +
				getDinner().getCalories() + getAnSnack().getCalories() +
				getSupper().getCalories() + getSnack().getCalories();
	}

	public void updateDates() {
		breakfast.updateDateOfLastDispense();
		tiffin.updateDateOfLastDispense();
		dinner.updateDateOfLastDispense();
		snack.updateDateOfLastDispense();
		supper.updateDateOfLastDispense();
		anSnack.updateDateOfLastDispense();
	}

	public boolean equals(Menu compare) {
		boolean ret = true;
		if (!breakfast.equals(compare.breakfast)) {
			ret = false;
		}
		if (!tiffin.equals(compare.tiffin)) {
			ret = false;
		}
		if (!dinner.equals(compare.dinner)) {
			ret = false;
		}
		if (!anSnack.equals(compare.anSnack)) {
			ret = false;
		}
		if (supper != compare.supper) {
			ret = false;
		}
		if (!snack.equals(compare.snack)) {
			ret = false;
		}
		return ret;
	}

	public double getAvgRating() {
		double result = getBreakfast().getRating() + getDinner().getRating() +
				getAnSnack().getRating() + getSnack().getRating() +
				getSupper().getRating() + getTiffin().getRating();

		//Проверка на нахождение в списке избранного
		if(User.getInstance().getFavorMeals().contains(getBreakfast())) {
			result += 3;
		}
		if(User.getInstance().getFavorMeals().contains(getTiffin())) {
			result += 3;
		}
		if(User.getInstance().getFavorMeals().contains(getDinner())) {
			result += 3;
		}
		if(User.getInstance().getFavorMeals().contains(getAnSnack())) {
			result += 3;
		}
		if(User.getInstance().getFavorMeals().contains(getSnack())) {
			result += 3;
		}
		if(User.getInstance().getFavorMeals().contains(getSupper())) {
			result += 3;
		}

		//проверка на любимые продукты
		ArrayList<Product> allProducts = new ArrayList<>();
		for (Ingredient in: getBreakfast().getIngredients()) {
			allProducts.add(in.getProduct());
		}
		for (Ingredient in: getTiffin().getIngredients()) {
			allProducts.add(in.getProduct());
		}
		for (Ingredient in: getDinner().getIngredients()) {
			allProducts.add(in.getProduct());
		}
		for (Ingredient in: getAnSnack().getIngredients()) {
			allProducts.add(in.getProduct());
		}
		for (Ingredient in: getSnack().getIngredients()) {
			allProducts.add(in.getProduct());
		}
		for (Ingredient in: getSupper().getIngredients()) {
			allProducts.add(in.getProduct());
		}
		int size = allProducts.size();
		allProducts.removeAll(User.getInstance().getFavorProducts());
		result += (allProducts.size() - size);

		return result/6;
	}

	public void setMeal(Meal m) {
		if(m.getType().equals("Завтрак")) {
			this.setBreakfast(m);
			return;
		}
		if(m.getType().equals("Обед")) {
			this.setDinner(m);
			return;
		}
		if(m.getType().equals("Ужин")) {
			this.setSupper(m);
			return;
		}
		if(m.getType().equals("Второй завтрак")) {
			this.setTiffin(m);
			return;
		}
		if(m.getType().equals("Перекус")) {
			this.setSnack(m);
		}
	}
}
