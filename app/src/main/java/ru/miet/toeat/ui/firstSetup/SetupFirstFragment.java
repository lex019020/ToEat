package ru.miet.toeat.ui.firstSetup;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.Year;
import java.util.Date;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ru.miet.toeat.R;
import ru.miet.toeat.ui.FirstSetupActivity;
import ru.miet.toeat.ui.MainActivity;


public class SetupFirstFragment extends Fragment {

    Bundle bundle = new Bundle();
    String name = "";
    Boolean sex = true;

    GregorianCalendar ch_birth = new GregorianCalendar();
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
    private DatePickerDialog.OnDateSetListener  dateListener;

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
        EditText editname= view.findViewById(R.id.et_setup_name);
        name=editname.getText().toString();

        Button btn_birth= view.findViewById(R.id.btn_replace_some2);
        Button next= view.findViewById(R.id.btn_next_1);
        btn_birth.setText(df.format(ch_birth.getTime()));
        ((RadioGroup)view.findViewById(R.id.sex_id)).setOnCheckedChangeListener((arg0, id) -> {
            switch(id) {
                case R.id.rb_setup_male:
                    sex=true;
                    break;
                case R.id.radioButton2:
                    sex=false;
                    break;
                default:
                    break;
            }
        });
        ((EditText)view.findViewById(R.id.et_setup_name)).addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                name=((EditText)view.findViewById(R.id.et_setup_name)).getText().toString();
                if((new GregorianCalendar()).get(Calendar.YEAR)-ch_birth.get(Calendar.YEAR)>10 && name.length()>0 && (new GregorianCalendar()).get(Calendar.YEAR)-ch_birth.get(Calendar.YEAR)<110){
                    next.setEnabled(true);

                } else{
                    next.setEnabled(false);
                    if(name.length()==0) {
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Имя пустое!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        btn_birth.setOnClickListener(v -> {
            int year = ch_birth.get(Calendar.YEAR);
            int month = ch_birth.get(Calendar.MONTH);
            int day = ch_birth.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_DarkActionBar, dateListener, year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
        });

        dateListener = (view1, year, month, dayOfMonth) -> {
            ch_birth.set(year, month, dayOfMonth);
            btn_birth.setText(df.format(ch_birth.getTime()));
            if((new GregorianCalendar()).get(Calendar.YEAR)-ch_birth.get(Calendar.YEAR)>=10 && name.length()>0 && name.length()<30 && (new GregorianCalendar()).get(Calendar.YEAR)-ch_birth.get(Calendar.YEAR)<100){
                next.setEnabled(true);
            } else{
                next.setEnabled(false);
                if((new GregorianCalendar()).get(Calendar.YEAR)-ch_birth.get(Calendar.YEAR)<10 || (new GregorianCalendar()).get(Calendar.YEAR)-ch_birth.get(Calendar.YEAR)>100) {
                    Context context = getActivity().getApplicationContext();
                    CharSequence text = "Вы не подходите по возрасту, для пользования нашим приложением!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        };


       next.setOnClickListener((v)->{
            bundle.putString("name", name);
            bundle.putSerializable("birth", ch_birth.getTime());
            bundle.putBoolean("sex", sex);
            Navigation.findNavController(view).navigate(R.id.action_setupFirstFragment_to_setupSecondFragment, bundle);
        });

    }


}