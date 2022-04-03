package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;


public class KayitOl extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;

    private EditText editAd, editTelefon, editEmail,editSifre;
    private Button kayitOl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        mAuth = FirebaseAuth.getInstance();

        kayitOl = (Button) findViewById(R.id.kayitOl);
        kayitOl.setOnClickListener(this);

        editAd = (EditText) findViewById(R.id.editAd);
        editTelefon = (EditText) findViewById(R.id.editTelefon);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSifre = (EditText) findViewById(R.id.editSifre);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.kayitOl:
                kayitOl();
                break;
        }
    }
    private void kayitOl() {
        String email = editEmail.getText().toString().trim();
        String sifre = editSifre.getText().toString().trim();
        String ad = editAd.getText().toString().trim();
        String tel =editTelefon.getText().toString().trim();

        if(ad.isEmpty()){
            editAd.setError("Ad kısmı boş olamaz !");
            editAd.requestFocus();
            return;
        }
        if(tel.isEmpty()){
            editTelefon.setError("Telefon kısmı boş olamaz !");
            editTelefon.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editEmail.setError("Email kısmı boş olamaz !");
            editEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Email formatına dikkat ediniz !");
            editEmail.requestFocus();
            return;

        }
        if(sifre.isEmpty()){
            editSifre.setError("Şifre kısmı boş olamaz !");
            editSifre.requestFocus();
            return;
        }
        if(sifre.length() < 6 ){
            editSifre.setError("Şifre Minimum 6 karakterli olmalı !");
            editSifre.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email , sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    User user = new User(ad,tel,email);

                    FirebaseDatabase.getInstance().getReference("Kullanıcılar").child(FirebaseAuth.getInstance()
                            .getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(KayitOl.this ,"Kullanıcı kaydı Başarılı :) ",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(KayitOl.this ,"Kullanıcı kaydı başarısız tekrar deneyiniz :( ",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }else{
                    Toast.makeText(KayitOl.this ,"Kullanıcı kaydı başarısız tekrar deneyiniz :( ",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}