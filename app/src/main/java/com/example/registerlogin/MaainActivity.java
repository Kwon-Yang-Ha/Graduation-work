package com.example.registerlogin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Size;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class MaainActivity extends AppCompatActivity {

    TextView text_more;
    TextView text_find;
    TextView text_free;
    TextView text_tip;
    TextView text_hot;
    TextView text_need;

    private ViewPager2 viewPager2;
    private ImageSliderAdapter adapter;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private String[] mRanking = {
            "1등 디투금쪽이들",
            "2등 에이투금쪽이들",
            "3등 씨세븐금쪽이들",
            "4등 씨나인금쪽이들",
            "5등 씨식스금쪽이들",
            "6등 디나인금쪽이들",
            "7등 디일레븐금쪽이들",
            "8등 씨썰틴금쪽이들" };
    private int mCurrentIndex = 0;
    private final int mDelay = 3000; // 스크롤 딜레이(ms)
    private Handler mHandler = new Handler();
    private Runnable mScrollRunnable = new Runnable() {
        @Override
        public void run() {
            if (mCurrentIndex >= mAdapter.getCount()) {
                mCurrentIndex = 0;
            }
            mListView.smoothScrollToPosition(mCurrentIndex++);
            mHandler.postDelayed(mScrollRunnable, mDelay);
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maain);

        TextView trashsetting = findViewById(R.id.trashsetting);
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);

        // 저장된 값을 가져오기
        String monid = sharedPreferences.getString("monday", "");
        String tueid = sharedPreferences.getString("tuesday", "");
        String wenid = sharedPreferences.getString("wednesday", "");
        String thuid = sharedPreferences.getString("thursday", "");
        String friid = sharedPreferences.getString("friday", "");
        String satid = sharedPreferences.getString("saturday", "");
        String sunid = sharedPreferences.getString("sunday", "");

        // 요일에 따른 텍스트 설정
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                if (sunid != null) {
                    trashsetting.setText("일요일입니다. " + sunid + " 배출일입니다.");
                } else {
                    trashsetting.setText("해당 요일에는 쓰레기 배출이 없습니다.");
                }
                break;
            case Calendar.MONDAY:
                if (monid != null) {
                    trashsetting.setText("월요일입니다. " + monid + " 배출일입니다.");
                } else {
                    trashsetting.setText("해당 요일에는 쓰레기 배출이 없습니다.");
                }
                break;
            case Calendar.TUESDAY:
                if (tueid != null) {
                    trashsetting.setText("화요일입니다. " + tueid + " 배출일입니다.");
                } else {
                    trashsetting.setText("해당 요일에는 쓰레기 배출이 없습니다.");
                }
                break;
            case Calendar.WEDNESDAY:
                if (wenid != null) {
                    trashsetting.setText("수요일입니다. " + wenid + " 배출일입니다.");
                } else {
                    trashsetting.setText("해당 요일에는 쓰레기 배출이 없습니다.");
                }
                break;
            case Calendar.THURSDAY:
                if (thuid != null) {
                    trashsetting.setText("목요일입니다. " + thuid + " 배출일입니다.");
                } else {
                    trashsetting.setText("해당 요일에는 쓰레기 배출이 없습니다.");
                }
                break;
            case Calendar.FRIDAY:
                if (friid != null) {
                    trashsetting.setText("금요일입니다. " + friid + " 배출일입니다.");
                } else {
                    trashsetting.setText("해당 요일에는 쓰레기 배출이 없습니다.");
                }
                break;
            case Calendar.SATURDAY:
                if (satid != null) {
                    trashsetting.setText("토요일입니다. " + satid + " 배출일입니다.");
                } else {
                    trashsetting.setText("해당 요일에는 쓰레기 배출이 없습니다.");
                }
                break;
        }

        ImageButton searchbutton = (ImageButton) findViewById(R.id.imageButton1);
        searchbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Searching.class);
                startActivity(intent);
            }
        });

        trashsetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TrashSetting.class);
                startActivity(intent);
            }

        });

        // Get reference to ViewPager2
        viewPager2 = findViewById(R.id.view_pager);
        ImageView imageView = findViewById(R.id.image_view);

        // Create and set adapter
        adapter = new ImageSliderAdapter(this);
        viewPager2.setAdapter(adapter);

        // Set infinite scrolling enabled
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setCurrentItem(1, false);
        viewPager2.post(new Runnable() {
            @Override
            public void run() {
                viewPager2.setCurrentItem(0);
            }
        });
        viewPager2.setClipToPadding(false);
        viewPager2.setPadding(0, 0, 0, 0);

        for (int i = 0; i < viewPager2.getChildCount(); i++) {
            View child = viewPager2.getChildAt(i);

            if (child instanceof ImageView) {
                ImageView imageView2 = (ImageView) child;

                imageView2.setScaleType(ImageView.ScaleType.FIT_XY);

                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();

                layoutParams.setMargins(0, 0, 0, 0);
                imageView2.setLayoutParams(layoutParams);
            }
        }
        mListView = findViewById(R.id.list_view);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Ranking.class);
                startActivity(intent);
            }
        });
        mAdapter = new ArrayAdapter<>(this, R.layout.listitem, R.id.listitem, mRanking);
        mListView.setAdapter(mAdapter);
        mHandler.postDelayed(mScrollRunnable, mDelay);
    }
    @Override
    protected void onResume() {
        super.onResume();

        TextView trashsetting = findViewById(R.id.trashsetting);
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        SharedPreferences sharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE);

        // 저장된 값을 가져오기
        String monid = sharedPreferences.getString("monday", "");
        String tueid = sharedPreferences.getString("tuesday", "");
        String wenid = sharedPreferences.getString("wednesday", "");
        String thuid = sharedPreferences.getString("thursday", "");
        String friid = sharedPreferences.getString("friday", "");
        String satid = sharedPreferences.getString("saturday", "");
        String sunid = sharedPreferences.getString("sunday", "");

        // 요일에 따른 텍스트 설정
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                trashsetting.setText("일요일입니다. "+ sunid +" 배출일입니다." );
                break;
            case Calendar.MONDAY:
                trashsetting.setText("월요일입니다. "+ monid +" 배출일입니다.");
                break;
            case Calendar.TUESDAY:
                trashsetting.setText("화요일입니다. "+ tueid +" 배출일입니다.");
                break;
            case Calendar.WEDNESDAY:
                trashsetting.setText("수요일입니다. "+ wenid +" 배출일입니다.");
                break;
            case Calendar.THURSDAY:
                trashsetting.setText("목요일입니다. "+ thuid +" 배출일입니다.");
                break;
            case Calendar.FRIDAY:
                trashsetting.setText("금요일입니다."+ friid +"배출일입니다.");
                break;
            case Calendar.SATURDAY:
                trashsetting.setText("토요일입니다. "+ satid +" 배출일입니다.");
                break;
        }

        ImageButton carmeraButton = (ImageButton) findViewById(R.id.im_ca);

        carmeraButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), ShootActivity.class);
            startActivity(intent);
        }
    });

        ImageButton recyclingButton = (ImageButton) findViewById(R.id.im_re);

        recyclingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecyclingActivity.class);
                startActivity(intent);
            }
        });

        text_find = (TextView) findViewById(R.id.tx_find);
        text_free = (TextView) findViewById(R.id.tx_free);
        text_hot = (TextView) findViewById(R.id.tx_hot);
        text_tip = (TextView) findViewById(R.id.tx_tip);
        text_need = (TextView) findViewById(R.id.tx_need);
        text_more = (TextView) findViewById(R.id.tx_more);


        text_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaainActivity.this,BoardActivity.class);
                startActivity(intent);
            }
        });

        text_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaainActivity.this,BoardActivity.class);
                startActivity(intent);
            }
        });
        text_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaainActivity.this,FreeActivity.class);
                startActivity(intent);
            }
        });
        text_tip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaainActivity.this,TipActivity.class);
                startActivity(intent);
            }
        });
        text_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaainActivity.this,HotActivity.class);
                startActivity(intent);
            }
        });
        text_need.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaainActivity.this,NeedActivity.class);
                startActivity(intent);
            }
        });
    }
}