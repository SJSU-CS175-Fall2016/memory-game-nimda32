package com.example.root.memorygame;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_game);

        List<Integer> images = new ArrayList(20);

        //
        //
        //
        for(int i = 0 ; i <= 2; i++){

            images.add(R.drawable.i1);
            images.add(R.drawable.i2);
            images.add(R.drawable.i3);
            images.add(R.drawable.i4);
            images.add(R.drawable.i5);
            images.add(R.drawable.i6);
            images.add(R.drawable.i7);
            images.add(R.drawable.i8);
            images.add(R.drawable.i9);
            images.add(R.drawable.i10);

        }


//        images.add(ResourcesCompat.getDrawable(getResources(), R.drawable.i1, null));
        //
        // randomly assign images to ImageButtons
        //
        Random ra = new Random();
        ImageButton[] imgButtons = new ImageButton[20];
        GridLayout gl = (GridLayout) findViewById(R.id.layout);
        int wh = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());

        for (int i = 0; i < 20; i++) {

            int rand = ra.nextInt(20);

            imgButtons[i] = new ImageButton(this);
            imgButtons[i].setImageResource(images.get(rand));
//            imgButtons[i].setLayoutParams(new GridLayout.LayoutParams());  NEED TO ADD PARAM TO FIX size of images!
            imgButtons[i].setOnClickListener(match);
//            imgButtons[i].setBackgroundColor(Color.TRANSPARENT);
            imgButtons[i].setTag(i);
            imgButtons[i].setId(i);

            gl.addView(imgButtons[i]);


            //  ImageButton imgButton = (ImageButton) getViewById();
            //  image_count.put();


        }

    }

    View.OnClickListener match = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

}
