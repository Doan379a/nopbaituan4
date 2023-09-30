package com.example.du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_mau.database.DbHelper;
import com.example.du_an_mau.model.Sach;
import com.example.du_an_mau.model.ThanhVien;

import java.util.ArrayList;

public class sachdao {
    private DbHelper dbHelper;
    public sachdao(Context context) {
        dbHelper=new DbHelper(context);
    }
    public ArrayList<Sach> taoDuLieu() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        try {
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM Sach", null);
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Sach s = new Sach();
                    s.setMaSach(cursor.getInt(0));
                    s.setTenSach(cursor.getString(1));
                    s.setGiaThue(cursor.getInt(2));
                    s.setMaLoai(cursor.getInt(3));
                    list.add(s);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public boolean add(Sach s) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSach", s.getTenSach());
        values.put("giaThue",String.valueOf(s.getGiaThue()));
        values.put("maLoai",String.valueOf(s.getMaLoai()));
        long row = db.insert("Sach", null, values);
        return (row > 0);
    }
    public boolean delete(Sach s) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long row = db.delete("Sach", "maSach=?", new String[]{String.valueOf(s.getMaSach())});
        return (row > 0);

    }
}
