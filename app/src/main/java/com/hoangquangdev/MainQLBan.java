package com.hoangquangdev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.TextView;

import com.hoangquangdev.Adapter.Ban_Adapter;
import com.hoangquangdev.Model.Ban;
import com.hoangquangdev.Model.KhuVuc;

import java.util.ArrayList;

public class MainQLBan extends Activity {
    GridView gr_hinhB;
    TextView txt_tenBan,txt_tenKV;

    ArrayList<Ban> dsBan;
    Ban_Adapter ban_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_qlban);

        addctroll();
    }

    private void addctroll() {
        gr_hinhB = findViewById(R.id.gr_hinhBan);
        txt_tenBan = findViewById(R.id.txt_tenBan);
        txt_tenKV = findViewById(R.id.txt_tenKV_QLB);


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("send");
        txt_tenKV.setText(bundle.getString("tenKV"));

//        Intent intent = getIntent();
//        txt_tenKV.setText(intent.getIntExtra("tenKV",0));

        dsBan = new ArrayList<>();
        dsBan.add(new Ban("001","Ban1",0,R.drawable.bantrong));
        dsBan.add(new Ban("002","Ban2",0,R.drawable.bantrong));
        dsBan.add(new Ban("003","Ban3",1,R.drawable.bantrong));
        dsBan.add(new Ban("004","Ban4",2,R.drawable.bantrong));
        dsBan.add(new Ban("005","Ban5",2,R.drawable.bantrong));
        dsBan.add(new Ban("006","Ban6",1,R.drawable.bantrong));
        dsBan.add(new Ban("007","Ban7",3,R.drawable.bantrong));
        dsBan.add(new Ban("008","Ban8",1,R.drawable.bantrong));
        dsBan.add(new Ban("009","Ban9",3,R.drawable.bantrong));
        dsBan.add(new Ban("010","Ban10",1,R.drawable.bantrong));

        ban_adapter = new Ban_Adapter(MainQLBan.this,R.layout.itemban , dsBan);
        gr_hinhB.setAdapter(ban_adapter);
        ban_adapter.notifyDataSetChanged();
    }
}