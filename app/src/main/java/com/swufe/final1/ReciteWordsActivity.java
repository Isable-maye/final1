package com.swufe.final1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.swufe.final1.Adapter.FragmentAdpter;
import com.swufe.final1.fragments.FragmentA;
import com.swufe.final1.fragments.FragmentB;
import com.swufe.final1.fragments.Recite2;

import java.util.ArrayList;
import java.util.List;

public class ReciteWordsActivity extends AppCompatActivity {

    /**
     * 创建元素
     */
    Button button_input, button_recite, button_check, button_self;
    TabLayout tableLayout;
    ViewPager viewPager;
    List<Fragment> fragments;
    String[] strings = null;
    FragmentA fragmentA;
    FragmentB fragmentB;
    //FragmentC fragmentC;
   //Recite recite;
   Recite2 recite2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite_words);

        /**
         * 初始化
         */
        viewPager = (ViewPager) findViewById(R.id.vp);
        tableLayout = (TabLayout) findViewById(R.id.tl);

        /**
         * 把页面添加到页面组
         */
        fragmentA = new FragmentA("单词写译");
        fragmentB = new FragmentB("翻译写英");
        //recite = new Recite("再认");
        //fragmentC = new FragmentC("再认");
        recite2 = new Recite2("再认");

        fragments = new ArrayList<>();
        fragments.add(fragmentA);
        fragments.add(fragmentB);
        //fragments.add(fragmentC);
        fragments.add(recite2);
        //fragments.add(recite);

        //strings = new String[]{"英译汉", "汉译英","再认"};
        FragmentAdpter fragmentAdpter = new FragmentAdpter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(fragmentAdpter);

        tableLayout.setupWithViewPager(viewPager);
        getButtons();
        setListener();
    }



    public void getButtons() {
        button_input = (Button) findViewById(R.id.btn_input);
        button_recite = (Button) findViewById(R.id.btn_recite);
        button_check = (Button) findViewById(R.id.btn_check);
        button_self = (Button) findViewById(R.id.btn_selfcenter);
    }


    public void setListener() {

        /**
         * 录词按钮设置监听
         * 实现跳转
         */
        button_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), InputWordsActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 背词按钮设置监听
         * 实现跳转
         */
        button_recite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), ReciteWordsActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 查词按钮设置监听
         * 实现跳转
         */
        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), CheckWordsActivity.class);
                startActivity(intent);
            }
        });

        /**
         * 个人中心按钮设置监听
         * 实现跳转
         */
        button_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), SelfCenterActivity.class);
                startActivity(intent);
            }
        });
    }
}
