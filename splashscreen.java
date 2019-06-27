package com.trial.rectifyapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class splashscreen extends Activity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        iv=(ImageView) findViewById(R.id.imageView3);
        Animation myanim= AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(myanim);
       Thread timer= new Thread(){
           public void run()
           {
               try
               {
                   sleep(3000);
               }
               catch(InterruptedException e)
               {
                   e.printStackTrace();
               }
               finally {
                  startActivity(new Intent(splashscreen.this, welcome3.class));
                   finish();
               }
           }
       };
       timer.start();



    }
}
