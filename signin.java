package com.trial.rectifyapp;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signin extends AppCompatActivity implements View.OnClickListener {

    private Button  buttonregister, back;
    private EditText editTextPassword,name,editTextEmail;;
    private DatabaseReference myref;
    private String name1;


    private ProgressDialog progressDialog;


    private FirebaseAuth firebaseAuth;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        RelativeLayout mylayout;
        AnimationDrawable animationDrawable;

        mylayout = (RelativeLayout) findViewById(R.id.mylayout1);
        animationDrawable = (AnimationDrawable) mylayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(500);
        animationDrawable.start();

        firebaseAuth= FirebaseAuth.getInstance();

        myref= FirebaseDatabase.getInstance().getReference("Users");
        /*if (firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext() , MainActivity.class));
        }*/


        progressDialog=new ProgressDialog(this);
        buttonregister=(Button)findViewById(R.id.button57);
        back = (Button) findViewById(R.id.button2);
        editTextEmail=(EditText)findViewById(R.id.editText96);
        editTextPassword=(EditText)findViewById(R.id.editText4);
        buttonregister.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signin.this, welcome3.class));
            }
        });


        name=(EditText)findViewById(R.id.editText27);

    }
    private void registeruser(){
        String email = editTextEmail.getText().toString().trim();
        String password =editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // user is successfully registered and logged in
                                progressDialog.dismiss();
                                name1 = name.getText().toString().trim();
                                DatabaseReference newpost = myref.child(firebaseAuth.getCurrentUser().getUid());
                                newpost.child("Name").setValue(name1);
                                Toast.makeText(signin.this, "Registration succesfull", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(), g_ng.class));
                            } else {
                                Toast.makeText(signin.this, "could not register,please try again", Toast.LENGTH_SHORT).show();
                                //progressDialog.dismiss();
                            }
                        }
                    });


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
        if(v== buttonregister){
            {
                registeruser();
            }

        }
    }
}
