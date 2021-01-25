package ru.miet.toeat.ui.ingredients;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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

        // setting text
        ((TextView) convertView.findViewById(R.id.text_view_name))
                .setText(i.getProduct().getName());
        ((TextView) convertView.findViewById(R.id.text_view_amount))
                .setText(i.getAmount());

        LinearLayout ll =  convertView.findViewById(R.id.ll_ing_view);
        // creating popup menu
        ll.setOnLongClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(getContext(),v);
            popupMenu.inflate(R.menu.ingridient_add_fav_menu);

            if(isIngridientFavorite(i)){
                popupMenu.getMenu().removeItem(R.id.add_fav_menuitem);
            }
            else {
                popupMenu.getMenu().removeItem(R.id.del_fav_menuitem);
            }

            if(isIngridientUnfavorite(i)){
                popupMenu.getMenu().removeItem(R.id.add_unfav_menuitem);
            }
            else {
                popupMenu.getMenu().removeItem(R.id.del_unfav_menuitem);
            }

            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()) {
                    case R.id.add_fav_menuitem:
                        setIngredientFavorite(i, true);
                        Toast.makeText(getContext(),
                                "Ингридиент добавлен в избранное",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.add_unfav_menuitem:
                        setIngredientUnfavorite(i, true);
                        Toast.makeText(getContext(),
                                "Ингридиент добавлен в нелюбимое",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.del_fav_menuitem:
                        setIngredientFavorite(i, false);
                        Toast.makeText(getContext(),
                                "Ингридиент убран из избранного",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.del_unfav_menuitem:
                        setIngredientUnfavorite(i, false);
                        Toast.makeText(getContext(),
                                "Ингридиент убран из нелюбимого",
                                Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                        return false;
                }
            });
            popupMenu.show();
            return false;
        });

        return convertView;

    }

    private boolean isIngridientFavorite(Ingredient ingredient){
        // TODO
        return false;
    }

    private boolean isIngridientUnfavorite(Ingredient ingredient){
        // TODO
        return false;
    }

    private void setIngredientFavorite(Ingredient ingredient, boolean set){
        // TODO
    }

    private void setIngredientUnfavorite(Ingredient ingredient, boolean set){
        // TODO
    }
}
