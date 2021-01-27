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

import ru.miet.toeat.R;

public class SetupThirdFragment extends Fragment {

    float p = 1; // prot
    float f = 3; // fat
    float c = 5; // carb

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setup_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((Button)view.findViewById(R.id.btn_next_3)).setOnClickListener((v)->{
            Bundle bundle = new Bundle();
            bundle.putAll(getArguments());
            bundle.putFloat("p", p);
            bundle.putFloat("f", f);
            bundle.putFloat("c", c);
            Navigation.findNavController(view).navigate(R.id.action_setupThree_to_setupLast, bundle);
        });
    }
}