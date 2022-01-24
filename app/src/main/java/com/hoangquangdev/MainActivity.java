package com.hoangquangdev;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Model.NhanVien;
import com.hoangquangdev.Model.User;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {


    Button btnLogin ,btnTaoTK, btnquenMK,btn_singup ;
    EditText etxtTaiKkhan , etxtMatKhau;
    EditText etxt_email,etxt_passdk,etxt_phone,etxt_fullname,etxt_namqua;



    DatabaseReference mData;

    User user = new User();
    ArrayList<User>  ds_user = new ArrayList<>();
    ArrayList<NhanVien> ds_userNv = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        mData = FirebaseDatabase.getInstance().getReference();


        addcontroll();
        addevent();
        getdata();

    }

    private void addevent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                funLogin();
            }
        });
        btnTaoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                open_dialog(Gravity.CENTER);

            }
        });
    }


    private void addcontroll() {
        btnLogin =  findViewById(R.id.btn_Login);
        btnTaoTK = findViewById(R.id.bt_dkTaiKhan);
        btnquenMK =  findViewById(R.id.bt_quenMK);
        etxtTaiKkhan = findViewById(R.id.etxt_TaiKhoan);
        etxtMatKhau =  findViewById(R.id.etxt_MatKhau);

    }

    private void getdata() {
        mData.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                user = snapshot.getValue(User.class);
                ds_user.add(user);
                System.out.println(ds_user.toString());
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
        mData.child("NhanVien").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                NhanVien usernv = snapshot.getValue(NhanVien.class);
                ds_userNv.add(usernv);
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


    private void funLogin() {
        System.out.println("---------------"+ds_userNv.toString());
        int chek = 0;
       for (int i =0; i< ds_user.size();i++){
           if (ds_user.get(i).getEmail().equals(etxtTaiKkhan.getText().toString().trim()) &&
                   ds_user.get(i).getPass().equals(etxtMatKhau.getText().toString().trim())){
//               Intent intent = new Intent(MainActivity.this,MainQLKV.class);
//               startActivity(intent);
               chek =1;
           }
       }
       for (int i = 0 ; i < ds_userNv.size();i++){
           if (ds_userNv.get(i).getTaiKhoan().equals(etxtTaiKkhan.getText().toString().trim())&&
                   ds_userNv.get(i).getMatKhau().equals(etxtMatKhau.getText().toString().trim())){
               chek = 2;
           }
       }
       if (chek == 1){
          Intent intent = new Intent(MainActivity.this,MainQLKV.class);
               startActivity(intent);
       }
       else if (chek ==2){
           Intent intent = new Intent(MainActivity.this,Super_QLKV.class);
           startActivity(intent);
       }
       else {
           Toast.makeText(MainActivity.this, "Sai Tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
       }

    }



    private void open_dialog(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.digalog_dktk);

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
        etxt_email = dialog.findViewById(R.id.etxt_email);
        etxt_passdk = dialog.findViewById(R.id.etxt_passdk);
        btn_singup = dialog.findViewById(R.id.btn_singup);
        etxt_phone = dialog.findViewById(R.id.etxt_phone);
        etxt_fullname = dialog.findViewById(R.id.etxt_fullname);
        etxt_namqua = dialog.findViewById(R.id.etxt_namqua);
        dialog.show();

        btn_singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                singup();
            }
        });
    }
    private void singup(){

        String email = etxt_email.getText().toString().trim();
        String password =etxt_passdk.getText().toString().trim();
        String phone = etxt_phone.getText().toString().trim();
        String fullname = etxt_fullname.getText().toString().trim();
        String namquan = etxt_namqua.getText().toString().trim();
        int q = 0;
        Random random = new Random();
        int id  = 1+random.nextInt(100);

        User use = new User(id,fullname,namquan,phone,email,password,q);
        mData.child("User").child("id"+id).setValue(use);

        Toast.makeText(MainActivity.this, "Đăng Ký Thành Công !", Toast.LENGTH_SHORT).show();



    }


}
