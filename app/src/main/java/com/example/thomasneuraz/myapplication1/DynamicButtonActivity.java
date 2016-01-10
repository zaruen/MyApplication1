package com.example.thomasneuraz.myapplication1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class DynamicButtonActivity extends AppCompatActivity {

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Button bouton = (Button)v;

            SetDynamicButtonSize(event, bouton);

            return true;
        }
    };

    private void SetDynamicButtonSize(MotionEvent event, Button bouton) {
        int largeur = bouton.getWidth();
        int hauteur = bouton.getHeight();

        float x = event.getX();
        float y = event.getY();

        bouton.setTextSize(Math.abs(x - largeur / 2) + Math.abs(y - hauteur / 2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_button);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coucou hibou", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button button = (Button)findViewById(R.id.btn_dynamicButton);
        button.setOnTouchListener(touchListener);

    }

    public void startAnimation(View view){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.firstanim);

        view.startAnimation(animation);
    }

}
