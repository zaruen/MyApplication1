package com.example.thomasneuraz.myapplication1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.util.Log;

/**
 * Created by thomasneuraz on 09/01/16.
 */
public class MyImageGetter implements ImageGetter {
    /* Context de notre activité */
    protected Context context = null;

    public MyImageGetter(Context c) {
        context = c;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    /**
     * Donne un smiley en fonction du paramètre d'entrée
     * @param smiley Le nom du smiley à afficher
     */
    public Drawable getDrawable(String smiley) {
        Drawable retour = null;

        Log.d("TOTO", smiley);

        // On récupère le gestionnaire de ressources
        Resources resources = context.getResources();

        // Si on désire le clin d'œil…
        if(smiley.compareTo("smiley1") == 0)
            // … alors on récupère le drawable correspondant
            retour = ContextCompat.getDrawable(context, R.drawable.smiley1);
        else if(smiley.compareTo("smiley2") == 0)
            retour = ContextCompat.getDrawable(context, R.drawable.smiley2);
        else
            retour = ContextCompat.getDrawable(context, R.drawable.smiley3);
        // On délimite l'image (elle va de son coin en haut à gauche à son coin en bas à droite)
        retour.setBounds(0, 0, retour.getIntrinsicWidth(), retour.getIntrinsicHeight());
        return retour;
    }
}
