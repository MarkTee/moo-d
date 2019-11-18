package com.gittfo.moodtracker.views.addmood;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeholder_username); // Use a poorly made placeholder XML file

        usernameView = findViewById(R.id.username);
        updateButton = findViewById(R.id.update_username_button);
        c = this;

        String  username = Database.get(this).getUserName();
        usernameView.setText(Database.get(this).getUserName());

    }

    public void updateUsername(View view){
        try {
            Database.get(this).setUserName(usernameView.getText().toString());
        } catch (UserNameExists userNameExists) {
            // TODO: notify user that name exists
            userNameExists.printStackTrace();
        }
    }
}
