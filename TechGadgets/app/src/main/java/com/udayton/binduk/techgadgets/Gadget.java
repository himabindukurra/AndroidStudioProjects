package com.udayton.binduk.techgadgets;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Gadget extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget);
        TextView t = (TextView)findViewById(R.id.t1);
        Button b=  (Button)findViewById(R.id.b1);
        ImageView img =(ImageView)findViewById(R.id.img1);

        Intent iin= getIntent();
        int i = iin.getIntExtra("name",10);

        //Intent in;
        switch(i) {

            case 0:
                t.setText("FitBit");
                b.setText("open the site");
                img.setImageResource(R.drawable.fitbit);

                b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent in4 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fitbit.com/home"));
                        startActivity(in4);
                    }
                });   // end of button
                break;
            //  case 1:
            case 1:
                t.setText("Apple Watch");
                b.setText("open the site");
                img.setImageResource(R.drawable.apple_watch);

                b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent in3 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.apple.com/watch/"));
                        startActivity(in3);
                    }
                });   // end of button
                break;

            case 2:
                t.setText("Samsung Smart Watch");
                b.setText("open the site");
                img.setImageResource(R.drawable.samsung);

                b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent in2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.samsung.com/us/search/searchMain?N=/_/N-1z14194/Nr-QU5EKFNpdGVUeXBlOmdsb2JhbCxJc0hpZGRlbjpOKQ==&Ntt=watch&flow=new"));
                        startActivity(in2);
                    }
                });   // end of button
                break;

            case 3:
                t.setText("Sony Smart Watch");
                b.setText("open the site");
                img.setImageResource(R.drawable.sony);

                b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent in1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.sonymobile.com/gb/products/smart-products/"));
                        startActivity(in1);
                    }
                });   // end of button
                break;
            case 4:
                t.setText("Michael Kors Watch");
                b.setText("open the site!");
                img.setImageResource(R.drawable.mk);

                b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.michaelkors.com/bradshaw-rose-gold-tone-smartwatch/_/R-US_MKT5004"));
                        startActivity(in);
                    }
                });   // end of button

                // end of switch

                break;



        } //end of switch
    }
}
