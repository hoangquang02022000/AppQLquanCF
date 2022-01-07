package com.hoangquangdev;

import static com.hoangquangdev.R.drawable.aa1;

import android.app.Activity;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Adapter.KV_Adapter;
import com.hoangquangdev.Model.KhuVuc;
import com.hoangquangdev.Model.NhanVien;

import java.util.ArrayList;

public class MainQLKV extends Activity implements NavigationView.OnNavigationItemSelectedListener {
    GridView gr_hinhKV;
    TextView txt_tenKV;

    ArrayList<KhuVuc> dsKhuVuc;
    KV_Adapter kv_adapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar1;


    Button btb_menu;
    ImageButton btn_add_kv;
    int REQUEST_CODE_IMAGE = 1;
//    ImageView img ;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    ArrayList<NhanVien> nhanViens = new ArrayList<>();
    String user;
    String pss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qlkv);


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

        kv_adapter = new KV_Adapter(MainQLKV.this, R.layout.item_kv, dsKhuVuc);

        gr_hinhKV.setAdapter(kv_adapter);
        kv_adapter.notifyDataSetChanged();

        gr_hinhKV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final String maKV = dsKhuVuc.get(position).getMaKV();

                Intent intent = new Intent(MainQLKV.this, MainQLBan.class);
//                int maKV = dsKhuVuc.get(position).getMaKV();
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
            Intent in = new Intent(MainQLKV.this, MainQLKV.class);
            startActivity(in);
        } else if (item.getItemId() == R.id.itemQLSP) {
            Intent intent = new Intent(MainQLKV.this, MainQLSP.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.itemQLNHV) {
            Intent intent = new Intent(MainQLKV.this, MainQLNhanVien.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.itemLogOut) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }else if (item.getItemId()==R.id.itemthungan){
            Intent intent = new Intent(this,Main_thuNgan.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}

