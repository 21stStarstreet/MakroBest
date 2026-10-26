package model;

//bir siparisin detaylarını içerir,
//Ayrıca bir kere set edileceiğinden fiyat sonradan değişse bile halen eski fiyattan
//işlem devam eder böylece yanlış hesaplamalardan kaçınılır.

public class SiparisDetay{

    private MenuUrun urun;
    private int miktar;
    private double birimFiyatAninda; //bahsettiğimiz özelliği sağlayan kısım

    public SiparisDetay(MenuUrun urun, int miktar){
        this.miktar = miktar;
        this.urun = urun;
        this.birimFiyatAninda = urun.getFiyat(); //sabitleme işlemi
    }

    //dosyadan okuma kısmı
    public SiparisDetay (MenuUrun urun, int miktar, double birimFiyatAninda) {
        this.urun = urun;
        this.miktar = miktar;
        this.birimFiyatAninda = birimFiyatAninda;
    }

    //toplam
    public double getToplam(){
        return birimFiyatAninda= miktar;
    }

    //getters

    public MenuUrun getUrun() {
        return urun;
    }
    public int getMiktar() {
        return miktar;
    }
    public double getBirimFiyatAninda() {
        return birimFiyatAninda;
    }

    //setters, Zeynep Turhan miktar güncelemede bunu kullanacak
    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    //dosya yazma formatı
    public String toString(int SiparisNo){
        return "DETAY|" + siparisNo + "|" + urun.getId() + "|" + miktar + "|" + birimFiyatAninda;
    }
}