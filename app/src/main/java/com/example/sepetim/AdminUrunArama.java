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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AdminUrunArama extends AppCompatActivity {

    private EditText barkod;
    private TextView urunAd, urunAgirlik, urunFiyat, urunKategori, urunTarih, urunMiktar;
    private Button urunAra, sil, guncelle;
    private ImageButton btnGeri;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_urun_arama);

        barkod=(EditText) findViewById(R.id.barkod);

        urunAd=(TextView) findViewById(R.id.urunAd);
        urunAgirlik=(TextView) findViewById(R.id.urunAgirlik);
        urunFiyat=(TextView) findViewById(R.id.urunFiyat);
        urunKategori=(TextView) findViewById(R.id.urunKategori);
        urunTarih=(TextView) findViewById(R.id.urunTarih);
        urunMiktar=(TextView) findViewById(R.id.urunMiktar);

        urunAra=(Button) findViewById(R.id.urunAra);
        sil=(Button) findViewById(R.id.sil);
        guncelle=(Button) findViewById(R.id.guncelle);

        btnGeri=(ImageButton) findViewById(R.id.btnGeri);

        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminUrunArama.this,AdminPanel.class);
                startActivity(i);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Kullanıcılar");
        reference.child(firebaseAuth.getUid()).child("Urunler")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user=snapshot.getValue(User.class);

                        if(barkod!=null) {
                            String ad=user.ad;
                            urunAd.setText(ad);

                            //String agirlik=
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}