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
import com.hoangquangdev.Adapter.QLSP_Adapter;
import com.hoangquangdev.Model.SanPham;

import java.util.ArrayList;

public class MainQLSP extends Activity {

    ImageButton  ibtn_add,ibn_Del;
    RadioButton r_c,r_t,r_d;
    int r=0;
    ImageView img_anh,ibtn_trove;
    EditText etxt_tenSp,etxt_giaSP;
    SearchView etxt_nhapSP;
    Button btn_add,btn_yes,btn_no;
    ListView lv_SP;
    TextView txt_thongbao;
    ArrayList<SanPham>dsSanPhams=new ArrayList<>();
    QLSP_Adapter adapter;

    public Uri imgUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    String id="" ;
    String id_del="";
    String nameSP="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsp);
        addctroll();
        showSP();
        addevent();
        adapter = new QLSP_Adapter(MainQLSP.this, R.layout.itemlistviewsanpham, dsSanPhams);
        adapter.notifyDataSetChanged();
    }
    public void showSP(){
        mData.child("SanPham").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPham sanPham = snapshot.getValue(SanPham.class);
                dsSanPhams.add(sanPham);
//                adapter = new QLSP_Adapter(MainQLSP.this, R.layout.itemlistviewsanpham, dsSanPhams);
                lv_SP.setAdapter(adapter);
                adapter.notifyDataSetChanged();

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

    private void addevent() {
        ibtn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaddSanPham(Gravity.CENTER);
            }
        });
        // chua fix dc
        etxt_nhapSP.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        ibtn_trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_SP.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                id_del = dsSanPhams.get(position).getMaSP();
                nameSP =  dsSanPhams.get(position).getTenSP().toString();
                opendialog_thongbao(Gravity.CENTER);
                return false;
            }
        });

    }


    private void addctroll() {
        ibtn_add = findViewById(R.id.ibtn_QLSP_addSp);
        lv_SP = findViewById(R.id.lv_SP);
        etxt_nhapSP = findViewById(R.id.etxt_QLSP_nhapSP);
        ibtn_trove = findViewById(R.id.ibtn_trove);

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
        etxt_tenSp=dialog.findViewById(R.id.etxt_addSP_tenSP);
        etxt_giaSP=dialog.findViewById(R.id.etxt_addSP_giaSP);
        r_c = dialog.findViewById(R.id.rdio_AddSP_Coffee);
        r_d = dialog.findViewById(R.id.rdio_AddSP_drink);
        r_t = dialog.findViewById(R.id.rdio_Addsp_teamilk);
        img_anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                if(r_c.isChecked()){
                    r =0;
                }
                 else if(r_t.isChecked()){
                    r = 2;
                }else if (r_d.isChecked()){
                    r=1;
                }
                cloosePicture();
            }

            private void cloosePicture() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
           img_anh.setImageURI(imgUri);

        }
    }

    private void uploadPicture() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đang Thêm ảnh ....");
        progressDialog.show();
        StorageReference mountainsRef = storageReference.child("images/"+etxt_tenSp.getText().toString());
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
                        SanPham sanPham =new SanPham(id+etxt_tenSp.getText().toString(),etxt_tenSp.getText().toString(),r
                                ,Double.valueOf(etxt_giaSP.getText().toString()),String.valueOf(uri));
                        mData.child("SanPham").child(id+etxt_tenSp.getText().toString()).setValue(sanPham);
                        progressDialog.dismiss();
//                Snackbar.make(findViewById(R.id.center),"Image Uploaded.",Snackbar.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(),"Thêm Thành Công",Toast.LENGTH_LONG).show();
                    }
                });

            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Lỗi Thêm ảnh",Toast.LENGTH_LONG).show();
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
        txt_thongbao = dialog .findViewById(R.id.txt_thongbao);
        txt_thongbao.setText("Bạn Có Muốn Xóa "+nameSP);
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
                    }
                }
        );
        dialog.show();
    }

    public void del(){
        mData.child("SanPham").orderByChild("maSP").equalTo(id_del)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot ds : dataSnapshot.getChildren())
                        {
                            ds.getRef().removeValue();

                            Toast.makeText(MainQLSP.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                            for (int i =0 ; i<dsSanPhams.size();i++){
                                if (dsSanPhams.get(i).getMaSP().equals(id_del)){
                                    dsSanPhams.remove(i);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError)
                    {
                        Toast.makeText(MainQLSP.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}