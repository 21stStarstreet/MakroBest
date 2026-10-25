<div align="center">

# ☕ Kafe Sipariş Yönetim Sistemi

**VY-021 | Veri Yapıları Dersi — Dönem Sonu Projesi**

**Geliştirici:** Göktuğ Ezhan Karademir (Kişi 4)  
**Branch:** `goktug_ezhan_karademir`

---

## 📌 Proje ve Görev Tanımı

Bu depo, Veri Yapıları dersi kapsamında geliştirilen **Kafe Sipariş Yönetim Sistemi (VY-021)** projesinin **Göktuğ Ezhan Karademir** çalışma alanıdır.

### 👤 Kişi 4 Sorumluluk Alanı
* **Kullanıcı Arayüzü (GUI):** Java Swing kullanılarak Menü Yönetimi, Sipariş Alma (Masa + Ürün seçimi) ve Sipariş Takip (Bekleyen / Hazırlanıyor / Tamamlandı) ekranlarının tasarlanması.
* **Dinamik Liste (ArrayList) Kullanımı:** Kişi 3'ün arama metodundan dönen filtreleme sonuçlarının arayüz tablosuna yansıtılması ve dinamik olarak yönetilmesi.
* **Modül Entegrasyonu:** Kişi 1 (Veri modelleri, dosya işlemleri, FIFO Kuyruk, Bağlı Liste), Kişi 2 (Sipariş yaşam döngüsü, Harita) ve Kişi 3'ün (Menü yönetimi, sıfırdan sıralama algoritması) yazdığı modüllerin buton ve formlarla birbirine bağlanması.
* **Dokümantasyon:** Proje kurulum ve kullanım adımları ile yapay zekâ kullanım beyanının hazırlanması.

---

## 📅 Çalışma Takvimi & İlerleme

### 🔹 Hafta 1 — Hazırlık
- [x] Proje gereksinimlerinin ve kural analiz raporunun (VY-021) incelenmesi.
- [x] Geçmiş Java OOP ve temel programlama çalışma notlarının derlenip `calismalarim/` klasörüne aktarılması.
- [ ] Projede yer alan veri yapılarının teorik mantığının kavranması:
  - **Kuyruk (Queue - FIFO):** Siparişlerin geliş sırasıyla mutfağa alınması mantığı.
  - **Bağlı Liste (LinkedList):** Menü ürünlerinin dinamik eklenip çıkarılması.
  - **Harita (HashMap):** Sipariş ve masa numaralarıyla hızlı arama mekanizması.
  - **Dinamik Liste (ArrayList):** Arama sonuçlarının dinamik boyutta saklanıp tabloya yansıtılması (Kişi 4 mülakat savunması).
- [ ] Java Swing arayüz bileşenlerinin (JFrame, JPanel, JTable, JButton, JTextField) ve ekran düzenlerinin planlanması.
- [ ] Kişi 1, 2 ve 3'ün servis iskeletleri tamamlanana kadar arayüzün test edilebilmesi için sahte (mock) veri yapısının kurgulanması.

---

## 📂 Dizin Yapısı

```
Kafe-Yonetim/
│
├── calismalarim/               # Java temel çalışma notları ve kişisel pratikler
│   ├── 01-Temel-Kavramlar/
│   ├── 02-Degiskenler/
│   ├── 03-VeriAlma/
│   ├── 04-Switch-Case/
│   ├── 05-Dİzi/
│   ├── 05-Encapsulation(Kapsülleme)&Inheritance(Kalıtım)/
│   ├── Class/
│   └── Örnekler/
│
├── README.md                   # Kişisel çalışma planı ve proje dokümantasyonu
├── VY-021 — MVP Planı.pdf      # Proje MVP yol haritası
└── VY-021 Kafe Sipariş...pdf   # Proje ve kural analiz raporu
```

---

## ⚠️ Proje Kuralları & Akademik Dürüstlük

* **Veritabanı Yasağı:** SQLite, MySQL vb. herhangi bir veritabanı kesinlikle kullanılmayacaktır. Tüm kalıcılık `.kafe` uzantılı düz metin dosyası üzerinden sağlanacaktır.
* **Hazır Sıralama Kütüphanesi Yasağı:** Java'nın `Collections.sort()` veya `Arrays.sort()` metotları kullanılmayacak, sıralama algoritması sıfırdan yazılacaktır.
* **Yapay Zekâ Kullanım İlkesi:** Yapay zekâ araçları (Claude, Gemini vb.) yalnızca kavram öğrenme, mimari planlama, hata analizi ve soru-cevap rehberliği amacıyla kullanılmaktadır. Projedeki hiçbir kod yapay zekâdan doğrudan kopyalanıp yapıştırılmayacak; öğrenci tarafından adım adım yazılarak ve tam olarak kavranarak geliştirilecektir.
