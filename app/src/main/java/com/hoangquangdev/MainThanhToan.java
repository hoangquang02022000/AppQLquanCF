package com.hoangquangdev;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Adapter.Hoadon_Adaper;
import com.hoangquangdev.Model.Hoadon;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MainThanhToan extends Activity {
    Hoadon_Adaper hoadon_adaper;
    ArrayList<Hoadon>hoadons = new ArrayList<>();
    TextView txt_tenB,gia,txt_ThanhToan_tongGiaTien,txt_thongbao;
    Button btn_Thanhtoan,btn_yes,btn_no;
    ListView lv_sp_thanhtoan;
    ImageButton btn_back_thanhtoan ;
    double tongTien = 0.00;
    String maKV ,maBan,tenBan,idBan;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();

    DecimalFormat f = new DecimalFormat("###,###,###");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_thanhtoan);
        addcontroll();
        getdata();





        btn_back_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_Thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog_thongbao(Gravity.CENTER);
            }
        });

    }

    private void getdata() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("send");
         maKV = bundle.getString("maKV");
         maBan = bundle.getString("maBan");
         tenBan = bundle.getString("tenBan");
        txt_tenB.setText(tenBan);
        System.out.println("----"+maKV+"--------"+maBan+"-----"+tenBan);

        mData.child("Order").child(maKV).child(maKV+"-"+tenBan).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Hoadon hd = snapshot.getValue(Hoadon.class);
                hoadons.add(hd);
                idBan = hd.getIdBan();
                hoadon_adaper =new Hoadon_Adaper(MainThanhToan.this, R.layout.item_thanhtoan,hoadons);
                lv_sp_thanhtoan.setAdapter(hoadon_adaper);
                hoadon_adaper.notifyDataSetChanged();

                for (int i = 0 ; i<hoadons.size();i++){
                    tongTien += hoadons.get(i).getGiaSanpham();
                    txt_ThanhToan_tongGiaTien.setText(f.format(tongTien)+" VNĐ");
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

    private void addcontroll() {
        txt_tenB = findViewById(R.id.txt_tenBan_thanhtoan);
        gia = findViewById(R.id.txt_ThanhToan_tongGiaTien);
        btn_Thanhtoan = findViewById(R.id.btn_thanhtoan);
        lv_sp_thanhtoan = findViewById(R.id.lv_sp_thanhtoan);
        btn_back_thanhtoan = findViewById(R.id.btn_back_thanhtoan);
        txt_ThanhToan_tongGiaTien = findViewById(R.id.txt_ThanhToan_tongGiaTien);
    }
    private void opendialog_thongbao(int gravity) {
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
        btn_yes=dialog.findViewById(R.id.btn_yes);
        btn_no=dialog.findViewById(R.id.btn_no);
        txt_thongbao=dialog.findViewById(R.id.txt_thongbao);
        txt_thongbao.setText("Bạn có muốn thanh toán !");
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                thanhToan();
//                Toast.makeText(MainThanhToan.class, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void thanhToan(){
        UUID uniqueKey = UUID.randomUUID();
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        String id = String.valueOf(date);
        Map<String, Object> map = new HashMap<>();
        map.put(uniqueKey+"-"+id+"-"+maKV+"-"+tenBan,hoadons);




        thanhToan  tttien = (new thanhToan(uniqueKey+"-"+id+"-"+maKV+"-"+tenBan,tongTien,id));
        mData.child("HoaDon").child("ds_Hoandon").child(id).updateChildren(map);
        mData.child("HoaDon").child("TongTien").child(id).child(uniqueKey+id+"-"+maKV+"-"+tenBan).setValue(tttien);
        mData.child("Ban").child(idBan).child("trangThai").setValue(0);
        mData.child("Order").child(maKV).child(maKV+"-"+tenBan).removeValue();
        Intent intent = new Intent(MainThanhToan.this,Main_thuNgan.class);
        startActivity(intent);
        Toast.makeText(MainThanhToan.this, "Thanh Toán Thành Công !", Toast.LENGTH_SHORT).show();
//
//        thanhToan  tttien = (new thanhToan(uniqueKey+"-"+"2022-01-16"+"-"+maKV+"-"+tenBan,tongTien,id));
//        mData.child("HoaDon").child("ds_Hoandon").child("2022-01-16").updateChildren(map);
//        mData.child("HoaDon").child("TongTien").child("2022-01-16").child(uniqueKey+id+"-"+maKV+"-"+tenBan).setValue(tttien);
////        System.out.println("---------"+tien.toString());
//        mData.child("Ban").child(idBan).child("trangThai").setValue(0);
//        mData.child("Order").child(maKV).child(maKV+"-"+tenBan).removeValue();
//        Intent intent = new Intent(MainThanhToan.this,Main_thuNgan.class);
//        startActivity(intent);
//        Toast.makeText(MainThanhToan.this, "Thanh Toán Thành Công !", Toast.LENGTH_SHORT).show();
    }

}
class thanhToan{
    private String idHoaDon;
    private double tongTien;
    private String date;
    public thanhToan() {
    }

    public thanhToan(String idHoaDon, double tongTien, String date) {
        this.idHoaDon = idHoaDon;
        this.tongTien = tongTien;
        this.date = date;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
