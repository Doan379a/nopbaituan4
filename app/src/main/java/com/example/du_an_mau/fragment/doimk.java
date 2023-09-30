package com.example.du_an_mau.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.du_an_mau.Dao.daomk;
import com.example.du_an_mau.R;


public class doimk extends Fragment {

    daomk dao;

    public doimk() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View view=inflater.inflate(R.layout.fragment_doimk, container, false);
        // Inflate the layout for this fragment
        EditText edipass2 = view.findViewById(R.id.edipass2);
        EditText edipass3 = view.findViewById(R.id.edipass3);
        EditText edipass4 = view.findViewById(R.id.edipass4);
        ImageView imgmat2 = view.findViewById(R.id.mat2);
        ImageView imgmat3 = view.findViewById(R.id.mat3);
        ImageView imgmat4 = view.findViewById(R.id.mat4);
        Button btnLuu = view.findViewById(R.id.btndn);
        final boolean[] isPasswordVisible = {true};
        imgmat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    edipass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgmat2.setImageResource(R.drawable.matdong);
                    isPasswordVisible[0] = false;
                } else {
                    edipass2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imgmat2.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = true;
                }
            }
        });
        imgmat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    edipass3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgmat3.setImageResource(R.drawable.matdong);
                    isPasswordVisible[0] = false;
                } else {
                    edipass3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imgmat3.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = true;
                }
            }
        });
        imgmat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    edipass4.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgmat4.setImageResource(R.drawable.matdong);
                    isPasswordVisible[0] = false;
                } else {
                    edipass4.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imgmat4.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = true;
                }
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String matKhauCu = edipass2.getText().toString();
                String matKhauMoi = edipass3.getText().toString();
                String matKhauMoiRepeat = edipass4.getText().toString();
                if (TextUtils.isEmpty(matKhauCu) || TextUtils.isEmpty(matKhauMoi) || TextUtils.isEmpty(matKhauMoiRepeat)) {
                    Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!matKhauMoi.equals(matKhauMoiRepeat)) {
                    edipass4.setError("Mật khẩu mới không khớp");
                    return;
                }
                daomk dao = new daomk(getActivity());
                if (dao.checkOldPassword(matKhauCu)) {
                    dao.updatePassword(matKhauCu, matKhauMoi);
                    Toast.makeText(getActivity(), "Mật khẩu đã được thay đổi thành công", Toast.LENGTH_SHORT).show();
                } else {
                    edipass2.setError("Mật khẩu cũ không chính xác");
                }
            }
        });
        return view;
    }
}