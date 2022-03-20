package com.example.sepetim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class KayitOl extends AppCompatActivity {

    Button kayitOl;
    EditText editAd, editTelefon, editEmail, editSifre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        kayitOl = (Button) findViewById(R.id.kayitOl);
        editAd = (EditText) findViewById(R.id.editAd);
        editTelefon = (EditText) findViewById(R.id.editTelefon);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editSifre = (EditText) findViewById(R.id.editSifre);


        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

