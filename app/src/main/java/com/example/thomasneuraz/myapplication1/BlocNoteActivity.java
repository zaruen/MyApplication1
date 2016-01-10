package com.example.thomasneuraz.myapplication1;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BlocNoteActivity extends AppCompatActivity {

    private static final String TAG = BlocNoteActivity.class.getSimpleName();

    private TextView result;
    private EditText editor;
    private Resources resources;
    private Button hideShow = null;
    private Slider slider;
    private RelativeLayout toHide = null;

    private MyImageGetter getter = null;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Spanned formattedText = Html.fromHtml(editor.getText().toString(), getter, null);
            result.setText(formattedText);
        }
    };

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.noir:
                    setBalise(editor.getSelectionStart(), editor.getSelectionEnd(), "font", "#000000");
                    break;
                case R.id.blue:
                    setBalise(editor.getSelectionStart(), editor.getSelectionEnd(), "font", "#0000ff");
                    break;
                case R.id.red:
                    setBalise(editor.getSelectionStart(), editor.getSelectionEnd(), "font", "#ff0000");
                    break;
                    default:
                        editor.setTextColor(Color.parseColor("#000000"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloc_note);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editor = (EditText)findViewById(R.id.rawtext);
        editor.addTextChangedListener(textWatcher);

        result = (TextView)findViewById(R.id.result);

        resources = getResources();

        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.colorchoice);
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        Log.d(TAG, "Yo");

        getter = new MyImageGetter(this);

        //On récupère le menu
        toHide = (RelativeLayout) findViewById(R.id.toHide);

        //On récupère le bouton pour cacher/afficher le menu
        hideShow = (Button) findViewById(R.id.hideShow);
        //Puis, on recupère la vue racine de l'application et on change sa couleur
        hideShow.getRootView().setBackgroundColor(ContextCompat.getColor(this, R.color.background));
        hideShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                //...pour afficher ou cache le menu
                if (slider.toggle()) {
                    //Si le Slider est ouvert...
                    //... on change le texte en "Cacher"
                    hideShow.setText("Cacher");
                } else {
                    //Sinon on met "Afficher"
                    hideShow.setText("Afficher");
                }
            }
        });

        slider = (Slider)findViewById(R.id.slider);
        slider.setToHide(toHide);
    }

    public void onGrasClick(View view){
        setBalise(editor.getSelectionStart(), editor.getSelectionEnd(), "b");
    }
    public void onItalicClick(View view){
        setBalise(editor.getSelectionStart(), editor.getSelectionEnd(), "i");
    }
    public void onSouligneClick(View view){
        setBalise(editor.getSelectionStart(), editor.getSelectionEnd(), "u");
    }

    private void setBalise(int start, int end, String balise){
        String fulltext = editor.getText().toString();
        String start_part = fulltext.substring(0, start);
        String between = fulltext.substring(start, end);
        String end_part = fulltext.substring(end, fulltext.length());
        String open_balise = resources.getString(R.string.balise_start, balise);
        String close_balise = resources.getString(R.string.balise_end, balise);

        fulltext = start_part + open_balise + between + close_balise + end_part;

        editor.setText(fulltext);
    }

    private void setBalise(int start, int end, String balise, String color){
        String fulltext = editor.getText().toString();
        String start_part = fulltext.substring(0, start);
        String between = fulltext.substring(start, end);
        String end_part = fulltext.substring(end, fulltext.length());
        String open_balise = resources.getString(R.string.balise_color_start, balise, color);
        String close_balise = resources.getString(R.string.balise_end, balise);

        fulltext = start_part + open_balise + between + close_balise + end_part;

        editor.setText(fulltext);
    }

    public void onSmiley1Click(View view){
        int start = editor.getSelectionStart();
        editor.getText().insert(start, "<img src=\"smiley1\">");
    }
    public void onSmiley2Click(View view){
        int start = editor.getSelectionStart();
        editor.getText().insert(start, "<img src=\"smiley2\">");
    }
    public void onSmiley3Click(View view){
        int start = editor.getSelectionStart();
        editor.getText().insert(start, "<img src=\"smiley3\">");
    }

    public void onAfficherClick(View view){

    }
    public void onCacherClick(View view){
        if(slider.toggle())
        {
            //Si le Slider est ouvert...
            //... on change le texte en "Cacher"
            hideShow.setText("Cacher");
        }else
        {
            //Sinon on met "Afficher"
            hideShow.setText("Afficher");
        }
    }

}
