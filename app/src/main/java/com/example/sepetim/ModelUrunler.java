package com.example.sepetim;

public class ModelUrunler {

    private String urunId, urunBarkod, urunAgirlik, urunKategori, urunFiyat, urunTarih, urunAd, urunMiktar, uid;

    public ModelUrunler() {

    }

    public ModelUrunler(String urunId, String urunBarkod, String urunAgirlik, String urunKategori, String urunFiyat, String urunTarih, String urunAd, String urunMiktar, String uid) {
        this.urunId = urunId;
        this.urunBarkod = urunBarkod;
        this.urunAgirlik = urunAgirlik;
        this.urunKategori = urunKategori;
        this.urunFiyat = urunFiyat;
        this.urunTarih = urunTarih;
        this.urunAd = urunAd;
        this.urunMiktar = urunMiktar;
        this.uid = uid;
    }

    public String getUrunId() {
        return urunId;
    }

    public void setUrunId(String urunId) {
        this.urunId = urunId;
    }

    public String getUrunBarkod() {
        return urunBarkod;
    }

    public void setUrunBarkod(String urunBarkod) {
        this.urunBarkod = urunBarkod;
    }

    public String getUrunAgirlik() {
        return urunAgirlik;
    }

    public void setUrunAgirlik(String urunAgirlik) {
        this.urunAgirlik = urunAgirlik;
    }

    public String getUrunKategori() {
        return urunKategori;
    }

    public void setUrunKategori(String urunKategori) {
        this.urunKategori = urunKategori;
    }

    public String getUrunFiyat() {
        return urunFiyat;
    }

    public void setUrunFiyat(String urunFiyat) {
        this.urunFiyat = urunFiyat;
    }

    public String getUrunTarih() {
        return urunTarih;
    }

    public void setUrunTarih(String urunTarih) {
        this.urunTarih = urunTarih;
    }

    public String getUrunAd() {
        return urunAd;
    }

    public void setUrunAd(String urunAd) {
        this.urunAd = urunAd;
    }

    public String getUrunMiktar() {
        return urunMiktar;
    }

    public void setUrunMiktar(String urunMiktar) {
        this.urunMiktar = urunMiktar;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
