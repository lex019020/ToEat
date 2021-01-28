package ru.miet.toeat.ui.favorites;

import android.content.Context;
import android.view.LayoutInflater;
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

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.Product;

public class FavIngridientsAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> products;

    public FavIngridientsAdapter(@NonNull Context context, int resource, @NonNull List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.products = objects;
    }

    @Nullable
    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Product i = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.fav_ingrid_view, null);

        TextView tv = convertView.findViewById(R.id.tv_fav_ingrid_name);
        LinearLayout ll = convertView.findViewById(R.id.ll_fav_ingrid);

        tv.setText(i.getName());
        ll.setOnLongClickListener(v ->{

            PopupMenu popupMenu = new PopupMenu(getContext(),v);
            popupMenu.inflate(R.menu.ingridient_add_fav_menu);

            popupMenu.getMenu().removeItem(R.id.add_fav_menuitem);
            popupMenu.getMenu().removeItem(R.id.add_unfav_menuitem);
            popupMenu.getMenu().removeItem(R.id.del_unfav_menuitem);

            popupMenu.setOnMenuItemClickListener(item -> {
                if (item.getItemId() == R.id.del_fav_menuitem) {
                    delFromFav(i);
                    Toast.makeText(getContext(),
                            "Ингридиент убран из избранного",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            });
            popupMenu.show();
            return true;
        });

        return convertView;
    }

    private void delFromFav(Product p){
        User user = User.getInstance();
        try
        {
            user.getFavorProducts().remove(p);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        notifyDataSetChanged();
    }
}
