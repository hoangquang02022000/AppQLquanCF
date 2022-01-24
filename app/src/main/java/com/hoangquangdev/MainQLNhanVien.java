package com.hoangquangdev;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hoangquangdev.Adapter.Nhanvien_Adapter;
import com.hoangquangdev.Model.NhanVien;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainQLNhanVien extends Activity {
    ImageButton ibtn_add_nv,ibtn_addnv_thoat,ibtn_order_thoat,txt_nv_thoat;
    ImageView img_addNV,img_dialog_nv,ibtn_ttnv_thoat,img_dialog_nv_edit;
    EditText txt_add_tenNV,txt_add_ns,txt_add_sdt,txt_add_email,txt_add_diachi,txt_add_tk,txt_add_mk
            ,etxt_tenNV,etxt_namSinh,etxt_gioiTinh,etxt_diachi,etxt_phone,etxt_email,etxt_chuVu,etxt_tk,etxt_pass;
    RadioButton rb_quanLy,rb_nhaVien,rb_nam,rb_nu;
    Button btn_add_nv,btn_del,btn_yes,btn_no,btn_sua,btn_Sua_;
    SearchView search;
    ListView listView;
    TextView txt_tenNV,txt_namSinh,txt_gioiTinh,txt_diachi,txt_phone,txt_email,txt_chuVu,txt_tk,txt_pass
            ,txt_thongbao;
    String id;
    String id1;
    String img;


    public Uri imgUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String ten;
    String cv,gt,ns,sdt,email,dc,tk,mk;

    NhanVien nhanVien = new NhanVien();
    List<NhanVien> ds_NhanViens = new ArrayList<>();
    Nhanvien_Adapter adapter_nv  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_nhan_vien);
        show();
        addcontroll();
        addevent();




    }

    private void addevent() {
        txt_nv_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ibtn_add_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog_addNV(Gravity.BOTTOM);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                opendialog_thongtin(Gravity.BOTTOM);
                id1 = ds_NhanViens.get(position).getIdnhanVien();
                Picasso.get().load(ds_NhanViens.get(position).getImg()).into(img_dialog_nv);
                txt_tenNV.setText(ds_NhanViens.get(position).getTenNV());
                txt_namSinh.setText(ds_NhanViens.get(position).getNsinh());
                txt_gioiTinh.setText(ds_NhanViens.get(position).getGtinh());
                txt_diachi.setText(ds_NhanViens.get(position).getDchi());
                txt_phone.setText(ds_NhanViens.get(position).getPhone());
                txt_email.setText(ds_NhanViens.get(position).getEmail());
                txt_chuVu.setText(ds_NhanViens.get(position).getChucvu());
                txt_tk.setText(ds_NhanViens.get(position).getTaiKhoan());
                txt_pass.setText(ds_NhanViens.get(position).getMatKhau());



            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter_nv.getFilter().filter(s);
                return false;
            }
        });
    }

    private void addcontroll() {
        ibtn_add_nv = findViewById(R.id.ibtn_add_nv);
        listView = findViewById(R.id.lv_nhanVien);
        txt_nv_thoat = findViewById(R.id.txt_nv_thoat);
        search = findViewById(R.id.search);
    }
    public void show(){
        mData.child("NhanVien").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    nhanVien = snapshot.getValue(NhanVien.class);
                    ds_NhanViens.add(nhanVien);

                adapter_nv = new Nhanvien_Adapter(MainQLNhanVien.this,R.layout.item_nhanvien,ds_NhanViens);
                listView.setAdapter(adapter_nv);
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
    private void opendialog_addNV(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_nv);

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

        img_addNV = dialog.findViewById(R.id.img_addNV);
        ibtn_addnv_thoat = dialog.findViewById(R.id.ibtn_addnv_thoat);
        txt_add_tenNV = dialog.findViewById(R.id.txt_add_tenNV);
        rb_quanLy = dialog.findViewById(R.id.rb_quanLy);
        rb_nhaVien = dialog.findViewById(R.id.rb_nhaVien);
        rb_nam = dialog.findViewById(R.id.rb_nam);
        rb_nu = dialog.findViewById(R.id.rb_nu);
        txt_add_ns = dialog.findViewById(R.id.txt_add_ns);
        txt_add_sdt = dialog.findViewById(R.id.txt_add_sdt);
        txt_add_email = dialog.findViewById(R.id.txt_add_email);
        txt_add_diachi = dialog.findViewById(R.id.txt_add_diachi);
        txt_add_tk= dialog.findViewById(R.id.txt_add_tk);
        txt_add_mk = dialog.findViewById(R.id.txt_add_mk);
        btn_add_nv = dialog.findViewById(R.id.btn_add_nv);
        ibtn_addnv_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        img_addNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cloosePicture();
            }

            private void cloosePicture() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);

            }
        });

        btn_add_nv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                ten = txt_add_tenNV.getText().toString().trim();

                if (rb_quanLy.isChecked()){
                    cv = "Quản Lý";
                }
                else if (rb_nhaVien.isChecked()){
                    cv = "Nhan Viên";
                }

                if (rb_nam.isChecked()){
                    gt = "Nam";
                }
                else if (rb_nu.isChecked()){
                    gt = "Nữ";
                }
                ns = txt_add_ns.getText().toString().trim();
                sdt = txt_add_sdt.getText().toString().trim();
                email = txt_add_email.getText().toString().trim();
                dc = txt_add_diachi.getText().toString().trim();
                tk = txt_add_tk.getText().toString().trim();
                mk = txt_add_mk.getText().toString().trim();

                uploadPicture();


            }
        });
        dialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imgUri= data.getData();
            img_addNV.setImageURI(imgUri);

        }
    }
    private void uploadPicture() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang Thêm ....");
        progressDialog.show();
        StorageReference mountainsRef = storageReference.child("images_nv/"+txt_add_tenNV.getText().toString());
        mountainsRef.putFile(imgUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        mountainsRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                long millis=System.currentTimeMillis();
                                java.sql.Date date=new java.sql.Date(millis);
                                id = String.valueOf(date);

                                NhanVien nhanVien = new NhanVien(id+ten.substring(ten.lastIndexOf(' ') + 1),ten,cv,ns,gt,sdt,email,dc,tk,mk,String.valueOf(uri));
                                mData.child("NhanVien").child(id+ten.substring(ten.lastIndexOf(' ') + 1)).setValue(nhanVien);
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
//                                System.out.println("-----------"+cv+ns+gt+email+dc+tk+mk);
                            }
                        });

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Lỗi Thêm ",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPencent = (100.000*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount()) ;
                        progressDialog.setMessage("Percentage: "+(int)progressPencent+"%");
                    }
                });

    }
    private void opendialog_thongtin(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thong_tin_nv);

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
        img_dialog_nv = dialog.findViewById(R.id.img_dialog_nv);
        txt_tenNV = dialog.findViewById(R.id.txt_tenNV);
        txt_namSinh=dialog.findViewById(R.id.txt_namSinh);
        txt_gioiTinh =dialog.findViewById(R.id.txt_gioiTinh);
        txt_diachi = dialog.findViewById(R.id.txt_diachi);
        txt_phone = dialog.findViewById(R.id.txt_phone);
        txt_email = dialog.findViewById(R.id.txt_email);
        txt_chuVu = dialog.findViewById(R.id.txt_chuVu);
        txt_tk = dialog.findViewById(R.id.txt_tk);
        txt_pass = dialog.findViewById(R.id.txt_pass);
        ibtn_order_thoat = dialog.findViewById(R.id.ibtn_order_thoat);
        btn_del = dialog.findViewById(R.id.btn_del);
        btn_sua = dialog.findViewById(R.id.btn_sua);
        ibtn_order_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog_thongbao(Gravity.CENTER);
                dialog.dismiss();

            }
        });
        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                opendialog_suathongtin(Gravity.BOTTOM);
                int i ;
               for (i= 0 ; i < ds_NhanViens.size();i++){
                    if (ds_NhanViens.get(i).getIdnhanVien()==id1){
                        img = ds_NhanViens.get(i).getImg();
                        etxt_tenNV.setText(ds_NhanViens.get(i).getTenNV());
                        etxt_chuVu.setText(ds_NhanViens.get(i).getChucvu());
                        etxt_diachi.setText(ds_NhanViens.get(i).getDchi());
                        etxt_email.setText(ds_NhanViens.get(i).getEmail());
                        etxt_gioiTinh.setText(ds_NhanViens.get(i).getGtinh());
                        etxt_namSinh.setText(ds_NhanViens.get(i).getNsinh());
                        etxt_phone.setText(ds_NhanViens.get(i).getPhone());
                        etxt_tk.setText(ds_NhanViens.get(i).getTaiKhoan());
                        etxt_pass.setText(ds_NhanViens.get(i).getMatKhau());
                        Picasso.get().load(ds_NhanViens.get(i).getImg()).into(img_dialog_nv_edit);
                    }
               }
            }
        });

        dialog.show();
    }

    public  void  del(){ ;
        mData.child("NhanVien").orderByChild("idnhanVien").equalTo(id1)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            ds.getRef().removeValue();

                            Toast.makeText(MainQLNhanVien.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            adapter_nv.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(MainQLNhanVien.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void opendialog_thongbao(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thongbao);

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
        btn_yes = dialog.findViewById(R.id.btn_yes);
        btn_no = dialog.findViewById(R.id.btn_no);
        txt_thongbao = dialog.findViewById(R.id.txt_thongbao);
        txt_thongbao.setText("Bạn có muốn xóa nhân viên này !");
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
                dialog.dismiss();
                int i;
                for (i=0;i<=ds_NhanViens.size();i++){
                    if (ds_NhanViens.get(i).getIdnhanVien()==id1){
                        ds_NhanViens.remove(i);
                    }
                }
            }
        });
        dialog.show();
    }
    private void opendialog_suathongtin(int gravity) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua_nv);

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
        etxt_tenNV = dialog.findViewById(R.id.etxt_tenNV);
        etxt_namSinh= dialog.findViewById(R.id.etxt_namSinh);
        etxt_gioiTinh =  dialog.findViewById(R.id.etxt_gioiTinh);
        etxt_diachi = dialog.findViewById(R.id.etxt_diachi);
        etxt_phone = dialog.findViewById(R.id.etxt_phone);
        etxt_email= dialog.findViewById(R.id.etxt_email);
        etxt_chuVu= dialog.findViewById(R.id.etxt_chuVu);
        etxt_tk = dialog.findViewById(R.id.etxt_tk);
        etxt_pass= dialog.findViewById(R.id.etxt_pass);
        btn_Sua_ = dialog.findViewById(R.id.btn_Sua_);
        ibtn_ttnv_thoat = dialog.findViewById(R.id.ibtn_ttnv_thoat);
        img_dialog_nv_edit = dialog.findViewById(R.id.img_dialog_nv_edit);
        ibtn_ttnv_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }

        });
        btn_Sua_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NhanVien nhanVien = new NhanVien(id,ten,cv,ns,gt,sdt,email,dc,tk,mk,String.valueOf(uri));
                NhanVien nvs = new NhanVien(id1,etxt_tenNV.getText().toString(),etxt_chuVu.getText().toString(),etxt_namSinh.getText().toString(),
                        etxt_gioiTinh.getText().toString(),etxt_phone.getText().toString(),etxt_email.getText().toString(),etxt_diachi.getText().toString(),
                        etxt_tk.getText().toString(),etxt_pass.getText().toString(),img);
                mData.child("NhanVien").child(id1).setValue(nvs);
                Toast.makeText(getApplicationContext(),"Cập nhật thông tin thành công !",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }


}