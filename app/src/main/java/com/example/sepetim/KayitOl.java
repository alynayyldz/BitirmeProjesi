package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitOl extends AppCompatActivity {


    private EditText editAd,editSoyad,editTelefon,editEmail,editSifre,editSifreOnay;
    private String ad,soyad,txtEmail,telefon,txtSifre,sifreOnay;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        editEmail=(EditText) findViewById(R.id.editEmail);
        editSifre=(EditText) findViewById(R.id.editSifre);
        editAd=(EditText) findViewById(R.id.editAd);
        editTelefon=(EditText) findViewById(R.id.editTelefon);

        mAuth = FirebaseAuth.getInstance();

    }


    public void kayitbass(View v){
        txtEmail=editEmail.getText().toString();
        txtSifre=editSifre.getText().toString();

        if(!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtSifre)){
            mAuth.createUserWithEmailAndPassword(txtEmail,txtSifre)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                Toast.makeText(KayitOl.this,"Kayıt işlemi Başarılı", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(KayitOl.this, "Bu e-mail kayıtlı.",Toast.LENGTH_SHORT).show();
                        }
                    });


        }else
            Toast.makeText(this, "E-mail Ve Şifre Boş Olamaz", Toast.LENGTH_SHORT).show();





    }
}
