package com.gittfo.moodtracker.views.addmood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.database.UserNameExists;
import com.gittfo.moodtracker.views.MainActivity;
import com.gittfo.moodtracker.views.R;

/**
 * An activity for managing a user's profile.
 */
public class ProfileActivity extends AppCompatActivity {
    private EditText usernameView;
    private Button updateButton;
    private TextView welcomeBox;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.username_screen); // Use a poorly made placeholder XML file

        usernameView = findViewById(R.id.username);
        updateButton = findViewById(R.id.update_username_button);
        welcomeBox = findViewById(R.id.welcome_message);


        Database.get(this).getUserName(username -> {
            if(username != null){
                if (username.equals("")){
                    usernameView.setText("You must set an username");
                    updateButton.setText("Update Username");
                }else{
                    updateButton.setText("Continue");
                    usernameView.setText(username);
                    welcomeBox.setVisibility(View.VISIBLE);
                }
            }
        });




    }

    /**
     * called by onClick of update_user_button Button.
     * Pushes the username from the EditText to the database.
     * Potentially notifies the user if that username already exists.
     * @param view
     */
    public void updateUsername(View view){
        try {
            Database.get(this).setUserName(usernameView.getText().toString());
            startMain();
        } catch (UserNameExists userNameExists) {
            // TODO: notify user that name exists
            new AlertDialog.Builder(this)
                    .setTitle("Invalid Username")
                    .setMessage("Someone already uses that username, please come up with a new one.")
                    .setPositiveButton(android.R.string.ok, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            userNameExists.printStackTrace();
        }
    }


    public void startMain(){
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }


}
