package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Uygulamanın ana penceresi ve başlangıç noktası.
 * Sol kenar çubuğu üzerinden paneller (CardLayout) arasında geçiş yapar.
 */
public class AnaPencere extends JFrame {

    private static final String KART_SIPARIS = "SIPARIS";
    private static final String KART_MENU = "MENU";

    private final CardLayout kartDuzeni = new CardLayout();
    private final JPanel icerikAlani = new JPanel(kartDuzeni);

    private NavButonu btnSiparis;
    private NavButonu btnMenu;

    public AnaPencere() {
        setTitle("Kafe Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1140, 720);
        setMinimumSize(new Dimension(940, 600));
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

    /** 224px genişliğinde sakin sol navigasyon panelini oluşturur. */
    private JPanel kenarCubuguOlustur() {
        JPanel kenar = new JPanel(new BorderLayout());
        kenar.setPreferredSize(new Dimension(224, 0));
        kenar.setBackground(Tema.KENAR_CUBUGU);
        Border sagCizgi = BorderFactory.createMatteBorder(0, 0, 0, 1, Tema.KENARLIK);
        Border icBosluk = BorderFactory.createEmptyBorder(Tema.BOSLUK_24, Tema.BOSLUK_16, Tema.BOSLUK_24, Tema.BOSLUK_16);
        kenar.setBorder(BorderFactory.createCompoundBorder(sagCizgi, icBosluk));

        // Üst marka alanı
        JPanel ustMarka = new JPanel(new BorderLayout());
        ustMarka.setBackground(Tema.KENAR_CUBUGU);
        ustMarka.setBorder(BorderFactory.createEmptyBorder(0, 10, Tema.BOSLUK_24, 0));

        JLabel lblAd = new JLabel("Kafe Demo");
        lblAd.setFont(Tema.BASLIK_BUYUK);
        lblAd.setForeground(Tema.METIN_BIRINCIL);
        ustMarka.add(lblAd, BorderLayout.WEST);

        // Menü butonları (4px dikey boşluk)
        JPanel menuListesi = new JPanel();
        menuListesi.setLayout(new BoxLayout(menuListesi, BoxLayout.Y_AXIS));
        menuListesi.setBackground(Tema.KENAR_CUBUGU);

        btnSiparis = new NavButonu("Sipariş Paneli");
        btnSiparis.addActionListener(e -> ekranDegistir(KART_SIPARIS, btnSiparis));

        btnMenu = new NavButonu("Menü");
        btnMenu.addActionListener(e -> ekranDegistir(KART_MENU, btnMenu));

        menuListesi.add(btnSiparis);
        menuListesi.add(Box.createVerticalStrut(4));
        menuListesi.add(btnMenu);

        kenar.add(ustMarka, BorderLayout.NORTH);
        kenar.add(menuListesi, BorderLayout.CENTER);
        return kenar;
    }

    private void ekranDegistir(String kartAdi, NavButonu aktifButon) {
        kartDuzeni.show(icerikAlani, kartAdi);
        btnSiparis.setAktif(btnSiparis == aktifButon);
        btnMenu.setAktif(btnMenu == aktifButon);
    }

    /** Sol kenar çubuğu için yumuşak köşeli, hover ve aktif şeritli navigasyon butonu. */
    private static class NavButonu extends JButton {
        private boolean aktif = false;
        private boolean hover = false;

        NavButonu(String yazi) {
            super(yazi);
            setFont(Tema.METIN_NORMAL);
            setForeground(Tema.METIN_IKINCIL);
            setHorizontalAlignment(SwingConstants.LEFT);
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
            setPreferredSize(new Dimension(190, 38));
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 12));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    hover = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    hover = false;
                    repaint();
                }
            });
        }

        void setAktif(boolean aktif) {
            this.aktif = aktif;
            setFont(aktif ? Tema.METIN_KALIN : Tema.METIN_NORMAL);
            setForeground(aktif ? Tema.METIN_BIRINCIL : Tema.METIN_IKINCIL);
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            if (aktif) {
                g2.setColor(Tema.AKTIF_SATIR);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), Tema.KOSE_KONTROL, Tema.KOSE_KONTROL);

                // Sol tarafta ince yuvarlak kehribar aktif işareti
                g2.setColor(Tema.VURGU);
                g2.fillRoundRect(0, 8, 3, getHeight() - 16, 3, 3);
            } else if (hover) {
                g2.setColor(Tema.YUZEY_HOVER);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), Tema.KOSE_KONTROL, Tema.KOSE_KONTROL);
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }

    public static void main(String[] args) {
        // Sistem yazı tipi yumuşatma ayarı
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        SwingUtilities.invokeLater(() -> {
            AnaPencere pencere = new AnaPencere();
            pencere.setVisible(true);
        });
    }
}
