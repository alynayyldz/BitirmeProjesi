package com.example.sepetim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilGuncelleme extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String kullaniciId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_guncelleme);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Kullanıcılar");
        kullaniciId =user.getUid();

        reference.child(kullaniciId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    String ad = userProfile.ad;
                    //isim2.setText(ad);

                    String eMail=userProfile.email;
                    //mail2.setText(eMail);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(ProfilGuncelleme.this,"Bir şeyler ters gitti.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}