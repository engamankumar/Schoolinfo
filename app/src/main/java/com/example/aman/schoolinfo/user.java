package com.example.aman.schoolinfo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user extends AppCompatActivity {
    private FirebaseAuth mAuth;
    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;
    private GcmNetworkManager mGcmNetworkManager;
    public static final String TASK_TAG_PERIODIC = "periodic_task";

    private static final String TAG = "MainActivity";
    private BroadcastReceiver mReceiver;
    private static final int RC_PLAY_SERVICES = 123;
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        final TextView t1,d1,sub1;
        final CheckBox c1;
        t1 = (TextView) findViewById(R.id.notify);
        d1 = (TextView) findViewById(R.id.udate);
        sub1 = (TextView) findViewById(R.id.usubject);
        mGcmNetworkManager = GcmNetworkManager.getInstance(user.this);
        checkPlayServicesAvailable();

        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference myRef = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("newmessage");

        DatabaseReference extract  = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("oldmessage");
        extract.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(value != null)
                {

                    t1.setText(value); }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        DatabaseReference extractdate  = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("olddate");
        extractdate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(value != null)
                {

                    d1.setText(value); }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        DatabaseReference extractsubject  = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("oldsubject");
        extractsubject.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                if(value != null)
                {

                    sub1.setText(value); }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Alert.ACTION_DONE)) {
                    String tag = intent.getStringExtra(Alert.EXTRA_TAG);
                    int result = intent.getIntExtra(Alert.EXTRA_RESULT, -1);

                    String msg = String.format("DONE: %s (%d)", tag, result);

                }
                else {
                    return;
                }
            }
        };

        Log.d(TAG, "startPeriodicTask");

        // [START start_periodic_task]
        PeriodicTask task = new PeriodicTask.Builder()
                .setService(Alert.class)
                .setTag(TASK_TAG_PERIODIC)
                .setPeriod(20L)
                .setPersisted(true)

                .build();

        mGcmNetworkManager.schedule(task);


    }

    @Override
    public void onStart() {
        super.onStart();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Alert.ACTION_DONE);

        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(getApplicationContext());
        manager.registerReceiver(mReceiver, filter);

    }

    public void startPeriodicTask() {

        // [END start_periodic_task]
    }

    public void stopPeriodicTask() {
        Log.d(TAG, "stopPeriodicTask");

        // [START stop_periodic_task]
        mGcmNetworkManager.cancelTask(TASK_TAG_PERIODIC, Alert.class);
        // [END stop_per
    }

    private void checkPlayServicesAvailable() {
        GoogleApiAvailability availability = GoogleApiAvailability.getInstance();
        int resultCode = availability.isGooglePlayServicesAvailable(user.this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (availability.isUserResolvableError(resultCode)) {
                // Show dialog to resolve the error.
                availability.getErrorDialog(user.this, resultCode, RC_PLAY_SERVICES).show();
            } else {
                // Unresolvable error
                Toast.makeText(user.this, "Google Play Services error", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}