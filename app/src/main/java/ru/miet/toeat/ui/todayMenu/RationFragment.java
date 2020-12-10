package ru.miet.toeat.ui.todayMenu;

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

import java.util.ArrayList;

import ru.miet.toeat.R;
import ru.miet.toeat.model.Ingredient;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Menu;
import ru.miet.toeat.model.Product;
import ru.miet.toeat.ui.DishViewActivity;


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
        // TODO

        loadTodayMenu();
    }

    private void loadTodayMenu(){
        // TODO 111111111111111111111111111111111111111111111111111111111111
        // Надо забить блюдами, похожими на правду, с полными данными и ингредиентами.
        try{
            today_menu = new Menu();
            today_menu.setBreakfast(new Meal());
            today_menu.getBreakfast().setName("Стейк из форели с картошкой");
            today_menu.getBreakfast().setIngredients(new ArrayList<>());
            today_menu.getBreakfast().getIngredients().add(new Ingredient("250 грамм",
                    new Product("Форель", 0), "рыба"));
            today_menu.getBreakfast().getIngredients().add(new Ingredient("230 грамм",
                    new Product("Картофель свежий", 1), "овощи"));

            today_menu.setTiffin(today_menu.getBreakfast());
            today_menu.setDinner(today_menu.getBreakfast());
            today_menu.setAnSnack(today_menu.getBreakfast());
            today_menu.setSupper(today_menu.getBreakfast());
            today_menu.setSnack(today_menu.getBreakfast());
        }
        catch (Throwable e){
            //
        }
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