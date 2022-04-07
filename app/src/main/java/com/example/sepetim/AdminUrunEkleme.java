package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AdminUrunEkleme extends AppCompatActivity {
    EditText barkod,agirlik,fiyat,tarih,ad,miktar;
    TextView kategori;
    Button ekle;
    ImageButton btnGeri;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_urun_ekleme);

        barkod=(EditText) findViewById(R.id.barkod);
        agirlik=(EditText) findViewById(R.id.agirlik);
        fiyat=(EditText) findViewById(R.id.fiyat);
        tarih=(EditText) findViewById(R.id.tarih);
        ad=(EditText) findViewById(R.id.ad);
        miktar=(EditText) findViewById(R.id.miktar);

        ekle=(Button) findViewById(R.id.ekle);
        btnGeri=(ImageButton) findViewById(R.id.btnGeri);

        kategori=(TextView) findViewById(R.id.kategori);

        firebaseAuth= FirebaseAuth.getInstance();

        //setup progress dialog
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Lütfen Bekleyiniz.");
        progressDialog.setCanceledOnTouchOutside(false);

        kategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryDiaolog();
            }
        });

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData();
            }
        });

        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminUrunEkleme.this,AdminPanel.class);
                startActivity(intent);
            }
        });
    }

    private String urunBarkod, urunAgirlik, urunKategori, urunFiyat, urunTarih, urunAd, urunMiktar;
    private void inputData() {
        urunBarkod=barkod.getText().toString().trim();
        urunAgirlik=agirlik.getText().toString().trim();
        urunKategori=kategori.getText().toString().trim();
        urunFiyat=fiyat.getText().toString().trim();
        urunTarih=tarih.getText().toString().trim();
        urunAd=ad.getText().toString().trim();
        urunMiktar=miktar.getText().toString().trim();

        if(TextUtils.isEmpty((urunBarkod))) {
            barkod.setError("Barkod kısmı boş bırakılamaz.");
            barkod.requestFocus();
            return;
        }
        if(barkod.length()<13) {
            barkod.setError("Barkod 13 haneli bir sayı olmalıdır.");
        }
        if(TextUtils.isEmpty((urunAgirlik))) {
            agirlik.setError("Ağırlık alanı boş bırakılamaz.");
            agirlik.requestFocus();
            return;
        }
        if(TextUtils.isEmpty((urunKategori))) {
            kategori.setError("Kategori alanı boş bırakılamaz.");
            kategori.requestFocus();
            return;
        }
        if(TextUtils.isEmpty((urunFiyat))) {
            fiyat.setError("Fiyat alanı boş bırakılamaz.");
            fiyat.requestFocus();
            return;
        }
        if(TextUtils.isEmpty((urunTarih))) {
            tarih.setError("Tarih alanı boş bırakılamaz.");
            tarih.requestFocus();
            return;
        }
        if(TextUtils.isEmpty((urunAd))) {
            ad.setError("Ad alanı boş bırakılamaz.");
            ad.requestFocus();
            return;
        }
        if(TextUtils.isEmpty((urunMiktar))) {
            miktar.setError("Miktar alanı boş bırakılamaz.");
            miktar.requestFocus();
            return;
        }
        urunEkle();
    }

    private void urunEkle() {
        //Veri tabanına ürün ekleme
        progressDialog.setMessage("Ürün eklendi.");
        progressDialog.show();

        String timestamp=""+System.currentTimeMillis();

        HashMap <String,Object> hashMap=new HashMap<>();
        hashMap.put("urunId",""+timestamp);
        hashMap.put("urunBarkod",""+urunBarkod);
        hashMap.put("urunAgirlik",""+urunAgirlik);
        hashMap.put("urunKategori",""+urunKategori);
        hashMap.put("urunFiyat",""+urunFiyat);
        hashMap.put("urunTarih",""+urunTarih);
        hashMap.put("urunAd",""+urunAd);
        hashMap.put("urunMiktar",""+urunMiktar);
        hashMap.put("uid",""+firebaseAuth.getUid());

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Kullanıcılar");
        reference.child(firebaseAuth.getUid()).child("Urunler").child(timestamp).setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        Toast.makeText(AdminUrunEkleme.this,"Ürün Ekleniyor...",Toast.LENGTH_SHORT).show();
                        veriyiTemizle();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(AdminUrunEkleme.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void veriyiTemizle() {
        barkod.setText("");
        agirlik.setText("");
        fiyat.setText("");
        tarih.setText("");
        ad.setText("");
        miktar.setText("");
        kategori.setText("");
    }

    private void categoryDiaolog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Ürün Kategorisi")
                .setItems(Icerik.urun_kategori, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String category=Icerik.urun_kategori[which];
                        kategori.setText(category);
                    }
                }).show();
    }
}