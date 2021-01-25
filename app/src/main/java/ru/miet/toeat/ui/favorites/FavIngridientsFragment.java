package ru.miet.toeat.ui.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Product;


public class FavIngridientsFragment extends Fragment {
    private ArrayList<Product> products;

    public FavIngridientsFragment() {
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
        return inflater.inflate(R.layout.fragment_fav_ingridients, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv = view.findViewById(R.id.tv_no_fav_ingrids);
        ListView lw = view.findViewById(R.id.lv_fav_ingrids);

        loadData();
        if(products.size() > 0){
            tv.setVisibility(View.GONE);
            lw.setAdapter(new FavIngridientsAdapter(getContext(), 0, products));
        }
    }

    private void loadData(){
        products = new ArrayList<>();
        products = User.getInstance().getFavorProducts();

        // FIXME for testing only
        try
        {
            Product p = new Product("маслины", 0);
            for(int i = 0; i < 30; i++)
                products.add(p);
        }
        catch (FormatException e){
            e.printStackTrace();
        }

    }

}