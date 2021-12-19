package com.hoangquangdev;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.hoangquangdev.Adapter.Hoadon_Adaper;
import com.hoangquangdev.Model.Hoadon;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainThanhToan extends Activity {
    Hoadon_Adaper hoadon_adaper;
    ArrayList<Hoadon>hoadons = new ArrayList<>();
    TextView txt_tenB,gia,txt_ThanhToan_tongGiaTien;
    Button btn_Thanhtoan;
    ListView lv_sp_thanhtoan;
    ImageButton btn_back_thanhtoan ;

    DecimalFormat f = new DecimalFormat("##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_thanhtoan);
        addcontroll();


        hoadons = (ArrayList<Hoadon>) getIntent().getSerializableExtra("send_data");

        hoadon_adaper = new Hoadon_Adaper(MainThanhToan.this,R.layout.item_thanhtoan,hoadons);
        lv_sp_thanhtoan.setAdapter(hoadon_adaper);
        hoadon_adaper.notifyDataSetChanged();
        int i;
        double tong=0;
        for ( i=0 ;i <hoadons.size();i++){
                tong = tong+hoadons.get(i).getGiaSanpham();
        }
        txt_ThanhToan_tongGiaTien.setText(f.format(tong));

        btn_back_thanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
}
