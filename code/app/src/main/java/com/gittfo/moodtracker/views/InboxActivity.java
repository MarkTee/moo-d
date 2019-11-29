package com.gittfo.moodtracker.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gittfo.moodtracker.database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * This is an activity for users to manage their inbox, containing things like follow requests.
 */
public class InboxActivity extends AppCompatActivity {

    private RecyclerView inboxViews;
    private List<Pair<String, String>> inboxItems;
    private BottomAppBar bottomAppBar;

    protected void onCreate(Bundle savedInstanceState){
        bottomAppBar = new BottomAppBar(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        bottomAppBar.setListeners();

        // Hide the ActionBar
        getSupportActionBar().hide();

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
        View layout = inflater.inflate(R.layout.dialog_allow_follow_request, null);
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
                .inflate(R.layout.follow_request_notification, parent, false);
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
