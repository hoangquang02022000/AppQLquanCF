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

import com.hoangquangdev.Model.NhanVien;
import com.hoangquangdev.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Nhanvien_Adapter extends ArrayAdapter<NhanVien> {

    Activity context;
    int resource;
    List<NhanVien> objects;

    public Nhanvien_Adapter(@NonNull Activity context, int resource, @NonNull List<NhanVien> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        row = inflater.inflate(this.resource,null);

        NhanVien nhanVien = this.objects.get(position);

        ImageView img_nv = row.findViewById(R.id.img_nv_);
        TextView txt_ten = row.findViewById(R.id.txt_tenNV_);
        TextView txt_cv = row.findViewById(R.id.txt_chuvu);

        Picasso.get().load(nhanVien.getImg()).into(img_nv);
        txt_ten.setText(nhanVien.getTenNV());
        txt_cv.setText(nhanVien.getChucvu());
       return row;



    }
}
