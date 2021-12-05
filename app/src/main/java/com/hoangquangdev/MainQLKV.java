package com.hoangquangdev;

import static com.hoangquangdev.R.drawable.aa1;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.app.Activity;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.hoangquangdev.Adapter.KV_Adapter;
import com.hoangquangdev.Model.KhuVuc;

import java.util.ArrayList;

public class MainQLKV extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    GridView gr_hinhKV;
    TextView txt_tenKV;

    ArrayList<KhuVuc> dsKhuVuc;
    KV_Adapter kv_adapter ;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar1;


    Button btb_menu ;
    ImageButton btn_add_kv;
    int REQUEST_CODE_IMAGE = 1;
//    ImageView img ;

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
        drawerLayout=findViewById(R.id.lauoutdraw);
        toolbar1=findViewById(R.id.toolBar);


        setSupportActionBar(toolbar1);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar1,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener( this);
    }

    private void addevent() {
        btn_add_kv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendilogaddkv(Gravity.CENTER);
            }

        });


    }

    private void addcontroll() {
        gr_hinhKV = findViewById(R.id.gr_hinhkhv);
        txt_tenKV = findViewById(R.id.txt_tenKV);
        btn_add_kv = findViewById(R.id.btn_addkv);

//        img = findViewById(R.id.img_KVss);
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

               final int maKV = dsKhuVuc.get(position).getMaKV();

                Intent intent = new Intent(MainQLKV.this,MainQLBan.class);
//                int maKV = dsKhuVuc.get(position).getMaKV();
                Bundle bundle = new Bundle();
                bundle.putString("tenKV",dsKhuVuc.get(position).getTenKV());
                intent.putExtra("send",bundle);


                intent.putExtra("maKV", dsKhuVuc.get(position).getMaKV());
                startActivity(intent);

            }
        });

    }


    private void opendilogaddkv(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_kv);

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

        }

        ImageView img = dialog.findViewById(R.id.img_KVss);
        TextView txt = dialog.findViewById(R.id.txt_tenKV);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
//                tesst();
//                onActivityResult();
            }
        });

            dialog.show();
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            if (requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data !=null){
//                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                img.setImageBitmap(bitmap);
//            }
//            super.onActivityResult(requestCode, resultCode, data);
}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemQLKV) {
            Intent in =new Intent(MainQLKV.this,MainQLKV.class);
            startActivity(in);
        }else if (item.getItemId()==R.id.itemQLSP){

            Intent qlsp = new Intent(this,MainQLSP.class);
            startActivity(qlsp);
        }


        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}

