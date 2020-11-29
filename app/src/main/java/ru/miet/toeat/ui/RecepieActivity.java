package ru.miet.toeat.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import ru.miet.toeat.R;

public class RecepieActivity extends AppCompatActivity {

    private String url;
    private WebView webView;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recepie);

        url = getIntent().getStringExtra("url");
        setTitle("Просмотр рецепта");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.wv_recepie);

        webView.getSettings().setJavaScriptEnabled(true);


        webView.loadUrl(url);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }
}