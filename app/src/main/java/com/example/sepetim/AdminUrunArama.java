package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminUrunArama extends AppCompatActivity {

    private EditText arama;
    private TextView metin;
    private ImageButton filtre,btnGeri;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private ArrayList<ModelUrunler> urunlerArrayList;
    private AdaptorUrunler adaptorUrunler;
    private RecyclerView urunlerRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_urun_arama);

        arama=(EditText) findViewById(R.id.arama);
        metin=(TextView) findViewById(R.id.metin);
        filtre=(ImageButton) findViewById(R.id.filtre);
        btnGeri=(ImageButton) findViewById(R.id.btnGeri);
        urunlerRv=(RecyclerView) findViewById(R.id.urunlerRv);


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Lütfen bekleyin...");
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth=FirebaseAuth.getInstance();

        //tumUrunleriYukle();

        arama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    adaptorUrunler.getFilter().filter(s);
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        filtre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(AdminUrunArama.this);
                builder.setTitle("Kategori Seçiniz:")
                        .setItems(Icerik.urun_kategori2, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String secim=Icerik.urun_kategori2[which];
                                if(secim.equals("Hepsi")) {
                                    tumUrunleriYukle();
                                }
                                else {
                                    // seçimi yükle
                                   secilenUrunleriYukle(secim);
                                }
                            }
                        })
                .show();
            }
        });

        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminUrunArama.this,AdminPanel.class);
                startActivity(intent);
            }
        });

    }

    private void secilenUrunleriYukle(String secim) {
        urunlerArrayList=new ArrayList<>();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Kullanıcılar");
        reference.child(firebaseAuth.getUid()).child("Urunler")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        urunlerArrayList.clear();
                        for (DataSnapshot ds:snapshot.getChildren()) {

                            String urunKategori=""+ds.child("kategori").getValue();

                            if(secim.equals(urunKategori)) {
                                ModelUrunler modelUrunler=ds.getValue(ModelUrunler.class);
                                urunlerArrayList.add(modelUrunler);
                            }
                        }
                        adaptorUrunler=new AdaptorUrunler(AdminUrunArama.this,urunlerArrayList);
                        urunlerRv.setAdapter(adaptorUrunler);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void tumUrunleriYukle() {
        urunlerArrayList=new ArrayList<>();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Kullanıcılar");
        reference.child(firebaseAuth.getUid()).child("Urunler")
        .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                urunlerArrayList.clear();
                for (DataSnapshot ds:snapshot.getChildren()) {
                    ModelUrunler modelUrunler=ds.getValue(ModelUrunler.class);
                    urunlerArrayList.add(modelUrunler);
                }
                adaptorUrunler=new AdaptorUrunler(AdminUrunArama.this,urunlerArrayList);
                urunlerRv.setAdapter(adaptorUrunler);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}