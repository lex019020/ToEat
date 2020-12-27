package ru.miet.toeat.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.miet.toeat.R;

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
            (new Handler()).postDelayed(this::switchToMainMenu, 500);
        else
            (new Handler()).postDelayed(this::switchToSetupPage, 500);
    }

    private boolean tryToLoadData(){
        //TODO Load DB and User here
        return true;
    }

    private void switchToMainMenu(){
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void switchToSetupPage(){
        //TODO Delete this method maybe?
    }

}
