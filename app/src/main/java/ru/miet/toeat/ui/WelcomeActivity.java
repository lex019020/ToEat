package ru.miet.toeat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.miet.toeat.R;
import ru.miet.toeat.infoStorage.DataBase;
import ru.miet.toeat.infoStorage.User;
import ru.miet.toeat.model.FormatException;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){
            //
        }

        if(tryToLoadData())
            (new Handler()).postDelayed(this::switchToMainMenu, 300);
        else
            (new Handler()).postDelayed(this::switchToSetupPage, 300);
    }

    private boolean tryToLoadData(){
        User.getInstance(getFilesDir() + "/user");
        DataBase.getInstance(getFilesDir() + "/database");

        User.getInstance().load();
        DataBase.getInstance().load();

        return User.getInstance().getWeight() != 0
                && DataBase.getInstance().getMeals().size() > 0;
    }

    private void switchToMainMenu(){
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void switchToSetupPage(){
        Intent intent = new Intent(WelcomeActivity.this, FirstSetupActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
