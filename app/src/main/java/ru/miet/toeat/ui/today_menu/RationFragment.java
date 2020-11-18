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

    private LinearLayout lay_list;
    private LinearLayout lay_breakfast;
    private LinearLayout lay_tiffin;
    private LinearLayout lay_dinner;
    private LinearLayout lay_aft_snack;
    private LinearLayout lay_supper;
    private LinearLayout lay_snack;

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

        lay_list = getView().findViewById(R.id.today_menu_layout);
        lay_breakfast = getView().findViewById(R.id.lay_breakfast_element);
        lay_tiffin = getView().findViewById(R.id.lay_tiffin_element);
        lay_dinner = getView().findViewById(R.id.lay_dinner_element);
        lay_aft_snack = getView().findViewById(R.id.lay_afternoon_snack_element);
        lay_supper = getView().findViewById(R.id.lay_supper_element);
        lay_snack = getView().findViewById(R.id.lay_snack_element);
        // TODO
    }

    private void loadTodayMenu(){
        // TODO
    }
}