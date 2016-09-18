package com.example.root.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;


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
        final Intent intent = new Intent(MainActivity.this, GameActivity.class);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);

        // set title
        alertDialogBuilder.setTitle("Resume Game?");

        // set dialog message
        alertDialogBuilder
                .setMessage("")
                .setCancelable(false)
                .setPositiveButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        startActivity(intent);
                        MainActivity.this.finish();

                    }
                })
                .setNegativeButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        if (!hidden_images.isEmpty()) {
                            intent.putExtra("hashmap", hidden_images);
                            intent.putExtra("checkedbuttons", checkedButtons);
                            intent.putExtra("points", points);
                        }
                        startActivity(intent);

                        MainActivity.this.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();






    }

    public void showRules(View v) {
        startActivity(new Intent(MainActivity.this, RulesActivity.class));
    }

}
