package com.udayton.binduk.appalachiantrialfestival;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private TextView txtReservation;

    private static final Calendar cal= Calendar.getInstance();
    private static final DateFormat fmtDate = DateFormat.getDateInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtReservation = (TextView) findViewById(R.id.txtReservation);
        Button btnDate = (Button) findViewById(R.id.btnDate);

        btnDate.setOnClickListener(btnListener);
    }


    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener()

    {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            cal.set(Calendar.YEAR,year);
            cal.set(Calendar.MONTH,monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);

            Date reservedDate=cal.getTime();
            txtReservation.setText("Your reservation is set for "+fmtDate.format(reservedDate));

        }
    };

    Button.OnClickListener btnListener= new Button.OnClickListener(){

        @Override
        public void onClick(View v) {
            int year= cal.get(Calendar.YEAR);
            int monthOfYear= cal.get(Calendar.MONTH);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    dateListener, year,monthOfYear, dayOfMonth);
            datePickerDialog.show();
        }
    };

}

