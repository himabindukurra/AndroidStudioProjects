package com.udayton.binduk.electriccarfinancing;

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

    private int numberOfYears;
    private float loanAmount;
    private float interestInput, interestRate;

    private static final int NUMBER_OF_TERMS = 3;

    public static final String YEARS_KEY="key1",
            LOAN_KEY="key2",
            RATE_KEY="key3";

    public static final int DEFAULT_YEARS = 0;
    public static final float DEFAULT_LOAN= 0;
    public static final float DEFAULT_RATE = 0;

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
        showCurrentYears();
        showCurrentLoan();
        showCurrentRate();

        //create listener for btnPayment
        View.OnClickListener btnPaymentListener = new View.OnClickListener(){

            //get GUI references to the Spinner and text fields
            final Spinner yearsSpinner = (Spinner)findViewById(R.id.yearsSpinner);
            final EditText txtLoan = (EditText)findViewById(R.id.txtLoan);
            final EditText txtInterest = (EditText)findViewById(R.id.txtInterest);

            //helper method to validate interest rate (should be > 0 and <=100)
            private boolean validInterestInput(float input){
                return (input > 0 && input <=100);
            }
            @Override
            public void onClick(View v) {
                //get (zero-based) index of chosen Spinner item
                int yearsIndex = yearsSpinner.getSelectedItemPosition();

                //convert yearsIndex to number of years
                numberOfYears = yearsIndex + NUMBER_OF_TERMS;

                //on the incredibly unlikely (0.00000001%) chance that the number of years
                //is invalid,show an error message (toast) and we've done
                if(numberOfYears < 3 || numberOfYears>5)
                {
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Invalid number of years: "+numberOfYears,
                            Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                //get loan amount and convert itto a doublel with error-checking
                String LoanAmt = txtLoan.getText().toString();

                try{
                    loanAmount=Float.parseFloat(LoanAmt);
                }catch(Exception e){
                    Toast toast = Toast.makeText(MainActivity.this,
                            "Invalid Loan Amount: "+LoanAmt,Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                //get interest rate, convert it to a double, and then
                //from percentage to decimal, with error-checking
                String InterestRate = txtInterest.getText().toString();

                try{
                    interestInput = Float.parseFloat(InterestRate);

                    if(validInterestInput(interestInput)){
                        interestRate = (float)(interestInput/100.0);
                        }else{
                            throw new Exception("Invalid Interest Rate: "+InterestRate);
                        }
                    }catch(NumberFormatException e){
                        Toast toast= Toast.makeText(MainActivity.this,
                                "Invalid Interest Rate: "+InterestRate,
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

                //wriet persistent data to the SharedPreferences object
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(YEARS_KEY,numberOfYears);
                editor.putFloat(LOAN_KEY,loanAmount);
                editor.putFloat(RATE_KEY,interestRate);
                editor.commit();

                //start the payment Activity
                Intent intent = new Intent(MainActivity.this,Payment.class);
                startActivity(intent);
            }//end onClick
        };

        //set the btnPaymentListener for the btnPaymentButton
        btnPayment.setOnClickListener(btnPaymentListener);
    }//end onCreate method

    //method displays persistent number of years if present
    private void showCurrentYears()
    {
        final Spinner yearsSpinner = (Spinner)findViewById(R.id.yearsSpinner);

        numberOfYears = sharedPref.getInt(YEARS_KEY, DEFAULT_YEARS);

        if(numberOfYears > DEFAULT_YEARS)
        {
            //convert number of years to (zero-based) yarsSpinner index
            int yearsIndex = numberOfYears - NUMBER_OF_TERMS;

            //set the yearsSpinner selection based on the yearsIndex
            yearsSpinner.setSelection(yearsIndex);
        }

    }//end showCurrentYears

    private void showCurrentLoan()
    {
       final EditText txtLoan = (EditText)findViewById(R.id.txtLoan);

        loanAmount = sharedPref.getFloat(LOAN_KEY, DEFAULT_LOAN);

        if(loanAmount > DEFAULT_LOAN)
        {
            txtLoan.setText(Float.toString(loanAmount));
        }
    }//end showCurrentLoan

    private void showCurrentRate()
    {
        final EditText txtInterest = (EditText)findViewById(R.id.txtInterest);

        interestRate = sharedPref.getFloat(RATE_KEY, DEFAULT_RATE);

        if(interestRate > DEFAULT_RATE)
        {
            txtInterest.setText(Float.toString(100*interestRate));
        }
    }//end showCurrentRate
}//end MainActivity class
