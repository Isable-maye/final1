package com.swufe.final1.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.swufe.final1.R;
import com.swufe.final1.database.DBWords;
import com.swufe.final1.info.Word;

/**
 * Created by Administrator on 2019/6/18.
 */

public class FragmentB extends Fragment {
    /**
     * 创建控件
     */
    String title;
    TextView textView;
    EditText editText;
    Button button_ensure, button_forget,button_next;
    DBWords dbWords;
    int wordscount = 0;
    int number = 0;
    Word word ;

    public String getTitle() {
        return title;
    }

    int count = 0;
    int right = 0; //正确数量
    int wrong = 0; //错误数量

    public FragmentB(String title){
        super();
        this.title = title;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getContext();
        textView = (TextView) view.findViewById(R.id.b_tv_translate);
        editText = (EditText)view.findViewById(R.id.b_et_word);
        button_ensure = (Button) view.findViewById(R.id.b_btn_ensure);
        button_forget = (Button) view.findViewById(R.id.b_btn_forget);
        button_next = (Button)view.findViewById(R.id.b_btn_next);
        dbWords = new DBWords(getActivity(), "tb_words", null, 1);
       // dbResult = new DBResult(getActivity(), "tb_result", null, 1);

        setButtonListener();
        Test();
    }

    /**
     * 随机取单词测试
     */
    public void Test() {
        if (count < 10) {
            wordscount = dbWords.getCount();
            number = (int) (wordscount * Math.random());
            word = dbWords.getWord(number);
            textView.setText(word.getTranslate());
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
                if (word.getWord().equals(editText.getText().toString())) {
                    Toast.makeText(getActivity(),"回答正确！",Toast.LENGTH_LONG).show();
                    right++;
                    editText.setText("");
                } else {
                    Toast.makeText(getActivity(),"回答错误！",Toast.LENGTH_LONG).show();
                    wrong++;
                    editText.setText("");
                }
                //Test();
            }
        });

        /**
         * 设置删除按钮的监听
         * 点击删除单词
         */
        button_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(word.getWord());
                Toast.makeText(getActivity(),"頑張れ~",Toast.LENGTH_LONG).show();
                wrong++;
            }
        });
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                Test();
            }
        });
    }

    /**
     * 判断是否再来一组
     * 选“是”则再来一组
     * 选“否”则停止背词
     */
    public void ifBackToFirst() {
       // dbResult.writeData(dbResult.getReadableDatabase(), "正确：" + right, "错误：" + wrong, "删除：" + delete);
       // dbWords.updateData(Integer.toString(number),right,wrong);
        wrong = 0;
        right = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("已经背完一组啦！").setMessage("是否再来一组？")
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
                Toast.makeText(getActivity(),"已背到完一组啦！",Toast.LENGTH_LONG);
                textView.setText("");
                editText.setText("");
            }
        }).show();
    }
}
