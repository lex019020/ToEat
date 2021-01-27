package ru.miet.toeat.ui.firstSetup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.User;

public class SetupSecondFragment extends Fragment {

    float height = 170;
    float weight = 70;
    int act = 0; // activity type

    public SetupSecondFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setup_two, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NumberPicker np_height = view.findViewById(R.id.np_height);
        NumberPicker np_weight = view.findViewById(R.id.np_weight);
        NumberPicker np_activity = view.findViewById(R.id.np_activity);


        np_height.setMinValue(120);
        np_height.setMaxValue(220);
        np_height.setValue(170);
        np_height.setWrapSelectorWheel(true);

        np_weight.setMinValue(30);
        np_weight.setMaxValue(250);
        np_weight.setValue(70);
        np_weight.setWrapSelectorWheel(true);

        np_activity.setMinValue(0);
        np_activity.setMaxValue(User.LifestyleStrings.length - 1);
        np_activity.setDisplayedValues(User.LifestyleStrings);
        np_activity.setWrapSelectorWheel(true);

        ((Button)view.findViewById(R.id.btn_next_2)).setOnClickListener((v)->{
            Bundle newB = new Bundle();
            newB.putAll(getArguments());
            newB.putFloat("height", height);
            newB.putFloat("weight", weight);
            newB.putInt("act", act);
            Navigation.findNavController(view).navigate(R.id.action_setupSecondFragment_to_setupThree, newB);
        });
    }
}