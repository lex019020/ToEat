package ru.miet.toeat.ui.firstSetup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.util.Date;

import ru.miet.toeat.R;


public class SetupFirstFragment extends Fragment {

    Bundle bundle = new Bundle();
    String name = "";
    Date birth = new Date(); // запрашивать через datepickerdialog, накинуть туда ограничения
    Boolean sex = false;     // не забыть держать кнопку next_1 disabled, пока значения не корректны


    public SetupFirstFragment() {
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
        return inflater.inflate(R.layout.fragment_setup_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((Button)view.findViewById(R.id.btn_next_1)).setOnClickListener((v)->{
            bundle.putString("name", name);
            bundle.putSerializable("birth", birth);
            bundle.putBoolean("sex", sex);
            Navigation.findNavController(view).navigate(R.id.action_setupFirstFragment_to_setupSecondFragment, bundle);
        });
    }
}