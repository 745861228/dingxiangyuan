package com.me.dingxiangyuan.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.me.dingxiangyuan.bean.NetDataBean;
import com.me.dingxiangyuan.sqlite.MySqliteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * author by LiKe on 2016/12/28.
 */

public class NetTabDao {

    private MySqliteOpenHelper mySqliteOpenHelper;

    public NetTabDao(Context context) {
        mySqliteOpenHelper = new MySqliteOpenHelper(context);
    }

    /**
     * 添加数据
     */
    public void insertData(String path, String data, String time) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from netDataTab where path = ?", new String[]{path});
        while (cursor.moveToNext()) {
            updateData(path, time,data);
            return;
        }
        db.beginTransaction();//开启事物
        try {
            ContentValues values = new ContentValues();
            values.put("path", path);
            values.put("data", data);
            values.put("time", time);
            db.insert("netDataTab", null, values);
            db.setTransactionSuccessful();          //添加成功
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction(); // 结束事务
            db.close();
        }
    }

    /**
     * 查询数据
     * 1、定义一个集合用来封装查询下来的数据
     * 2、进行查询
     */
    List<NetDataBean> list = new ArrayList<>();

    public List<NetDataBean> selectData() {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        Cursor cursor = db.query("netDataTab", null, null, null, null, null, null);
        list.clear();
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex("path"));
            String json = cursor.getString(cursor.getColumnIndex("data"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            list.add(new NetDataBean(json, path, time));
        }
        db.close();
        return list;
    }

    /**
     * 修改数据
     */
    public void updateData(String path, String newTime,String data) {
        SQLiteDatabase db = mySqliteOpenHelper.getWritableDatabase();
        db.execSQL("update netDataTab set time=? and data=? where path = ?", new Object[]{newTime,data,path});
        db.close();
    }


}
