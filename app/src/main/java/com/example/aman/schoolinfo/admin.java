package com.example.aman.schoolinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        final CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9;
        final EditText e1,s1;
        Button send;
        c1=(CheckBox)findViewById(R.id.class1);
        c2=(CheckBox)findViewById(R.id.class2);
        c3=(CheckBox)findViewById(R.id.class3);
        c4=(CheckBox)findViewById(R.id.class4);
        c5=(CheckBox)findViewById(R.id.class5);
        c6=(CheckBox)findViewById(R.id.class6);
        c7=(CheckBox)findViewById(R.id.class7);
        c8=(CheckBox)findViewById(R.id.class8);
        c9=(CheckBox)findViewById(R.id.class9);
        s1=(EditText) findViewById(R.id.subject);
        e1=(EditText) findViewById(R.id.messagewindow);
        send=(Button)findViewById(R.id.send);
      String message = String.valueOf(e1.getText());
            final String date= new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(c1.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class1").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();


                                DatabaseReference myRef1 = database.getReference("Info").child(usr);
                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if(c2.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class2").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRef1 = database.getReference("Info").child(usr);
                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if(c3.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class3").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRef1 = database.getReference("Info").child(usr);
                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if(c4.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class4").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRef1 = database.getReference("Info").child(usr);
                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if(c5.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class5").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRef1 = database.getReference("Info").child(usr);
                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if(c6.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class6").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRef1 = database.getReference("Info").child(usr);
                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if(c7.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class7").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRef1 = database.getReference("Info").child(usr);
                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if(c8.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class8").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRef1 = database.getReference("Info").child(usr);
                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);;
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                if(c9.isChecked()){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    DatabaseReference myRef = database.getReference();
                    myRef.child("class9").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot keySnapshot : dataSnapshot.getChildren()){
                                //Loop 1 to go through all the child nodes of users

                                //loop 2 to go through all the child nodes of books node
                                String usr = keySnapshot.getKey();
                                String booksValue = (String) keySnapshot.getValue();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();

                                DatabaseReference myRef1 = database.getReference("Info").child(usr);

                                String message = String.valueOf(e1.getText());
                                String subject = String.valueOf(s1.getText());
                                myRef1.child("newmessage").setValue(message);
                                myRef1.child("date").setValue(date);
                                myRef1.child("subject").setValue(subject);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        });

    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}
