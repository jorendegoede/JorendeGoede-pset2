package com.example.jorendegoede.jorengoede;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.IOException;
import java.io.InputStream;

import static com.example.jorendegoede.jorengoede.R.id.buttonNext;

public class FirstActivity extends AppCompatActivity {
    // global variables
    public Story story;
    public EditText userInput;

    public static final String intentArg = "storyText";
    int wordsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        // set story when opening
        if (savedInstanceState == null) {
            try {
                setStory();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // restore previous story object.
        else {
            story = (Story) savedInstanceState.getSerializable("story");

            // reset EditText
            userInput = (EditText) findViewById(R.id.word);
            String placeholder = story.getNextPlaceholder();
            userInput.setHint(placeholder);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable("story", story);
    }

    public void setStory() throws IOException {
        // retrieve assets.
        Context context = getApplicationContext();
        AssetManager am = context.getAssets();
        // text is tarzan
        try {
            InputStream is = am.open("madlib1_tarzan.txt");
            story = new Story(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // set EditText hint.
        userInput = (EditText) findViewById(R.id.word);
        String placeholder = story.getNextPlaceholder();
        userInput.setHint(placeholder);
    }

    public void onSubmit (View view){
        // userinput
        String inputText = userInput.getText().toString();
        story.fillInPlaceholder(inputText);
        // reset EditText
        String placeholder = story.getNextPlaceholder();
        userInput.setText("");
        userInput.setHint(placeholder);
        // if no words left to fill in show story
        if (story.getPlaceholderRemainingCount() == 0){
            goToSecond(view);
        }
    }

    public void goToSecond(View view) {
        // make sure text goes to second activity
        String text = story.toString();
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("wordInput", text);
        // clear story and close when redirected to other activity
        startActivity(intent);
        story.clear();
        finish();
    }

    // android button navigation
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}