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
import com.hoangquangdev.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Hoadon_Adaper extends ArrayAdapter<Hoadon> {
    Activity context;
    int resource;
    List<Hoadon> objects;
    DecimalFormat f = new DecimalFormat("###,###,###");

    public Hoadon_Adaper(@NonNull Activity context, int resource,  @NonNull List<Hoadon> objects) {
        super(context, resource, objects);
        this.context=context;
        this.objects=objects;
        this.resource=resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        row = inflater.inflate(this.resource,null);

        Hoadon hoadon =this.objects.get(position);
        ImageView img = row.findViewById(R.id.imgsp_thanhtoan);
        TextView ten = row.findViewById(R.id.txt_tenSP_thanhtoan);
        TextView size = row.findViewById(R.id.txt_size_thanhtoan);
        TextView sl =row.findViewById(R.id.txt_sl_thanhtoan);
        TextView gia = row.findViewById(R.id.txt_gia_thanhtoan);

        Picasso.get().load(hoadon.getImg()).into(img);
        ten.setText(hoadon.getTenSampam());
        size.setText(hoadon.getSize());
        sl.setText(String.valueOf(hoadon.getSoLuong()));
        gia.setText(f.format(hoadon.getGiaSanpham())+" VNĐ");

        return row;

    }
}
