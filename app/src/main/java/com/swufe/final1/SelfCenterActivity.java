package com.swufe.final1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SelfCenterActivity extends AppCompatActivity {

    TextView textView_explain, textView_resetname, textView_allword, textView_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_center);
        textView_explain = (TextView) findViewById(R.id.self_tv_explain);
        //textView_resetname = (TextView) findViewById(R.id.self_tv_resetname);

        textView_allword = (TextView) findViewById(R.id.self_tv_allwords);
        //textView_result = (TextView) findViewById(R.id.self_tv_result);

        //点击软件说明
        textView_explain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), Explain.class);
                startActivity(intent);
            }
        });


        //点击单词库
        textView_allword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), allWordsBase0.class);
                startActivity(intent);
            }
        });

//        //点击单词战况
//        textView_result.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplication(), Result.class);
//                startActivity(intent);
//            }
//        });
    }
}
