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
import com.hoangquangdev.R;

import java.util.ArrayList;
import java.util.List;

public class Ban_Adapter extends ArrayAdapter<Ban> {
    Activity context;
    int resource;

    List<Ban> objects;

    public Ban_Adapter(@NonNull Activity context, int resource, List<Ban> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        row = inflater.inflate(this.resource, null);

        Ban ban = this.objects.get(position);
        ImageView img_Ban = row.findViewById(R.id.img_Ban);
        TextView txt_TenBan = row.findViewById(R.id.txt_tenBan);
        txt_TenBan.setText(ban.getTenBan());
//        img_Ban.setImageResource(ban.getImgBan());
        //checkTrangThai
        if (ban.getTrangThai() == 0) {
            img_Ban.setImageResource(R.drawable.bantrong);
        } else if (ban.getTrangThai() == 1) {
            img_Ban.setImageResource(R.drawable.bancokhach);
        } else if (ban.getTrangThai() == 2) {
            img_Ban.setImageResource(R.drawable.bandangdon);
        }
        return row;
    }
}