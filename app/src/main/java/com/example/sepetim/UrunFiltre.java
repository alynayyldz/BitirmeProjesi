package com.example.sepetim;

import java.util.ArrayList;
import android.widget.Filter;
import java.util.logging.LogRecord;

public class UrunFiltre extends Filter {

    private AdaptorUrunler adaptorUrunler;
    private ArrayList<ModelUrunler> filtreList;

    public UrunFiltre(AdaptorUrunler adaptorUrunler, ArrayList<ModelUrunler> filtreList) {
        this.adaptorUrunler = adaptorUrunler;
        this.filtreList = filtreList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults=new FilterResults();
        //arama için doğrulama
        if(constraint != null && constraint.length()>0) {
            constraint=constraint.toString().toUpperCase();
            ArrayList<ModelUrunler> filtreModels=new ArrayList<>();
            for(int i=0; i<filtreList.size();i++) {
                //kontrol
                if(filtreList.get(i).getUrunBarkod().toUpperCase().contains(constraint) ||
                        filtreList.get(i).getUrunKategori().toUpperCase().contains(constraint)) {
                    //filtrelenmiş veriyi list'e ekleme
                    filtreModels.add(filtreList.get(i));
                }
            }
        }
        else {
            filterResults.count=filtreList.size();
            filterResults.values=filtreList;
        }
        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adaptorUrunler.urunListe=(ArrayList<ModelUrunler>)results.values;
        adaptorUrunler.notifyDataSetChanged();
    }
}
