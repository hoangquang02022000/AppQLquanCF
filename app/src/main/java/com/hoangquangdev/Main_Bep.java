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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Adapter.Ban_Thu_Ngan_Adapter;
import com.hoangquangdev.Adapter.Mon_order_adapter;
import com.hoangquangdev.Model.Ban;
import com.hoangquangdev.Model.Detail_Buil;
import com.hoangquangdev.Model.Mon_order;
import com.hoangquangdev.Model.User;

import java.util.ArrayList;
import java.util.Date;

public class Main_Bep extends Activity {

    private final String thongtinlhu = "tk_mk keySho login";
    GridView gr_1,gr_2,gr_3,gr_4;
    ListView lv_gioHang_bep;
    TextView txt_tenKV;
    ArrayList<Ban> ds1,ds2,ds3,ds4;
    ArrayList<Detail_Buil> hd1 = new ArrayList<>();
    ArrayList<Detail_Buil> hd2 = new ArrayList<>();
    ArrayList<Detail_Buil> hd3 = new ArrayList<>();
    ArrayList<Detail_Buil> hd4 = new ArrayList<>();
    Ban_Thu_Ngan_Adapter adapter1 ,adapter2,adapter3,adapter4;
    Mon_order_adapter adhd1,adhd2,adhd3,adhd4;
    ImageButton ibtn_tn_thoat,ibtn_bep_thoat_gioHang;
    Button btn_done;
    String mBAN,userShop;
    long millis=System.currentTimeMillis();
     final java.sql.Date date = new java.sql.Date(millis);
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bep);
        addcontroll();
        getdata();
//        LoadData();
        addevent();




    }

    private void addcontroll() {
        gr_1 = findViewById(R.id.gr_A1);
        gr_2 = findViewById(R.id.gr_A2);
        gr_3 = findViewById(R.id.gr_B1);
        gr_4 = findViewById(R.id.gr_B2);
        ibtn_tn_thoat = findViewById(R.id.ibtn_bep_thoat);

        SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu,MODE_PRIVATE);
        userShop =sharedPreferences.getString("userShop","");
        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

    }

    private void addevent() {
        ibtn_tn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main_Bep.this,MainQLKV.class);
                startActivity(intent);
            }
        });


        gr_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mData.child("UserShop").child(userShop).child("HoaDon").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Detail_Buil ts = snapshot.getValue(Detail_Buil.class);
                    hd1.add(ts);
                    mBAN = ds1.get(position).getMaBan();
                        if (ts.getIdBan().equalsIgnoreCase(mBAN)){
                            adhd1 =new Mon_order_adapter(Main_Bep.this, R.layout.item_giohang_bep,ts.getmList_mon_order());
                            lv_gioHang_bep.setAdapter(adhd1);
                            adhd1.notifyDataSetChanged();
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

            open_dialog_giaHang(Gravity.BOTTOM);

        }
    });
        gr_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mData.child("UserShop").child(userShop).child("HoaDon").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Detail_Buil ts = snapshot.getValue(Detail_Buil.class);
                        hd2.add(ts);
                        mBAN = ds2.get(position).getMaBan();

                            if (ts.getIdBan().equalsIgnoreCase(mBAN)){
                                adhd2 =new Mon_order_adapter(Main_Bep.this, R.layout.item_giohang_bep,ts.getmList_mon_order());
                                lv_gioHang_bep.setAdapter(adhd2);
                                adhd2.notifyDataSetChanged();
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

                open_dialog_giaHang(Gravity.BOTTOM);

            }
        });
        gr_3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mData.child("UserShop").child(userShop).child("HoaDon").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Detail_Buil ts = snapshot.getValue(Detail_Buil.class);
                        hd3.add(ts);
                        mBAN = ds3.get(position).getMaBan();

                            if (ts.getIdBan().equalsIgnoreCase(mBAN)){
                                adhd3 =new Mon_order_adapter(Main_Bep.this, R.layout.item_giohang_bep,ts.getmList_mon_order());
                                lv_gioHang_bep.setAdapter(adhd3);
                                adhd3.notifyDataSetChanged();
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

                open_dialog_giaHang(Gravity.BOTTOM);

            }
        });
        gr_4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mData.child("UserShop").child(userShop).child("HoaDon").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Detail_Buil ts = snapshot.getValue(Detail_Buil.class);
                        hd4.add(ts);
                        mBAN = ds4.get(position).getMaBan();

                            if (ts.getIdBan().equalsIgnoreCase(mBAN)){
                                adhd4 =new Mon_order_adapter(Main_Bep.this, R.layout.item_giohang_bep,ts.getmList_mon_order());
                                lv_gioHang_bep.setAdapter(adhd4);
                                adhd4.notifyDataSetChanged();
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

                open_dialog_giaHang(Gravity.BOTTOM);

            }
        });
    }

    private void getdata() {
        ds1 = new ArrayList<>();
        ds2 = new ArrayList<>();
        ds3 = new ArrayList<>();
        ds4 = new ArrayList<>();
        mData.child("UserShop").child(userShop).child("Ban").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Ban ban = snapshot.getValue(Ban.class);
                if (ban.getMaKV().equals("kva1")&&ban.getTrangThai()==1){
                    ds1.add(ban);

                }
                else if (ban.getMaKV().equals("kva2")&&ban.getTrangThai()==1){
                    ds2.add(ban);
                }
                else if (ban.getMaKV().equals("kvb1")&&ban.getTrangThai()==1){
                    ds3.add(ban);
                }
                else if (ban.getMaKV().equals("kvb2")&&ban.getTrangThai()==1){
                    ds4.add(ban);
                }
                adapter1 = new Ban_Thu_Ngan_Adapter(Main_Bep.this,R.layout.itemban, ds1);
                gr_1.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                adapter2 = new Ban_Thu_Ngan_Adapter(Main_Bep.this,R.layout.itemban, ds2);
                gr_2.setAdapter(adapter2);
                adapter2.notifyDataSetChanged();
                adapter3 = new Ban_Thu_Ngan_Adapter(Main_Bep.this,R.layout.itemban, ds3);
                gr_3.setAdapter(adapter3);
                adapter3.notifyDataSetChanged();
                adapter4 = new Ban_Thu_Ngan_Adapter(Main_Bep.this,R.layout.itemban, ds4);
                gr_4.setAdapter(adapter4);
                adapter4.notifyDataSetChanged();
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
    private void open_dialog_giaHang(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bep);

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
        ibtn_bep_thoat_gioHang = dialog.findViewById(R.id.ibtn_bep_thoat_gioHang);
        lv_gioHang_bep = dialog.findViewById(R.id.lv_gioHang_bep);
        btn_done = dialog.findViewById(R.id.btn_done);
        ibtn_bep_thoat_gioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.child("UserShop").child(userShop).child("Ban").child(mBAN).child("trangThai").setValue(0);
                finish();
                startActivity(getIntent());
                dialog.dismiss();


            }
        });
        dialog.show();
    }


}