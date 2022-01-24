package com.hoangquangdev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Adapter.Ban_Adapter;
import com.hoangquangdev.Adapter.Ban_Thu_Ngan_Adapter;
import com.hoangquangdev.Model.Ban;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Ban_ThuNgan extends AppCompatActivity {

    GridView gr_ban_thuNgan;
    TextView txt_tenKV_Thu_Ngan;
    ImageButton btn_trove_ThuNgan;

    ArrayList<Ban>ds_Ban = new ArrayList<>();
    Ban_Thu_Ngan_Adapter adapter;


    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_thu_ngan);
        addcontroll();
        addevent();
        getdata();


        adapter = new Ban_Thu_Ngan_Adapter(Ban_ThuNgan.this, R.layout.itemban, ds_Ban);
        adapter.notifyDataSetChanged();
    }

    private void getdata() {
        mData.child("Ban").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Ban ban = snapshot.getValue(Ban.class);
                Intent intent = getIntent();
                String maKV = intent.getStringExtra("maKV");
                txt_tenKV_Thu_Ngan.setText(maKV);
                if (maKV.equals(ban.getMaKV())&&ban.getTrangThai()==1){
                    ds_Ban.add(ban);
                    Collections.sort(ds_Ban, new Comparator<Ban>() {
                        @Override
                        public int compare(Ban sv1, Ban sv2) {

                            if (sv1.getTenBan().charAt(sv1.getTenBan().length() - 2)
                                    < sv2.getTenBan().charAt(sv2.getTenBan().length() - 2)) {
                                return - 1;
                            } else {
                                if (sv1.getTenBan().charAt(sv1.getTenBan().length() - 2)
                                        == sv2.getTenBan().charAt(sv2.getTenBan().length() - 2)) {
                                    return 0;
                                } else {
                                    return 1;
                                }
                            }

                        }
                    });
                    gr_ban_thuNgan.setAdapter(adapter);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addevent() {
        btn_trove_ThuNgan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gr_ban_thuNgan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Ban_ThuNgan.this,MainThanhToan.class);
                Bundle bundle = new Bundle();

                //
                bundle.putString("maKV",ds_Ban.get(position).getMaKV());
                bundle.putString("tenBan",ds_Ban.get(position).getTenBan());
                bundle.putString("maBan",ds_Ban.get(position).getMaBan());
                intent.putExtra("send",bundle);
                startActivity(intent);
                System.out.println("----------"+ds_Ban.get(position).getMaBan());
            }
        });
        btn_trove_ThuNgan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               finish();
            }
        });
    }

    private void addcontroll() {
        gr_ban_thuNgan =  findViewById(R.id.gr_ban_thuNgan);
        txt_tenKV_Thu_Ngan = findViewById(R.id.txt_tenKV_Thu_Ngan);
        btn_trove_ThuNgan = findViewById(R.id.btn_trove_ThuNgan);
    }
}