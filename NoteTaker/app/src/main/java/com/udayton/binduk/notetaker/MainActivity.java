package com.udayton.binduk.notetaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ListView mListNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code for creating new note
        final Button createNewBtn=(Button)findViewById(R.id.btnCreateNoteId);



        //create listener for btnPayment
        View.OnClickListener btnPaymentListener = new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                //start the payment Activity

                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                startActivity(intent);
            }//end onClick
        };

        //set the btnPaymentListener for the btnPaymentButton
        createNewBtn.setOnClickListener(btnPaymentListener);

        //code for opening existing notes
        final Button openNoteBtn=(Button)findViewById(R.id.btnOpenNoteId);



        //create listener for btnPayment
        View.OnClickListener btnListener = new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                //start the payment Activity

                Intent intent = new Intent(MainActivity.this,NoteListActivity.class);
                startActivity(intent);
            }//end onClick
        };

        //set the btnPaymentListener for the btnPaymentButton
        openNoteBtn.setOnClickListener(btnListener);

        //code for about
        final Button aboutBtn=(Button)findViewById(R.id.btnAboutId);



        //create listener for btnPayment
        View.OnClickListener btnAboutListener = new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                //start the payment Activity

                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }//end onClick
        };

        //set the btnPaymentListener for the btnPaymentButton
        aboutBtn.setOnClickListener(btnAboutListener);

        //code for exiting app
        final Button exitAppBtn=(Button)findViewById(R.id.btnExitAppId);



        //create listener for btnPayment
        View.OnClickListener btnExitListener = new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                //start the payment Activity
                        AlertDialog.Builder dialogCancel = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Exit Sticky Notes")
                                .setMessage("Are you sure you want to exit the app?")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish(); //just go back to main activity
                                        System.exit(0);
                                    }
                                })
                                .setNegativeButton("NO", null); //null = stay in the activity!
                        dialogCancel.show();

            }//end onClick
        };

        //set the btnPaymentListener for the btnPaymentButton
        exitAppBtn.setOnClickListener(btnExitListener);


    }



}
