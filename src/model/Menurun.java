package model;
/**
 * Kafenin menüsündeki ürünleri temsil eder.
 * Fatma Nisa Gedik bu sınıfı kendi oluşturduğu sınıfta tutacak.
 */

public class Menurun{
    private int id;
    private String ad;
    private double fiyat;
    public Menurun(int id, String ad, double fiyat) {
        this.id = id;
        this.ad = ad;
        this.fiyat = fiyat;
    }

    //getter'lar

    public int getId() {
        return id;
    }
    public String getAd() {
        return ad;
    }
    public double getFiyat() {
        return fiyat;
    }

    //setter'lar

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    //dosyaya yazılacak formatımız

    @Override
    public String toString() {
        return "MENU|" + id + "|" + ad + "|" + fiyat;
    }
}