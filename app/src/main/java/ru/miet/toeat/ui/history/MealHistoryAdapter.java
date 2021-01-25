package ru.miet.toeat.ui.history;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import ru.miet.toeat.R;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.ui.DishViewActivity;

public class MealHistoryAdapter extends ArrayAdapter<Meal> {
    private List<Meal> meals;
    private Context context;

    public MealHistoryAdapter(@NonNull Context context, int resource, @NonNull List<Meal> objects) {
        super(context, resource, objects);
        meals = objects;
        this.context = context;
    }

    @Nullable
    @Override
    public Meal getItem(int position) {
        return meals.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Meal item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.meal_history_view, null); // not sure for parent
        }

        TextView tvDate = convertView.findViewById(R.id.tv_history_date);
        TextView tvName = convertView.findViewById(R.id.tv_history_name);
        LinearLayout ll = convertView.findViewById(R.id.ll_meal_history_element);

        SimpleDateFormat format = new SimpleDateFormat("EEEE, d MMMM  y", Locale.getDefault());
        tvDate.setText(format.format(item.getDateOfLastDispense()));

        tvName.setText(item.getName());

        ll.setOnClickListener(v ->{
            Intent intent = new Intent(context, DishViewActivity.class);
            intent.putExtra("meal", item);
            context.startActivity(intent);
        });

        return convertView;
    }
}
