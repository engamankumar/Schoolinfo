package com.example.aman.schoolinfo;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends BaseActivity  {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sub;
        ImageButton adm;


        sub=(Button)findViewById(R.id.userpage);
        adm=(ImageButton) findViewById(R.id.adminpage);
        mAuth = FirebaseAuth.getInstance();
        //  final String s1= String.valueOf(st1.getText());
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),Panel.class);
                startActivity(i);
            }
        });
       adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),adminlogin.class);
                startActivity(i);
            }
        });


    }
    @Override
    public void onStart() {

        // Check if user is signed in (non-null) and update UI accordingly.
        super.onStart();
        if (Build.BRAND.equalsIgnoreCase("xiaomi")) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            startActivity(intent);
        }
        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
            onAuthSuccess(mAuth.getCurrentUser());
        }
    }
    private void onAuthSuccess(FirebaseUser user) {

        String uemail=user.getEmail();
        switch (uemail){
            case "admin@gmail.com":

                startActivity(new Intent(MainActivity.this, admin.class));
                finish();
                break;
             default:

                 startActivity(new Intent(MainActivity.this, user.class));
                 finish();
                 break;
        }

    }
}
