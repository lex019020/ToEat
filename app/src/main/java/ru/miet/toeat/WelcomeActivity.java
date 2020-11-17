package ru.miet.toeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e)
        {//
             }

        setContentView(R.layout.activity_welcome);

        if(tryToLoadData())
        {
            (new Handler()).postDelayed(this::switchToMainMnu, 1000);
        }
        else{
            (new Handler()).postDelayed(this::switchToFirstSetup, 1000);
        }
    }

    private void switchToMainMnu(){
        //TODO implement
    }

    private void switchToFirstSetup(){
        //TODO implement
    }

    private boolean tryToLoadData(){
        //TODO implement
        return true;
    }

}