package com.hoangquangdev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hoangquangdev.Adapter.Ban_Adapter;
import com.hoangquangdev.Model.Ban;
import com.hoangquangdev.Model.KhuVuc;

import java.util.ArrayList;

public class MainQLBan extends AppCompatActivity {
    GridView gr_hinhB;
    TextView txt_tenBan,txt_tenKV;
    ImageButton imgbtn_addBan;
    Button btn_Back;

    ArrayList<Ban> dsBan = new ArrayList<>();
    Ban_Adapter ban_adapter;

    int id ;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qlban);

        addctroll();
        addevent();
        showBan();


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("send");
        txt_tenKV.setText(bundle.getString("tenKV"));



        ban_adapter = new Ban_Adapter(MainQLBan.this, R.layout.itemban, dsBan);
        ban_adapter.notifyDataSetChanged();
    }

    private void addevent() {
        imgbtn_addBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendilogadd(Gravity.CENTER);

            }
        });

        gr_hinhB.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainQLBan.this,MainBan.class);
                Bundle bundle = new Bundle();

                //
                bundle.putInt("maKV",dsBan.get(position).getMaKV());
                bundle.putString("tenBan",dsBan.get(position).getTenBan());
                bundle.putInt("maBan",dsBan.get(position).getMaBan());
                intent.putExtra("send",bundle);
                startActivity(intent);
                System.out.println(dsBan.get(position).getMaBan()+dsBan.get(position).getTenBan()+dsBan.get(position).getMaKV());

            }
        });
        btn_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addctroll() {
        gr_hinhB = findViewById(R.id.gr_hinhBan);
        txt_tenBan = findViewById(R.id.txt_tenBan);
        txt_tenKV = findViewById(R.id.txt_tenKV_QLB);
        imgbtn_addBan = findViewById(R.id.imgbtn_addBan);
        btn_Back = findViewById(R.id.btn_troveKV);






    }
    public void showBan(){
        mData.child("Ban").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Ban ban = dataSnapshot.getValue(Ban.class);
                id = ban.getMaBan();
                Intent intent = getIntent();
                int maKV = intent.getIntExtra("maKV", 100);
                if (maKV == ban.getMaKV()) {
                    dsBan.add(ban);
                    gr_hinhB.setAdapter(ban_adapter);
                    ban_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void opendilogadd(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_ban);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);

        if (Gravity.CENTER == gravity){
            dialog.setCancelable(true);
        }
            else{
                dialog.setCancelable(false);
            }
        EditText etxt_tenBan = dialog.findViewById(R.id.etxt_tenBan);
        Button btn_add = dialog.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=id+1;
                Intent intent = getIntent();
                int maKV = intent.getIntExtra("maKV", 100);
                Ban ban = new Ban(maKV,id,etxt_tenBan.getText().toString(),0);
                mData.child("Ban").push().setValue(ban);


                Toast.makeText(MainQLBan.this, "Them Ban Thanh Cong", Toast.LENGTH_SHORT).show();

            }
        });


        dialog.show();
    }
}