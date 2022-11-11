package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SifremiUnuttum extends AppCompatActivity {

    private EditText editemailreset;
    private Button  resetpassword;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifremi_unuttum);

        editemailreset =(EditText) findViewById(R.id.editemailreset);
        resetpassword = (Button) findViewById(R.id.resetpassword);

        auth = FirebaseAuth.getInstance();

        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });

    }

    private void resetPassword(){
        String email = editemailreset.getText().toString().trim();

        if(email.isEmpty()){
            editemailreset.setError("E-mail kısmı boş olamaz !");
            editemailreset.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editemailreset.setError("E-mail formatına dikkat ediniz !");
            editemailreset.requestFocus();
            return;

        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SifremiUnuttum.this,"E-ailinizi Kontrol Ediniz !!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SifremiUnuttum.this,"Bir şeyler ters gitti !!",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}