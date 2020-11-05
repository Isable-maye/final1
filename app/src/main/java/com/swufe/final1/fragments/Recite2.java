package com.swufe.final1.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.swufe.final1.R;
import com.swufe.final1.database.DBWords;
import com.swufe.final1.info.Word;


public class Recite2 extends Fragment {

    //创建控件
    String title;

    public String getTitle() {
        return title;
    }

    TextView tv_translate,tv_word;

    Button button_ensure, button_delete,button_next;
    DBWords dbWords;
    //DBResult dbResult;
    int wordscount = 0;
    int number = 0;
    Word word = null;
    int count = 0;
    int right = 0; //正确数量
    int wrong = 0; //错误数量
    int delete = 0; //删除数量

    public Recite2(String title)
    {
        super();
        this.title = title;;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getContext();
        tv_word = (TextView) view.findViewById(R.id.recite_tv_word);
        tv_translate = (TextView) view.findViewById(R.id.recite_tv_translate);
        button_ensure = (Button) view.findViewById(R.id.recite_btn_know);
        button_delete = (Button) view.findViewById(R.id.recite_btn_unknow);
        button_next = (Button)view.findViewById(R.id.recite_btn_next);
        dbWords = new DBWords(getActivity(), "tb_words", null, 1);
      //  dbResult = new DBResult(getActivity(), "tb_result", null, 1);

        setButtonListener();
        Test();
    }

    /**
     * 随机取单词测试
     */
    public void Test() {
        if (count < 10) {
            wordscount = dbWords.getCount();
            Log.d(wordscount+"", "Test: 1111");
            number = (int) (wordscount * Math.random());
            word = dbWords.getWord(number);
            tv_word.setText(word.getWord());
            count++;
        } else {
            ifBackToFirst();
        }
    }

    /**
     * 设置按钮的监听
     */
    public void setButtonListener() {
        /**
         * 设置确定按钮的监听
         * 判断是否正确
         */
        button_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tv_translate.setText(word.getTranslate());
                right++;

               // Test();
            }
        });

        /**
         * 设置删除按钮的监听
         * 点击删除单词
         */
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrong++;
                tv_translate.setText(word.getTranslate());

            }
        });

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_translate.setText("");
                Test();
            }
        });

    }

    /**
     * 判断是否再来一组
     * 选“是”则再来一组
     * 选“否”则停止背词
     * 十个一组
     */
    public void ifBackToFirst() {
        //dbResult.writeData(dbResult.getReadableDatabase(), "正确：" + right, "错误：" + wrong, "删除：" + delete);
      //  dbWords.updateData(Integer.toString(number),right,wrong);
        wrong = 0;
        delete = 0;
        right = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("背完一组啦啦啦").setMessage("是否再来一组？")
                .setIcon(R.drawable.icon_image)
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                        count = 0;
                        Test();
                    }
                }).setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"已背到完一组啦",Toast.LENGTH_LONG).show();
                tv_word.setText("");
                tv_translate.setText("");
            }
        }).show();
    }
}
