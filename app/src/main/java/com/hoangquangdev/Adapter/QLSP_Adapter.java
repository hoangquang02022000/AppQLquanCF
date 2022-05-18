package com.hoangquangdev.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.hoangquangdev.MainQLSP;
import com.hoangquangdev.Model.SanPham;
import com.hoangquangdev.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class QLSP_Adapter extends ArrayAdapter<SanPham> {
    DecimalFormat f = new DecimalFormat("###,###,###");


    Activity context;
    int resource;
    List<SanPham> object;
    public QLSP_Adapter(@NonNull Activity context, int resource, @NonNull List<SanPham> objects) {
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

        //QLSP
        ImageView img_QLSP_imgSP = row.findViewById(R.id.img_QLSP_imgSP);
        TextView txt_QLSP_tenSP = row.findViewById(R.id.txt_QLSP_tenSP);
//        TextView  txt_QLSP_loaiSP = row.findViewById(R.id.txt_QLSP_loaiSP);
        TextView txt_QLSP_giaSP = row.findViewById(R.id.txt_QLSP_giaSP);

        Picasso.get().load(sanPham.getImgSP()).into(img_QLSP_imgSP);

        txt_QLSP_tenSP.setText(sanPham.getTenSP());
        txt_QLSP_giaSP.setText(f.format(sanPham.getGiaSp())+"VNĐ");
        return row;
    }
}
