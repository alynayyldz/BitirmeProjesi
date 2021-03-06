package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    TextView isim2,numara2,mail2;
    Button guncelle;


    private FirebaseUser user;
    private DatabaseReference reference;
    private String kullaniciId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_islemleri);

        isim2=(TextView) findViewById(R.id.isim2);
        numara2=(TextView) findViewById(R.id.numara2);
        mail2=(TextView) findViewById(R.id.mail2);
        guncelle=(Button) findViewById(R.id.guncelle);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Kullanıcılar");
        kullaniciId =user.getUid();

        reference.child(kullaniciId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    String ad = userProfile.ad;
                    isim2.setText(ad);

                    String no=userProfile.telefon;
                    numara2.setText(no);

                    String eMail=userProfile.email;
                    mail2.setText(eMail);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(KullaniciIslemleri.this,"Bir şeyler ters gitti.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KullaniciIslemleri.this,ProfilGuncelleme.class);
                startActivity(intent);
            }
        });


    }
}