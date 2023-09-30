package com.example.du_an_mau.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.Dao.thanhviendao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.adapter.thanhvienadapter;
import com.example.du_an_mau.model.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class qlthanhvien extends Fragment {

    RecyclerView rcvthanhvien;
    FloatingActionButton fladd;
    private ArrayList<ThanhVien> list = new ArrayList<>();
    thanhviendao dao;
    thanhvienadapter adapter;

    public qlthanhvien() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlthanhvien, container, false);
        rcvthanhvien = view.findViewById(R.id.rcvthanhvien);
        fladd = view.findViewById(R.id.fltadd);
        dao = new thanhviendao(this.getActivity());
        list = dao.taoDuLieu();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvthanhvien.setLayoutManager(layoutManager);
        adapter = new thanhvienadapter(getActivity(), list);
        rcvthanhvien.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fladd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
        return view;
    }

    public void opendialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item_addtv, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        EditText edmatv = view.findViewById(R.id.edmatv);
        EditText edtentv = view.findViewById(R.id.edtentv);
        EditText ednamsinhtv = view.findViewById(R.id.ednamsinhtv);
        Button btadd = view.findViewById(R.id.btnluuadd);
        Button btnhuy = view.findViewById(R.id.btnhuyadd);
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matv = edmatv.getText().toString();
                String tentv = edtentv.getText().toString();
                String namsinhtv = ednamsinhtv.getText().toString();
                ThanhVien tv = new ThanhVien();
                if (matv.isEmpty() || tentv.isEmpty() || namsinhtv.isEmpty()) {
                    Toast.makeText(getActivity(), "không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    tv.setHoTen(tentv);
                    tv.setNamSinh(namsinhtv);
                    boolean check = dao.add(tv);
                    if (check) {
                        list.clear();
                        list.addAll(dao.taoDuLieu());
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        dialog.show();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}