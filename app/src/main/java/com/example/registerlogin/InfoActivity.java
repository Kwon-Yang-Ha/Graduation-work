package com.example.registerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        CheckBox mCheckBox = (CheckBox) findViewById(R.id.ch_m);
        CheckBox nCheckBox = (CheckBox) findViewById(R.id.ch_n);

        Button myButton = (Button) findViewById(R.id.bt_check);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckBox.isChecked()&&nCheckBox.isChecked()) {
                    // 체크박스가 체크되어 있으면 다음 화면으로 이동
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                } else {
                    // 체크박스가 체크되어 있지 않으면 메시지를 표시
                    Toast toast = Toast.makeText(getApplicationContext(), "약관에 동의해 주세요", Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toast.show();
                }
            }
        });

        Button cButton = (Button) findViewById(R.id.bt_cancel);
        cButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

}