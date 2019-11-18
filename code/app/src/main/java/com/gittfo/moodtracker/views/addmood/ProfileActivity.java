package com.gittfo.moodtracker.views.addmood;

import android.content.Context;
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
import com.gittfo.moodtracker.views.R;

/**
 * An activity for managing a user's profile.
 */
public class ProfileActivity extends AppCompatActivity {
    private EditText usernameView;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeholder_username); // Use a poorly made placeholder XML file

        usernameView = findViewById(R.id.username);
        updateButton = findViewById(R.id.update_username_button);

        usernameView.setText(Database.get(this).getUserName());

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
}
