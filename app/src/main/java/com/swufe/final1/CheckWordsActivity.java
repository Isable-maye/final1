package com.swufe.final1;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.swufe.final1.database.DBWords;
import com.swufe.final1.info.Word;

import java.util.ArrayList;

public class CheckWordsActivity extends AppCompatActivity {
    DBWords dbWords;
    EditText tv_word,tv_translate;
    Button query_btn_translate,query_btn_word,clear;
    ArrayList<Word> words;
    int i = 0;   //背诵数量
    int a = 1;   //删除数量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_words);

        dbWords = new DBWords(getApplication(),"tb_words",null,1);
        tv_word = (EditText) findViewById(R.id.query_et_word);
        tv_translate = (EditText) findViewById(R.id.query_et_translate);
        query_btn_translate = (Button) findViewById(R.id.query_btn_translate);
        query_btn_word = (Button) findViewById(R.id.query_btn_word);
        clear = (Button)findViewById(R.id.clear);
        setListener();
    }
    //设置按钮监听
    private void setListener()
    {
        //汉译英
        query_btn_translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_word.setText(getWords());
            }
        });
        //英译汉
        query_btn_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_translate.setText(getTranslate());
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_word.setText("");
                tv_translate.setText("");
            }
        });
    }

    private String getWords(){
        String translate = tv_translate.getText().toString();
        String word = null;
        Cursor cursor = dbWords.getReadableDatabase().query("tb_words",null,"translate = ? ", new String[]{translate},null,null,null);
        if (cursor.getCount()==0)
            Toast.makeText(CheckWordsActivity.this,"没有该单词!",Toast.LENGTH_LONG).show();
        else {
            while (cursor.moveToNext()){
                Word word1 = new Word();
                //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
                word1.setWord(cursor.getString(cursor.getColumnIndex("word")));
                word = word1.getWord();
            }
        }
        return word;
    }

    private String getTranslate(){
        String word = tv_word.getText().toString();
        String translate = null;
        Log.d("1111", "onClick: ");
        Cursor cursor = dbWords.getReadableDatabase().query("tb_words",null,"word = ?", new String[]{word},null,null,null);
        Log.d("1111", "onClick: ");
        if (cursor.getCount()==0)
             Toast.makeText(CheckWordsActivity.this,"没有该单词!",Toast.LENGTH_LONG).show();
        else {
            while (cursor.moveToNext()){
                Word word1 = new Word();
                //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
                word1.setTranslate(cursor.getString(cursor.getColumnIndex("translate")));
                translate = word1.getTranslate();
            }
        }
        return translate;
    }
}
