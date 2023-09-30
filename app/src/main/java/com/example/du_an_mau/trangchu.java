package com.example.du_an_mau;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.du_an_mau.dn_dk.dangnhap;
import com.example.du_an_mau.fragment.doanhthu;
import com.example.du_an_mau.fragment.doimk;
import com.example.du_an_mau.fragment.qlloaisach;
import com.example.du_an_mau.fragment.qlphieumuon;
import com.example.du_an_mau.fragment.qlsach;
import com.example.du_an_mau.fragment.qlthanhvien;
import com.example.du_an_mau.fragment.top10sach;
import com.google.android.material.navigation.NavigationView;

public class trangchu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);
        DrawerLayout dralayout = findViewById(R.id.dralayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationView nav = findViewById(R.id.menunav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                dralayout, toolbar, R.string.open, R.string.close);
        dralayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        //xử lý sự kiện khi kích chọn item
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.qlphieumuon) {
                    toolbar.setTitle("Quản lý phiếu mượn");
                    qlphieumuon qlphieumuon = new qlphieumuon();
                    replay(qlphieumuon);
                } else if (item.getItemId() == R.id.qlloaisach) {
                    toolbar.setTitle("Quản lý loại sách");
                    qlloaisach loaisach = new qlloaisach();
                    replay(loaisach);
                } else if (item.getItemId() == R.id.qlsach) {
                    toolbar.setTitle("Quản lý sách");
                    qlsach sach = new qlsach();
                    replay(sach);
                } else if (item.getItemId() == R.id.qlthanhvien) {
                    toolbar.setTitle("Quản lý thành viên");
                    qlthanhvien thanhvien = new qlthanhvien();
                    replay(thanhvien);
                }  else if (item.getItemId() == R.id.topsach) {
                    toolbar.setTitle("Top 10 sách mượn nhiều nhất");
                    top10sach topsach = new top10sach();
                    replay(topsach);
                } else if (item.getItemId() == R.id.doanhthu) {
                    toolbar.setTitle("Doanh thu");
                    doanhthu doanhthu = new doanhthu();
                    replay(doanhthu);
                } else if (item.getItemId() == R.id.doimk) {
                    toolbar.setTitle("Đổi mật khẩu");
                    doimk doimk = new doimk();
                    replay(doimk);
                }else {
                    Intent i = new Intent(trangchu.this, dangnhap.class);
                    finish();
                }
                return true;
            }
        });
    }



    public void replay(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frglayout, fragment).commit();

    }
}