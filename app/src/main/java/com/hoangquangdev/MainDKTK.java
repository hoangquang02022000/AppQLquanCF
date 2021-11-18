package com.hoangquangdev;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Model.taiKhoan;

import java.util.ArrayList;

public class MainDKTK extends Activity {
    EditText etxt_tenDN,etxt_passWord,etxt_hoTen,etxt_ngaySinh,etxt_Email,etxt_sdt,etxt_diaChi;
    Button btn_dangK;
    ArrayList<taiKhoan> taiKhoans;
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dktk);
        addcontroll();
        mData = FirebaseDatabase.getInstance().getReference();

        addevent();
    }


    private void addcontroll() {
        etxt_tenDN = (EditText) findViewById(R.id.etxt_TaiKhoanDK);
        etxt_passWord =(EditText)  findViewById(R.id.etxt_Password);
        etxt_hoTen = (EditText) findViewById(R.id.etxt_hovTen);
        etxt_ngaySinh = (EditText) findViewById(R.id.etxt_ngaySinh);
        etxt_Email =(EditText)  findViewById(R.id.etxt_email);
        etxt_sdt =(EditText)  findViewById(R.id.etxt_sdt);
        etxt_diaChi = (EditText) findViewById(R.id.etxt_diaChi);
        btn_dangK = (Button) findViewById(R.id.btn_DangKy);


    }
    private void addevent() {
        btn_dangK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dc = etxt_diaChi.getText().toString();
                String ht =  etxt_hoTen.getText().toString();
                String ngSinh = etxt_ngaySinh.getText().toString();
                String email = etxt_Email.getText().toString();
                String pass = etxt_passWord.getText().toString();
                String sdt = etxt_sdt.getText().toString();
                String user = etxt_tenDN.getText().toString();

                taiKhoan tk = new taiKhoan(user,pass,ht,ngSinh,dc,email,sdt);

//                DKTK dktk = new DKTK(etxt_tenDN.getText().toString(),etxt_passWord.getText().toString(),
//                        etxt_hoTen.getText().toString(),etxt_ngaySinh.getText().toString() ,
//                        etxt_diaChi.getText().toString(),etxt_Email.getText().toString(),etxt_sdt.getText().toString());

                mData.child("TaiKhoan").push().setValue(tk, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, DatabaseReference databaseReference) {
                        if(databaseError==null){
                            Toast.makeText(MainDKTK.this,"Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                            Intent mhdn = new Intent(MainDKTK.this,MainActivity.class);
                            startActivity(mhdn);

                        }else {
                            Toast.makeText(MainDKTK.this,"Đăng Ký Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

}