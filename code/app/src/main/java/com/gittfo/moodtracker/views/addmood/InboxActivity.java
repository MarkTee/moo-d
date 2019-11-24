package com.gittfo.moodtracker.views.addmood;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.views.R;
import com.gittfo.moodtracker.views.SigninActivity;

/**
 * This is an activity for users to manage their inbox, containing things like follow requests.
 */
public class InboxActivity extends AppCompatActivity {

    private ImageButton dropDownButton;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    public void startProfileActivity(View view){
        Intent i = new Intent(this, ProfileActivity.class);
        this.startActivity(i);
    }

    public void startSigninActivity(View view){
        Intent i = new Intent(this, SigninActivity.class);
        this.startActivity(i);

    }

    public void dropdownPressed(View view){
        dropDownButton = (ImageButton) findViewById(R.id.settings_button);
        dropDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a PopupMenu
                PopupMenu popup = new PopupMenu(InboxActivity.this, dropDownButton);
                // inflate the popup via xml file
                popup.getMenuInflater()
                        .inflate(R.menu.dropdown_menu, popup.getMenu());

                // tie popup to OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch(menuItem.getItemId()) {
                            case (R.id.dropdown_one):
                                // change username
                                startProfileActivity(view);
                                break;
                            case (R.id.dropdown_two):
                                // change color scheme
                                //TODO: color scheme change functionality
                                Toast.makeText(InboxActivity.this,
                                        "Coming soon!",
                                        Toast.LENGTH_SHORT).show();
                                break;
                            case (R.id.dropdown_three):
                                //TODO: allow for proper log out?
                                startSigninActivity(view);
                                break;
                        }
                        return true;
                    }
                });
                popup.show(); // show popup menu
            }
        }); // close setOnClickListener method

    }
}
