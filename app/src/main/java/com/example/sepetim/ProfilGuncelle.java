package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ProfilGuncelle extends AppCompatActivity {

    DatabaseReference databaseReference;
    Button btnGuncelle;
    EditText isim, numara, mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_guncelle);

        isim = (EditText) findViewById(R.id.isim);
        numara = (EditText) findViewById(R.id.numara);
        mail = (EditText) findViewById(R.id.mail);
        btnGuncelle = (Button) findViewById(R.id.btnGuncelle);

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad = isim.getText().toString();
                String no = numara.getText().toString();
                String eMail = mail.getText().toString();

                guncelle(ad, no, eMail);
            }
        });
    }

    private void guncelle(String isim, String numara, String mail) {
        HashMap User = new HashMap();
        User.put("Ad", isim);
        User.put("Numara", numara);
        User.put("Mail", mail);
        databaseReference = FirebaseDatabase.getInstance().getReference("Kullanıcılar");
        databaseReference.child(isim).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()) {
                    Toast.makeText(ProfilGuncelle.this, "Bilgiler başarıyla güncellendi.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfilGuncelle.this, "Güncelleme başarısız!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}