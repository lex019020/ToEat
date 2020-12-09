package ru.miet.toeat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import ru.miet.toeat.R;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.ui.ingredients.IngridientsActivity;

public class DishViewActivity extends AppCompatActivity implements View.OnClickListener {


    private RatingBar ratingBar;
    private ImageView iv_like;
    private Button recBtn;
    private Button ingBtn;
    private boolean is_fav;
    private Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_view);

        meal = (Meal) getIntent().getSerializableExtra("meal");
        setTitle(meal.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        is_fav = false;

        iv_like = findViewById(R.id.iv_fav);
        recBtn = findViewById(R.id.btn_watch_recepie);
        ingBtn = findViewById(R.id.btn_watch_ingredients);


        iv_like.setOnClickListener(this);
        recBtn.setOnClickListener(this);
        ingBtn.setOnClickListener(this);
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
                    intent.putExtra("url", "https://1000.menu/cooking/33497-steik-iz-foreli-na-skovorode"); // TODO get from DB
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