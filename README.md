Menü Yönetimi, Arama ve Sıralama Modülü

VY-021 Kafe Sipariş Yönetim Sistemi projesinin bir parçasıdır.

| | |
|---|---|
| Sorumlu | Fatma Nisa Gedik (Kişi 3) |
| Modül | Menü yönetimi + Arama + Sıralama |
| Proje aşaması | Faz 3 (Faz 2 ile paralel yürütülür) |
| Dal (branch) | `fatma_nisa_gedik` |

---

 1. Modülün Amacı

Kafenin menüsünü güncel tutmak, garsonun aradığı siparişi hızlıca bulabilmesini sağlamak ve siparişleri istenen ölçüte göre sıralamak.

Bu modül diğer modüllerden büyük ölçüde bağımsız çalışır. Sadece şu noktalarda ekip arkadaşlarımın kodunu kullanır:

- Kişi 1: `MenuUrun`, `SiparisDetay`, `Siparis` sınıfları ve menü için bağlı liste
- Kişi 2: Sipariş oluşturma ve durum geçişleri (aktif siparişlerin bilgisi)
- Kişi 4: Arayüz. Arama sonuçlarını `ArrayList` olarak ona teslim ederim, tabloya o basar.

---

 2. Görevlerim

 2.1 Menü Yönetimi

| İşlev | Açıklama |
|---|---|
| Ürün ekle | Yeni ürün (örn. Filtre Kahve) Kişi 1'in bağlı listesine yeni düğüm olarak eklenir. |
| Fiyat güncelle | Menüdeki ürünün fiyatı değişir. Eski siparişler etkilenmez, çünkü fiyat sipariş anında `birimFiyatAninda` alanına kaydedilir. |
| Ürün sil | Ürün aktif bir siparişte kullanılıyorsa silinmez ve kullanıcıya uyarı gösterilir. Kullanılmıyorsa listeden çıkarılır. |

2.2 Arama ve Filtreleme

Aşağıdaki ölçütlere göre sipariş araması yapılır. Sonuçlar `ArrayList` olarak döndürülür.

- Sipariş numarası
- Masa numarası
- Ürün adı (örn. içinde "Latte" geçen siparişler)
- Durum (Bekleyen / Hazırlanıyor / Tamamlandı)

 2.3 Sıralama

Siparişler iki ölçüte göre sıralanabilir:

- Sipariş oluşturulma zamanı
- Toplam tutar

> Kural: Java'nın hazır sıralama metotları (`Collections.sort()` vb.) kullanılmaz. Sıralama algoritması (Bubble Sort) sıfırdan, döngülerle elle yazılır.

---
 3. Kullanılan Veri Yapıları ve Nedenleri

| Yapı | Nerede | Neden |
|---|---|---|
| Bağlı Liste (Kişi 1'in yazdığı) | Menü ürünleri | Ortaya ürün eklemek veya çıkarmak sadece bağlantıları değiştirmeyi gerektirir. |
| `ArrayList` | Arama sonuçları | Sonuç sayısı her aramada değişir. Liste kolayca temizlenip yeniden doldurulabilir. |
| Bubble Sort (elle yazılmış) | Sıralama | Yan yana iki elemanı karşılaştırıp büyüğü sağa kaydıran, anlaşılması kolay bir algoritmadır. |

Bubble Sort Nasıl Çalışır?

1. Listede yan yana duran iki sipariş karşılaştırılır.
2. Sırası yanlışsa yerleri değiştirilir.
3. Bu işlem listenin sonuna kadar tekrarlanır. Her turda en büyük eleman sona yerleşir.
4. Bir turda hiç yer değişikliği olmazsa liste sıralanmıştır ve algoritma durur.

---

 4. Proje Kuralları (Bu Modülü İlgilendirenler)

- Veritabanı kullanılmaz. Veriler özel uzantılı metin dosyasında (`.kafe`) tutulur.
- Program açılışında veriler dosyadan belleğe yüklenir. Arama, sıralama ve güncelleme işlemleri bellekteki veri yapısı üzerinde yapılır.
- Her değişiklikten sonra veri dosyaya baştan sona yeniden yazılır.
- Tüm girişler ve sonuçlar arayüz (GUI) üzerinden gösterilir, terminal çıktısı kullanılmaz.

---

 5. Yapılacaklar Listesi

- [ ] Menüye ürün ekleme fonksiyonu
- [ ] Menüden ürün silme fonksiyonu
- [ ] Aktif siparişte olan ürünün silinmesini engelleyen kontrol
- [ ] Ürün fiyatı güncelleme (eski siparişler korunacak şekilde)
- [ ] Sipariş no, masa no, ürün adı ve duruma göre arama
- [ ] Zamana göre sıralama (Bubble Sort)
- [ ] Tutara göre sıralama (Bubble Sort)
- [ ] Arayüz ekranına (arama/menü paneli) bağlama
- [ ] Ekip arkadaşlarının modülleriyle entegrasyon testi

---

 6. Ekip

| Kişi | Sorumluluk |
|---|---|
| Kişi 1 | Veri yapıları, kuyruk, bağlı liste, dosya okuma/yazma |
| Kişi 2 | Sipariş mantığı, FIFO, durum geçişleri |
| Kişi 3 (Fatma Nisa Gedik) | Menü yönetimi, arama, sıralama |
| Kişi 4 | Arayüz ve entegrasyon |
