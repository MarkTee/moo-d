package com.gittfo.moodtracker.views.addmood;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.views.R;

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
                        //TODO: Make this do something useful
                        Toast.makeText(
                                InboxActivity.this,
                                "You clicked: " + menuItem.getTitle(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show(); // show popup menu
            }
        }); // close setOnClickListener method

    }
}
