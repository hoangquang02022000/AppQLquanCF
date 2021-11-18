package com.hoangquangdev.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.hoangquangdev.MainQLBan;
import com.hoangquangdev.MainQLKV;
import com.hoangquangdev.Model.KhuVuc;
import com.hoangquangdev.R;

import java.util.List;

public class KV_Adapter extends ArrayAdapter<KhuVuc> {
    Activity context;
    int resuorce;
    List<KhuVuc> objects;
    public KV_Adapter(@NonNull Activity context, int resource, @NonNull List<KhuVuc> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resuorce=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        row = inflater.inflate(this.resuorce,null);
        ImageView img_kv = row.findViewById(R.id.img_KV);
        TextView txt_tenKV = row.findViewById(R.id.txt_tenKV);

        KhuVuc khuVuc = this.objects.get(position);

        img_kv.setImageResource(khuVuc.getImg());
        txt_tenKV.setText(khuVuc.getTenKV());
        return row;
    }
}
