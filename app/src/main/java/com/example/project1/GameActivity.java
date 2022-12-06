package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    ImageView iv;
    ImageView hole1;
    ImageView hole2;
    ImageView hole3;
    ImageView hole4;
    ImageView hole5;
    ImageView hole6;
    ImageView hole7;
    ImageView hole8;
    ImageView hole9;
    ImageView hole10;
    ImageView hole11;
    ImageView hole12;
    TextView currentScore;
    TextView textViewHighscore;
    TextView textViewHighScoreMain;



    public boolean clicked = true;
    public int scoreNumber = 0;
    public int numberOfTries = 3;
    public int highscore;
    public boolean thread;


    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";
    public static final String KEY_LASTSCORE = "lastScore";

    Context context = this;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
       // mp = MediaPlayer.create(context, R.raw.sound);
        iv = findViewById(R.id.moleimage);
        hole1 = findViewById(R.id.hole1);
        hole2 = findViewById(R.id.hole2);
        hole3 = findViewById(R.id.hole3);
        hole4 = findViewById(R.id.hole4);
        hole5 = findViewById(R.id.hole5);
        hole6 = findViewById(R.id.hole6);
        hole7 = findViewById(R.id.hole7);
        hole8 = findViewById(R.id.hole8);
        hole9 = findViewById(R.id.hole9);
        hole10 = findViewById(R.id.hole10);
        hole11 = findViewById(R.id.hole11);
        hole12 = findViewById(R.id.hole12);
        currentScore = findViewById(R.id.scoreNumberTextView);
        textViewHighscore = findViewById(R.id.highScoreNumberTextview);
        loadHighscore();
        //loadLastScore();
        currentScore.setText("" + scoreNumber);
        numberOfTries = 3;
        clicked = true;


    }


    public void onClickListener(View view) {
        thread = true;

        if (numberOfTries != 0 && numberOfTries < 0) {
            iv.setOnClickListener(null);
        } else {
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    scoreNumber += 10;
                    currentScore.setText("" + scoreNumber);
                    if (scoreNumber > highscore) {
                        updateHighscore(scoreNumber);
                    }
                    List givenListXCoordinate = Arrays.asList(hole1.getLeft(), hole2.getLeft(), hole3.getLeft(),
                            hole4.getLeft(), hole5.getLeft(), hole6.getLeft(), hole7.getLeft(), hole8.getLeft(),
                            hole9.getLeft(), hole10.getLeft(), hole11.getLeft(), hole12.getLeft());
                    List givenListYCoordinate = Arrays.asList(hole1.getTop(), hole2.getTop(), hole3.getTop(),
                            hole4.getTop(), hole5.getTop(), hole6.getTop(), hole7.getTop(), hole8.getTop(),
                            hole9.getTop(), hole10.getTop(), hole11.getTop(), hole12.getTop());
                    Random rand = new Random();
                    int randX = (int) givenListXCoordinate.get(rand.nextInt(givenListXCoordinate.size()));
                    int randY = (int) givenListYCoordinate.get(rand.nextInt(givenListYCoordinate.size()));
                    iv.setX(randX);
                    iv.setY(randY);
                    final Handler handler = new Handler();
                    final Runnable r = new Runnable(){
                        @Override
                        public void run() {
                            if (thread) {
                                handler.postDelayed(this, 2000);
                            }
                        }
                    };
                    //base speed at which moles pop up, 5 moles or less is regular speed
                    if(scoreNumber <= 50){
                        handler.postDelayed(r, 2000);
                    }
                    //at 6 moles, timer speeds up
                    else if(scoreNumber > 50 && scoreNumber <= 100){
                        handler.postDelayed(r, 1500);
                    }
                    //at 11 moles, timer speeds up more
                    else if(scoreNumber > 100 && scoreNumber <= 150){
                        handler.postDelayed(r, 1000);
                    }
                    //at 16 moles, timer speeds up even more
                    else if(scoreNumber >150 && scoreNumber <= 200){
                        handler.postDelayed(r, 500);
                    }
                    //at 26 moles, timer goes to max speed
                    else if(scoreNumber >250){
                        handler.postDelayed(r, 100);
                    }
                    clicked = false;
                }

            });

        }
        if (!clicked) {
            numberOfTries--;
            if(numberOfTries ==2) {
                ImageView imageview = new ImageView(GameActivity.this);
                RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.relativeLayout);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 220);
                params.leftMargin = 170;
                params.topMargin = 150;
                // Add image path from drawable folder.
                imageview.setImageResource(R.drawable.illustration_cute_cartoon_walrus_with_eyeglasses_beach_21085_415_removebg_preview);
                imageview.setLayoutParams(params);
                relativelayout.addView(imageview);
            List givenListXCoordinate = Arrays.asList(hole1.getLeft(), hole2.getLeft(), hole3.getLeft(),
                    hole4.getLeft(), hole5.getLeft(), hole6.getLeft(), hole7.getLeft(), hole8.getLeft(),
                    hole9.getLeft(), hole10.getLeft(), hole11.getLeft(), hole12.getLeft());
            List givenListYCoordinate = Arrays.asList(hole1.getTop(), hole2.getTop(), hole3.getTop(),
                    hole4.getTop(), hole5.getTop(), hole6.getTop(), hole7.getTop(), hole8.getTop(),
                    hole9.getTop(), hole10.getTop(), hole11.getTop(), hole12.getTop());
            Random rand = new Random();
            int randX = (int) givenListXCoordinate.get(rand.nextInt(givenListXCoordinate.size()));
            int randY = (int) givenListYCoordinate.get(rand.nextInt(givenListYCoordinate.size()));
            iv.setX(randX);
            iv.setY(randY);
            }
            else if(numberOfTries ==1){
                ImageView imageview = new ImageView(GameActivity.this);
                RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.relativeLayout);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 220);
                params.leftMargin = 400;
                params.topMargin = 150;
                // Add image path from drawable folder.
                imageview.setImageResource(R.drawable.illustration_cute_cartoon_walrus_with_eyeglasses_beach_21085_415_removebg_preview);
                imageview.setLayoutParams(params);
                relativelayout.addView(imageview);
        List givenListXCoordinate = Arrays.asList(hole1.getLeft(), hole2.getLeft(), hole3.getLeft(),
                hole4.getLeft(), hole5.getLeft(), hole6.getLeft(), hole7.getLeft(), hole8.getLeft(),
                hole9.getLeft(), hole10.getLeft(), hole11.getLeft(), hole12.getLeft());
        List givenListYCoordinate = Arrays.asList(hole1.getTop(), hole2.getTop(), hole3.getTop(),
                hole4.getTop(), hole5.getTop(), hole6.getTop(), hole7.getTop(), hole8.getTop(),
                hole9.getTop(), hole10.getTop(), hole11.getTop(), hole12.getTop());
        Random rand = new Random();
        int randX = (int) givenListXCoordinate.get(rand.nextInt(givenListXCoordinate.size()));
        int randY = (int) givenListYCoordinate.get(rand.nextInt(givenListYCoordinate.size()));
        iv.setX(randX);
        iv.setY(randY);
            }
            else if(numberOfTries ==0){
                ImageView imageview = new ImageView(GameActivity.this);
                RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.relativeLayout);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(250, 220);
                params.leftMargin = 650;
                params.topMargin = 150;
                // Add image path from drawable folder.
                imageview.setImageResource(R.drawable.illustration_cute_cartoon_walrus_with_eyeglasses_beach_21085_415_removebg_preview);
                imageview.setLayoutParams(params);
                relativelayout.addView(imageview);
        List givenListXCoordinate = Arrays.asList(hole1.getLeft(), hole2.getLeft(), hole3.getLeft(),
                hole4.getLeft(), hole5.getLeft(), hole6.getLeft(), hole7.getLeft(), hole8.getLeft(),
                hole9.getLeft(), hole10.getLeft(), hole11.getLeft(), hole12.getLeft());
        List givenListYCoordinate = Arrays.asList(hole1.getTop(), hole2.getTop(), hole3.getTop(),
                hole4.getTop(), hole5.getTop(), hole6.getTop(), hole7.getTop(), hole8.getTop(),
                hole9.getTop(), hole10.getTop(), hole11.getTop(), hole12.getTop());
        Random rand = new Random();
        int randX = (int) givenListXCoordinate.get(rand.nextInt(givenListXCoordinate.size()));
        int randY = (int) givenListYCoordinate.get(rand.nextInt(givenListYCoordinate.size()));
        iv.setX(randX);
        iv.setY(randY);
            }
            else{
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(KEY_HIGHSCORE,textViewHighscore.toString());
                intent.putExtra(KEY_LASTSCORE,scoreNumber);
                startActivity(intent);
                //scoreNumber =0;
            }
        }
    }
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText("" + highscore);
    }

    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText("" + highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}
