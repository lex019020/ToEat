package ru.miet.toeat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Objects;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.DataBase;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.ui.ingredients.IngridientsActivity;

public class DishViewActivity extends AppCompatActivity implements View.OnClickListener, RatingBar.OnRatingBarChangeListener {


    private RatingBar ratingBar;
    private ImageView iv_like;
    private ImageView iv_pic;
    private Button recBtn;
    private Button ingBtn;
    private boolean is_fav;
    private Meal meal;
    private TextView tv_prot;
    private TextView tv_fat;
    private TextView tv_carb;
    private TextView tv_kcal;
    private TextView tv_desc;
    private boolean ratingChangedInCode = true;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_view);
        Meal tmeal = (Meal) getIntent().getSerializableExtra("meal");
        meal = findMeal(tmeal);
        setTitle(meal.getName());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        is_fav = false;

        iv_like = findViewById(R.id.iv_fav);
        recBtn = findViewById(R.id.btn_watch_recepie);
        ingBtn = findViewById(R.id.btn_watch_ingredients);
        iv_pic = findViewById(R.id.iv_dish_pic);
        tv_prot = findViewById(R.id.tv_prot);
        tv_fat = findViewById(R.id.tv_fat);
        tv_carb = findViewById(R.id.tv_carb);
        tv_kcal = findViewById(R.id.tv_kcal);
        tv_desc = findViewById(R.id.tv_description);
        ratingBar = findViewById(R.id.ratingBar);


        iv_like.setOnClickListener(this);
        recBtn.setOnClickListener(this);
        ingBtn.setOnClickListener(this);
        ratingBar.setOnRatingBarChangeListener(this);

        if(isNetworkConnected()){
            Picasso.get()
                    .load(meal.getImage())
                    .into(iv_pic);
        }

        tv_prot.setText("Б: " + new DecimalFormat("#.#").format(meal.getProteins()));
        tv_carb.setText("У: " + new DecimalFormat("#.#").format(meal.getCarbs()));
        tv_fat.setText("Ж: " + new DecimalFormat("#.#").format(meal.getFat()));
        tv_kcal.setText("ККал: " + new DecimalFormat("#.#").format(meal.getCalories()));
        tv_desc.setText(meal.getDescription());

        boolean found = false;
        for (Meal m:
             User.getInstance().getFavorMeals()) {
            if(m.getName().equals(meal.getName())){
                found = true;
                break;
            }
        }
        if(found){
            iv_like.setImageResource(R.drawable.ic_favorite);
            is_fav = true;
        }
        else{
            iv_like.setImageResource(R.drawable.ic_favorite_empty);
            is_fav = false;
        }

            ratingBar.setRating(meal.getRating());
        ratingChangedInCode = false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_fav:
                is_fav = !is_fav;
                if(is_fav) {
                    iv_like.setImageResource(R.drawable.ic_favorite);
                    setLiked(meal, true);
                }
                else {
                    iv_like.setImageResource(R.drawable.ic_favorite_empty);
                    setLiked(meal, false);
                }
                break;
            case R.id.btn_watch_recepie:
                if(isNetworkConnected()){
                    Intent intent = new Intent(DishViewActivity.this, RecepieActivity.class);
                    intent.putExtra("url", meal.getRecipeURL());
                    startActivity(intent);
                }
                else {
                    Context context = getApplicationContext();
                    CharSequence text = "Нет доступа к сети!";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                }
                break;
            case R.id.btn_watch_ingredients:

                if(meal.getIngredients().size() < 1){
                    Context context = getApplicationContext();
                    CharSequence text = "Список ингридиентов пуст!";
                    int duration = Toast.LENGTH_LONG;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else {
                    Intent intent = new Intent(DishViewActivity.this, IngridientsActivity.class);
                    intent.putExtra("meal", meal);
                    startActivity(intent);
                }
                break;
        }

    }

    private void setLiked(Meal m, boolean isLiked){
        User user = User.getInstance();
        if(isLiked){
            user.addFavorMeal(m);
        }
        else{
            user.removeFavorMeal(m.getName());
        }
    }

    private void setRating(Meal m, float rating){
        DataBase db = DataBase.getInstance();
        try {
            m.setRating(rating);
        } catch (FormatException e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        if(!ratingChangedInCode)
            setRating(meal, ratingBar.getRating());
    }

    private Meal findMeal(Meal m){
        DataBase dataBase = DataBase.getInstance();
        for (Meal x:
             dataBase.getMeals()) {
            if(x.getName().equals(m.getName()))
                return x;
        }
        return null;
    }
}