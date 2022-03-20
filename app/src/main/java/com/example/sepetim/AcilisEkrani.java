package com.example.sepetim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class AcilisEkrani extends AppCompatActivity {

    private Handler handler;
    private TextView metin;
    private Animation textviewAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis_ekrani);

        metin=(TextView) findViewById(R.id.metin);
        textviewAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.textview_anim);

        handler=new Handler();

        /*View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);*/


        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(AcilisEkrani.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        };

        handler.postDelayed(runnable,5000);

        metin.setAnimation(textviewAnimation);

    }
}