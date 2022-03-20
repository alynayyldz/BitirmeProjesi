package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.ResultSet;
import java.sql.Statement;

public class GirisYap extends AppCompatActivity {

    private EditText editGirisMail, editGirisSifre;
    private String txtgirisMail, txtgirissifre;

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris_yap);


        editGirisMail = (EditText) findViewById(R.id.editGirisMail);
        editGirisSifre = (EditText) findViewById(R.id.editGirisSifre);

        mAuth = FirebaseAuth.getInstance();

    }
    public void girisyap(View view){
        txtgirisMail = editGirisMail.getText().toString();
        txtgirissifre = editGirisSifre.getText().toString();

        if(!TextUtils.isEmpty(txtgirisMail) && !TextUtils.isEmpty(txtgirissifre)){
            mAuth.signInWithEmailAndPassword(txtgirisMail,txtgirissifre).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    mUser = mAuth.getCurrentUser();
                    Intent intent = new Intent(getApplicationContext(),AnaSayfa.class);
                    startActivity(intent);
                    Toast.makeText(GirisYap.this,"Giriş Başarılı!",Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(GirisYap.this,"Şifre veya Email Hatalı!",Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(GirisYap.this,"Şifre ya da e-mail boş bırakalamaz.",Toast.LENGTH_SHORT).show();
        }

    }

}
