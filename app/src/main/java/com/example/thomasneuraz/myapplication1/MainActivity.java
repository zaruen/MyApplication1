package com.example.thomasneuraz.myapplication1;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.thomasneuraz.myapplication1.MESSAGE";

    private String mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Resources resource = getResources();


        Button buttonToDynamicButton = (Button)findViewById(R.id.btn_goto_dynamicButton);
        buttonToDynamicButton.setText(resource.getString(R.string.btn_page, "Dynamic Button"));

        Button buttonToBlocNote = (Button)findViewById(R.id.btn_goto_blocNote);
        buttonToBlocNote.setText(resource.getString(R.string.btn_page, "Bloc Note"));

        Button buttonToList = (Button)findViewById(R.id.btn_goto_list);
        buttonToList.setText(resource.getString(R.string.btn_page, "List"));

        Button buttonToMenu = (Button)findViewById(R.id.btn_goto_menu);
        buttonToMenu.setText(resource.getString(R.string.btn_page, "Menu"));

        Button buttonToChess = (Button)findViewById(R.id.btn_goto_menu);
        buttonToChess.setText(resource.getString(R.string.btn_page, "ChessBoard"));

        Button buttonToIntents = (Button)findViewById(R.id.btn_goto_intents);
        buttonToIntents.setText(resource.getString(R.string.btn_page, "My Intents"));

        // Initialize member TextView so we can manipulate it later
        //mTextView = (TextView) findViewById(R.id.text_message);

        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            // For the main activity, make sure the app icon in the action bar
//            // does not behave as a button
//            ActionBar actionBar = getActionBar();
//            actionBar.setHomeButtonEnabled(false);
//        }
    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        // Release the Camera because we don't need it when paused
        // and other activities might need to use it.
        //if (mCamera != null) {
        //    mCamera.release();
        //    mCamera = null;
        //}
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        // Get the Camera instance as the activity achieves full user focus
        //if (mCamera == null) {
        //    initializeCamera(); // Local method to handle camera init
        //}
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();  // Always call the superclass method first

        // The activity is either being restarted or started for the first time
        // so this is where we should make sure that GPS is enabled
//        LocationManager locationManager =
//                (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//
//        if (!gpsEnabled) {
            // Create a dialog here that requests the user to enable GPS, and use an intent
            // with the android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS action
            // to take the user to the Settings screen to enable GPS when they click "OK"
        //}
    }

    @Override
    protected void onRestart() {
        super.onRestart();  // Always call the superclass method first

        // Activity being restarted from stopped state
    }

    @Override
    public void onDestroy() {
        super.onDestroy();  // Always call the superclass

        // Stop method tracing that the activity started during onCreate()
        android.os.Debug.stopMethodTracing();
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void goToPage(View view){
        Intent intent = new Intent(this, DynamicButtonActivity.class);
        startActivity(intent);
    }

    public void goToBlocNote(View view){
        Intent intent = new Intent(this, BlocNoteActivity.class);
        startActivity(intent);
    }

    public void goToList(View view){
        Intent intent = new Intent(this, MyListActivity.class);
        startActivity(intent);
    }

    public void goToMenu(View view){
        Intent intent = new Intent(this, MyMenuActivity.class);
        startActivity(intent);
    }

    public void goToChess(View view){
        Intent intent = new Intent(this, ChessBoardActivity.class);
        startActivity(intent);
    }

    public void goToIntents(View view){
        Intent intent = new Intent(this, MyIntentsActivity.class);
        startActivity(intent);
    }
}
