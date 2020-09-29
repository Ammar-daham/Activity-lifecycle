package com.example.tehtava5_1;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    Counter create = new Counter(-100, 100);
    Counter start = new Counter(-100, 100);
    Counter resume = new Counter(-100, 100);
    TextView createTv, startTv, resumeTv;
    Button resetBtn;
    int created;
    int started;
    int resumed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createTv = findViewById(R.id.create);
        startTv = findViewById(R.id.start);
        resumeTv = findViewById(R.id.resume);
        resetBtn = findViewById(R.id.resetBtn);
        // Read from value.xml
        SharedPreferences sharedPreferences = getSharedPreferences("value", Activity.MODE_PRIVATE);
        int cr = sharedPreferences.getInt("Created",0);
        int st = sharedPreferences.getInt("Started",0);
        int re = sharedPreferences.getInt("Resumed",0);
        Log.d("Started from value.xml", String.valueOf(st));
        // if the creation has values --> not zero
        if(cr !=0 ) {
            create.setValue(cr);
            start.setValue(st);
            resume.setValue(re);
            sharedPref(0);
            updateUi();
            create.plusCounter();

            Log.d("started", "there is some value");
        }
        // if its run for the first time.
        if(cr == 0) {
            create.plusCounter();
            sharedPref(0);

        }

        Log.d("Create","onCreate called");

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create.initCounter();
                resume.initCounter();
                start.initCounter();
                sharedPref(1);
                updateUi();
            }
        });
    }

    @Override
    public void onPause() {
        resume.plusCounter();
        sharedPref(0);
        super.onPause();
        Log.d("PAUSE", "onPause called");
    }

    public void onResume() {
        start.plusCounter();
        super.onResume();
        sharedPref(0);
        Log.d("START", "onStart called");
        updateUi();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        sharedPref(0);
        Log.d("DESTROY", "onDestroy called");
    }

    private void updateUi() {

        createTv.setText(Integer.toString(create.getValue()));
        startTv.setText(Integer.toString(start.getValue()));
        resumeTv.setText(Integer.toString(resume.getValue()));
    }


    public void sharedPref(int operation) {
        SharedPreferences prefPut = getSharedPreferences("value", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        created = create.getValue();
        started =start.getValue();
        resumed = resume.getValue();
        switch (operation) {
            case 0:
            {
                prefEditor.putInt("Created",created);
                prefEditor.putInt("Started",started);
                prefEditor.putInt("Resumed",resumed);
                prefEditor.commit();
                break;
            }
            case 1:
            {
                prefEditor.putInt("Created",0);
                prefEditor.putInt("Started",0);
                prefEditor.putInt("Resumed",0);
                prefEditor.commit();
                break;
            }
            default:
                createTv.setText("Error");
                Log.d("error", "invalid operation parameter" );
                break;
        }
    }
    public void showSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("value", Activity.MODE_PRIVATE);
        int cr = sharedPreferences.getInt("Created",40);
        int st = sharedPreferences.getInt("Started",20);
        int re = sharedPreferences.getInt("Resumed",30);
        createTv.setText(String.valueOf(cr));
        startTv.setText(String.valueOf(st));
        resumeTv.setText(String.valueOf(re));
    }
}