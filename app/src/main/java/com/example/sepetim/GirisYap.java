package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.ResultSet;
import java.sql.Statement;

public class GirisYap extends AppCompatActivity implements View.OnClickListener{
    private TextView unuttum;
    private Button girisyap;
    private EditText editGirisMail ,editGirisSifre;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);

        unuttum =(TextView) findViewById(R.id.unuttum);
        unuttum.setOnClickListener(this);

        girisyap =(Button) findViewById(R.id.girisyap);
        girisyap.setOnClickListener(this);

        editGirisMail =(EditText) findViewById(R.id.editGirisMail);
        editGirisSifre =(EditText) findViewById(R.id.editGirisSifre);

        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.girisyap:
                userLogin();
                break;
            case R.id.unuttum:
                startActivity(new Intent(this, SifremiUnuttum.class));
                break;
        }
    }

    private void userLogin() {
        String email = editGirisMail.getText().toString().trim();
        String sifre = editGirisSifre.getText().toString().trim();

        if(email.isEmpty()){
            editGirisMail.setError("Email kısmı boş olamaz !");
            editGirisMail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editGirisMail.setError("Email formatına dikkat ediniz !");
            editGirisMail.requestFocus();
            return;

        }
        if(sifre.isEmpty()){
            editGirisSifre.setError("Şifre kısmı boş olamaz !");
            editGirisSifre.requestFocus();
            return;
        }
        if(sifre.length() < 6 ){
            editGirisSifre.setError("Şifre Minimum 6 karakterli olmalı !");
            editGirisSifre.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email , sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if (email.equals("alynayyldz@gmail.com")) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user.isEmailVerified()) {
                                startActivity(new Intent(GirisYap.this, AdminPanel.class));
                            } else {
                                user.sendEmailVerification();
                                Toast.makeText(GirisYap.this, "", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(GirisYap.this, "Giriş Başarısız Bilgileri Kontrol Ediniz !! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                            if (user.isEmailVerified()) {
                                startActivity(new Intent(GirisYap.this, AnaSayfa.class));
                            } else {
                                user.sendEmailVerification();
                                Toast.makeText(GirisYap.this, "Email Onayı İçin E-mailiniz Kontrol Ediniz ! ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(GirisYap.this, "Giriş Başarısız Bilgileri Kontrol Ediniz !! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        });
    }
}

