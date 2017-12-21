package com.ninad.pc.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView dial;
    Button start, stop, savelap, reset;
    long millisectime, starttime, timebuff, updatetime = 0L;
    Handler handler;
    int i=0,sec, min, millisec;
    List<String> ListElementsArrayList;
    Adapter adapter;
    String[] ListElements= new String[]{};
    Toolbar toolbar;
    RecyclerView laps;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        dial = (TextView) findViewById(R.id.dial);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        savelap = (Button) findViewById(R.id.savelap);
        reset = (Button) findViewById(R.id.reset);
        laps= (RecyclerView) findViewById(R.id.recyclerview);
        laps.setHasFixedSize(true);
        laps.setLayoutManager(new LinearLayoutManager(getApplicationContext()));





        handler = new Handler();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Stopwatch");

        ListElementsArrayList=new ArrayList<String>(Arrays.asList(ListElements));
        adapter= new Adapter(ListElementsArrayList);
        laps.setAdapter(adapter);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starttime = SystemClock.uptimeMillis();
                handler.postDelayed(r, 0);
                reset.setEnabled(false);
                start.setEnabled(false);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timebuff = timebuff + millisectime;
                handler.removeCallbacks(r);
                reset.setEnabled(true);
                start.setEnabled(true);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i=0;
                millisectime = 0L;
                starttime = 0L;
                timebuff = 0L;
                updatetime = 0L;
                sec = 0;
                min = 0;
                millisec = 0;
                dial.setText("00:00:00");
                ListElementsArrayList.clear();
                adapter.notifyDataSetChanged();
            }
        });
        savelap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                ListElementsArrayList.add(i+".    "+dial.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }

    public Runnable r = new Runnable() {
        @Override
        public void run() {
            millisectime= SystemClock.uptimeMillis()-starttime;
            updatetime=timebuff+millisectime;
            sec=(int)(updatetime/1000);
            min=sec/60;
            sec=sec%60;
            millisec=(int) (updatetime%1000);
            dial.setText(""+min+":"+String.format("%02d",sec)+":"+String.format("%03d",millisec));
            handler.postDelayed(this,0);
        }
    };
}
