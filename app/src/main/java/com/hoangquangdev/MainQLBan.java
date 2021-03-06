package com.hoangquangdev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MainQLBan extends Activity {
    GridView gr_hinhB;
    TextView txt_tenBan,txt_tenKV,txt_thongbao;
    ImageButton imgbtn_addBan,btn_Back;
    Button btn_yes,btn_no;
    ArrayList<Ban> dsBan = new ArrayList<>();
    Ban_Adapter ban_adapter;
    String thongtinlhu = "tk_mk keySho login";

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
        txt_tenKV.setText("KV - "+bundle.getString("tenKV"));




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
                bundle.putString("maKV",dsBan.get(position).getMaKV());
                bundle.putString("tenBan",dsBan.get(position).getTenBan());
                bundle.putString("maBan",dsBan.get(position).getMaBan());
                intent.putExtra("send",bundle);
                startActivity(intent);
//                System.out.println(dsBan.get(position).getMaBan()+dsBan.get(position).getTenBan()+dsBan.get(position).getMaKV());

            }
        });
        gr_hinhB.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                opendialog_thongbao(Gravity.CENTER,dsBan.get(i).getTenBan(),dsBan.get(i).getMaBan());
                return true;
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
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu,MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");
        String pass = sharedPreferences.getString("password","");
        String userShop =sharedPreferences.getString("userShop","");
        mData.child("UserShop").child(userShop).child("Ban").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Ban ban = dataSnapshot.getValue(Ban.class);

//                id = ban.getMaBan();
                Intent intent = getIntent();

                String maKV = intent.getStringExtra("maKV");


                if (maKV.equals(ban.getMaKV())) {
                    dsBan.add(ban);
                        Collections.sort(dsBan, new Comparator<Ban>() {
                        @Override
                        public int compare(Ban sv1, Ban sv2) {

                            if (sv1.getTenBan().charAt(sv1.getTenBan().length() - 1)
                                    < sv2.getTenBan().charAt(sv2.getTenBan().length() - 1)) {
                                return - 1;
                            } else {
                                if (sv1.getTenBan().charAt(sv1.getTenBan().length() - 1)
                                        == sv2.getTenBan().charAt(sv2.getTenBan().length() - 1)) {
                                    return 0;
                                } else {
                                    return 1;
                                }
                            }

                        }
                    });
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
    private void opendilogadd(int gravity ){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_ban);

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
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu,MODE_PRIVATE);
        String userShop =sharedPreferences.getString("userShop","");
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String maKV = intent.getStringExtra("maKV");
                Ban ban = new Ban(maKV,maKV+"-"+etxt_tenBan.getText().toString(),etxt_tenBan.getText().toString(),0,0);
                mData.child("UserShop").child(userShop).child("Ban").child(maKV+"-"+etxt_tenBan.getText().toString()).setValue(ban);


                Toast.makeText(MainQLBan.this, "Them Ban Thanh Cong", Toast.LENGTH_SHORT).show();

            }
        });


        dialog.show();
    }
    private void opendialog_thongbao(int gravity , String nd, String id_del) {
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

        }
        btn_yes = dialog.findViewById(R.id.btn_yes);
        btn_no = dialog.findViewById(R.id.btn_no);
        txt_thongbao = dialog .findViewById(R.id.txt_thongbao);
        txt_thongbao.setText("B???n C?? Mu???n X??a "+nd);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           del(id_del);
                                           dialog.dismiss();
                                       }
                                   }
        );
        dialog.show();
    }
    public void del(String id_del){
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu,MODE_PRIVATE);
        String userShop =sharedPreferences.getString("userShop","");
        mData.child("UserShop").child(userShop).child("Ban").orderByChild("maBan").equalTo(id_del)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            ds.getRef().removeValue();

                            Toast.makeText(MainQLBan.this, "X??a th??nh c??ng", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(MainQLBan.this, "X??a th???t b???i", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}