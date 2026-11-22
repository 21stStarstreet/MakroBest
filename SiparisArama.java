// Kişi 3 - Nisa
// Bu sınıfta siparişleri arıyorum: sipariş no, masa no, ürün adı ve duruma göre.
// Sonuçları yeni bir ArrayList'e koyuyorum, Kişi 4 bunu ekrandaki tabloda gösterecek.

package servis;

import model.Siparis;
import model.SiparisDurumu;
import java.util.ArrayList;
import java.util.List;

public class SiparisArama {

    // Sipariş numarasına göre siparişi bulur.
    public ArrayList<Siparis> siparisNoIleAra(List<Siparis> siparisler, int siparisNo) {
        ArrayList<Siparis> sonuc = new ArrayList<>();
        return sonuc;
    }

    // Bir masanın bütün siparişlerini bulur.
    public ArrayList<Siparis> masaNoIleAra(List<Siparis> siparisler, int masaNo) {
        ArrayList<Siparis> sonuc = new ArrayList<>();
        return sonuc;
    }

    // İçinde verilen ürün olan siparişleri bulur.
    public ArrayList<Siparis> urunAdiIleAra(List<Siparis> siparisler, String urunAdi) {
        ArrayList<Siparis> sonuc = new ArrayList<>();
        return sonuc;
    }

    // Durumu BEKLEYEN, HAZIRLANIYOR veya TAMAMLANDI olan siparişleri bulur.
    public ArrayList<Siparis> durumIleAra(List<Siparis> siparisler, SiparisDurumu durum) {
        ArrayList<Siparis> sonuc = new ArrayList<>();
        return sonuc;
    }
}
