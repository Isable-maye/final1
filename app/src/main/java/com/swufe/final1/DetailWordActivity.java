package com.swufe.final1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.swufe.final1.database.DBWords;

//
public class DetailWordActivity extends AppCompatActivity {

    /**
     * 创建元素
     */
    DBWords dbWords;
    TextView textView_word,textView_translate;
    Button button_modify;
    Bundle bundle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_word);

        /**
         * 初始化
         */
        dbWords = new DBWords(getApplication(),"tb_words",null,1);
        textView_word = (TextView) findViewById(R.id.detail_tv_word);
        textView_translate = (TextView) findViewById(R.id.detail_tv_translate);
        button_modify = (Button) findViewById(R.id.detail_btn_modify);
       // button_delete = (Button) findViewById(R.id.detail_btn_delete);

        /**
         * 获取bundle，显示传来的单词和翻译
         */
        bundle = getIntent().getExtras();
        textView_word.setText(bundle.getString("word"));
        textView_translate.setText(bundle.getString("translate"));

        /**
         * 给修改按钮设置监听
         */
        button_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Mdialog mdialog = new Mdialog(DetailWordActivity.this);  //实例化自定义对话框

                //给取消按钮设置监听事件
                mdialog.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mdialog.isShowing()){
                            mdialog.dismiss();  //关闭对话框
                        }
                    }
                });

                //给确定按钮设置监听事件
                mdialog.ensure.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mdialog.isShowing()){
                            String word = mdialog.editText_word.getText().toString();
                            String translate = mdialog.editText_translate.getText().toString();
                            //插入数据库 修改后的单词和翻译
                            dbWords.updateWord(bundle.getString("number"),word,translate);
                            System.out.print(bundle.getString("number"));
                            mdialog.dismiss();      //关闭对话框
                            textView_word.setText(word);
                            textView_translate.setText(translate);
                        }
                    }
                });
                mdialog.show();
            }//onClick


        });//setOnClickListener
    }//onCreate
}//Activity