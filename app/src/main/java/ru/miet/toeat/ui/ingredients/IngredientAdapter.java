package ru.miet.toeat.ui.ingredients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ru.miet.toeat.R;
import ru.miet.toeat.model.Ingredient;

public class IngredientAdapter extends ArrayAdapter<Ingredient> {
    private List<Ingredient> ingredients;

    @Nullable
    @Override
    public Ingredient getItem(int position) {
        return ingredients.get(position);
    }

    public IngredientAdapter(@NonNull Context context, int resource, @NonNull List<Ingredient> objects) {
        super(context, resource, objects);
        ingredients = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Ingredient i = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.ingredient_view, null);
        }
        ((TextView) convertView.findViewById(R.id.text_view_name))
                .setText(i.getProduct().getName());
        ((TextView) convertView.findViewById(R.id.text_view_amount))
                .setText(fmt(i.getAmount()));
        return convertView;

    }

    private static String fmt(double d)
    {
        if(d == (long) d)
            return String.format(Locale.ENGLISH ,"%d",(long)d);
        else
            return String.format("%s",d);
    }
}
