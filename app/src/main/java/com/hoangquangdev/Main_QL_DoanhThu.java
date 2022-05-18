package com.hoangquangdev;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Model.Detail_Buil;
import com.hoangquangdev.Model.Tessst;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Main_QL_DoanhThu extends Activity {
    BarChart barChart;
    ImageButton ibtn_dt_thoat;
    TextView txt_soHd ,txt_tongHD;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    ArrayList<Detail_Buil> ds_hoaDon = new ArrayList<>();
    double t1 = 0.000, t2 = 0.000, t3 = 0.000, t4 = 0.000, t5 = 0.000, t6 = 0.000, t7 = 0.000,tongtien;
    ArrayList<Double> gt = new ArrayList<>();
    ArrayList arr = new ArrayList();
    DecimalFormat f = new DecimalFormat("###,###,###");
    ArrayList<Tessst> tess = new ArrayList<>();
    String thongtinlhu = "tk_mk keySho login";
    String userShop;
    int tong_hd =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ql_doanh_thu);
        barChart = findViewById(R.id.barchart);
        ibtn_dt_thoat = findViewById(R.id.ibtn_dt_thoat);
        ibtn_dt_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu, MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String pass = sharedPreferences.getString("password", "");
        userShop = sharedPreferences.getString("userShop", "");
        int q = sharedPreferences.getInt("quyen", 0);
         txt_soHd =findViewById(R.id.txt_soHd);
         txt_tongHD = findViewById(R.id.txt_tongHD);
         getdata();

    }

    private void getdata() {
        for (int i = 6; i >= 0; i--) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -i);
            Date todate = cal.getTime();
            String fromdate = dateFormat.format(todate);
            arr.add(fromdate);
        }



            mData.child("UserShop").child(userShop).child("HoaDon").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    Detail_Buil detail_buil = snapshot.getValue(Detail_Buil.class);
                    ds_hoaDon.add(detail_buil);




                        if (detail_buil.getDate().equalsIgnoreCase((String) arr.get(0))) {

                            t1 += detail_buil.getTongBuild();
                            tong_hd +=1;

                        } else if (detail_buil.getDate().equalsIgnoreCase((String) arr.get(1))) {
                            t2 += detail_buil.getTongBuild();
                            tong_hd +=1;


                        } else if (detail_buil.getDate().equalsIgnoreCase((String) arr.get(2))) {
                            t3 += detail_buil.getTongBuild();
                            tong_hd +=1;


                        } else if (detail_buil.getDate().equalsIgnoreCase((String) arr.get(3))) {
                            t4 += detail_buil.getTongBuild();
                            tong_hd +=1;


                        } else if (detail_buil.getDate().equalsIgnoreCase((String) arr.get(4))) {
                            t5 += detail_buil.getTongBuild();
                            tong_hd +=1;


                        } else if (detail_buil.getDate().equalsIgnoreCase((String) arr.get(5))) {
                            t6 += detail_buil.getTongBuild();


                        } else if (detail_buil.getDate().equalsIgnoreCase((String) arr.get(6))) {
                            t7 += detail_buil.getTongBuild();
                            tong_hd +=1;

                        }

                    ArrayList NoOfEmp = new ArrayList();
                    NoOfEmp.add(new BarEntry((float) t1, 0));
                    NoOfEmp.add(new BarEntry((float) t2, 1));
                    NoOfEmp.add(new BarEntry((float) t3, 2));
                    NoOfEmp.add(new BarEntry((float) t4, 3));
                    NoOfEmp.add(new BarEntry((float) t5, 4));
                    NoOfEmp.add(new BarEntry((float) t6, 5));
                    NoOfEmp.add(new BarEntry((float) t7, 6));
                    BarDataSet bardataset = new BarDataSet(NoOfEmp, "Danh  Thu 7 Ngày");
                    barChart.animateX(5000);
                    BarData data = new BarData(arr, bardataset);
                    bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                    barChart.setData(data);
                    txt_soHd.setText(String.valueOf(tong_hd)+"Hóa Đơn");
                    txt_tongHD.setText(String.valueOf(f.format(t1+t2+t3+t4+t5+t6+t7))+" VNĐ");
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
//        ArrayList arr = new ArrayList();


//        }

//        for (int i = 0 ; i < ds_hoaDon.size();i++){
//            if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(2))){
//                System.out.println("---------================================"+ds_hoaDon.get(i).toString());
//            }
//        }
    }

}

