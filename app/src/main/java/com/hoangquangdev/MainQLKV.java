package com.hoangquangdev;

import static com.hoangquangdev.R.drawable.aa1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.hoangquangdev.Adapter.KV_Adapter;
import com.hoangquangdev.Model.KhuVuc;

import java.util.ArrayList;

public class MainQLKV extends Activity {
    GridView gr_hinhKV;
    TextView txt_tenKV;
    ArrayList<KhuVuc> dsKhuVuc;
    KV_Adapter kv_adapter ;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button btb_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qlkv);
        addcontroll();
        OnpenMenu();


    }

    private void OnpenMenu() {

//            btn_menu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    drawerLayout.openDrawer(GravityCompat.START);
//                }
//            });

    }

    private void addcontroll() {
        gr_hinhKV = findViewById(R.id.gr_hinhkhv);
        txt_tenKV = findViewById(R.id.txt_tenKV);
//        btb_menu = findViewById(R.id.btnMnu);

        dsKhuVuc = new ArrayList<>();
        dsKhuVuc.add(new KhuVuc(1,"A1", aa1));
        dsKhuVuc.add(new KhuVuc(2,"A2", R.drawable.aa2));
        dsKhuVuc.add(new KhuVuc(3,"B1", R.drawable.bb1));
        dsKhuVuc.add(new KhuVuc(4,"B2", R.drawable.bb2));

        kv_adapter = new KV_Adapter(MainQLKV.this, R.layout.item_kv, dsKhuVuc);

        gr_hinhKV.setAdapter(kv_adapter);
        kv_adapter.notifyDataSetChanged();

        gr_hinhKV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainQLKV.this,MainQLBan.class);
                Bundle bundle = new Bundle();
                bundle.putString("tenKV",dsKhuVuc.get(position).getTenKV());
                intent.putExtra("send",bundle);
                startActivity(intent);

            }
        });

    }


}