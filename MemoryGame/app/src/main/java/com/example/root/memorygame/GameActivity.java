package com.example.root.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    //***********************************************************
    //
    // Global variables

    //
    // HashMap for keeping the image "behind" the button
    //  Key:    Button ID
    //  Value:  Image Resource ID
    //
    final HashMap<Integer, Integer> hidden_images = new HashMap<>(20);

    //
    // List of image resources
    //
    ArrayList<Integer> imageIDs = new ArrayList(20);

    //
    // List of correct answers for screen orientation
    //
    ArrayList<Integer> checkedButtons = new ArrayList<>();
    int points = 0;
    int reveal_time = 500;
    boolean firstRun = true;
    //
    //
    //**********************************************************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        loadImages();

        createGame();

        //back button handle
        Bundle extras = this.getIntent().getExtras();
        if (extras != null) {

            firstRun = false;

            hidden_images.putAll((HashMap<Integer, Integer>) extras.get("hashmap"));
            checkedButtons.addAll(extras.getIntegerArrayList("checkedbuttons"));

            Log.v("siiize", " size: " + checkedButtons.size());
            Iterator cbit = checkedButtons.iterator();
            while (cbit.hasNext()) {
                int id = (int) cbit.next();

                Log.v("button", "" + id);
                ImageButton a = (ImageButton) findViewById(id);
                if (a.getId() >= 0) {
                    a.setEnabled(false);
                    a.setImageResource(R.drawable.check);
                }
            }

            int p = (int) extras.get("points");
            changePoints(p);


        }



        //
        // Bring "Memory Game" above the table
        //
        TextView title = (TextView) findViewById(R.id.game_title);
        title.bringToFront();


    }

    //*****************************************
    //
    //  void loadImages
    //
    //      Description:
    //
    //          Loads Images from files into ArrayList
    //
    //
    //*****************************************

    public void loadImages() {

        //
        // Add image IDs to imgIDs list
        //
        for (int i = 0; i < 2; i++) {

            imageIDs.add(R.drawable.i1);
            imageIDs.add(R.drawable.i2);
            imageIDs.add(R.drawable.i3);
            imageIDs.add(R.drawable.i4);
            imageIDs.add(R.drawable.i5);
            imageIDs.add(R.drawable.i6);
            imageIDs.add(R.drawable.i7);
            imageIDs.add(R.drawable.i8);
            imageIDs.add(R.drawable.i9);
            imageIDs.add(R.drawable.i10);

        }

        Collections.shuffle(imageIDs);

    }

    //*************************************************************
    //
    //  public void createGame()
    //
    //  Description:
    //      Handles Logic of the game
    //          1) Create image buttons with randomized images
    //          2) Create click handler for each button
    //              Onclick functionality:
    //                  a) Store previous button clicked into LinkedList
    //                  b) Compare resource ID's and change points accordingly
    //
    //
    //*************************************************************

    public void createGame() {


        //
        // Layout for game to be inserted
        //
        TableLayout tl = (TableLayout) findViewById(R.id.tableLayout);

        //
        // Array of game buttons
        //
        ImageButton[] imgButtons = new ImageButton[20];

        //
        // user's last picked button
        //
        final List<ImageButton> selectedList = new ArrayList(1);

        int id = 0;
        int rows = 5;
        int cols = 4;


        for (int i = 0; i < rows; i++) {


            TableRow tr = new TableRow(this);

            for (int j = 0; j < cols; j++) {

                imgButtons[i] = new ImageButton(this);
                imgButtons[i].setId(id++);

                imgButtons[i].setImageDrawable(

                        ContextCompat.getDrawable(GameActivity.this, R.drawable.hiddenx)

                );

                //
                // store image ID in map to check later
                //
                if (firstRun) {

                    hidden_images.put(imgButtons[i].getId(),
                            imageIDs.get(id - 1));

                }

                imgButtons[i].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        final ImageButton button = (ImageButton) v;


                        button.setImageDrawable(

                                ContextCompat.getDrawable(GameActivity.this, hidden_images.get(button.getId()))

                        );

                        //
                        //  Checks if there was a button previously selected
                        //  if not, add to list
                        //

                        if (selectedList.isEmpty()) {

                            selectedList.add(button);

                        } else if (hidden_images.get(selectedList.get(0).getId())
                                .equals(hidden_images.get(button.getId()))
                                &&
                                button.getId() != selectedList.get(0).getId())

                        {// Else if there is a button compare images and ID's

                            //
                            // Correct! Add a point!
                            //
                            changePoints(1);


                            ImageButton secondButton = selectedList.get(0);
                            secondButton.setImageDrawable(
                                    ContextCompat.getDrawable(GameActivity.this, hidden_images.get(secondButton.getId()))
                            );

                            //
                            //Remove buttons from play and replace with check
                            //
                            secondButton.setEnabled(false);

                            button.setEnabled(false);

                            checkedButtons.add(secondButton.getId());
                            checkedButtons.add(button.getId());

                            secondButton.setImageResource(R.drawable.check);
                            button.setImageResource(R.drawable.check);

                            //
                            // remove selected item
                            //
                            selectedList.remove(0);

                        } else { //incorrect choice

                            changePoints(-1);

                            final ImageButton secondButton = selectedList.get(0);


                            //
                            // Display the images
                            //
                            secondButton.setImageDrawable(
                                    ContextCompat.getDrawable(GameActivity.this, hidden_images.get(secondButton.getId()))
                            );
                            button.setImageDrawable(
                                    ContextCompat.getDrawable(GameActivity.this, hidden_images.get(button.getId()))
                            );


                            //
                            // Thread to allow the incorrect images to be revealed
                            //

                            v.post(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(reveal_time);
                                    } catch (Exception e) {

                                    }
                                    secondButton.setImageDrawable(
                                            ContextCompat.getDrawable(GameActivity.this, R.drawable.hiddenx)
                                    );
                                    button.setImageDrawable(
                                            ContextCompat.getDrawable(GameActivity.this, R.drawable.hiddenx)
                                    );
                                }
                            });

                            selectedList.remove(0);

                        }

                    }
                });
                tr.addView(imgButtons[i]);
            }
            tl.addView(tr);
        }
    }


    public void changePoints(int p) {
        points += p;

        TextView tv = (TextView) findViewById(R.id.score);
        tv.setText("Points: " + points);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putSerializable("hashmap", hidden_images);
        savedInstanceState.putSerializable("checkedbuttons", checkedButtons);
        savedInstanceState.putInt("points", points);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        firstRun = false;

        hidden_images.putAll((HashMap<Integer, Integer>) savedInstanceState.getSerializable("hashmap"));
        checkedButtons.addAll((ArrayList<Integer>) savedInstanceState.getSerializable("checkedbuttons"));

        Iterator cbit = checkedButtons.iterator();
        while (cbit.hasNext()) {
            ImageButton a = (ImageButton) findViewById((Integer) cbit.next());
            a.setEnabled(false);
            a.setImageResource(R.drawable.check);

        }

        int p = savedInstanceState.getInt("points");
        changePoints(p);


    }

    @Override
    public void onBackPressed() {

        Intent i = new Intent(GameActivity.this, MainActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("hashmap", hidden_images);
        i.putIntegerArrayListExtra("checkedbuttons", checkedButtons);
        i.putExtra("points", points);

        startActivity(i);
        super.onBackPressed();

    }

}
