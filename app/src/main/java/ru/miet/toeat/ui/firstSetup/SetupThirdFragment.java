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
import android.widget.SeekBar;
import android.widget.TextView;

import ru.miet.toeat.R;
import ru.miet.toeat.tools.Tools;

public class SetupThirdFragment extends Fragment {

    float p = 1; // prot
    float f = 3; // fat
    float c = 5; // carb

    SeekBar sbProt;
    SeekBar sbFat;
    SeekBar sbCarb;

    TextView tvProt;
    TextView tvFat;
    TextView tvCarb;

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

        sbProt = view.findViewById(R.id.sb_prot);
        sbFat = view.findViewById(R.id.sb_fat);
        sbCarb = view.findViewById(R.id.sb_carb);

        tvProt = view.findViewById(R.id.tv_setup_prot);
        tvFat = view.findViewById(R.id.tv_setup_fat);
        tvCarb = view.findViewById(R.id.tv_setup_carbs);

        sbProt.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p = seekBar.getProgress()+1;
                tvProt.setText("Белки: " + Tools.removeAfterLastDot(String.valueOf(p)));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }
        });

        sbFat.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                f = seekBar.getProgress()+1;
                tvFat.setText("Жиры: " + Tools.removeAfterLastDot(String.valueOf(f)));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }
        });

        sbCarb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                c = seekBar.getProgress()+1;
                tvCarb.setText("Углеводы: " + Tools.removeAfterLastDot(String.valueOf(c)));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }
        });

        view.findViewById(R.id.btn_next_3).setOnClickListener((v)->{
            Bundle bundle = new Bundle();
            bundle.putAll(getArguments());
            bundle.putFloat("p", p);
            bundle.putFloat("f", f);
            bundle.putFloat("c", c);
            Navigation.findNavController(view).navigate(R.id.action_setupThree_to_setupLast, bundle);
        });
    }
}