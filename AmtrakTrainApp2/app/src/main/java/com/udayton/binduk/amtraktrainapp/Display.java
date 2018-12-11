package com.udayton.binduk.amtraktrainapp;

import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


@RequiresApi(api = Build.VERSION_CODES.N)
public class Display extends AppCompatActivity {

    private SharedPreferences sharedPref;

    //private final DecimalFormat currency = new DecimalFormat("$###,###.00");

    //  private final DecimalFormat time = new DecimalFormat("####.00");

    private static final int MINUTES_PER_HOUR = 60;

    private static final String ARRIVAL_TIME_PREFIX= "Arrival Time of the Train: ";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        //get GUI control references
        final TextView txtArrivalTime = (TextView)findViewById(R.id.txtArrivaltime);
        //final ImageView imgYears = (ImageView)findViewById(R.id.imgYears);

        //retrive the presistent data from the SharedPreferences object
        int numberOfHours = sharedPref.getInt(MainActivity.HOURS_KEY,MainActivity.DEFAULT_HOURS);
        int minutes = sharedPref.getInt(MainActivity.MINUTES_KEY,MainActivity.DEFAULT_MINUTES);
        int traveltime = sharedPref.getInt(MainActivity.LENGTH_KEY, MainActivity.DEFAULT_LENGTH);

        int minutesTime = minutes + traveltime;
        if(minutesTime<60){
            if(numberOfHours>=0 && numberOfHours<=9){
                if(minutesTime>=0 && minutesTime<=9) {
                    String ArrivalTime = "0" + numberOfHours + ":" + "0"+ minutesTime;
                    txtArrivalTime.setText(ARRIVAL_TIME_PREFIX+ArrivalTime);
                }
                else{
                    String ArrivalTime = "0" + numberOfHours + ":" + minutesTime;
                    txtArrivalTime.setText(ARRIVAL_TIME_PREFIX+ArrivalTime);

                }
            }
            else{
                if(minutesTime>=0 && minutesTime<=9) {
                    String ArrivalTime = numberOfHours + ":" +"0" +minutesTime;
                    txtArrivalTime.setText(ARRIVAL_TIME_PREFIX + ArrivalTime);
                }else{
                    String ArrivalTime = numberOfHours + ": " +minutesTime;
                    txtArrivalTime.setText(ARRIVAL_TIME_PREFIX + ArrivalTime);
                }
            }




            if(numberOfHours>=0 && numberOfHours<01 ){
                Toast toast= Toast.makeText(Display.this,
                        "RED-EYE ARRIVAL",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else{
            int extramin = minutesTime%60;
            int extrahrs = minutesTime/60;
            numberOfHours += extrahrs;

            if(numberOfHours>=0 && numberOfHours<=9){
                if(extramin>=0 && extramin<=9) {
                    String ArrivalTime = "0" + numberOfHours + ":" + "0"+extramin;
                    txtArrivalTime.setText(ARRIVAL_TIME_PREFIX+ArrivalTime);
                }
                else {

                    String ArrivalTime = "0" + numberOfHours + ":" + extramin;
                    txtArrivalTime.setText(ARRIVAL_TIME_PREFIX+ArrivalTime);

                }


            }
            else {
                if(numberOfHours==48) {
                    numberOfHours = 48 - numberOfHours;
                    if(numberOfHours>=0 && numberOfHours<=9) {
                        if(extramin>=0 && extramin<=9) {
                            String ArrivalTime = "0"+numberOfHours + ":" + "0"+extramin;
                            txtArrivalTime.setText(ARRIVAL_TIME_PREFIX+ArrivalTime);
                        }
                        else{
                            String ArrivalTime = "0" + numberOfHours + ":" + extramin;
                            txtArrivalTime.setText(ARRIVAL_TIME_PREFIX+ArrivalTime);
                        }
                    }
                    else{
                        if(extramin>=0 && extramin<=9) {
                            String ArrivalTime = numberOfHours + ":" + "0"+extramin;
                            txtArrivalTime.setText(ARRIVAL_TIME_PREFIX + ArrivalTime);
                        }
                        else{
                            String ArrivalTime = numberOfHours + ":" + extramin;
                            txtArrivalTime.setText(ARRIVAL_TIME_PREFIX + ArrivalTime);
                        }
                    }

                }
                if(numberOfHours>=24 && numberOfHours<48) {
                    numberOfHours -= 24;
                    if(numberOfHours>=0 && numberOfHours<=9) {
                        if(extramin>=0 && extramin<=9) {
                            String ArrivalTime = "0"+numberOfHours + ":" + "0"+extramin;
                            txtArrivalTime.setText(ARRIVAL_TIME_PREFIX+ArrivalTime);
                        }
                        else{
                            String ArrivalTime = "0" + numberOfHours + ":" + extramin;
                            txtArrivalTime.setText(ARRIVAL_TIME_PREFIX+ArrivalTime);
                        }
                    }
                    else{
                        if(extramin>=0 && extramin<=9) {
                            String ArrivalTime = numberOfHours + ":" + "0"+extramin;
                            txtArrivalTime.setText(ARRIVAL_TIME_PREFIX + ArrivalTime);
                        }
                        else{
                            String ArrivalTime = numberOfHours + ":" + extramin;
                            txtArrivalTime.setText(ARRIVAL_TIME_PREFIX + ArrivalTime);
                        }
                    }
                }
                else
                {
                    if(extramin>=0 && extramin<=9) {
                        String ArrivalTime = numberOfHours + ":" + "0"+extramin;
                        txtArrivalTime.setText(ARRIVAL_TIME_PREFIX + ArrivalTime);
                    }
                    else{
                        String ArrivalTime = numberOfHours + ":" + extramin;
                        txtArrivalTime.setText(ARRIVAL_TIME_PREFIX + ArrivalTime);
                    }
                }

            }
            if(numberOfHours>=0 && numberOfHours<01 ){
                Toast toast= Toast.makeText(Display.this,
                        "RED-EYE ARRIVAL",
                        Toast.LENGTH_LONG);
                toast.show();
            }
        }

    }
}