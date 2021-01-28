package ru.miet.toeat.ui.todayMenu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.DataBase;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.Ingredient;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Menu;
import ru.miet.toeat.model.Product;
import ru.miet.toeat.ui.DishViewActivity;


@SuppressWarnings("FieldCanBeLocal")
@SuppressLint("SetTextI18n")
public class RationFragment extends Fragment implements View.OnClickListener {


    public RationFragment() {
        // Required empty public constructor
    }

    private TextView tv_breakfast;
    private TextView tv_tiffin;
    private TextView tv_dinner;
    private TextView tv_aft_snack;
    private TextView tv_supper;
    private TextView tv_snack;
    private TextView tv_carb;
    private TextView tv_fat;
    private TextView tv_prot;
    private TextView tv_kcal;

    private LinearLayout lay_list;
    private LinearLayout lay_breakfast;
    private LinearLayout lay_tiffin;
    private LinearLayout lay_dinner;
    private LinearLayout lay_aft_snack;
    private LinearLayout lay_supper;
    private LinearLayout lay_snack;

    private final boolean DEBUG = true; // TODO remove

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

        tv_breakfast = getView().findViewById(R.id.tv_breakfast);
        tv_tiffin = getView().findViewById(R.id.tv_tiffin);
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

        lay_breakfast.setOnClickListener(this);
        lay_aft_snack.setOnClickListener(this);
        lay_dinner.setOnClickListener(this);
        lay_snack.setOnClickListener(this);
        lay_supper.setOnClickListener(this);
        lay_tiffin.setOnClickListener(this);

        tv_prot  = getView().findViewById(R.id.tv_menu_prot);
        tv_fat  = getView().findViewById(R.id.tv_menu_fat);
        tv_carb  = getView().findViewById(R.id.tv_menu_carb);
        tv_kcal  = getView().findViewById(R.id.tv_menu_kcal);

        loadTodayMenu();

        updateTextViews();

    }

    private void updateTextViews() {
        tv_breakfast.setText(today_menu.getBreakfast().getName());
        tv_tiffin.setText(today_menu.getTiffin().getName());
        tv_dinner.setText(today_menu.getDinner().getName());
        tv_aft_snack.setText(today_menu.getAnSnack().getName());
        tv_supper.setText(today_menu.getSupper().getName());
        tv_snack.setText(today_menu.getSnack().getName());

        tv_carb.setText("У: " + new DecimalFormat("#.#")
                .format(today_menu.getCarbs()));
        tv_fat.setText("Ж: " + new DecimalFormat("#.#")
                .format(today_menu.getFat()));
        tv_prot.setText("Б: " + new DecimalFormat("#.#")
                .format(today_menu.getProteins()));
        tv_kcal.setText("Ккал: " + new DecimalFormat("#.#")
                .format(today_menu.getCalories()));


    }

    private void loadTodayMenu(){
        today_menu = getToday_menu();

    }

    private Menu getToday_menu(){
        User user = User.getInstance();
        @SuppressLint("SimpleDateFormat")
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date today = new Date();
        Date todayWithZeroTime = null;

        try {
            todayWithZeroTime = formatter.parse(formatter.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(     DEBUG // TODO remove
                || user.getMenu() == null
                || user.getMenu().getBreakfast() == null
                || user.getMenu().getBreakfast().getDateOfLastDispense() == null
                || !user.getMenu().getBreakfast().getDateOfLastDispense()
                .equals(todayWithZeroTime)){
            // new menu
            // TODO start generation
            try{
                Menu menu = new Menu();
                menu = user.genMenu();

                menu.calcNutrition();
                menu.updateDates();
                user.setMenu(menu);
                addMenuToHistory(menu);
            }
            catch (Throwable e){
                e.printStackTrace();
            }
            return user.getMenu();
        }
        else{
            return user.getMenu();
        }
    }

    private void addMenuToHistory(Menu menu){
        User user = User.getInstance();
        user.addMealToHistory(menu.getBreakfast());
        user.addMealToHistory(menu.getTiffin());
        user.addMealToHistory(menu.getDinner());
        user.addMealToHistory(menu.getSnack());
        user.addMealToHistory(menu.getSupper());
        user.addMealToHistory(menu.getAnSnack());
        while (user.getMealHistory().size() > 300)
            user.getMealHistory().remove(0);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.lay_breakfast_element:
                intent = new Intent(getActivity(), DishViewActivity.class);
                //intent.putExtra("id", 0);
                intent.putExtra("meal", today_menu.getBreakfast());
                startActivity(intent);

                break;
            case R.id.lay_afternoon_snack_element:
                intent = new Intent(getActivity(), DishViewActivity.class);
                //intent.putExtra("id", 0);
                intent.putExtra("meal", today_menu.getAnSnack());
                startActivity(intent);
                break;
            case R.id.lay_dinner_element:
                intent = new Intent(getActivity(), DishViewActivity.class);
                //intent.putExtra("id", 0);
                intent.putExtra("meal", today_menu.getDinner());
                startActivity(intent);

                break;
            case R.id.lay_snack_element:
                intent = new Intent(getActivity(), DishViewActivity.class);
                //intent.putExtra("id", 0);
                intent.putExtra("meal", today_menu.getSnack());
                startActivity(intent);

                break;
            case R.id.lay_supper_element:
                intent = new Intent(getActivity(), DishViewActivity.class);
                //intent.putExtra("id", 0);
                intent.putExtra("meal", today_menu.getSupper());
                startActivity(intent);

                break;
            case R.id.lay_tiffin_element:
                intent = new Intent(getActivity(), DishViewActivity.class);
                //intent.putExtra("id", 0);
                intent.putExtra("meal", today_menu.getTiffin());
                startActivity(intent);

                break;
            default:
                //
        }
    }
}