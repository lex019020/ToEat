package ru.miet.toeat.ui.history;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Ingredient;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Product;


public class HistoryFragment extends Fragment {

    private ArrayList<Meal> history;

    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        ListView lw = getActivity().findViewById(R.id.lv_dish_history);
        TextView tv = getActivity().findViewById(R.id.tv_history_is_empty);
        loadHistory();

        if(history.size() > 0){
            tv.setVisibility(View.GONE);
            lw.setAdapter(new MealHistoryAdapter(getContext(), 0, history));
        }

        super.onViewCreated(view, savedInstanceState);
    }

    private void loadHistory(){
        history = new ArrayList<>(User.getInstance().getMealHistory());
        Collections.reverse(history);
        
    }
}