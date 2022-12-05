package com.example.rabbit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

public class ChoiceMusicActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_music);
    }

    public void music1(View view){
        mediaPlayer = MediaPlayer.create(ChoiceMusicActivity.this, R.raw.jingglebell);
        mediaPlayer.start();
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }

    public void music2(View view){
        mediaPlayer = MediaPlayer.create(ChoiceMusicActivity.this, R.raw.moneymoneymoney);
        mediaPlayer.start();
        GameView gameView = new GameView(this);
        setContentView(gameView);
    }
}