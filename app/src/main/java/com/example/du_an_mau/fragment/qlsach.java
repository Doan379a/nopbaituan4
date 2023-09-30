package com.example.du_an_mau.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.Dao.sachdao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.adapter.sachadapter;
import com.example.du_an_mau.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class qlsach extends Fragment {
    RecyclerView rcv;
    FloatingActionButton fltadd;
    sachdao dao;
    sachadapter adapter;
    private ArrayList<Sach> list = new ArrayList<>();
    Spinner spinner;

    public qlsach() {
        // Required empty public constructor
    }
private List<String> data;
    private void chon() {
        // Tạo danh sách dữ liệu cho Spinner
        data = new ArrayList<>();
        data.add("CNTT");
        data.add("VHXH");
        data.add("TA");
        data.add("LS");
        // Tạo Adapter và gán dữ liệu cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_qlsach, container, false);
        // Inflate the layout for this fragment
        rcv = view.findViewById(R.id.rcvsach);
        fltadd = view.findViewById(R.id.fltadd);
        dao = new sachdao(this.getActivity());
        list = dao.taoDuLieu();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(layoutManager);
        adapter = new sachadapter(getActivity(), list);
        rcv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        fltadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getLayoutInflater();
                View view = inflater.inflate(R.layout.item_adds, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                EditText edmasach = view.findViewById(R.id.edmas);
                EditText edtensach = view.findViewById(R.id.edtens);
                EditText edgiathue = view.findViewById(R.id.edgiathues);
                EditText edloaisach = view.findViewById(R.id.edloaisach);
                Button btnadds = view.findViewById(R.id.btnluuadds);
                Button btnhuy = view.findViewById(R.id.btnhuyadds);
                // Thêm biến để lưu trữ loại sách đã chọn
               spinner=view.findViewById(R.id.edloaisach);
chon();
                btnadds.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String masach = edmasach.getText().toString();
                        String tensach = edtensach.getText().toString();
                        String giathue = edgiathue.getText().toString();
                        String selectedItem = spinner.getSelectedItem().toString();
                        Sach s = new Sach();

                        if (masach.isEmpty() || tensach.isEmpty() || giathue.isEmpty() || selectedItem.isEmpty()) {
                            Toast.makeText(getActivity(), "không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            int giathuev = Integer.parseInt(giathue);
                            if (giathuev > 0) {
                            s.setTenSach(tensach);
                            s.setGiaThue(giathuev);
//                           s.setMaLoai(selectedItem);
                                boolean check = dao.add(s);
                                if (check) {
                                    list.clear();
                                    list.addAll(dao.taoDuLieu());
                                    adapter.notifyDataSetChanged();
                                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Giá thuê phải lớn hơn 0", Toast.LENGTH_SHORT).show();
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
        });

        return view;
    }
}