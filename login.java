package com.trial.rectifyapp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity implements View.OnClickListener {

    private Button buttonsignin, back, reset;
    private EditText editTextpassword;
    private  EditText editTextemail;

   // private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RelativeLayout mylayout;
        AnimationDrawable animationDrawable;

        mylayout = (RelativeLayout) findViewById(R.id.mylayout1);
        animationDrawable = (AnimationDrawable) mylayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(500);
        animationDrawable.start();

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), g_ng.class));
        }
        editTextemail = (EditText) findViewById(R.id.editText14);
        editTextpassword = (EditText) findViewById(R.id.editText15);
        buttonsignin = (Button) findViewById(R.id.button12);
        back = (Button) findViewById(R.id.button3);
        reset = (Button) findViewById(R.id.button7);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), g_ng.class));
                }


                // ...
            }
        };


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, welcome3.class));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail = editTextemail.getText().toString().trim();
                String userpass = editTextpassword.getText().toString().trim();
                if (userpass.isEmpty()) {
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(login.this, "Password Reset Email Sent ", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), login.class));
                            } else {
                                Toast.makeText(login.this, "Error in sending  password reset email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });
        buttonsignin.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void userlogin(){
        String email=editTextemail.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();



        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches())
        {
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {
                            Toast.makeText(login.this,"Logged in!", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), g_ng.class));


                        }
                        else
                        {
                            Toast.makeText(login.this,"Incorrect username or password", Toast.LENGTH_SHORT).show();
                        }
                    } });



    }
      else
    {
        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
    }
        if(password.length()<6)
    {
        Toast.makeText(getApplicationContext(),"password should be greater than 6 characters",Toast.LENGTH_SHORT).show();
    }



}


    @Override
    public void onClick(View v) {
        if (v ==buttonsignin) {
            userlogin();

        }
    }


}


