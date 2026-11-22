// Kişi 3 - Nisa
// Bu sınıfta menü işlemlerini yapıyorum: ürün ekleme, fiyat değiştirme, ürün silme.
// Menü, Kişi 1'in yazdığı bağlı listede tutuluyor.

package servis;

import model.MenuUrun;
import model.Siparis;
import veriYapilari.BagliListe;
import java.util.List;

public class MenuYonetimi {

    // menü burada duruyor (Kişi 1'in bağlı listesi)
    private BagliListe<MenuUrun> menu;

    // menüyü dışarıdan alıyorum, kendim oluşturmuyorum
    public MenuYonetimi(BagliListe<MenuUrun> menu) {
        this.menu = menu;
    }

    // Menüde adı verilen ürünü arar. Bulursa ürünü, bulamazsa null döndürür.
    public MenuUrun urunBul(String ad) {
        return null;
    }

    // Menüye yeni ürün ekler. Ad boşsa, fiyat hatalıysa veya ürün zaten varsa eklemez.
    public boolean urunEkle(String ad, double fiyat) {
        return false;
    }

    // Ürünün fiyatını değiştirir. Eski siparişler değişmez çünkü onların fiyatı
    // sipariş anında SiparisDetay içine kaydedildi.
    public boolean fiyatGuncelle(String ad, double yeniFiyat) {
        return false;
    }

    // Ürünü menüden siler. Ürün bekleyen veya hazırlanan bir siparişte varsa silmez.
    public boolean urunSil(String ad, List<Siparis> siparisler) {
        return false;
    }

    // Ürün bekleyen veya hazırlanan bir siparişte var mı diye bakar.
    private boolean aktifSiparisteVarMi(String ad, List<Siparis> siparisler) {
        return false;
    }
}
