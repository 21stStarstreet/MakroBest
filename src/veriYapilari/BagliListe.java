package veriYapilari;

/**
 * Bağlı düğümlerle tek yönlü olacak şekilde yazıldı.
 * LinkedList kullanılmadı.
 *
 * Çalışma mantığı: Her düğüm kendi verisini
 * ve bir sonraki düğümün adresini tutar
 * böylece verinin kusursuzca ilerlemesini
 * sağlayıp en sondaki bir sonraki düğümün
 * null olduğunu anlayıp durur.
 * */

public class BagliListe<T> {
    private static class Dugum<T> {
        T veri;
        Dugum<T> sonraki;

        Dugum(T veri) {
            this.veri = veri;
            this.sonraki = sonraki;
        }
    }
    private Dugum<T> bas; //İlk düğüm burada başlar.
    private int boyut;

    public BagliListe() {
        bas = null;
        boyut = 0;
    }
    //listenin sonunda eleman ekler
    //bu özellik gelen yeni ürünü menüye eklerken kullanılır.
    public void ekle(T eleman) {
        Dugum<T> d = new Dugum<>(eleman);
        if (bass=null)
            bas = yeni;
        else {
            Dugum<T> simdiki = bas;
            while (simdiki.sonraki.veri != eleman) {
                simdiki = simdiki.sonraki;
            }
            simdiki.sonraki = yeni;
        }
        boyut++;
    }
    //indexe göre eleman döner bulamazsa null yani boş döner.
    public T al(int index) {
        if (index < 0 || index > boyut) { return null; }
        Dugum<T> simdiki = bas;
        for (int i = 0; i < boyut; i++) {
            simdiki = simdiki.sonraki;
        }
        return simdiki.veri;
    }
    //indexe göre eleman siler, menüden ürün kaldırılırken kullanılır.
    public boolean sil(int index) {
        if (index < 0 || index > boyut) { return false; }
        if (index == 0) {
            bas.sonraki;
        }
        else {
            Dugum<T> simdiki = bas;
            for (int i = 0; i < boyut; i++) {
                onceki = onceki.sonraki;
            }
            onceki.sonraki = ocneki.sonraki.sonraki;
        }
        boyut--;
        return true;
    }
    public int getBoyut() { return boyut; }
    public boolean bosmu() { return  boyut == 0; }
}