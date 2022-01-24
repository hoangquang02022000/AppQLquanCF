package com.hoangquangdev;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Main_QL_DoanhThu extends AppCompatActivity {
    BarChart barChart;
    ImageButton ibtn_dt_thoat;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    ArrayList<DoanhThu>ds_hoaDon ;
    double t1=0.000,t2=0.000,t3=0.000,t4=0.000,t5=0.000,t6=0.000,t7=0.000;
    ArrayList arr = new ArrayList();
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


        getdata();



    }

    private void getdata() {
        ds_hoaDon = new ArrayList<>();
//        ArrayList arr = new ArrayList();
        for (int i=0 ;i<7;i++){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -i);
             Date todate = cal.getTime();
            String fromdate = dateFormat.format(todate);
            arr.add(fromdate);
        }
        System.out.println("-----"+arr.toString());

        for (int i =0 ; i<arr.size();i++){
            ds_hoaDon.clear();

            mData.child("HoaDon").child("TongTien").child(String.valueOf(arr.get(i).toString())).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    DoanhThu doanhThu = snapshot.getValue(DoanhThu.class);
                    ds_hoaDon.add(doanhThu);
                    System.out.println("-----------"+ds_hoaDon.toString());
                    for (int i = 0 ; i < ds_hoaDon.size();i++) {
                        if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(0))) {
                            t1 += ds_hoaDon.get(i).getTongTien();
//                            System.out.println("---------================================" + t1);

                        } else
                        if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(1))) {
                            t2 += ds_hoaDon.get(i).getTongTien();
//                            System.out.println("---------================================" + t2);

                        }
                        else
                        if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(2))) {
                            t3 += ds_hoaDon.get(i).getTongTien();
//                            System.out.println("---------================================" + t3);

                        }
                        else
                        if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(3))) {
                            t4 += ds_hoaDon.get(i).getTongTien();
//                            System.out.println("---------================================" + t4);

                        }
                        else
                        if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(4))) {
                            t5 += ds_hoaDon.get(i).getTongTien();
//                            System.out.println("---------================================" + t5);

                        }
                        else
                        if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(5))) {
                            t6 += ds_hoaDon.get(i).getTongTien();
//                            System.out.println("---------================================" + t6);

                        }
                        else
                        if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(6))) {
                            t7 += ds_hoaDon.get(i).getTongTien();
//                            System.out.println("---------================================" +ds_hoaDon.get(i).toString());
//                            System.out.println("---------================================" +t7);

                        }
                    }
                    ArrayList NoOfEmp = new ArrayList();
                    NoOfEmp.add(new BarEntry((float) t1/10, 0));
                    NoOfEmp.add(new BarEntry((float) t2/10, 1));
                    NoOfEmp.add(new BarEntry((float) t3/10, 2));
                    NoOfEmp.add(new BarEntry((float) t4/10, 3));
                    NoOfEmp.add(new BarEntry((float) t5/10, 4));
                    NoOfEmp.add(new BarEntry((float) t6/10, 5));
                    NoOfEmp.add(new BarEntry((float) t7/10, 6));
                    BarDataSet bardataset = new BarDataSet(NoOfEmp, "Danh  Thu 7 Ngày");
                    barChart.animateX(5000);
                    BarData data = new BarData(arr, bardataset);
                    bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
                    barChart.setData(data);



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

//        for (int i = 0 ; i < ds_hoaDon.size();i++){
//            if (ds_hoaDon.get(i).getDate().equalsIgnoreCase((String) arr.get(2))){
//                System.out.println("---------================================"+ds_hoaDon.get(i).toString());
//            }
//        }
    }


}
    class DoanhThu{
        private String idHoaDon;
        private double tongTien;
        private String date;
        public DoanhThu() {
        }

        public DoanhThu(String idHoaDon, double tongTien, String date) {
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

        @Override
        public String toString() {
            return "DoanhThu{" +
                    "idHoaDon='" + idHoaDon + '\'' +
                    ", tongTien=" + tongTien +
                    ", date='" + date + '\'' +
                    '}';
        }
    }
