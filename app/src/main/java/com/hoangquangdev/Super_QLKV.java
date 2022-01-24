package com.hoangquangdev;

import static com.hoangquangdev.R.drawable.aa1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Adapter.KV_Adapter;
import com.hoangquangdev.Model.KhuVuc;
import com.hoangquangdev.Model.NhanVien;

import java.util.ArrayList;

public class Super_QLKV  extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
        GridView gr_hinhKV;
        TextView txt_tenKV;

        ArrayList<KhuVuc> dsKhuVuc;
        KV_Adapter kv_adapter;

        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar1;


        Button btb_menu,btn_yes_;
        ImageButton btn_add_kv;
        int REQUEST_CODE_IMAGE = 1;
//    ImageView img ;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_super_qlkv);


        addcontroll();
        OpenMenuChinh();
        addevent();


        }

    private void OpenMenuChinh() {
//        setContentView(R.layout.activity_qlkv);
        drawerLayout = findViewById(R.id.lauoutdraw);
        toolbar1 = findViewById(R.id.toolBar);


//        setSupportActionBar(toolbar1);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar1, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        }


    private void addevent() {
    }
        private void addcontroll() {
        gr_hinhKV = findViewById(R.id.gr_hinhkhv);
        txt_tenKV = findViewById(R.id.txt_tenKV);

//        img = findViewById(R.id.img_KVss);
//        btb_menu = findViewById(R.id.btnMnu);

        dsKhuVuc = new ArrayList<>();
            dsKhuVuc.add(new KhuVuc("KV-A1", "A1", aa1));
            dsKhuVuc.add(new KhuVuc("KV-A2", "A2", R.drawable.aa2));
            dsKhuVuc.add(new KhuVuc("KV-B1", "B1", R.drawable.bb1));
            dsKhuVuc.add(new KhuVuc("KV-B2", "B2", R.drawable.bb2));

        kv_adapter = new KV_Adapter(Super_QLKV.this, R.layout.item_kv, dsKhuVuc);

        gr_hinhKV.setAdapter(kv_adapter);
        kv_adapter.notifyDataSetChanged();

        gr_hinhKV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent intent = new Intent(Super_QLKV.this, MainQLBan.class);
        Bundle bundle = new Bundle();
        bundle.putString("tenKV", dsKhuVuc.get(position).getTenKV());
        intent.putExtra("send", bundle);
        intent.putExtra("maKV", dsKhuVuc.get(position).getMaKV());
        startActivity(intent);

        }
        });

        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemQLKV) {
        Intent in = new Intent(Super_QLKV.this, Super_QLKV.class);
        startActivity(in);
        } else if (item.getItemId() == R.id.itemLogOut) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.itemQLNHV){
            open_dialog_(Gravity.CENTER);
        }else if (item.getItemId() == R.id.itemQLSP){
            open_dialog_(Gravity.CENTER);
        }else if (item.getItemId() == R.id.itemDanhThu){
            open_dialog_(Gravity.CENTER);
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
        }
    private void open_dialog_(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thongbao);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);

        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
            btn_yes_ = dialog.findViewById(R.id.btn_yes_);

            btn_yes_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
}
}