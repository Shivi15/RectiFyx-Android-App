package com.trial.rectifyapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.R.attr.button;

public class group1 extends AppCompatActivity {

    private EditText Amount, Description, Members;

    private Button Back, rectify,Invite, ok, calculate;

    DatabaseReference databaseFix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group1);


        RelativeLayout mylayout;
        AnimationDrawable animationDrawable;

        mylayout = (RelativeLayout) findViewById(R.id.mylayout1);
        animationDrawable = (AnimationDrawable) mylayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(500);
        animationDrawable.start();

        databaseFix = FirebaseDatabase.getInstance().getReference("fixusers");
       // Amount = (EditText) findViewById(R.id.editText99);
       // Description = (EditText) findViewById(R.id.editText);
        Members = (EditText) findViewById(R.id.editText96);
        calculate = (Button) findViewById(R.id.button54);

        //Button  Fix= (Button) findViewById(R.id.button57);
         Back = (Button) findViewById(R.id.button2);
        rectify = (Button) findViewById(R.id.button5);
        Invite= (Button) findViewById(R.id.button10);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(group1.this, descriptionbox.class);
                startActivity(intent);
            }


        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(group1.this, g_ng.class);
                startActivity(intent);
            }

        });

        rectify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Integer.parseInt(Amount.getText().toString());
                int members = Integer.parseInt(Members.getText().toString());
                String description = Description.getText().toString().trim();
                int ppamt = (amount / members);
                int fixamt = (amount - ppamt);


                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(getApplicationContext(), "Please enter the description", Toast.LENGTH_SHORT).show();
                    return;

                }
                else
                    {
                    String id = databaseFix.push().getKey();
                    fix Fix = new fix(description, amount, members);
                    databaseFix.child(id).setValue(Fix);
                    Toast.makeText(getApplicationContext(), "Amount added to the database", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Amount added="+ppamt, Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(group1.this, group_creation.class);
                    //startActivity(intent);
                }

                Toast.makeText(getApplicationContext(), "others owe" + fixamt, Toast.LENGTH_LONG).show();

                AlertDialog.Builder builder=new AlertDialog.Builder(group1.this);
                builder.setTitle("Details");
                builder.setMessage("Amount per person: "+ppamt+"\n Amount recieved should be: "+fixamt);
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Amount Added.", Toast.LENGTH_LONG).show();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();



            }
            });
        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float amount = Integer.parseInt(Amount.getText().toString());
                int members = Integer.parseInt(Members.getText().toString());
                String description = Description.getText().toString().trim();
               float ppamt = (amount / members);
                float fixamt = (amount - ppamt);

                Intent ss = new Intent(Intent.ACTION_VIEW);
                String msg = "Hi, Please install RectiFYX App from PlayStore to know your owes/dues. You're succesfully added to the group! You owe Rs";
                ss.setData(Uri.parse("sms://"));
                ss.putExtra("sms_body", msg+""+ppamt);
                ss.setType("vnd.android-dir/mms-sms");
                startActivity(ss);


            }
        });
    }
}



       /* Fix.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int amount = Integer.parseInt(Amount.getText().toString());
                int members = Integer.parseInt(Members.getText().toString());
                int ppamt = (amount / members);
                int fixamt = (amount - ppamt);
                String description = Description.getText().toString().trim();

                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(getApplicationContext(), "Please enter the description", Toast.LENGTH_SHORT).show();
                    return;

                } else
                {
                    String id = databaseFix.push().getKey();
                    fix Fix = new fix(description,amount,members);
                    databaseFix.child(id).setValue(Fix);
                    Toast.makeText(getApplicationContext(), "Amount added to the database", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(), "others owe" + fixamt, Toast.LENGTH_LONG).show();

            }

        });*/








