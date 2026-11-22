package veriYapilari;

/**
 * bağlı düğümleri kullanarak FIFO mantığında kuyruk algoritması.
 * ekle() ile yeni geleni en sona ekler ki sıra bozulmaz.
 * */

public class Kuyruk<T> {
    private static class Dugum<T> {
        T veri;
        Dugum<T> sonraki;
        Dugum(T veri) {
            this.veri = veri;
            this.sonraki = sonraki;
        }
    }
    private Dugum<T> bas; //girilir
    private Dugum<T> son; //çıkılır
    private int boyut;

    public Kuyruk() {
        bas = null;
        son = null;
        boyut = 0;
    }

    //kuyruğa siparişi ekler
    public void ekle(T eleman) {
        Dugum<T> yeni = new Dugum<>(eleman);
        if (bosmu()) {
            bas = yeni;
            son = yeni;
        }
        else {
            son.sonraki = yeni;
            son = yeni;
        }
        boyut++;
    }
    //çıkarma işlemi: en eski yani aslında en öndeki sipraişi
    //çıkarır eğer silecek index bulamazsa da null döner.

    public T cikar(){
        if(bosmu()) { return null; }
        T veri = bas.veri;
        bas = bas.sonraki;
        if (bas == null) son = null;
        boyut--;
        return veri;
    }

    //Kişi 2 "sıradaki kimse" diye kontrol ederken kullanacak.

    public T onbak() {
        if (bosmu()) return null;
        return bas.veri;
    }
    public boolean bosmu() {
        return boyut == 0;
    }
    public int getBoyut() {
        return boyut;
    }
}