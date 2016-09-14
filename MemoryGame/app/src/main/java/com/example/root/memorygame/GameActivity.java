package com.example.root.memorygame;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        HashMap image_count = new HashMap(21);

        //
        //randomly assign images to ImageButtons
        //
        for (int i = 0; i <= 20; i++) {
            ImageButton imgButton = (ImageButton) getViewById();

            image_count.put();


        }
    }


}
