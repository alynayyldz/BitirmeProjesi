package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminGiris extends AppCompatActivity {

    private Button giris;
    private ImageView adminIcon;
    private EditText adminMail, adminSifre;
    private TextView unuttum;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_giris);

        giris = (Button) findViewById(R.id.giris);
        adminIcon = (ImageView) findViewById(R.id.adminIcon);
        adminMail = (EditText) findViewById(R.id.adminMail);
        adminSifre = (EditText) findViewById(R.id.adminSifre);
        unuttum = (TextView) findViewById(R.id.unuttum);

        auth = FirebaseAuth.getInstance();

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.girisyap:
                adminGiris();
                break;
            case R.id.unuttum:
                startActivity(new Intent(this, SifremiUnuttum.class));
                break;
        }
    }

    private void adminGiris() {
        String email = adminMail.getText().toString().trim();
        String sifre = adminSifre.getText().toString().trim();

        if (email.isEmpty()) {
            adminMail.setError("E-mail kısmı boş olamaz!");
            adminMail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            adminMail.setError("E-mail formatına dikkat ediniz!");
            adminMail.requestFocus();
            return;

        }
        if (sifre.isEmpty()) {
            adminSifre.setError("Şifre kısmı boş olamaz!");
            adminSifre.requestFocus();
            return;
        }
        if (sifre.length() < 6) {
            adminSifre.setError("Şifre minimum 6 karakterli olmalı!");
            adminSifre.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email, sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser admin= FirebaseAuth.getInstance().getCurrentUser();
                    if(admin.isEmailVerified()) {
                        startActivity(new Intent(AdminGiris.this,AdminPanel.class));
                    } else {
                        admin.sendEmailVerification();
                        Toast.makeText(AdminGiris.this,"E-mailinizi onaylayınız.",Toast.LENGTH_SHORT);
                    }
                } else {
                    Toast.makeText(AdminGiris.this,"Giriş başarısız oldu. Lütfen bilgilerinizi kontrol ediniz.",Toast.LENGTH_SHORT);
                }
            }
        });
    }
}