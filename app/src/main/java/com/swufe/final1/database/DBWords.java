package com.swufe.final1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.swufe.final1.info.Word;

//这个是改到一半崩坏的版本
//这个就相当于惯例命名方式的DBHelper
public class DBWords extends SQLiteOpenHelper {
    //  private static final int VERSION = 1;
    int number = 0;
//    private static final String DB_NAME = "tv_words.db";
//    private static final String TB_NAME = "tv_words";

    final String Create_Table_SQL = "create table tb_words (_id integer primary key autoincrement,numbers,word,translate,t,f)";

    //final String Create_Table_SQL = "create table "+TB_NAME +"(,,,,,)";
    //"(_id integer primary key autoincrement,numbers,word,translate,t,f)";//,true,false

    // final String Create_Table_SQL1 = "CREATE TABLE "+TB_NAME+" (, numbers text, word text, translate text, t integer, f integer)";
//    final String Create_SQL="create table tv_words(" +
//           "_id integer primary key autoincrement," +
//           "name varchar(30)," +
//           "numbers varchar(30)," +
//           "word varchar(30)," +
//           "translate varchar(30)," +
//           "t varchar(30)," +
//           "f varchar(30))"
//           ;

    //db.execSQL("create table username( name varchar(5) primary key,password varchar(30))");
//    public DBWords(Context context){
//        this(context,DB_NAME,null,VERSION);
//    }

    public DBWords(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Create_Table_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("delete from tb_words");
        sqLiteDatabase.execSQL("update sqlite_sequence SET seq = 0 where name ='tb_words'");
    }

    /**
     * 单词的添加方法
     */
    public void writeWord(String word, String translate) {
        number++;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("numbers",number+"");
        values.put("word", word);
        values.put("translate", translate);
        //values.put("t","0");
        //values.put("f","0");
        db.insert("tb_words", null, values);
    }



    /**
     * 删除单词方法
     *
     * @param id
     */
    public void deleteWord(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereValue = { id+"" };
        db.delete("tb_words", "numbers = ?", whereValue);

    }

    /**
     * 更新正误率
     *
        * @param id
        * @param right
        * @param wrong
     */
    public void updateData(String id, int right, int wrong) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "numbers = ?";
        String[] whereValue = { id };

        ContentValues values = new ContentValues();
        values.put("t", right);
        values.put("f", wrong);
        db.update("tb_words", values, where, whereValue);
    }

    public void updateWord(String id, String word, String translate) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "numbers = ?";
        String[] whereValue = { id };

        ContentValues values = new ContentValues();
        values.put("word", word);
        values.put("translate", translate);
        db.update("tb_words", values, where, whereValue);
    }


    /**
     * 获取单词
     *
     * @param id
     * @return
     */
    public Word getWord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Word word = new Word();
        Cursor cursor = db.query("tb_words", null, null, null, null, null, null);
        cursor.moveToPosition(id);
        try {
            word.setWord(cursor.getString(cursor.getColumnIndex("word")));
            word.setTranslate(cursor.getString(cursor.getColumnIndex("translate")));
        }catch (Exception e){

        }
        return word;
    }


    public int getCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("tb_words", null, null, null, null, null, null);
        return cursor.getCount();
    }
}
