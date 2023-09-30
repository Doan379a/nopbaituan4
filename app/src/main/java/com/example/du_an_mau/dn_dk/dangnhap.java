package com.example.du_an_mau.dn_dk;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_mau.Dao.daomk;
import com.example.du_an_mau.R;
import com.example.du_an_mau.trangchu;

public class dangnhap extends AppCompatActivity {
    EditText edtk, edmk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        edtk = findViewById(R.id.edinhap);
        edmk = findViewById(R.id.edipass);
        Button btndn = findViewById(R.id.btndn);
        ImageView imgmat = findViewById(R.id.mat);
        Button btnhuy=findViewById(R.id.btnhuy);
        final boolean[] isPasswordVisible = {false};
        imgmat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible[0]) {
                    edmk.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imgmat.setImageResource(R.drawable.matdong);
                    isPasswordVisible[0] = false;
                } else {
                    edmk.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imgmat.setImageResource(R.drawable.mat_eye_24);
                    isPasswordVisible[0] = true;
                }
            }
        });
        daomk daomk = new daomk(this);
        btndn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String use = edtk.getText().toString();
                String pass = edmk.getText().toString();
                boolean check = daomk.checkLogin(use, pass);
                if (use.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(dangnhap.this, "Điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else if (check) {
                    Toast.makeText(dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(dangnhap.this, trangchu.class));
                } else {
                    Toast.makeText(dangnhap.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              edtk.setText("");
              edmk.setText("");
            }
        });
//chkmk.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//
//    }
//});
    }
}
