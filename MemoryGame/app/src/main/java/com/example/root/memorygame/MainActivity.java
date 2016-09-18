package com.example.root.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    HashMap<Integer, Integer> hidden_images = new HashMap<>(21);
    ArrayList<Integer> checkedButtons = new ArrayList<>();
    int points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //
        //back button pressed
        //

        Bundle extras = this.getIntent().getExtras();

        if (extras != null) { //store game data

            hidden_images.putAll((HashMap<Integer, Integer>) extras.getSerializable("hashmap"));
            checkedButtons.addAll(extras.getIntegerArrayList("checkedbuttons"));
            points = extras.getInt("points");

        }


    }

    public void startGame(View v) {

        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        if(!hidden_images.isEmpty()) {
            intent.putExtra("hashmap", hidden_images);
            intent.putExtra("checkedbuttons", checkedButtons);
            intent.putExtra("points", points);
        }
        startActivity(intent);

    }

    public void showRules(View v) {
        startActivity(new Intent(MainActivity.this, RulesActivity.class));
    }

}
