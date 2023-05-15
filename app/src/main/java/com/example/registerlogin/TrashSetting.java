package com.example.registerlogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TrashSetting extends AppCompatActivity {
    EditText montext, tuetext, wentext, thutext, fritext, sattext, suntext;
    Button button;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trashsetting);

        // 날짜 입력 값
        montext = findViewById(R.id.moninput);
        montext.setPadding(30, 20, 20, 20);
        tuetext = findViewById(R.id.tueinout);
        tuetext.setPadding(30, 20, 20, 20);
        wentext = findViewById(R.id.weninput);
        wentext.setPadding(30, 20, 20, 20);
        thutext = findViewById(R.id.thuinput);
        thutext.setPadding(30, 20, 20, 20);
        fritext = findViewById(R.id.friinput);
        fritext.setPadding(30, 20, 20, 20);
        sattext = findViewById(R.id.satinput);
        sattext.setPadding(30, 20, 20, 20);
        suntext = findViewById(R.id.suninput);
        suntext.setPadding(30, 20, 20, 20);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 저장한 값 불러오기
                sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("monday", montext.getText().toString());
                editor.putString("tuesday", tuetext.getText().toString());
                editor.putString("wednesday", wentext.getText().toString());
                editor.putString("thursday", thutext.getText().toString());
                editor.putString("friday", fritext.getText().toString());
                editor.putString("saturday", sattext.getText().toString());
                editor.putString("sunday", suntext.getText().toString());
                editor.apply();

                Toast.makeText(TrashSetting.this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 저장된 값 불러오기
        sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
        String monid = sharedPreferences.getString("monday", "");
        String tueid = sharedPreferences.getString("tuesday", "");
        String wenid = sharedPreferences.getString("wednesday", "");
        String thuid = sharedPreferences.getString("thursday", "");
        String friid = sharedPreferences.getString("friday", "");
        String satid = sharedPreferences.getString("saturday", "");
        String sunid = sharedPreferences.getString("sunday", "");

        // 값을 출력하기
        montext.setText(monid);
        tuetext.setText(tueid);
        wentext.setText(wenid);
        thutext.setText(thuid);
        fritext.setText(friid);
        sattext.setText(satid);
        suntext.setText(sunid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // 값을 수정했을 때 메인 액티비티로 돌아갔을 때 수정된 값을 반영하도록 함
        SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);
        String monid = sharedPreferences.getString("monday", "");
        String tueid = sharedPreferences.getString("tuesday", "");
        String wenid = sharedPreferences.getString("wednesday", "");
        String thuid = sharedPreferences.getString("thursday", "");
        String friid = sharedPreferences.getString("friday", "");
        String satid = sharedPreferences.getString("saturday", "");
        String sunid = sharedPreferences.getString("sunday", "");

        Intent intent = new Intent(TrashSetting.this, MainActivity.class);
        intent.putExtra("monday", monid);
        intent.putExtra("tuesday", tueid);
        intent.putExtra("wednesday", wenid);
        intent.putExtra("thursday", thuid);
        intent.putExtra("friday", friid);
        intent.putExtra("saturday", satid);
        intent.putExtra("sunday", sunid);
        setResult(RESULT_OK, intent);
        finish();
    }
}
