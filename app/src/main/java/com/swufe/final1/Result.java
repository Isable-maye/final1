package com.swufe.final1;

import android.database.Cursor;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

//import com.example.administrator.dictionary.database.DBResult;

import androidx.appcompat.app.AppCompatActivity;

import com.swufe.final1.database.DBResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result extends AppCompatActivity {
    DBResult dbResult;
    ListView listView;
    List<Map<String,Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        dbResult = new DBResult(Result.this,"tb_result",null,1);

        ArrayList<Result_item> result_items = getResult();
        listView = (ListView) findViewById(R.id.list);
        list = new ArrayList<Map<String, Object>>();

        for (int i = 0;i < result_items.size() ;i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id",result_items.get(i).id+".");
            map.put("right", result_items.get(i).right);
            map.put("wrong", result_items.get(i).wrong);
           // map.put("delete", result_items.get(i).delete);
            list.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(Result.this,list, R.layout.list_item,
                new String[]{"id","right","wrong","delete"},new int[]{R.id.id,R.id.word,R.id.translate,R.id.delete});

        listView.setAdapter(simpleAdapter);

    }

    /**
     * 取出数据库中数据
     * @return
     */
    private ArrayList<Result_item> getResult(){
        ArrayList<Result_item> result_items = new ArrayList<>();
        Cursor cursor = dbResult.getReadableDatabase().query("tb_result",null,null,null,null,null,null);
        int i = 1;
        while(cursor.moveToNext()){
            Result_item result_item = new Result_item();
            //利用getColumnIndex：String 来获取列的下标，再根据下标获取cursor的值
            result_item.id = i;
            result_item.right = cursor.getString(cursor.getColumnIndex("right"));
            result_item.wrong = cursor.getString(cursor.getColumnIndex("wrong"));
          //  result_item.delete = cursor.getString(cursor.getColumnIndex("remove"));
            result_items.add(result_item);
            i++;
        }
        return result_items;
    }
}
