package com.trial.rectifyapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class descriptionbox extends AppCompatActivity {


    EditText input;
    Button amount2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptionbox);

        final AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setTitle("Description");
      //  builder.setIcon(R.drawable.lo);

        builder.setMessage("Enter Amoount:");
        input=new EditText(this);
        builder.setView(input);
        builder.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                int text=Integer.parseInt(input.getText().toString());
                Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();

                }
        });

        builder.setNegativeButton("Cancelled", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }

        });
        final AlertDialog alertDialog=builder.create();
        amount2=(Button)findViewById(R.id.button23);
        amount2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.show();
            }
        });

    }
}
