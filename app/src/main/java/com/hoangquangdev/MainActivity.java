package com.hoangquangdev;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Model.Login;
import com.hoangquangdev.Model.taiKhoan;

public class MainActivity extends Activity {


    Button btnLogin ,btnTaoTK, btnquenMK ;
    EditText etxtTaiKkhan , etxtMatKhau;


    DatabaseReference mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        mData = FirebaseDatabase.getInstance().getReference();

        addcontroll();
        addevent();
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
                funTaoTK();
            }
        });
    }


    private void addcontroll() {
        btnLogin = (Button) findViewById(R.id.btn_Login);
        btnTaoTK = (Button) findViewById(R.id.bt_dkTaiKhan);
        btnquenMK = (Button) findViewById(R.id.bt_quenMK);
        etxtTaiKkhan = (EditText)findViewById(R.id.etxt_TaiKhoan);
        etxtMatKhau = (EditText) findViewById(R.id.etxt_MatKhau);

    }

    ///////////////////////////////////////////////////////////----
    private void funLogin() {

        mData.child("TaiKhoan").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Login lg= dataSnapshot.getValue(Login.class);
//                System.out.println(lg.toString());
                taiKhoan tk = dataSnapshot.getValue(taiKhoan.class);
                String user = etxtTaiKkhan.getText().toString();
                String pass = etxtMatKhau.getText().toString();
                String userg = tk.getTenDN();
                String passg = tk.getPassword();
                System.out.println("tk : "+tk.getTenDN()+"pas : "+tk.getPassword()+"\n");
//                Toast.makeText(MainActivity.this, taikhoan.getTenDN() , Toast.LENGTH_SHORT).show();
                if(user.equals(userg)&&pass.equals(passg)){
                            Intent mhqlv = new Intent(MainActivity.this,MainQLKV.class);
                            startActivity(mhqlv);
                }else if (user.equalsIgnoreCase(userg)||pass.equalsIgnoreCase(passg)){
                    Toast.makeText(MainActivity.this, "Sai tai khoảng hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        Intent mhqlv = new Intent(MainActivity.this,MainQLKV.class);
//        startActivity(mhqlv);
    
    }

    private void funTaoTK() {
        Intent mhttk = new Intent(MainActivity.this,MainDKTK.class);
        startActivity(mhttk);
    }

}