package com.udayton.binduk.amtraktrainapp;

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



public class MainActivity extends AppCompatActivity {

    private int hours;
    private int minutes;
    private int timeInput, traveltime;

    private static final int NUMBER_OF_TERMS = 60;

    public static final String HOURS_KEY="key1",
            MINUTES_KEY="key2",
            LENGTH_KEY="key3";

    public static final int DEFAULT_HOURS = 0;
    public static final int DEFAULT_MINUTES= 0;
    public static final int DEFAULT_LENGTH = 0;

    private SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get btnPayment GUI control reference
        final Button btnPayment=(Button)findViewById(R.id.btnPayment);

        //create the SharedPreferences object for this activity
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        //display persistent date if present
        showCurrentHours();
        showCurrentMinutes();
        showCurrentLength();

        //create listener for btnPayment
        View.OnClickListener btnPaymentListener = new View.OnClickListener(){

            //get GUI references to the Spinner and text fields
            final Spinner hoursSpinner = (Spinner)findViewById(R.id.hoursSpinner);
            final Spinner minutesSpinner = (Spinner)findViewById(R.id.minutesSpinner);
            final EditText txtArrivalTime = (EditText)findViewById(R.id.txtArrivalTime);
            final Button btnPayment = (Button)findViewById(R.id.btnPayment);

            //helper method to validate interest rate (should be > 0 and <=100)
            private boolean validLengthInput(float input){
                return (input > 0 && input <=1500);
            }
            @Override
            public void onClick(View v) {
                //get (zero-based) index of chosen Spinner item
                int minutesIndex = minutesSpinner.getSelectedItemPosition();

                //convert yearsIndex to number of years
                //hours = minutesIndex + NUMBER_OF_TERMS;
                hours = hoursSpinner.getSelectedItemPosition();

                //on the incredibly unlikely (0.00000001%) chance that the number of years
                //is invalid,show an error message (toast) and we've done
                String Length = txtArrivalTime.getText().toString();

                try{
                    timeInput = Integer.parseInt(Length);

                    if(validLengthInput(timeInput)){
                        traveltime = (int)(timeInput/60.0);
                    }else{
                        throw new Exception("Invalid Length of entire train trip (<1500): "+timeInput);
                    }
                }catch(NumberFormatException e){
                    Toast toast= Toast.makeText(MainActivity.this,
                            "Invalid Length of entire train trip (<1500): "+timeInput,
                            Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }catch(Exception e){
                    Toast toast = Toast.makeText(MainActivity.this,
                            e.getMessage(),
                            Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                //get loan amount and convert itto a doublel with error-checking
                //String ArrivalTime = txtArrivalTime.getText().toString();



                //wriet persistent data to the SharedPreferences object
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(HOURS_KEY,hours);
                editor.putInt(MINUTES_KEY,minutesIndex);
                editor.putInt(LENGTH_KEY, Integer.parseInt(Length));
                editor.commit();

                //start the payment Activity
                Intent intent = new Intent(MainActivity.this,Display.class);
                startActivity(intent);

            }//end onClick
            //  btnPayment.setOnClickListener(btnPaymentListener);
        };

        //set the btnPaymentListener for the btnPaymentButton
        btnPayment.setOnClickListener(btnPaymentListener);

    }//end onCreate method

    //method displays persistent number of years if present
    private void showCurrentHours()
    {

        final Spinner hourSpinner = (Spinner)findViewById(R.id.hoursSpinner);

        hours = sharedPref.getInt(HOURS_KEY, DEFAULT_HOURS);

        if(hours > DEFAULT_HOURS)
        {
            //convert number of hours to (zero-based) hourSpinner index
            int hourIndex = hours - NUMBER_OF_TERMS;

            //set the hourSpinner selection based on the hourIndex
            hourSpinner.setSelection(hourIndex);
        }

    }//end showCurrentYears

    private void showCurrentMinutes()
    {
        final Spinner minutesSpinner = (Spinner)findViewById(R.id.minutesSpinner);

        minutes = sharedPref.getInt(MINUTES_KEY, DEFAULT_MINUTES);

        if(minutes > DEFAULT_MINUTES)
        {
            //convert number of hours to (zero-based) hourSpinner index
            int minutesIndex = minutes - NUMBER_OF_TERMS;

            //set the hourSpinner selection based on the hourIndex
            minutesSpinner.setSelection(minutesIndex);
        }
    }//end showCurrentLoan

    private void showCurrentLength()
    {

        final EditText txtArrivalTime = (EditText)findViewById(R.id.txtArrivalTime);

        traveltime = sharedPref.getInt(LENGTH_KEY, DEFAULT_LENGTH);

        if(traveltime > DEFAULT_LENGTH)
        {
            txtArrivalTime.setText(Integer.toString(traveltime/60));
        }

    }//end showCurrentRate
}
