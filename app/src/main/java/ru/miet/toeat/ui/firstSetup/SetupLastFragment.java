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

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.DataBase;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.FormatException;
import ru.miet.toeat.model.Meal;
import ru.miet.toeat.ui.MainActivity;

public class SetupLastFragment extends Fragment {

    String name;
    Date birth;
    Boolean sex;
    float height;
    float weight;
    int act;
    float p; // prot
    float f; // fat
    float c; // carb
    float kcal;

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

        Bundle bundle = getArguments();
        try
        {
            name = bundle.getString("name");
            birth = (Date) bundle.getSerializable("birth");
            sex = bundle.getBoolean("sex");
            height = bundle.getFloat("height");
            weight = bundle.getFloat("weight");
            act = bundle.getInt("act");
            p = bundle.getFloat("p");
            f = bundle.getFloat("f");
            c = bundle.getFloat("c");
            // todo etc

        }
        catch (Exception e){
            e.printStackTrace();
            //todo message
            Navigation.findNavController(view).popBackStack(R.id.setupZeroFragment, false);
        }
        ((Button)view.findViewById(R.id.btn_next_last)).setOnClickListener((v)->{
            initDatabaseAndGo();
        });
    }

    private void initDatabaseAndGo(){
        ArrayList<Meal> list;
        try {
            //Resources r = getActivity().getResources();
            InputStream is = getFileFromResourceAsStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            list = (ArrayList<Meal>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        User user = User.getInstance();
        DataBase dataBase = DataBase.getInstance();

        dataBase.setMeals(list);

        try {

            user.setBirthDate(birth);
            user.setHeight(height);
            user.setLifestyle(User.Lifestyle.values()[act]);
            user.setName(name);
            user.setSex(sex);
            user.setWeight(weight);
            user.setCarbs(c);
            user.setFat(f);
            user.setProteins(p);
            if(!user.calculateCalories())
                throw new FormatException("bad calculation");
        } catch (Exception e) {
            e.printStackTrace();
            // todo error
        }

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private InputStream getFileFromResourceAsStream() {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("res/raw/meals.bin");

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + "res/raw/meals.bin");
        } else {
            return inputStream;
        }

    }

}