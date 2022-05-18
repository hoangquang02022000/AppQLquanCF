package com.hoangquangdev;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hoangquangdev.Model.Ban;
import com.hoangquangdev.Model.NhanVien;
import com.hoangquangdev.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main_Login extends Activity {


    Button btnLogin ,btnTaoTK, btnquenMK,btn_singup ;
    EditText etxtTaiKkhan , etxtMatKhau,etxt_userShop_;
    EditText etxt_email,etxt_passdk,etxt_phone,etxt_fullname,etxt_namqua,etxt_userShop;
    ProgressBar progressBar, progressBar_login ;
    CheckBox check_NV;
    ImageButton show , unshow;



    DatabaseReference mData;
    private FirebaseAuth auth;

    User user = new User();
    ArrayList<User>  ds_user ;
    ArrayList<NhanVien> ds_userNv = new ArrayList<>();
    String thongtinlhu = "tk_mk keySho login";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        mData = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Main_Login.this, MainQLKV.class));
            finish();
        }

        // set the view now
        setContentView(R.layout.activity_login);


        addcontroll();
        addevent();
//        getdata();

    }

    private void addevent() {
//        check_NV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if (check_NV.isChecked()==true){
//                    etxt_userShop_.setFocusableInTouchMode(true);
//                }else {
//
//                    etxt_userShop_.setFocusable(false);
//                }
//            }
//        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etxtTaiKkhan.getText().toString();
                String password = etxtMatKhau.getText().toString();
//                String userShop = etxt_userShop_.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

//                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                progressBar_login.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email, password)

                        .addOnCompleteListener(Main_Login.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
//                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        etxtMatKhau.setError(
//                                                getString(R.string.minimum_password)
                                                "Wrong password !"
                                        );
                                    } else {
                                        Toast.makeText(Main_Login.this,
//                                                getString(R.string.auth_failed)
                                                "Kết nối mạng không ổn định !"
                                                , Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                            progressBar_login.setVisibility(View.GONE);
                                            SharedPreferences sharedPreferences = getSharedPreferences(thongtinlhu,MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("email",email);
                                            editor.putString("password",password);

                                            String shop ="";
                                            String nameShop="";
                                            String nameUser="";
                                            int q = 0 ;
                                            for (int i = 0 ; i <ds_user.size();i++){
                                                if (ds_user.get(i).getEmail().equalsIgnoreCase(email)){
                                                    shop = ds_user.get(i).getUserShop();
                                                    q = ds_user.get(i).getQuyen();
                                                }
                                            }
                                            for (int i = 0 ; i <ds_user.size();i++){
                                                if (ds_user.get(i).getEmail().equalsIgnoreCase(email)){
                                                    nameShop = ds_user.get(i).getNameShop();
                                                    q = ds_user.get(i).getQuyen();
                                                }
                                            }
                                            for (int i = 0 ; i <ds_user.size();i++){
                                                if (ds_user.get(i).getEmail().equalsIgnoreCase(email)){
                                                    nameUser = ds_user.get(i).getNameUser();
                                                    q = ds_user.get(i).getQuyen();
                                                }
                                            }
                                            editor.putString("nameShop",nameShop);
                                            editor.putString("nameUser",nameUser);
                                            editor.putInt("quyen",q);
                                            editor.putString("userShop",shop);

                                            editor.commit();
                                            Intent intent = new Intent(Main_Login.this, MainQLKV.class);
                                            startActivity(intent);
                                            finish();

                                        }
                            }
                        });
            }
        });
        btnTaoTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getdata();
//
                open_dialog(Gravity.CENTER);

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unshow.setVisibility(View.VISIBLE);
                show.setVisibility(view.GONE);
            }
        });
        btnquenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open_dialog_laylaimk(Gravity.CENTER);
            }
        });
    }


    private void addcontroll() {
        btnLogin =  findViewById(R.id.btn_Login);
        btnTaoTK = findViewById(R.id.bt_dkTaiKhan);
        btnquenMK =  findViewById(R.id.bt_quenMK);
        etxtTaiKkhan = findViewById(R.id.etxt_TaiKhoan);
        etxtMatKhau =  findViewById(R.id.etxt_MatKhau);
//        etxt_userShop_ = findViewById(R.id.etxt_userShop_);
//        check_NV = findViewById(R.id.chek_NhanVien);
        progressBar_login = findViewById(R.id.progressBar_login);
        show = findViewById(R.id.ibtn_show);
        unshow = findViewById(R.id.ibtn_unshow);


        ds_user = new ArrayList<>();


    }

    private ArrayList<User> getdata() {
        ArrayList<User> temp = new ArrayList<>();
        mData.child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                user = snapshot.getValue(User.class);
                temp.add(user);
                System.out.println("-------------"+ds_user.toString());

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
        return temp;
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
        progressBar = dialog.findViewById(R.id.progressBar);
        etxt_userShop = dialog.findViewById(R.id.etxt_userShop);
        dialog.show();

        btn_singup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = etxt_email.getText().toString().trim();
                String password =etxt_passdk.getText().toString().trim();
                String phone = etxt_phone.getText().toString().trim();
                String fullname = etxt_fullname.getText().toString().trim();
                String namquan = etxt_namqua.getText().toString().trim();
                String userShop = etxt_userShop.getText().toString().trim();
                auth = FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;


                }
                if (ds_user.size()==0){
                    progressBar.setVisibility(View.VISIBLE);
                    //create user
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Main_Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(Main_Login.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Main_Login.this, "Authentication failed." + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    } else {
//                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                        singup();
                                        dialog.dismiss();
                                    }
                                }
                            });
                }
                for (int i =0 ; i<ds_user.size();i++){
                    if (ds_user.get(i).getUserShop().equalsIgnoreCase(userShop)){
                        Toast.makeText(getApplicationContext(), "User Shop already exists !", Toast.LENGTH_SHORT).show();
                        break;


                    }else {


                        progressBar.setVisibility(View.VISIBLE);
                        //create user
                        auth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(Main_Login.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(Main_Login.this, "" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(Main_Login.this, "" + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
//                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                            singup();
                                            dialog.dismiss();
                                        }
                                    }
                                });
                    }

                }



            }
        });


    }
    private void singup(){

        String email = etxt_email.getText().toString().trim();
        String password =etxt_passdk.getText().toString().trim();
        String phone = etxt_phone.getText().toString().trim();
        String fullname = etxt_fullname.getText().toString().trim();
        String namquan = etxt_namqua.getText().toString().trim();
        final String userShop = etxt_userShop.getText().toString().trim();
        int q = 0;
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
         String id = String.valueOf(date);
        User use = new User(email,fullname,namquan,phone,email,password,q,userShop);
        mData.child("User").child(fullname + id).setValue(use);
        Ban ban = new Ban();
        NhanVien nhanVien = new NhanVien();
//        creat_data(userShop);


    }

    private void open_dialog_laylaimk(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_password_retrieval);

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
        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ds_user = getdata();
    }
}
