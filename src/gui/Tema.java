package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Uygulamanın tüm görsel sabitlerini (renkler, yazı tipleri, boşluklar)
 * ve saf Java Swing (Graphics2D) ile çizilen modern bileşen yardımcılarını tutar.
 *
 * Tasarım yönü: Sıcak Kağıt & Kavrulmuş Espresso (4px ızgara düzeni).
 */
public class Tema {

    // --- Renk Paleti ---
    public static final Color ARKA_PLAN = new Color(0xFA, 0xF8, 0xF5);        // #FAF8F5 Sıcak kağıt
    public static final Color YUZEY = new Color(0xFF, 0xFF, 0xFF);            // #FFFFFF Kart / tablo zemini
    public static final Color YUZEY_HOVER = new Color(0xF7, 0xF3, 0xEE);      // #F7F3EE İkincil buton hover
    public static final Color YUZEY_BASILI = new Color(0xEF, 0xE9, 0xE1);     // #EFE9E1 İkincil buton basılı
    public static final Color KENAR_CUBUGU = new Color(0xF3, 0xEF, 0xEA);     // #F3EFEA Sol menü zemini
    public static final Color AKTIF_SATIR = new Color(0xE8, 0xE1, 0xD8);      // #E8E1D8 Seçili öğe
    public static final Color SATIR_ALTERNATIF = new Color(0xFC, 0xFB, 0xF9); // #FCFBF9 İnce zebra satır
    public static final Color KENARLIK = new Color(0xE5, 0xDF, 0xD7);         // #E5DFD7 İnce 1px çizgi
    public static final Color KAYDIRMA_CUBUGU = new Color(0xD6, 0xCF, 0xC7);  // #D6CFC7 Scrollbar rengi

    public static final Color METIN_BIRINCIL = new Color(0x1F, 0x1D, 0x1B);   // #1F1D1B Espresso koyu
    public static final Color METIN_IKINCIL = new Color(0x5C, 0x55, 0x4E);    // #5C554E Etiketler
    public static final Color METIN_SOLUK = new Color(0x8C, 0x83, 0x7A);      // #8C837A Alt bilgiler

    public static final Color VURGU = new Color(0xB4, 0x53, 0x09);            // #B45309 Kavrulmuş kehribar
    public static final Color VURGU_HOVER = new Color(0x92, 0x40, 0x0E);      // #92400E Hover kehribar
    public static final Color VURGU_BASILI = new Color(0x78, 0x35, 0x0F);     // #78350F Basılı kehribar

    // --- Anlamsal Durum Renkleri ---
    public static final Color DURUM_BEKLEYEN = new Color(0xDC, 0x26, 0x26);     // Kırmızı
    public static final Color DURUM_HAZIRLANIYOR = new Color(0xEA, 0xB3, 0x08); // Sarı
    public static final Color DURUM_TAMAMLANDI = new Color(0x15, 0x80, 0x3D);   // Yeşil
    public static final Color HATA_KIRMIZI = new Color(0xB9, 0x1C, 0x1C);       // #B91C1C

    // --- Yazı Tipleri ---
    private static final String FONT_ADI = "Segoe UI";
    public static final Font BASLIK_BUYUK = new Font(FONT_ADI, Font.BOLD, 20);
    public static final Font BASLIK_ORTA = new Font(FONT_ADI, Font.BOLD, 15);
    public static final Font METIN_NORMAL = new Font(FONT_ADI, Font.PLAIN, 13);
    public static final Font METIN_KALIN = new Font(FONT_ADI, Font.BOLD, 13);
    public static final Font METIN_KUCUK = new Font(FONT_ADI, Font.PLAIN, 12);

    // --- 4px Izgara Boşlukları ve Köşe Yarıçapları ---
    public static final int BOSLUK_8 = 8;
    public static final int BOSLUK_12 = 12;
    public static final int BOSLUK_16 = 16;
    public static final int BOSLUK_24 = 24;
    public static final int KOSE_KART = 12;
    public static final int KOSE_KONTROL = 8;

    private Tema() {
    }

    /** 12px yuvarlatılmış köşeli, hafif alt derinlikli ve ince kenarlıklı beyaz kart oluşturur. */
    public static JPanel kartOlustur(int icBosluk) {
        JPanel kart = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                // Çok hafif sıcak alt gölge (1px derinlik hissi)
                g2.setColor(new Color(0x1F, 0x1D, 0x1B, 14));
                g2.fillRoundRect(0, 2, w - 1, h - 3, KOSE_KART, KOSE_KART);

                // Kart gövdesi
                g2.setColor(YUZEY);
                g2.fillRoundRect(0, 0, w - 1, h - 2, KOSE_KART, KOSE_KART);

                // İnce 1px kenarlık
                g2.setColor(KENARLIK);
                g2.drawRoundRect(0, 0, w - 1, h - 2, KOSE_KART, KOSE_KART);

                g2.dispose();
            }
        };
        kart.setOpaque(false);
        kart.setBorder(BorderFactory.createEmptyBorder(icBosluk, icBosluk, icBosluk + 1, icBosluk));
        return kart;
    }

    /** 8px yuvarlatılmış, hover ve basılma efektli kehribar ana buton oluşturur. */
    public static JButton butonBirincil(String yazi) {
        return yuvarlakButonOlustur(yazi, VURGU, VURGU_HOVER, VURGU_BASILI, Color.WHITE, null, METIN_KALIN);
    }

    /** 8px yuvarlatılmış, ince kenarlıklı ve hover efektli ikincil buton oluşturur. */
    public static JButton butonIkincil(String yazi, Color yaziRengi) {
        return yuvarlakButonOlustur(yazi, YUZEY, YUZEY_HOVER, YUZEY_BASILI, yaziRengi, KENARLIK, METIN_NORMAL);
    }

    /** Birincil ve ikincil butonlar için ortak Graphics2D yuvarlak buton üreticisi. */
    private static JButton yuvarlakButonOlustur(
            String yazi, Color normal, Color hover, Color basili, Color yaziRengi, Color kenarlik, Font font) {

        JButton btn = new JButton(yazi) {
            private boolean fareUzerinde = false;
            private boolean fareBasili = false;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        fareUzerinde = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        fareUzerinde = false;
                        fareBasili = false;
                        repaint();
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        fareBasili = true;
                        repaint();
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        fareBasili = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color zemin = fareBasili ? basili : (fareUzerinde ? hover : normal);
                g2.setColor(zemin);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, KOSE_KONTROL, KOSE_KONTROL);

                if (kenarlik != null) {
                    g2.setColor(kenarlik);
                    g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, KOSE_KONTROL, KOSE_KONTROL);
                }

                g2.dispose();
                super.paintComponent(g);
            }
        };

        btn.setFont(font);
        btn.setForeground(yaziRengi);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        return btn;
    }

    /** 8px yuvarlatılmış, odaklanıldığında kehribar kenarlık alan modern metin kutusu oluşturur. */
    public static JTextField metinKutusuOlustur(int sutun) {
        JTextField alan = new JTextField(sutun) {
            private boolean odakli = false;

            {
                addFocusListener(new FocusAdapter() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        odakli = true;
                        repaint();
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        odakli = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(YUZEY);
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, KOSE_KONTROL, KOSE_KONTROL);

                g2.setColor(odakli ? VURGU : KENARLIK);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, KOSE_KONTROL, KOSE_KONTROL);

                g2.dispose();
                super.paintComponent(g);
            }
        };
        alan.setOpaque(false);
        alan.setFont(METIN_NORMAL);
        alan.setForeground(METIN_BIRINCIL);
        alan.setCaretColor(METIN_BIRINCIL);
        alan.setBorder(BorderFactory.createEmptyBorder(7, 10, 7, 10));
        return alan;
    }

    /** JComboBox bileşenini işletim sisteminin kaba görünümünden kurtarıp karta uyumlu yapar. */
    public static <T> void acilirListeHazirla(JComboBox<T> combo) {
        combo.setFont(METIN_NORMAL);
        combo.setForeground(METIN_BIRINCIL);
        combo.setBackground(YUZEY);
        combo.setFocusable(false);
        combo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        combo.setBorder(BorderFactory.createCompoundBorder(
                new YuvarlakKenarlik(KENARLIK, KOSE_KONTROL),
                BorderFactory.createEmptyBorder(3, 8, 3, 4)));

        combo.setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton ok = new JButton() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(YUZEY);
                        g2.fillRect(0, 0, getWidth(), getHeight());

                        // İnce minimal aşağı ok (chevron)
                        int cx = getWidth() / 2;
                        int cy = getHeight() / 2;
                        g2.setColor(METIN_IKINCIL);
                        g2.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                        g2.drawLine(cx - 4, cy - 2, cx, cy + 2);
                        g2.drawLine(cx, cy + 2, cx + 4, cy - 2);
                        g2.dispose();
                    }
                };
                ok.setBorder(BorderFactory.createEmptyBorder());
                ok.setContentAreaFilled(false);
                ok.setFocusPainted(false);
                return ok;
            }

            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                g.setColor(YUZEY);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });

        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel lbl = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                lbl.setFont(METIN_NORMAL);
                lbl.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
                lbl.setBackground(isSelected ? AKTIF_SATIR : YUZEY);
                lbl.setForeground(METIN_BIRINCIL);
                return lbl;
            }
        });
    }

    /** JSpinner bileşenini sade, ince kenarlıklı ve diğer form elemanlarıyla aynı yüksekliğe getirir. */
    public static void sayacHazirla(JSpinner spinner) {
        spinner.setFont(METIN_NORMAL);
        spinner.setPreferredSize(new Dimension(70, 34));
        spinner.setBackground(YUZEY);
        spinner.setBorder(new YuvarlakKenarlik(KENARLIK, KOSE_KONTROL));

        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor defaultEditor) {
            JTextField tf = defaultEditor.getTextField();
            tf.setFont(METIN_NORMAL);
            tf.setForeground(METIN_BIRINCIL);
            tf.setBackground(YUZEY);
            tf.setBorder(BorderFactory.createEmptyBorder(4, 6, 4, 6));
        }
    }

    /** JScrollPane için ince, modern kaydırma çubuğu ve temiz kenarlık uygular. */
    public static void kaydirmaPaneliHazirla(JScrollPane kaydirma) {
        kaydirma.setBorder(BorderFactory.createLineBorder(KENARLIK, 1));
        kaydirma.getViewport().setBackground(YUZEY);
        kaydirma.setBackground(YUZEY);

        kaydirma.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        kaydirma.getVerticalScrollBar().setUI(inceKaydirmaCubugu());
        kaydirma.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 8));
        kaydirma.getHorizontalScrollBar().setUI(inceKaydirmaCubugu());
    }

    private static BasicScrollBarUI inceKaydirmaCubugu() {
        return new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = KAYDIRMA_CUBUGU;
                trackColor = YUZEY;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return bosButon();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return bosButon();
            }

            private JButton bosButon() {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(0, 0));
                return b;
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
                if (r.isEmpty() || !scrollbar.isEnabled()) return;
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(isDragging ? METIN_SOLUK : thumbColor);
                g2.fillRoundRect(r.x + 1, r.y + 2, r.width - 2, r.height - 4, 6, 6);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle r) {
                g.setColor(YUZEY);
                g.fillRect(r.x, r.y, r.width, r.height);
            }
        };
    }

    /** Durum başlıklarının yanına Graphics2D ile düz, tek renk yuvarlak durum noktası çizer. */
    public static JComponent durumNoktasi(Color renk) {
        JComponent nokta = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(renk);
                g2.fillOval(0, 0, 10, 10);
                g2.dispose();
            }
        };
        nokta.setPreferredSize(new Dimension(10, 10));
        nokta.setMinimumSize(new Dimension(10, 10));
        nokta.setMaximumSize(new Dimension(10, 10));
        return nokta;
    }

    /** Tabloları temiz, okunaklı, ferah satırlı ve düz başlıklı hale getirir. */
    public static void tabloHazirla(JTable tablo, int... sagaHizaliSutunlar) {
        tablo.setFont(METIN_NORMAL);
        tablo.setForeground(METIN_BIRINCIL);
        tablo.setBackground(YUZEY);
        tablo.setRowHeight(36);
        tablo.setIntercellSpacing(new Dimension(0, 0));
        tablo.setShowVerticalLines(false);
        tablo.setShowHorizontalLines(true);
        tablo.setGridColor(KENARLIK);
        tablo.setSelectionBackground(AKTIF_SATIR);
        tablo.setSelectionForeground(METIN_BIRINCIL);
        tablo.setFillsViewportHeight(true);

        JTableHeader baslik = tablo.getTableHeader();
        baslik.setReorderingAllowed(false);
        baslik.setPreferredSize(new Dimension(baslik.getPreferredSize().width, 36));
        baslik.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable t, Object val, boolean isSel, boolean hasFocus, int row, int col) {
                JLabel lbl = (JLabel) super.getTableCellRendererComponent(t, val, isSel, hasFocus, row, col);
                lbl.setFont(METIN_KUCUK.deriveFont(Font.BOLD));
                lbl.setForeground(METIN_IKINCIL);
                lbl.setBackground(KENAR_CUBUGU);
                lbl.setOpaque(true);
                lbl.setHorizontalAlignment(sagaHizaliMi(col, sagaHizaliSutunlar) ? SwingConstants.RIGHT : SwingConstants.LEFT);
                lbl.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, KENARLIK),
                        BorderFactory.createEmptyBorder(0, 12, 0, 12)));
                return lbl;
            }
        });

        // Hücre renderer: ferah iç boşluk ve hafif zebra satır tonu
        for (int i = 0; i < tablo.getColumnCount(); i++) {
            final boolean sagaHizali = sagaHizaliMi(i, sagaHizaliSutunlar);
            tablo.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(
                        JTable t, Object val, boolean isSel, boolean hasFocus, int row, int col) {
                    JLabel lbl = (JLabel) super.getTableCellRendererComponent(t, val, isSel, false, row, col);
                    lbl.setFont(METIN_NORMAL);
                    lbl.setHorizontalAlignment(sagaHizali ? SwingConstants.RIGHT : SwingConstants.LEFT);
                    lbl.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));
                    if (isSel) {
                        lbl.setBackground(AKTIF_SATIR);
                        lbl.setForeground(METIN_BIRINCIL);
                    } else {
                        lbl.setBackground(row % 2 == 0 ? YUZEY : SATIR_ALTERNATIF);
                        lbl.setForeground(METIN_BIRINCIL);
                    }
                    return lbl;
                }
            });
        }
    }

    private static boolean sagaHizaliMi(int col, int[] sagaHizaliSutunlar) {
        for (int s : sagaHizaliSutunlar) {
            if (s == col) return true;
        }
        return false;
    }

    /** Sayfa üst başlık alanını oluşturur. */
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

    /** Basit etiket oluşturur. */
    public static JLabel etiket(String metin) {
        JLabel lbl = new JLabel(metin);
        lbl.setFont(METIN_KUCUK);
        lbl.setForeground(METIN_IKINCIL);
        return lbl;
    }

    /** ComboBox ve Spinner gibi kontroller için sade yuvarlak kenarlık sınıfı. */
    private static class YuvarlakKenarlik implements Border {
        private final Color renk;
        private final int yaricap;

        YuvarlakKenarlik(Color renk, int yaricap) {
            this.renk = renk;
            this.yaricap = yaricap;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(renk);
            g2.drawRoundRect(x, y, width - 1, height - 1, yaricap, yaricap);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(1, 1, 1, 1);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
