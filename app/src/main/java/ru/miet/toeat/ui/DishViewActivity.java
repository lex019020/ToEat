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
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.ui.ingredients.IngridientsActivity;

public class DishViewActivity extends AppCompatActivity implements View.OnClickListener {


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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_view);

        meal = (Meal) getIntent().getSerializableExtra("meal");
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


        iv_like.setOnClickListener(this);
        recBtn.setOnClickListener(this);
        ingBtn.setOnClickListener(this);

        if(isNetworkConnected()){
            Picasso.get()
                    .load(meal.getImage())
                    .into(iv_pic);
        }

        tv_prot.setText("Б: " + new DecimalFormat("#.#").format(meal.getProteins()));
        tv_carb.setText("У: " + new DecimalFormat("#.#").format(meal.getCarbs()));
        tv_fat.setText("Ж: " + new DecimalFormat("#.#").format(meal.getFat()));
        tv_kcal.setText("ККал: " + new DecimalFormat("#.#").format(meal.getCalories()));
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
                }
                else {
                    iv_like.setImageResource(R.drawable.ic_favorite_empty);
                }
                break;
            case R.id.btn_watch_recepie:
                if(isNetworkConnected()){
                    Intent intent = new Intent(DishViewActivity.this, RecepieActivity.class);
                    intent.putExtra("url", meal.getLink_to_recept());
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

                // TODO check zero ingredients
                Intent intent = new Intent(DishViewActivity.this, IngridientsActivity.class);
                intent.putExtra("meal", meal);
                startActivity(intent);
                break;
        }

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}