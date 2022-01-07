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

public class SanPham_Adapter extends ArrayAdapter<SanPham> {

    DecimalFormat f = new DecimalFormat("###,###,###");

    Activity context;
    int resource;
    List<SanPham>object;
    public SanPham_Adapter(@NonNull Activity context, int resource, @NonNull List<SanPham> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.object = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.context.getLayoutInflater();
        row =  inflater.inflate(this.resource,null);

        SanPham sanPham =this.object.get(position);
        //order Acivity order
        TextView txt_tenSP = row.findViewById(R.id.txt_tenSP);
        TextView txt_giaSP = row.findViewById(R.id.txt_giaSP);
        ImageView img_SP = row.findViewById(R.id.img_SP);

        txt_tenSP.setText(sanPham.getTenSP());
        txt_giaSP.setText((f.format(sanPham.getGiaSp()))+" VNĐ");
        Picasso.get().load(sanPham.getImgSP()).into(img_SP);
//        img_SP.setImageResource(sanPham.getImgSP());



        return row;
    }
}
