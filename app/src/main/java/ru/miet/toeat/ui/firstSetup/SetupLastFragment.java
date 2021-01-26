package ru.miet.toeat.ui.firstSetup;

import android.content.Intent;
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
import ru.miet.toeat.ui.FirstSetupActivity;
import ru.miet.toeat.ui.MainActivity;
import ru.miet.toeat.ui.WelcomeActivity;

public class SetupLastFragment extends Fragment {


    public SetupLastFragment() {
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
        return inflater.inflate(R.layout.fragment_setup_last, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((Button)view.findViewById(R.id.btn_next_last)).setOnClickListener((v)->{
            Intent intent = new Intent(getActivity(), MainActivity.class);
            // intent.put extra
            intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}