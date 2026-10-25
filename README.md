# ☕ Kafe Sipariş Yönetim Sistemi
### VY-021 | Veri Yapıları Dersi — Dönem Sonu Projesi
**Geliştirici:** Zeynep Turhan (Kişi 2)  

---

### Neler Yapıyorum? (Görevlerim)
Projede siparişlerin ilk oluşturulma anından mutfaktaki süreçlerine ve tamamlanmasına kadar geçen tüm döngüyü ben yönetiyorum. 

* **Sipariş ve Fiyat Sabitleme:** Garson sipariş girdiğinde ürünün o anki fiyatını siparişe sabitliyorum.Menüde fiyat değişse bile eski siparişlerin tutarı bundan etkilenmiyor.
* **FIFO Mutfak Sırası:** Bekleyen siparişler mutfağa sırayla gidiyor. Kimse öne geçemez, ilk gelen ilk hazırlanır.
* **Durumlar ve Silme Kısıtı:** Siparişler "Bekliyor -> Hazırlanıyor -> Tamamlandı" şeklinde ilerliyor. Mutfağa girmiş veya bitmiş bir siparişi kimse silemez, silme sadece bekleyenler için geçerli.
* **Miktar Değişikliği:** Bekleyen siparişte ürün sayısı artıp azalınca toplam tutarı anlık olarak yeniden hesaplıyorum.

### Neden HashMap Kullandım?
"3 numaralı sipariş nerede?" diye arattığımızda bütün listeyi tek tek taramak mantıksız olurdu. Bu yüzden `HashMap` kullandım. Sipariş numarasını anahtar (`key`) yapıp siparişe direkt ulaşıyorum, böylece arama ve güncellemeler anlık (`O(1)`) oluyor.

### İlk Kontrol için Kod İskeleti Planım
ilk takip öncesinde kendi hesabım üzerinden depoya göndereceğim ilk kod katkısında şu temel iskelet yer alacak:
* Siparişleri depolayacak `HashMap` yapısının tanımlanması.
* Yeni sipariş ekleme (fiyat sabitleme mantığıyla) fonksiyonunun boş şablonu.
* Sipariş durumunu değiştirme ve yalnızca bekleyen siparişlerin silinmesini denetleyen `if` kontrol bloklarının hazırlanması.