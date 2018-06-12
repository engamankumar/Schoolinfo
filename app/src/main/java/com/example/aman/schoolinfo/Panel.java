package com.example.aman.schoolinfo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Panel extends BaseActivity  {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        Button sub,adm;
        final EditText st1,st2,st3,cl1,cl2,cl3,st4,cl4,username,password;
        st1=(EditText)findViewById(R.id.studentname);
        st2=(EditText)findViewById(R.id.student2);
        st3=(EditText)findViewById(R.id.student3);
        cl1=(EditText)findViewById(R.id.class1);
        st4=(EditText)findViewById(R.id.student4);
        cl4=(EditText)findViewById(R.id.class4);
        username=(EditText)findViewById(R.id.username);

        password=(EditText)findViewById(R.id.password);
        cl2=(EditText)findViewById(R.id.class2);
        cl3=(EditText)findViewById(R.id.class3);
        sub=(Button)findViewById(R.id.userpage);

        mAuth = FirebaseAuth.getInstance();
        //  final String s1= String.valueOf(st1.getText());

        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                String usr= String.valueOf(username.getText());
                String pass= String.valueOf(password.getText());
                String s4=String.valueOf(st4.getText());
                String c4=String.valueOf(cl4.getText());
                String email=usr+"@schoolinfo.com";


                if( s4.isEmpty()  || c4.isEmpty()   || pass.isEmpty() || usr.isEmpty() || pass.length()<8 )

                {
                   if(s4.isEmpty()){
                       st4.setError("First Student Name  is Required");
                   }
                  if(c4.isEmpty()){
                    cl4.setError("Class is Required");
                }
                    if(pass.isEmpty()){
                       password.setError("Password is Required");
                   }
                    if(usr.isEmpty()){
                       username.setError("User name is Required");
                   }
                   if (pass.length()<8){
                       password.setError("Password must be at least 8 character long");
                   }
                }





                else {
                    showProgressDialog();
                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(Panel.this , new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("Info").child(user.getUid());
                                DatabaseReference myRef1 = database.getReference("class1");


                                DatabaseReference myRef4 = database.getReference("class4");
                                DatabaseReference myRef5 = database.getReference("class5");
                                DatabaseReference myRef6 = database.getReference("class6");
                                DatabaseReference myRef7 = database.getReference("class7");
                                DatabaseReference myRef8 = database.getReference("class8");
                                DatabaseReference myRef9 = database.getReference("class9");
                                String c1= String.valueOf(cl1.getText());
                                String c2= String.valueOf(cl2.getText());
                                String c3= String.valueOf(cl3.getText());
                                String c4= String.valueOf(cl4.getText());

                                if(c1!= null) {
                                    DatabaseReference myRe = database.getReference("class"+c1);
                                    myRe.child(user.getUid()).setValue("true");
                                }
                                if(c2!= null) {
                                    DatabaseReference myRef2 = database.getReference("class"+c2);
                                    myRef2.child(user.getUid()).setValue("true");
                                }
                                if(c3!= null) {
                                    DatabaseReference myRef3 = database.getReference("class"+c3);
                                    myRef3.child(user.getUid()).setValue("true");
                                }
                                if(c4!= null) {
                                    DatabaseReference myRe4 = database.getReference("class"+c4);
                                    myRe4.child(user.getUid()).setValue("true");
                                }

                                String s1= String.valueOf(st1.getText());
                                String s2= String.valueOf(st2.getText());
                                String s3= String.valueOf(st3.getText());
                                String s4= String.valueOf(st4.getText());
                                myRef.child("Name1").setValue(s4);
                                myRef.child("Name2").setValue(s1);
                                myRef.child("Name3").setValue(s2);
                                myRef.child("Name4").setValue(s3);
                                myRef.child("class1").setValue(c4);
                                myRef.child("class2").setValue(c1);
                                myRef.child("class3").setValue(c2);
                                myRef.child("class4").setValue(c3);

                                onAuthSuccess(task.getResult().getUser());

                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(Panel.this, "Authentication failed. Username already exits",
                                        Toast.LENGTH_SHORT).show();

                            }
                            hideProgressDialog();
                        }
                    }); }

            }
        });

    }
    @Override
    public void onStart() {

        // Check if user is signed in (non-null) and update UI accordingly.
        super.onStart();

        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }
    }
    private void onAuthSuccess(FirebaseUser user) {

        if(user.getEmail()!="admin@gmail.com")
        // Go to MainActivity
        {  startActivity(new Intent(Panel.this, user.class));
            finish(); }
        else {
            startActivity(new Intent(Panel.this, admin.class));
            finish();
        }
    }
    private boolean isValidEmail(String us) {
        if (us != null) {
            return true;
        }
        return false;
    }

    private boolean isValidStudentName(String name) {
        if (name != null ) {
            return true;
        }
        return false;
    }
    private boolean isValidStudentClass(String unclass) {
        if (unclass != null ) {
            return true;
        }

        return false;
    }



    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8) {
            return true;
        }
        return false;
    }

}
