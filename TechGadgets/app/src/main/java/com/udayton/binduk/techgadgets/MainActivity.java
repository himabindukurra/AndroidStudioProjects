package com.udayton.binduk.techgadgets;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        List<String> Attractions= Arrays.asList(getResources().getStringArray(R.array.attractions));

        setListAdapter(new ArrayAdapter<String>(this,R.layout.activity_main,R.id.travel,Attractions));
    }
    protected void onListItemClick(ListView l, View v, int position, long id)
    {

        Intent intent;
        switch(position){

            case 0:
                intent = new Intent(MainActivity.this,Gadget.class);
                intent.putExtra("name", position);
                startActivity(intent);

                break;

            case 1:
                intent = new Intent(MainActivity.this,Gadget.class);
                intent.putExtra("name", position);
                startActivity(intent);

                break;

            case 2:
                intent = new Intent(MainActivity.this,Gadget.class);
                intent.putExtra("name", position);
                startActivity(intent);
                break;

            case 3:
                intent = new Intent(MainActivity.this,Gadget.class);
                intent.putExtra("name", position);
                startActivity(intent);
                break;

            case 4:
                intent = new Intent(MainActivity.this,Gadget.class);
                intent.putExtra("name", position);
                startActivity(intent);

                break;



        }


    }
}
