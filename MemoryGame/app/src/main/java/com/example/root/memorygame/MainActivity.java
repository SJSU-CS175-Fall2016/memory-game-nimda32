package com.example.root.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
    }

// startActivityForResult
//    on activi

// to start activity you need an intent
//    openDemo()View view
//    intent demointenet= new intent this,demoactivity.class
//demointent.putextra(bsad,dsads);
//    startactivity(demoactivity

    public void startGame(View v){
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }

    public void showRules(View v){
        startActivity(new Intent(MainActivity.this, RulesActivity.class));
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//outState.set(blahhh)
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        savedInstances ...
    }
}
