package com.example.du_an_mau.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_mau.Dao.thanhviendao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.ThanhVien;

import java.util.ArrayList;

public class thanhvienadapter extends RecyclerView.Adapter<thanhvienadapter.viewhoder> {
    private final Context context;
    private final ArrayList<ThanhVien> list;
    thanhviendao dao;

    public thanhvienadapter(Context context, ArrayList<ThanhVien> list) {
        this.context = context;
        this.list = list;
        dao = new thanhviendao(context);
    }
    @NonNull
    @Override
    public viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_tv, null);
        return new viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhoder holder, int position) {
        holder.txtmatv.setText("Mã thành viên: "+String.valueOf(list.get(position).getMaTV()));
        holder.txttentv.setText("Tên thành viên:"+list.get(position).getHoTen());
        holder.txtnamsinhtv.setText("Năm sinh :"+list.get(position).getNamSinh());
        ThanhVien tv=list.get(position);
        holder.btnxoatv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.note);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa thành viên" + holder.txttentv.getText()+" không");
//
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dao.delete(tv)) {
                            list.clear();
                            list.addAll(dao.taoDuLieu());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Không xóa", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewhoder extends RecyclerView.ViewHolder {
        TextView txtmatv, txttentv, txtnamsinhtv;
        ImageButton btnxoatv;

        public viewhoder(@NonNull View itemView) {
            super(itemView);
            txtmatv = itemView.findViewById(R.id.txtmatv);
            txttentv = itemView.findViewById(R.id.txttentv);
            txtnamsinhtv = itemView.findViewById(R.id.txtnamsinhtv);
            btnxoatv = itemView.findViewById(R.id.btnxoatv);
        }
    }
}
