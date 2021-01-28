package ru.miet.toeat.ui.favorites;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.DataBase;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.ui.DishViewActivity;

public class FavMealsAdapter extends ArrayAdapter<Meal> {
    private Context context;
    private List<Meal> meals;

    public FavMealsAdapter(@NonNull Context context, int resource, @NonNull List<Meal> objects) {
        super(context, resource, objects);
        this.context = context;
        this.meals = objects;
    }

    @Nullable
    @Override
    public Meal getItem(int position) {
        return findMeal(meals.get(position));
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Meal item = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.fav_meal_view, null);
        }

        TextView tv = convertView.findViewById(R.id.tv_fav_meal_name);
        LinearLayout ll = convertView.findViewById(R.id.ll_fav_meal);

        tv.setText(item.getName());
        ll.setOnClickListener(v ->{
            Intent intent = new Intent(context, DishViewActivity.class);
            intent.putExtra("meal", item);
            context.startActivity(intent);
        });


        return convertView;
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
