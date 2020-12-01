package ru.miet.toeat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.miet.toeat.R;

public class FirstSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_setup);

        try
        {
            this.getSupportActionBar().hide();  // hide actionbar
        }
        catch (NullPointerException e){
            //
        }

        //
    }
}