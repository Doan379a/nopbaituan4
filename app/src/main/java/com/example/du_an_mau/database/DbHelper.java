package com.example.du_an_mau.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "PNLIB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String thuthu = "CREATE TABLE ThuThu(maTT TEXT PRIMARY KEY,hoTen TEXT ,matKhau TEXT)";
        db.execSQL(thuthu);
        String thanhvien = "CREATE TABLE ThanhVien(maTv INTEGER PRIMARY KEY AUTOINCREMENT,hoTen TEXT,namSinh TEXT)";
        db.execSQL(thanhvien);
        String theloaisach = "CREATE TABLE LoaiSach(maLoai INTEGER PRIMARY KEY AUTOINCREMENT,tenLoai TEXT)";
        db.execSQL(theloaisach);
        String sach = "CREATE TABLE Sach(maSach INTEGER PRIMARY KEY AUTOINCREMENT, tenSach TEXT,giaThue INTEGER,maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(sach);
        String phieumuon = "CREATE TABLE PhieuMuon(maPM INTEGER PRIMARY KEY AUTOINCREMENT,maTT TEXT REFERENCES ThuThu(maTT),maTV INTEGER REFERENCES ThanhVien(maTV),maSach INTEGER REFERENCES Sach(maSach),tienThue INTEGER,ngay TEXT,traSach INTEGER)";
        db.execSQL(phieumuon);
        String dnNguoiDung = "CREATE TABLE NGUOIDUNG(tendangnhap TEXT PRIMARY KEY, matkhau TEXT)";
        db.execSQL(dnNguoiDung);
        String dNguoiDung = "INSERT INTO NGUOIDUNG VALUES('h','h')";
        db.execSQL(dNguoiDung);
        String ndthuthu = "INSERT INTO ThuThu VALUES('adas','sds','sadas')";
        db.execSQL((ndthuthu));
        String ndthanhvien = "INSERT INTO ThanhVien VALUES (1,'adsad','adasd2323')";
        db.execSQL(ndthanhvien);
        String ndtheloaisach = "INSERT INTO LoaiSach VALUES(1,'sadsa')";
        db.execSQL(ndtheloaisach);
        String ndsach = "INSERT INTO Sach VALUES(1,'asdsa',1,1)";
        db.execSQL(ndsach);
        String ndphieumuon = "INSERT INTO PhieuMuon VALUES (1,'adas',12,2,23333,'asdas',232)";
        db.execSQL(ndphieumuon);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS ThuThu");
            db.execSQL("DROP TABLE IF EXISTS ThanhVien");
            db.execSQL("DROP TABLE IF EXISTS LoaiSach");
            db.execSQL("DROP TABLE IF EXISTS Sach");
            db.execSQL("DROP TABLE IF EXISTS PhieuMuon");
            onCreate(db);
        }
    }
}
