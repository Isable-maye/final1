package com.swufe.final1;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.swufe.final1.database.DBWords;


public class InputWordsActivity extends AppCompatActivity {

    /**
     * 创建控件
     */
    TextView textView;
    Button input, button_input, button_recite, button_check, button_self;
    EditText editText_word, editText_translate;
    DBWords dbWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_words);

        /**
         * 初始化控件
         */
        textView = (TextView) findViewById(R.id.name);
        editText_word = (EditText) findViewById(R.id.et_words);
        editText_translate = (EditText) findViewById(R.id.et_translate);
        input = (Button) findViewById(R.id.btn);
        dbWords = new DBWords(getApplication(), "tb_words", null, 1);
        getButtons();
        //给录入按钮设置监听的方法
        setInputListener();
        setListener();

        Bundle bundle = getIntent().getExtras();
        try {
            textView.setText(bundle.getString("name"));
        }catch (Exception e){

        }
    }


    /**
     * 给录入按钮设置监听
     * 把单词录到单词数据库
     */
    public void setInputListener() {
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String word = editText_word.getText().toString();
                String translate = editText_translate.getText().toString();

                if (word.isEmpty() || translate.isEmpty()){
                     Toast.makeText(InputWordsActivity.this,"请输入数据",Toast.LENGTH_LONG).show();
                }else{
                    //数据库操作：插入新单词
                    dbWords.writeWord(word, translate);
                    Toast.makeText(InputWordsActivity.this,"录入成功",Toast.LENGTH_LONG).show();
                    editText_translate.setText("");
                    editText_word.setText("");
                    editText_word.requestFocus();
                }

            }
        });
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
