package com.hoangquangdev;

import static com.hoangquangdev.R.drawable.aa1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hoangquangdev.Adapter.KV_Adapter;
import com.hoangquangdev.Model.KhuVuc;

import java.util.ArrayList;

public class Main_thuNgan extends AppCompatActivity {
    GridView gr_hinhKV;
    TextView txt_tenKV;
    ArrayList<KhuVuc> dsKhuVuc;
    KV_Adapter kv_adapter;
    ImageButton ibtn_tn_thoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thu_ngan);
        ibtn_tn_thoat = findViewById(R.id.ibtn_tn_thoat);
        ibtn_tn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_thuNgan.this,MainQLKV.class);
                startActivity(intent);
            }
        });

        gr_hinhKV = findViewById(R.id.gr_hinhkhv_tn);
        txt_tenKV = findViewById(R.id.txt_tenKV);

        dsKhuVuc = new ArrayList<>();
        dsKhuVuc.add(new KhuVuc("KV-A1", "A1", aa1));
        dsKhuVuc.add(new KhuVuc("KV-A2", "A2", R.drawable.aa2));
        dsKhuVuc.add(new KhuVuc("KV-B1", "B1", R.drawable.bb1));
        dsKhuVuc.add(new KhuVuc("KV-B2", "B2", R.drawable.bb2));

        kv_adapter = new KV_Adapter(Main_thuNgan.this, R.layout.item_kv, dsKhuVuc);

        gr_hinhKV.setAdapter(kv_adapter);
        kv_adapter.notifyDataSetChanged();

        gr_hinhKV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Main_thuNgan.this,Ban_ThuNgan.class);
                intent.putExtra("maKV", dsKhuVuc.get(position).getMaKV());
                startActivity(intent);
            }
        });
    }
}