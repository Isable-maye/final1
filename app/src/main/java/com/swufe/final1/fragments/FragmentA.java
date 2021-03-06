package com.swufe.final1.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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

public class FragmentA extends Fragment {

    /**
     * 创建控件
     */
    String title;

    public String getTitle() {
        return title;
    }

    TextView textView;
    EditText editText;
    Button button_ensure, button_forget,button_next;
    DBWords dbWords;
     int wordscount = 0;
    int number = 0;
    Word word = null;
    int count = 0;
    int rightt = 0; //正确数量
    int wrong = 0; //错误数量
    int delete = 0; //删除数量

    public FragmentA(String title)
    {
        super();
        this.title = title;;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getContext();
        textView = (TextView) view.findViewById(R.id.tv_word_1);
        editText = (EditText) view.findViewById(R.id.et_translate);
        button_ensure = (Button) view.findViewById(R.id.btn_ensure);
        button_forget = (Button) view.findViewById(R.id.btn_forget);
        button_next = (Button)view.findViewById(R.id.btn_next);

        dbWords = new DBWords(getActivity(), "tb_words", null, 1);
        //dbResult = new DBResult(getActivity(), "tb_result", null, 1);

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
            textView.setText(word.getWord());
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
                if (word.getTranslate().equals(editText.getText().toString())) {
                    Toast.makeText(getActivity(),"答对啦，棒棒哒！",Toast.LENGTH_LONG).show();
                    rightt++;
                    //dbWords.updateRightt(Integer.toString(number),Integer.toString(rightt));
                    editText.setText("");
                } else {
                    Toast.makeText(getActivity(),"不对哦，再动动你的小脑袋瓜~",Toast.LENGTH_LONG).show();
                    wrong++;
                    // dbWords.updateData(Integer.toString(number),right,wrong);
                   // dbWords.updateWrong(Integer.toString(number),Integer.toString(wrong));
                    editText.setText("");
                }
                // Test();
            }
        });

        /**
         * 设置遗忘按钮的监听
         * 点击遗忘 单词
         */
        button_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                dbWords.deleteWord(number+"");
//                textView.setText("");
//                editText.setText("");
//                delete++;
                editText.setText(word.getTranslate());
                Toast.makeText(getActivity(),"再接再厉呀~",Toast.LENGTH_LONG).show();
                wrong++;
              //  dbWords.updateWrong(Integer.toString(number),Integer.toString(wrong));
                //  dbWords.updateData(Integer.toString(number),right,wrong);
                //Test();
            }
        });

        //下一个
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
        //dbResult.writeData(dbResult.getReadableDatabase(), "正确：" + right, "错误：" + wrong, "删除：" + delete);
        //dbWords.writeData(dbWords.getReadableDatabase(), Integer.toString(number),right,wrong);
        //word = dbWords.getWord(number);
        //dbWords.updateData(Integer.toString(number),Integer.toString(right),Integer.toString(wrong));
        dbWords.updateRightt(Integer.toString(number),Integer.toString(rightt));

        dbWords.updateWrong(Integer.toString(number),Integer.toString(wrong));

        wrong = 0;
        delete = 0;
        rightt = 0;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("已经背完一组咯~").setMessage("是否再来一组？")
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
                textView.setText("");
                editText.setText("");
            }
        }).show();
    }
}
