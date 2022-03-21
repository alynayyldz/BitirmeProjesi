package com.example.sepetim;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilFragment extends Fragment {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String kullaniciId;


    Button cikis,hesap,dil;
    TextView isimsoyisim,kulId;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_profil,container,false);
        cikis=v.findViewById(R.id.cikis);
        hesap=v.findViewById(R.id.hesap);
        dil=v.findViewById(R.id.dil);

        isimsoyisim=v.findViewById(R.id.isimsoyisim);
        kulId=v.findViewById(R.id.kulId);



        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Kullanıcılar");
        kullaniciId =user.getUid();

        reference.child(kullaniciId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null) {
                    String ad = userProfile.ad;


                    isimsoyisim.setText(ad);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),"Birşeyler Ters Gitti ",Toast.LENGTH_SHORT);
                toast.show();

            }
        });



        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(),GirisYap.class);
                startActivity(intent);
            }
        });


        hesap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),KullaniciIslemleri.class);
                startActivity(intent);
            }
        });

        dil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),DilSecenekleri.class);
                startActivity(intent);
            }
        });
        return v;

    }
}
