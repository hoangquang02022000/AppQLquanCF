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

import java.util.List;

public class Dialog_nv_Adapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int resource;
    List<NhanVien>objects;
    public Dialog_nv_Adapter(@NonNull Activity context, int resource, @NonNull List<NhanVien> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {

        LayoutInflater inflater =  this.context.getLayoutInflater();
        row = inflater.inflate(this.resource,null);

        NhanVien nv = objects.get(position);
        ImageView img = row.findViewById(R.id.img_dialog_nv);
        TextView ten = row.findViewById(R.id.txt_tenNV);
        TextView ns = row.findViewById(R.id.txt_namSinh);

    return row;
    }
}
