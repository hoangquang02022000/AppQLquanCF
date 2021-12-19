package com.hoangquangdev.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hoangquangdev.Model.Ban;
import com.hoangquangdev.Model.NhanVien;
import com.hoangquangdev.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NhanVien_Adapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int resuorce;
    List<NhanVien> objects;
    public NhanVien_Adapter(@NonNull Activity context, int resource, @NonNull List<NhanVien> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resuorce = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        row = inflater.inflate(this.resuorce, null);

        NhanVien nv = this.objects.get(position);
        ImageView img = row.findViewById(R.id.img_nv);
        TextView tenNv =row.findViewById(R.id.txt_tenNV);
        TextView chuvu = row.findViewById(R.id.txt_chuvu);

        Picasso.get().load(nv.getImg()).into(img);
        tenNv.setText(nv.getTenNV());
        if (nv.getChucvu()==0){
            chuvu.setText("Chủ");
        }
        else if (nv.getChucvu()==1){
            chuvu.setText("Quản Lý");
        }
        else if (nv.getChucvu()==2){
            chuvu.setText("Nhân Viên");
        }
        return row;
    }
}
