package com.example.registerlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BoardActivity extends AppCompatActivity {
    TextView tx_hot;
    TextView tx_free;
    TextView tx_need;
    TextView tx_tp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        tx_hot = (TextView) findViewById(R.id.tx_com);
        tx_free = (TextView) findViewById(R.id.tx_me);
        tx_need = (TextView) findViewById(R.id.need);
        tx_tp = (TextView) findViewById(R.id.tip);
        tx_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this,HotActivity.class);
                startActivity(intent);
            }
        });
        tx_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this,FreeActivity.class);
                startActivity(intent);
            }
        });
        tx_need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this,NeedActivity.class);
                startActivity(intent);
            }
        });
        tx_tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardActivity.this,TipActivity.class);
                startActivity(intent);
            }
        });
    }
}
