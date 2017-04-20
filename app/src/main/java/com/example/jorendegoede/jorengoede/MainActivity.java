package com.example.jorendegoede.jorengoede;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jorendegoede.jorengoede.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToFirst(View view) {
        // got to wordpage
        Intent intent = new Intent(this, FirstActivity.class);
        // close activity when finished
        startActivity(intent);
        finish();
    }
}
