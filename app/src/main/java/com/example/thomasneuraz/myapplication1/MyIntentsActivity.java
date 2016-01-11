package com.example.thomasneuraz.myapplication1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MyIntentsActivity extends AppCompatActivity {

    Chronometer chrono;
    // id de la request
    public static final int CHOOSE_BTN_REQUEST = 0;
    //id du string qui contient le resultat de la request
    public static final String BUTTONS = "com.example.thomasneuraz.myapplication1.Boutons";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_intents);

        Button bibiBtn = (Button)findViewById(R.id.call_cassandre);
        bibiBtn.setText("Bibi");
        bibiBtn.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
                Toast.makeText(MyIntentsActivity.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Toast.makeText(MyIntentsActivity.this, "right", Toast.LENGTH_SHORT).show();
                Intent send = new Intent(Intent.ACTION_SENDTO);
                String uriText = "mailto:" + Uri.encode("daigre.cassandre@gmail.com") +
                        "?subject=" + Uri.encode("Un email de mon appli android qui trou le cul") +
                        "&body=" + Uri.encode("Je t'aime Je t'aime Je t'aime");
                Uri uri = Uri.parse(uriText);

                send.setData(uri);
                startActivity(Intent.createChooser(send, "Send mail..."));
            }

            public void onSwipeLeft() {
                Toast.makeText(MyIntentsActivity.this, "left", Toast.LENGTH_SHORT).show();
                Uri telephone = Uri.parse("tel:93177906");
                Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);
                startActivity(secondeActivite);
            }

            public void onSwipeBottom() {
                Toast.makeText(MyIntentsActivity.this, "bottom", Toast.LENGTH_SHORT).show();
                Intent openCamera = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(openCamera);
            }
        });

        Button withResultBtn = (Button)findViewById(R.id.with_result);
        withResultBtn.setText("With Result");

        chrono = (Chronometer)findViewById(R.id.chronometer);
        //chrono.start();
    }


    public void onCallCassandre(View view){
        //chrono.stop();
        Uri telephone = Uri.parse("tel:93177906");
        Intent secondeActivite = new Intent(Intent.ACTION_DIAL, telephone);
        startActivity(secondeActivite);
    }

    public void onIntentWithResult(View view){
        Intent chessBoard = new Intent(this, ChessBoardActivity.class);
        startActivityForResult(chessBoard, CHOOSE_BTN_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == CHOOSE_BTN_REQUEST) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // On affiche le bouton qui a été choisi
                Toast.makeText(this, "Vous avez choisi le bouton " + data.getStringExtra(BUTTONS), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
