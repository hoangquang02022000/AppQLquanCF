package com.hoangquangdev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hoangquangdev.Adapter.GioHang_Apdapter;
import com.hoangquangdev.Adapter.SanPham_Adapter;
import com.hoangquangdev.Model.Detail_Buil;
import com.hoangquangdev.Model.Hoadon;
import com.hoangquangdev.Model.Mon_order;
import com.hoangquangdev.Model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainBan extends Activity {
    double tongthanhtoan_ = 0 , tong_Buid = 0;
    String size = "M";
    String topping = "Không";
    int sl = 1;
    TabHost tabHost;
    GridView gr_all, gr_coffree,gr_tea,gr_drink ;
    ArrayList<SanPham> list_all , list_coffee , list_mikltea , list_drink ,list_order;
    SanPham_Adapter adapter ;
    Button btn_order_them,btn_order,btn_order_GioHang;
    ImageButton btn_tru ,btn_cong,btn_back,btn_BackQLB,ibtn_check,ibtn_order_thoat_gioHang;
    TextView txt_tong,txt_tenSp,txt_giaSp,txt_tongSP,txt_SL,txt_tongGiaTien,ten_Ban,txt_TongTien;
    ImageView img_sp;
    RadioGroup rb_Gr;
    CheckBox ck_order_topping;
    String idhd;
    String idKV;
    String idBan;
    Double TongTien=0.000,ttongttien=0.000;
    ListView lv_gioHang;
    GioHang_Apdapter apdapter_GioHang;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();


    ArrayList<Hoadon> ds_Hoadon = new ArrayList<>();
    Hoadon hd = new Hoadon();

    String thongtinlhu = "tk_mk keySho login";


    ///-----------
    DecimalFormat f = new DecimalFormat("###,###,###");

    ArrayList<Detail_Buil> list_Buill = new ArrayList<>();
    ArrayList<Mon_order> list_mon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban);

        showSP();
        addcontroll();
        addevent();


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("send");
        String tenBan = bundle.getString("tenBan");
        ten_Ban.setText(tenBan);

    }

    private void addevent() {
        list_order = new ArrayList<>();
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengioHang(Gravity.BOTTOM,tong_Buid);

                btn_order_GioHang.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        oder();
                        Toast.makeText(MainBan.this, "Order Thành Công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainBan.this,MainQLKV.class);
                        startActivity(intent);
                    }
                });
            }
        });
        btn_BackQLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        gr_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openOder(Gravity.BOTTOM);
                sl = 1;
                size = "M";
                topping = "Không";
                String ten = list_all.get(position).getTenSP();
                double gia_ = list_all.get(position).getGiaSp();

                final Double[] gia = {list_all.get(position).getGiaSp()};
                double tam = list_all.get(position).getGiaSp();
                String img = list_all.get(position).getImgSP();
                Picasso.get().load(img).into(img_sp);
                txt_tenSp.setText(ten);
                txt_giaSp.setText((f.format(gia_))+" VNĐ");
                txt_tongSP.setText((f.format(gia_))+" VNĐ");
                tongthanhtoan_=gia_;



                btn_cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl+=1;
                        txt_SL.setText(String.valueOf(sl));
                        tongthanhtoan_ += gia_ ;
                        txt_giaSp.setText(String.valueOf(f.format(list_all.get(position).getGiaSp()))+" VNĐ");
                        txt_tongSP.setText((f.format(tongthanhtoan_))+" VNĐ");

                        txt_tongGiaTien.setText(f.format(tongthanhtoan_)+" VNĐ");
//                        hoadons.add(new Hoadon(1,list_all.get(position).getMaSP(),list_all.get(position).getTenSP(),"m","có",sl[0],gia[0]));
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl -= 1;
                        txt_SL.setText(String.valueOf(sl));
                        tongthanhtoan_-=gia_;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
//                        txt_tongGiaTien.setText(f.format(tongthanhtoan_)+" VNĐ");
                    }
                });
                rb_Gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_order_sizeM:
                                size = "M" ;
                                tongthanhtoan_=tongthanhtoan_-15000.0;
                                txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                                break;
                            case R.id.rb_order_sizeL:
                                size="L";
                                tongthanhtoan_=tongthanhtoan_+15000.0;
                                txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                    }
                });

                ck_order_topping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ck_order_topping.isChecked())
                        {
                            topping = "Thạch đậu đen ";
                            tongthanhtoan_=tongthanhtoan_+10000.0;
                            txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                        else
                        {
                            topping = "Không";
                            tongthanhtoan_=tongthanhtoan_-10000.0;
                            txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                    }
                });

                //-------------------------------------------------------

                btn_order_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        Bundle bundle = intent.getBundleExtra("send");
                         idKV = bundle.getString("maKV");
                         idBan = bundle.getString("maBan");
                         Mon_order mon = new Mon_order(list_all.get(position).getMaSP(),list_all.get(position).getTenSP(),
                                 list_all.get(position).getImgSP(),sl,tongthanhtoan_,size,topping);
                        list_mon.add(mon);
                        tong_Buid += tongthanhtoan_;
                        txt_tongGiaTien.setText(f.format(tong_Buid)+" VNĐ");
                        System.out.println("-------------------------------------"+list_mon.toString());
                        Toast.makeText(MainBan.this, "Thêm Vào Giỏ Hàng Thành Công", Toast.LENGTH_SHORT).show();

                    }
                });
            }

        });

        gr_coffree.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openOder(Gravity.BOTTOM);
                sl = 1;
                size = "M";
                topping = "Không";
                String ten = list_coffee.get(position).getTenSP();
                double gia_ = list_coffee.get(position).getGiaSp();

                final Double[] gia = {list_coffee.get(position).getGiaSp()};
                String img = list_coffee.get(position).getImgSP();
                Picasso.get().load(img).into(img_sp);
                txt_tenSp.setText(ten);
                txt_giaSp.setText((f.format(gia_))+" VNĐ");
                txt_tongSP.setText((f.format(gia_))+" VNĐ");
                tongthanhtoan_=gia_;



                btn_cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl+=1;
                        txt_SL.setText(String.valueOf(sl));
                        tongthanhtoan_ += gia_ ;
                        txt_giaSp.setText(String.valueOf(f.format(list_all.get(position).getGiaSp()))+" VNĐ");
                        txt_tongSP.setText((f.format(tongthanhtoan_))+" VNĐ");

                        txt_tongGiaTien.setText(f.format(tongthanhtoan_)+" VNĐ");
//                        hoadons.add(new Hoadon(1,list_all.get(position).getMaSP(),list_all.get(position).getTenSP(),"m","có",sl[0],gia[0]));
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl -= 1;
                        txt_SL.setText(String.valueOf(sl));
                        tongthanhtoan_-=gia_;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
//                        txt_tongGiaTien.setText(f.format(tongthanhtoan_)+" VNĐ");
                    }
                });
                rb_Gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_order_sizeM:
                                size = "M" ;
                                tongthanhtoan_=tongthanhtoan_-15000.0;
                                txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                                break;
                            case R.id.rb_order_sizeL:
                                size="L";
                                tongthanhtoan_=tongthanhtoan_+15000.0;
                                txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                    }
                });

                ck_order_topping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ck_order_topping.isChecked())
                        {
                            topping = "Có";
                            tongthanhtoan_=tongthanhtoan_+10000.0;
                            txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                        else
                        {
                            topping = "Không";
                            tongthanhtoan_=tongthanhtoan_-10000.0;
                            txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                    }
                });

                //-------------------------------------------------------

                btn_order_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        Bundle bundle = intent.getBundleExtra("send");
                        idKV = bundle.getString("maKV");
                        idBan = bundle.getString("maBan");
                        Mon_order mon = new Mon_order(list_coffee.get(position).getMaSP(),list_coffee.get(position).getTenSP(),
                                list_coffee.get(position).getImgSP(),sl,tongthanhtoan_,size,topping);
                        list_mon.add(mon);
                        tong_Buid += tongthanhtoan_;
                        txt_tongGiaTien.setText(f.format(tong_Buid)+" VNĐ");
                        System.out.println("-------------------------------------"+list_mon.toString());
                        Toast.makeText(MainBan.this, "Thêm Vào Giỏ Hàng Thành Công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        gr_drink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openOder(Gravity.BOTTOM);
                sl = 1;
                size = "M";
                topping = "Không";
                String ten = list_drink.get(position).getTenSP();
                double gia_ = list_drink.get(position).getGiaSp();

                final Double[] gia = {list_drink.get(position).getGiaSp()};
                double tam = list_drink.get(position).getGiaSp();
                String img = list_drink.get(position).getImgSP();
                Picasso.get().load(img).into(img_sp);
                txt_tenSp.setText(ten);
                txt_giaSp.setText((f.format(gia_))+" VNĐ");
                txt_tongSP.setText((f.format(gia_))+" VNĐ");
                tongthanhtoan_=gia_;

                btn_cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl+=1;
                        txt_SL.setText(String.valueOf(sl));
                        tongthanhtoan_ += gia_ ;
                        txt_giaSp.setText(String.valueOf(f.format(list_drink.get(position).getGiaSp()))+" VNĐ");
                        txt_tongSP.setText((f.format(tongthanhtoan_))+" VNĐ");

                        txt_tongGiaTien.setText(f.format(tongthanhtoan_)+" VNĐ");
//                        hoadons.add(new Hoadon(1,list_all.get(position).getMaSP(),list_all.get(position).getTenSP(),"m","có",sl[0],gia[0]));
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl -= 1;
                        txt_SL.setText(String.valueOf(sl));
                        tongthanhtoan_-=gia_;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
//                        txt_tongGiaTien.setText(f.format(tongthanhtoan_)+" VNĐ");
                    }
                });
                rb_Gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_order_sizeM:
                                size = "M" ;
                                tongthanhtoan_=tongthanhtoan_-15000.0;
                                txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                                break;
                            case R.id.rb_order_sizeL:
                                size="L";
                                tongthanhtoan_=tongthanhtoan_+15000.0;
                                txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                    }
                });

                ck_order_topping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ck_order_topping.isChecked())
                        {
                            topping = "Thạch đậu đen ";
                            tongthanhtoan_=tongthanhtoan_+10000.0;
                            txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                        else
                        {
                            topping = "Không";
                            tongthanhtoan_=tongthanhtoan_-10000.0;
                            txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                    }
                });

                //-------------------------------------------------------

                btn_order_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        Bundle bundle = intent.getBundleExtra("send");
                        idKV = bundle.getString("maKV");
                        idBan = bundle.getString("maBan");
                        Mon_order mon = new Mon_order(list_drink.get(position).getMaSP(),list_drink.get(position).getTenSP(),
                                list_drink.get(position).getImgSP(),sl,tongthanhtoan_,size,topping);
                        list_mon.add(mon);
                        tong_Buid += tongthanhtoan_;
                        txt_tongGiaTien.setText(f.format(tong_Buid)+" VNĐ");
                        System.out.println("-------------------------------------"+list_mon.toString());
                        Toast.makeText(MainBan.this, "Thêm Vào Giỏ Hàng Thành Công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        gr_tea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openOder(Gravity.BOTTOM);
                sl = 1;
                size = "M";
                topping = "Không";
                String ten = list_mikltea.get(position).getTenSP();
                double gia_ = list_mikltea.get(position).getGiaSp();

                final Double[] gia = {list_mikltea.get(position).getGiaSp()};
                double tam = list_mikltea.get(position).getGiaSp();
                String img = list_mikltea.get(position).getImgSP();
                Picasso.get().load(img).into(img_sp);
                txt_tenSp.setText(ten);
                txt_giaSp.setText((f.format(gia_))+" VNĐ");
                txt_tongSP.setText((f.format(gia_))+" VNĐ");
                tongthanhtoan_=gia_;



                btn_cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl+=1;
                        txt_SL.setText(String.valueOf(sl));
                        tongthanhtoan_ += gia_ ;
                        txt_giaSp.setText(String.valueOf(f.format(list_mikltea.get(position).getGiaSp()))+" VNĐ");
                        txt_tongSP.setText((f.format(tongthanhtoan_))+" VNĐ");

                        txt_tongGiaTien.setText(f.format(tongthanhtoan_)+" VNĐ");
//                        hoadons.add(new Hoadon(1,list_all.get(position).getMaSP(),list_all.get(position).getTenSP(),"m","có",sl[0],gia[0]));
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl -= 1;
                        txt_SL.setText(String.valueOf(sl));
                        tongthanhtoan_-=gia_;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
//                        txt_tongGiaTien.setText(f.format(tongthanhtoan_)+" VNĐ");
                    }
                });
                rb_Gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_order_sizeM:
                                size = "M" ;
                                tongthanhtoan_=tongthanhtoan_-15000.0;
                                txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                                break;
                            case R.id.rb_order_sizeL:
                                size="L";
                                tongthanhtoan_=tongthanhtoan_+15000.0;
                                txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                    }
                });

                ck_order_topping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ck_order_topping.isChecked())
                        {
                            topping = "Thạch đậu đen ";
                            tongthanhtoan_=tongthanhtoan_+10000.0;
                            txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                        else
                        {
                            topping = "Không";
                            tongthanhtoan_=tongthanhtoan_-10000.0;
                            txt_tongSP.setText(f.format(tongthanhtoan_)+" VNĐ");
                        }
                    }
                });

                //-------------------------------------------------------

                btn_order_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        Bundle bundle = intent.getBundleExtra("send");
                        idKV = bundle.getString("maKV");
                        idBan = bundle.getString("maBan");
                        Mon_order mon = new Mon_order(list_mikltea.get(position).getMaSP(),list_mikltea.get(position).getTenSP(),
                                list_mikltea.get(position).getImgSP(),sl,tongthanhtoan_,size,topping);
                        list_mon.add(mon);
                        tong_Buid += tongthanhtoan_;
                        txt_tongGiaTien.setText(f.format(tong_Buid)+" VNĐ");
                        System.out.println("-------------------------------------"+list_mon.toString());
                        Toast.makeText(MainBan.this, "Thêm Vào Giỏ Hàng Thành Công", Toast.LENGTH_SHORT).show();
                    }
                });
            }
                });


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String s) {

                if (s.equalsIgnoreCase("ALL")) {

                    adapter = new SanPham_Adapter(MainBan.this, R.layout.item_sanpham, list_all);
                    gr_all.setAdapter(adapter);
                }else if (s.equalsIgnoreCase("Coffee")){
                    list_coffee.clear();
                    for (int i=0;i<list_all.size();i++){
                        if (list_all.get(i).getLoaiSP().equalsIgnoreCase("coffee")){
                            list_coffee.add(list_all.get(i));
                        }
                        adapter = new SanPham_Adapter(MainBan.this,R.layout.item_sanpham,list_coffee);

                        gr_coffree.setAdapter(adapter);
                    }
                }else if (s.equalsIgnoreCase("Milk Tea")){
                    list_mikltea.clear();
                    for (int i=0;i<list_all.size();i++){
                        if (list_all.get(i).getLoaiSP().equalsIgnoreCase("milk tea")){
                            list_mikltea.add(list_all.get(i));
                        }
                        adapter = new SanPham_Adapter(MainBan.this,R.layout.item_sanpham,list_mikltea);

                        gr_tea.setAdapter(adapter);
                    }
                }else if (s.equalsIgnoreCase("Dirnk")){
                    list_drink.clear();
                    for (int i=0;i<list_all.size();i++){
                        if (list_all.get(i).getLoaiSP().equalsIgnoreCase("drink")){
                            list_drink.add(list_all.get(i));
                        }
                        adapter = new SanPham_Adapter(MainBan.this,R.layout.item_sanpham,list_drink);

                        gr_drink.setAdapter(adapter);
                    }

                }
            }
        });
    }


    public void showSP(){
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu,MODE_PRIVATE);
        String email = sharedPreferences.getString("email","");
        String pass = sharedPreferences.getString("password","");
        String userShop =sharedPreferences.getString("userShop","");
        list_all = new ArrayList<>();
        list_coffee = new ArrayList<>();
        list_mikltea = new ArrayList<>();
        list_drink = new ArrayList<>();
        mData.child("UserShop").child(userShop).child("SanPham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham = snapshot.getValue(SanPham.class);
                list_all.add(sanPham);
                gr_all.setAdapter(adapter);
                gr_all.deferNotifyDataSetChanged();



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
    private void openOder(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_order);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);

        }
        txt_giaSp = dialog.findViewById(R.id.txt_order_giaSp);
        txt_tenSp = dialog.findViewById(R.id.txt_order_tenSP);
        img_sp = dialog.findViewById(R.id.img_oder_SP);
        txt_tongSP = dialog.findViewById(R.id.txt_order_tonggiaOrder);
        txt_SL = dialog.findViewById(R.id.txt_order_sl);
        btn_tru = dialog.findViewById(R.id.ibtn_order_giam);
        btn_cong = dialog.findViewById(R.id.ibtn_order_tang);
        btn_back = dialog.findViewById(R.id.ibtn_order_thoat);

        rb_Gr = dialog.findViewById(R.id.rb_hr_order);

        ck_order_topping = dialog.findViewById(R.id.ck_order_topping);

        btn_order_them = dialog.findViewById(R.id.btn_order_them);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btn_order_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void opengioHang(int gravity , Double gia) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_giahang);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);

        }
        lv_gioHang = dialog.findViewById(R.id.lv_gioHang);
        txt_TongTien = dialog.findViewById(R.id.txt_TongTien);
        ibtn_order_thoat_gioHang = dialog.findViewById(R.id.ibtn_order_thoat_gioHang);
        btn_order_GioHang = dialog.findViewById(R.id.btn_order_GioHang);
        apdapter_GioHang = new GioHang_Apdapter(this,R.layout.item_giohang,list_mon);
        lv_gioHang.setAdapter(apdapter_GioHang);
        txt_TongTien.setText((f.format(gia))+" VNĐ");
        ibtn_order_thoat_gioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    private void crearTab() {
        TabHost.TabSpec tabAll;
        tabAll = tabHost.newTabSpec("ALL");
        tabAll.setContent(R.id.tab_All);
        tabAll.setIndicator("ALL");
        tabHost.addTab(tabAll);

        TabHost.TabSpec tabcoffee;
        tabcoffee = tabHost.newTabSpec("Coffee");
        tabcoffee.setContent(R.id.gr_tabcoffee);
        tabcoffee.setIndicator("Coffee");
        tabHost.addTab(tabcoffee);
//
        TabHost.TabSpec tabmilkTea;
        tabmilkTea = tabHost.newTabSpec("Milk Tea");
        tabmilkTea.setContent(R.id.tab_milkTea);
        tabmilkTea.setIndicator("Milk Tea");
        tabHost.addTab(tabmilkTea);

        TabHost.TabSpec tabdirk;
        tabdirk = tabHost.newTabSpec("Dirnk");
        tabdirk.setContent(R.id.tab_Drink);
        tabdirk.setIndicator("Drink");
        tabHost.addTab(tabdirk);
    }

    private void addcontroll() {
        gr_all = findViewById(R.id.gr_taball);
        gr_coffree = findViewById(R.id.gr_tabcoffee);
        gr_tea = findViewById(R.id.gr_tabmilktea);
        gr_drink = findViewById(R.id.gr_tabdrink);
        txt_tong = findViewById(R.id.txt_tongGiaTien);
        btn_BackQLB =findViewById(R.id.btn_troveQLB);
        btn_order = findViewById(R.id.btn_order);
        ten_Ban = findViewById(R.id.ten_Ban);
        txt_tongGiaTien = findViewById(R.id.txt_tongGiaTien);

        adapter = new SanPham_Adapter(MainBan.this, R.layout.item_sanpham, list_all);

        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        crearTab();
    }
    private void oder(){
                Random rand = new Random();
                int ranNum = rand.nextInt(1000)+1;
                String thongtinlhu = "tk_mk keySho login";
                SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu,MODE_PRIVATE);
                String userShop =sharedPreferences.getString("userShop","");
                long millis=System.currentTimeMillis();
                java.sql.Date date=new java.sql.Date(millis);
                Detail_Buil detail_buil = new Detail_Buil(String.valueOf(date),idKV+idBan+String.valueOf(date)+String.valueOf(ranNum),idKV,idBan,list_mon,tong_Buid);
                mData.child("UserShop").child(userShop).child("HoaDon").child(idKV+idBan+String.valueOf(date)+String.valueOf(ranNum)).setValue(detail_buil);
                Intent intent = getIntent();
                Bundle bundle = intent.getBundleExtra("send");
                String idBan = bundle.getString("maBan");
                Toast.makeText(MainBan.this, "Order thành công", Toast.LENGTH_SHORT).show();
                mData.child("UserShop").child(userShop).child("Ban").child(idBan)
                        .child("trangThai").setValue(1);
    }



}