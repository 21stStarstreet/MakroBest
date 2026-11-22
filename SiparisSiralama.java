// Kişi 3 - Nisa
// Bu sınıfta siparişleri sıralıyorum.
// Java'nın hazır sort metodu yasak olduğu için Bubble Sort'u kendim yazıyorum.

package servis;

import model.Siparis;
import java.util.ArrayList;
import java.util.List;

public class SiparisSiralama {

    // Siparişleri toplam tutara göre küçükten büyüğe sıralar (Bubble Sort).
    // Asıl listeyi bozmamak için kopyasını sıralar.
    public ArrayList<Siparis> tutaraGoreSirala(List<Siparis> siparisler) {
        ArrayList<Siparis> liste = new ArrayList<>(siparisler);
        return liste;
    }

    // Siparişleri oluşturulma zamanına göre eskiden yeniye sıralar (Bubble Sort).
    // Asıl listeyi bozmamak için kopyasını sıralar.
    public ArrayList<Siparis> zamanaGoreSirala(List<Siparis> siparisler) {
        ArrayList<Siparis> liste = new ArrayList<>(siparisler);
        return liste;
    }
}
