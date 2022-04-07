package com.example.sepetim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptorUrunler extends RecyclerView.Adapter<AdaptorUrunler.HolderUrunler> implements Filterable {

    private Context context;
    public ArrayList<ModelUrunler> urunListe,filtreList;
    public UrunFiltre filtre;


    public AdaptorUrunler(Context context, ArrayList<ModelUrunler> urunListe) {
        this.context = context;
        this.urunListe = urunListe;
        this.filtreList=urunListe;
    }

    @NonNull
    @Override
    public HolderUrunler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.activity_admin_urun_arama,parent,false);
        return new HolderUrunler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderUrunler holder, int position) {
        //get data
        ModelUrunler modelUrunler=urunListe.get(position);
        String id=modelUrunler.getUrunId();
        String getBarkod=modelUrunler.getUrunBarkod();
        String getAgirlik=modelUrunler.getUrunAgirlik();
        String getKategori=modelUrunler.getUrunKategori();
        String getFiyat=modelUrunler.getUrunFiyat();
        String getTarih=modelUrunler.getUrunTarih();
        String getAd=modelUrunler.getUrunAd();
        String getMiktar=modelUrunler.getUrunMiktar();
        String uid=modelUrunler.getUid();

        //set data
        holder.barkod.setText(getBarkod);
        holder.ad.setText(getAd);
        holder.fiyat.setText(getFiyat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ürün detayı gösterme

            }
        });
    }

    @Override
    public int getItemCount() {
        return urunListe.size();
    }

    @Override
    public Filter getFilter() {
        if(filtre==null) {
            filtre=new UrunFiltre(this, filtreList);
        }
        return filtre;
    }

    class HolderUrunler extends RecyclerView.ViewHolder {
        //recyclerview'daki görünümleri tutmak

        private ImageView icon;
        private TextView barkod, ad, fiyat;

        public HolderUrunler(@NonNull View itemView) {
            super(itemView);

            icon=itemView.findViewById(R.id.icon);
            barkod=itemView.findViewById(R.id.barkod);
            ad=itemView.findViewById(R.id.ad);
            fiyat=itemView.findViewById(R.id.fiyat);


        }
    }

    ;
}
