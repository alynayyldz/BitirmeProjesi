package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KullaniciIslemleri extends AppCompatActivity {

    TextView isim2,numara2,mail2,text;
    Button guncelle,sil;
    ImageButton btnGeri;

    private FirebaseUser user;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_islemleri);

        isim2=(TextView) findViewById(R.id.isim2);
        numara2=(TextView) findViewById(R.id.numara2);
        mail2=(TextView) findViewById(R.id.mail2);
        guncelle=(Button) findViewById(R.id.guncelle);
        btnGeri=(ImageButton) findViewById(R.id.btnGeri);
        sil=(Button) findViewById(R.id.sil);

        btnGeri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KullaniciIslemleri.this,ProfilFragment.class);
                startActivity(intent);
            }
        });

        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KullaniciIslemleri.this,ProfilGuncelle.class);
                startActivity(intent);
            }
        });

        sil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //normalAlertDialog(v);
            }
        });
    }
   /* public void normalAlertDialog(View v) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Hesabımı Sil");
        alert.setMessage("Hesabınızı silmek istediğinize emin misiniz?");
        alert.setCancelable(false); //kapanmasını engeller.
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reference = FirebaseDatabase.getInstance().getReference("Kullanıcılar");
                reference.removeValue();
                Intent i=new Intent(KullaniciIslemleri.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(),"Hesabınız silindi.", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }*/
}