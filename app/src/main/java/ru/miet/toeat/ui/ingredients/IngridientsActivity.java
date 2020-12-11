package ru.miet.toeat.ui.ingredients;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import ru.miet.toeat.R;
import ru.miet.toeat.model.Ingredient;
import ru.miet.toeat.model.Meal;

public class IngridientsActivity extends AppCompatActivity{

    private ArrayList<Ingredient> ingredients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingridients);
        setTitle("Список ингредиентов");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ingredients = ((Meal) getIntent().getSerializableExtra("meal")).getIngredients();
        IngredientAdapter adapter = new IngredientAdapter(this, 0, ingredients);

        ListView listView = findViewById(R.id.lview_ingredients);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }

    private void loadIngredients(){
        // TODO
    }


}