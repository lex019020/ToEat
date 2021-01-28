package ru.miet.toeat.ui.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import dialog.NumberPickerDialog;
import dialog.TextDialog;
import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.tools.Tools;


public class SettingsFragment extends Fragment {
    ImageView imageView3;
    ImageView imageView32;
    ImageView imageView33;
    ImageView imageView34;
    ImageView imageView35;
    ImageView imageView36;
    ImageView imageView37;

    private TextView tv_settings_name;
    private TextView tv_settings_sex;
    private TextView tv_settings_age;
    private TextView tv_settings_height;
    private TextView tv_settings_weight;
    private TextView tv_settings_lifestyle;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        tv_settings_name = view.findViewById(R.id.tv_settings_name);
        tv_settings_sex = view.findViewById(R.id.tv_settings_sex);
        tv_settings_age = view.findViewById(R.id.tv_settings_age);
        tv_settings_height = view.findViewById(R.id.tv_settings_height);
        tv_settings_weight = view.findViewById(R.id.tv_settings_weight);
        tv_settings_lifestyle = view.findViewById(R.id.tv_settings_lifestyle);

        imageView3 = view.findViewById(R.id.imageView3);
        imageView32 = view.findViewById(R.id.imageView32);
        imageView33 = view.findViewById(R.id.imageView33);
        imageView34 = view.findViewById(R.id.imageView34);
        imageView35 = view.findViewById(R.id.imageView35);
        imageView36 = view.findViewById(R.id.imageView36);
        imageView37 = view.findViewById(R.id.imageView37);

        tv_settings_name.setText(User.getInstance().getName());
        tv_settings_sex.setText(User.getInstance().isSex() ? "Мужской" : "Женский");
        tv_settings_age.setText(new SimpleDateFormat("MM.dd.yyyy").format(User.getInstance().getBirthDate()));
        tv_settings_height.setText((int) User.getInstance().getHeight() + "");
        tv_settings_weight.setText((int) User.getInstance().getWeight() + "");
        tv_settings_lifestyle.setText(User.getInstance().getLifestyle().getString(User.getInstance().getLifestyle().getValue()));

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextDialog td = new TextDialog("Введите имя");
                td.show(getParentFragmentManager(), "picker");
                td.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tv_settings_name.setText(td.getCurrentValue());
                            User.getInstance().setName(td.getCurrentValue());
                        } catch (FormatException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        imageView32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] sex = {"М","Ж"};
                NumberPickerDialog npd = new NumberPickerDialog(0,0,1,sex, "Ввод", "Значение: ");
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {
                        tv_settings_sex.setText(npd.getCurrentValue() == 0 ? "Мужской" : "Женский");
                        User.getInstance().setSex(npd.getCurrentValue() == 0);
                    }
                });
                npd.show(getParentFragmentManager(), "picker");
            }
        });

        imageView33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerDialog npd = new NumberPickerDialog(0,0,0, "Ввод", "Значение: ");
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                npd.show(getParentFragmentManager(), "picker");
            }
        });

        imageView34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerDialog npd = new NumberPickerDialog(170, 120, 220, "Ввод", "Значение: ");
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {
                        int v = npd.getCurrentValue();
                        tv_settings_height.setText(npd.getCurrentValue() + "");
                        try {
                            User.getInstance().setHeight(npd.getCurrentValue());
                        } catch (FormatException e) {
                            e.printStackTrace();
                        }
                    }
                });
                npd.show(getParentFragmentManager(), "picker");
            }
        });

        imageView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerDialog npd = new NumberPickerDialog(70,30,250, "Ввод", "Значение: ");
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {
                        tv_settings_weight.setText(npd.getCurrentValue() + "");
                        try {
                            User.getInstance().setWeight(npd.getCurrentValue());
                        } catch (FormatException e) {
                            e.printStackTrace();
                        }
                    }
                });
                npd.show(getParentFragmentManager(), "picker");
            }
        });

        imageView36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerDialog npd = new NumberPickerDialog(
                        0, 0,
                        User.LifestyleStrings.length - 1, User.LifestyleStrings, "Ввод", "Значение: ");
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {
                        tv_settings_lifestyle.setText(User.getInstance().getLifestyle().getString(npd.getCurrentValue()));
                        User.getInstance().setLifestyle(User.Lifestyle.values()[npd.getCurrentValue()]);
                    }
                });
                npd.show(getParentFragmentManager(), "picker");
            }
        });

        imageView37.setOnClickListener(v -> {
            NumberPickerDialog npd = new NumberPickerDialog(
                    (int)User.getInstance().getProteins(), 1, 10,
                    "Белки" , "Выберите пропорцию белков");// prot
            NumberPickerDialog finalNpd = npd;
            npd.setOnOkFunction(() -> {
                try {
                    User.getInstance().setProteins(finalNpd.getCurrentValue());
                } catch (FormatException e) {
                    e.printStackTrace();
                }
            });
            finalNpd.show(getParentFragmentManager(), "picker");

            npd = new NumberPickerDialog( // fat
                    (int)User.getInstance().getProteins(), 1, 10,
                    "Жиры", "Выберите пропорцию жиров");
            NumberPickerDialog finalNpd1 = npd;
            npd.setOnOkFunction(() -> {
                try {
                    User.getInstance().setProteins(finalNpd1.getCurrentValue());
                } catch (FormatException e) {
                    e.printStackTrace();
                }
            });
            finalNpd1.show(getParentFragmentManager(), "picker");


            npd = new NumberPickerDialog( // carb
                    (int)User.getInstance().getProteins(), 1, 10,
                    "Углеводы", "Выберите пропорцию углеводов");
            NumberPickerDialog finalNpd2 = npd;
            npd.setOnOkFunction(() -> {
                try {
                    User.getInstance().setProteins(finalNpd2.getCurrentValue());
                } catch (FormatException e) {
                    e.printStackTrace();
                }
            });
            finalNpd2.show(getParentFragmentManager(), "picker");


        });

        return view;
    }
}