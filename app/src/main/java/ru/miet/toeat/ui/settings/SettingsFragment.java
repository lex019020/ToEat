package ru.miet.toeat.ui.settings;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.miet.toeat.dialog.NumberPickerDialog;
import ru.miet.toeat.dialog.TextDialog;
import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.FormatException;


public class SettingsFragment extends Fragment {
    ImageView imageView3;
    ImageView imageView32;
    ImageView imageView33;
    ImageView imageView34;
    ImageView imageView35;
    ImageView imageView36;

    GregorianCalendar ch_birth = new GregorianCalendar();
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private DatePickerDialog.OnDateSetListener  dateListener;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        imageView3 = view.findViewById(R.id.imageView3);
        imageView32 = view.findViewById(R.id.imageView32);
        imageView33 = view.findViewById(R.id.imageView33);
        imageView34 = view.findViewById(R.id.imageView34);
        imageView35 = view.findViewById(R.id.imageView35);
        imageView36 = view.findViewById(R.id.imageView36);

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextDialog td = new TextDialog("Введите имя");
                td.show(getParentFragmentManager(), "picker");
                td.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {
                        try {
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
                NumberPickerDialog npd = new NumberPickerDialog(0,0,1,sex);
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {
                        User.getInstance().setSex(npd.getCurrentValue() != 0);
                    }
                });
                npd.show(getParentFragmentManager(), "picker");
            }
        });
        imageView33.setOnClickListener(v -> {
            int year = ch_birth.get(Calendar.YEAR);
            int month = ch_birth.get(Calendar.MONTH);
            int day = ch_birth.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_DARK, dateListener, year, month, day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ch_birth.set(year, month, dayOfMonth);

                    if((new GregorianCalendar()).get(Calendar.YEAR)-ch_birth.get(Calendar.YEAR)<10 || (new GregorianCalendar()).get(Calendar.YEAR)-ch_birth.get(Calendar.YEAR)>100) {
                        Context context = getActivity().getApplicationContext();
                        CharSequence text = "Вы не подходите по возрасту, для пользования нашим приложением!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    } else{
                        try {
                            User.getInstance().setBirthDate(ch_birth.getTime());
                        } catch (FormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
        };
        imageView34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerDialog npd = new NumberPickerDialog(170, 120, 220);
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                npd.show(getParentFragmentManager(), "picker");
            }
        });
        imageView35.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumberPickerDialog npd = new NumberPickerDialog(70,30,250);
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {

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
                        User.LifestyleStrings.length - 1, User.LifestyleStrings);
                npd.setOnOkFunction(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                npd.show(getParentFragmentManager(), "picker");
            }
        });

        return view;
    }
}