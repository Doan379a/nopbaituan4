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

import com.example.du_an_mau.Dao.sachdao;
import com.example.du_an_mau.R;
import com.example.du_an_mau.model.Sach;

import java.util.ArrayList;

public class sachadapter extends RecyclerView.Adapter<sachadapter.viewhoder>{

private final Context context;
private final ArrayList<Sach>list;
sachdao dao;

    public sachadapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        dao=new sachdao(context);
    }

    @NonNull
    @Override
    public viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_sach,null);

        return new viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewhoder holder, int position) {
holder.txtmasach.setText("Mã sách:"+String.valueOf(list.get(position).getMaSach()));
        holder.txttensach.setText("Tên sách:"+list.get(position).getTenSach());
        holder.txtgiathue.setText("Giá thuê:"+String.valueOf(list.get(position).getGiaThue()));
        holder.txtloaisach.setText("Loại Sách:"+String.valueOf(list.get(position).getMaLoai()));
Sach s=list.get(position);
holder.btnxoas.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.note);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa Sach " + holder.txttensach.getText()+" không");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dao.delete(s)) {
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

    public static class viewhoder extends RecyclerView.ViewHolder{
        TextView txtmasach,txttensach,txtgiathue,txtloaisach;
        ImageButton btnxoas;
        public viewhoder(@NonNull View itemView) {
            super(itemView);
            txtmasach=itemView.findViewById(R.id.txtmas);
            txttensach=itemView.findViewById(R.id.txttens);
            txtgiathue=itemView.findViewById(R.id.txtgiathues);
            txtloaisach=itemView.findViewById(R.id.txtloaisach);
            btnxoas=itemView.findViewById(R.id.btnxoas);
        }
    }
}
