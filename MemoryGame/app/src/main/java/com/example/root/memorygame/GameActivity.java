package com.example.root.memorygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GameActivity extends AppCompatActivity{

    int points = 0;

    List<Integer> imageIDs = new ArrayList(20);
    final Iterator<Integer> imgIt = imageIDs.iterator();
    //
    //
    // HashMap for keeping the hidden resources "behind" the button
    //
    final HashMap<ImageButton, Integer> hidden_images = new HashMap<>(20);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        loadImages();

        createGameButtons();



    }


    public void loadImages(){
        //
        // Add image IDs to list
        //
        for(int i = 0 ; i < 2; i++){

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

    public void createGameButtons(){

        //
        // game buttons
        //
        ImageButton[] imgButtons = new ImageButton[20];

        //
        // selected buttons
        //

        final List<ImageButton> selectedList = new ArrayList(2);

        TableLayout tl = (TableLayout) findViewById(R.id.tableLayout);

        int count = 0;
        int rows = 5;
        int cols = 4;

        for ( int i = 0  ;  i < rows  ;  i++ ) {

            TableRow tr = new TableRow(this);

            for ( int j = 0  ;  j < cols  ;  j++ ) {

                imgButtons[i] = new ImageButton(this);

                imgButtons[i].setId(count++);

                //
                // set preclicked image
                //
                imgButtons[i].setBackgroundResource(R.drawable.hiddenx);


                //
                // store image ID and button to check matches later
                //
                hidden_images.put(imgButtons[i], imgIt.next());



                imgButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ImageButton button = (ImageButton ) v;


                        button.setBackgroundResource(
                                hidden_images.get(v)
                        );

                        if (selectedList.isEmpty()) {

                            selectedList.add(button);

                        } else if (hidden_images.get(selectedList.get(0)).equals(hidden_images.get(button))) { //and their ID's are not the same
                            Log.v("TRUE!", "ids matched!");

//                            selectedList.get(0).getButtonDrawable().equals(button.getButtonDrawable())
                            points++;

                            ImageButton secondButton = selectedList.get(0);
                            secondButton.setBackgroundResource(
                                    hidden_images.get(secondButton)
                            );


                            secondButton.setEnabled(false);
                            button.setEnabled(false);

                            secondButton.setBackgroundResource(R.drawable.check);
                            button.setBackgroundResource(R.drawable.check);
                            selectedList.remove(0);

                        } else {
                            Log.v("Not equal", "second button:" + hidden_images.get(selectedList.get(0))+"|button"+hidden_images.get(button));

                            points--;

                            ImageButton secondButton = selectedList.get(0);


                            button.setBackgroundResource(
                                    hidden_images.get(button)
                            );

//                                ContextCompat.getDrawable(GameActivity.this, hidden_images.get(secondButton))


                            secondButton.setBackgroundResource(R.drawable.hiddenx);
                            button.setBackgroundResource(R.drawable.hiddenx);
                            selectedList.remove(0);

                        }



                    }
                });

                tr.addView(imgButtons[i]);

            }

            tl.addView(tr);

        }

    }

}
