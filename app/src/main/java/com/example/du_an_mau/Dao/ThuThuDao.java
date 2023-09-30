package com.example.du_an_mau.Dao;

import android.content.Context;

import com.example.du_an_mau.database.DbHelper;

public class ThuThuDao {
    private DbHelper dbHelper;
    public ThuThuDao(Context context){
        dbHelper=new DbHelper(context);
    }

}
