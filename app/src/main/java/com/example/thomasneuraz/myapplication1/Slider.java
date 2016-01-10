package com.example.thomasneuraz.myapplication1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by thomasneuraz on 09/01/16.
 */
public class Slider extends LinearLayout {

    private boolean isOpen = true;
    private RelativeLayout toHide = null;

    private final static int SPEED = 300;

    private Animation.AnimationListener closeListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            toHide.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    private Animation.AnimationListener openListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            toHide.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    public Slider(Context context) {
        super(context);
    }

    /**
     * Constructeur utilisé pour l'initialisation en XML.
     * @param context Le contexte de l'activité.
     * @param attrs Les attributs définis dans le XML.
     */
    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Utilisée pour ouvrir ou fermer le menu.
     * @return true si le menu est désormais ouvert.
     */
    public boolean toggle() {
        //Animation de transition.
        TranslateAnimation animation = null;

        // On passe de ouvert à fermé (ou vice versa)
        isOpen = !isOpen;

        // Si le menu est déjà ouvert
        if (isOpen)
        {
            // Animation de translation du bas vers le haut
            animation = new TranslateAnimation(0.0f, 0.0f, -toHide.getHeight(), 0.0f);
            animation.setAnimationListener(openListener);
        } else
        {
            // Sinon, animation de translation du haut vers le bas
            animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, -toHide.getHeight());
            animation.setAnimationListener(closeListener);
        }

        // On détermine la durée de l'animation
        animation.setDuration(SPEED);
        // On ajoute un effet d'accélération
        animation.setInterpolator(new AccelerateInterpolator());
        // Enfin, on lance l'animation
        startAnimation(animation);

        return isOpen;
    }

    public void setToHide(RelativeLayout toHide) {
        this.toHide = toHide;
    }

}
