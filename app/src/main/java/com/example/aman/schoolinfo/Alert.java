package com.example.aman.schoolinfo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.location.LocationListener;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Alert extends GcmTaskService {

    NotificationCompat.Builder notification;
    private static final int uniqueID = 45612;

    private static final String TAG = "MyTaskService";

    public static final String ACTION_DONE = "GcmTaskService#ACTION_DONE";
    public static final String EXTRA_TAG = "extra_tag";
    public static final String EXTRA_RESULT = "extra_result";


    @Override
    public void onInitializeTasks() {
        // When your package is removed or updated, all of its network tasks are cleared by
        // the GcmNetworkManager. You can override this method to reschedule them in the case of
        // an updated package. This is not called when your application is first installed.
        //
        // This is called on your application's main thread.

        // TODO(developer): In a real app, this should be implemented to re-schedule important tasks.
    }

    @Override
    public int onRunTask(TaskParams taskParams) {
        Log.d(TAG, "onRunTask: " + taskParams.getTag());

        String tag = taskParams.getTag();

        // Default result is success.
        int result = GcmNetworkManager.RESULT_SUCCESS;

        // Choose method based on the tag.

        if (user.TASK_TAG_PERIODIC.equals(tag)) {
            result = doPeriodicTask();
        }

        // Create Intent to broadcast the task information.
        Intent intent = new Intent();
        intent.setAction(ACTION_DONE);
        intent.putExtra(EXTRA_TAG, tag);
        intent.putExtra(EXTRA_RESULT, result);

        // Send local broadcast, running Activities will be notified about the task.
        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(this);
        manager.sendBroadcast(intent);

        // Return one of RESULT_SUCCESS, RESULT_FAILURE, or RESULT_RESCHEDULE
        return result;
    }



    private int doPeriodicTask() {



        FirebaseDatabase database1 = FirebaseDatabase.getInstance();

        DatabaseReference gref = database1.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("newmessage");

        gref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.getValue(String.class);
                if (name != null) {

                    notification = new NotificationCompat.Builder(Alert.this);
                    notification.setAutoCancel(true);

                    notification.setSmallIcon(R.drawable.common_google_signin_btn_icon_dark);
                    notification.setTicker("School Message");
                    notification.setWhen(System.currentTimeMillis());
                    notification.setContentTitle("School Message");
                    notification.setContentText(name);
                    notification.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
                    notification.setDefaults(Notification.DEFAULT_VIBRATE);

                    //    Uri uri = Uri.parse("android.resource://" + user.this.getPackageName() + "/" + R.raw.em);
                    //    notification.setSound(uri);

                    Intent intent = new Intent(Alert.this, user.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(Alert.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    notification.setContentIntent(pendingIntent);

                    //Builds notification and issues it
                    NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(uniqueID, notification.build());


                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference upd = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("oldmessage");
                    upd.setValue(name);
                                DatabaseReference del = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("newmessage");
                                del.removeValue();
                    DatabaseReference extractd  = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("date");
                    extractd.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            String dvalue = dataSnapshot.getValue(String.class);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference upd = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("olddate");
                                upd.setValue(dvalue);

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value

                        }
                    });
                    DatabaseReference extracts  = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("subject");
                    extracts.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            String svalue = dataSnapshot.getValue(String.class);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference upd = database.getReference("Info").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("oldsubject");
                            upd.setValue(svalue);

                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Failed to read value

                        }
                    });



                    //else close
                } else {
                    return;
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return GcmNetworkManager.RESULT_SUCCESS;




    }









}