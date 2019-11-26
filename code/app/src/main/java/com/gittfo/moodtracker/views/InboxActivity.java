package com.gittfo.moodtracker.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.database.Database;
import com.gittfo.moodtracker.views.addmood.AddMoodEventActivity;
import com.gittfo.moodtracker.views.map.MapActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This is an activity for users to manage their inbox, containing things like follow requests.
 */
public class InboxActivity extends AppCompatActivity {

    private RecyclerView inboxViews;
    private List<Pair<String, String>> inboxItems;
    private ImageButton dropDownButton;
    private ColorSchemeDialog colorDialog;
    private static int DEFAULT_THEME_ID = R.style.AppTheme;

    protected void onCreate(Bundle savedInstanceState){
        setTheme(DEFAULT_THEME_ID);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        // Hide the ActionBar
        getSupportActionBar().hide();

        colorDialog = new ColorSchemeDialog(this);

        inboxItems = new ArrayList<>();
        inboxViews = findViewById(R.id.inbox_items_view);
        inboxViews.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        inboxViews.setLayoutManager(new LinearLayoutManager(this));
        inboxViews.setAdapter(new InboxRCAdapter(inboxItems, this));

        Database.get(this).getFollowRequests(followRequests -> {
            Log.d("JDB", "Got all requests");
            for (String fromUserId : followRequests) {
                Log.d("JDB", "Found a request: " + fromUserId);
                Database.get(this).getUsernameFromUserId(fromUserId, username -> {
                    Log.d("JDB", "This person wants to follow you: " + username);
                    inboxItems.add(new Pair<>(username, fromUserId));
                    inboxViews.getAdapter().notifyDataSetChanged();
                });
            }
        });
    }

    public void startSigninActivity(View view){
        Intent i = new Intent(this, SigninActivity.class);
        this.startActivity(i);
    }

    public void startUsernameActivity(View view){
        Intent i = new Intent(this, ChangeUsernameActivity.class);
        this.startActivity(i);
    }

    public void dropdownPressed(View view){
        dropDownButton = (ImageButton) findViewById(R.id.settings_button);
        dropDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a PopupMenu
                PopupMenu popup = new PopupMenu(com.gittfo.moodtracker.views.InboxActivity.this, dropDownButton);
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
                                startUsernameActivity(view);
                                break;
                            case (R.id.dropdown_two):
                                // change color scheme
                                onChangeColorSchemePressed();
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

    /**
     * When the New MoodEvent Button (the '+' icon in the bottom-middle of the screen) is clicked,
     * pass the user through to AddMoodEventActivity.
     *
     * @param view The New MoodEvent Button
     */
    public void createMoodEvent(View view) {
        Intent i = new Intent(this, AddMoodEventActivity.class);
        this.startActivity(i);
    }

    /**
     * When the "inbox" button is pressed, don't do anything (since we're already in InboxActivity)
     * @param view the Inbox button.
     */
    public void startTimelineActivity(View view) {
        // don't animate transition between activities
        Intent i = new Intent(this, TimelineActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    /**
     * When the "inbox" button is pressed, go to the inbox activity.
     * @param view the Inbox button.
     */
    public void startInboxActivity(View view) {
    }

    /**
     * When the "profile" button is pressed, don't do anything (since we're already in MainActivity)
     * @param view the Inbox button.
     */
    public void startProfileActivity(View view){
        // don't animate transition between activities
        Intent i = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    /**
     * When the "map" button is pressed, go the map-viewing activity.
     * @param view the Map button.
     */
    public void startMapActivity(View view) {
        Intent i = new Intent(this, MapActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    public void onChangeColorSchemePressed(){
        colorDialog.show();
    }

    public void validateNewTheme(){
        Intent i = new Intent(this, MapActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        this.startActivity(i);
    }

    public void applyColorScheme(View v) {
        int selectedButton = colorDialog.getSelectedNum();
        Log.d("Selected", Integer.toString(selectedButton));

        if (selectedButton == 0) {
            DEFAULT_THEME_ID = R.style.AppTheme;
        } else if (selectedButton == 1) {
            DEFAULT_THEME_ID = R.style.NeonTheme;
        } else if (selectedButton == 2) {
            DEFAULT_THEME_ID = R.style.MonochromeTheme;
        } else if (selectedButton == 3) {
            DEFAULT_THEME_ID = R.style.PastelTheme;
        }

        colorDialog.cancel();
        applyToAllActivities();
        validateNewTheme();
    }

    public void applyToAllActivities() {
        MapActivity.setDefaultTheme(DEFAULT_THEME_ID);
        MainActivity.setDefaultTheme(DEFAULT_THEME_ID);
        TimelineActivity.setDefaultTheme(DEFAULT_THEME_ID);
    }

    public static void setDefaultTheme(int THEME_ID) {
        DEFAULT_THEME_ID = THEME_ID;
    }

    /**
     * For smoother transitions between activities, disable animations when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, 0);
    }
}
class ResponseDialog {
    private Activity c;
    private String usrname;
    private String usrid;

    public ResponseDialog(Activity c, String usrname, String usrid) {
        this.c = c;
        this.usrname = usrname;
        this.usrid = usrid;
    }

    public void open(Consumer<Boolean> callback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        LayoutInflater inflater = c.getLayoutInflater();
        View layout = inflater.inflate(R.layout.mood_following_permission, null);
        builder.setView(layout);

        ((TextView) layout.findViewById(R.id.requesting_username)).setText(this.usrname);

        AlertDialog ad = builder.create();

        layout.findViewById(R.id.cancel_permission_button).setOnClickListener(v -> ad.cancel());

        layout.findViewById(R.id.allow_button).setOnClickListener(v -> {
            ad.cancel();
            callback.accept(true);
        });
        layout.findViewById(R.id.deny_button).setOnClickListener(v -> {
            ad.cancel();
            callback.accept(false);
        });

        ad.show();
    }
}
class InboxRCViewHolder extends RecyclerView.ViewHolder {

    View layout;
    public InboxRCViewHolder(@NonNull View itemView) {
        super(itemView);
        this.layout = itemView;
    }

    public View getLayout() {
        return layout;
    }
}
class InboxRCAdapter extends RecyclerView.Adapter<InboxRCViewHolder> {

    private final List<Pair<String, String>> items;
    private Activity c;

    InboxRCAdapter(List<Pair<String, String>> items, Activity c) {
        this.items = items;
        this.c = c;

    }

    @NonNull
    @Override
    public InboxRCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_inbox_notification, parent, false);
        return new InboxRCViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxRCViewHolder holder, int position) {
        Log.d("JUI", "Setting inbox for loc: " + position);
        TextView usr = holder.getLayout().findViewById(R.id.follow_request_username);
        usr.setText(items.get(position).first);
        ResponseDialog rd = new ResponseDialog(c, items.get(position).first, items.get(position).second);
        holder.getLayout().setOnClickListener(v ->
            rd.open(didFollow -> {
                Database.get(c).completeFollowRequest(items.get(position).second, didFollow);
                items.remove(position);
                this.notifyDataSetChanged();
                Log.d("JUI", "Removing old follow");
            }));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
