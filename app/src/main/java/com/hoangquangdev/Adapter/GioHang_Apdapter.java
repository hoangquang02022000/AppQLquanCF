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

import com.hoangquangdev.Model.Hoadon;
import com.hoangquangdev.Model.Mon_order;
import com.hoangquangdev.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class GioHang_Apdapter extends ArrayAdapter<Mon_order> {
    Activity context;
    int resource;
    List<Mon_order> objects;
    DecimalFormat f = new DecimalFormat("###,###,###");
    public GioHang_Apdapter(@NonNull Activity context, int resource, @NonNull List<Mon_order> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects= objects;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        row = inflater.inflate(this.resource,null);

        Mon_order mon_order =this.objects.get(position);
        ImageView img = row.findViewById(R.id.img_giaHang);
        TextView ten = row.findViewById(R.id.txt_tenSP_gioHang);
        TextView size = row.findViewById(R.id.txt_size_giaHang);
        TextView sl =row.findViewById(R.id.txt_dl_gioHang);
        TextView gia = row.findViewById(R.id.giaTien_spGioHang);

        Picasso.get().load(mon_order.getImg()).into(img);
        ten.setText(mon_order.getTen_sp());
        size.setText(mon_order.getSize());
        sl.setText(String.valueOf(mon_order.getSluong()));
        gia.setText(f.format(mon_order.getGia_sp())+" VNĐ");

        return row;

    }
}
