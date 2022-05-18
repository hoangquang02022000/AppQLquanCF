package com.hoangquangdev;

import static com.hoangquangdev.R.drawable.aa1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Adapter.KV_Adapter;
import com.hoangquangdev.Model.KhuVuc;
import com.hoangquangdev.Model.NhanVien;

import java.util.ArrayList;

public class MainQLKV extends Activity implements NavigationView.OnNavigationItemSelectedListener {
    GridView gr_hinhKV;
    TextView txt_tenKV,txt_nameuser,txt_email_user,txt_nameShop;

    ArrayList<KhuVuc> dsKhuVuc;
    KV_Adapter kv_adapter;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar1;
    TextView txtname;


    Button btb_menu;
    ImageButton btn_add_kv;
    int REQUEST_CODE_IMAGE = 1;
//    ImageView img ;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth auth;
    ArrayList<NhanVien> nhanViens = new ArrayList<>();
    String user;
    String pss;
    String thongtinlhu = "tk_mk keySho login";
    String email,userShop,nameShop,nameUser;
    int q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qlkv);
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu,MODE_PRIVATE);
        email = sharedPreferences.getString("email","");
        userShop =sharedPreferences.getString("userShop","");
        nameShop = sharedPreferences.getString("nameShop","");
        nameUser = sharedPreferences.getString("nameUser","");
        q = sharedPreferences.getInt("quyen",0);



//        auth = FirebaseAuth.getInstance();

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
        View headerView = navigationView.getHeaderView(0);
        txt_nameuser = headerView.findViewById(R.id.txt_nameuser);
        txt_email_user = headerView.findViewById(R.id.txt_nameuser);
        txt_nameShop = headerView.findViewById(R.id.txt_nameShop);

        txt_nameuser.setText(nameUser);
        txt_email_user.setText(email);
        txt_nameShop.setText(nameShop);
//        MenuItem item = findViewById(R.id.quanly);
//        if (q==0){
//            item.setVisible(false);
//            this.invalidateOptionsMenu();
//        }


    }

    private void addevent() {


    }

    private void addcontroll() {
        gr_hinhKV = findViewById(R.id.gr_hinhkhv);
        txt_tenKV = findViewById(R.id.txt_tenKV);

        dsKhuVuc = new ArrayList<>();
        dsKhuVuc.add(new KhuVuc("kva1", "A1", aa1));
        dsKhuVuc.add(new KhuVuc("kva2", "A2", R.drawable.aa2));
        dsKhuVuc.add(new KhuVuc("kvb1", "B1", R.drawable.bb1));
        dsKhuVuc.add(new KhuVuc("kvb2", "B2", R.drawable.bb2));

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
            if (q == 0){
                Intent intent = new Intent(MainQLKV.this, MainQLSP.class);
                startActivity(intent);
            }else {
                Toast.makeText(MainQLKV.this, "Bạn không có quyền truy cập !", Toast.LENGTH_LONG).show();
            }

        } else if (item.getItemId() == R.id.itemQLNHV) {
            if (q == 0){
                Intent intent = new Intent(MainQLKV.this, MainQLNhanVien.class);
                startActivity(intent);
            }else {
                Toast.makeText(MainQLKV.this, "Bạn không có quyền truy cập !", Toast.LENGTH_LONG).show();
            }

        } else if (item.getItemId() == R.id.itemLogOut) {
            auth.getInstance().signOut();
            Intent intent = new Intent(MainQLKV.this, Main_Login.class);
            startActivity(intent);
            finishAffinity();
        }else if (item.getItemId()==R.id.itemDanhThu){
            if (q == 0){
                Intent intent = new Intent(this,Main_QL_DoanhThu.class);
                startActivity(intent);
            }else {
                Toast.makeText(MainQLKV.this, "Bạn không có quyền truy cập !", Toast.LENGTH_LONG).show();
            }

        }else if (item.getItemId()==R.id.itemBep){
            Intent intent = new Intent(this,Main_Bep.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}

