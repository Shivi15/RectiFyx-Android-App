package com.trial.rectifyapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class g_ng extends AppCompatActivity {

    private FirebaseAuth mauth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_ng);



        RelativeLayout mylayout;
        AnimationDrawable animationDrawable;

        mylayout = (RelativeLayout) findViewById(R.id.mylayout1);
        animationDrawable = (AnimationDrawable) mylayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(500);
        animationDrawable.start();

        // Button NonGroup = (Button) findViewById(R.id.button5);
         mauth=FirebaseAuth.getInstance();
        Button Groups = (Button) findViewById(R.id.button4);
        Button invite2 = (Button) findViewById(R.id.invite);
        Button logout= (Button) findViewById(R.id.button14);
        Button nongroups = (Button) findViewById(R.id.button13);





        Groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(g_ng.this, group1.class);
                startActivity(intent);
            }


        });



        invite2.setOnClickListener(new View.OnClickListener() {
            public static final String TAG ="g_ng" ;
            public static final int REQUEST_INVITE = 100;

                                       @Override
                                       public void onClick(View v) {

                                           Intent intent = new AppInviteInvitation.IntentBuilder("invitation_title")
                                                   .setMessage("Hey, join RectiFYX to enjoy the ease of settling up dues/owes.")
                                                   .setDeepLink(Uri.parse("http://google.com"))
                                                   .setCallToActionText("Invitation CTA")
                                                   .build();
                                           startActivityForResult(intent, REQUEST_INVITE);

                                       }

          /*  @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                Log.d(TAG, "onActivityResult: requestCode=" + requestCode + ", resultCode=" + resultCode);

                if (requestCode == REQUEST_INVITE) {
                    if (resultCode == RESULT_OK) {
                        // Get the invitation IDs of all sent messages
                        String[] ids = AppInviteInvitation.getInvitationIds(resultCode, data);
                        for (String id : ids) {
                            Log.d(TAG, "onActivityResult: sent invitation " + id);
                        }
                    } else {
                        // Sending failed or it was canceled, show failure message to the user
                        // ...
                    }
                }
            }*/

                                   });



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mauth.signOut();
                finish();
                Intent intent = new Intent(g_ng.this, welcome3.class);
                startActivity(intent);

            }


        });

        nongroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Note: Only 2 members are allowed.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(g_ng.this, group1.class);
                startActivity(intent);
            }


        });
    }


}