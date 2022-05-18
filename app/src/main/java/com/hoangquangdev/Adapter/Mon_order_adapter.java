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

public class Mon_order_adapter extends ArrayAdapter<Mon_order> {
    Activity context;
    int resource;
    List<Mon_order> objects;
    DecimalFormat f = new DecimalFormat("###,###,###");

    public Mon_order_adapter(@NonNull Activity context, int resource, @NonNull List<Mon_order> objects) {
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

        Mon_order mon_order =this.objects.get(position);
        ImageView img = row.findViewById(R.id.imgsp_thanhtoan);
        TextView ten = row.findViewById(R.id.txt_tenSP_thanhtoan);
        TextView size = row.findViewById(R.id.txt_size_thanhtoan);
        TextView sl =row.findViewById(R.id.txt_sl_thanhtoan);
        TextView gia = row.findViewById(R.id.txt_gia_thanhtoan);
        TextView top = row.findViewById(R.id.txt_bep_topping);

        Picasso.get().load(mon_order.getImg()).into(img);
        ten.setText(mon_order.getTen_sp());
        size.setText(mon_order.getSize());
        sl.setText(String.valueOf(mon_order.getSluong()));
        gia.setText(f.format(mon_order.getGia_sp())+" VNĐ");

        top.setText(mon_order.getTopping());

        return row;

    }
}
