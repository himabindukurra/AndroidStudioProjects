package com.udayton.binduk.mystickynotes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class NewNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        //get btnPayment GUI control reference
        //final EditText description=(EditText) findViewById(R.id.descriptionEditText);
        final Button saveBtn=(Button)findViewById(R.id.saveBtn);



        //create listener for btnPayment
        View.OnClickListener btnPaymentListener = new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.descriptionEditText);
                TextView textView = new TextView(getApplicationContext());

                textView.setTypeface(editText.getTypeface());
                textView.setText(editText.getText());
                textView.measure(
                        View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                textView.layout(0, 0, textView.getMeasuredWidth(), textView.getMeasuredHeight());
                textView.setDrawingCacheEnabled(true);
                textView.buildDrawingCache();

                Bitmap b = textView.getDrawingCache().copy(Bitmap.Config.ARGB_8888, false);
                textView.destroyDrawingCache();

                try{
                    String path = Environment.getExternalStorageDirectory().toString() +File.separator +"picture.png";
                    OutputStream outputStream = new FileOutputStream(new File(path));
                    b.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }//end onClick


        };

        //set the btnPaymentListener for the btnPaymentButton
        saveBtn.setOnClickListener(btnPaymentListener);
    }
}
