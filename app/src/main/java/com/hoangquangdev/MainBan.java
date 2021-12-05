package com.hoangquangdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.hoangquangdev.Adapter.SanPham_Adapter;
import com.hoangquangdev.Model.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainBan extends Activity {

    TabHost tabHost;
    GridView gr_all , gr_coffee , gr_milktea , gr_drink ;
    ArrayList<SanPham> list_all , list_coffee , list_mikltea , list_drink;
    SanPham_Adapter adapter , adapter_coffee ,adapter_milktea , adapter_drink ;
    Button btn_BackQLB;
    TextView txt_tong;


    DecimalFormat f = new DecimalFormat("##.000");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ban);
        
        addcontroll();

        test();

        addevent();
    }

    private void addevent() {
        btn_BackQLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void test() {
        //data_test
        list_all = new ArrayList<>();
        list_coffee = new ArrayList<>();
        list_mikltea = new ArrayList<>();
        list_drink = new ArrayList<>();



        list_all.add(new SanPham("01","Coffe",0,15.000,R.drawable.coffe));
        list_all.add(new SanPham("02","Syting",1,15.000,R.drawable.coffe));
        list_all.add(new SanPham("03","Nutifool",1,15.000,R.drawable.coffe));
        list_all.add(new SanPham("04","Trà Sữa",2,15.000,R.drawable.coffe));
        list_all.add(new SanPham("05","Trà",2,15.000,R.drawable.coffe));

        adapter = new SanPham_Adapter(this,R.layout.item_sanpham,list_all);
        gr_all.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter_coffee = new SanPham_Adapter(this,R.layout.item_sanpham,list_coffee);
        gr_coffee.setAdapter(adapter_coffee);
        adapter_coffee.notifyDataSetChanged();
        adapter_milktea = new SanPham_Adapter(this,R.layout.item_sanpham,list_mikltea);
        gr_milktea.setAdapter(adapter_milktea);

        adapter_drink = new SanPham_Adapter(this,R.layout.item_sanpham,list_drink);
        gr_drink.setAdapter(adapter_drink);
        for (SanPham sp : list_all){
            if (sp.getLoaiSP()==0){

                list_coffee.add(sp);

            }
            else if(sp.getLoaiSP()==1){
                list_drink.add(sp);

            }
            else if(sp.getLoaiSP()==2){
                list_mikltea.add(sp);

            }
            adapter_coffee.notifyDataSetChanged();
            adapter_drink.notifyDataSetChanged();
            adapter_milktea.notifyDataSetChanged();
        }


        ///
        gr_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                double giaSP = list_all.get(position).getGiaSp();
                double tong=0.000 ;

                txt_tong.setText(f.format(tong=+giaSP)+"VND");


            }
        });
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



        tabHost = findViewById(R.id.tabHost);
        tabHost.setup();
        crearTab();
    }
}