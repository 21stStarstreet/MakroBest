package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
public class Siparis {

    private int siparisNo;
    private int masaNo;
    private Date acilisZamani;
    private SiparisDurumu durum;
    private ArrayList <SiparisDetay> satirlar;

    public Siparis(int siparisNo, int masaNo) {
        this.siparisNo = siparisNo;
        this.masaNo = masaNo;
        this.acilisZamani = new Date();
        this.durum = SiparisDurumu.BEKLEYEN;
        this.satirlar = new ArrayList<>();
    }
    public Siparis (int siparisNo, int masaNo, Date acilisZamani, SiparisDurumu durum) {
        this.siparisNo = siparisNo;
        this.masaNo = masaNo;
        this.acilisZamani = acilisZamani;
        this.durum = durum;
        this.satirlar = new ArrayList<>();
    }

    //siparise yeni bir satır sıra ekler
    public void starEkle(SiparisDetay detay) {
        satirlar.add(detay);
    }

    //toplam
    public double getToplamTutar(){
        double toplam = 0;
        for (SiparisDetay satir:satirlar) {
            toplam += getToplam;
        }
        return toplam;
    }

    //set
    //Zeynep Turhan geçişlerde bunu kullanacak.
    public void setDurum(SiparisDurumu durum){
        this.durum = durum;
    }

    //dosya yazım formatı
    @Override
    public String toString(){
        String tarih = new SimpleDateFormat("yyyy-MM--dd HH:mm").format(acilisZamani);
        return "SIPARIS|" + siparisNo + "|" + masaNo + "|" + tarih + "|" + durum.name();
    }
}