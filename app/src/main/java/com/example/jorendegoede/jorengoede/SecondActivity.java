package com.example.jorendegoede.jorengoede;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        // get received words of user
        Intent intent = getIntent();
        String receivedFirstWord = intent.getStringExtra("wordInput");
        // show words in text
        TextView storytext = (TextView) findViewById(R.id.storytext);
        storytext.setText(receivedFirstWord);
    }

    public void goToMain (View view) {
        // go to mainpage
        Intent intent = new Intent(this, MainActivity.class);
        // close when button pressed
        startActivity(intent);
        finish();
    }

    // android phone button navigation
    @Override
    public void onBackPressed() {
        // go to mainpage
        Intent intent = new Intent(this, MainActivity.class);
        // close when button pressed
        startActivity(intent);
        finish();
    }
}