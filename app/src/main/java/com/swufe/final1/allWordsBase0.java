package com.swufe.final1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.swufe.final1.database.DBWords;
import com.swufe.final1.info.Word;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//原版
public class allWordsBase0 extends AppCompatActivity {

    DBWords dbWords;
    ListView listView;
    List<Map<String, Object>> list;
    private static final String TAG = "allWordsBase0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_all_base);
        dbWords = new DBWords(allWordsBase0.this, "tb_words", null, 1);
       final ArrayList<Word> words = getWords();

        listView = (ListView) findViewById(R.id.listAll);

        list = new ArrayList<Map<String, Object>>();


        /**
         * 把单词添加到列表组里
         */
        for (int i = 0; i < words.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", words.get(i).getNumber() + ".");
            map.put("word", words.get(i).getWord());
            map.put("translate", words.get(i).getTranslate());
            map.put("true",words.get(i).getRight());
           // map.put("false",words.get(i).getWrong());
            list.add(map);
        }

        final SimpleAdapter simpleAdapter = new SimpleAdapter(allWordsBase0.this,
                list, R.layout.list_item,
               new String[]{"id", "word", "translate","true"},
                new int[]{R.id.id, R.id.word, R.id.translate,R.id.rightt});
               // new String[]{"id", "word", "translate"},
               // new int[]{R.id.id, R.id.word, R.id.translate});

        listView.setAdapter(simpleAdapter);

        //短按事件处理
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();

                String word =  words.get(position).getWord();
                String translate = words.get(position).getTranslate();
                String number = words.get(position).getNumber();

                Intent intent = new Intent(getApplication(),DetailWordActivity.class);
                bundle.putString("word",word);
                bundle.putString("translate",translate);
                bundle.putString("number",number);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        //长按事件处理--删除
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(allWordsBase0.this);
                builder.setTitle("提示")
                        .setMessage("请确认是否删除当前数据")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i(TAG, "onClick: 对话框事件处理");
                                // 删除数据项
                                list.remove(position);
                                String number = words.get(position).getNumber();
                                dbWords.deleteWord(number);
                                Toast.makeText(allWordsBase0.this,"删除成功!", Toast.LENGTH_LONG).show();
                                // 更新适配器
                                simpleAdapter.notifyDataSetChanged();
                            }
                        }).setNegativeButton("否", null); builder.create().show();
                return true;
                //屏蔽短按事件 true
            }
        });
    }

    /**
     * 获取单词组
     * @return
     */
    private ArrayList<Word> getWords() {
        ArrayList<Word> words = new ArrayList<>();
        Cursor cursor = dbWords.getReadableDatabase().query("tb_words", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Word word = new Word();
            //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
            word.setNumber(cursor.getString(cursor.getColumnIndex("numbers")));
            word.setWord(cursor.getString(cursor.getColumnIndex("word")));
            word.setTranslate(cursor.getString(cursor.getColumnIndex("translate")));
            words.add(word);
        }
        return words;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       // super.onResume();
        ArrayList<Word> words = getWords();

        list = new ArrayList<Map<String, Object>>();

        /**
         * 把单词添加到列表组里
         */
        for (int i = 0; i < words.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", words.get(i).getNumber() + ".");
            map.put("word", words.get(i).getWord());
            map.put("translate", words.get(i).getTranslate());
            list.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(allWordsBase0.this, list, R.layout.list_item,
                new String[]{"id", "word", "translate"}, new int[]{R.id.id, R.id.word, R.id.translate});

        listView.setAdapter(simpleAdapter);

    }
}
