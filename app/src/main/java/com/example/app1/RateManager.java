package com.example.app1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RateManager {
    private DBHelper dbHelper;
    private String TBNAME;
    private static final String TAG = "DBHELPER";
    public RateManager(Context context){
        dbHelper=new DBHelper(context);
        TBNAME=DBHelper.TB_NAME;
    }
    public void add(RateItem item){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("curname",item.getCurName());
        values.put("currate",item.getCurRate());
        db.insert(TBNAME,null,values);
        Log.i(TAG,"hhhhhinsert"+item.getCurName()+"--"+item.getCurRate());
        db.close();
    }
    public RateItem findById(int id){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query(TBNAME,null,"ID=?",new String[]{String.valueOf(id)},null,null,null);
        RateItem item=null;
        if(cursor!=null&&cursor.moveToFirst()){
            item=new RateItem();
            item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            item.setCurName(cursor.getString(cursor.getColumnIndex("CURNAME")));
            item.setCurRate(cursor.getString(cursor.getColumnIndex("CURRATE")));
            Log.i(TAG,"hhhhfind"+id);
            cursor.close();
        }
        db.close();
        Log.i(TAG,"hhhhitem:"+item.getCurName()+"--"+item.getCurRate());
        return item;
    }
}
