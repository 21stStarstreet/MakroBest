package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Sipariş oluşturma formu ve 3 aşamalı sipariş akışı (Bekleyen / Hazırlanıyor / Tamamlandı).
 *
 * Not: Servis katmanları (Kişi 1 ve Kişi 2) tamamlandığında butonların arkasına
 * doğrudan SiparisServisi ve Kuyruk çağrıları bağlanacaktır.
 */
public class SiparisPanel extends JPanel {

    private JTextField txtMasaNo;
    private JComboBox<String> cmbUrunler;
    private JSpinner spnMiktar;
    private JLabel lblBilgi;

    private DefaultTableModel modelBekleyen;
    private DefaultTableModel modelHazirlaniyor;
    private DefaultTableModel modelTamamlandi;

    private JTable tabloBekleyen;
    private JTable tabloHazirlaniyor;

    private int siradakiSiparisNo = 1005;

    public SiparisPanel() {
        setLayout(new BorderLayout(0, Tema.BOSLUK_16));
        setBackground(Tema.ARKA_PLAN);
        setBorder(BorderFactory.createEmptyBorder(Tema.BOSLUK_24, Tema.BOSLUK_24, Tema.BOSLUK_24, Tema.BOSLUK_24));

        add(Tema.sayfaBasligi("Sipariş Paneli"), BorderLayout.NORTH);

        JPanel merkez = new JPanel(new BorderLayout(0, Tema.BOSLUK_16));
        merkez.setBackground(Tema.ARKA_PLAN);
        merkez.add(yeniSiparisFormuOlustur(), BorderLayout.NORTH);
        merkez.add(ucDurumPanosuOlustur(), BorderLayout.CENTER);

        add(merkez, BorderLayout.CENTER);
        ornekVerileriYukle();
    }

    /** Üstteki yatay yeni sipariş oluşturma kartını kurar. */
    private JPanel yeniSiparisFormuOlustur() {
        JPanel kart = Tema.kartOlustur(Tema.BOSLUK_16);
        kart.setLayout(new BorderLayout(0, Tema.BOSLUK_12));

        JLabel formBaslik = new JLabel("Yeni sipariş");
        formBaslik.setFont(Tema.BASLIK_ORTA);
        formBaslik.setForeground(Tema.METIN_BIRINCIL);

        JPanel alanlar = new JPanel(new FlowLayout(FlowLayout.LEFT, Tema.BOSLUK_12, 0));
        alanlar.setOpaque(false);

        txtMasaNo = Tema.metinKutusuOlustur(5);
        txtMasaNo.setText("4");

        String[] ornekUrunler = {
            "Simit - 25.00 TL",
            "Çay - 15.00 TL"
        };
        cmbUrunler = new JComboBox<>(ornekUrunler);
        Tema.acilirListeHazirla(cmbUrunler);

        spnMiktar = new JSpinner(new SpinnerNumberModel(1, 1, 50, 1));
        Tema.sayacHazirla(spnMiktar);

        JButton btnOlustur = Tema.butonBirincil("Sipariş oluştur");
        btnOlustur.addActionListener(e -> ornekSiparisEkle());

        lblBilgi = new JLabel(" ");
        lblBilgi.setFont(Tema.METIN_KUCUK);
        lblBilgi.setForeground(Tema.METIN_SOLUK);

        alanlar.add(Tema.etiket("Masa no"));
        alanlar.add(txtMasaNo);
        alanlar.add(Tema.etiket("Ürün"));
        alanlar.add(cmbUrunler);
        alanlar.add(Tema.etiket("Adet"));
        alanlar.add(spnMiktar);
        alanlar.add(btnOlustur);

        kart.add(formBaslik, BorderLayout.NORTH);
        kart.add(alanlar, BorderLayout.CENTER);
        kart.add(lblBilgi, BorderLayout.SOUTH);
        return kart;
    }

    /** Bekleyen, Hazırlanıyor ve Tamamlandı sütunlarını yan yana yerleştirir. */
    private JPanel ucDurumPanosuOlustur() {
        JPanel pano = new JPanel(new GridLayout(1, 3, Tema.BOSLUK_16, 0));
        pano.setBackground(Tema.ARKA_PLAN);

        String[] sutunlar = {"No", "Masa", "İçerik", "Tutar"};

        modelBekleyen = saltOkunurModel(sutunlar);
        tabloBekleyen = new JTable(modelBekleyen);
        Tema.tabloHazirla(tabloBekleyen, 3);

        modelHazirlaniyor = saltOkunurModel(sutunlar);
        tabloHazirlaniyor = new JTable(modelHazirlaniyor);
        Tema.tabloHazirla(tabloHazirlaniyor, 3);

        modelTamamlandi = saltOkunurModel(sutunlar);
        JTable tabloTamamlandi = new JTable(modelTamamlandi);
        Tema.tabloHazirla(tabloTamamlandi, 3);

        // 1. Bekleyen sütunu butonları (FIFO mutfağa al ve bekleyeni sil)
        JButton btnMutfaAl = Tema.butonBirincil("Sıradakini mutfağa al");
        btnMutfaAl.addActionListener(e -> siradakiSiparisiMutfagaAl());

        JButton btnSil = Tema.butonIkincil("Seçiliyi sil", Tema.HATA_KIRMIZI);
        btnSil.addActionListener(e -> bekleyenSiparisiSil());

        // 2. Hazırlanıyor sütunu butonu
        JButton btnTamamla = Tema.butonBirincil("Tamamlandı işaretle");
        btnTamamla.addActionListener(e -> siparisiTamamla());

        pano.add(durumKartiOlustur("Bekleyen", Tema.DURUM_BEKLEYEN, tabloBekleyen, btnMutfaAl, btnSil));
        pano.add(durumKartiOlustur("Hazırlanıyor", Tema.DURUM_HAZIRLANIYOR, tabloHazirlaniyor, btnTamamla));
        pano.add(durumKartiOlustur("Tamamlandı", Tema.DURUM_TAMAMLANDI, tabloTamamlandi));

        return pano;
    }

    /** Üç durum kartı için ortak sade kart şablonu. */
    private JPanel durumKartiOlustur(String baslik, Color durumRengi, JTable tablo, JButton... butonlar) {
        JPanel kart = Tema.kartOlustur(Tema.BOSLUK_16);
        kart.setLayout(new BorderLayout(0, Tema.BOSLUK_12));

        // Başlık + pürüzsüz yuvarlak durum noktası
        JPanel ust = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        ust.setOpaque(false);

        JLabel lblBaslik = new JLabel(baslik);
        lblBaslik.setFont(Tema.BASLIK_ORTA);
        lblBaslik.setForeground(Tema.METIN_BIRINCIL);

        ust.add(Tema.durumNoktasi(durumRengi));
        ust.add(lblBaslik);

        JScrollPane kaydirma = new JScrollPane(tablo);
        Tema.kaydirmaPaneliHazirla(kaydirma);

        kart.add(ust, BorderLayout.NORTH);
        kart.add(kaydirma, BorderLayout.CENTER);

        if (butonlar.length > 0) {
            JPanel altButonlar = new JPanel(new FlowLayout(FlowLayout.LEFT, Tema.BOSLUK_8, 0));
            altButonlar.setOpaque(false);
            for (JButton b : butonlar) {
                altButonlar.add(b);
            }
            kart.add(altButonlar, BorderLayout.SOUTH);
        }
        return kart;
    }

    private DefaultTableModel saltOkunurModel(String[] sutunlar) {
        return new DefaultTableModel(sutunlar, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    /** İskelet aşamasında arayüzün test edilebilmesi için örnek veriler. */
    private void ornekVerileriYukle() {
        modelBekleyen.addRow(new Object[]{"1002", "Masa 3", "2x Simit", "50.00 TL"});
        modelBekleyen.addRow(new Object[]{"1003", "Masa 5", "2x Çay", "30.00 TL"});
        modelHazirlaniyor.addRow(new Object[]{"1001", "Masa 1", "1x Simit", "25.00 TL"});
        modelTamamlandi.addRow(new Object[]{"1000", "Masa 2", "3x Çay", "45.00 TL"});
    }

    /** Menü panelinde yapılan ekleme/güncelleme/silme sonrası ürün listesini yeniler. */
    public void urunListesiniGuncelle(java.util.List<String> yeniUrunler) {
        cmbUrunler.removeAllItems();
        for (String urun : yeniUrunler) {
            cmbUrunler.addItem(urun);
        }
    }

    // --- Geçici İskelet Olayları (Entegrasyonda Kişi 2 servislerine bağlanacak) ---

    private void ornekSiparisEkle() {
        String masa = txtMasaNo.getText().trim();
        if (masa.isEmpty()) {
            lblBilgi.setForeground(Tema.HATA_KIRMIZI);
            lblBilgi.setText("Masa numarası boş bırakılamaz.");
            return;
        }
        String secilen = (String) cmbUrunler.getSelectedItem();
        if (secilen == null || !secilen.contains(" - ")) {
            lblBilgi.setForeground(Tema.HATA_KIRMIZI);
            lblBilgi.setText("Sipariş için menüde en az bir ürün olmalıdır.");
            return;
        }
        String[] parcalar = secilen.split(" - ");
        String urunAdi = parcalar[0];
        double birimFiyat = Double.parseDouble(parcalar[1].replace(" TL", "").replace(",", "."));
        int miktar = (Integer) spnMiktar.getValue();
        double toplam = birimFiyat * miktar;

        modelBekleyen.addRow(new Object[]{
            String.valueOf(siradakiSiparisNo++),
            "Masa " + masa,
            miktar + "x " + urunAdi,
            String.format("%.2f TL", toplam).replace(",", ".")
        });
        lblBilgi.setForeground(Tema.DURUM_TAMAMLANDI);
        lblBilgi.setText("Sipariş bekleyen kuyruğuna eklendi (örnek veri).");
    }

    private void siradakiSiparisiMutfagaAl() {
        // FIFO kuralı: Her zaman en üstteki (ilk gelen) sipariş alınır
        if (modelBekleyen.getRowCount() == 0) {
            lblBilgi.setForeground(Tema.METIN_SOLUK);
            lblBilgi.setText("Bekleyen sipariş bulunmuyor.");
            return;
        }
        Object[] ilk = new Object[4];
        for (int i = 0; i < 4; i++) {
            ilk[i] = modelBekleyen.getValueAt(0, i);
        }
        modelBekleyen.removeRow(0);
        modelHazirlaniyor.addRow(ilk);
        lblBilgi.setForeground(Tema.DURUM_HAZIRLANIYOR);
        lblBilgi.setText(ilk[0] + " numaralı sipariş FIFO sırasıyla mutfağa alındı.");
    }

    private void bekleyenSiparisiSil() {
        int secili = tabloBekleyen.getSelectedRow();
        if (secili == -1) {
            lblBilgi.setForeground(Tema.METIN_SOLUK);
            lblBilgi.setText("Silmek için Bekleyen listesinden bir sipariş seçin.");
            return;
        }
        String no = String.valueOf(modelBekleyen.getValueAt(secili, 0));
        modelBekleyen.removeRow(secili);
        lblBilgi.setForeground(Tema.HATA_KIRMIZI);
        lblBilgi.setText(no + " numaralı bekleyen sipariş silindi.");
    }

    private void siparisiTamamla() {
        int secili = tabloHazirlaniyor.getSelectedRow();
        if (secili == -1) {
            lblBilgi.setForeground(Tema.METIN_SOLUK);
            lblBilgi.setText("Tamamlamak için Hazırlanıyor listesinden bir sipariş seçin.");
            return;
        }
        Object[] satir = new Object[4];
        for (int i = 0; i < 4; i++) {
            satir[i] = modelHazirlaniyor.getValueAt(secili, i);
        }
        modelHazirlaniyor.removeRow(secili);
        modelTamamlandi.addRow(satir);
        lblBilgi.setForeground(Tema.DURUM_TAMAMLANDI);
        lblBilgi.setText(satir[0] + " numaralı sipariş tamamlandı.");
    }
}
