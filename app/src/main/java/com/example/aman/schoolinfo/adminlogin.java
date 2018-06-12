package com.example.aman.schoolinfo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class adminlogin extends BaseActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "LOGIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        Button login;
        mAuth = FirebaseAuth.getInstance();
        final EditText username,password;
        login = (Button)findViewById(R.id.adlogin);
        username=(EditText)findViewById(R.id.adminusername);
        password=(EditText)findViewById(R.id.adminpassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em = String.valueOf(username.getText());
                String email = em + "@gmail.com";
                String pass = String.valueOf(password.getText());
                if (em.isEmpty() || pass.isEmpty())

                {

                    if (pass.isEmpty()) {
                        password.setError("Password is Required");
                    }
                    if (em.isEmpty()) {
                        username.setError("User name is Required");
                    }
                } else{
                    showProgressDialog();
                switch (email) {
                    case "admin@gmail.com":
                        mAuth.signInWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(adminlogin.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.d(TAG, "signIn:onComplete:" + task.isSuccessful());
                                        hideProgressDialog();

                                        if (task.isSuccessful()) {
                                            onAuthSuccess(task.getResult().getUser());
                                        } else {
                                            Toast.makeText(adminlogin.this, "Sign In Failed",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        break;
                    default:
                        Toast.makeText(adminlogin.this, "Sign In Failed",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }


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


        // Go to MainActivity
        startActivity(new Intent(adminlogin.this, admin.class));
        finish();
    }

}
