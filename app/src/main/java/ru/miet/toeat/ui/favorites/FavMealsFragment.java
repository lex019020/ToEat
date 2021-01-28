package ru.miet.toeat.ui.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.DataBase;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Ingredient;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Product;


public class FavMealsFragment extends Fragment {
    private ArrayList<Meal> meals;

    public FavMealsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ListView lw = view.findViewById(R.id.lv_fav_meals);
        TextView tw = view.findViewById(R.id.tv_no_fav_meals);

        loadFavMeals();
        if(meals.size() > 0){
            tw.setVisibility(View.GONE);
            lw.setAdapter(new FavMealsAdapter(getContext(), 0, meals));
        }

    }

    private void loadFavMeals(){
        meals = new ArrayList<>();
        meals = User.getInstance().getFavorMeals();


    }

}