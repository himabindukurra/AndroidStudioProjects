package com.udayton.binduk.electriccarfinancing;

import android.content.SharedPreferences;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



@RequiresApi(api = Build.VERSION_CODES.N)
public class Payment extends AppCompatActivity {

    private SharedPreferences sharedPref;

    private final DecimalFormat currency = new DecimalFormat("$###,###.00");

    private static final int MONTHS_PER_YEAR = 12;

    private static final String MONTLY_PAYMENT_PREFIX= "Montly Payment: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        //create the SharedPreferences object for this activity
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        //get GUI control references
        final TextView txtMonthlyPayment = (TextView)findViewById(R.id.txtMonthlyPayment);
        final ImageView imgYears = (ImageView)findViewById(R.id.imgYears);

        //retrive the presistent data from the SharedPreferences object
        int numberOfYears = sharedPref.getInt(MainActivity.YEARS_KEY,MainActivity.DEFAULT_YEARS);
        float loanAmount = sharedPref.getFloat(MainActivity.LOAN_KEY,MainActivity.DEFAULT_LOAN);
        float interestRate = sharedPref.getFloat(MainActivity.RATE_KEY,MainActivity.DEFAULT_RATE);
        float monthlyPaymentAmt = 0;

        //only continue if all persistent data was retrieved
        if(numberOfYears > MainActivity.DEFAULT_YEARS &&
                loanAmount > MainActivity.DEFAULT_LOAN &&
                interestRate > MainActivity.DEFAULT_RATE)
        {

            //calculate and display the monthly payment amount in the txtMonthly Payment TextView
            monthlyPaymentAmt = (loanAmount * (1 + (interestRate * numberOfYears))) /
                    (MONTHS_PER_YEAR * numberOfYears);
            String Output = MONTLY_PAYMENT_PREFIX + currency.format(monthlyPaymentAmt);
            txtMonthlyPayment.setText(Output);

            //display the appropriate number of years image in the imgYears ImageView
            switch(numberOfYears)
            {
                case 3:
                    imgYears.setImageResource(R.drawable.three);
                    break;
                case 4:
                    imgYears.setImageResource(R.drawable.four);
                    break;
                case 5:
                    imgYears.setImageResource(R.drawable.five);
                    break;
                default:
                    Toast toast= Toast.makeText(Payment.this,
                            "Invalid number of years(weird)",
                            Toast.LENGTH_LONG);
                    toast.show();
            }//end switch
        }
    }//end onCreate method
}//end Payment class
