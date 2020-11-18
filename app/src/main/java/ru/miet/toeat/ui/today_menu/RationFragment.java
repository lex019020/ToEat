package ru.miet.toeat.ui.today_menu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.miet.toeat.R;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Menu;


public class RationFragment extends Fragment {


    public RationFragment() {
        // Required empty public constructor
    }

    private TextView tv_breakfast;
    private TextView tv_tiffin;
    private TextView tv_dinner;
    private TextView tv_aft_snack;
    private TextView tv_supper;
    private TextView tv_snack;

    private Layout lay_list;
    private Layout lay_breakfast;
    private Layout lay_tiffin;
    private Layout lay_dinner;
    private Layout lay_aft_snack;
    private Layout lay_supper;
    private Layout lay_snack;

    private Menu today_menu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_breakfast = getView().findViewById(R.id.tv_breakfast_name);
        tv_tiffin = getView().findViewById(R.id.tv_btiffin_name);
        tv_dinner = getView().findViewById(R.id.tv_dinner_name);
        tv_aft_snack = getView().findViewById(R.id.tv_afternoon_snack_name);
        tv_supper = getView().findViewById(R.id.tv_supper_name);
        tv_snack = getView().findViewById(R.id.tv_snack_name);

        //lay_list
        // TODO
    }

    private void loadTodayMenu(){
        // TODO
    }
}