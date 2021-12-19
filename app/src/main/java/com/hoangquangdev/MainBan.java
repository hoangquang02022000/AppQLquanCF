package com.hoangquangdev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Adapter.SanPham_Adapter;
import com.hoangquangdev.Model.Hoadon;
import com.hoangquangdev.Model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainBan extends Activity {

    TabHost tabHost;
    GridView gr_all , gr_coffee , gr_milktea , gr_drink ;
    ArrayList<SanPham> list_all , list_coffee , list_mikltea , list_drink ,list_order;
    SanPham_Adapter adapter , adapter_coffee ,adapter_milktea , adapter_drink ;
    Button btn_BackQLB,btn_order_them,btn_order;
    ImageButton btn_tru ,btn_cong,btn_back,ibtn_check;
    TextView txt_tong,txt_tenSp,txt_giaSp,txt_tongSP,txt_SL,txt_tongGiaTien;
    ImageView img_sp;
    RadioGroup rb_Gr;
    CheckBox ck_order_topping;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();


    DecimalFormat f = new DecimalFormat("##");

    ArrayList<Hoadon> ds_Hoadon = new ArrayList<>();
    Hoadon hd = new Hoadon();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ban);
        
        addcontroll();
        addevent();
        showSP();
    }

    private void addevent() {
        list_order = new ArrayList<>();
        ibtn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainBan.this,MainThanhToan.class);
                intent.putExtra("send_data",ds_Hoadon);
                startActivity(intent);
            }

        });
        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mData.child("Order").push().child(String.valueOf(ds_Hoadon));
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
                String ten = list_all.get(position).getTenSP();
                final Double[] gia = {list_all.get(position).getGiaSp()};
                double tam = list_all.get(position).getGiaSp();
                String img = list_all.get(position).getImgSP();
                Picasso.get().load(img).into(img_sp);
                txt_tenSp.setText(ten);
                txt_giaSp.setText((f.format(gia[0]))+" VNĐ");

                final int[] sl = {1};
                btn_cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl[0] = sl[0] +1;
                        txt_SL.setText(String.valueOf(sl[0]));
                        gia[0] = gia[0]+tam ;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText((f.format(gia[0]))+" VNĐ");

                        txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
//                        hoadons.add(new Hoadon(1,list_all.get(position).getMaSP(),list_all.get(position).getTenSP(),"m","có",sl[0],gia[0]));
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl[0] -= 1;
                        txt_SL.setText(String.valueOf(sl[0]));
                        gia[0]=gia[0]-tam;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                        txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                rb_Gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    String size;
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_order_sizeM:
                                 size = "M" ;
                                break;
                            case R.id.rb_order_sizeL:
                                size="L";
                                gia[0]=gia[0]+sl[0]*15000;
                                txt_tongSP.setText(f.format(gia[0])+" VNĐ");
                        }
                    }
                });

                ck_order_topping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gia[0]=gia[0]+sl[0]*10000;
                        txt_tongSP.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                btn_order_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         hd = new Hoadon(1,list_all.get(position).getMaSP()
                                ,list_all.get(position).getTenSP(),"m","có",sl[0],gia[0]
                                ,list_all.get(position).getImgSP());
                        ds_Hoadon.add(hd);

                    }
                });

            }

        });
        gr_coffee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openOder(Gravity.BOTTOM);
                String ten = list_coffee.get(position).getTenSP();
                final Double[] gia = {list_coffee.get(position).getGiaSp()};
                String img = list_coffee.get(position).getImgSP();
                Picasso.get().load(img).into(img_sp);
                txt_tenSp.setText(ten);
                txt_giaSp.setText(String.valueOf(gia[0])+"VND");
                txt_tongSP.setText(String.valueOf(gia[0])+"VND");
                double tam = list_all.get(position).getGiaSp();
                final int[] sl = {1};
                btn_cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl[0] = sl[0] +1;
                        txt_SL.setText(String.valueOf(sl[0]));
                        gia[0] = gia[0]+tam ;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                        txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl[0] -= 1;
                        txt_SL.setText(String.valueOf(sl[0]));
                        gia[0]=gia[0]-tam;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                        txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                rb_Gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    String size;
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_order_sizeM:
                                size = "M" ;
                                break;
                            case R.id.rb_order_sizeL:
                                size="L";
                                gia[0]=gia[0]+sl[0]*15000;
                                txt_tongSP.setText(f.format(gia[0])+" VNĐ");
                        }
                    }
                });
                ck_order_topping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gia[0]=gia[0]+sl[0]*10000;
                        txt_tongSP.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                btn_order_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Hoadon hd = new Hoadon(1,list_coffee.get(position).getMaSP()
                                ,list_coffee.get(position).getTenSP(),"m","có",sl[0],gia[0]
                                ,list_coffee.get(position).getImgSP());
                        finish();
                    }
                });


            }
        });
        gr_drink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openOder(Gravity.BOTTOM);
                String ten = list_drink.get(position).getTenSP();
                final Double[] gia = {list_all.get(position).getGiaSp()};
                String img = list_drink.get(position).getImgSP();
                Picasso.get().load(img).into(img_sp);
                txt_tenSp.setText(ten);
                txt_giaSp.setText(String.valueOf(gia[0])+"VND");
                txt_tongSP.setText(String.valueOf(gia[0])+"VND");
                double tam = list_all.get(position).getGiaSp();
                final int[] sl = {1};
                btn_cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl[0] = sl[0] +1;
                        txt_SL.setText(String.valueOf(sl[0]));
                        gia[0] = gia[0]+tam ;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                        txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl[0] -= 1;
                        txt_SL.setText(String.valueOf(sl[0]));
                        gia[0]=gia[0]-tam;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                        txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                ck_order_topping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gia[0]=gia[0]+sl[0]*10000;
                        txt_tongSP.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                rb_Gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    String size;
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_order_sizeM:
                                size = "M" ;
                                break;
                            case R.id.rb_order_sizeL:
                                size="L";
                                gia[0]=gia[0]+sl[0]*15000;
                                txt_tongSP.setText(f.format(gia[0])+" VNĐ");
                        }
                    }
                });
                btn_order_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Hoadon hd = new Hoadon(1,list_drink.get(position).getMaSP()
                                ,list_drink.get(position).getTenSP(),"m","có",sl[0],gia[0]
                                ,list_drink.get(position).getImgSP());


                    }
                });


            }
        });

        gr_milktea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openOder(Gravity.BOTTOM);
                String ten = list_mikltea.get(position).getTenSP();
                final Double[] gia = {list_mikltea.get(position).getGiaSp()};
                String img = list_mikltea.get(position).getImgSP();
                Picasso.get().load(img).into(img_sp);
                txt_tenSp.setText(ten);
                txt_giaSp.setText(String.valueOf(gia[0])+"VND");
                txt_tongSP.setText(String.valueOf(gia[0])+"VND");

                double tam = list_all.get(position).getGiaSp();
                final int[] sl = {1};
                btn_cong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl[0] = sl[0] +1;
                        txt_SL.setText(String.valueOf(sl[0]));
                        gia[0] = gia[0]+tam ;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                        txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                btn_tru.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sl[0] -= 1;
                        txt_SL.setText(String.valueOf(sl[0]));
                        gia[0]=gia[0]-tam;
                        txt_giaSp.setText(String.valueOf(f.format(gia[0]))+" VNĐ");
                        txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                        txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                txt_tongSP.setText((f.format(gia[0]))+" VNĐ");
                txt_tongGiaTien.setText(f.format(gia[0])+" VNĐ");
                rb_Gr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    String size;
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_order_sizeM:
                                size = "M" ;
                                break;
                            case R.id.rb_order_sizeL:
                                size="L";
                                gia[0]=gia[0]+sl[0]*15000;
                                txt_tongSP.setText(f.format(gia[0])+" VNĐ");
                        }
                    }
                });
                ck_order_topping.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gia[0]=gia[0]+sl[0]*10000;
                        txt_tongSP.setText(f.format(gia[0])+" VNĐ");
                    }
                });
                btn_order_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Hoadon hd = new Hoadon(1,list_mikltea.get(position).getMaSP()
                                ,list_mikltea.get(position).getTenSP(),"m","có",sl[0],gia[0]
                                ,list_mikltea.get(position).getImgSP());
                        Intent intent = new Intent(MainBan.this,MainThanhToan.class);
                        intent.putExtra("send_data",hd);
                        startActivity(intent);

                    }
                });

            }
        });
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equalsIgnoreCase("ALL")){
                    adapter.notifyDataSetChanged();


                }else if (s.equalsIgnoreCase("Coffee")){
                    adapter_coffee.notifyDataSetChanged();
                    list_coffee.clear();
                    for (int i=0;i<list_all.size();i++){
                        if (list_all.get(i).getLoaiSP()==0){
                            list_coffee.add(list_all.get(i));
                        }
                    }
                }else if (s.equalsIgnoreCase("Milk Tea")){
                    adapter_milktea.notifyDataSetChanged();
                    list_mikltea.clear();
                    for (int i=0;i<list_all.size();i++){
                        if (list_all.get(i).getLoaiSP()==2){
                            list_mikltea.add(list_all.get(i));
                        }
                    }
                }else if (s.equalsIgnoreCase("Dirnk")){
                    adapter_drink.notifyDataSetChanged();
                    list_drink.clear();
                    for (int i=0;i<list_all.size();i++){
                        if (list_all.get(i).getLoaiSP()==1){
                            list_drink.add(list_all.get(i));
                        }
                    }

                }
            }
        });
    }


    public void showSP(){
        list_all = new ArrayList<>();
        list_coffee = new ArrayList<>();
        list_mikltea = new ArrayList<>();
        list_drink = new ArrayList<>();
        mData.child("SanPham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham = snapshot.getValue(SanPham.class);
                list_all.add(sanPham);


                adapter = new SanPham_Adapter(MainBan.this, R.layout.item_sanpham, list_all);
                gr_all.setAdapter(adapter);

                adapter_coffee = new SanPham_Adapter(MainBan.this,R.layout.item_sanpham,list_coffee);
                gr_coffee.setAdapter(adapter_coffee);

                adapter_milktea = new SanPham_Adapter(MainBan.this,R.layout.item_sanpham,list_mikltea);
                gr_milktea.setAdapter(adapter_milktea);

                adapter_drink = new SanPham_Adapter(MainBan.this,R.layout.item_sanpham,list_drink);
                gr_drink.setAdapter(adapter_drink);

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
        dialog.setContentView(R.layout.order);

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
        tabcoffee.setContent(R.id.tab_coffee);
        tabcoffee.setIndicator("Coffee");
        tabHost.addTab(tabcoffee);

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
        gr_coffee = findViewById(R.id.gr_tabcoffee);
        gr_milktea = findViewById(R.id.gr_tabmilktea);
        gr_drink = findViewById(R.id.gr_tabdrink);
        txt_tong = findViewById(R.id.txt_tongGiaTien);
        btn_BackQLB =findViewById(R.id.btn_troveQLB);
        btn_order = findViewById(R.id.btn_order);

        txt_tongGiaTien = findViewById(R.id.txt_tongGiaTien);
        ibtn_check = findViewById(R.id.ibtn_check);



        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        crearTab();
    }

}
/*
*       List<Hoadon> = new ArraryList<>(); //id, tensp, sl, gia, size, tongthanhtoan
*
*
* */