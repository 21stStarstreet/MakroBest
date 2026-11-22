package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Uygulamanın renk, yazı tipi ve temel bileşen yardımcılarını tutar.
 * İlk hafta iskeleti için sade ve okunabilir tutulmuştur.
 */
public class Tema {

    // --- Renkler ---
    public static final Color ARKA_PLAN = new Color(0xFA, 0xF8, 0xF5);       // Sıcak kağıt
    public static final Color YUZEY = new Color(0xFF, 0xFF, 0xFF);           // Beyaz kart
    public static final Color KENAR_CUBUGU = new Color(0xF3, 0xEF, 0xEA);    // Sol menü
    public static final Color AKTIF_SATIR = new Color(0xE8, 0xE1, 0xD8);     // Seçili öğe
    public static final Color KENARLIK = new Color(0xE5, 0xDF, 0xD7);        // İnce kenarlık

    public static final Color METIN_BIRINCIL = new Color(0x1F, 0x1D, 0x1B);  // Ana metin
    public static final Color METIN_IKINCIL = new Color(0x5C, 0x55, 0x4E);   // İkincil metin
    public static final Color METIN_SOLUK = new Color(0x8C, 0x83, 0x7A);     // Soluk metin
    public static final Color VURGU = new Color(0xB4, 0x53, 0x09);           // Kehribar buton

    // --- Durum Renkleri ---
    public static final Color DURUM_BEKLEYEN = new Color(0xDC, 0x26, 0x26);     // Kırmızı
    public static final Color DURUM_HAZIRLANIYOR = new Color(0xEA, 0xB3, 0x08); // Sarı
    public static final Color DURUM_TAMAMLANDI = new Color(0x15, 0x80, 0x3D);   // Yeşil
    public static final Color HATA_KIRMIZI = new Color(0xB9, 0x1C, 0x1C);

    // --- Yazı Tipleri ---
    public static final Font BASLIK_BUYUK = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font BASLIK_ORTA = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font METIN_NORMAL = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font METIN_KALIN = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font METIN_KUCUK = new Font("Segoe UI", Font.PLAIN, 12);

    // --- Boşluklar ---
    public static final int BOSLUK_8 = 8;
    public static final int BOSLUK_12 = 12;
    public static final int BOSLUK_16 = 16;
    public static final int BOSLUK_24 = 24;

    private Tema() {
    }

    public static JPanel kartOlustur(int icBosluk) {
        JPanel kart = new JPanel();
        kart.setBackground(YUZEY);
        Border cizgi = BorderFactory.createLineBorder(KENARLIK, 1);
        Border dolgu = BorderFactory.createEmptyBorder(icBosluk, icBosluk, icBosluk, icBosluk);
        kart.setBorder(BorderFactory.createCompoundBorder(cizgi, dolgu));
        return kart;
    }

    public static JButton butonBirincil(String yazi) {
        JButton btn = new JButton(yazi);
        btn.setFont(METIN_KALIN);
        btn.setBackground(VURGU);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return btn;
    }

    public static JButton butonIkincil(String yazi, Color yaziRengi) {
        JButton btn = new JButton(yazi);
        btn.setFont(METIN_NORMAL);
        btn.setBackground(YUZEY);
        btn.setForeground(yaziRengi);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Border cizgi = BorderFactory.createLineBorder(KENARLIK, 1);
        Border dolgu = BorderFactory.createEmptyBorder(7, 14, 7, 14);
        btn.setBorder(BorderFactory.createCompoundBorder(cizgi, dolgu));
        return btn;
    }

    public static JTextField metinKutusuOlustur(int sutun) {
        JTextField alan = new JTextField(sutun);
        alan.setFont(METIN_NORMAL);
        alan.setForeground(METIN_BIRINCIL);
        alan.setBackground(YUZEY);
        Border cizgi = BorderFactory.createLineBorder(KENARLIK, 1);
        Border dolgu = BorderFactory.createEmptyBorder(6, 10, 6, 10);
        alan.setBorder(BorderFactory.createCompoundBorder(cizgi, dolgu));
        return alan;
    }

    public static void tabloHazirla(JTable tablo, int... sagaHizaliSutunlar) {
        tablo.setFont(METIN_NORMAL);
        tablo.setForeground(METIN_BIRINCIL);
        tablo.setBackground(YUZEY);
        tablo.setRowHeight(32);
        tablo.setShowVerticalLines(false);
        tablo.setGridColor(KENARLIK);
        tablo.setSelectionBackground(AKTIF_SATIR);
        tablo.setSelectionForeground(METIN_BIRINCIL);
        tablo.setFillsViewportHeight(true);

        tablo.getTableHeader().setFont(METIN_KALIN);
        tablo.getTableHeader().setForeground(METIN_IKINCIL);
        tablo.getTableHeader().setBackground(KENAR_CUBUGU);
        tablo.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer sagRenderer = new DefaultTableCellRenderer();
        sagRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        for (int sutunIndex : sagaHizaliSutunlar) {
            if (sutunIndex >= 0 && sutunIndex < tablo.getColumnCount()) {
                tablo.getColumnModel().getColumn(sutunIndex).setCellRenderer(sagRenderer);
            }
        }
    }

    public static JPanel sayfaBasligi(String baslikMetni) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(ARKA_PLAN);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, BOSLUK_16, 0));

        JLabel baslik = new JLabel(baslikMetni);
        baslik.setFont(BASLIK_BUYUK);
        baslik.setForeground(METIN_BIRINCIL);

        panel.add(baslik, BorderLayout.WEST);
        return panel;
    }

    public static JLabel etiket(String metin) {
        JLabel lbl = new JLabel(metin);
        lbl.setFont(METIN_KUCUK);
        lbl.setForeground(METIN_IKINCIL);
        return lbl;
    }
}
