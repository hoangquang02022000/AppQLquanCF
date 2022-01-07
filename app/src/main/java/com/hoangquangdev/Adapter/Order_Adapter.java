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

import com.hoangquangdev.Model.SanPham;
import com.hoangquangdev.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class Order_Adapter extends ArrayAdapter<SanPham> {
    DecimalFormat f = new DecimalFormat("###,###,###");
    Activity context;
    int resource;
    List<SanPham> objects;
    public Order_Adapter(@NonNull Activity context, int resource, List<SanPham> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        row = inflater.inflate(this.resource,null);

        SanPham sp = this.objects.get(position);

        TextView txt_tenSp =row.findViewById(R.id.txt_order_tenSP);
        TextView txt_giaSp =row.findViewById(R.id.txt_order_giaSp);
        ImageView img_Sp = row.findViewById(R.id.img_oder_SP);

        txt_giaSp.setText(f.format(sp.getMaSP()));
        txt_tenSp.setText(sp.getTenSP());

        Picasso.get().load(sp.getImgSP()).into(img_Sp);

        return  row;
    }
}
