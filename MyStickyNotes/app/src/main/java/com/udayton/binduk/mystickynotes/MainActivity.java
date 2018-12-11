package com.udayton.binduk.mystickynotes;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get btnPayment GUI control reference
        final Button createNewBtn=(Button)findViewById(R.id.btnCreateNoteId);



        //create listener for btnPayment
        View.OnClickListener btnPaymentListener = new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                //start the payment Activity

                Intent intent = new Intent(MainActivity.this,NewNoteActivity.class);
                startActivity(intent);
            }//end onClick
        };

        //set the btnPaymentListener for the btnPaymentButton
        createNewBtn.setOnClickListener(btnPaymentListener);
    }


}


