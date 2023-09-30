package com.example.du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_mau.database.DbHelper;

public class daomk {
    private DbHelper dbHelper;

    public daomk(Context context) {
        dbHelper = new DbHelper(context);
    }

    public boolean checkLogin(String usename, String password) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT *FROM NGUOIDUNG WHERE tendangnhap=? AND matkhau=?", new String[]{usename, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    public boolean checkOldPassword( String matKhauCu) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG WHERE  matkhau=?", new String[]{ matKhauCu});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    // Cập nhật mật khẩu mới
    public void updatePassword(String tenDangNhap, String matKhauMoi) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("matkhau", matKhauMoi);
        db.update("NGUOIDUNG", values, "tendangnhap=?", new String[]{tenDangNhap});
        db.close();
    }








}
