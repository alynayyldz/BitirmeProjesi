package com.example.sepetim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DilSecenekleri extends AppCompatActivity {

    Button kaydet;
    RadioGroup radioGroup;
    RadioButton secimTurkce, secimIngilizce;
    String secim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dil_secenekleri);

        kaydet=(Button) findViewById(R.id.kaydet);
        radioGroup=(RadioGroup) findViewById(R.id.radioGrup);
        secimIngilizce=(RadioButton) findViewById(R.id.secimIngilizce);
        secimTurkce=(RadioButton) findViewById(R.id.secimTurkce);


        kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DilSecenekleri.this,AnaSayfa.class);
                startActivity(intent);
                Toast.makeText(getApplication(), "Uygulama dili "+secim+" olarak değiştirildi.", Toast.LENGTH_SHORT).show();
            }
        });

        secimTurkce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                     secim="Türkçe";
                }
            }
        });
        secimIngilizce.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                     secim="İngilizce";
                }
            }
        });
    }
}