package com.example.sepetim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {

    Button btnEkle, btnArama, btnCikis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        btnArama=(Button) findViewById(R.id.btnArama);
        btnEkle=(Button) findViewById(R.id.btnEkle);
        btnCikis=(Button) findViewById(R.id.btnCikis);

        btnArama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminPanel.this,AdminUrunArama.class);
                startActivity(i);
            }
        });

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPanel.this,AdminUrunEkleme.class);
                startActivity(intent);
            }
        });

        btnCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(AdminPanel.this,MainActivity.class);
                startActivity(intent2);
            }
        });

    }
}