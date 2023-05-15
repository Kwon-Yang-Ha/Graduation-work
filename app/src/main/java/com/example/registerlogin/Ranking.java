package com.example.registerlogin;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Ranking extends AppCompatActivity {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private SearchingAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        listView = (ListView) findViewById(R.id.rankinglist);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchingAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);


    }



    private void settingList(){
        list.add("1등 디투금쪽이들");
        list.add("2등 에이투금쪽이들");
        list.add("3등 씨세븐금쪽이들");
        list.add("4등 씨나인금쪽이들");
        list.add("5등 씨식스금쪽이들");
        list.add("6등 디나인금쪽이들");
        list.add("7등 디일레븐금쪽이들");
        list.add("8등 씨썰틴금쪽이들");
    }
}
