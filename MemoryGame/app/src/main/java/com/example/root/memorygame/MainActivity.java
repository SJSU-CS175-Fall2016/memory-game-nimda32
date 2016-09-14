package com.example.root.memorygame;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

// startActivityForResult
//    on activi

//    btn = (Button)findViewById(R.id.open_activity_button);
//
//    btn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            startActivity(new Intent(MainActivity.this, MyOtherActivity.class));
//        }
//    });

// to start activity you need an intent
//    openDemo()View view
//    intent demointenet= new intent this,demoactivity.class
//demointent.putextra(bsad,dsads);
//    startactivity(demoactivity


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
