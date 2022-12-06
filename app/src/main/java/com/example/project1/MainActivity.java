package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView high_score;
    int score;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    public static final String KEY_LASTSCORE = "lastScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Intent intent = getIntent();
            Intent myintent = getIntent();
            int intValue = myintent.getIntExtra(KEY_HIGHSCORE, 0);
            int lastScoreValue = myintent.getIntExtra(KEY_LASTSCORE, 0);
            SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            intValue = prefs.getInt(KEY_HIGHSCORE, 0);
            editor.commit();
            final TextView textViewTEST = (TextView) findViewById(R.id.highScoreNumberTextView);
            textViewTEST.setText(String.valueOf(intValue));
            final TextView textViewLastScore = (TextView) findViewById(R.id.lastScoreNumberTextView);
            textViewLastScore.setText(String.valueOf(lastScoreValue));
    }

    public void onClickPlay(View view){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }
}