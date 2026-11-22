package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Kafe menü ürünlerini listeleme, ekleme, fiyat güncelleme ve silme ekranı.
 *
 * Not: Entegrasyon aşamasında bu işlemler Kişi 1'in BagliListe yapısına ve
 * Kişi 3'ün MenuServisi metodlarına bağlanacaktır.
 */
public class MenuPanel extends JPanel {

    private JTextField txtUrunAdi;
    private JTextField txtFiyat;
    private JLabel lblBilgi;

    private DefaultTableModel modelMenu;
    private JTable tabloMenu;
    private int siradakiUrunId = 3;
    private Runnable menuDegistiDinleyici;

    public MenuPanel() {
        setLayout(new BorderLayout(0, Tema.BOSLUK_16));
        setBackground(Tema.ARKA_PLAN);
        setBorder(BorderFactory.createEmptyBorder(Tema.BOSLUK_24, Tema.BOSLUK_24, Tema.BOSLUK_24, Tema.BOSLUK_24));

        add(Tema.sayfaBasligi("Menü"), BorderLayout.NORTH);

        JPanel icerik = new JPanel(new BorderLayout(0, Tema.BOSLUK_16));
        icerik.setBackground(Tema.ARKA_PLAN);
        icerik.add(formKartiOlustur(), BorderLayout.NORTH);
        icerik.add(tabloKartiOlustur(), BorderLayout.CENTER);

        add(icerik, BorderLayout.CENTER);
        ornekMenuyuYukle();
    }

    private JPanel formKartiOlustur() {
        JPanel kart = Tema.kartOlustur(Tema.BOSLUK_16);
        kart.setLayout(new BorderLayout(0, Tema.BOSLUK_12));

        JLabel baslik = new JLabel("Ürün bilgileri");
        baslik.setFont(Tema.BASLIK_ORTA);
        baslik.setForeground(Tema.METIN_BIRINCIL);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT, Tema.BOSLUK_12, 0));
        form.setOpaque(false);

        txtUrunAdi = Tema.metinKutusuOlustur(16);
        txtFiyat = Tema.metinKutusuOlustur(7);

        JButton btnEkle = Tema.butonBirincil("Ürün ekle");
        btnEkle.addActionListener(e -> urunEkle());

        JButton btnGuncelle = Tema.butonIkincil("Fiyat güncelle", Tema.METIN_BIRINCIL);
        btnGuncelle.addActionListener(e -> fiyatGuncelle());

        JButton btnSil = Tema.butonIkincil("Ürünü sil", Tema.HATA_KIRMIZI);
        btnSil.addActionListener(e -> urunSil());

        form.add(Tema.etiket("Ürün adı"));
        form.add(txtUrunAdi);
        form.add(Tema.etiket("Fiyat (TL)"));
        form.add(txtFiyat);
        form.add(btnEkle);
        form.add(btnGuncelle);
        form.add(btnSil);

        lblBilgi = new JLabel("Tablodan bir satır seçerek fiyatını güncelleyebilirsiniz.");
        lblBilgi.setFont(Tema.METIN_KUCUK);
        lblBilgi.setForeground(Tema.METIN_SOLUK);

        kart.add(baslik, BorderLayout.NORTH);
        kart.add(form, BorderLayout.CENTER);
        kart.add(lblBilgi, BorderLayout.SOUTH);
        return kart;
    }

    private JPanel tabloKartiOlustur() {
        JPanel kart = Tema.kartOlustur(Tema.BOSLUK_16);
        kart.setLayout(new BorderLayout(0, Tema.BOSLUK_12));

        JLabel baslik = new JLabel("Mevcut menü");
        baslik.setFont(Tema.BASLIK_ORTA);
        baslik.setForeground(Tema.METIN_BIRINCIL);

        String[] sutunlar = {"ID", "Ürün Adı", "Fiyat"};
        modelMenu = new DefaultTableModel(sutunlar, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabloMenu = new JTable(modelMenu);
        Tema.tabloHazirla(tabloMenu, 2);

        // Satıra tıklanınca ürün adı ve fiyat kutularını otomatik doldurur
        tabloMenu.getSelectionModel().addListSelectionListener(e -> {
            int secili = tabloMenu.getSelectedRow();
            if (!e.getValueIsAdjusting() && secili != -1) {
                txtUrunAdi.setText(String.valueOf(modelMenu.getValueAt(secili, 1)));
                String fiyatStr = String.valueOf(modelMenu.getValueAt(secili, 2)).replace(" TL", "");
                txtFiyat.setText(fiyatStr);
            }
        });

        JScrollPane kaydirma = new JScrollPane(tabloMenu);
        Tema.kaydirmaPaneliHazirla(kaydirma);

        kart.add(baslik, BorderLayout.NORTH);
        kart.add(kaydirma, BorderLayout.CENTER);
        return kart;
    }

    public void setMenuDegistiDinleyici(Runnable dinleyici) {
        this.menuDegistiDinleyici = dinleyici;
    }

    /** Sipariş panelindeki ürün seçme kutusu için güncel menü listesini döndürür. */
    public List<String> getUrunListesiMetin() {
        List<String> liste = new ArrayList<>();
        for (int i = 0; i < modelMenu.getRowCount(); i++) {
            String ad = String.valueOf(modelMenu.getValueAt(i, 1));
            String fiyat = String.valueOf(modelMenu.getValueAt(i, 2));
            liste.add(ad + " - " + fiyat);
        }
        return liste;
    }

    private void degisikligiBildir() {
        if (menuDegistiDinleyici != null) {
            menuDegistiDinleyici.run();
        }
    }

    private void ornekMenuyuYukle() {
        modelMenu.addRow(new Object[]{"1", "Simit", "25.00 TL"});
        modelMenu.addRow(new Object[]{"2", "Çay", "15.00 TL"});
    }

    private void urunEkle() {
        String ad = txtUrunAdi.getText().trim();
        String fiyatStr = txtFiyat.getText().trim().replace(",", ".");
        if (ad.isEmpty() || fiyatStr.isEmpty()) {
            lblBilgi.setForeground(Tema.HATA_KIRMIZI);
            lblBilgi.setText("Ürün adı ve fiyat alanları boş bırakılamaz.");
            return;
        }
        try {
            double fiyat = Double.parseDouble(fiyatStr);
            modelMenu.addRow(new Object[]{String.valueOf(siradakiUrunId++), ad, String.format("%.2f TL", fiyat).replace(",", ".")});
            txtUrunAdi.setText("");
            txtFiyat.setText("");
            lblBilgi.setForeground(Tema.DURUM_TAMAMLANDI);
            lblBilgi.setText(ad + " menüye eklendi.");
            degisikligiBildir();
        } catch (NumberFormatException ex) {
            lblBilgi.setForeground(Tema.HATA_KIRMIZI);
            lblBilgi.setText("Fiyat alanı geçerli bir sayı olmalıdır.");
        }
    }

    private void fiyatGuncelle() {
        int secili = tabloMenu.getSelectedRow();
        if (secili == -1) {
            lblBilgi.setForeground(Tema.METIN_SOLUK);
            lblBilgi.setText("Fiyat güncellemek için listeden bir ürün seçin.");
            return;
        }
        try {
            double yeniFiyat = Double.parseDouble(txtFiyat.getText().trim().replace(",", "."));
            modelMenu.setValueAt(String.format("%.2f TL", yeniFiyat).replace(",", "."), secili, 2);
            lblBilgi.setForeground(Tema.DURUM_TAMAMLANDI);
            lblBilgi.setText("Fiyat güncellendi; eski siparişlerin anlık fiyatı korunur.");
            degisikligiBildir();
        } catch (NumberFormatException ex) {
            lblBilgi.setForeground(Tema.HATA_KIRMIZI);
            lblBilgi.setText("Geçerli bir fiyat girin.");
        }
    }

    private void urunSil() {
        int secili = tabloMenu.getSelectedRow();
        if (secili == -1) {
            lblBilgi.setForeground(Tema.METIN_SOLUK);
            lblBilgi.setText("Silmek için listeden bir ürün seçin.");
            return;
        }
        String ad = String.valueOf(modelMenu.getValueAt(secili, 1));
        modelMenu.removeRow(secili);
        lblBilgi.setForeground(Tema.HATA_KIRMIZI);
        lblBilgi.setText(ad + " menüden kaldırıldı.");
        degisikligiBildir();
    }
}
