package com.example.du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.database.DbHelper;
import com.example.du_an_mau.model.ThanhVien;

import java.util.ArrayList;

public class thanhviendao {
    private DbHelper dbHelper;

    public thanhviendao(Context context) {
       dbHelper=new DbHelper(context);
    }
    public ArrayList<ThanhVien> taoDuLieu() {
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM ThanhVien", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    ThanhVien tv = new ThanhVien();
                    tv.setMaTV(cursor.getInt(0));
                    tv.setHoTen(cursor.getString(1));
                    tv.setNamSinh(cursor.getString(2));
                    list.add(tv);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public boolean add(ThanhVien tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("hoTen", tv.getHoTen());
        values.put("namSinh", tv.getNamSinh());
        long row = db.insert("ThanhVien", null, values);
        return (row > 0);
    }
    public boolean delete(ThanhVien tv) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("ThanhVien", "maTv=?", new String[]{String.valueOf(tv.getMaTV())});
        return (row > 0);
    }
}
