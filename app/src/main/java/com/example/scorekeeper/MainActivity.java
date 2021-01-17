package com.example.scorekeeper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int team1ScoreInt;
    private int team2ScoreInt;
    private TextView team1Score;
    private TextView team2Score;
    private ImageButton buttonMinusTeam1;
    private ImageButton buttonPlusTeam1;
    private ImageButton buttonMinusTeam2;
    private ImageButton buttonPlusTeam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarViews();
        inicializarPuntuaciones();
        if (savedInstanceState!=null){
            team1ScoreInt=savedInstanceState.getInt("score1");
            team2ScoreInt=savedInstanceState.getInt("score2");

            team1Score.setText(team1ScoreInt);
            team2Score.setText(team2ScoreInt);
        }
    }

    private void inicializarViews(){
        this.team1Score=findViewById(R.id.team1Score);
        this.team2Score=findViewById(R.id.team2Score);
        //buttons
        this.buttonMinusTeam1=findViewById(R.id.minusButtonTeam1);
        this.buttonMinusTeam1.setOnClickListener(this);
        this.buttonPlusTeam1=findViewById(R.id.plusButtonTeam1);
        this.buttonPlusTeam1.setOnClickListener(this);
        this.buttonMinusTeam2=findViewById(R.id.minusButtonTeam2);
        this.buttonMinusTeam2.setOnClickListener(this);
        this.buttonPlusTeam2=findViewById(R.id.plusButtonTeam2);
        this.buttonPlusTeam2.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//Inflate the menu from XML
        getMenuInflater().inflate(R.menu.main_menu, menu);
//Change the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.night_mode){
            Toast.makeText(this,"fajfd",Toast.LENGTH_SHORT).show();
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode==AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializarPuntuaciones(){
        this.team1ScoreInt=0;
        this.team2ScoreInt=0;
    }

    private void increaseScore(View view){
        if (view.equals(this.buttonPlusTeam1)){
            this.team1ScoreInt++;
            this.team1Score.setText(Integer.toString(this.team1ScoreInt));
        }else{
            this.team2ScoreInt++;
            this.team2Score.setText(Integer.toString(this.team2ScoreInt));
        }
    }

    private void decreaseScore(View view){
        if (view.equals(this.buttonMinusTeam1)){
            this.team1ScoreInt--;
            this.team1Score.setText(Integer.toString(this.team1ScoreInt));
        }else if(view.equals(this.buttonMinusTeam2)){
            this.team2ScoreInt--;
            this.team2Score.setText(Integer.toString(this.team2ScoreInt));
        }
    }

    @Override
    public void onClick(View view) {
        if (view.equals(this.buttonPlusTeam1)||view.equals(this.buttonPlusTeam2)){
            increaseScore(view);
        }else if(view.equals(this.buttonMinusTeam1)||view.equals(this.buttonMinusTeam2)){
            decreaseScore(view);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("score1",this.team1ScoreInt);
        outState.putInt("score2",this.team2ScoreInt);
        super.onSaveInstanceState(outState);
    }
}