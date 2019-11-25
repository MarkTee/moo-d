package com.gittfo.moodtracker.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

public class ColorSchemeDialog {

    private AlertDialog colorDialog;
    Activity c;
    private boolean[] buttonState;

    /**
     * Create a new ColorSchemeDialog object
     *
     * @param c The activity context that the dialog will be created in
     */
    public ColorSchemeDialog(Activity c) {
        this.c = c;
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater inflater = c.getLayoutInflater();

        buttonState = new boolean[]{false, false, false, false};


        builder.setView(inflater.inflate(R.layout.color_scheme_dialog, null));
        builder.setTitle("Change Color Scheme");
        Log.d("JUI", "Making builder");
        colorDialog = builder.create();
    }

    /**
     * Display the ColorSchemeDialog and set OnClickListeners for each of the mood buttons.
     */
    public void show() {
        colorDialog.show();
        colorDialog.findViewById(R.id.default_theme_button).setOnClickListener(v -> this.updateButtonUI(v));
        colorDialog.findViewById(R.id.neon_theme_button).setOnClickListener(v -> this.updateButtonUI(v));
        colorDialog.findViewById(R.id.pastel_theme_button).setOnClickListener(v -> this.updateButtonUI(v));
        colorDialog.findViewById(R.id.monochrome_theme_button).setOnClickListener(v -> this.updateButtonUI(v));

    }

    /**
     * Update the color dialog when a button is selected.
     */
    private void updateButtonUI(View v) {
        View[] buttons = new View[]{
                colorDialog.findViewById(R.id.default_theme_button),
                colorDialog.findViewById(R.id.neon_theme_button),
                colorDialog.findViewById(R.id.pastel_theme_button),
                colorDialog.findViewById(R.id.monochrome_theme_button),
        };

        ColorStateList[] tints = new ColorStateList[]{
                ContextCompat.getColorStateList(c, R.color.colorDefaultThemeButton),
                ContextCompat.getColorStateList(c, R.color.colorNeonThemeButton),
                ContextCompat.getColorStateList(c, R.color.colorPastelThemeButton),
                ContextCompat.getColorStateList(c, R.color.colorMonochromeThemeButton),
                ContextCompat.getColorStateList(c, R.color.design_default_color_background)
        };

        for (int i = 0; i < 4; i++){
            buttons[i].setBackgroundTintList(tints[4]);
        }

        if (v == buttons[0]) {
            buttons[0].setBackgroundTintList(tints[0]);
            for(int i = 0; i < 4; i++) {buttonState[i] = false; }
            buttonState[0] = true;
        } else if (v == buttons[1]) {
            buttons[1].setBackgroundTintList(tints[1]);
            for(int i = 0; i < 4; i++) {buttonState[i] = false; }
            buttonState[1] = true;
        } else if (v == buttons[2]) {
            buttons[2].setBackgroundTintList(tints[3]);
            for(int i = 0; i < 4; i++) {buttonState[i] = false; }
            buttonState[2] = true;
        } else if (v == buttons[3]) {
            buttons[3].setBackgroundTintList(tints[2]);
            for(int i = 0; i < 4; i++) {buttonState[i] = false; }
            buttonState[3] = true;
        }

    }

    public int getSelectedNum() {
        for (int i = 0; i < 4; i++) {
            if (buttonState[i]) {
                return i;
            }
        }
        return 0;
    }

    /**
     * Close the ColorSchemeDialog.
     */
    public void cancel() {
        colorDialog.cancel();
    }
}