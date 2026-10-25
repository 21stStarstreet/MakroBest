<div align="center">

# ☕ Kafe Sipariş Yönetim Sistemi

**VY-021 | Veri Yapıları Dersi — Dönem Sonu Projesi**

![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Java Swing](https://img.shields.io/badge/Java_Swing-3D7EBF?style=for-the-badge&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans_IDE-1B6AC6?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

</div>

---

## 📖 Proje Hakkında

Menü ürünlerini, masa siparişlerini ve hazırlama sürecini yöneten bir masaüstü kafe sipariş takip uygulamasıdır.

Siparişler **üç aşamalı** bir akışla yönetilir:

```
Bekleyen  →  Hazırlanıyor  →  Tamamlandı
```

Tüm veriler herhangi bir veritabanı kullanılmadan `.kafe` uzantılı özel bir metin dosyasında saklanır. Program her açılışında bu dosyayı okuyarak veri yapılarına yükler; her değişiklikte bellekteki veri yapısı dosyaya baştan sona yeniden yazılır.

---

## ✨ Temel İşlevler

| İşlem | Açıklama |
|-------|----------|
| ➕ **Kaydetme** | Masa numarası ve ürün‑miktar bilgisiyle yeni sipariş oluşturma |
| 🗑️ **Silme** | Bekleyen siparişi silme; aktif siparişte kullanılmayan menü ürününü kaldırma |
| ✏️ **Güncelleme** | Ürün fiyatı, bekleyen sipariş içeriği ve sipariş hazırlık durumunu değiştirme |
| 🔃 **Sıralama** | Siparişleri oluşturulma zamanına veya toplam tutara göre sıralama |
| 🔍 **Sorgulama** | Sipariş no, masa no, ürün adı ve hazırlık durumuna göre arama |

---

## 🏗️ Kullanılan Veri Yapıları

| Veri Yapısı | Kullanım Yeri | Sorumlu |
|-------------|---------------|---------|
| **Kuyruk (Queue)** | Bekleyen sipariş sırası — ilk gelen ilk hazırlanır | Kişi 1 |
| **Bağlı Liste (LinkedList)** | Menü ürünleri listesi | Kişi 1 |
| **Harita (HashMap)** | Sipariş no ve masa no bazlı hızlı erişim | Kişi 2 |
| **Dinamik Liste (ArrayList)** | Arama sonuçlarının tutulması | Kişi 4 |
| **Sıralama Algoritması** | Sipariş sıralama — sıfırdan uygulanmış | Kişi 3 |

> Tüm veri yapıları, ders kapsamında gerekli görüldüğü ölçüde sıfırdan geliştirilmiştir.

---

## 📂 Proje Yapısı

```
kafe-siparis-sistemi/
│
├── src/
│   ├── model/
│   │   ├── MenuUrun.java           # Menü ürünü veri modeli
│   │   ├── Siparis.java            # Sipariş veri modeli
│   │   ├── SiparisDetay.java       # Sipariş satırı (ürün + miktar + anlık fiyat)
│   │   └── SiparisDurumu.java      # Enum: BEKLEYEN / HAZIRLANIYOR / TAMAMLANDI
│   │
│   ├── veriYapilari/
│   │   ├── Kuyruk.java             # FIFO kuyruk uygulaması
│   │   ├── BagliListe.java         # Bağlı liste uygulaması
│   │   ├── Harita.java             # Anahtar-değer harita uygulaması
│   │   └── DinamikListe.java       # Dinamik dizi uygulaması
│   │
│   ├── servis/
│   │   ├── SiparisServisi.java     # Sipariş oluşturma, güncelleme, silme mantığı
│   │   ├── MenuServisi.java        # Menü yönetimi, arama ve sıralama
│   │   └── DosyaServisi.java       # .kafe dosyası okuma ve yazma
│   │
│   └── gui/
│       ├── AnaPencere.java         # Ana uygulama penceresi
│       ├── SiparisPanel.java       # Sipariş oluşturma ve takip ekranı
│       ├── MenuPanel.java          # Menü yönetimi ekranı
│       └── AramaPanel.java         # Arama ve sıralama ekranı
│
├── veri/
│   ├── veriler.kafe                # Uygulama veri dosyası
│   └── ornek.kafe                  # Örnek veri (ilk çalıştırma için)
│
└── README.md
```

---

## 👥 Takım

| # | Ad Soyad | Görev | Zorluk |
|---|----------|-------|--------|
| Kişi 1 | Ad Soyad | Temel Veri Katmanı: Veri Yapıları + Dosya Okuma/Yazma | 9/10 |
| Kişi 2 | Ad Soyad | Sipariş İşleme: Oluşturma, Durum Yönetimi, Harita | 8/10 |
| Kişi 3 | Ad Soyad | Menü Yönetimi + Arama + Sıralama Algoritması | 6/10 |
| Kişi 4 | Ad Soyad | GUI Tasarımı + Uygulama Entegrasyonu + README | 8/10 |

---

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler

- Java 21 veya üzeri
- NetBeans IDE (önerilen) ya da herhangi bir Java IDE

### Adımlar

**1. Repoyu klonlayın:**
```bash
git clone https://github.com/KULLANICI/REPO-ADI.git
```

**2. NetBeans ile açın:**
```
File → Open Project → İndirilen klasörü seçin
```

**3. Projeyi çalıştırın:**
```
Run → Run Project   (ya da F6)
```

### İlk Çalıştırma

Program ilk açıldığında `veri/veriler.kafe` dosyası yoksa otomatik olarak boş bir dosya oluşturulur. Hazır örnek verilerle başlamak için `veri/ornek.kafe` dosyasını `veriler.kafe` olarak kopyalayın.

---

## 📁 Veri Dosyası Formatı

Tüm veriler `.kafe` uzantılı düz metin dosyasında tutulur. Her satır bir kaydı temsil eder:

```
MENU|1|Filtre Kahve|45.0
MENU|2|Cappuccino|55.0
SIPARIS|1001|3|2026-10-13 14:30|BEKLEYEN
DETAY|1001|1|2|45.0
DETAY|1001|2|1|55.0
```

| Önek | Alanlar |
|------|---------|
| `MENU` | id \| ad \| fiyat |
| `SIPARIS` | siparisNo \| masaNo \| tarih \| durum |
| `DETAY` | siparisNo \| urunId \| miktar \| birimFiyatAninda |

> **Önemli:** `birimFiyatAninda` alanı siparişin verildiği andaki fiyatı saklar. Menüdeki fiyat sonradan değişse de eski siparişler etkilenmez.

---

## 🤖 Yapay Zeka ve Dış Kaynak Kullanımı

| Araç / Kaynak | Kullanım Amacı | Etkilenen Bölüm |
|---------------|----------------|-----------------|
| Claude (Anthropic) | Proje planlama, README taslağı, kavram açıklaması | Genel |

> ⚠️ **Not:** Yapay zeka araçlarından alınan hiçbir kod doğrudan kopyalanmamıştır. Tüm kodlar, araçlardan yalnızca öğrenme ve kavrama amacıyla yararlanılarak öğrenciler tarafından sıfırdan yeniden yazılmıştır.

---

## 📄 Ders Bilgisi

| Alan | Bilgi |
|------|-------|
| Ders | Veri Yapıları |
| Proje Kodu | VY-021 |
| Dönem | 2025–2026 Güz |
| Üniversite | İnönü Üniversitesi |
| Başvuru E-postası | inonubmveriyapilari@gmail.com |

---

<div align="center">
