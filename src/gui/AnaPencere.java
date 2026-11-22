package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Uygulamanın ana penceresi ve başlangıç noktası.
 * Sol menü üzerinden Sipariş Paneli ve Menü ekranları arasında geçiş yapar.
 */
public class AnaPencere extends JFrame {

    private static final String KART_SIPARIS = "SIPARIS";
    private static final String KART_MENU = "MENU";

    private final CardLayout kartDuzeni = new CardLayout();
    private final JPanel icerikAlani = new JPanel(kartDuzeni);

    private JButton btnSiparis;
    private JButton btnMenu;

    public AnaPencere() {
        setTitle("Kafe Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 700);
        setMinimumSize(new Dimension(900, 580));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(kenarCubuguOlustur(), BorderLayout.WEST);

        SiparisPanel siparisPanel = new SiparisPanel();
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.setMenuDegistiDinleyici(() -> siparisPanel.urunListesiniGuncelle(menuPanel.getUrunListesiMetin()));

        icerikAlani.setBackground(Tema.ARKA_PLAN);
        icerikAlani.add(siparisPanel, KART_SIPARIS);
        icerikAlani.add(menuPanel, KART_MENU);
        add(icerikAlani, BorderLayout.CENTER);

        ekranDegistir(KART_SIPARIS, btnSiparis);
    }

    private JPanel kenarCubuguOlustur() {
        JPanel kenar = new JPanel(new BorderLayout());
        kenar.setPreferredSize(new Dimension(220, 0));
        kenar.setBackground(Tema.KENAR_CUBUGU);
        Border sagCizgi = BorderFactory.createMatteBorder(0, 0, 0, 1, Tema.KENARLIK);
        Border icBosluk = BorderFactory.createEmptyBorder(Tema.BOSLUK_24, Tema.BOSLUK_16, Tema.BOSLUK_24, Tema.BOSLUK_16);
        kenar.setBorder(BorderFactory.createCompoundBorder(sagCizgi, icBosluk));

        JLabel lblAd = new JLabel("Kafe Demo");
        lblAd.setFont(Tema.BASLIK_BUYUK);
        lblAd.setForeground(Tema.METIN_BIRINCIL);
        lblAd.setBorder(BorderFactory.createEmptyBorder(0, 8, Tema.BOSLUK_24, 0));

        JPanel menuListesi = new JPanel();
        menuListesi.setLayout(new BoxLayout(menuListesi, BoxLayout.Y_AXIS));
        menuListesi.setBackground(Tema.KENAR_CUBUGU);

        btnSiparis = menuButonuOlustur("Sipariş Paneli", KART_SIPARIS);
        btnMenu = menuButonuOlustur("Menü", KART_MENU);

        menuListesi.add(btnSiparis);
        menuListesi.add(Box.createVerticalStrut(4));
        menuListesi.add(btnMenu);

        kenar.add(lblAd, BorderLayout.NORTH);
        kenar.add(menuListesi, BorderLayout.CENTER);
        return kenar;
    }

    private JButton menuButonuOlustur(String yazi, String kartAdi) {
        JButton btn = new JButton(yazi);
        btn.setFont(Tema.METIN_NORMAL);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        btn.setPreferredSize(new Dimension(188, 36));
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> ekranDegistir(kartAdi, btn));
        return btn;
    }

    private void ekranDegistir(String kartAdi, JButton aktifButon) {
        kartDuzeni.show(icerikAlani, kartAdi);

        JButton[] butonlar = {btnSiparis, btnMenu};
        for (JButton b : butonlar) {
            boolean secili = (b == aktifButon);
            b.setBackground(secili ? Tema.AKTIF_SATIR : Tema.KENAR_CUBUGU);
            b.setForeground(secili ? Tema.METIN_BIRINCIL : Tema.METIN_IKINCIL);
            b.setFont(secili ? Tema.METIN_KALIN : Tema.METIN_NORMAL);

            Border solSerit = BorderFactory.createMatteBorder(0, secili ? 3 : 0, 0, 0, Tema.VURGU);
            Border dolgu = BorderFactory.createEmptyBorder(6, secili ? 9 : 12, 6, 12);
            b.setBorder(BorderFactory.createCompoundBorder(solSerit, dolgu));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnaPencere pencere = new AnaPencere();
            pencere.setVisible(true);
        });
    }
}
