package com.hoangquangdev;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.hoangquangdev.Adapter.QLSP_Adapter;
import com.hoangquangdev.Adapter.SanPham_Adapter;
import com.hoangquangdev.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class MainQLSP extends Activity {

    ImageButton ibtn_xoa , ibtn_add;
    ImageView img_anh;
    EditText etxt_tenSp,etxt_giaSP;
    Button btn_add;
    ListView lv_SP;
    List<SanPham> dsSanPhams ;
    QLSP_Adapter adapter;


    int REQUEST_CODE_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_qlsp);
        addctroll();
        test();
        addevent();
    }

    private void addevent() {
        ibtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaddSanPham(Gravity.CENTER);
            }
        });
    }

    private void addctroll() {
        ibtn_xoa = findViewById(R.id.ibtn_QLSP_xoaSP);
        ibtn_add = findViewById(R.id.ibtn_QLSP_addSp);
        lv_SP = findViewById(R.id.lv_SP);

    }

    private void test() {
        //data_test

        dsSanPhams = new ArrayList<>();
        dsSanPhams.add(new SanPham("01", "Coffe", 0, 15.000, R.drawable.coffe));
        dsSanPhams.add(new SanPham("02", "Syting", 1, 15.000, R.drawable.coffe));
        dsSanPhams.add(new SanPham("03", "Nutifool", 1, 15.000, R.drawable.coffe));
        dsSanPhams.add(new SanPham("04", "Trà Sữa", 2, 15.000, R.drawable.coffe));
        dsSanPhams.add(new SanPham("05", "Trà", 2, 15.000, R.drawable.coffe));
        adapter = new QLSP_Adapter(this, R.layout.itemlistviewsanpham, dsSanPhams);
        lv_SP.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void openaddSanPham(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_sanpam);

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
        btn_add = dialog.findViewById(R.id.btn_addsp);
        img_anh=dialog.findViewById(R.id.img_addsp);

        img_anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });


        dialog.show();
    }
}