package com.example.registerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecyclingActivity extends AppCompatActivity {
    TextView tx_com;

    TextView tx_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycling);

        tx_com = (TextView) findViewById(R.id.tx_com);
        tx_me = (TextView) findViewById(R.id.tx_me);

        tx_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclingActivity.this,CompanyActivity.class);
                startActivity(intent);
            }
        });
        tx_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclingActivity.this,MeActivity.class);
                startActivity(intent);
            }
        });
    }
}