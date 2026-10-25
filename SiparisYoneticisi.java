import java.util.HashMap;


public class SiparisYoneticisi {
// Hızlı arama yapabilmek için Anahtar(siparişNo) - Değer(SiparişNesnesi) tutan HashMap
    private HashMap<Integer, Siparis> siparisler;
    private int siraNo = 1; //Her yeni siparişe otomatik artan sıra numarası veremek için
    
    public SiparisYoneticisi() {
        siparisler= new HashMap<>();
    }


    // Yeni sipariş ekleme ve fiyatı o an sabitleme metodu
    public void siparisEkle(int masaNo, String urunAdi, double fiyat, int adet){
        //ürünün o anki fiyatı nesneye aktarılıyor (Fiyat sabitleme kuralı)
        Siparis yeni = new Siparis(siraNo, masaNo, urunAdi, fiyat, adet);

        //HashMap içine anahtar-değer mantığıyla kaydediliyor
        siparisler.put(siraNo, yeni);
        System.out.println(siraNo + " nolu sipariş başarıyla eklendi.");
        siraNo++;
    }

    //HashMap kullanarak siparişi anında bulan metot
    public Siparis siparisGetir(int siparisNo){
        return siparisler.get(siparisNo);
    }

    // Güvenlik Kısıtı: Sadece "Bekliyor" durumundaki siparişlerin silinmesini sağlayan metot
    public void siparisSil(int siparisNo){
        Siparis s = siparisler.get(siparisNo);

        if(s != null){
            //sadece beklenenler silinebilir kuralı kontrol ediliyor
            if(s.durum.equals("Bekliyor")){
                siparisler.remove(siparisNo);
                System.out.println(siparisNo + " nolu bekleyen siparis silindi.");

            }
            else{
                System.out.println("Hata: Mutfağa giren veya tamamlanan sipariş silinemez!");

            }
        }
        else{
                System.out.println("Aradığınız numaraya ait sipariş bulunamadı.");
            }
        }

        //kodun çalışıp çalışmadığını test ettiğimiz ana metot
        public static void main(String[] args){
            SiparisYoneticisi yonetim = new SiparisYoneticisi();

            //test1:sipariş oluşturma
            yonetim.siparisEkle(2, "Türk Kahvesi",40.0,1);

            //test2: HashMap ile arama testi
            Siparis bul = yonetim.siparisGetir(1);
            if(bul != null){
                System.out.println("Bulunan Masa: " + bul.masaNo + " | Durum: " + bul.durum);
            }
            //test3: silme kısıtı testi
            yonetim.siparisSil(1);
        }
}  
    //Siparisin verilerini tutan yardımcı veri sınıfı
class Siparis{
    int siparisNo;
    int masaNo;
    String urunAdi;
    double birimFiyatAninda;//Menü fiyatı değişse bile sipariş anındaki fiyatı korur
    int miktar;
    String durum;//Bekliyor,Hazırlıyor,Tamamlandı

    public Siparis(int siparisNo, int masaNo, String urunAdi, double birimFiyatAninda, int miktar){
        this.siparisNo = siparisNo;
        this.masaNo = masaNo;
        this.urunAdi = urunAdi;
        this.birimFiyatAninda = birimFiyatAninda;
        this.miktar = miktar;
        this.durum = "Bekliyor"; // Yeni açılan her sipariş ilk başta beklemek zorundadır    
        }

    }

